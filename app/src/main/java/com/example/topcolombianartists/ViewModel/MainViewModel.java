package com.example.topcolombianartists.ViewModel;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.topcolombianartists.ApiHelper;
import com.example.topcolombianartists.Model.Artist;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class MainViewModel extends ViewModel {
    private MutableLiveData<List<Artist>> artistsLiveData;

    /**
     * This method public load the data from model @{@link Artist} for view @{@link com.example.topcolombianartists.View.MainActivity}.
     * Use the data loaded by {@link #loadArtists(Context)}
     * @param context the context of main activity
     * **/
    public LiveData<List<Artist>> getArtists(Context context){
        if (artistsLiveData == null){
            artistsLiveData = new MutableLiveData<>();
            loadArtists(context);
        }
        return artistsLiveData;
    }

    /**
     * This method use the api service #GetArtists from @{@link ApiHelper} for fill the data in {@link #artistsLiveData}
     * @param currentContext the context for use the api
     * **/
    private void loadArtists(Context currentContext){
        List<Artist> artists = new ArrayList<>();
        ApiHelper api = new ApiHelper();
        api.GetArtists(currentContext, response -> {
            //use the response of api as JSONObject
            try {
                //get list of artist as JSONArray
                JSONArray artistsJSONArray = response.optJSONObject("topartists").getJSONArray("artist");
                for (int i=0;i<10;i++){
                    String nameArtist = artistsJSONArray.getJSONObject(i).getString("name");
                    artists.add(new Artist(nameArtist));
                    //update the last item of artist
                    artistsLiveData.setValue(artists);
                }
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
