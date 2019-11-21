package com.bleizing.dimasciciltest.network;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIService {
    @GET(".")
    Observable<SearchMovieResponse> searchMovies(@Query("s") String s, @Query("page") int page, @Query("apikey") String apiKey);

    @GET(".")
    Observable<DetailMovieResponse> getDetailMovie(@Query("i") String id, @Query("plot") String plot, @Query("apikey") String apiKey);
}
