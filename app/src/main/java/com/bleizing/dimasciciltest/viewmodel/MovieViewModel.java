package com.bleizing.dimasciciltest.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.bleizing.dimasciciltest.repository.MovieRepository;
import com.bleizing.dimasciciltest.network.SearchMovie;

import java.util.List;

public class MovieViewModel extends ViewModel {

    private MutableLiveData<List<SearchMovie>> mutableLiveData;
    private MovieRepository movieRepository;

    public void init() {
        if (mutableLiveData != null) {
            return;
        }

        movieRepository = MovieRepository.getInstance();
        if (mutableLiveData == null) {
            mutableLiveData = new MutableLiveData<>();
            processSearchMovie("", 1);
        }
    }

    public void processSearchMovie(String s, int page) {
        mutableLiveData = movieRepository.searchMovies(s, page);
    }

    public LiveData<List<SearchMovie>> getSearchMovie() {
        return mutableLiveData;
    }
}
