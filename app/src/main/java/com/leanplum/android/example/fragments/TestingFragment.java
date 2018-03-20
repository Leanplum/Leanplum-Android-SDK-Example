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
    View root = inflater.inflate(R.layout.testing_layout, container, false);

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
    final Button push_button = root.findViewById(R.id.send_push);
    push_button.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Leanplum.track("Send_push");
      }
    });

    final Button push_button_confirm = root.findViewById(R.id.send_push_confirm);
    push_button_confirm.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Leanplum.track("Send_push_confirm");
      }
    });

    final Button push_button_mute_confirm = root.findViewById(R.id.send_push_mute_confirm);
    push_button_mute_confirm.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Leanplum.track("Send_push_mute_confirm");
      }
    });

    final Button push_button_mute = root.findViewById(R.id.send_push_mute);
    push_button_mute.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View v) {
                                            Leanplum.track("Send_push_mute");
                                          }
                                        }
    );

    final Button push_button_alert = root.findViewById(R.id.send_push_alert);
    push_button_alert.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Leanplum.track("Send_push_alert");
      }
    });

    final Button push_button_mute_alert = root.findViewById(R.id.send_push_mute_alert);
    push_button_mute_alert.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Leanplum.track("Send_push_mute_alert");
      }
    });


    final Button push_button_center = root.findViewById(R.id.send_push_center);
    push_button_center.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Leanplum.track("Send_push_center");
      }
    });

    final Button push_button_mute_center = root.findViewById(R.id.send_push_mute_center);
    push_button_mute_center.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Leanplum.track("Send_push_mute_center");
      }
    });


    final Button push_button_interstitial = root.findViewById(R.id.send_push_interstitial);
    push_button_interstitial.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Leanplum.track("Send_push_interstitial");
      }
    });

    final Button push_button_mute_interstitial = root.findViewById(R.id.send_push_mute_interstitial);
    push_button_mute_interstitial.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Leanplum.track("Send_push_mute_interstitial");
      }
    });

    final Button push_triggered = root.findViewById(R.id.send_push_triggered);
    push_triggered.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Leanplum.track("Send_push_triggered");
      }
    });

    final Button push_triggered_cancel = root.findViewById(R.id.cancel_push_triggered);
    push_triggered_cancel.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Leanplum.track("Cancel_push_triggered");
      }
    });
    final Button alert_limitSession_2 = root.findViewById(R.id.alert_limitSession_2);
    alert_limitSession_2.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Leanplum.track("Alert_limitSession_2");
      }
    });
    final Button interstitial_limitSession_2 = root.findViewById(R.id.interstitial_limitSession_2);
    interstitial_limitSession_2.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Leanplum.track("Interstitial_limitSession_2");
      }
    });

    final Button center_limitSession_2 = root.findViewById(R.id.center_limitSession_2);
    center_limitSession_2.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Leanplum.track("Center_limitSession_2");
      }
    });
    return root;
  }
}
