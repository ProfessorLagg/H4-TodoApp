package com.laggserver.rpgtodo.model;

import org.json.JSONException;
import org.json.JSONObject;

public interface JSONSerializeable {
	public JSONObject toJSON() throws JSONException;
}
