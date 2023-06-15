package com.example.topcolombianartists;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.topcolombianartists.ViewModel.TracksViewModel;

import java.util.Objects;

public class TracksActivity extends AppCompatActivity {

    private RecyclerView rvTracksList;
    private TrackAdapter trackAdapter;
    private TracksViewModel tracksViewModel;
    private String artistName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracks);
        rvTracksList = findViewById(R.id.rvTracksList);
        rvTracksList.setLayoutManager(new LinearLayoutManager(this));
        trackAdapter = new TrackAdapter();
        rvTracksList.setAdapter(trackAdapter);

        Bundle bundle = getIntent().getExtras();
        if (bundle.getString("artist") != null){
            artistName = bundle.getString("artist");
            Objects.requireNonNull(getSupportActionBar()).setTitle(artistName);
        }else {
            finish();
        }
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        tracksViewModel = new ViewModelProvider(this).get(TracksViewModel.class);
        tracksViewModel.getTracks(getApplicationContext(), artistName).observe(this,tracks -> {
            trackAdapter.setTracks(tracks);
        });

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}