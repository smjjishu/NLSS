package com.smj.comm.tools;


import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.*;

/**
 * Gson工具类
 */
public class GsonUtil {

    public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    private static final Gson gson = new GsonBuilder()
            .serializeNulls()
            .enableComplexMapKeySerialization().setDateFormat(DATE_FORMAT)
            .registerTypeAdapter(
                    new TypeToken<TreeMap<String, Object>>() {
                    }.getType(),
                    new JsonDeserializer<TreeMap<String, Object>>() {
                        @Override
                        public TreeMap<String, Object> deserialize(
                                JsonElement json, Type typeOfT,
                                JsonDeserializationContext context) throws JsonParseException {
                            TreeMap<String, Object> treeMap = new TreeMap<String, Object>();
                            JsonObject jsonObject = json.getAsJsonObject();
                            Set<Map.Entry<String, JsonElement>> entrySet = jsonObject.entrySet();
                            for (Map.Entry<String, JsonElement> entry : entrySet) {
                                treeMap.put(entry.getKey(), entry.getValue());
                            }
                            return treeMap;
                        }
                    })
            .create();

    /**
     * 转成Json字符串
     *
     * @param object
     * @return
     */
    public static String toJson(Object object) {
        return gson.toJson(object);
    }

    /**
     * Json转Java对象
     *
     * @param json
     * @param clz
     * @param <T>
     * @return
     */
    public static <T> T fromJson(String json, Class<T> clz) {
        return gson.fromJson(json, clz);
    }

    /**
     * Json转Java对象
     *
     * @param json
     * @param type
     * @param <T>
     * @return
     */
    public static <T> T fromJson(String json, Type type) {
        return gson.fromJson(json, type);
    }

    /**
     * json转List集合
     *
     * @param json
     * @param clz
     * @param <T>
     * @return
     */
    public static <T> List<T> jsonToList(String json, Class<T> clz) {
        Type type = new TypeToken<List<T>>() {
        }.getType();
        return gson.fromJson(json, type);
    }

    /**
     * Json转List集合,遇到解析不了的，就使用这个
     *
     * @param json
     * @param cls
     * @param <T>
     * @return
     */
    public static <T> List<T> fromJsonList(String json, Class<T> cls) {
        List<T> mList = new ArrayList<T>();
        JsonArray array = new JsonParser().parse(json).getAsJsonArray();
        Gson mGson = new Gson();
        for (final JsonElement elem : array) {
            mList.add(mGson.fromJson(elem, cls));
        }
        return mList;
    }

    /**
     * Json转换成Map的List集合对象
     *
     * @param json
     * @param clz
     * @param <T>
     * @return
     */
    public static <T> List<Map<String, T>> toListMap(String json, Class<T> clz) {
        Type type = new TypeToken<List<Map<String, T>>>() {
        }.getType();
        return gson.fromJson(json, type);
    }

    /**
     * Json转Map对象
     *
     * @param json
     * @param clz
     * @param <T>
     * @return
     */
    public static <T> Map<String, T> toMap(String json, Class<T> clz) {
        Type type = new TypeToken<Map<String, T>>() {
        }.getType();
        return gson.fromJson(json, type);
    }

    /**
     * Json转Map对象
     *
     * @param json
     * @return
     */
    public static Map<String, Object> toMap(String json) {
        TreeMap<String, Object> map =
                gson.fromJson(json, new TypeToken<TreeMap<String, Object>>() {
                }.getType());
        return map;
    }

}
