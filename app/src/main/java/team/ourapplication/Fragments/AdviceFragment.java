package team.ourapplication.Fragments;


import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import team.ourapplication.Adapters.LocationAndAdviceAdapter;
import team.ourapplication.ClassHelper.LocationAndAdviceCell;
import team.ourapplication.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AdviceFragment extends Fragment {
    RecyclerView recyclerView;
    TextView toolbar_title;


    public AdviceFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_advice, container, false);
        recyclerView = view.findViewById(R.id.recycler_advice);
        toolbar_title=view.findViewById(R.id.toolbar_title);
        toolbar_title.setTypeface(Typeface.createFromAsset(getActivity().getAssets(), "BoutrosJazirahTextLight.ttf"));


        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        LocationAndAdviceAdapter a = new LocationAndAdviceAdapter(getActivity(),prepareArrays());
        recyclerView.setAdapter(a);
        return view;
    }
    private ArrayList<LocationAndAdviceCell> prepareArrays()
    {
        ArrayList<LocationAndAdviceCell> m=new ArrayList<>();

        LocationAndAdviceCell p1=new LocationAndAdviceCell();
        p1.setBnTitle("الدفاع المدني");
        p1.setId("advice");
        m.add (p1);

        p1=new LocationAndAdviceCell();
        p1.setBnTitle("الامن العام");
        p1.setId("advice");
        m.add (p1);

        p1=new LocationAndAdviceCell();
        p1.setBnTitle("ادارة السير");
        p1.setId("advice");
        m.add (p1);

        p1 = new LocationAndAdviceCell();
        p1.setBnTitle("الطوارئ");
        p1.setId("advice");
        m.add (p1);

        return m;

    }

}
