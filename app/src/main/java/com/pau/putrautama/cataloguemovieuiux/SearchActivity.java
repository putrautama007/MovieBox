package com.pau.putrautama.cataloguemovieuiux;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.pau.putrautama.cataloguemovieuiux.API.Client;
import com.pau.putrautama.cataloguemovieuiux.API.Service;
import com.pau.putrautama.cataloguemovieuiux.Adapter.MoviesAdapter;
import com.pau.putrautama.cataloguemovieuiux.Model.MovieList;
import com.pau.putrautama.cataloguemovieuiux.Model.MovieResponse;
import com.pau.putrautama.cataloguemovieuiux.Util.Language;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private List<MovieList> mMovieLists;
    private MoviesAdapter mMoviesAdapter;
    private Call<MovieResponse> mMovieResponse;
    private Client mClient = new Client();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);


        mRecyclerView = findViewById(R.id.recyclerview_search);
        mMovieLists = new ArrayList<>();
        mMoviesAdapter = new MoviesAdapter(getApplicationContext(),mMovieLists);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mMoviesAdapter);

        String filmTitle = getIntent().getStringExtra("movie_title");
        loadData(filmTitle);
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        if (mMovieResponse != null) mMovieResponse.cancel();
   }

   private void loadData(String filmTitle){
       mMovieResponse = mClient.getService().getSeachMovie(filmTitle,Language.getCountry());
       mMovieResponse.enqueue(new Callback<MovieResponse>() {
           @Override
           public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
               List<MovieList> movies = response.body().getResults();
               mRecyclerView.setAdapter(new MoviesAdapter(getApplicationContext(), movies));

           }

           @Override
           public void onFailure(Call<MovieResponse> call, Throwable t) {

           }
       });
    }
}
