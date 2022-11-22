

/*
 *
 *  * Copyright (c) 2022
 *  * http://license.coscl.org.cn/MulanPSL2
 *  * 汪旭辉
 *
 */

package com.guoshiyao.rely.core.utils.date;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 不再建议使用该类,建议用 cn.hutool...DateUtil
 */
@Deprecated
public final class DateUtils {
    public static final String SHORT_DATE_PATTERN = "yyyy-MM-dd";
    public static final String LONG_DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";
    private static final String TIME_PATTERN = "HH:mm:ss";
    private final static String MIN_TIME = "00:00:00";
    private final static String MAX_TIME = "23:59:59";
    private final static int DAY_CNT_OF_YEAR = 365;
    // 静态初始化闰日数组
    private static final List<Date> leapDates = new ArrayList();

    static {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            for (int i = 1988; i <= 2096; i = i + 4) {
                leapDates.add(sdf.parse(i + "-2-29"));
            }
        } catch (ParseException e) {
        }
    }

    /**
     * Checkstyle rule: utility classes should not have public constructor
     */
    private DateUtils() {
    }

    /**
     * 将字符串转为日期
     *
     * @param dateStr
     * @param format
     * @return
     */
    public static Date parseDate(String dateStr, String format) {
        if (dateStr.equals(""))
            return null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        try {
            Date date = simpleDateFormat.parse(dateStr);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 比较日前
     *
     * @param DATE1
     * @return -1:在现在之前 1:在现在之后
     */
    public static int compareNow(String DATE1) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date dt1 = df.parse(DATE1);
            Date dt2 = new Date();
            if (dt1.getTime() > dt2.getTime()) {
                return 1;
            } else if (dt1.getTime() < dt2.getTime()) {
                return -1;
            } else {
                return 0;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
    }

    /**
     * Return default datePattern (MM/dd/yyyy)
     *
     * @return a string representing the date pattern on the UI
     */
    public static String getDatePattern() {
        return "yyyy-MM-dd";
    }

    public static String getDateTimePattern() {
        return DateUtils.getDatePattern() + " HH:mm:ss.S";
    }

    /**
     * 按照默认日期格式化为"yyyy-MM-dd"
     *
     * @param aDate
     * @return
     * @author 汪旭辉
     * @date 2018年10月30日
     * @readme TODO
     */
    public static String getDate(Date aDate) {
        SimpleDateFormat df;
        String returnValue = "";

        if (aDate != null) {
            df = new SimpleDateFormat(getDatePattern());
            returnValue = df.format(aDate);
        }

        return (returnValue);
    }

    /**
     * 按照指定格式为序列化为日期
     *
     * @param strDate aMask
     * @return
     * @author 汪旭辉
     * @date 2018年10月30日
     * @readme TODO
     */
    public static Date convertStringToDate(String aMask, String strDate) throws ParseException {


        SimpleDateFormat df;
        Date date;
        df = new SimpleDateFormat(aMask);

        try {
            date = df.parse(strDate);
        } catch (ParseException pe) {
            // log.error("ParseException: " + pe);
            throw new ParseException(pe.getMessage(), pe.getErrorOffset());
        }

        return (date);
    }

    /**
     * 按照默认日期格式化为"HH:mm:ss"
     *
     * @param aDate
     * @return
     * @author 汪旭辉
     * @date 2018年10月30日
     * @readme TODO
     */
    public static String getTimeNow(Date theTime) {
        return getDateTime(TIME_PATTERN, theTime);
    }

    /**
     * This method returns the current date in the format: MM/dd/yyyy
     *
     * @return the current date
     * @throws ParseException when String doesn't match the expected format
     */
    public static Calendar getToday() throws ParseException {
        Date today = new Date();
        SimpleDateFormat df = new SimpleDateFormat(getDatePattern());

        // This seems like quite a hack (date -> string -> date),
        // but it works ;-)
        String todayAsString = df.format(today);
        Calendar cal = new GregorianCalendar();
        cal.setTime(convertStringToDate(todayAsString));

        return cal;
    }

    /**
     * 返回当前日期
     *
     * @return the current date
     */
    public static Date getTodayDateType() {
        return new Date();
    }

    /**
     * This method generates a string representation of a date's date/time in the
     * format you specify on input
     *
     * @param aMask the date pattern the string is in
     * @param aDate a date object
     * @return a formatted string representation of the date
     * @see java.text.SimpleDateFormat
     */
    public static String getDateTime(String aMask, Date aDate) {
        SimpleDateFormat df = null;
        String returnValue = "";

        if (aDate == null) {
            // log.warn("aDate is null!");
        } else {
            df = new SimpleDateFormat(aMask);
            returnValue = df.format(aDate);
        }

        return (returnValue);
    }

    /**
     * This method generates a string representation of a date based on the System
     * Property 'dateFormat' in the format you specify on input
     *
     * @param aDate A date to convert
     * @return a string representation of the date
     */
    public static String convertDateToString(Date aDate) {
        return getDateTime(getDatePattern(), aDate);
    }

    /**
     * This method converts a String to a date using the datePattern
     *
     * @param strDate the date to convert (in format MM/dd/yyyy)
     * @return a date object
     * @throws ParseException when String doesn't match the expected format
     */
    public static Date convertStringToDate(final String strDate) throws ParseException {
        return convertStringToDate(getDatePattern(), strDate);
    }

    /**
     * 比较两个日期的年份。
     *
     * @param d1
     * @param d2
     * @return
     */
    public static int compareYear(Date d1, Date d2) {
        Calendar c1 = Calendar.getInstance();
        c1.setTime(d1);

        Calendar c2 = Calendar.getInstance();
        c2.setTime(d2);

        return c1.get(Calendar.YEAR) - c2.get(Calendar.YEAR);
    }

    /**
     * 在指定日期基础上计算n天后的日期
     *
     * @param d1
     * @param amount
     * @return
     */
    public static Date addDays(Date d1, int n) {
        Calendar c = Calendar.getInstance();
        c.setTime(d1);

        c.add(Calendar.DATE, n);

        return c.getTime();

    }

    /**
     * 在指定日期基础上计算n月后的日期
     *
     * @param d1
     * @param amount
     * @return
     */
    public static Date addMonths(Date d1, int n) {
        Calendar c = Calendar.getInstance();
        c.setTime(d1);

        c.add(Calendar.MONTH, n);

        return c.getTime();

    }

    /**
     * 在指定日期基础上计算n年后的日期
     *
     * @param d1
     * @param amount
     * @return
     */
    public static Date addhour(Date d1, int n) {
        Calendar c = Calendar.getInstance();
        c.setTime(d1);
        c.add(Calendar.HOUR_OF_DAY, n);
        return c.getTime();
    }

    /**
     * 在指定日期基础上计算n年后的日期
     *
     * @param d1
     * @param amount
     * @return
     */
    public static Date addmin(Date d1, int n) {
        Calendar c = Calendar.getInstance();
        c.setTime(d1);
        c.add(Calendar.MINUTE, n);
        return c.getTime();
    }

    /**
     * 在指定日期基础上计算n年后的日期
     *
     * @param d1
     * @param amount
     * @return
     */
    public static Date addYears(Date d1, int n) {
        Calendar c = Calendar.getInstance();
        c.setTime(d1);

        c.add(Calendar.YEAR, n);

        return c.getTime();

    }

    /**
     * 在指定日期基础上计算设定的间隔日期之后的日期，其中间隔日期为“整数+单位”，单位为D、M、Q、Y四种。
     *
     * @param d1              基准日期
     * @param patternInterval 间隔日期，例如5D，表示5天后，2M表示2月后，等等
     * @return 计算结果日期，如果传入参数不合法导致计算错误，返回null
     */
    public static Date addPatternInterval(Date d1, String patternInterval) {

        // 首先判断patternInterval是否合法
        String prefix = patternInterval.substring(0, patternInterval.length() - 1);
        char suffix = patternInterval.charAt(patternInterval.length() - 1);
        int interval;
        try {
            interval = Integer.parseInt(prefix);
        } catch (NumberFormatException e) {
            return null;
        }

        // 计算
        switch (suffix) {
            case 'Y':
                return addYears(d1, interval);
            case 'Q':
                return addMonths(d1, interval * 3);
            case 'M':
                return addMonths(d1, interval);
            case 'D':
                return addDays(d1, interval);
            default:
                return null;
        }
    }

    /**
     * 将模式日期字符，转换为实际的年数。 接受的模式日期类型分为：nD/nM/nQ/nY四种，分别对应日、月、季、年，n为整数
     *
     * @param patternDays
     * @return
     */
    public static double toYears(String patternDays) {
        String prefix = patternDays.substring(0, patternDays.length() - 1);
        char suffix = patternDays.charAt(patternDays.length() - 1);

        double result;
        switch (suffix) {
            case 'Y':
                result = Double.parseDouble(prefix);
                break;
            case 'Q':
                result = Double.parseDouble(prefix) / 4.0;
                break;
            case 'M':
                result = Double.parseDouble(prefix) / 12.0;
                break;
            case 'D':
                result = Double.parseDouble(prefix) / 360.0;
                break;
            default:
                return 0;
        }

        return result;
    }

    /**
     * 计算两个日期之间的间隔天数
     *
     * @param d1
     * @param d2
     * @return
     */
    public static int getDaysBetween(Date d1, Date d2) {
        if (d1.after(d2)) { // swap dates so that d1 is start and d2 is end
            Date swap = d1;
            d1 = d2;
            d2 = swap;
        }
        Calendar c1 = Calendar.getInstance();
        c1.setTime(d1);

        Calendar c2 = Calendar.getInstance();
        c2.setTime(d2);

        int days = c2.get(Calendar.DAY_OF_YEAR) - c1.get(Calendar.DAY_OF_YEAR);
        int y2 = c2.get(Calendar.YEAR);
        if (c1.get(Calendar.YEAR) != y2) {
            c1 = (Calendar) c1.clone();
            do {
                days += c1.getActualMaximum(Calendar.DAY_OF_YEAR);// 得到当年的实际天数
                c1.add(Calendar.YEAR, 1);
            } while (c1.get(Calendar.YEAR) != y2);
        }
        return days;
    }

    /**
     * 求两个日期之间的天数，如果包含闰年，则减去多出的一天（主要针对原alpha的逻辑，新产品中应该不需要）
     *
     * @param d1
     * @param d2
     * @return
     */
    public static int getDaysBetweenWithoutLeap(Date d1, Date d2) {
        if (d1.after(d2)) { // swap dates so that d1 is start and d2 is end
            Date swap = d1;
            d1 = d2;
            d2 = swap;
        }

        int days = getDaysBetween(d1, d2);

        // 计算d1、d2之间的闰日天数
        int leapDays = 0;

        for (Date leapDate : leapDates) {
            if (leapDate.compareTo(d2) > 0) {
                // 截止日已经超出
                break;
            }

            if (d1.compareTo(leapDate) <= 0) {
                leapDays++;
            }
        }

        return days - leapDays;
    }

    /**
     * 输入指定日期，返回它是星期几
     *
     * @param date
     * @return
     */
    public static int getWeekNum(Date _date) {
        int weekNum = 0;
        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(_date);
            weekNum = calendar.get(Calendar.DAY_OF_WEEK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return weekNum;
    }

    /**
     * 获取指定日期的月份，如'2012-3-4'返回3
     *
     * @param date
     * @return
     */
    public static int getMonth(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        // Calendar里取出来的month比实际的月份少1，所以要加上
        int mon = cal.get(Calendar.MONTH) + 1;

        return mon;
    }

    /**
     * 获取指定日期的日，如'2012-3-4'返回4
     *
     * @param date
     * @return
     */
    public static int getDay(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return day;
    }

    /**
     * 获取指定日期的日，如'2012-3-4'返回2012
     *
     * @param date
     * @return
     */
    public static int getYear(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int year = cal.get(Calendar.YEAR);
        return year;
    }

    /**
     * 比较一组日期的最大值
     *
     * @param dates
     * @return
     */
    public static Date max(Date[] dates) {
        if (dates == null || dates.length == 0)
            return null;

        if (dates.length == 1)
            return dates[0];

        Date max = dates[0];

        for (int i = 1; i < dates.length; i++) {
            max = max.compareTo(dates[i]) > 0 ? max : dates[i];
        }

        return max;
    }

    /**
     * 根据指定日期，返回其带精确时间的日期类型(23:59:59) 例如传入"2011-1-1"，返回"2011-01-01 23:59:59"
     *
     * @param theDate
     * @return
     */
    public static Date getMaxTimeOfDate(Date theDate) {
        String sDate = DateUtils.convertDateToString(theDate);
        sDate = sDate + " " + MAX_TIME;

        try {
            return convertStringToDate("yyyy-MM-dd HH:mm:ss", sDate);
        } catch (ParseException e) {
            // log.warn(e.getMessage());
            return null;
        }
    }

    /**
     * 根据指定日期，返回其带精确时间的日期类型(00:00:00) 例如传入"2011-1-1"，返回"2011-01-01 00:00:00"
     *
     * @param theDate
     * @return
     */
    public static Date getMinTimeOfDate(Date theDate) {
        String sDate = DateUtils.convertDateToString(theDate);
        sDate = sDate + " " + MIN_TIME;

        try {
            return convertStringToDate("yyyy-MM-dd HH:mm:ss", sDate);
        } catch (ParseException e) {
            // log.warn(e.getMessage());
            return null;
        }
    }

    /**
     * 对于未维护的交易日，依据传入的日期进行计算，取得工作日（仅限于非周末）
     *
     * @param baseDate
     * @param offset
     * @return
     */
    public static Date getCalculatedWorkDay(Date baseDate, int offset) {
        Date target = baseDate;

        if (offset == 0)
            return target;

        if (offset > 0) {
            int steps = 1;
            do {
                target = DateUtils.addDays(target, 1);
                if (!DateUtils.isWeekend(target))
                    steps++;

            } while (steps <= offset);
        } else {
            int steps = -1;
            do {
                target = DateUtils.addDays(target, -1);
                if (!DateUtils.isWeekend(target))
                    steps--;
            } while (steps >= offset);
        }

        return target;
    }

    /**
     * 判断指定日期是否周末
     *
     * @param theDate
     * @return
     */
    public static boolean isWeekend(Date theDate) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(theDate);

        int day = cal.get(Calendar.DAY_OF_WEEK);

        return day == Calendar.SATURDAY || day == Calendar.SUNDAY;
    }

    /**
     * 获取两个日期直接的间隔，以年为单位。日期顺序可以随意，该方法永远返回正值。
     *
     * @param d1
     * @param d2
     * @return
     */
    public static BigDecimal getIntervalYear(Date d1, Date d2) {
        int days = getDaysBetween(d1, d2);
        return new BigDecimal(days).divide(new BigDecimal(DAY_CNT_OF_YEAR), new MathContext(2, RoundingMode.HALF_UP));
    }

}
