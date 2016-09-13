package lc;

/**
 * Created by kpan on 9/12/16.
 */
public class L283MoveZeroes {
    public void moveZeroes(int[] input) {
        if (input == null || input.length <= 1) {
            return;
        }
        int length = input.length;
        int i = 0;
        int j = 0;

        while (i < length && j < length) {
            if (input[i] == 0 && input[j] != 0) {
                int temp = input[i];
                input[i] = input[j];
                input[j] = temp;
            }
            if (input[i] != 0) {
                i++;
            }
            j++;
        }
    }
}
