package j2cache.nullcache;

import j2cache.Cache;
import j2cache.CacheProvider;
import j2cache.util.CacheException;

import java.util.Properties;


public class NullCacheProvider implements CacheProvider {

    private final static NullCache cache = new NullCache();

    @Override
    public String name() {
        return "none";
    }


    @Override
    public Cache buildCache(String regionName, boolean isCreate) throws CacheException {
        return cache;
    }

    @Override
    public void start(Properties props) throws CacheException {
        
    }


    @Override
    public void stop() {
    }

}
