package team.ourapplication.Adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import team.ourapplication.ClassHelper.HomeCell;
import team.ourapplication.ClassHelper.LocationAndAdviceCell;
import team.ourapplication.R;


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


public class LocationAndAdviceAdapter extends RecyclerView.Adapter<LocationAndAdviceAdapter.viewitem> {
    ArrayList<LocationAndAdviceCell> items;
    Context context;
    Typeface myTypeface;


    public LocationAndAdviceAdapter(Context c, ArrayList<LocationAndAdviceCell> item)
    {
        items=item;
        context=c;

    }
    class  viewitem extends RecyclerView.ViewHolder
    {

        //Declare

        Button btn;


        //initialize
        public viewitem(View itemView) {
            super(itemView);
            btn=itemView.findViewById(R.id.location_btn_cell);


        }
    }



    //onCreateViewHolder used to HAndle on Clicks
    @Override
    public LocationAndAdviceAdapter.viewitem onCreateViewHolder(final ViewGroup parent, int viewType) {



        //the itemView now is the row
        //We will add 2 onClicks


        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.location_and_advice_row, parent, false);





        return new LocationAndAdviceAdapter.viewitem(itemView);
    }










    @Override
    public void onBindViewHolder(final LocationAndAdviceAdapter.viewitem holder, final int position) {

        holder.btn.setText(items.get(position).getBtn());

        holder.btn.setOnClickListener(new View.OnClickListener() {
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

