package com.tikal.themoviedb.api;

import com.tikal.themoviedb.api.services.ConfigurationService;
import com.tikal.themoviedb.api.services.MoviesService;
import com.tikal.themoviedb.server.HttpClient;
import com.tikal.themoviedb.util.OSUtil;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.client.OkClient;

/**
 * Created by Yarden Rosenberg on 07/03/2016.
 */
public class MdbApi {

    public static final String API_URL = "https://api.themoviedb.org/3";
    public static final String PARAM_API_KEY = "api_key";

    public static final String API_KEY = "1943b285ed480045fa89950f09bffbae";


    private RestAdapter mRestAdapter;

    private static MdbApi mInstance = null;

    public static synchronized MdbApi getInstance() {
        if (mInstance == null) {
            mInstance = new MdbApi();
        }
        return mInstance;
    }

    private MdbApi() {
        RestAdapter.Builder builder = new RestAdapter.Builder();
        builder.setClient(new OkClient(HttpClient.getInstance()));
        builder.setEndpoint(API_URL);
//        builder.setConverter(new GsonConverter(TmdbHelper.getGsonBuilder().create()));
        builder.setRequestInterceptor(new RequestInterceptor() {
            public void intercept(RequestFacade requestFacade) {
                if (!OSUtil.isNetworkAvailable()) {
                    int maxStale = 60 * 60 * 24 * 28; // tolerate 4-weeks stale
                    requestFacade.addHeader("Cache-Control",
                            "public, only-if-cached, max-stale=" + maxStale);
                }
                requestFacade.addQueryParam(PARAM_API_KEY, API_KEY);
            }
        });

        mRestAdapter = builder.build();
    }

    public static ConfigurationService configurationService() {
        return getInstance().mRestAdapter.create(ConfigurationService.class);
    }

    public static MoviesService moviesService() {
        return getInstance().mRestAdapter.create(MoviesService.class);
    }
}
