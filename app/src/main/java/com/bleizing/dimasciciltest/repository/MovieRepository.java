package com.bleizing.dimasciciltest.repository;

import androidx.lifecycle.MutableLiveData;

import com.bleizing.dimasciciltest.network.APIService;
import com.bleizing.dimasciciltest.network.DetailMovieResponse;
import com.bleizing.dimasciciltest.network.HTTPClient;
import com.bleizing.dimasciciltest.network.SearchMovie;
import com.bleizing.dimasciciltest.network.SearchMovieResponse;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MovieRepository {

    private static MovieRepository movieRepository;

    private MutableLiveData<List<SearchMovie>> searchMovieMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<DetailMovieResponse> detailMovieResponseMutableLiveData = new MutableLiveData<>();

    private APIService apiService;

    public static MovieRepository getInstance() {
        if (movieRepository == null) {
            movieRepository = new MovieRepository();
        }
        return movieRepository;
    }

    public MovieRepository() {
        apiService = HTTPClient.getClient().create(APIService.class);
    }

    public MutableLiveData<List<SearchMovie>> searchMovies(String s, int page) {
        apiService.searchMovies(s, page, HTTPClient.API_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SearchMovieResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(SearchMovieResponse searchMovieResponse) {
                        if (searchMovieResponse.getResponse().equals("True")) {
                            searchMovieMutableLiveData.setValue(searchMovieResponse.getSearchMovieArrayList());
                        } else {
                            searchMovieMutableLiveData.setValue(null);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        searchMovieMutableLiveData.setValue(null);
                    }

                    @Override
                    public void onComplete() {

                    }
                });

        return searchMovieMutableLiveData;
    }

    public MutableLiveData<DetailMovieResponse> getDetailMovieById(String id) {
        apiService.getDetailMovie(id, "full", HTTPClient.API_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<DetailMovieResponse>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(DetailMovieResponse detailMovieResponse) {
                        if (detailMovieResponse.getResponse().equals("True")) {
                            detailMovieResponseMutableLiveData.setValue(detailMovieResponse);
                        } else {
                            detailMovieResponseMutableLiveData.setValue(null);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        detailMovieResponseMutableLiveData.setValue(null);
                    }

                    @Override
                    public void onComplete() {

                    }
                });

        return detailMovieResponseMutableLiveData;
    }
}
