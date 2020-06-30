package com.example.mandiricontactapps.view;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.mandiricontactapps.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HeaderFragment extends Fragment {

    @BindView(R.id.iv_headerLeftIcon)
    ImageView ivHeaderLeftIcon;
    @BindView(R.id.tv_headerTitle)
    TextView tvHeaderTitle;
    @BindView(R.id.iv_headerRightIcon)
    ImageView ivHeaderRightIcon;

    public HeaderFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_header, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
