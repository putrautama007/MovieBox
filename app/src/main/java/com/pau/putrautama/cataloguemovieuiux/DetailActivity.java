package com.pau.putrautama.cataloguemovieuiux;

import android.content.Intent;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {

    TextView mFilmTitle;
    TextView mFilmSysnopsis;
    TextView mFilmRating;
    TextView mFilmReleaseDate;
    ImageView mFilmPoster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        initCollapsingToolbar();
        mFilmTitle = findViewById(R.id.title);
        mFilmSysnopsis = findViewById(R.id.sysnopsis);
        mFilmRating = findViewById(R.id.userrating);
        mFilmReleaseDate = findViewById(R.id.release);
        mFilmPoster = findViewById(R.id.film_poster_header);


        Intent intentStartActivity = getIntent();

        if (intentStartActivity.hasExtra("original_title")){
            String thumbnail = getIntent().getExtras().getString("poster_path");
            String movieName = getIntent().getExtras().getString("original_title");
            String sysnopsis = getIntent().getExtras().getString("overview");
            String rating = getIntent().getExtras().getString("vote_average");
            String dateOfRelease = getIntent().getExtras().getString("release_date");

            Glide.with(this)
                    .load(thumbnail)
                    .into(mFilmPoster);

            mFilmTitle.setText(movieName);
            mFilmSysnopsis.setText(sysnopsis);
            mFilmRating.setText(rating);
            mFilmReleaseDate.setText(dateOfRelease);

        }else {
            Toast.makeText(this, R.string.no_api_data, Toast.LENGTH_SHORT).show();
        }
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
                    collapsingToolbarLayout.setTitle(getIntent().getExtras().getString("original_title"));
                    isShow = true;
                }else  if (isShow){
                    collapsingToolbarLayout.setTitle("");
                    isShow = false;
                }

            }
        });
    }
}
