package com.tikal.themoviedb.models;

import java.util.List;

/**
 * Created by Yarden Rosenberg on 07/03/2016.
 */
public class MovieResultsPage {

    public Integer page;
    public Integer total_pages;
    public Integer total_results;

    public List<Movie> results;
}
