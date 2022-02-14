package logger;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.appender.RollingFileAppender;
import org.apache.logging.log4j.core.appender.rolling.CronTriggeringPolicy;
import org.apache.logging.log4j.core.appender.rolling.DirectWriteRolloverStrategy;
import org.apache.logging.log4j.core.appender.rolling.RolloverStrategy;
import org.apache.logging.log4j.core.appender.rolling.action.*;
import org.apache.logging.log4j.core.config.AppenderRef;
import org.apache.logging.log4j.core.config.LoggerConfig;
import org.apache.logging.log4j.core.layout.PatternLayout;

public class LoggerFactory {

    private static String getAppenderName(LoggerType type, String fileName) {
        return "appender<" + type.name() + ">" + fileName;
    }

    private static String getLoggerName(LoggerType type, String fileName) {
        return "logger<" + type.name() + ">" + fileName;
    }

    /**
     * @param fileDir
     *            文件放的路径
     * @param fileName
     *            文件名字
     * @param deleteFileDay
     *            是否自动删除, 0不删除, >0为deleteFileDay天之后删除
     */
    private static void newLogger(LoggerType type, String fileDir, String fileName, String fileNamePrefix,
                                  String fileNameSuffix, String pattern, String deleteFileTime) {
        // 创建一个打印样式layout
        PatternLayout layout = PatternLayout.newBuilder().withConfiguration(JLoggerWriter.config)
                .withPattern(pattern).build();

        // 增加文件滚动策略(按cronExpression分隔),
        // 使用方法见https://docs.oracle.com/cd/E12058_01/doc/doc.1014/e12030/cron_expressions.htm
        // 直接使用TimebasedTriggeringPolicy的话需要创建默认文件名, 无法和现在的同步
        CronTriggeringPolicy policy = CronTriggeringPolicy.createPolicy(JLoggerWriter.config, "true", "* * * * * ?");

        RolloverStrategy strategy = null;
        // 如果>0的话, 增加自动删除日志的action
        // TODO 优化为不会删除其他的log
        if (deleteFileTime != null && !"".equals(deleteFileTime)) {
            IfFileName ifFileName = IfFileName.createNameCondition(null, ".*" + fileName + ".*");
            IfLastModified ifLastModified = IfLastModified
                    .createAgeCondition(Duration.parse(deleteFileTime));
            DeleteAction deleteAction = DeleteAction.createDeleteAction(fileDir, false, 1, false, null,
                    new PathCondition[] { ifLastModified, ifFileName }, null, JLoggerWriter.config);
            Action[] actions = new Action[] { deleteAction };
            // 自动删除的日志的strategy
            strategy = DirectWriteRolloverStrategy.newBuilder().withCustomActions(actions)
                    .withStopCustomActionsOnError(false).withConfig(JLoggerWriter.config).build();
        }

        String appenderName = getAppenderName(type, fileName);
        String loggerName = getLoggerName(type, fileName);
        // 滚动appender
        RollingFileAppender appender = RollingFileAppender.newBuilder().
                withAppend(true)
                .setConfiguration(JLoggerWriter.config)
                .withFilePattern(fileDir + fileNamePrefix + fileName + fileNameSuffix)
                .withPolicy(policy).withStrategy(strategy).withImmediateFlush(true)
                .setLayout(layout)
                .setName(appenderName).build();

        // 启动appender
        appender.start();
        // 添加appender到config中
        JLoggerWriter.config.addAppender(appender);

        // appenderRef
        AppenderRef ref = AppenderRef.createAppenderRef(appenderName, null, null);
        AppenderRef[] refs = new AppenderRef[] { ref };
        LoggerConfig loggerConfig = LoggerConfig.createLogger(false, Level.INFO, loggerName, "true", refs, null,
                JLoggerWriter.config, null);
        loggerConfig.addAppender(appender, null, null);
        // 创建logger
        JLoggerWriter.config.addLogger(loggerName, loggerConfig);
        JLoggerWriter.context.updateLoggers();
    }

    /**
     * @param loggerDir
     *            文件放的路径
     * @param loggerName
     *            文件名字
     * @param deleteFileDay
     *            是否自动删除, 0不删除, >0为deleteFileDay天之后删除
     */
    private static void newDayLogger(String loggerDir, String loggerName, int deleteFileDay) {
        newLogger(LoggerType.DAY, loggerDir, loggerName, "%d{yyyyMMdd}_", ".log", "%d{yyyy-MM-dd HH:mm:ss},%m%n",
                deleteFileDay == 0 ? "" : (deleteFileDay + "d"));
    }

    /**
     * @param loggerDir
     *            文件放的路径
     * @param loggerName
     *            文件名字
     * @param deleteFileDay
     *            是否自动删除, 0不删除, >0为deleteFileDay天之后删除
     */
    private static void newHourLogger(String loggerDir, String loggerName, int deleteFileDay) {
        newLogger(LoggerType.HOUR, loggerDir, loggerName, "%d{yyyyMMddHH}_", ".log", "%d{yyyy-MM-dd HH:mm:ss},%m%n",
                deleteFileDay == 0 ? "" : (deleteFileDay + "H"));
    }

    /**
     * 关闭动态创建的logger，避免内存不够用或者文件打开太多
     *
     * @param loggerName
     */
    static void stop(LoggerType type, String fileName) {
        synchronized (JLoggerWriter.config) {
            String appenderName = getAppenderName(type, fileName);
            String loggerName = getLoggerName(type, fileName);
            JLoggerWriter.config.getAppender(appenderName).stop();
            JLoggerWriter.config.getLoggerConfig(loggerName).removeAppender(appenderName);
            JLoggerWriter.config.removeLogger(loggerName);
            JLoggerWriter.context.updateLoggers();
        }
    }

    /**
     * 获取一个gamelogger, 如果没有的话会生成一个.(暂时不提供自动删除日志的选项)
     *
     * @param fileDir
     *            文件放的路径
     * @param loggerName
     *            文件名字
     * @return
     */
    static Logger getHourLogger(String fileDir, String filerName) {
        String loggerName = getLoggerName(LoggerType.HOUR, filerName);
        synchronized (JLoggerWriter.config) {
            if (!JLoggerWriter.config.getLoggers().containsKey(loggerName)) {
                newHourLogger(fileDir, filerName, 0);
            }
        }
        return LogManager.getLogger(loggerName);
    }

    /**
     * 获取一个gamelogger, 如果没有的话会生成一个.(暂时不提供自动删除日志的选项)
     *
     * @param fileDir
     *            文件放的路径
     * @param loggerName
     *            文件名字
     * @return
     */
    static Logger getDayLogger(String fileDir, String filerName) {
        String loggerName = getLoggerName(LoggerType.DAY, filerName);
        synchronized (JLoggerWriter.config) {
            if (!JLoggerWriter.config.getLoggers().containsKey(loggerName)) {
                newDayLogger(fileDir, filerName, 0);
            }
        }
        return LogManager.getLogger(loggerName);
    }

    static Logger getCustomCronLogger(String fileDir, String filerName, String fileNamePrefix,
                                      String fileNameSuffix, String pattern, String deleteFileTime) {
        String loggerName = getLoggerName(LoggerType.CUSTOM_CRON, filerName);
        synchronized (JLoggerWriter.config) {
            if (!JLoggerWriter.config.getLoggers().containsKey(loggerName)) {
                newLogger(LoggerType.CUSTOM_CRON, fileDir, filerName, fileNamePrefix, fileNameSuffix, pattern,
                        deleteFileTime);
            }
        }
        return LogManager.getLogger(loggerName);
    }
}
