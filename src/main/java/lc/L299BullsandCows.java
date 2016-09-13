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

    // Write a program that prints the numbers from 1 to 100. But for multiples of three print "Fizz"
// instead of the number and for the multiples of five print "Buzz". For numbers which are multiples
// of both three and five print "FizzBuzz".


    public List<String> printNumber(int n) {
        List<String> result = new ArrayList<String>();

        for (int i = 1; i <= n; i++) {
            if (i % 15 == 0) {
                result.add("FizzBuzz");
            } else if (i % 3 == 0) {
                result.add("Fizz");
            } else if (i % 5 == 0) {
                result.add("Buzz");
            } else {
                result.add(Integer.valueOf(i).toString());
            }
        }
        return result;
    }

    public void main(String[] args) {

        List<String> result = printNumber(100);
        for (String s : result) {
            System.out.println(s);
        }
    }

    @Test
    public void testPrintNumber1() {
        int n = 5;
        List<String> actual = printNumber(n); // ["1", "2", "Fizz", 4, "Buzz"]
        List<String> expected = Arrays.asList(new String[] {"1", "2", "Fizz", "4", "Buzz"});
        Assert.assertEquals(expected, actual);
    }








}
