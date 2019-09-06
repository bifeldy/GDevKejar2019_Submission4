package id.ac.umn.made_basiliusbias_submission4.models;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import id.ac.umn.made_basiliusbias_submission4.CacheReq;
import id.ac.umn.made_basiliusbias_submission4.R;
import id.ac.umn.made_basiliusbias_submission4.Utility;
import id.ac.umn.made_basiliusbias_submission4.apis.DiscoverAPI;
import id.ac.umn.made_basiliusbias_submission4.pojos.Movie;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.List;

public class DiscoverMovieViewModel extends ViewModel {

    private MutableLiveData<List<Movie>> listDiscoverMovie = new MutableLiveData<>();

    public void loadDiscoverMovie(View v, ImageView loadingImage, TextView loadingText, String sort_by, int year) {

        String urlApi = v.getContext().getResources().getString(R.string.tmdb_api) +
                        "discover/movie?api_key=" +
                        v.getContext().getResources().getString(R.string.tmdb_key) +
                        "&sort_by=" +
                        sort_by +
                        "&year=" +
                        year +
                        "&language=" +
                        Utility.getAppLanguage(v.getContext()) +
                        "&page=1"
        ;

        // API
        DiscoverAPI discoverMovieAPI = new DiscoverAPI();
        discoverMovieAPI.setContext(v.getContext());

        // Fetching Data
        CacheReq cacheReq = new CacheReq(0, urlApi,
        response -> {
            try {

                // Create Object For DiscoverMovie Response
                final String jsonString = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
                JSONObject jsonObject = new JSONObject(jsonString);
                discoverMovieAPI.createDiscoverMovie(jsonObject);

                // Hide Loading Animation
                loadingImage.setVisibility(View.GONE);
                loadingText.setVisibility(View.GONE);

                // Add Data & Show
                listDiscoverMovie.postValue(discoverMovieAPI.getDiscoverMovie().getResults());
                v.findViewById(R.id.recycler_fragment).setVisibility(View.VISIBLE);
            }
            catch (UnsupportedEncodingException | JSONException e) {
                e.printStackTrace();
            }
        },
        error -> {

            // Show Error Animation
            Glide.with(v.getContext())
                .load(R.drawable.maido3)
                .override(256, 256)
                .transition(DrawableTransitionOptions.withCrossFade(125))
                .into(loadingImage)
            ;

            // Error Messages
            loadingText.setText(error.toString());
            loadingText.setTextColor(Color.RED);
        });
        RequestQueue requestQueue = Volley.newRequestQueue(v.getContext());
        requestQueue.add(cacheReq);
    }

    public LiveData<List<Movie>> getDiscoverMovie() {
        return listDiscoverMovie;
    }
}
