package team.ourapplication.Fragments;


import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import team.ourapplication.Activities.LoginActivity;
import team.ourapplication.Adapters.HomeAdapter;
import team.ourapplication.ClassHelper.HomeCell;
import team.ourapplication.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
RecyclerView recyclerView;

ImageView ExitFromApp;

TextView toolbar_title;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        toolbar_title=view.findViewById(R.id.toolbar_title);
        toolbar_title.setTypeface(Typeface.createFromAsset(getActivity().getAssets(), "BoutrosJazirahTextLight.ttf"));

        ExitFromApp=view.findViewById(R.id.Exit);
        ExitFromApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(getActivity())
                        .setTitle(R.string.logOut_Title)
                        .setMessage(R.string.logOut)
                        .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                SharedPreferences daftar = PreferenceManager.getDefaultSharedPreferences(getActivity());
                                SharedPreferences.Editor pen = daftar.edit();
                                pen.clear();
                                pen.apply();

                                Intent i = new Intent(getActivity(), LoginActivity.class);
                                startActivity(i);
                                getActivity().finish();


                            }
                        }).setNegativeButton(R.string.no, null).show();

            }
        });

        recyclerView = view.findViewById(R.id.recycler_home);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),2));
        HomeAdapter a = new HomeAdapter(getActivity(),prepareArrays());
        recyclerView.setAdapter(a);
        return view;
    }
    private ArrayList<HomeCell> prepareArrays()
    {
        ArrayList<HomeCell> m=new ArrayList<>();

        HomeCell p1=new HomeCell();
        p1.setImge(R.mipmap.police);
        p1.setTitle(R.string.police);
        m.add (p1);

        p1=new HomeCell();
        p1.setImge(R.mipmap.fire);
        p1.setTitle(R.string.fireMan);
        m.add (p1);

        p1=new HomeCell();
        p1.setImge(R.mipmap.ambalance);
        p1.setTitle(R.string.ambalance);
        m.add (p1);

        p1 = new HomeCell();
        p1.setImge(R.mipmap.traffic);
        p1.setTitle(R.string.traffic);
        m.add(p1);

        return m;

    }



}
