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

public class TestingInAppFragment extends Fragment {
  private static final String TAG = TestingInAppFragment.class.getSimpleName();

  public TestingInAppFragment() {
  }

  public static TestingInAppFragment newInstance() {
    return new TestingInAppFragment();
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View root = inflater.inflate(R.layout.testing_inapp, container, false);
    initButtons(root);

    return root;
  }

  private void initButtons(View root) {
    // IN APP TEMPLATES
    final Button alert = root.findViewById(R.id.alert);
    alert.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Leanplum.track("alert");
      }
    });

    final Button center_popup = root.findViewById(R.id.center_popup);
    center_popup.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Leanplum.track("center_popup");
      }
    });

    final Button interstitial = root.findViewById(R.id.interstitial);
    interstitial.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Leanplum.track("interstitial");
      }
    });

    final Button push_pre_permission = root.findViewById(R.id.push_pre_permission);
    push_pre_permission.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Leanplum.track("push_pre_permission");
      }
    });

    final Button web_interstitial = root.findViewById(R.id.web_interstitial);
    web_interstitial.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Leanplum.track("web_interstitial");
      }
    });

    // ACTIONS
    final Button open_url = root.findViewById(R.id.open_url);
    open_url.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Leanplum.track("open_url");
      }
    });

    final Button register_for_push = root.findViewById(R.id.register_for_push);
    register_for_push.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Leanplum.track("register_for_push");
      }
    });

    // RICH IN-APP TEMPLATES
    final Button banner = root.findViewById(R.id.banner);
    banner.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Leanplum.track("banner");
      }
    });

    final Button rich_interstitial = root.findViewById(R.id.rich_interstitial);
    rich_interstitial.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Leanplum.track("rich_interstitial");
      }
    });

    final Button star_rating = root.findViewById(R.id.star_rating);
    star_rating.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Leanplum.track("star_rating");
      }
    });

    // CHAINED IN-APP MESSAGES
    final Button chained_message = root.findViewById(R.id.chained_message);
    chained_message.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Leanplum.track("chained_message");
      }
    });

    // WHEN LIMITS
    final Button limit_session = root.findViewById(R.id.limit_session);
    limit_session.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Leanplum.track("limit_session");
      }
    });

    final Button limit_life = root.findViewById(R.id.limit_lifetime);
    limit_life.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Leanplum.track("limit_lifetime");
      }
    });
  }
}
