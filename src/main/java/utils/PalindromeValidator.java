package utils;

public class PalindromeValidator {
    String s;

    public PalindromeValidator string(String string) {
        this.s = string;
        return this;
    }

    public boolean isPalindrome() {
        if (s.length() < 2) {
            return false;
        }
        char[] charArray = this.s.toCharArray();
        for (int i = 0; i < charArray.length / 2; i++) {
            int last = charArray.length - 1 - i;
            if (charArray[i] != charArray[last]) {
                return false;
            }
        }
        return true;
    }

    public PalindromeValidator integer(int i) {
        this.s = Integer.valueOf(i).toString();
        return this;
    }
}
