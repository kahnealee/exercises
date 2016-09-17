package lc;
import java.util.Map;
import java.util.HashMap;

/**
 * Created by kpan on 9/16/16.
 */
public class L149MaxPointsonaLine {
    public int maxPoints(Point[] points) {
        if (points == null || points.length == 0) {
            return 0;
        }
        int length = points.length;
        int max = 0;
        for (int i = 0; i < length; i++) {
            Map<Float, Integer> map = new HashMap<Float, Integer>();
            int thisMax = 1;
            int dup = 0;
            for (int j = i+1; j < length; j++) {
                if ((points[i].y == points[j].y) && (points[i].x == points[j].x)) {
                    dup++;
                    continue;
                }

                Float slope;
                if (points[i].x == points[j].x) {
                    slope = Float.MAX_VALUE;
                } else if (points[i].y == points[j].y) {
                    slope = 0.0f;
                } else {
                    slope = Float.valueOf(points[i].y - points[j].y) / Float.valueOf(points[i].x - points[j].x);
                }
                int thisSlopeCount;
                if (map.get(slope) == null) {
                    thisSlopeCount = 2;
                    map.put(slope, 2);
                } else {
                    thisSlopeCount = map.get(slope) + 1;
                    map.put(slope, thisSlopeCount);
                }
                if (thisSlopeCount > thisMax) {
                    thisMax = thisSlopeCount;
                }
            }
            thisMax += dup;
            if (thisMax > max) {
                max = thisMax;
            }
        }
        return max;
    }
}
