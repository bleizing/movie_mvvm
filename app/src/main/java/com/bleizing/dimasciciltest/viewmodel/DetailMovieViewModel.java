package com.bleizing.dimasciciltest.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.bleizing.dimasciciltest.network.DetailMovieResponse;
import com.bleizing.dimasciciltest.repository.MovieRepository;

public class DetailMovieViewModel extends ViewModel {
    private MutableLiveData<DetailMovieResponse> mutableLiveData;
    private MovieRepository movieRepository;

    public void init(String id) {
        if (mutableLiveData != null) {
            return;
        }

        movieRepository = MovieRepository.getInstance();
        if (mutableLiveData == null) {
            mutableLiveData = new MutableLiveData<>();
            processGetDetailMovieById(id);
        }
    }

    public void processGetDetailMovieById(String id) {
        mutableLiveData = movieRepository.getDetailMovieById(id);
    }

    public LiveData<DetailMovieResponse> getMutableLiveData() {
        return mutableLiveData;
    }
}
