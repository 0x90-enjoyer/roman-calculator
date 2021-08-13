package com.example.daniel.RomanCalculator;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link RomanFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link RomanFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RomanFragment extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";

    private Expression exp;

    private OnFragmentInteractionListener mListener;

    public RomanFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param exp Parameter 1.
     * @return A new instance of fragment RomanFragment.
     */
    public static RomanFragment newInstance(Expression exp) {
        RomanFragment fragment = new RomanFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, exp);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Called to do initial creation of a fragment. This is called after onAttach(Activity) and
     * before onCreateView(LayoutInflater, ViewGroup, Bundle), but is not called if the fragment
     * instance is retained across Activity re-creation.
     *
     * @param savedInstanceState If the fragment is being re-created from a previous saved state,
     *                           this is the state. This value may be null.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            exp = (Expression) getArguments().getSerializable(ARG_PARAM1);
        }
    }

    /**
     * Creates and returns the view hierarchy associated with the fragment.
     *
     * @param inflater The LayoutInflater object that can be used to inflate any views in the
     *                 fragment
     * @param container If non-null, this is the parent view that the fragment's UI should be
     *                  attached to. The fragment should not add the view itself, but this can be
     *                  used to generate the LayoutParams of the view.
     * @param savedInstanceState If non-null, this fragment is being re-constructed from a previous
     *                           saved state as given here.
     * @return View - Return the View for the fragment's UI, or null.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_roman, container, false);

        ArrayList<Button> buttons = new ArrayList<>();

        buttons.add(view.findViewById(R.id.button_I));
        buttons.add(view.findViewById(R.id.button_V));
        buttons.add(view.findViewById(R.id.button_X));
        buttons.add(view.findViewById(R.id.button_L));
        buttons.add(view.findViewById(R.id.button_C));
        buttons.add(view.findViewById(R.id.button_D));
        buttons.add(view.findViewById(R.id.button_M));

        Keypad kp = new Keypad(buttons);
        kp.addObserver(exp);

        return view;
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    /**
     * Called when a fragment is first attached to its context. onCreate(Bundle) will be called
     * after this.
     *
     * @param context the context
     */
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    /**
     * Called when the fragment is no longer attached to its activity. This is called after
     * onDestroy(), except in the cases where the fragment instance is retained across Activity
     * re-creation, in which case it is called after onStop().
     *
     */
    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
