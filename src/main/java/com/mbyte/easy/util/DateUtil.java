package com.mbyte.easy.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.Date;

/**
 * @ClassName: DateUtil
 * @Description: 时间工具类
 * @Version 1.0
 **/
public class DateUtil {
    /**
     * 日志
     */
    private static final Logger logger = LoggerFactory.getLogger(DateUtil.class);
    /**
     * date format yyyy-MM-dd HH:mm:ss
     */
    public static final String PATTERN_24_h = "yyyy-MM-dd HH:mm:ss";
    /**
     * date format PATTERN_yyyyMMddHHmmss
     */
    public static final String PATTERN_yyyyMMddHHmmss = "yyyyMMddHHmmss";
    /**
     * date format yyyyMMddHHmmssSSS
     */
    public static final String PATTERN_yyyyMMddHHmmssSSS = "yyyyMMddHHmmssSSS";
    /**
     * date format yyyyMMdd
     */
    public static final String PATTERN_yyyyMMdd = "yyyyMMdd";
    /**
     * date format yyyyMMdd
     */
    public static final String PATTERN_MMDD = "MMDD";
    /**
     * 月索引获取对应季度（月份减1），第一个0，用于占位
     */
    public static final int [] QUARTERS = {0,1,1,1,2,2,2,3,3,3,4,4,4};

    /**
     * 枚举时间单位
     */
    enum TimeUnits{
        YEAR,MONTH,DAY,HOUR,MINUTES,SECOND
    }
    /**
     * LocalDateTime->Date
     * @param localDateTime
     * @return
     */
    public static Date local2Date(LocalDateTime localDateTime){
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }
    /**
     *LocalDate->Date
     * @param localDate
     * @return
     */
    public static Date local2Date(LocalDate localDate){
        return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
    }
    /**
     *LocalDate->Date
     * @param localTime
     * @return
     */
    public static Date local2Date(LocalTime localTime){
        return Date.from(LocalDateTime.of(LocalDate.now(), localTime).atZone(ZoneId.systemDefault()).toInstant());
    }
    /**
     *Date->LocalDate
     * @param date
     * @return
     */
    public static LocalDate date2LocalDate(Date date){
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()).toLocalDate();
    }
    /**
     *Date->LocalDate
     * @param date
     * @return
     */
    public static LocalDateTime date2LocalDateTime(Date date){
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }
    /**
     *Date->LocalTime
     * @param date
     * @return
     */
    public static LocalTime date2LocalTime(Date date){
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()).toLocalTime();
    }
    /**
     * @Title: plusDate
     * @Description: 根据偏移量（大于0后移，小于0前移）获取时间
     * @param: date 基准时间，null为当前时间
     * @param: chronoUnit 单位
     * @param: count 数值
     * @return: java.time.LocalDateTime
     * @throws:
     */
    public static LocalDateTime plusDate(LocalDateTime localDateTime,ChronoUnit chronoUnit,int count){
        //默认取当前时间
        localDateTime = localDateTime == null ? LocalDateTime.now() : localDateTime;
        switch (chronoUnit){
            case YEARS:
                return localDateTime.plusYears(count);
            case MONTHS:
                return localDateTime.plusMonths(count);
            case DAYS:
                return localDateTime.plusDays(count);
            case HOURS:
                return localDateTime.plusHours(count);
            case MINUTES:
                return localDateTime.plusMinutes(count);
            case SECONDS:
                return localDateTime.plusSeconds(count);
            case NANOS:
                return localDateTime.plusNanos(count);
            default:
                return localDateTime.plusYears(count);
        }
    }
    
    /**
     * 获取某季度第一天
     * @param quarter eg：1、2、3、4季度
     * @return
     *  成功：返回正确的日期
     *  失败：返回null
     */
    public static LocalDate getQuarterOfFirstDay(int quarter){
        switch (quarter){
            case 1:
                return LocalDate.of(LocalDate.now().getYear(),1,1);
            case 2:
                return LocalDate.of(LocalDate.now().getYear(),4,1);
            case 3:
                return LocalDate.of(LocalDate.now().getYear(),7,1);
            case 4:
                return LocalDate.of(LocalDate.now().getYear(),10,1);
            default:
                return null;
        }
    }
    /**
     *获取某季度最后一天
     * @param quarter：1、2、3、4季度
     * @return
     *  成功：返回正确的日期
     *  失败：返回null
     */
    public static LocalDate getQuarterOfLastDay(int quarter){
        switch (quarter){
            case 1:
                return LocalDate.of(LocalDate.now().getYear(),3,1).with(TemporalAdjusters.lastDayOfMonth());
            case 2:
                return LocalDate.of(LocalDate.now().getYear(),6,1).with(TemporalAdjusters.lastDayOfMonth());
            case 3:
                return LocalDate.of(LocalDate.now().getYear(),9,1).with(TemporalAdjusters.lastDayOfMonth());
            case 4:
                return LocalDate.of(LocalDate.now().getYear(),12,1).with(TemporalAdjusters.lastDayOfMonth());
            default:
                return null;
        }
    }
    /**
     * 格式化时间
     * @param localDateTime
     * @param pattern 格式
     * @return
     */
    public static String format(LocalDateTime localDateTime, String pattern){
        //默认取当前时间
        localDateTime = localDateTime == null ? LocalDateTime.now() : localDateTime;
        return localDateTime.format(DateTimeFormatter.ofPattern(pattern));
    }
    /**
     * @Title: getCurrentTime
     * @Description: 获取当前时间
     * @param: pattern
     * @return: java.lang.String
     * @throws: 
     */
    public static String getCurrentTime(String pattern){
        return format(null,pattern);
    }
    /**
     * 获取日期时间差endDate-startDate
     * @param startDate
     * @param endDate
     * @return
     * startDate before 0f  endDate =>正数
     * startDate after 0f  endDate =>负数
     */
    public static long bewteenTwoDays(LocalDate startDate,LocalDate endDate){
        return ChronoUnit.DAYS.between(startDate, endDate);
    }
    /**
     * 获取时间差endDateTime-startDateTime
     * @param startDateTime
     * @param endDateTime
     * @param chronoUnit ChronoUnit枚举类型
     * @return
     * startDateTime before 0f  endDateTime =>正数
     * startDateTime after 0f  endDateTime =>负数
     */
    public static long bewteenTwoTimes(LocalDateTime startDateTime,LocalDateTime endDateTime,ChronoUnit chronoUnit){
        switch (chronoUnit){
            case YEARS:
                return ChronoUnit.YEARS.between(startDateTime, endDateTime);
            case MONTHS:
                return ChronoUnit.MONTHS.between(startDateTime, endDateTime);
            case DAYS:
                return ChronoUnit.DAYS.between(startDateTime, endDateTime);
            case HOURS:
                return ChronoUnit.HOURS.between(startDateTime, endDateTime);
            case MINUTES:
                return ChronoUnit.MINUTES.between(startDateTime, endDateTime);
            case SECONDS:
                return ChronoUnit.SECONDS.between(startDateTime, endDateTime);
            case NANOS:
                return ChronoUnit.NANOS.between(startDateTime, endDateTime);
            default:
                return 0;
        }
    }
    
    /**
     * 
    * @Title: differentDays
    * @Description: TODO date2比date1多的天数
    * @param @param date1
    * @param @param date2
    * @param @return   
    * @return int    
    * @throws
     */
    public static int differentDays(Date date1,Date date2)
    {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);
        
        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
       int day1= cal1.get(Calendar.DAY_OF_YEAR);
        int day2 = cal2.get(Calendar.DAY_OF_YEAR);
        
        int year1 = cal1.get(Calendar.YEAR);
        int year2 = cal2.get(Calendar.YEAR);
        if(year1 != year2)   //同一年
        {
            int timeDistance = 0 ;
            for(int i = year1 ; i < year2 ; i ++)
            {
                if(i%4==0 && i%100!=0 || i%400==0)    //闰年            
                {
                    timeDistance += 366;
                }
                else    //不是闰年
                {
                    timeDistance += 365;
                }
            }
            
            return timeDistance + (day2-day1) ;
        }
        else    //不同年
        {
//            System.out.println("判断day2 - day1 : " + (day2-day1));
            return day2-day1;
        }
    }
}
