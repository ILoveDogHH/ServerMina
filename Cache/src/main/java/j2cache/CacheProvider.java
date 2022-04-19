package j2cache;

import j2cache.util.CacheException;

import java.util.Properties;


public interface CacheProvider {

    String name();

    Cache buildCache(String regionName, boolean isCreate) throws CacheException;

    void start(Properties props) throws CacheException;

    void stop();

}
