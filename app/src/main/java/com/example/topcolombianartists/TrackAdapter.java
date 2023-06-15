package com.example.topcolombianartists;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.topcolombianartists.Model.Track;

import java.util.ArrayList;
import java.util.List;

public class TrackAdapter extends RecyclerView.Adapter<TrackAdapter.ViewHolder> {
    private List<Track> tracks = new ArrayList<>();

    public void setTracks(List<Track> tracks) {
        this.tracks = tracks;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TrackAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_track, parent, false);
        return new TrackAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final TrackAdapter.ViewHolder holder, final   int position) {
        //use the item of track and the position in list
        holder.bindData(tracks.get(position), position +1);
    }

    @Override
    public int getItemCount() {
        return tracks.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtTrackName;

        ViewHolder(View itemView) {
            super(itemView);
            txtTrackName = itemView.findViewById(R.id.txtTrackName);
        }

        void bindData(final Track item, final int position){
            //fill the view elements
            String trackName = position + ". " + item.getName();
            txtTrackName.setText(trackName);

        }
    }
}
