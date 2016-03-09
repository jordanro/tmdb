package com.tikal.themoviedb.api;

import com.tikal.themoviedb.api.services.ConfigurationService;
import com.tikal.themoviedb.api.services.MoviesService;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;

/**
 * Created by Yarden Rosenberg on 07/03/2016.
 */
public class MdbApi {

    public static final String API_URL = "https://api.themoviedb.org/3";
    public static final String PARAM_API_KEY = "api_key";

    public static final String API_KEY = "1943b285ed480045fa89950f09bffbae";


    private RestAdapter mRestAdapter;

    private static MdbApi mInstance = null;

    public static synchronized MdbApi getInstance(){
        if(mInstance == null){
            mInstance = new MdbApi();
        }
        return mInstance;
    }

    private MdbApi(){
        RestAdapter.Builder builder = new RestAdapter.Builder();

        builder.setEndpoint(API_URL);
//        builder.setConverter(new GsonConverter(TmdbHelper.getGsonBuilder().create()));
        builder.setRequestInterceptor(new RequestInterceptor() {
            public void intercept(RequestFacade requestFacade) {
                requestFacade.addQueryParam(PARAM_API_KEY, API_KEY);
            }
        });

        mRestAdapter = builder.build();
    }

    public static ConfigurationService configurationService(){
        return getInstance().mRestAdapter.create(ConfigurationService.class);
    }

    public static MoviesService moviesService() {
        return getInstance().mRestAdapter.create(MoviesService.class);
    }
}
