package nl.lakedigital.hulpmiddelen.utils;

import java.util.List;

public final class ArrayUtils {

    private ArrayUtils() {
    }

    public static boolean checkNotEmpty(List<? extends Object> lijst) {
        if (lijst != null && lijst.size() > 0) {
            return true;
        }

        return false;
    }
}
