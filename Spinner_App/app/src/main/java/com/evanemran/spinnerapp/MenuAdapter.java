package com.evanemran.spinnerapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class MenuAdapter extends RecyclerView.Adapter<MenuViewHolder> {

    Context context;
    List<Menu> list;

    //listener added for Assignment 3
    ClickListener listener;

    public MenuAdapter(Context context, List<Menu> list, ClickListener listener) {
        this.context = context;
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MenuViewHolder(LayoutInflater.from(context).inflate(R.layout.list_menu, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MenuViewHolder holder, int position) {
        holder.textView_menu.setText(list.get(position).name);
        Picasso.get().load(list.get(position).image).into(holder.imageView_menu);

        holder.menu_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onClicked(list.get(holder.getAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

class MenuViewHolder extends RecyclerView.ViewHolder {

    ImageView imageView_menu;
    TextView textView_menu;
    CardView menu_container;

    public MenuViewHolder(@NonNull View itemView) {
        super(itemView);

        imageView_menu = itemView.findViewById(R.id.imageView_menu);
        textView_menu = itemView.findViewById(R.id.textView_menu);
        menu_container = itemView.findViewById(R.id.menu_container);
    }
}
