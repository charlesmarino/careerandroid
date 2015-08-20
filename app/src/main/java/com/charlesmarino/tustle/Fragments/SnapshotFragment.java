package com.charlesmarino.tustle.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.charlesmarino.tustle.R;

/**
 * Fragment for snapshots
 * Created by kirstiebooras on 8/18/15.
 */
public class SnapshotFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.activity_main, container, false);

    }
}
