package com.company;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Expression {

    public String getExpression() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String string = "";
        try {
            string = reader.readLine().replaceAll(" ", "");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return string;
    }

    private int getA(String string, boolean isArabicNumbers) {
        Pattern pattern = Pattern.compile("^\\w+");
        Matcher matcher = pattern.matcher(string);
        String s1 = matcher.find() ? matcher.group() : "";

        if (isArabicNumbers) {
            int a = Integer.parseInt(s1);
            if (Integer.parseInt(s1) > 10 || Integer.parseInt(s1) < 1) {
                throw new RuntimeException("Первое число должно быть в диапазоне от 1 до 10");
            }
            return a;
        } else {
            if(Numbers.romanToArabic(s1) > 10 || Numbers.romanToArabic(s1) < 1){
                throw new RuntimeException("Первое число должно быть в диапазоне от 1 до 10");
            }
            return Numbers.romanToArabic(s1);
        }
    }

    private int getB(String string, boolean isArabicNumbers){
        Pattern pattern = Pattern.compile("\\w+$");
        Matcher matcher = pattern.matcher(string);
        String s1 = matcher.find() ? matcher.group() : "";

        if (isArabicNumbers) {
            int a = Integer.parseInt(s1);
            if (Integer.parseInt(s1) > 10 || Integer.parseInt(s1) < 1) {
                throw new RuntimeException("Второе число должно быть в диапазоне от 1 до 10");
            }
            return a;
        } else {
            if(Numbers.romanToArabic(s1) > 10 || Numbers.romanToArabic(s1) < 1){
                throw new RuntimeException("Второе число должно быть в диапазоне от 1 до 10");
            }
            return Numbers.romanToArabic(s1);
        }
    }

    public String getResultExpression(String string){
        int result = 0;
        Numbers numbers = new Numbers();
        Operation operations = new Operation();
        boolean isArabicNumbers = numbers.getFormatNumbers(string);
        int a = getA(string, isArabicNumbers);
        int b = getB(string, isArabicNumbers);


        switch (operations.getOperation(string, isArabicNumbers)) {
            case "+":
                result = a + b;
                break;
            case "*":
                result = a * b;
                break;
            case "-":
                result = a - b;
                break;
            case "/":
                result = a / b;
                break;
            default:
                throw new RuntimeException("Оператор не соответствует доступным: " +
                        "-, +, *, /");
        }

        if(!isArabicNumbers){
            return numbers.arabicToRoman(result);
        }

        return String.valueOf(result);
    }
}