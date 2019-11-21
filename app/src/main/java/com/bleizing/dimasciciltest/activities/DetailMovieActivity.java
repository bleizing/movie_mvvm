package com.bleizing.dimasciciltest.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bleizing.dimasciciltest.R;
import com.bleizing.dimasciciltest.network.DetailMovieResponse;
import com.bleizing.dimasciciltest.viewmodel.DetailMovieViewModel;
import com.squareup.picasso.Picasso;

public class DetailMovieActivity extends AppCompatActivity {
    private static final String TAG = DetailMovieActivity.class.getSimpleName();

    private ImageView imgPoster;
    private TextView tvTitle;
    private TextView tvYear;
    private TextView tvDirector;
    private TextView tvRelease;
    private TextView tvGenre;
    private TextView tvRuntime;
    private TextView tvWriter;
    private TextView tvActor;
    private TextView tvRated;
    private TextView tvType;
    private TextView tvProduction;
    private TextView tvWebsite;
    private TextView tvPlot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);

        String imdbId = getIntent().getExtras().getString(MainActivity.EXTRA_IMDB_ID);

        setupUI();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        DetailMovieViewModel viewModel = ViewModelProviders.of(this).get(DetailMovieViewModel.class);
        viewModel.init(imdbId);

        viewModel.getMutableLiveData().observe(this, new Observer<DetailMovieResponse>() {
            @Override
            public void onChanged(DetailMovieResponse detailMovieResponse) {
                updateUI(detailMovieResponse);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if ( id == android.R.id.home ) {
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setupUI() {
        imgPoster = (ImageView) findViewById(R.id.img_poster);
        tvTitle = (TextView) findViewById(R.id.tv_title);
        tvYear = (TextView) findViewById(R.id.tv_year);
        tvDirector = (TextView) findViewById(R.id.tv_director);
        tvRelease = (TextView) findViewById(R.id.tv_release);
        tvGenre = (TextView) findViewById(R.id.tv_genre);
        tvRuntime = (TextView) findViewById(R.id.tv_runtime);
        tvWriter = (TextView) findViewById(R.id.tv_writer);
        tvActor = (TextView) findViewById(R.id.tv_actor);
        tvRated = (TextView) findViewById(R.id.tv_rated);
        tvType = (TextView) findViewById(R.id.tv_type);
        tvProduction = (TextView) findViewById(R.id.tv_production);
        tvWebsite = (TextView) findViewById(R.id.tv_website);
        tvPlot = (TextView) findViewById(R.id.tv_plot);

        tvTitle.setText(String.format(getResources().getString(R.string.title), ""));
        tvYear.setText(String.format(getResources().getString(R.string.year), ""));
        tvDirector.setText(String.format(getResources().getString(R.string.director), ""));
        tvRelease.setText(String.format(getResources().getString(R.string.release), ""));
        tvGenre.setText(String.format(getResources().getString(R.string.genre), ""));
        tvRuntime.setText(String.format(getResources().getString(R.string.runtime), ""));
        tvWriter.setText(String.format(getResources().getString(R.string.writer), ""));
        tvActor.setText(String.format(getResources().getString(R.string.actor), ""));
        tvRated.setText(String.format(getResources().getString(R.string.rated), ""));
        tvType.setText(String.format(getResources().getString(R.string.type), ""));
        tvProduction.setText(String.format(getResources().getString(R.string.production), ""));
        tvWebsite.setText(String.format(getResources().getString(R.string.website), ""));
        tvPlot.setText(String.format(getResources().getString(R.string.plot), ""));
    }

    private void updateUI(DetailMovieResponse detailMovieResponse) {
        Picasso.get().load(detailMovieResponse.getPoster()).placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).into(imgPoster);
        tvTitle.setText(String.format(getResources().getString(R.string.title), detailMovieResponse.getTitle()));
        tvYear.setText(String.format(getResources().getString(R.string.year), detailMovieResponse.getYear()));
        tvDirector.setText(String.format(getResources().getString(R.string.director), detailMovieResponse.getDirector()));
        tvRelease.setText(String.format(getResources().getString(R.string.release), detailMovieResponse.getReleased()));
        tvGenre.setText(String.format(getResources().getString(R.string.genre), detailMovieResponse.getGenre()));
        tvRuntime.setText(String.format(getResources().getString(R.string.runtime), detailMovieResponse.getRuntime()));
        tvWriter.setText(String.format(getResources().getString(R.string.writer), detailMovieResponse.getWriter()));
        tvActor.setText(String.format(getResources().getString(R.string.actor), detailMovieResponse.getActors()));
        tvRated.setText(String.format(getResources().getString(R.string.rated), detailMovieResponse.getRated()));
        tvType.setText(String.format(getResources().getString(R.string.type), detailMovieResponse.getType()));
        tvProduction.setText(String.format(getResources().getString(R.string.production), detailMovieResponse.getProduction()));
        tvWebsite.setText(String.format(getResources().getString(R.string.website), detailMovieResponse.getWebsite()));
        tvPlot.setText(String.format(getResources().getString(R.string.plot), detailMovieResponse.getPlot()));
    }
}
