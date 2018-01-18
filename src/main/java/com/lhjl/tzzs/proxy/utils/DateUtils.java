package com.lhjl.tzzs.proxy.utils;

import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author caochuangui
 * @date 2018-1-14 17:02:32
 */
public class DateUtils{

    /**
     * 23小时59分59秒
     */
    public static final long ONE_DAY_TIMESTAMP = 24 * 60 * 60 * 1000l;
    /**
     * 标准容器格式
     * yyyy-MM-dd
     */
    public static final String FMT_DATE = "yyyy-MM-dd";
    /**
     * 标准时间格式
     * HH:mm
     */
    public static final String FMT_TIME = "HH:mm:ss";
    /**
     * 标准日期时间格式
     * yyyy-MM-dd HH:mm
     */
    public static final String FMT_DATETIME = FMT_DATE + " " + FMT_TIME;
    /**
     * 日期格式
     * yyyy-MM-dd HH:mm:ss
     */
    public static final String FMT_DATE_TIME = "yyyy-MM-dd HH:mm:ss";

    /**
     * 压缩日期格式 yyyyMMdd
     */
    public static final String FMT_DATEMIN = "yyyyMMdd";

    /**
     * 压缩日期时间格式 yyyyMMddHHmmss
     */
    public static final String FMT_DATETIMEMIN = "yyyyMMddHHmmss";

    /**
     * 获取日期对应的时间戳(只到日期的时间戳)
     *
     * @param dateStar 格式 yyyy-MM-dd
     * @return
     */
    public static long parseDateTimestamp(String dateStar) {
        Date date = parse(dateStar, FMT_DATE);
        return date.getTime();
    }

    /**
     * 获取日期对应的时间戳(只到日期的时间戳)
     *
     * @param timestamp 时间戳
     * @return
     */
    public static long parseDateTimestamp(long timestamp) {
        return parseDateTimestamp(format(new Date(timestamp), FMT_DATE));
    }

    public static long parseDateTimestamp() {

        return parseDateTimestamp(System.currentTimeMillis());
    }

    /**
     * 返回开始和结束区间内的dateTime(每天的时间戳)
     *
     * @param start
     * @param end
     * @return
     */
    public static List<Long> parseDateTimestamps(long start, long end) {
        List<Long> ret = new ArrayList<>();
        long endDate = parseDateTimestamp(end);
        long startDate = parseDateTimestamp(start);
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(start);
        if (endDate == startDate) {
            ret.add(endDate);
            return ret;
        }
        ret.add(startDate);
        while (true) {
            c.add(Calendar.DATE, 1);
            long tmp = parseDateTimestamp(c.getTimeInMillis());

            if (tmp <= endDate) {
                ret.add(tmp);
                continue;
            }
            return ret;
        }
    }

    /**
     * 根据开始日期和结束日期 统计中间日期
     *
     * @param startDate
     * @param endDate
     * @return
     */
    public static List<String> calcDates(String startDate, String endDate) {
        List<String> ret = new ArrayList<>();
        long startTimestamp = parseDateTimestamp(startDate);
        long endTimestamp = parseDateTimestamp(endDate);
        if (startTimestamp >= endTimestamp) {
            ret.add(format(startTimestamp, FMT_DATE));
            return ret;
        }
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(startTimestamp);
        ret.add(format(startTimestamp, FMT_DATE));
        while (true) {
            c.add(Calendar.DATE, 1);
            long tmp = parseDateTimestamp(c.getTimeInMillis());
            if (tmp <= endTimestamp) {
                ret.add(format(tmp, FMT_DATE));
                continue;
            }
            return ret;
        }
    }

    /**
     * 解析字符串日期为日期对象
     *
     * @param str 日期文本 , 必须是<b>yyyy-MM-dd HH:mm</b>格式
     * @return
     */
    public static Date parse(String str) {
        return parse(str, FMT_DATETIME);
    }

    public static Date parse1(String str) {
        return parse(str, FMT_DATE);
    }

    /**
     * 解析字符串日期为日期对象
     *
     * @param str     日期文本
     * @param pattern 解析格式
     * @return
     */
    public static Date parse(String str, String pattern) {
        SimpleDateFormat df = new SimpleDateFormat(pattern);
        try {
            return df.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 格式化日期
     * <p>
     * 使用默认格式({@link #FMT_DATETIME})格式化日期
     * </p>
     * yyyy-MM-dd HH:mm
     *
     * @param date
     * @return
     */
    public static String format(long date) {
        return format(new Date(date));
    }

    public static String format(long date, String pattern) {
        return format(new Date(date), pattern);
    }

    /**
     * 格式化日期
     * <p>
     * 使用默认格式({@link #FMT_DATETIME})格式化日期
     * </p>
     * yyyy-MM-dd HH:mm
     *
     * @param date
     * @return
     */
    public static String format(Date date) {
        return format(date, FMT_DATETIME);
    }

    /**
     * 格式化日期
     *
     * @param date
     * @param pattern
     * @return
     * @see SimpleDateFormat
     */
    public static String format(Date date, String pattern) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat df = new SimpleDateFormat(pattern);
        return df.format(date);
    }

    /**
     * 格式化时长以分钟表示
     *
     * @param ts
     * @return
     */
    public static String formatTimelenToMinute(long ts) {
        long m = ts / 60000;
        return NumberFormat.getNumberInstance().format(m);
    }

    public static final byte FLAG_Second = 0;
    public static final byte FLAG_Minute = 1;
    public static final byte FLAG_Hour = 2;
    public static final byte FLAG_Day = 3;
    /**
     * 单位名称
     */
    public static final String[] jinzhiNameTable = new String[]{"秒", "分",
            "时", "天"};
    /**
     * 单位进制
     */
    public static final int[] jinzhiTable = new int[]{1000, 60, 60, 24};

    /**
     * 计算时长在各个单位上的分解
     *
     * @param ts
     * @return
     */
    static int[] computeTimelenFields(long ts) {
        int[] data = new int[]{0, 0, 0, 0};
        for (int i = 0; i < jinzhiTable.length; i++) {
            // 转换单位
            ts = ts / jinzhiTable[i];
            if (ts > 0) {
                if (i == jinzhiTable.length - 1) {
                    data[i] = (int) ts;
                } else {
                    data[i] = (int) (ts % jinzhiTable[i + 1]);
                }
            } else {
                break;
            }
        }
        return data;
    }

    /**
     * 格式化时长
     *
     * @param ts
     * @return
     */
    public static String formatTimelen(long ts) {
        int[] data = computeTimelenFields(ts);
        StringBuilder sb = new StringBuilder();
        for (int i = data.length - 1; i >= 0; i--) {
            if (sb.length() == 0 && data[i] <= 0) {
                continue;
            }
            sb.append(data[i]);
            sb.append(jinzhiNameTable[i]);
        }
        return sb.toString();
    }

    /**
     * 格式化时长, 只显示一个最大的有效单位
     *
     * @param ts
     * @return
     */
    public static String formatSimpleTimelen(long ts) {
        int[] data = computeTimelenFields(ts);
        for (int i = data.length - 1; i >= 0; i--) {
            if (data[i] > 0) {
                return data[i] + jinzhiNameTable[i];
            }
        }
        return "-";
    }

    /**
     * 比较2个时间戳的天数差, 以 0:00 为分割点
     * <p>
     * <p>
     * </p>
     *
     * @param t1
     * @param t2
     * @return 如果 t1 比 t2 晚一天, 返回1
     */
    public static int compareByDay(long t1, long t2) {
        int rawOffset = TimeZone.getDefault().getRawOffset();
        return (int) (((t1 + rawOffset) / 86400000) - ((t2 + rawOffset) / 86400000));
    }

    /**
     * 是否在同一周(以周日零点为分割线)
     * t1 t2 不分大小
     *
     * @param t1
     * @param t2
     * @return
     */
    public static boolean inOneWeek(long t1, long t2) {
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTimeInMillis(t1);
        cal2.setTimeInMillis(t2);
        cal1.setFirstDayOfWeek(Calendar.SUNDAY);
        cal2.setFirstDayOfWeek(Calendar.SUNDAY);
        int subYear = cal1.get(Calendar.YEAR) - cal2.get(Calendar.YEAR);
        // subYear==0,说明是同一年
        if (subYear == 0) {
            if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2.get(Calendar.WEEK_OF_YEAR)) {
                return true;
            }
        } else if (subYear == 1 && cal2.get(Calendar.MONTH) == 11) {// :cal1是"2005-1-1"，cal2是"2004-12-25"
            if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2.get(Calendar.WEEK_OF_YEAR)) {
                return true;
            }
        } else if (subYear == -1 && cal1.get(Calendar.MONTH) == 11) {// 例子:cal1是"2004-12-31"，cal2是"2005-1-1"
            if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2.get(Calendar.WEEK_OF_YEAR)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 从服务器获取的时区差,默认是东八区的时区
     */
    public static long serverRawOffset = 28800000;

    /**
     * 根据服务器下发的时区偏移量计算日期差
     *
     * @param t1
     * @param t2
     * @return
     */
    public static int compareByDayWithServerRawOffset(long t1, long t2) {
        long rawOffset = serverRawOffset;
        return (int) (((t1 + rawOffset) / 86400000) - ((t2 + rawOffset) / 86400000));
    }

    /**
     * @param
     * @return FMT_DATE
     */
    public static String getYestoday() {
        return getSomeday(-1);
    }

    /**
     * @param addDay
     * @return FMT_DATE
     */
    private static String getSomeday(int addDay) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, addDay);
        String someday = new SimpleDateFormat(FMT_DATE).format(cal
                .getTime());
        return someday;
    }

    /**
     * @return FMT_DATE
     */
    public static String getToday() {
        return getSomeday(0);
    }

    /**
     * @return FMT_DATE
     */
    public static String getTomorrow() {
        return getSomeday(1);
    }

    public static int getHourOfDay() {
        Calendar cal = Calendar.getInstance();
        return cal.get(Calendar.HOUR_OF_DAY);
    }

    public static Date addHour(Date d, int hour) {
        Calendar myTime = Calendar.getInstance();
        if (d != null) {
            myTime.setTime(d);
        }
        myTime.add(Calendar.HOUR, hour);
        return myTime.getTime();
    }

    /**
     * 是否在同一年的同一周(以周日零点为分割线)
     * t1 t2 不分大小
     *
     * @param t1
     * @param t2
     * @return
     */
    public static boolean inOneWeekOneYear(long t1, long t2) {
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTimeInMillis(t1);
        cal2.setTimeInMillis(t2);
        cal1.setFirstDayOfWeek(Calendar.SUNDAY);
        cal2.setFirstDayOfWeek(Calendar.SUNDAY);
        int subYear = cal1.get(Calendar.YEAR) - cal2.get(Calendar.YEAR);
        // subYear==0,说明是同一年
        if (subYear == 0) {
            if (cal1.get(Calendar.WEEK_OF_YEAR) == cal2.get(Calendar.WEEK_OF_YEAR)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isSameDay(long time1, long time2) {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTimeInMillis(time1);
        Calendar cal2 = Calendar.getInstance();
        cal2.setTimeInMillis(time2);
        return cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR) && cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR);
    }

    /**
     * 如果time1>time2 正数
     * 否则 负数
     *
     * @return
     */
//	public static int diffDay(long time1, long time2) {
//
//		Calendar cal1 = Calendar.getInstance();
//		cal1.setTimeInMillis(time1);
//		Calendar cal2 = Calendar.getInstance();
//		cal2.setTimeInMillis(time2);
//		return cal1.;
//	}
    public static long days(long start, long end) {
        long diff = 0;
        diff = end - start;
        long day = diff / ONE_DAY_TIMESTAMP;//计算差多少天
        return day;
    }

    /**
     * 计算day天前的零点
     *
     * @param day
     * @return
     */
    public static long day2Zero(int day) {
//		long date = System.currentTimeMillis();
        Date date = new Date(System.currentTimeMillis());
        long time = System.currentTimeMillis();
        //3天前
        long before = System.currentTimeMillis() - day * 24 * 60 * 60 * 1000;
        return time - before;

    }

    /**
     * @param year
     * @param month
     * @param day
     * @return 格式 yyyy-MM-dd
     */
    public static String formatDate(int year, int month, int day) {
        String m = month < 10 ? "0" + month : String.valueOf(month);
        String d = day < 10 ? "0" + day : String.valueOf(day);
        return year + "-" + m + "-" + d;
    }
}

