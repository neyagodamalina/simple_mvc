package org.example.validators;

import org.example.dto.RegexWrapper;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class RegexSyntaxValidator implements ConstraintValidator<RegexSyntaxOk, RegexWrapper> {
   public void initialize(RegexSyntaxOk constraint) {
   }

   public boolean isValid(RegexWrapper regexWrapper, ConstraintValidatorContext context) {
      try {
         Pattern.compile(regexWrapper.getQueryRegEx());
      }catch (PatternSyntaxException ex){
         context.disableDefaultConstraintViolation();
         context.buildConstraintViolationWithTemplate(
                 "Syntax error in a regular-expression pattern")
                 .addPropertyNode("queryRegEx").addConstraintViolation();
         return false;
      }
      return true;
   }
}
