package lc;

/**
 * Created by kpan on 9/16/16.
 */
public class L204CountPrimes {
    public int countPrimes(int n) {
        if (n < 3) {
            return 0;
        }
        boolean[] notPrimes = new boolean[n];
        notPrimes[0] = true;
        notPrimes[1] = true;
        for (int i = 2; i * i < n; i++) {
            if (!notPrimes[i]) {
                for (int j=i*i; j < n; j = j+i) {
                    notPrimes[j] = true;
                }
            }
        }
        int count = 0;
        for (boolean b : notPrimes) {
            if(!b) {
                count ++;
            }
        }
        return count;
    }
}
