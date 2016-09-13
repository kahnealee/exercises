package lc;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by kpan on 9/12/16.
 */
public class L290WordPattern {
    public boolean wordPattern(String pattern, String str) {
        if (pattern == null || str == null) {
            return false;
        }
        String[] strings = str.split(" ");
        if (strings.length != pattern.length()) {
            return false;
        }
        Map<Character, String> map1 = new HashMap<Character, String>();
        Map<String, Character> map2 = new HashMap<String, Character>();
        for(int i = 0; i < pattern.length(); i++) {
            Character c = pattern.charAt(i);
            String s = strings[i];
            String getString = map1.get(c);
            Character getC = map2.get(s);
            if (getString == null && getC == null) {
                map1.put(c, s);
                map2.put(s, c);
            } else if (getString != null && getC != null) {
                if (!s.equals(getString) || !c.equals(getC)) {
                    return false;
                }
            } else {
                return false;
            }
        }
        return true;
    }
}
