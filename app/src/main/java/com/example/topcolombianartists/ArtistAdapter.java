package com.example.topcolombianartists;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.topcolombianartists.Model.Artist;

import java.util.ArrayList;
import java.util.List;

public class ArtistAdapter extends RecyclerView.Adapter<ArtistAdapter.ViewHolder> {
    private List<Artist> artists = new ArrayList<>();
    final ArtistAdapter.onItemClickListener listener;

    public ArtistAdapter(ArtistAdapter.onItemClickListener listener) {
        this.listener = listener;
    }

    public interface onItemClickListener{
        void onItemClick(Artist item);
    }

    public void setArtists(List<Artist> artists) {
        this.artists = artists;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ArtistAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_artist, parent, false);
        return new ArtistAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ArtistAdapter.ViewHolder holder, final   int position) {
        //use the item of artist and the position in list
        holder.bindData(artists.get(position), position+1);
    }

    @Override
    public int getItemCount() {
        return artists.size();
    }

   public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtArtistName;
        CardView cardArtist;

        ViewHolder(View itemView) {
            super(itemView);
            cardArtist = itemView.findViewById(R.id.cardArtist);
            txtArtistName = itemView.findViewById(R.id.txtArtistName);
        }

        void bindData(final Artist item, final int position){
            //fill the view elements
            String artistName = position + ". " + item.getName();
            txtArtistName.setText(artistName);
            cardArtist.setOnClickListener(v -> listener.onItemClick(item));
        }
    }
}
