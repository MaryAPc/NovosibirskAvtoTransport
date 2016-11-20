package com.novosibavto.novosibavtotransport.models;

import com.google.gson.annotations.SerializedName;

public class MarshData {
	@SerializedName("id")
	private String mId;
	@SerializedName("type_transport")
	private String mTypeTransport;
	@SerializedName("title")
	private String mTitle;
	@SerializedName("name_begin")
	private String mNameBegin;
	@SerializedName("name_end")
	private String mNameEnd;

	private boolean mChecked;

	public boolean isChecked() {
		return mChecked;
	}

	public void setChecked(boolean checked) {
		mChecked = checked;
	}

	public String getId() {
		return mId;
	}

	public String getTypeTransport() {
		return mTypeTransport;
	}

	public String getTitle() {
		return mTitle;
	}

	public String getNameBegin() {
		return mNameBegin;
	}

	public String getNameEnd() {
		return mNameEnd;
	}

}
