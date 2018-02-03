package com.example.gtg.cineaplication.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.gtg.cineaplication.R;
import com.example.gtg.cineaplication.activity.PrincipalActivity;
import com.example.gtg.cineaplication.modelo.Cinema;
import java.util.List;

/**
 * Created by leo_b on 30/01/2018.
 */
public class CinemaAdapter extends RecyclerView.Adapter {
	private List<Cinema> cinemas;
	private Context context;
	private Drawable[] cartazes;

	public CinemaAdapter(Context ctx, List<Cinema> cinemas) {
		this.context = ctx;
		this.cinemas = cinemas;

		cartazes = new Drawable[]{getCartaz(R.drawable.cinepolis),
		                          getCartaz(R.drawable.cinesystem),
		                          getCartaz(R.drawable.kinoplex)};
	}

	private Drawable getCartaz(final int drw) {
		if (VERSION.SDK_INT >= VERSION_CODES.LOLLIPOP)
			return context.getDrawable(drw);

		return ContextCompat.getDrawable(context, drw);
	}

	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View layoutitemrecycler = LayoutInflater.from(context).inflate(R.layout.content_filme, null);
		ViewHolderCinema vhc = new ViewHolderCinema(layoutitemrecycler);

		return (vhc);
	}

	@Override
	public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
		Cinema cine = cinemas.get(position);

		ViewHolderCinema vhc = (ViewHolderCinema) holder;

		vhc.imageView.setImageDrawable(cartazes[position]);
		vhc.lblCinema.setText(cine.getNome());

		vhc.imageView.setOnClickListener(new CinemaOnClickListener(context, this.cinemas, position));
	}

	@Override
	public int getItemCount() {
		return cinemas.size();
	}

	private class CinemaOnClickListener implements View.OnClickListener {
		private List<Cinema> cinemas;
		private int position;

		CinemaOnClickListener(Context ctx, List<Cinema> cinemas, int position) {
			this.cinemas = cinemas;
			this.position = position;
		}

		@Override
		public void onClick(View view) {
			Intent intentCarregarCinema = new Intent(context, PrincipalActivity.class);
			intentCarregarCinema.putExtra("pos", position);
			context.startActivity(intentCarregarCinema);
		}
	}
}