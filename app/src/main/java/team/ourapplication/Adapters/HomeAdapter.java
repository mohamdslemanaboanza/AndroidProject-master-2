package team.ourapplication.Adapters;


import android.app.Activity;
import android.content.Context;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.ArrayList;

import team.ourapplication.ClassHelper.HomeCell;
import team.ourapplication.R;


public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.viewitem> {
    ArrayList<HomeCell> items;
    Context context;
    Typeface myTypeface;


    public HomeAdapter(Context c, ArrayList<HomeCell> item)
    {
        items=item;
        context=c;

    }
    class  viewitem extends RecyclerView.ViewHolder
    {

        //Declare

        ImageView img;
        TextView title;

        //initialize
        public viewitem(View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.homee);
            title=itemView.findViewById(R.id.title);

        }
    }



    //onCreateViewHolder used to HAndle on Clicks
    @Override
    public HomeAdapter.viewitem onCreateViewHolder(final ViewGroup parent, int viewType) {



        //the itemView now is the row
        //We will add 2 onClicks


        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.home_row, parent, false);





        return new HomeAdapter.viewitem(itemView);
    }










    @Override
    public void onBindViewHolder(final HomeAdapter.viewitem holder, final int position) {

        holder.img.setImageResource(items.get(position).getImge());
        holder.title.setText(items.get(position).getTitle());
        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });



    }




    @Override
    public int getItemCount() {
        return items.size();
    }




}

