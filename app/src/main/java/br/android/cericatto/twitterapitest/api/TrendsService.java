package br.android.cericatto.twitterapitest.api;

import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.GET;

/**
 * TrendsService.java class.
 *
 * @author Rodrigo Cericatto
 * @since Sep 28, 2016
 */
public interface TrendsService {
    @GET("/1.1/trends/place.json?id=1")
    void getTrends(Callback<Response> callback);
}