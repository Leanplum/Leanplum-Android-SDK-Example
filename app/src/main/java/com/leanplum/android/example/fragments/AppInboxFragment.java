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
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.leanplum.Leanplum;
import com.leanplum.LeanplumInboxMessage;
import com.leanplum.android.example.adapters.AppInboxMessagesAdapter;
import com.leanplum.callbacks.InboxChangedCallback;
import com.leanplum.android.example.R;

import java.util.ArrayList;
import java.util.List;

public class AppInboxFragment extends BaseFragment {

  private final OnListItemInteraction<AppInboxMessageData> mListener = new
      OnListItemInteraction<AppInboxMessageData>() {
        @Override
        public void onListFragmentInteraction(AppInboxMessageData item) {
          AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
          builder.setMessage(item.details);
          builder.setTitle(item.title);
          builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
              dialog.dismiss();
            }
          });
          builder.create().show();
        }
      };

  private final ArrayList<AppInboxMessageData> mListItems = new ArrayList<>();

  public static AppInboxFragment newInstance() {
    return new AppInboxFragment();
  }

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Leanplum.getInbox().addChangedHandler(new InboxChangedCallback() {
      @Override
      public void inboxChanged() {
        prepareMessages();
      }
    });

    prepareMessages();
  }

  private void prepareMessages() {
    mListItems.clear();
    List messagesList = Leanplum.getInbox().allMessages();
    List<LeanplumInboxMessage> messages = (List<LeanplumInboxMessage>)messagesList;
    for (LeanplumInboxMessage message : messages) {
      AppInboxMessageData data = new AppInboxMessageData(message.getTitle(), message.getSubtitle());
      mListItems.add(data);
    }
  }

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
      Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.generic_list_fragment, container, false);

    // Set the adapter
    if (view instanceof RecyclerView) {
      Context context = view.getContext();
      RecyclerView recyclerView = (RecyclerView) view;
      recyclerView.setLayoutManager(new LinearLayoutManager(context));
      recyclerView.setAdapter(new AppInboxMessagesAdapter(mListItems, mListener));
    }
    return view;
  }

  public static class AppInboxMessageData {
    public final String title;
    public final String details;

    public AppInboxMessageData(String title, String details) {
      this.title = title;
      this.details = details;
    }
  }
}
