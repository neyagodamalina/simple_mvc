package org.example.web.controllers;

import org.apache.log4j.Logger;
import org.example.app.services.BookService;
import org.example.dto.RegexWrapper;
import org.example.dto.Book;
import org.example.dto.BookIdToRemove;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;


@Controller
@RequestMapping(value = "books")
@Scope("singleton")
public class BookShelfController {
    private Logger logger = Logger.getLogger(BookShelfController.class);
    private BookService bookService;

    @Autowired
    public BookShelfController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/shelf")
    public String books(Model model){
        model.addAttribute("book", new Book());
        model.addAttribute("bookIdToRemove", new BookIdToRemove());
        model.addAttribute("regexWrapper", new RegexWrapper());
        model.addAttribute("bookList", bookService.getAllBooks());
        return "book_shelf";
    }

    @PostMapping("/save")
    public String saveBook(@Valid Book book, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()){
            model.addAttribute("book", book);
            model.addAttribute("bookIdToRemove", new BookIdToRemove());
            model.addAttribute("regexWrapper", new RegexWrapper());
            model.addAttribute("bookList", bookService.getAllBooks());
            return "book_shelf";
        }else {
            //if (!"".equals(book.getAuthor()) || !"".equals(book.getTitle()) || (book.getSize() != null)) {
                bookService.saveBook(book);
                logger.info("current repository size: " + bookService.getAllBooks().size());
            //}
            return "redirect:/books/shelf";
        }


    }

    @PostMapping("/remove")
    public String removeBook(@Valid BookIdToRemove bookIdToRemove, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()){
            model.addAttribute("book", new Book());
            model.addAttribute("regexWrapper", new RegexWrapper());
            model.addAttribute("bookList", bookService.getAllBooks());
            return "book_shelf";
        }else {
            bookService.removeBookById(Integer.valueOf(bookIdToRemove.getId()));
            return "redirect:/books/shelf";
        }
    }

    @PostMapping("/removeByRegex")
    public String removeByRegex(@Valid RegexWrapper regexWrapper, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()){
            model.addAttribute("book", new Book());
            model.addAttribute("bookIdToRemove", new BookIdToRemove());
            model.addAttribute("bookList", bookService.getAllBooks());
            return "book_shelf";
        }else {
            bookService.removeBookByRegex(regexWrapper.getQueryRegEx());
            return "redirect:/books/shelf";
        }

    }

    @PostMapping("/uploadFile")
    public String uploadFile(@RequestParam("file") MultipartFile multipartFile, Model model) throws Exception{
        String name = multipartFile.getOriginalFilename();
        byte[] bytes = multipartFile.getBytes();
        if ("".equals(name)){
            model.addAttribute("notSelectedFile", Boolean.TRUE);
            model.addAttribute("book", new Book());
            model.addAttribute("bookIdToRemove", new BookIdToRemove());
            model.addAttribute("bookList", bookService.getAllBooks());
            model.addAttribute("regexWrapper", new RegexWrapper());
            return "book_shelf";
            //return "redirect:/books/shelf";
            //throw new NotSelectedUploadFile();
        }
        // create directory
        String rootPath = System.getProperty("catalina.home");
        File dir = new File(rootPath + File.separator + "external_uploads");
        if (!dir.exists()) dir.mkdir();

        // create file
        File serverFile = new File(dir.getAbsolutePath() + File.separator + name);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(serverFile));
        bufferedOutputStream.write(bytes);
        bufferedOutputStream.close();

        logger.info("new file saved at: " + serverFile.getAbsolutePath());

        return "redirect:/books/shelf";
    }
}
