package com.tikal.themoviedb.models;

/**
 * Created by Yarden Rosenberg on 08/03/2016.
 */
public class AppendToResponse {

    private final AppendToResponseItem[] items;

    public AppendToResponse(AppendToResponseItem... items) {
        this.items = items;
    }

    @Override
    public String toString() {
        if (items != null && items.length > 0) {
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < items.length ; i++) {
                sb.append(items[i]);

                if (i < items.length - 1) {
                    sb.append(',');
                }
            }

            return sb.toString();
        }

        return null;
    }
}
