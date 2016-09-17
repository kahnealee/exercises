package lc;

/**
 * Created by kpan on 9/16/16.
 */
public class L151ReverseWordsinaString {
    // need rewrite
    public String reverseWords(String s) {
        if (s.length() == 0) {
            return s;
        }
        char[] charArray = s.toCharArray();
        int length = s.length();
        int wordCount = 0;
        int spaceCount = 0;
        reverse(charArray, 0, length-1);
        int i = 0;
        int j = 0;

        while (j < length && charArray[j] == ' ') {
            j++;
            spaceCount++;
        }
        i = j;
        while (i < length) {
            while (j < length && charArray[j] != ' ') {
                j++;
            }
            reverse(charArray, i, j-1);
            wordCount ++;
            while (j < length && charArray[j] == ' ') {
                j++;
                spaceCount++;
            }
            i = j;
        }

        int extraSpace = spaceCount - (wordCount-1);
        if (extraSpace > length) {
            extraSpace = length;
        }
        char[] newCharArray = new char[length-extraSpace];
        i = 0;
        j = 0;
        while (i < length && charArray[i] == ' ') {
            i++;
        }
        while (i < length && j < newCharArray.length) {
            if (charArray[i] != ' ' || charArray[i-1] != ' ') {
                newCharArray[j++] = charArray[i];
            }
            i++;
        }
        return new String(newCharArray);
    }

    public void reverse(char[] s, int start, int end) {
        int i = start;
        int j = end;
        while (i < j) {
            char temp = s[i];
            s[i] = s[j];
            s[j] = temp;
            i++;
            j--;
        }
    }
}
