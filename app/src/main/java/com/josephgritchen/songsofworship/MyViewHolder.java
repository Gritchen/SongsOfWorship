package com.josephgritchen.songsofworship;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

class MyViewHolder extends RecyclerView.ViewHolder {
    ImageView mImageaplay, mImageStop;
    TextView mTitle, mDes;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);

        this.mImageaplay = itemView.findViewById(R.id.ivPlay);
        this.mImageStop = itemView.findViewById(R.id.ivStop);
        this.mTitle = itemView.findViewById(R.id.textName);
        this.mDes = itemView.findViewById(R.id.txtSinger);
    }

}
