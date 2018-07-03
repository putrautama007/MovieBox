package com.pau.putrautama.cataloguemovieuiux.API;

import com.pau.putrautama.cataloguemovieuiux.Model.MovieResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Service {
    @GET("movie/now_playing")
    Call<MovieResponse> getNowPlaying(@Query("language") String language);

    @GET("movie/upcoming")
    Call<MovieResponse> getUpComing(@Query("language") String language);


    @GET("search/movie")
    Call<MovieResponse> getSeachMovie(@Query("query") String query, @Query("language") String language);
}
