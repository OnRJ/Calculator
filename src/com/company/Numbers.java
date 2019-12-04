package com.company;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Numbers {

    public boolean getFormatNumbers(String string){
        Pattern patternForRimNumbers = Pattern.compile("[a-zA-Z]");
        Pattern patternForArabicNumbers = Pattern.compile("[0-9]");
        Pattern patternFloatNumbers = Pattern.compile("[.,]");
        Pattern patternNegativeNumbers1 = Pattern.compile("(\\*-|\\/-|\\--|\\+-)");
        Pattern patternNegativeNumbers2 = Pattern.compile("^-.");

            if((patternNegativeNumbers1.matcher(string)).find() || (patternNegativeNumbers2.matcher(string)).find()){
                throw new RuntimeException("Числа должны быть в диапазоне от 1 до 10");
            }

            if((patternFloatNumbers.matcher(string)).find()){
                throw new RuntimeException("Число должно быть целое");
            }

            if((patternForRimNumbers.matcher(string)).find()
                   && (patternForArabicNumbers.matcher(string)).find()){
                throw new RuntimeException("Все числа в выражение должны быть арабскими, либо римскими");
            } else if((patternForArabicNumbers.matcher(string)).find()){
                return true;
            } else {
               return false;
           }
    }

    // понять и простить
    enum RomanNumeral {
        I(1), IV(4), V(5), IX(9), X(10),
        XL(40), L(50), XC(90), C(100),
        CD(400), D(500), CM(900), M(1000);

        private int value;

        RomanNumeral(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public static List<RomanNumeral> getReverseSortedValues() {
            return Arrays.stream(values())
                    .sorted(Comparator.comparing((RomanNumeral e) -> e.value).reversed())
                    .collect(Collectors.toList());
        }
    }

    // понять и простить
    public static int romanToArabic(String input) {
        String romanNumeral = input.toUpperCase();
        int result = 0;

        List<RomanNumeral> romanNumerals = RomanNumeral.getReverseSortedValues();

        int i = 0;

        while ((romanNumeral.length() > 0) && (i < romanNumerals.size())) {
            RomanNumeral symbol = romanNumerals.get(i);
            if (romanNumeral.startsWith(symbol.name())) {
                result += symbol.getValue();
                romanNumeral = romanNumeral.substring(symbol.name().length());
            } else {
                i++;
            }
        }

        if (romanNumeral.length() > 0) {
            throw new IllegalArgumentException(input
                    + " cannot be converted to a Roman Numeral");
        }

        return result;
    }
}