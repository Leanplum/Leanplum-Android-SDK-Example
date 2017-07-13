package com.leanplum.android.example.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.leanplum.Leanplum;
import com.leanplum.android.example.R;

/**
 * Created by sayaan on 7/10/17.
 */

public class TestingFragment extends Fragment {
    public TestingFragment() {
    }

    public static TestingFragment newInstance() {
        return new TestingFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                         Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.testing_layout, container, false);

        Button example = (Button) root.findViewById(R.id.track_event);
        example.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Leanplum.track("Example");
            }
        });

        Button loadTest = (Button) root.findViewById(R.id.load_test);
        loadTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < 10002; i++) {
                    Leanplum.track("load_test_iteration" + i);
                }
            }
        });

        Button crashApp = (Button) root.findViewById(R.id.crash_app);
        crashApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                throw new RuntimeException("This is a crash");
            }

        });

        return root;
    }
}
