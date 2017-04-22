package com.ignitesolutions.omdb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.ignitesolutions.omdb.Adapters.MoviesGridAdapter;
import com.ignitesolutions.omdb.Models.GridItemMovies;
import com.ignitesolutions.omdb.Utils.ExpandableHeightGridView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;

public class SearchResultesActivity extends AppCompatActivity {

    private ExpandableHeightGridView gridMovies;
    private ArrayList<GridItemMovies> gridArray;
    private MoviesGridAdapter moviesGridAdapter;
    private TextView txtSearchQuery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_resultes);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        findViewById(R.id.btnBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        txtSearchQuery = (TextView) findViewById(R.id.txtSearchText);
        txtSearchQuery.setText(getIntent().getStringExtra("searchQuery"));

        gridMovies = (ExpandableHeightGridView) findViewById(R.id.gridMovies);
        try {
            JSONObject json_object = new JSONObject(getIntent().getStringExtra("searchResults"));
            JSONArray jsonArr = json_object.getJSONArray("Search");
            gridArray = new ArrayList<GridItemMovies>();
            for (int i = 0; i < jsonArr.length(); i++) {
                JSONObject item = jsonArr.getJSONObject(i);
                GridItemMovies movie = new GridItemMovies();
                movie.setMoviePosterPath(item.getString("Poster"));
                movie.setMovieTitle(item.getString("Title"));
                movie.setMovieReleaseYear(item.getString("Year"));
                movie.setMovieID(item.getString("imdbID"));

                gridArray.add(movie);
            }
            moviesGridAdapter = new MoviesGridAdapter(SearchResultesActivity.this, R.layout.activity_search_resultes, gridArray);
            gridMovies.setAdapter(moviesGridAdapter);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
