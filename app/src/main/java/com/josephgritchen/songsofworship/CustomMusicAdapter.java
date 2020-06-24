package com.josephgritchen.songsofworship;

import android.annotation.SuppressLint;
import android.content.Context;
import android.media.MediaPlayer;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CustomMusicAdapter extends RecyclerView.Adapter<MyViewHolder> implements Filterable {

    private Context context;
    private int layout;
    private ArrayList<Music> myArraylist;
    private ArrayList<Music> getUserModelListFiltered;
    private static MediaPlayer mediaPlayer;
    private boolean flag=true;


    public CustomMusicAdapter(Context context, int custom_music_row, ArrayList<Music> myArraylist) {
        this.context = context;
        this.layout = layout;
        this.getUserModelListFiltered = myArraylist;
        this.myArraylist = myArraylist;

    }

    public void release(){
        mediaPlayer.stop();
        mediaPlayer.release();
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_music_row, parent, false);
        return new MyViewHolder(itemView);
    }



    @SuppressLint("RecyclerView")
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        holder.setIsRecyclable(false);

        final Music music = myArraylist.get(position);
        holder.mTitle.setText(music.getName());
        holder.mDes.setText(music.getSinger());

        //Play Music Setup
        holder.mImageaplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (flag){
                    mediaPlayer=MediaPlayer.create(context,music.getSong());
                    flag=false;
                }
                if (mediaPlayer.isPlaying()){
                    mediaPlayer.pause();
                    mediaPlayer.start();
                    holder.mImageaplay.setImageResource(R.drawable.ic_play);
                }else {
                    mediaPlayer.start();
                    holder. mImageaplay.setImageResource(R.drawable.ic_pause);
                }
                mediaPlayer.start();

            }
        });

        // stop player
        holder.mImageStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!flag){
                    mediaPlayer.stop();
                    mediaPlayer.release();
                    flag=true;
                }
                holder.mImageaplay.setImageResource(R.drawable.ic_play);
            }
        });

    }

    @Override
    public int getItemCount() {
        return myArraylist.size();
    }

    @Override
    public Filter getFilter() {

        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                FilterResults filterResults = new FilterResults();
                if (charSequence == null | charSequence.length() == 0){
                    filterResults.count = getUserModelListFiltered.size();
                    filterResults.values = getUserModelListFiltered;
                }else {
                    String searchChr = charSequence.toString().toLowerCase();
                    List<Music> resultData = new ArrayList<>();
                    for (Music music: getUserModelListFiltered){
                        if (music.getName().toLowerCase().contains(searchChr)){
                            resultData.add(music);
                        }
                    }
                    filterResults.count = resultData.size();
                    filterResults.values = resultData;
                }
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {

                myArraylist = (ArrayList<Music>) filterResults.values;
                notifyDataSetChanged();

            }
        };
        return filter;
    }

    public class ViewHolder {
        TextView textView_SongName, textView_artist;
        ImageView imageView_play, imageView_stop;
        private View convertview;
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);

        public ViewHolder() {
            convertview = layoutInflater.inflate(layout, null);
            textView_SongName = convertview.findViewById(R.id.textName);
            textView_artist = convertview.findViewById(R.id.txtSinger);
            imageView_play = convertview.findViewById(R.id.ivPlay);
            imageView_stop = convertview.findViewById(R.id.ivStop);

        }
    }
}