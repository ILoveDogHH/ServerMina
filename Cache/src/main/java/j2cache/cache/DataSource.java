package j2cache.cache;

import j2cache.CacheDataSource;
import j2cache.test.User;

/**
 * 缓存
 * @param <T>
 */
public class DataSource<T>  implements CacheDataSource<T> {




    public T load(){
        User user = new User();
        user.setAge("223242");
        return (T) user;
    }

}
