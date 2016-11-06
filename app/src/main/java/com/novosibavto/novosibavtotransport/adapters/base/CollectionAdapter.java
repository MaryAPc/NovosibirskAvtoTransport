package com.novosibavto.novosibavtotransport.adapters.base;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.BaseAdapter;

/**
 * Date: 22-Nov-15
 * Time: 17:14
 *
 * @author esorokin
 */
@SuppressWarnings({"unused", "WeakerAccess"})
public abstract class CollectionAdapter<T> extends BaseAdapter {
	protected LayoutInflater mInflater;
	private Context mContext;
	private final List<T> mList = new ArrayList<>();

	public CollectionAdapter(Context context) {
		mContext = context;
		mInflater = LayoutInflater.from(context);
	}

	public CollectionAdapter(Context context, Collection<? extends T> collection) {
		this(context);

		if (collection != null) {
			mList.addAll(collection);
		}
	}

	@Override
	public int getCount() {
		return mList.size();
	}

	@Override
	public T getItem(int position) {
		return mList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	public void setCollection(Collection<? extends T> collection) {
		mList.clear();

		if (collection != null) {
			mList.addAll(collection);
		}

		notifyDataSetChanged();
	}

	public void clearCollection() {
		mList.clear();
		notifyDataSetChanged();
	}

	public void removeItem(T item) {
		mList.remove(item);
		notifyDataSetChanged();
	}

	public List<T> getCollection() {
		return mList;
	}

	protected Context getContext() {
		return mContext;
	}
}
