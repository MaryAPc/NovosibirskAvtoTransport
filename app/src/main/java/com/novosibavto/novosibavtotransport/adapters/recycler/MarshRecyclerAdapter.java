package com.novosibavto.novosibavtotransport.adapters.recycler;

import java.util.ArrayList;
import java.util.List;

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

	@Override
	public MarshRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_marsh, parent, false);
		mViewHolder = new MarshRecyclerViewHolder(view);
		return mViewHolder;
	}

	@Override
	public void onBindViewHolder(MarshRecyclerViewHolder holder, int position) {
		holder.bind(mMarshList.get(position));
	}

	@Override
	public int getItemCount() {
		return mMarshList.size();
	}

	public void setCollection(List<MarshData> marshData) {
		mMarshList = marshData;
		notifyDataSetChanged();
	}
}