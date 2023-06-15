package com.example.topcolombianartists.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.topcolombianartists.ArtistAdapter;
import com.example.topcolombianartists.R;
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
            //when pressed the item, change view whit artist name
            Intent intent = new Intent(MainActivity.this, TracksActivity.class);
            intent.putExtra("artist", item.getName());
            startActivity(intent);
        });
        rvArtistsList.setAdapter(artistAdapter);
        //load the artist list from viewModel
        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);
        mainViewModel.getArtists(getApplicationContext()).observe(this, artists -> {
            artistAdapter.setArtists(artists);
        });
    }
}