package com.example.gtg.cineaplication.adapter;

import android.support.v7.view.menu.MenuView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.gtg.cineaplication.R;

/**
 * Created by leo_b on 30/01/2018.
 */

public class ViewHolderCinema extends RecyclerView.ViewHolder{
    final TextView lblCinema;
    final ImageView imageView;

    public ViewHolderCinema(View itemView){
        super(itemView);
        lblCinema = itemView.findViewById(R.id.lblCinema);
        imageView = itemView.findViewById(R.id.imageView);
    }
}
