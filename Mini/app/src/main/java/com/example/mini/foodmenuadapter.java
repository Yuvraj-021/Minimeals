package com.example.mini;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mini.Model.Menus;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class foodmenuadapter extends RecyclerView.Adapter<foodmenuadapter.ViewHolder> {
    public Context context;
    ArrayList<Menus> listData;
    TextView msnm;
    String msname;

    public foodmenuadapter(Context context, ArrayList<Menus> listData) {
        this.listData = listData;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.foodmenuitems,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Menus menus=listData.get(position);
        holder.txtname.setText(menus.getFoodName());
        holder.txtprice.setText(menus.getPrice().toString());
        holder.txtdec.setText(menus.getDescription());
        msname=menus.getMessName();
        Picasso.with(context)
                .load(menus.getImageURL())
                .fit()
                .centerCrop()
                .into(holder.imageview1);
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView txtdec,txtname,txtprice;
        private ImageView imageview1;
        private Button b1;
        public ViewHolder(View itemView) {
            super(itemView);
            imageview1=(ImageView) itemView.findViewById(R.id.img);
            txtname=(TextView)itemView.findViewById(R.id.food);
            txtprice=(TextView)itemView.findViewById(R.id.price);
            txtdec=(TextView)itemView.findViewById(R.id.description);
            b1=(Button) itemView.findViewById(R.id.add);
            msnm=itemView.findViewById(R.id.messnme);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String a,b,c,d;
                a=txtname.getText().toString();
                b=txtprice.getText().toString();
                c=msname;
                d=txtdec.getText().toString();
                Intent intent = new Intent(context, recieptpg.class);
                intent.putExtra("foodname",a);
                intent.putExtra("price",b);
                intent.putExtra("messname",c);
                intent.putExtra("description",d);
                context.startActivity(intent);
            }
        });
        }
    }
}
