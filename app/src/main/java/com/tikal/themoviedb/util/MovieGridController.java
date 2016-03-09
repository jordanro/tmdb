package com.tikal.themoviedb.util;

import com.tikal.themoviedb.models.Movie;

/**
 * Created by Yarden Rosenberg on 08/03/2016.
 */
public interface MovieGridController {

    void onMovieClick(Movie movie);
    void onMovieListLoaded(Movie firstMovie);
}
