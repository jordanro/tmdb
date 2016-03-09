package com.tikal.themoviedb.models;

/**
 * Created by Yarden Rosenberg on 08/03/2016.
 */
public enum AppendToResponseItem {

    VIDEOS("videos"),
    RELEASES("releases"),
    RELEASE_DATES("release_dates"),
    CREDITS("credits"),
    SIMILAR("similar"),
    IMAGES("images"),
    EXTERNAL_IDS("external_ids");

    private final String value;

    private AppendToResponseItem(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
