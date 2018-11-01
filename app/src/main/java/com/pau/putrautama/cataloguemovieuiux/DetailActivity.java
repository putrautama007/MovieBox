package com.pau.putrautama.cataloguemovieuiux;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.pau.putrautama.cataloguemovieuiux.model.MovieList;

import java.util.Date;

public class DetailActivity extends AppCompatActivity {

    public static final String ITEM_MOVIE = "item_movie";
    TextView mFilmTitle;
    TextView mFilmSysnopsis;
    TextView mFilmRating;
    TextView mFilmReleaseDate;
    ImageView mFilmPoster;
    private MovieList movieList;
    private Gson gson = new Gson();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        initCollapsingToolbar();
        String json = getIntent().getStringExtra(ITEM_MOVIE);
        movieList = gson.fromJson(json, MovieList.class);

        mFilmTitle = findViewById(R.id.title);
        mFilmSysnopsis = findViewById(R.id.sysnopsis);
        mFilmRating = findViewById(R.id.userrating);
        mFilmReleaseDate = findViewById(R.id.release);
        mFilmPoster = findViewById(R.id.film_poster_header);

        loadData();


    }


    private void initCollapsingToolbar() {
        final CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collpase_toolbar );
        collapsingToolbarLayout.setTitle("");
        AppBarLayout appBarLayout = findViewById(R.id.appbar);
        appBarLayout.setExpanded(true);

        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1){
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0){
                    collapsingToolbarLayout.setTitle(movieList.getTitle());
                    isShow = true;
                }else  if (isShow){
                    collapsingToolbarLayout.setTitle("");
                    isShow = false;
                }

            }
        });
    }

    private void loadData() {

        mFilmTitle.setText(movieList.getTitle());

        Glide.with(this)
                .load( BuildConfig.IMG_URL + "w500" + movieList.getBackdrop_path())
                .into(mFilmPoster);

        mFilmSysnopsis.setText(movieList.getOverview());
        String rating = Double.toString(movieList.getVote_average());
        mFilmRating.setText(rating);
        mFilmReleaseDate.setText(movieList.getRelease_date());

    }

}
