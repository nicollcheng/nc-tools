package cn.nicollcheng.tools.date;

import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;
import java.util.Objects;

/**
 * @author <a href="mailto:email.nicollcheng@gmail.com">nicollcheng</a>
 * <b>Creation Time:</b> 2021-09-04 15:54.
 * desc 日期工具类
 */
public class DateTools {
    /**
     * 获取指定日期的时间戳
     * @param year 年
     * @param month 月
     * @param dayOfMonth 指定月份中的日
     * @return 时间戳
     */
    public static long getTimestamp(int year, int month, int dayOfMonth) {
        return LocalDate.of(year, month, dayOfMonth).atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    /**
     * LocalDate转Date
     * @param localDate {@link LocalDate} 对象
     * @return {@link Date}
     */
    public static Date localDate2Date(LocalDate localDate) {
        return Date.from(localDate.atStartOfDay(ZoneOffset.ofHours(8)).toInstant());
    }

    /**
     * Date转LocalDate
     * @param date {@link Date} 对象
     * @return {@link LocalDate}
     */
    public static LocalDate date2LocalDate(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    /**
     * LocalDateTime转Date
     * @param localDateTime {@link LocalDateTime} 对象
     * @return {@link Date}
     */
    public static Date localDateTime2Date(LocalDateTime localDateTime) {
        return Date.from(localDateTime.toInstant(ZoneOffset.ofHours(8)));
    }

    /**
     * Date转LocalDateTime
     * @param date {@link Date} 对象
     * @return {@link LocalDateTime}
     */
    public static LocalDateTime date2LocalDateTime(Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    /**
     * 时间戳转LocalDate
     * @param timestamp 时间戳
     * @return {@link LocalDate}
     */
    public static LocalDate timestamp2LocalDate(long timestamp) {
        return Instant.ofEpochMilli(timestamp).atZone(ZoneOffset.ofHours(8)).toLocalDate();
    }

    /**
     * LocalDate转时间戳
     * @param localDate {@link LocalDate}
     * @return 时间戳
     */
    public static long localDate2Timestamp(LocalDate localDate) {
        return localDate.atStartOfDay(ZoneOffset.ofHours(8)).toInstant().toEpochMilli();
    }

    /**
     * 时间戳转LocalDateTime
     * @param timestamp 时间戳
     * @return {@link LocalDateTime}
     */
    public static LocalDateTime timestamp2localDateTime(long timestamp) {
        return Instant.ofEpochMilli(timestamp).atZone(ZoneOffset.ofHours(8)).toLocalDateTime();
    }

    /**
     * LocalDateTime转时间戳
     * @param localDateTime {@link LocalDateTime}
     * @return 时间戳
     */
    public static long localDateTime2Timestamp(LocalDateTime localDateTime) {
        return localDateTime.toInstant(ZoneOffset.ofHours(8)).toEpochMilli();
    }

    /**
     * 获取指定日期格式的日期的时间戳
     * @param timestamp 时间戳
     * @param pattern 日期格式
     * @return 日期字符串
     */
    private String toDateString(long timestamp, String pattern) {
        LocalDateTime localDateTime = Instant.ofEpochMilli(timestamp).atZone(ZoneOffset.ofHours(8)).toLocalDateTime();
        return localDateTime.format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 获取指定日期格式的日期的时间戳
     * @param date 日期字符串
     * @param pattern 日期格式
     * @return 间隔天数
     */
    private static long getTimestamp(String date, String pattern) {
        DateTimeFormatter ftf = DateTimeFormatter.ofPattern(pattern);
        LocalDate parse = LocalDate.parse(date, ftf);
        return parse.atStartOfDay(ZoneOffset.ofHours(8)).toInstant().toEpochMilli();
    }

    /**
     * 获取两个日期时间戳相隔天数
     * @param startTimestamp 开始时间戳
     * @param endTimestamp 结束时间戳
     * @return 间隔天数
     */
    public static long betweenDays(long startTimestamp, long endTimestamp) {
        LocalDate start = Instant.ofEpochMilli(startTimestamp).atZone(ZoneOffset.ofHours(8)).toLocalDate();
        LocalDate end = Instant.ofEpochMilli(endTimestamp).atZone(ZoneOffset.ofHours(8)).toLocalDate();
        return ChronoUnit.DAYS.between(start, end);
    }

    /**
     * 获取当天开始时间戳
     * @return 时间戳
     */
    public static long getTodayStartTimestamp() {
        return LocalDateTime.of(LocalDate.now(), LocalTime.MIN).toInstant(ZoneOffset.ofHours(8)).toEpochMilli();
    }

    /**
     * 获取当天结束时间戳
     * @return 时间戳
     */
    public static long getTodayEndTimestamp() {
        return LocalDateTime.of(LocalDate.now(), LocalTime.MAX).toInstant(ZoneOffset.ofHours(8)).toEpochMilli();
    }

    /**
     * 获取日期字符串的第n天的开始时间戳
     * @param dateTime 时间字符串
     * @param pattern 日期格式
     * @param nextDays 0：当天，-1：上一天，1：下一天
     * @return timestamp
     */
    private static long getDateStrStartTimestamp(String dateTime, String pattern, int nextDays) {
        LocalDate localDate;
        if (!Objects.isNull(pattern) && (pattern.contains("HH"))) {
            localDate = LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern(pattern)).toLocalDate();
        }else {
            localDate = LocalDate.parse(dateTime, DateTimeFormatter.ofPattern(pattern));
        }
        return localDate.plusDays(nextDays).atStartOfDay(ZoneOffset.ofHours(8)).toInstant().toEpochMilli();
    }

    /**
     * 获取日期字符串的第n天的结束时间戳
     * @param dateTime 时间字符串
     * @param pattern 日期格式
     * @param nextDays 0：当天，-1：上一天，1：下一天
     * @return timestamp
     */
    private static long getDateStrEndTimestamp(String dateTime, String pattern, int nextDays) {
        LocalDate localDate;
        if (!Objects.isNull(pattern) && (pattern.contains("HH"))) {
            localDate = LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern(pattern)).toLocalDate();
        }else {
            localDate = LocalDate.parse(dateTime, DateTimeFormatter.ofPattern(pattern));
        }
        return LocalDateTime.of(localDate, LocalTime.MAX).plusDays(nextDays).toInstant(ZoneOffset.ofHours(8)).toEpochMilli();
    }

    /**
     * 获取指定时间戳间隔n周的开始时间戳
     * @param timestamp 时间戳
     * @param steps 0：本周，-1：上周，1：下周
     * @return timestamp
     */
    public static long getThisWeekStartTimestamp(long timestamp, int steps) {
        return Instant.ofEpochMilli(timestamp).atZone(ZoneOffset.ofHours(8)).toLocalDate()
                .with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY))
                .plusWeeks(steps).atStartOfDay(ZoneId.systemDefault()).toInstant()
                .toEpochMilli();
    }

    /**
     * 获取指定时间戳间隔n周的结束时间戳
     * @param timestamp 时间戳
     * @param steps 0：本周，-1：上周，1：下周
     * @return timestamp
     */
    public static long getThisWeekEndTimestamp(long timestamp, int steps) {
        return LocalDateTime.of(
                Instant.ofEpochMilli(timestamp).atZone(ZoneOffset.ofHours(8)).toLocalDate()
                        .with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY)),
                LocalTime.MAX)
                .plusWeeks(steps).toInstant(ZoneOffset.ofHours(8)).toEpochMilli();
    }

    /**
     * 获取指定时间戳的间隔n天的开始时间戳
     * @param timestamp 时间戳
     * @param nextDays 0：当天，-1：上一天，1：下一天
     * @return timestamp
     */
    private static long getNextDaysStartTimestamp(long timestamp, int nextDays) {
        return LocalDateTime.of(Instant.ofEpochMilli(timestamp).atZone(ZoneOffset.ofHours(8)).toLocalDate(), LocalTime.MIN).plusDays(nextDays).toInstant(ZoneOffset.ofHours(8)).toEpochMilli();
    }

    /**
     * 获取指定时间戳的间隔n天的结束时间戳
     * @param timestamp 时间戳
     * @param nextDays 0：当天，-1：上一天，1：下一天
     * @return timestamp
     */
    private static long getNextDaysEndTimestamp(long timestamp, int nextDays) {
        return LocalDateTime.of(Instant.ofEpochMilli(timestamp).atZone(ZoneOffset.ofHours(8)).toLocalDate(), LocalTime.MAX).plusDays(nextDays).toInstant(ZoneOffset.ofHours(8)).toEpochMilli();
    }

    /**
     * 获取指定时间戳间隔n个月的开始时间戳
     * @param timestamp 时间戳
     * @param steps 0：当月，-1：上个月，1：下个月
     * @return timestamp
     */
    public static long getStartMonthDayTimestamp(long timestamp, int steps) {
        LocalDate localDate = Instant.ofEpochMilli(timestamp).atZone(ZoneOffset.ofHours(8)).toLocalDate();
        LocalDate startDay = LocalDate.of(localDate.getYear(), localDate.getMonth(), 1);
        return startDay.plusMonths(steps).atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }
    /**
     * 获取指定时间戳间隔n个月的结束时间戳
     * @param timestamp 时间戳
     * @param steps 0：当月，-1：上个月，1：下个月
     * @return timestamp
     */
    public static long getEndMonthDayTimestamp(long timestamp, int steps) {
        LocalDate localDate = Instant.ofEpochMilli(timestamp).atZone(ZoneOffset.ofHours(8)).toLocalDate();
        LocalDate startDay = LocalDate.of(localDate.getYear(), localDate.getMonth(), localDate.getMonth().length(localDate.isLeapYear()));
        return LocalDateTime.of(startDay, LocalTime.MAX).plusMonths(steps).toInstant(ZoneOffset.ofHours(8)).toEpochMilli();
    }

    /**
     * 获取指定时间戳间隔n个季度的季度时间戳范围
     * @param timestamp 时间戳
     * @param steps 0：当前季度，-1：上个季度，1：下个季度
     * @return [startTimestamp, endTimestamp]
     */
    public static long[] getQuarterTimestampRange(long timestamp, int steps) {
        LocalDate localDate = Instant.ofEpochMilli(timestamp).atZone(ZoneOffset.ofHours(8)).toLocalDate();
        int month = localDate.getMonthValue();
        LocalDate endTime =  localDate.plusMonths(3 - (month%3) - (steps * 3))
                .with(TemporalAdjusters.lastDayOfMonth());
        LocalDate startTime =  endTime.plusMonths(-2)
                .with(TemporalAdjusters.firstDayOfMonth());
        return new long[] {
                LocalDateTime.of(startTime, LocalTime.MIN).toInstant(ZoneOffset.ofHours(8)).toEpochMilli(),
                LocalDateTime.of(endTime, LocalTime.MAX).toInstant(ZoneOffset.ofHours(8)).toEpochMilli()
        };
    }

    /**
     * 获取指定时间戳间隔n年的年时间戳范围
     * @param timestamp 时间戳
     * @param steps 0：当年，-1：上年，1：下年
     * @return [startTimestamp, endTimestamp]
     */
    public static long[] getYearTimestampRange(long timestamp, int steps) {
        LocalDate localDate = Instant.ofEpochMilli(timestamp).atZone(ZoneOffset.ofHours(8)).toLocalDate();
        LocalDate startDate =  LocalDate.of(localDate.getYear(), Month.JANUARY, 1);
        LocalDate endDate = LocalDate.of(localDate.getYear(), Month.DECEMBER, Month.DECEMBER.length(localDate.isLeapYear()));
        return new long[] {
                LocalDateTime.of(startDate, LocalTime.MIN).plusYears(steps).toInstant(ZoneOffset.ofHours(8)).toEpochMilli(),
                LocalDateTime.of(endDate, LocalTime.MAX).plusYears(steps).toInstant(ZoneOffset.ofHours(8)).toEpochMilli()
        };
    }
}
