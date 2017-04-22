package com.ignitesolutions.omdb;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.View;
import android.widget.Toast;

import com.ignitesolutions.omdb.ServiceCallHelper.WebServiceCall;
import com.ignitesolutions.omdb.ServiceCallHelper.WebserviceResponseListener;
import com.ignitesolutions.omdb.Utils.APIURLs;
import com.ignitesolutions.omdb.Utils.Utility;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class SearchActivity extends AppCompatActivity implements WebserviceResponseListener {

    private static String TAG = "";
    private SearchView searchTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        TAG = this.getClass().getSimpleName().toString();

        searchTitle = (SearchView) findViewById(R.id.btnSearchTitle);

        searchTitle.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (Utility.checkInternetConnection(SearchActivity.this)) {

                    Map<String, String> postParam = new HashMap<String, String>();
                    postParam.put("title", "title");
                    WebServiceCall serviceCall = new WebServiceCall(SearchActivity.this, (APIURLs.GET_MOVIE_DETAILS + (searchTitle.getQuery() + "")), SearchActivity.this, new JSONObject(postParam));
                    serviceCall.postRequest(1, TAG, true);
                } else {
                    Toast.makeText(SearchActivity.this, "No internet connection", Toast.LENGTH_LONG).show();
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        searchTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public void response(int returnFrom, String strresponse) {
        switch (returnFrom) {
            case 1:
                try {
                    JSONObject response = new JSONObject(strresponse);
                    if (response != null) {
                        if (response.getString("Response").equals("True")) {
                            Intent intent = new Intent(SearchActivity.this, SearchResultesActivity.class);
                            intent.putExtra("searchResults", strresponse);
                            intent.putExtra("searchQuery", (searchTitle.getQuery() + ""));
                            startActivity(intent);
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
}
