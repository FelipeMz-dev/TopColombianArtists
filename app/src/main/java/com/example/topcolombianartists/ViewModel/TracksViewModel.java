package com.example.topcolombianartists.ViewModel;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.topcolombianartists.ApiHelper;
import com.example.topcolombianartists.Model.Artist;
import com.example.topcolombianartists.Model.Track;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class TracksViewModel extends ViewModel {
    private MutableLiveData<List<Track>> tracksLiveData;

    /**
     * This method public load the data from model @{@link Track} for view @{@link com.example.topcolombianartists.View.TracksActivity}.
     * Use the data loaded by {@link #loadTracks(Context, String)}
     * @param context the context of main activity
     * **/
    public LiveData<List<Track>> getTracks(Context context, String artist){
        if (tracksLiveData == null){
            tracksLiveData = new MutableLiveData<>();
            loadTracks(context, artist);
        }
        return tracksLiveData;
    }

    /**
     * This method use the api service #GetTracks from @{@link ApiHelper} for fill the data in {@link #tracksLiveData}
     * @param currentContext the context for use the api
     * **/
    private void loadTracks(Context currentContext, String currentArtist){
        List<Track> tracks = new ArrayList<>();
        ApiHelper api = new ApiHelper();
        api.GetTracks(currentContext, response -> {
            //use the response of api as JSONObject
            try {
                //get list of tracks as JSONArray
                JSONArray tracksJSONArray = response.optJSONObject("toptracks").getJSONArray("track");
                for (int i=0;i<5;i++){
                    String nameTrack = tracksJSONArray.getJSONObject(i).getString("name");
                    tracks.add(new Track(nameTrack));
                    //update the last item of tracks
                    tracksLiveData.setValue(tracks);
                }
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }, currentArtist);
    }
}
