package com.tikal.themoviedb.server;

import android.os.StatFs;

import com.squareup.okhttp.Cache;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Response;
import com.tikal.themoviedb.MovieDBApplication;
import com.tikal.themoviedb.util.OSUtil;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;


/**
 * Created by Yarden Rosenberg on 09/03/2016.
 */
public class HttpClient extends OkHttpClient {

    private static final int MAX_DISK_CACHE_SIZE = 50 * 1024 * 1024; // 50MB
    private static final int MIN_DISK_CACHE_SIZE = 5 * 1024 * 1024; // 5MB

    private static HttpClient instance = new HttpClient();


    public static HttpClient getInstance() {
        return instance;
    }

    private HttpClient() {
        File cache = createDefaultCacheDir();
        long size = calculateDiskCacheSize(cache);
        setCache(new Cache(cache, size));
        networkInterceptors().add(new CacheControlInterceptor());
    }

    static File createDefaultCacheDir() {
        File cache = new File(MovieDBApplication.getAppContext().getCacheDir(), "CashDir");
        if (!cache.exists()) {
            cache.mkdirs();
        }
        return cache;
    }

    static long calculateDiskCacheSize(File dir) {
        long size = MIN_DISK_CACHE_SIZE;

        try {
            StatFs statFs = new StatFs(dir.getAbsolutePath());
            long available = ((long) statFs.getBlockCount()) * statFs.getBlockSize();
            // Target 2% of the total space.
            size = available / 50;
        } catch (IllegalArgumentException ignored) {
        }

        // Bound inside min/max size for disk cache.
        return Math.max(Math.min(size, MAX_DISK_CACHE_SIZE), MIN_DISK_CACHE_SIZE);
    }

    public static boolean removeFromCache(String url){
        boolean removed = false;
        Cache cache = getInstance().getCache();
        if(cache != null){
            try {
                Iterator<String> it = cache.urls();
                while(it.hasNext()){
                    String currentUrl = it.next();

                    if(currentUrl.equals(url)){
                        it.remove();
                        removed = true;
                        break;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return removed;
    }

    private static class CacheControlInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Response originalResponse = chain.proceed(chain.request());
            if(!OSUtil.isNetworkAvailable()) {
                int maxStale = 60 * 60 * 24 * 28; // tolerate 4-weeks stale
                return originalResponse.newBuilder()
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                        .build();
            }
            else{
                return originalResponse;
            }

        }
    };
}
