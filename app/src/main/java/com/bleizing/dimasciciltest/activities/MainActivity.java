package com.bleizing.dimasciciltest.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.SearchView;

import com.bleizing.dimasciciltest.R;
import com.bleizing.dimasciciltest.RecyclerViewClickListener;
import com.bleizing.dimasciciltest.RecyclerViewTouch;
import com.bleizing.dimasciciltest.adapter.MovieAdapter;
import com.bleizing.dimasciciltest.network.SearchMovie;
import com.bleizing.dimasciciltest.viewmodel.MovieViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = MainActivity.class.getSimpleName();

    private Handler handler = new Handler();
    private Runnable runnable;

    private int page = 1;

    private String title = "";

    private MovieAdapter movieAdapter;
    private ArrayList<SearchMovie> searchMovieArrayList = new ArrayList<>();

    private int previousTotal = 0;
    private boolean loading = true;
    private int visibleThreshold = 5;
    private int firstVisibleItem, visibleItemCount, totalItemCount;

    private MovieViewModel movieViewModel;

    public static String EXTRA_IMDB_ID = "imdbID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        movieViewModel = ViewModelProviders.of(this).get(MovieViewModel.class);
        movieViewModel.init();

        movieViewModel.getSearchMovie().observe(this, new Observer<List<SearchMovie>>() {
            @Override
            public void onChanged(List<SearchMovie> searchMovies) {
                searchMovieArrayList = movieAdapter.getSearchMovieArrayList();
                if (searchMovies != null && searchMovies.size() > 0) {
                    searchMovieArrayList.addAll(searchMovies);
                    movieAdapter.setSearchMovieArrayList(searchMovieArrayList);
                }
            }
        });

        SearchView svMovie = (SearchView) findViewById(R.id.sv_movie);

        final RecyclerView rvMovie = (RecyclerView) findViewById(R.id.rv_movie);
        final GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        rvMovie.setLayoutManager(gridLayoutManager);
        rvMovie.setItemAnimator(new DefaultItemAnimator());
        rvMovie.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                firstVisibleItem = ((GridLayoutManager) gridLayoutManager).findFirstVisibleItemPosition();
                visibleItemCount = recyclerView.getChildCount();
                totalItemCount = gridLayoutManager.getItemCount();

                if (loading) {
                    if (totalItemCount > previousTotal) {
                        loading = false;
                        previousTotal = totalItemCount;
                    }
                }

                if (!loading && (totalItemCount - visibleItemCount) <= (firstVisibleItem + visibleThreshold)) {
                    if (page <= 4) {
                        loading = true;
                        page++;

                        movieViewModel.processSearchMovie(title, page);
                    }
                }
            }
        });
        rvMovie.addOnItemTouchListener(new RecyclerViewTouch(this, rvMovie, new RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {
                SearchMovie searchMovie = searchMovieArrayList.get(position);

                Intent intent = new Intent(MainActivity.this, DetailMovieActivity.class);
                intent.putExtra(EXTRA_IMDB_ID, searchMovie.getImdbID());

                startActivity(intent);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        movieAdapter = new MovieAdapter(this, searchMovieArrayList);
        rvMovie.setAdapter(movieAdapter);

        svMovie.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(final String newText) {
                if (!newText.equals("")) {
                    handler.removeCallbacks(runnable);
                    if (newText.length() > 0) {
                        runnable = new Runnable() {
                            @Override
                            public void run() {
                                if (!title.equals(newText)) {
                                    page = 1;
                                    previousTotal = 0;
                                    searchMovieArrayList.clear();
                                    movieAdapter.setSearchMovieArrayList(searchMovieArrayList);
                                    title = newText;
                                    movieViewModel.processSearchMovie(title, page);
                                }
                            }
                        };
                        handler.postDelayed(runnable, 2000);
                    }
                }

                return false;
            }
        });
    }
}
