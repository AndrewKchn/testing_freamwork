package org.example;


import utils.PalindromeCounter;
import utils.PalindromeValidator;

public class Main {
    public static void main(String[] args) {
        PalindromeValidator validator = new PalindromeValidator();

        System.out.println(validator.string("aa1aa").isPalindrome());

        PalindromeCounter counter = new PalindromeCounter();
        System.out.println(counter.string("asddsa1qwerrewq21qwerrewq").containsPalindromeAmount());
    }
}

