package org.example.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

public class BookIdToRemove {

    @NotEmpty(message = "The ID book should not be empty")
    @Pattern(regexp = "^[0-9]+$", message = "The ID book should be number")
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
