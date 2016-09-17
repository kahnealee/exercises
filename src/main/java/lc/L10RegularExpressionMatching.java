package lc;

/**
 * Created by kpan on 9/14/16.
 */
public class L10RegularExpressionMatching {
    public boolean isMatch(String s, String p) {
        boolean[][] dp = new boolean[p.length() + 1][s.length() + 1];
        dp[0][0] = true;
        for (int i = 1; i <= p.length(); i++) {
            dp[i][0] = p.charAt(i - 1) == '*' && dp[i - 2][0];
            for (int j = 0; j <= s.length(); j++) {
                if (i == 0 && j>=0) dp[i][j] = true;
                if (j > 0) {
                    if (p.charAt(i - 1) == '*') {
                        if (dp[i - 2][j]) {
                            dp[i][j] = true;

                        }

                    } else {
                        dp[i][j] = dp[i - 1][j - 1]
                                && (p.charAt(i - 1) == '.' || p.charAt(i - 1) == s
                                .charAt(j - 1));
                    }
                }

            }

        }
        return dp[p.length()][s.length()];
    }
}
