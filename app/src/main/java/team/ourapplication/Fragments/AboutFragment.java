package team.ourapplication.Fragments;


import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import team.ourapplication.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AboutFragment extends Fragment {

    TextView text_about;
    TextView toolbar_title;

    public AboutFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_about, container, false);
        text_about = view.findViewById(R.id.text_about);
        toolbar_title=view.findViewById(R.id.toolbar_title);
        text_about.setTypeface(Typeface.createFromAsset(getActivity().getAssets(), "BoutrosJazirahTextLight.ttf"));
        toolbar_title.setTypeface(Typeface.createFromAsset(getActivity().getAssets(), "BoutrosJazirahTextLight.ttf"));

        return view;
    }


}
