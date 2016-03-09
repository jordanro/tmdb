package com.tikal.themoviedb.api.services;

import com.tikal.themoviedb.models.AppendToResponse;
import com.tikal.themoviedb.models.Images;
import com.tikal.themoviedb.models.Movie;
import com.tikal.themoviedb.models.MovieResultsPage;
import com.tikal.themoviedb.models.Videos;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * Created by Yarden Rosenberg on 07/03/2016.
 */
public interface MoviesService {

    // Movie queries

    @GET("/movie/{id}")
              void summary(
                    @Path("id") int id,
                    @Query("language") String language,
                    Callback<Movie> callback
            );

    @GET("/movie/{id}")
              void details(
                    @Path("id") int id,
                    @Query("language") String language,
                    @Query("append_to_response") AppendToResponse appendToResponse,
                    Callback<Movie> callback
            );

    @GET("/movie/{id}/images")
    Images images(
            @Path("id") int id
    );

    @GET("/movie/{id}/videos")
    Videos videos(
            @Path("id") int tmdbId,
            @Query("language") String language
    );

    // Movie Lists

    @GET("/movie/popular")
    void popular(
            @Query("page") Integer page,
            Callback<MovieResultsPage> callback
    );

}
