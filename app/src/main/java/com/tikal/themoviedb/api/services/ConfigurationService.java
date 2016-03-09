package com.tikal.themoviedb.api.services;


import com.tikal.themoviedb.models.Configuration;

import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by Yarden Rosenberg on 08/03/2016.
 */
public interface ConfigurationService {

    @GET("/configuration")
    void configuration(Callback<Configuration> callback);

}
