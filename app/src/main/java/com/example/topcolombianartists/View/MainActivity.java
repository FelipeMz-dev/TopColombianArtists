package com.example.topcolombianartists;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.topcolombianartists.ViewModel.MainViewModel;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvArtistsList;
    private ArtistAdapter artistAdapter;
    private MainViewModel mainViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvArtistsList = findViewById(R.id.rvArtistsList);
        rvArtistsList.setLayoutManager(new LinearLayoutManager(this));
        artistAdapter = new ArtistAdapter(item -> {
            Intent intent = new Intent(MainActivity.this, TracksActivity.class);
            intent.putExtra("artist", item.getName());
            startActivity(intent);
        });
        rvArtistsList.setAdapter(artistAdapter);

        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        mainViewModel.getArtists(getApplicationContext()).observe(this, artists -> {
            artistAdapter.setArtists(artists);
        });
    }
}