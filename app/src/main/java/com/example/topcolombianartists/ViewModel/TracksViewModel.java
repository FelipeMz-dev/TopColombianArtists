package com.example.topcolombianartists.ViewModel;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.topcolombianartists.ApiHelper;
import com.example.topcolombianartists.Model.Track;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class TracksViewModel extends ViewModel {
    private MutableLiveData<List<Track>> tracksLiveData;

    public LiveData<List<Track>> getTracks(Context context, String artist){
        if (tracksLiveData == null){
            tracksLiveData = new MutableLiveData<>();
            loadTracks(context, artist);
        }
        return tracksLiveData;
    }

    private void loadTracks(Context currentContext, String currentArtist){
        List<Track> tracks = new ArrayList<>();
        ApiHelper api = new ApiHelper();
        api.GetTracks(currentContext, response -> {
            Log.e("response", response.toString());
            try {
                JSONArray tracksJSONArray = response.optJSONObject("toptracks").getJSONArray("track");
                for (int i=0;i<5;i++){
                    String nameTrack = tracksJSONArray.getJSONObject(i).getString("name");
                    Log.d("value", nameTrack);
                    tracks.add(new Track(nameTrack));
                    tracksLiveData.setValue(tracks);
                }
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }, currentArtist);
    }
}
