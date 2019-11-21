package com.bleizing.dimasciciltest.network;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DetailMovieResponse {
    @SerializedName("Title")
    @Expose
    private String title;

    @SerializedName("Year")
    @Expose
    private String year;

    @SerializedName("Rated")
    @Expose
    private String rated;

    @SerializedName("Released")
    @Expose
    private String released;

    @SerializedName("Runtime")
    @Expose
    private String runtime;

    @SerializedName("Genre")
    @Expose
    private String genre;

    @SerializedName("Director")
    @Expose
    private String director;

    @SerializedName("Writer")
    @Expose
    private String writer;

    @SerializedName("Actors")
    @Expose
    private String actors;

    @SerializedName("Plot")
    @Expose
    private String plot;

    @SerializedName("Poster")
    @Expose
    private String poster;

    @SerializedName("Type")
    @Expose
    private String type;

    @SerializedName("Production")
    @Expose
    private String production;

    @SerializedName("Website")
    @Expose
    private String website;

    @SerializedName("Response")
    @Expose
    private String response;

    public void setYear(String year) {
        this.year = year;
    }

    public String getYear() {
        return year;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getTitle() {
        return title;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getActors() {
        return actors;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return director;
    }

    public void setActors(String actors) {
        this.actors = actors;
    }

    public String getGenre() {
        return genre;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getRated() {
        return rated;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
    }

    public String getReleased() {
        return released;
    }

    public void setRated(String rated) {
        this.rated = rated;
    }

    public String getResponse() {
        return response;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getPoster() {
        return poster;
    }

    public void setProduction(String production) {
        this.production = production;
    }

    public String getRuntime() {
        return runtime;
    }

    public void setReleased(String released) {
        this.released = released;
    }

    public String getWriter() {
        return writer;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    public String getType() {
        return type;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getProduction() {
        return production;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getWebsite() {
        return website;
    }
}
