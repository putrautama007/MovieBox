package com.pau.putrautama.cataloguemovieuiux.api;

import com.pau.putrautama.cataloguemovieuiux.model.MovieResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Service {
    @GET("movie/now_playing")
    Call<MovieResponse> getNowPlaying(@Query("language") String language);

    @GET("movie/upcoming")
    Call<MovieResponse> getUpComing(@Query("language") String language);

    @GET("movie/popular")
    Call<MovieResponse> getPopular(@Query("language") String language);

    @GET("movie/top_rated")
    Call<MovieResponse> getTopRated(@Query("language") String language);

    @GET("search/movie")
    Call<MovieResponse> getSeachMovie(@Query("query") String query, @Query("language") String language);


}
