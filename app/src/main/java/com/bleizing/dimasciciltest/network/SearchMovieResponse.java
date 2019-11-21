package com.bleizing.dimasciciltest.network;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class SearchMovieResponse {
    @SerializedName("Search")
    @Expose
    private ArrayList<SearchMovie> searchMovieArrayList;

    @SerializedName("totalResults")
    @Expose
    private String totalResults;

    @SerializedName("Response")
    @Expose
    private String response;

    public void setResponse(String response) {
        this.response = response;
    }

    public ArrayList<SearchMovie> getSearchMovieArrayList() {
        return searchMovieArrayList;
    }

    public void setSearchMovieArrayList(ArrayList<SearchMovie> searchMovieArrayList) {
        this.searchMovieArrayList = searchMovieArrayList;
    }

    public String getResponse() {
        return response;
    }

    public void setTotalResults(String totalResults) {
        this.totalResults = totalResults;
    }

    public String getTotalResults() {
        return totalResults;
    }
}
