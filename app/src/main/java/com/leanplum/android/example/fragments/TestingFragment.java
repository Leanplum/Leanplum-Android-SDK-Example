package com.leanplum.android.example.fragments;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.leanplum.Leanplum;
import com.leanplum.android.example.R;


public class TestingFragment extends Fragment {
  private static final String TAG = TestingFragment.class.getSimpleName();

  public TestingFragment() {
  }

  public static TestingFragment newInstance() {
    return new TestingFragment();
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View root = inflater.inflate(R.layout.testing, container, false);
    initButtons(root);

    return root;
  }

  private void initButtons(View root) {
    final Button loadTestButton = root.findViewById(R.id.load_test);
    loadTestButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        loadTestButton.setText(R.string.load_testing);
        loadTestButton.setEnabled(false);
        new Thread(new Runnable() {
          @Override
          public void run() {
            try {
              for (int i = 0; i < 10002; i++) {
                Leanplum.track("load_test_iteration" + i);
              }
            } catch (Throwable t) {
              Log.e(TAG, "Cannot insert 10002 events: " + t.toString());
            }
            Handler uiHandler = new Handler(Looper.getMainLooper());
            Runnable myRunnable = new Runnable() {
              @Override
              public void run() {
                loadTestButton.setText(R.string.load_test);
                loadTestButton.setEnabled(true);
              }
            };
            uiHandler.post(myRunnable);
          }
        }).start();
      }
    });

    Button crashApp = root.findViewById(R.id.crash_app);
    crashApp.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        throw new RuntimeException("This is a crash");
      }
    });
  }
}
