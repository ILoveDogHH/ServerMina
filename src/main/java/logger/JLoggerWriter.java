package logger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.Configuration;

import java.io.File;

public enum JLoggerWriter {
	INSTANCE;

	static LoggerContext context = (LoggerContext) LogManager.getContext(false);
	static Configuration config = context.getConfiguration();

	private static Logger infoLogger;
	private static Logger debuglogger;
	private static Logger errorLogger;
	private static Logger cronLogger;

	static{
		infoLogger = LogManager.getLogger("infoLogger");
		debuglogger = LogManager.getLogger("debuglogger");
		errorLogger = LogManager.getLogger("errorLogger");
		cronLogger = LogManager.getLogger("cronLogger");
	}

	/**
	 * 输出debug log，debug版本使用，默认只输出到console
	 * 
	 * @param msg
	 */
	public void debug(String msg, Object... args) {
		debuglogger.debug(msg, args);
	}

	/**
	 * 输出error log, 默认只输出到error.log文件
	 * 
	 * @param msg
	 * @param e
	 */
	public void error(String msg, Throwable e) {
		errorLogger.error(msg, e);
	}

	/**
	 * 输出 info log，默认只输出到info.log
	 * 
	 * @param msg
	 */
	public void info(String msg, Object... args) {
		infoLogger.info(msg, args);
	}

	/**
	 * 输出cron info log，只应该被cron模块调用，默认只输出到cron.log文件
	 * 
	 * @param msg
	 */
	public void cronInfo(String msg, Object... args) {
		cronLogger.info(msg, args);
	}

	/**
	 * 输出cron error log，只应该被corn模块调用，默认只输出到error.log文件
	 * 
	 * @param msg
	 * @param e
	 */
	public void cronError(String msg, Throwable e) {
		error(msg, e);
	}





	/**
	 * 写日志
	 * 
	 * @param file
	 *            文件的路径
	 * @param msg
	 *            要写入的信息
	 * @param args
	 *            msg相关参数
	 */
	public void dayGameLog(String path, String file, String msg, Object... args) {
		LoggerFactory.getDayLogger(path, file).info(msg, args);
	}

	/**
	 * 写日志
	 * 
	 * @param file
	 *            文件的路径
	 * @param msg
	 *            要写入的信息
	 * @param args
	 *            msg相关参数
	 */
	public void hourGameLog(String path, String file, String msg, Object... args) {
		LoggerFactory.getHourLogger(path, file).info(msg, args);
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
	public void customGameLog(String path, String file, String fileNamePrefix, String fileNameSuffix,
			String pattern, String deleteFileTime, String msg, Object... args) {
		LoggerFactory
				.getCustomCronLogger(path, file, fileNamePrefix, fileNameSuffix, pattern, deleteFileTime)
				.info(msg, args);
	}

	public void stopLogger(LoggerType type, String fileName) {
		LoggerFactory.stop(type, fileName);
	}

	public void reconfigure(String configFile) {
		synchronized (config) {
			// 移除之前的config
			context.getLoggers().forEach(logger -> {
				String loggerName = logger.getName();
				logger.getAppenders().forEach((appenderName, appender) -> {
					appender.stop();
					config.getLoggerConfig(loggerName).removeAppender(appenderName);
				});
				config.removeLogger(loggerName);
			});
			context.updateLoggers();

			boolean hasReconfig = false;
			// 重新初始化Log4j2的配置上下文
			if (configFile != null) {
				File file = new File(configFile);
				if (file.exists()) {
					context.setConfigLocation(file.toURI());
					hasReconfig = true;
				}
			}
			if (!hasReconfig) {
				context.reconfigure();
			}
			context = (LoggerContext) LogManager.getContext(false);
			config = context.getConfiguration();
		}
	}
}
