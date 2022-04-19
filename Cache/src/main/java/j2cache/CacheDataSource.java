package j2cache;

@FunctionalInterface
public interface CacheDataSource<T> {

    T load();

}
