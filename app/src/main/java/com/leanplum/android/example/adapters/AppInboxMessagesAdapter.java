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
import com.leanplum.android.example.fragments.AppInboxFragment;
import com.leanplum.android.example.fragments.BaseFragment;

import java.util.List;

public class AppInboxMessagesAdapter extends RecyclerView.Adapter<AppInboxMessagesAdapter
    .AppInboxMessagesViewHolder> {

  private final List<AppInboxFragment.AppInboxMessageData> mValues;
  private final BaseFragment.OnListItemInteraction<AppInboxFragment.AppInboxMessageData> mListener;

  public AppInboxMessagesAdapter(List<AppInboxFragment.AppInboxMessageData> items, BaseFragment.OnListItemInteraction<AppInboxFragment.AppInboxMessageData> listener) {
    mValues = items;
    mListener = listener;
  }

  @Override
  public AppInboxMessagesAdapter.AppInboxMessagesViewHolder onCreateViewHolder(ViewGroup parent, int
      viewType) {
    View view = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.generic_detail_list_item, parent, false);
    return new AppInboxMessagesAdapter.AppInboxMessagesViewHolder(view);
  }

  @Override
  public void onBindViewHolder(final AppInboxMessagesAdapter.AppInboxMessagesViewHolder holder, int
      position) {
    holder.mItem = mValues.get(position);
    holder.mTitleView.setText(mValues.get(position).title);
    holder.mDetailsView.setText(mValues.get(position).details);

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

  class AppInboxMessagesViewHolder extends RecyclerView.ViewHolder {
    final View mView;
    final TextView mTitleView;
    final TextView mDetailsView;
    AppInboxFragment.AppInboxMessageData mItem;

    AppInboxMessagesViewHolder(View view) {
      super(view);
      mView = view;
      mTitleView = (TextView) view.findViewById(R.id.content);
      mDetailsView = (TextView) view.findViewById(R.id.details);
    }
  }
}
