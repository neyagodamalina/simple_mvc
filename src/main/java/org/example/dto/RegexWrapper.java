package org.example.dto;

import org.example.validators.RegexSyntaxOk;

import javax.validation.constraints.NotEmpty;

@RegexSyntaxOk
public class RegexWrapper {

    @NotEmpty(message = "The query regex should not be empty")
    private String queryRegEx;

    public String getQueryRegEx() {
        return queryRegEx;
    }

    public void setQueryRegEx(String queryRegEx) {
        this.queryRegEx = queryRegEx;
    }
}
