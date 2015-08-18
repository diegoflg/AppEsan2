package pe.edu.esan.appesan2;

import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * Created by educacionadistancia on 17/08/2015.
 */
public class CacheFragment  extends Fragment {
    private Cache cache = new Cache();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        cache.setData("Data goes here");
        this.setRetainInstance(true);
        super.onCreate(savedInstanceState);
    }

    public Cache getCache() {
        return cache;
    }

    public void setCache(Cache cache) {
        this.cache = cache;
    }

    private class Cache{
    String data;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}

}
