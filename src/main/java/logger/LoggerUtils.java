package logger;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoggerUtils {



    private LoggerUtils() { }

    public static void create(String configFile, String debugLoggerLevel) {
        System.setProperty("debugLoggerLevel", debugLoggerLevel);
        reconfigure(configFile);
    }

    public static void setDebugLoggerLevel(String debugLoggerLevel) {
        System.setProperty("debugLoggerLevel", debugLoggerLevel);
        reconfigure(null);
    }

    public static void reconfigure(String configFile) {
        JLoggerWriter.INSTANCE.reconfigure(configFile);
    }

    /**
     * 输出debug log，debug版本使用，默认只输出到console
     *
     * @param msg
     */
    public static void debug(String msg, Object... args) {
        JLoggerWriter.INSTANCE.debug(msg, args);
    }

    /**
     * 输出error log, 默认只输出到error.log文件
     *
     * @param msg
     * @param e
     */
    public static void error(String msg, Throwable e) {
        JLoggerWriter.INSTANCE.error(msg, e);
    }

    /**
     * 输出 info log，默认只输出到info.log
     *
     * @param msg
     */
    public static void info(String msg, Object... args) {
        JLoggerWriter.INSTANCE.info(msg, args);
    }

    /**
     * 输出cron info log，只应该被cron模块调用，默认只输出到cron.log文件
     *
     * @param msg
     */
    public static void cronInfo(String msg, Object... args) {
        JLoggerWriter.INSTANCE.cronInfo(msg, args);
    }


    /**
     * 输出cron error log，只应该被corn模块调用，默认只输出到error.log文件
     *
     * @param msg
     * @param e
     */
    public static void cronError(String msg, Throwable e) {
        error(msg, e);
    }




    /**
     * 写游戏日志
     * @param file 文件的路径
     * @param msg 要写入的信息
     */
    public static void dayLog(String path, String file, String msg, Object... args) {
        JLoggerWriter.INSTANCE.dayGameLog(path, file, msg, args);
    }

    /**
     * 写游戏日志
     *
     * @param file
     *            文件的路径
     * @param msg
     *            要写入的信息
     */
    public static void hourLog(String path, String file, String msg, Object... args) {
        JLoggerWriter.INSTANCE.hourGameLog(path, file, msg, args);
    }

    /**
     * @param path
     *            文件路径
     * @param file
     *            文件名字
     * @param fileNamePrefix
     *            文件前缀, 使用log4j2的xml配置规则
     * @param fileNameSuffix
     *            文件后缀, 使用log4j2的xml配置规则
     * @param cronExpression
     *            cron表达式
     *            https://docs.oracle.com/cd/E12058_01/doc/doc.1014/e12030/cron_expressions.htm
     * @param pattern
     *            写入规则, 使用log4j2的xml配置规则
     * @param deleteFileTime
     *            自动删除文件时间(空字符串或者null则不删除)
     * @param msg
     *            写入的内容, 比如test{}
     * @param args
     *            写入的参数, 替换msg中的{}
     */
    public static void customCronGameLog(String path, String file, String fileNamePrefix, String fileNameSuffix,
                                         String pattern, String deleteFileTime, String msg, Object... args) {
        JLoggerWriter.INSTANCE.customGameLog(path, file, fileNamePrefix, fileNameSuffix, pattern,
                deleteFileTime, msg, args);
    }

    public static void stopDayLogger(String filename) {
        JLoggerWriter.INSTANCE.stopLogger(LoggerType.DAY, filename);
    }

    public static void stopHourLogger(String filename) {
        JLoggerWriter.INSTANCE.stopLogger(LoggerType.HOUR, filename);
    }

    public static void stopCustomCronLogger(String filename) {
        JLoggerWriter.INSTANCE.stopLogger(LoggerType.CUSTOM_CRON, filename);
    }



}
