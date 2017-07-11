//  Copyright © 2017 Leanplum.
//
//  Licensed under the Apache License, Version 2.0 (the "License");
//  you may not use this file except in compliance with the License.
//  You may obtain a copy of the License at
//      http://www.apache.org/licenses/LICENSE-2.0
//  Unless required by applicable law or agreed to in writing, software
//  distributed under the License is distributed on an "AS IS" BASIS,
//  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
//  See the License for the specific language governing permissions and
//  limitations under the License.
//
package com.leanplum.android.example.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.leanplum.Leanplum;
import com.leanplum.android.example.R;

public class ExampleFragment extends BaseFragment {
  private static final String BUNDLE_KEY_FEATURE = "feature";

  private InAppMessagesFragment.Features mFeature;

  public static ExampleFragment newInstance(InAppMessagesFragment.Features feature) {
    Bundle args = new Bundle();
    args.putSerializable(BUNDLE_KEY_FEATURE, feature);

    ExampleFragment fragment = new ExampleFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (getArguments() != null) {
      mFeature = (InAppMessagesFragment.Features) getArguments()
          .getSerializable(BUNDLE_KEY_FEATURE);
    }
  }

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
      Bundle savedInstanceState) {
    View root = inflater.inflate(R.layout.show_example_layout, container, false);

    getActivity().setTitle(mFeature.description);

    Button example = (Button) root.findViewById(R.id.track_event);
    example.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        switch (mFeature) {
          case ALERT:
            Leanplum.track("alert_example");
            break;
          case CENTER_POPUP:
            Leanplum.track("center_popup_example");
            break;
          case CONFIRM:
            Leanplum.track("confirm_example");
            break;
          case INTERSTITIAL:
            Leanplum.track("interstitial_example");
            break;
          case WEB_INTERSTITIAL:
            Leanplum.track("web_interstitial_example");
            break;
          case OPEN_URL:
            Leanplum.track("open_url_example");
            break;
        }
      }
    });
    return root;
  }
}
