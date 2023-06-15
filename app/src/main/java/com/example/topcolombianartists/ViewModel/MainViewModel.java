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

    public LiveData<List<Artist>> getArtists(Context context){
        if (artistsLiveData == null){
            artistsLiveData = new MutableLiveData<>();
            loadArtists(context);
        }
        return artistsLiveData;
    }

    private void loadArtists(Context currentContext){
        List<Artist> artists = new ArrayList<>();
        ApiHelper api = new ApiHelper();
        api.GetArtists(currentContext, response -> {
            try {
                JSONArray artistsJSONArray = response.optJSONObject("topartists").getJSONArray("artist");
                for (int i=0;i<10;i++){
                    String nameArtist = artistsJSONArray.getJSONObject(i).getString("name");
                    Log.e("value", nameArtist);
                    artists.add(new Artist(nameArtist));
                    artistsLiveData.setValue(artists);
                }
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
