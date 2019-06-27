package team.ourapplication.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import team.ourapplication.Adapters.HomeAdapter;
import team.ourapplication.ClassHelper.HomeCell;
import team.ourapplication.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
RecyclerView recyclerView;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
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
