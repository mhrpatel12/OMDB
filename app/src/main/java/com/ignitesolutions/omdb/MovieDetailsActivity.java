package com.ignitesolutions.omdb;

import android.content.Intent;
import android.graphics.Movie;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.ignitesolutions.omdb.ServiceCallHelper.WebServiceCall;
import com.ignitesolutions.omdb.ServiceCallHelper.WebserviceResponseListener;
import com.ignitesolutions.omdb.Utils.APIURLs;
import com.ignitesolutions.omdb.Utils.Utility;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

public class MovieDetailsActivity extends AppCompatActivity implements WebserviceResponseListener {
    private static String TAG = "";
    private TextView txtMovieTitle, txtReleaseYear, txtMoviePlot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        TAG = this.getClass().getSimpleName().toString();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        txtMovieTitle = (TextView) findViewById(R.id.txtMovieTitle);
        txtReleaseYear = (TextView) findViewById(R.id.txtMovieReleaseYear);
        txtMoviePlot = (TextView) findViewById(R.id.txtMoviePlot);

        findViewById(R.id.btnBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        String imdbId = getIntent().getStringExtra("imdbID");
        if (Utility.checkInternetConnection(MovieDetailsActivity.this)) {

            Map<String, String> postParam = new HashMap<String, String>();
            WebServiceCall serviceCall = new WebServiceCall(MovieDetailsActivity.this, (APIURLs.GET_MOVIE_PLOT_DETAILS + (imdbId + "&plot=full")), MovieDetailsActivity.this);
            serviceCall.postRequest(1, TAG, true);
        } else {
            Toast.makeText(MovieDetailsActivity.this, "No internet connection", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void response(int returnFrom, String strresponse) {
        switch (returnFrom) {
            case 1:
                try {
                    JSONObject response = new JSONObject(strresponse);
                    if (response != null) {
                        if (response.getString("Response").equals("True")) {
                            Glide.with(MovieDetailsActivity.this).load(response.getString("Poster")).into((ImageView) findViewById(R.id.imgMoviePoster));
                            txtMovieTitle.setText(response.getString("Title"));
                            txtReleaseYear.setText(response.getString("Year"));
                            txtMoviePlot.setText(response.getString("Plot"));
                        } else {
                            showError(response.getString("Error"));
                        }
                    } else {
                        showError("Yikes ! Some unknown error occurred.");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

    private void showError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onError(int returnFrom, String errorMsg) {
        Toast.makeText(this, errorMsg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
