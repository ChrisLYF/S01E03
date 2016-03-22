package com.zhixin.richard.s01e03.utils;

import android.util.Log;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import java.util.Date;

/**
 * Created by Administrator on 2016-03-21.
 */
public class DateDeserializer implements JsonDeserializer<Date> {
    @Override
    public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Date date = new Date();
        json.getClass();
        JsonObject jsonObject = json.getAsJsonObject();
        JsonElement jsonElement = jsonObject.get("time");
        date.setTime(jsonElement.getAsLong());
        Log.d("TAG", date.toString());
        return date;
    }
}
