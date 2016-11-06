package com.novosibavto.novosibavtotransport.adapters.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Date: 20-Oct-15
 * Time: 11:24
 *
 * @author esorokin
 */
@SuppressWarnings("WeakerAccess")
public abstract class SimpleViewHolder<ModelType> {
	public View rootView;

	protected SimpleViewHolder(final LayoutInflater inflater, ViewGroup parent) {
		rootView = create(inflater, parent);
	}

	final public View getRoot() {
		return rootView;
	}

	abstract protected View create(final LayoutInflater inflater, ViewGroup parent);

	abstract public void bind(ModelType model);

	protected Context context() {
		return rootView.getContext();
	}
}