package lc;

/**
 * Created by kpan on 9/9/16.
 */
public class L342PowerofFour {
    public boolean isPowerOfFour(int num) {
        // first, the number must be power of two. using (n & n - 1) to count 1s in binary string.
        //Secondly, we must make sure that the only bit 1 appears at some odd position. So using a mask 0x55555555.

        // 10000 & 1111
        // 10000 & 10101010101010101010101010101010

        if (num < 1 || (num & (num-1)) != 0) {
            return false;
        }

        if ((num & 0x55555555) != num) {
            return false;
        }
        return true;
    }
}
