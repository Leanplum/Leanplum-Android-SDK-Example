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
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.leanplum.Leanplum;
import com.leanplum.android.example.R;

public class PushNotificationsFragment extends Fragment {
  public PushNotificationsFragment() {

  }

  public static PushNotificationsFragment newInstance() {
    return new PushNotificationsFragment();
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View root = inflater.inflate(R.layout.show_example_layout, container, false);

    Button example = (Button) root.findViewById(R.id.example_button);
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
