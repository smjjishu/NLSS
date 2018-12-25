package com.smj.comm.tools;


import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.assertj.core.util.Lists;
import org.dozer.DozerBeanMapper;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * 常用工具公共类
 */
public class CommTool {
    public static Log log = LogFactory.getLog(CommTool.class);

    public final static String FORMAT_DATE = "yyyy-MM-dd";
    public final static String FORMAT_DATE_TIME = "yyyy-MM-dd hh:mm:ss";
    public final static String TIME_FORMAT_ALL = "yyyy-MM-dd HH:mm:ss:SSS";
    public final static String FORMAT_MONTH_DAY = "yyyy年MM月dd日";
    public final static String FORMAT_MONTH_DAY_TIME_All = "yyyy年MM月dd日 HH:mm:ss";
    public final static String YYYYMMDD = "yyyyMMdd";
    public final static String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
    //默认使用系统当前时区
    private static final ZoneId ZONE = ZoneId.systemDefault();
    private static DozerBeanMapper mapper = new DozerBeanMapper();


    //--------------------------------------------------------------------------------------------//
    //----------------------------------------基本相关函数-----------------------------------------//
    //--------------------------------------------------------------------------------------------//
    //region 基本相关函数

    /**
     * 字符串是否为空
     */
    public static boolean isStringToEmpty(String str) {
        return ((str == null) || (str.length() == 0));
    }

    /**
     * 集合是否为空
     */
    public static <T> Boolean isCollectionToEmpty(Collection<T> arg) {
        return arg != null && arg.size() > 0;
    }

    /**
     * 转换成百分比
     */
    public static String parsePercent(double i) {
        DecimalFormat decimalFormat = new DecimalFormat("##.00%");
        return decimalFormat.format(i);
    }

    /**
     * 对象转换成其他相关联对象
     */
    public static <T> T objectMap(Object source, Class<T> destinationClass) {
        if (source == null) {
            return null;
        }
        return mapper.map(source, destinationClass);
    }


    /**
     * 字符串转换成List通过标识符
     */
    public static List<String> StringToList(String str, String split) {
        List<String> list = Lists.newArrayList();
        if (!StringUtils.isEmpty(str)) {
            String[] array = str.split(split);
            list = Arrays.asList(array).stream().distinct().collect(Collectors.toList());
        }
        return list;
    }

    /**
     * 描述：手机号格式验证.
     *
     * @param str 指定的手机号码字符串
     * @return 是否为手机号码格式:是为true，否则false
     */
    public static boolean isMobileNo(String str) {
        boolean isMobileNo = false;
        try {
            //13开头
            //Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
            Pattern p = Pattern.compile("^(13|14|15|17|18)\\d{9}$");
            Matcher m = p.matcher(str);
            isMobileNo = m.matches();
        } catch (Exception e) {
            //BaseConstants.MY_LOG.warn("校验手机号码异常", e);
            //throw e;

        }
        return isMobileNo;
    }


    /**
     * 编码格式转换
     * @param s
     * @return
     */
    public static final String decode(String s) {
        if (s == null) {
            return null;
        }
        try {
            return URLDecoder.decode(s, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }


    //endregion


    //--------------------------------------------------------------------------------------------//
    //----------------------------------------时间相关函数-----------------------------------------//
    //--------------------------------------------------------------------------------------------//
    //region 时间相关函数

    /**
     * 字符串转换成日期
     */
    public static Date stringToDate(String dateStr) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
        Date date = null;
        try {
            date = format.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 字符串转换成日期
     */
    public static Date stringToDate(String dateStr, String pattern) {
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        Date date = null;
        try {
            date = format.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 日期转换成字符串
     */
    public static String dateToString(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
        String str = format.format(date);
        return str;
    }

    /**
     * 日期转换成字符串
     */
    public static String dateToString(Date date, String pattern) {
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        String str = format.format(date);
        return str;
    }


    /**
     * 将long类型的时间格式化：long--->String
     */
    public static String formatTimes(String format, long date) {
        if (StringUtils.isEmpty(format)) {
            simpleDateFormat.applyPattern(FORMAT_DATE_TIME);
        } else {
            simpleDateFormat.applyPattern(format);
        }
        return simpleDateFormat.format(new Date(date));
    }


    public static Integer dayNum(String startDate, String endDate) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date2 = format.parse(endDate);
            Date date = format.parse(startDate);
            int days = (int) ((date2.getTime() - date.getTime()) / (1000 * 3600 * 24));
            return days;
        } catch (Exception e) {
            return 0;
        }
    }

    /**
     * 日期上增加 n个月
     */
    public static Date addYear(Date date, int n) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.YEAR, n);
        return cal.getTime();
    }

    /**
     * 日期上增加 n个月
     */
    public static Date addMonth(Date date, int n) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, n);
        return cal.getTime();
    }

    /**
     * 日期上增加 n天
     */
    public static Date addDay(Date date, int n) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, n);
        return cal.getTime();
    }


    public static Date getDate(String year, String month, String day) {
        String result = year + "- "
                + (month.length() == 1 ? ("0 " + month) : month) + "- "
                + (day.length() == 1 ? ("0 " + day) : day);
        return stringToDate(result, FORMAT_DATE);
    }


    /**
     * 将Date转换成LocalDateTime
     *
     * @param d date
     * @return
     */
    public static LocalDateTime dateToLocalDateTime(Date d) {
        Instant instant = d.toInstant();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZONE);
        return localDateTime;
    }

    /**
     * 将Date转换成LocalDate
     *
     * @param d date
     * @return
     */
    public static LocalDate dateToLocalDate(Date d) {
        Instant instant = d.toInstant();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZONE);
        return localDateTime.toLocalDate();
    }

    /**
     * 将Date转换成LocalTime
     *
     * @param d date
     * @return
     */
    public static LocalTime dateToLocalTime(Date d) {
        Instant instant = d.toInstant();
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZONE);
        return localDateTime.toLocalTime();
    }

    /**
     * 将LocalDate转换成Date
     *
     * @param localDate
     * @return date
     */
    public static Date localDateToDate(LocalDate localDate) {
        Instant instant = localDate.atStartOfDay().atZone(ZONE).toInstant();
        return Date.from(instant);
    }

    /**
     * 将LocalDateTime转换成Date
     *
     * @param localDateTime
     * @return date
     */
    public static Date localDateTimeToDate(LocalDateTime localDateTime) {
        Instant instant = localDateTime.atZone(ZONE).toInstant();
        return Date.from(instant);
    }

    //endregion


}

