package com.company;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Operation {
    private String operation;

    public String getOperation(String string, Boolean isArabicNumbers) {
        operation = parseOperation(string, isArabicNumbers);
        return operation;
    }

    private String parseOperation(String string, Boolean isArabicNumbers){
        Pattern pattern;

        if(isArabicNumbers){
            pattern = Pattern.compile("\\D");
        } else {
            pattern = Pattern.compile("\\W");
        }
        Matcher matcher = pattern.matcher(string);
        return matcher.find() ? matcher.group(): "";
    }
}
