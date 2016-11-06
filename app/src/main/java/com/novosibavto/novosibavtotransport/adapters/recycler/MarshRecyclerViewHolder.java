package com.novosibavto.novosibavtotransport.adapters.recycler;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.novosibavto.novosibavtotransport.R;
import com.novosibavto.novosibavtotransport.models.MarshData;

import butterknife.BindView;
import butterknife.ButterKnife;

@SuppressWarnings("WeakerAccess")
public class MarshRecyclerViewHolder extends RecyclerView.ViewHolder {

	@BindView(R.id.item_marsh_text_view_name_marsh)
	TextView mNameMarshTextView;

	@BindView(R.id.item_marsh_text_view_num_marsh)
	TextView mNumMarshTextView;

	public MarshRecyclerViewHolder(View itemView) {
		super(itemView);
		ButterKnife.bind(this, itemView);
	}

	public void bind(MarshData model) {
		mNumMarshTextView.setText(model.getTitle());

		String fullNameMarsh = model.getNameBegin() + " - " + model.getNameEnd();
		mNameMarshTextView.setText(fullNameMarsh);
	}
}
