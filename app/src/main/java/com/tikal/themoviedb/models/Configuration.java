package com.tikal.themoviedb.models;

import java.util.List;

/**
 * Created by Yarden Rosenberg on 08/03/2016.
 */
public class Configuration {
    public static class ImagesConfiguration {

        public String base_url;
        public String secure_base_url;
        public List<String> poster_sizes;
        public List<String> backdrop_sizes;
        public List<String> profile_sizes;
        public List<String> logo_sizes;
    }

    public ImagesConfiguration images;
    public List<String> change_keys;

    public String getLargestPosterSize(){
        final List<String> posterSizes = images.poster_sizes;
        return posterSizes.get(posterSizes.size() - 2);
    }
}
