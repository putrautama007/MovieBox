package com.pau.putrautama.cataloguemovieuiux.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.pau.putrautama.cataloguemovieuiux.R;
import com.pau.putrautama.cataloguemovieuiux.adapter.MoviesAdapter;
import com.pau.putrautama.cataloguemovieuiux.api.Client;
import com.pau.putrautama.cataloguemovieuiux.model.MovieList;
import com.pau.putrautama.cataloguemovieuiux.model.MovieResponse;
import com.pau.putrautama.cataloguemovieuiux.util.Language;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class TopRatedFragment extends Fragment {


    private RecyclerView mRecyclerView;
    private List<MovieList> mMovieLists;
    private MoviesAdapter mMoviesAdapter;
    private Call<MovieResponse> mMovieResponse;
    private Client mClient = new Client();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_top_rated, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mRecyclerView = view.findViewById(R.id.recyclerview_top_rated);

        mMovieLists = new ArrayList<>();
        mMoviesAdapter = new MoviesAdapter(getContext(),mMovieLists);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(),1));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mMoviesAdapter);
        mMoviesAdapter.notifyDataSetChanged();

        loadMoiveData();
    }

    private void loadMoiveData() {
        mMovieResponse = mClient.getService().getTopRated(Language.getCountry());
        mMovieResponse.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                List<MovieList> movies = response.body().getResults();
                mRecyclerView.setAdapter(new MoviesAdapter(getContext(), movies));

            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                Toast.makeText(getContext(), R.string.error_connection, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
