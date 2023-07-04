package dev.fabby.com.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public final class TimeUtil {

    public enum TimeUnits {
        SECOND("s", 1L),
        MINUTE("m", 60L),
        HOUR("h", 3600L),
        DAY("d", 86400L),
        WEEK("w", 604800L),
        MONTH("mo", 2592000L),
        YEAR("y", 31104000L);

        public String shorts;

        public long mult;

        TimeUnits(String name, long mult) {
            this.shorts = name;
            this.mult = mult;
        }

        public long toMillis() {
            return this.mult * 1000L;
        }

        public static long fromString(String s, int time) {
            for (TimeUnits unit : values()) {
                if (s.toLowerCase().equals(unit.shorts)) {
                    long times = unit.toMillis() * time;
                    if (unit == YEAR)
                        times += ((time - 1) * 5) * DAY.toMillis();
                    return times;
                }
            }
            return -1L;
        }
    }

    private TimeUtil() {}

    public static long getUTCTimeMillis() {
        return System.currentTimeMillis();
    }

    public static long toLocal(long utc) {
        Date localTime = new Date(utc);
        String format = "yyyy/MM/dd HH:mm:ss";
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        sdf.setTimeZone(TimeZone.getDefault());
        Date gmtTime = new Date(sdf.format(localTime));
        return gmtTime.getTime();
    }

    public static String getTimeToString(int totalPlayTime) {
        int hours = (totalPlayTime / 3600)/ 24;
        int minutes = (totalPlayTime % 3600) / 60;

        return String.format("%02dh, %02dm", hours, minutes);
    }

    /*
        BORROWED FROM ESSENTIALS X
     */

    static int dateDiff(final int type, final Calendar fromDate, final Calendar toDate, final boolean future) {
        final int year = Calendar.YEAR;
        final int maxYears = 100000;

        final int fromYear = fromDate.get(year);
        final int toYear = toDate.get(year);
        if (Math.abs(fromYear - toYear) > maxYears) {
            toDate.set(year, fromYear +
                    (future ? maxYears : -maxYears));
        }

        int diff = 0;
        long savedDate = fromDate.getTimeInMillis();
        while ((future && !fromDate.after(toDate)) || (!future && !fromDate.before(toDate))) {
            savedDate = fromDate.getTimeInMillis();
            fromDate.add(type, future ? 1 : -1);
            diff++;
        }
        diff--;
        fromDate.setTimeInMillis(savedDate);
        return diff;
    }

    public static String formatDateDiff(final long date) {
        final Calendar c = new GregorianCalendar();
        c.setTimeInMillis(date);
        final Calendar now = new GregorianCalendar();
        return formatDateDiff(now, c);
    }

    public static String formatDateDiff(final Calendar fromDate, final Calendar toDate) {
        boolean future = false;
        if (toDate.equals(fromDate)) {
            return "now";
        }
        if (toDate.after(fromDate)) {
            future = true;
        }
        // Temporary 50ms time buffer added to avoid display truncation due to code execution delays
        toDate.add(Calendar.MILLISECOND, future ? 50 : -50);
        final StringBuilder sb = new StringBuilder();
        final int[] types = new int[] {Calendar.YEAR, Calendar.MONTH, Calendar.DAY_OF_MONTH, Calendar.HOUR_OF_DAY, Calendar.MINUTE, Calendar.SECOND};
        final String[] names = new String[] {"y", "y", "m", "m", "d", "d", "h", "h", "m", "m", "s", "s"};
        int accuracy = 0;
        for (int i = 0; i < types.length; i++) {
            if (accuracy > 2) {
                break;
            }
            final int diff = dateDiff(types[i], fromDate, toDate, future);
            if (diff > 0) {
                accuracy++;
                sb.append(" ").append(diff).append(names[i * 2 + (diff > 1 ? 1 : 0)]);
            }
        }
        // Preserve correctness in the original date object by removing the extra buffer time
        toDate.add(Calendar.MILLISECOND, future ? -50 : 50);
        if (sb.length() == 0) {
            return "now";
        }
        return sb.toString().trim();
    }
}
