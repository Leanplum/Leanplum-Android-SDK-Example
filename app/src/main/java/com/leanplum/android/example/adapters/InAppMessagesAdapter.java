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
package com.leanplum.android.example.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.leanplum.android.example.R;
import com.leanplum.android.example.fragments.BaseFragment;
import com.leanplum.android.example.fragments.InAppMessagesFragment;

import java.util.List;

public class InAppMessagesAdapter extends RecyclerView.Adapter<InAppMessagesAdapter
    .InAppMessagesViewHolder> {

  private final List<InAppMessagesFragment.InAppMessageData> mValues;
  private final BaseFragment.OnListItemInteraction<InAppMessagesFragment.InAppMessageData> mListener;

  public InAppMessagesAdapter(List<InAppMessagesFragment.InAppMessageData> items,
      BaseFragment.OnListItemInteraction<InAppMessagesFragment.InAppMessageData> listener) {
    mValues = items;
    mListener = listener;
  }

  @Override
  public InAppMessagesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.generic_list_item, parent, false);
    return new InAppMessagesViewHolder(view);
  }

  @Override
  public void onBindViewHolder(final InAppMessagesViewHolder holder, int position) {
    InAppMessagesFragment.InAppMessageData data = mValues.get(position);
    holder.mItem = data;
    holder.mContentView.setText(data.feature.description);

    holder.mView.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        if (null != mListener) {
          mListener.onListFragmentInteraction(holder.mItem);
        }
      }
    });
  }

  @Override
  public int getItemCount() {
    return mValues.size();
  }

  class InAppMessagesViewHolder extends RecyclerView.ViewHolder {
    final View mView;
    final TextView mContentView;
    InAppMessagesFragment.InAppMessageData mItem;

    InAppMessagesViewHolder(View view) {
      super(view);
      mView = view;
      mContentView = (TextView) view.findViewById(R.id.content);
    }
  }
}
