package j2cache.util;

import j2cache.CacheConstans;
import j2cache.J2Cache;

import java.io.InputStream;
import java.util.Properties;


/**
 * Created by 12 on 2018/1/15.
 */
public class ConfigKit {
//    private static final Logger LOG = LoggerFactory.getLogger(ConfigKit.class);

    private static Properties props;
    private static String CONFIG_FILE =CacheConstans.CONFIG_FILE;

    /**
     * @param props
     */
    public static void setProps(Properties props) {
        ConfigKit.props = props;
    }

    public static Properties initFromConfig() {
        if (props == null) props = new Properties();

        try (InputStream configStream = getConfigStream()) {
            props.load(configStream);
            return props;
        } catch (Exception e) {
            throw new CacheException("Cannot find " + CONFIG_FILE + " !!!");
        }
    }



    /**
     * get j2cache properties stream
     *
     * @return
     */
    private static InputStream getConfigStream() {
//        LOG.info("Load J2Cache Config File : [{}].", CONFIG_FILE);
        InputStream configStream = J2Cache.class.getClassLoader().getResourceAsStream(CONFIG_FILE);
        if (configStream == null)
            configStream = J2Cache.class.getClassLoader().getParent().getResourceAsStream(CONFIG_FILE);
        if (configStream == null)
            throw new CacheException("Cannot find " + CONFIG_FILE + " !!!");
        return configStream;
    }

}
