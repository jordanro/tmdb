package com.tikal.themoviedb.models;

/**
 * Created by Yarden Rosenberg on 07/03/2016.
 */

import java.util.ArrayList;
import java.util.List;

public class Movie {

    public Integer id;


    public String overview;
    public Double popularity;
    public String poster_path;
    public String release_date;
    public Integer revenue;
    public Integer runtime;
//    public List<SpokenLanguage> spoken_languages;
    public String tagline;
    public String title;
    public Double vote_average;
    public Integer vote_count;
    public Videos videos;

    private ArrayList<Video> trailers = null;

    public List<Video> getTrailers(){
        if(trailers == null){
            trailers = new ArrayList<>();
            if(videos != null){
                for(Video video : videos.results){
                    if("Trailer".equals(video.type)){
                        trailers.add(video);
                    }
                }
            }
        }
        return trailers;
    }

    // Following are used with append_to_response
//    public Videos videos;
//    public Releases releases;
//    public ReleaseDatesResult release_dates;
//    public Credits credits;
//    public MovieResultsPage similar;
}
