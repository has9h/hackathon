package com.example.fakenews;

public class FakeHeadlineDetector {
    public static int howManyChars(String headline){
        int charCount = 0;
        for (int i=0; i<headline.length(); i++){
            char ch = headline.charAt(i);
            if (Character.isLetter(ch)){
                charCount++;
            }
        }
        return charCount;
    }

    public static double howCapitalized(String headline, int charCount){
        int capCount = 0;
        for (int i=0; i<headline.length(); i++){
            Character ch = headline.charAt(i);
            if (Character.isLetter(ch) && ch.equals(Character.toUpperCase(ch))){
                capCount++;
            }
        }
        double propCaps = capCount/charCount;
        // if less than 0.3 of chars are capitalized
        // based on fact that avg. word length is 5.1
        // so ~0.2 of chars in headline should be capitalized
        // i.e. if less than 0.2 of chars are capitalized,
        // degree of capitalization likely not a problem,
        // hence return 0
        if (propCaps < 0.3){
            return 0;
        }
        return propCaps;
    }

    public static double howExclamatory(String headline){
        int exclamCount = 0;
        for (int i=0; i<headline.length(); i++){
            Character ch = headline.charAt(i);
            if (ch.equals('!')){
                exclamCount++;
            }
        }
        if (exclamCount == 0){
            return 0;
        }
        if (exclamCount == 1){
            return 0.5;
        }
        return 1;
    }

    public static double phraseCount(){
    }

    public static double fakeHeadlineIndex(String headline){
        int numChars = howManyChars(headline);
        double propCaps = howCapitalized(headline, numChars);
        double capIndex = propCaps*50;
        double propExclam = howExclamatory(headline);
        double exclamIndex = propExclam*50;
        return capIndex+exclamIndex;
    }

    public static void main(String[] args) {
        String headline = JOptionPane.showInputDialog("Enter the full headline.");
        System.out.println("The apparent, face-value fakeness of this news headline is "
                +fakeHeadlineIndex(headline)+" out of 100.0 points");
    }
}



