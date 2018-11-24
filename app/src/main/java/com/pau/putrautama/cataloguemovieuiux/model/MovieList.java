package com.pau.putrautama.cataloguemovieuiux.model;

import android.database.Cursor;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieList {
    @SerializedName("poster_path")
    private String posterPath;

    @SerializedName("overview")
    private String overview;

    @SerializedName("release_date")
    private String release_date;

    @SerializedName("id")
    private int id;

    @SerializedName("original_title")
    private String original_title;

    @SerializedName("original_language")
    private String original_language;

    @SerializedName("title")
    private String title;

    @SerializedName("backdrop_path")
    private String backdrop_path;

    @SerializedName("vote_average")
    private double vote_average;

    @SerializedName("genre_ids")
    private List<Integer> genreIds;

    @SerializedName("popularity")
    private double popularity;

    @SerializedName("adult")
    private boolean adult;

    @SerializedName("vote_count")
    private int voteCount;

    @SerializedName("video")
    private boolean video;

    public boolean isVideo() {
        return video;
    }

    public void setVideo(boolean video) {
        this.video = video;
    }

    public List<Integer> getGenreIds() {
        return genreIds;
    }

    public void setGenreIds(List<Integer> genreIds) {
        this.genreIds = genreIds;
    }

    public double getPopularity() {
        return popularity;
    }

    public void setPopularity(double popularity) {
        this.popularity = popularity;
    }

    public boolean isAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public void setVote_average(double vote_average) {
        this.vote_average = vote_average;
    }

    public String getPosterPath() {
        return "https://image.tmdb.org/t/p/w500"+posterPath;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public double getVote_average() {
        return vote_average;
    }


    public MovieList(){

    }
   }
