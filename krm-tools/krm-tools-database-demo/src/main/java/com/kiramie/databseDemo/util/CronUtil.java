package com.kiramie.databseDemo.util;

import com.cronutils.model.Cron;
import com.cronutils.model.CronType;
import com.cronutils.model.definition.CronDefinition;
import com.cronutils.model.definition.CronDefinitionBuilder;
import com.cronutils.model.time.ExecutionTime;
import com.cronutils.parser.CronParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class CronUtil {

    public static Logger logger = LoggerFactory.getLogger(CronUtil.class);

    public static final SimpleDateFormat sdf = new SimpleDateFormat("ss mm HH dd MM ? yyyy");

    /**
     * 检查cron表达式的合法性
     *
     * @param cron cron exp
     * @return true if valid
     */
    public static boolean checkValid(String cron) {
        try {
            CronDefinition cronDefinition = CronDefinitionBuilder.instanceDefinitionFor(CronType.QUARTZ);
            CronParser parser = new CronParser(cronDefinition);
            parser.parse(cron);
        } catch (IllegalArgumentException e) {
            logger.error(String.format("cron=%s not valid", cron));
            return false;
        }
        return true;
    }
    public static List<String> getExecutionTimeByNum(String cronStr, Integer num) {
        CronParser parser = new CronParser(CronDefinitionBuilder.instanceDefinitionFor(CronType.QUARTZ));
        Cron cron = parser.parse(cronStr);
        ExecutionTime time = ExecutionTime.forCron(cron);
        ZonedDateTime now = ZonedDateTime.now();
        ZonedDateTime next = getNext(time, now);
        List<ZonedDateTime> timeList = new ArrayList<>(num);
        timeList.add(next);
        for (int i = 1; i < num; i++) {
            next = getNext(time, next);
            timeList.add(next);
        }
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        List<String> resultList = new ArrayList<>(num);
        for (ZonedDateTime item : timeList) {
            String result = item.format(format);
            resultList.add(result);
        }
        return resultList;
    }
    public static List<String> getExecutionTimeByNum(String cronStr, Integer num,String zont_time_id) {
        CronParser parser = new CronParser(CronDefinitionBuilder.instanceDefinitionFor(CronType.QUARTZ));
        Cron cron = parser.parse(cronStr);
        ExecutionTime time = ExecutionTime.forCron(cron);
        ZonedDateTime now = ZonedDateTime.now(ZoneId.of(zont_time_id));
        ZonedDateTime next = getNext(time, now);
        List<ZonedDateTime> timeList = new ArrayList<>(num);
        timeList.add(next);
        for (int i = 1; i < num; i++) {
            next = getNext(time, next);
            timeList.add(next);
        }
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        List<String> resultList = new ArrayList<>(num);
        for (ZonedDateTime item : timeList) {
            String result = item.format(format);
            resultList.add(result);
        }

        return resultList;
    }
    public static long getNextTime(String cronStr) {
        CronParser parser = new CronParser(CronDefinitionBuilder.instanceDefinitionFor(CronType.QUARTZ));
        Cron cron = parser.parse(cronStr);
        ExecutionTime time = ExecutionTime.forCron(cron);
        ZonedDateTime now = ZonedDateTime.now();
        ZonedDateTime next = getNext(time, now);
        return next.toInstant().toEpochMilli();
    }
    public static long getNextTime(String cronStr,String zont_time_id) {
        CronParser parser = new CronParser(CronDefinitionBuilder.instanceDefinitionFor(CronType.QUARTZ));
        Cron cron = parser.parse(cronStr);
        ExecutionTime time = ExecutionTime.forCron(cron);
        ZonedDateTime now = ZonedDateTime.now(ZoneId.of(zont_time_id));
        ZonedDateTime next = getNext(time, now);
        return next.toInstant().toEpochMilli();
    }

    public static long getNextTime(String cronStr, String zont_time_id, Long beginTimeMillis) {
        ZoneId zoneId = ZoneId.of("Asia/Shanghai");
        CronParser parser = new CronParser(CronDefinitionBuilder.instanceDefinitionFor(CronType.QUARTZ));
        Cron cron = parser.parse(cronStr);
        ExecutionTime time = ExecutionTime.forCron(cron);
        //ZonedDateTime now = ZonedDateTime.now(ZoneId.of(zont_time_id));
        LocalDateTime localDateTime = Instant.ofEpochMilli(beginTimeMillis).atZone(zoneId).toLocalDateTime();
        ZonedDateTime of = ZonedDateTime.of(localDateTime, zoneId);
        ZonedDateTime next = getNext(time, of);
        return next.toInstant().toEpochMilli();
    }

    private static ZonedDateTime getNext(ExecutionTime time, ZonedDateTime current) {
        return time.nextExecution(current).get();
    }

    /***
     *  日期转换cron表达式
     *
     * @param date
     * @return
     */
    public static String formatDateByPattern(Date date) {
        String formatTimeStr = null;
        if (Objects.nonNull(date)) {
            formatTimeStr = sdf.format(date);
        }
        return formatTimeStr;
    }

    /***
     * convert Date to cron, eg "0 07 10 15 1 ? 2021"
     * @param date  : 时间点
     * @return
     */
    public static String getCron(Date date) {
        return formatDateByPattern(date);
    }

}
