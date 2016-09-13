package lc;

/**
 * Created by kpan on 9/9/16.
 */
public class L345ReverseVowelsofaString {
    public String reverseVowels(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        int i = 0;
        int j = s.length() - 1;
        char[] chars = s.toCharArray();

        while (i < j && i < s.length() && j >= 0) {
            if (isVowel(chars[i]) && isVowel(chars[j])) {
                char tmp = chars[i];
                chars[i] = chars[j];
                chars[j] = tmp;
                i++;
                j--;
            } else {
                if (!isVowel(chars[i])) {
                    i ++;
                }
                if (!isVowel(chars[j])) {
                    j --;
                }
            }
        }
        return String.valueOf(chars);
    }

    private boolean isVowel(char c) {
        return c=='a' || c=='e' || c=='i' || c== 'o' || c=='u' || c=='A' || c=='E' || c=='I' || c== 'O' || c=='U';
    }
}
