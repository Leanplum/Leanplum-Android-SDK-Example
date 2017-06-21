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

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.leanplum.android.example.R;
import com.leanplum.android.example.adapters.InAppMessagesAdapter;

import java.util.ArrayList;

public class InAppMessagesFragment extends BaseFragment {
  private OnListItemInteraction<InAppMessageData> mListener = new
      OnListItemInteraction<InAppMessageData>() {

        @Override
        public void onListFragmentInteraction(InAppMessageData item) {
          Fragment fragment = ExampleFragment.newInstance(item.feature);
          FragmentTransaction ft = getFragmentManager().beginTransaction();
          ft.replace(R.id.content_main, fragment, null);
          ft.commit();
          ft.addToBackStack(null);
        }
      };
  private final ArrayList<InAppMessageData> mListItems = new ArrayList<>();

  public InAppMessagesFragment() {
  }

  public static InAppMessagesFragment newInstance() {
    return new InAppMessagesFragment();
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    mListItems.add(new InAppMessageData(Features.ALERT));
    mListItems.add(new InAppMessageData(Features.CENTER_POPUP));
    mListItems.add(new InAppMessageData(Features.CONFIRM));
    mListItems.add(new InAppMessageData(Features.INTERSTITIAL));
    mListItems.add(new InAppMessageData(Features.WEB_INTERSTITIAL));

    mListItems.add(new InAppMessageData(Features.OPEN_URL));
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.generic_list_fragment, container, false);

    getActivity().setTitle("In-app Messages");

    // Set the adapter
    if (view instanceof RecyclerView) {
      Context context = view.getContext();
      RecyclerView recyclerView = (RecyclerView) view;
      recyclerView.setLayoutManager(new LinearLayoutManager(context));
      recyclerView.setAdapter(new InAppMessagesAdapter(mListItems, mListener));
    }
    return view;
  }

  @Override
  public void onDetach() {
    super.onDetach();
    mListener = null;
  }

  public enum Features {
    ALERT("Alert"),
    CENTER_POPUP("Center Popup"),
    CONFIRM("Confirm"),
    INTERSTITIAL("Interstitial"),
    WEB_INTERSTITIAL("Web Interstitial"),
    OPEN_URL("Open URL");

    public final String description;

    Features(String description) {
      this.description = description;
    }
  }

  public static class InAppMessageData {
    public final Features feature;

    public InAppMessageData(Features content) {
      this.feature = content;
    }

  }
}
