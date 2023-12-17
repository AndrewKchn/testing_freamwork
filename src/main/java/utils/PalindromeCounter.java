package utils;

import java.util.HashMap;
import java.util.Map;

public class PalindromeCounter {
    String target;
    PalindromeValidator validator;
    Map<String, Integer> count;

    public PalindromeCounter string(String s) {
        this.target = s;
        this.validator = new PalindromeValidator();
        this.count = new HashMap<>();
        return this;
    }

    public Map<String, Integer> containsPalindromeAmount() {
        char[] charArray = this.target.toCharArray();
        int first = 0;
        int last = charArray.length;
        while (first < last) {
            String tempString = this.target.substring(first, last);
            System.out.println(tempString);
            if (validator.string(tempString).isPalindrome()) {
                addToCount(tempString);
                System.out.println("=== Palindrome - " + tempString);
                first = last;
                last = charArray.length;
            } else {
                if (first == last - 1) {
                    first++;
                    last = charArray.length;
                } else {
                    last--;
                }
            }
        }
        return count;
    }

    private void addToCount(String tempString) {
        if (count.get(tempString) == null) {
            count.put(tempString, 1);
        } else {
            count.put(tempString, count.get(tempString) + 1);
        }
    }
}
