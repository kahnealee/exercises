package lc;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.*;
import java.util.Map;

/**
 * Created by kpan on 9/9/16.
 */
public class L299BullsandCows {

    public String getHint(String secret, String guess) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        int length = secret.length();
        Integer A = 0;
        Integer B = 0;
        for (int i = 0; i < length; i++) {
            Integer a = Integer.valueOf(secret.charAt(i));
            Integer b = Integer.valueOf(guess.charAt(i));
            if (a.equals(b)) {
                A++;
            } else {
                Integer aNum = map.get(a);
                if (aNum == null) {
                    map.put(a, 1);
                } else {
                    if (aNum < 0) {
                        B++;
                    }
                    map.put(a, aNum + 1);
                }
                Integer bNum = map.get(b);
                if (bNum == null) {
                    map.put(b, -1);
                } else {
                    if (bNum > 0) {
                        B++;
                    }
                    map.put(b, bNum - 1);
                }
            }
        }
        return A.toString() + "A" + B.toString() + "B";

    }
}
