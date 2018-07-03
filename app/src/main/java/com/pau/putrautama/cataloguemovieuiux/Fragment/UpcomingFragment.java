package com.pau.putrautama.cataloguemovieuiux.Fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.pau.putrautama.cataloguemovieuiux.API.Client;
import com.pau.putrautama.cataloguemovieuiux.Adapter.MoviesAdapter;
import com.pau.putrautama.cataloguemovieuiux.Model.MovieList;
import com.pau.putrautama.cataloguemovieuiux.Model.MovieResponse;
import com.pau.putrautama.cataloguemovieuiux.R;
import com.pau.putrautama.cataloguemovieuiux.Util.Language;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class UpcomingFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private List<MovieList> mMovieLists;
    private MoviesAdapter mMoviesAdapter;
    private Call<MovieResponse> mMovieResponse;
    private Client mClient = new Client();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_upcoming, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView = view.findViewById(R.id.recyclerview_upcoming);

        mMovieLists = new ArrayList<>();
        mMoviesAdapter = new MoviesAdapter(getContext(),mMovieLists);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(),1));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mMoviesAdapter);
        mMoviesAdapter.notifyDataSetChanged();

        loadMoiveData();
    }
    private void loadMoiveData() {
        mMovieResponse = mClient.getService().getUpComing(Language.getCountry());
        mMovieResponse.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                List<MovieList> movies = response.body().getResults();
                mRecyclerView.setAdapter(new MoviesAdapter(getContext(), movies));

            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {

            }
        });

    }
}
