package com.novosibavto.novosibavtotransport.adapters.recycler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.novosibavto.novosibavtotransport.R;
import com.novosibavto.novosibavtotransport.models.MarshData;

@SuppressWarnings({"WeakerAccess", "FieldCanBeLocal"})
public class MarshRecyclerAdapter extends RecyclerView.Adapter<MarshRecyclerViewHolder> {

	private List<MarshData> mMarshList = new ArrayList<>();
	private MarshRecyclerViewHolder mViewHolder;
	private OnItemClickListener mOnItemClickListener;

	public interface OnItemClickListener {
		void onItemClick(View view, int position);
	}

	public MarshRecyclerAdapter(List<MarshData> marshList, OnItemClickListener onItemClickListener) {
		mMarshList = marshList;
		mOnItemClickListener = onItemClickListener;
	}

	@Override
	public MarshRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_marsh, parent, false);
		mViewHolder = new MarshRecyclerViewHolder(view);
		return mViewHolder;
	}

	@Override
	public void onBindViewHolder(MarshRecyclerViewHolder holder, int position) {
		holder.bind(mMarshList.get(position));
		holder.itemView.setOnClickListener(view -> mOnItemClickListener.onItemClick(view, position));
	}

	@Override
	public int getItemCount() {
		return mMarshList.size();
	}

	public void setCollection(@Nullable List<MarshData> marshData) {
		if (marshData == null) {
			mMarshList = Collections.emptyList();
		} else {
			mMarshList = marshData;
		}
		notifyDataSetChanged();
	}
}
