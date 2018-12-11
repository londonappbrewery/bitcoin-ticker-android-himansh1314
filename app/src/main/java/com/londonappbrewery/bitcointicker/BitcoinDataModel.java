package com.londonappbrewery.bitcointicker;

import org.json.JSONException;
import org.json.JSONObject;

public class BitcoinDataModel {
    private String value;
    public static BitcoinDataModel fromJSON(JSONObject jsonObject){
        try{
            BitcoinDataModel data = new BitcoinDataModel();
            data.value = jsonObject.getString("ask");
            return data;
        }catch (JSONException e){
            e.printStackTrace();
            return null;
        }
    }

    public String getValue() {
        return value;
    }
}
