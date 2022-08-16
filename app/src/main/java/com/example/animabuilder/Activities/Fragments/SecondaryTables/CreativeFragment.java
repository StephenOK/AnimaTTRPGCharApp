package com.example.animabuilder.Activities.Fragments.SecondaryTables;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.animabuilder.R;

public class CreativeFragment extends Fragment {
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.creative_fragment, container, false);

        return view;
    }
}
