package com.example.blocdenotasPrueba.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.blocdenotasPrueba.NuevaNotaDialogViewModel;
import com.example.blocdenotasPrueba.db.Entinty.NotaEntinty;
import com.example.blocdenotasPrueba.R;

import java.util.List;


public class MyNotaRecyclerViewAdapter extends RecyclerView.Adapter<MyNotaRecyclerViewAdapter.ViewHolder> {

    private List<NotaEntinty> mValues;
    private Context ctx;
    private NuevaNotaDialogViewModel viewModel;

    public MyNotaRecyclerViewAdapter(List<NotaEntinty> items, Context ctx) {
        mValues = items;
        this.ctx = ctx;
        viewModel = new ViewModelProvider((AppCompatActivity)ctx).get(NuevaNotaDialogViewModel.class);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.fragment_item, parent,false);
            return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.textViewTitulo.setText(holder.mItem.getTitulo());
        holder.textViewContenido.setText(holder.mItem.getContenido());

        if(holder.mItem.isFavorita()) {
            holder.ivFavorita.setImageResource(R.drawable.ic_baseline_star_rate_24);
        }

        holder.ivFavorita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(holder.mItem.isFavorita()) {
                    holder.mItem.setFavorita(false);
                    holder.ivFavorita.setImageResource(R.drawable.ic_baseline_star_border_24);
                } else {
                    holder.mItem.setFavorita(true);
                    holder.ivFavorita.setImageResource(R.drawable.ic_baseline_star_rate_24);
                }

                viewModel.updateNota(holder.mItem);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public void setNuevasNotas(List<NotaEntinty> nuevasNotas){
        this.mValues = nuevasNotas;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView textViewTitulo;
        public final TextView textViewContenido;
        public final ImageView ivFavorita;
        public NotaEntinty mItem;

        public ViewHolder(View view) {
            super(view);
            textViewTitulo = view.findViewById(R.id.textViewTitulo);
            textViewContenido = view.findViewById(R.id.textView3Contenido);
            ivFavorita = view.findViewById(R.id.imageViewFavorita);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + textViewContenido.getText() + "'";
        }
    }
}