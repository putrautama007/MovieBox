package com.pau.putrautama.cataloguemovieuiux.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.pau.putrautama.cataloguemovieuiux.DetailActivity;
import com.pau.putrautama.cataloguemovieuiux.model.MovieList;
import com.pau.putrautama.cataloguemovieuiux.R;

import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder> {
    private Context context;
    private List<MovieList> movieList;

    public MoviesAdapter(Context context, List<MovieList> movieList) {
        this.context = context;
        this.movieList = movieList;
    }

    @Override
    public MoviesAdapter.MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.film_items,parent,false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, final int position) {
        holder.filmTitle.setText(movieList.get(position).getOriginal_title());
        holder.filmReleaseDate.setText(movieList.get(position).getRelease_date());
        String vote = Double.toString(movieList.get(position).getVote_average());
        holder.filmRating.setText(vote);

        Glide.with(context)
                .load(movieList.get(position).getPosterPath())
                .into(holder.filmThumbnail);

        holder.btnDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MovieList clickDataItem = movieList.get(position);
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra(DetailActivity.ITEM_MOVIE, new Gson().toJson(clickDataItem));
                context.startActivity(intent);
                Toast.makeText(v.getContext(), clickDataItem.getOriginal_title(), Toast.LENGTH_SHORT).show();
            }
        });
        holder.btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setType("text/plain");
                intent.putExtra(Intent.EXTRA_TITLE,movieList.get(position).getTitle());
                intent.putExtra(Intent.EXTRA_SUBJECT,movieList.get(position).getTitle());
                intent.putExtra(Intent.EXTRA_TEXT,movieList.get(position).getTitle() + "\n\n" + movieList.get(position).getOverview());
                context.startActivity(Intent.createChooser(intent, context.getResources().getString(R.string.share)));
            }
        });
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder{
        private TextView filmTitle,filmReleaseDate,filmRating;
        private ImageView filmThumbnail;
        private Button btnDetail, btnShare;

        public MovieViewHolder(View view) {
            super(view);
            filmTitle = view.findViewById(R.id.film_title);
            filmReleaseDate = view.findViewById(R.id.film_release_date);
            filmRating = view.findViewById(R.id.film_rating);
            filmThumbnail = view.findViewById(R.id.film_poster);
            btnDetail = view.findViewById(R.id.btn_detail);
            btnShare = view.findViewById(R.id.btn_share);
        }
    }
}
