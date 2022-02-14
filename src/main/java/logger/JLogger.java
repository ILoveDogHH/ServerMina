package logger;

public class JLogger {

    private static String _getLogBasePath(String type) {
        return String.format("%s/daylog/%s/", System.getProperty("user.dir"), type);
    }


    public static void _daylog(String type, String msg, Object... args) {
        String path = _getLogBasePath(type);
        String filename = String.format("%s", type);
        _doWriteDayLog(path, filename, msg, args);
    }


    /**
     * 写入日志
     * @param path
     * @param file
     * @param msg
     * @param args
     */
    private static void _doWriteDayLog(String path, String file, String msg, Object... args) {
        try {
            LoggerUtils.dayLog(path, file, msg, args);
        } catch (Throwable e) {
            LoggerUtils.error("error when _doWriteDayLog log", e);
        }
    }

    /**
     * 只输出到 info.log
     * @param msg
     * @param args
     */
    public static void info(String msg, Object... args){
        LoggerUtils.info(msg, args);
    }

    /**
     * 只输出到debug.log
     * @param msg
     * @param args
     */
    public static void debug(String msg, Object... args){
        LoggerUtils.debug(msg, args);
    }


    /**
     * 只输出到error.log
     * @param msg
     * @param e
     */
    public static void error(String msg, Exception e){
        LoggerUtils.error(msg, e);
    }

    /**
     * 只输出到cron.log
     * @param msg
     * @param params
     */
    public static void cron(String msg, Object... params){
        LoggerUtils.cronInfo(msg, params);
    }


}
