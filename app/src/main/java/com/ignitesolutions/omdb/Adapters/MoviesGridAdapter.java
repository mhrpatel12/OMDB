package com.ignitesolutions.omdb.Adapters;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ignitesolutions.omdb.Models.GridItemMovies;
import com.ignitesolutions.omdb.MovieDetailsActivity;
import com.ignitesolutions.omdb.R;
import com.ignitesolutions.omdb.Utils.LogUtils;
import com.ignitesolutions.omdb.Utils.Utility;

import java.util.ArrayList;

/**
 * Created by Mihir on 4/22/2017.
 */

public class MoviesGridAdapter extends ArrayAdapter<GridItemMovies> {

    private Context mContext;
    private Activity mActivity;
    public static int pos;
    int layoutResourceId;

    ArrayList<GridItemMovies> movie = new ArrayList<GridItemMovies>();

    public MoviesGridAdapter(Activity activity, int layoutResourceId, ArrayList<GridItemMovies> movie) {
        super(activity, layoutResourceId, movie);
        this.layoutResourceId = layoutResourceId;
        this.mContext = activity;
        this.movie = movie;
        this.mActivity = activity;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View row = convertView;
        RecordHolder holder = null;
        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            //final View
            row = inflater.inflate(R.layout.grid_item_movies, parent, false);

            /*LayoutInflater inflater = ((BlocksActivity) ).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);*/
            holder = new RecordHolder();
            holder.layoutMovieDetails = (FrameLayout) row.findViewById(R.id.layout_grid_item);
            holder.imgMoviePoster = (ImageView) row.findViewById(R.id.imgMoviePoster);
            holder.txtMovieTitle = (TextView) row.findViewById(R.id.txtMovieTitle);
            holder.txtReleaseYear = (TextView) row.findViewById(R.id.txtMovieReleaseYear);

            row.setTag(holder);
        } else {
            holder = (RecordHolder) row.getTag();
        }
        final GridItemMovies item = movie.get(position);
        Glide.with(mContext).load(item.getMoviePosterPath()).into(holder.imgMoviePoster);
        holder.txtMovieTitle.setText(item.getMovieTitle());
        holder.txtReleaseYear.setText(item.getMovieReleaseYear());

        holder.layoutMovieDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mActivity, MovieDetailsActivity.class);
                intent.putExtra("imdbID", item.getMovieID() + "");
                mActivity.startActivity(intent);
            }
        });

        return row;
    }

    static class RecordHolder {
        FrameLayout layoutMovieDetails;
        ImageView imgMoviePoster;
        TextView txtMovieTitle, txtReleaseYear;
    }

}
