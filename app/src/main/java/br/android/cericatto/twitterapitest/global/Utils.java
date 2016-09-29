package br.android.cericatto.twitterapitest.global;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Utils.java.
 *
 * @author Rodrigo Cericatto
 * @since Sep 27, 2016
 */
public class Utils {

    //--------------------------------------------------
    // Network Methods
    //--------------------------------------------------

    public static Boolean hasConnection(Activity activity) {
        ConnectivityManager connectivityManager = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        Boolean hasConnection = connectivityManager.getActiveNetworkInfo() != null;
        return hasConnection;
    }

    //--------------------------------------------------
    // String Methods
    //--------------------------------------------------

    public static Boolean isEmpty(String text) {
        Boolean result = true;
        Boolean isNull = (text == null);
        if (!isNull) {
            Boolean isZeroLength = (text.length() <= 0);
            Boolean isEmpty = (text.equals(""));
            Boolean contentOfTextIsLiteralNull = (text.equals("null"));
            result = isNull || isZeroLength || isEmpty || contentOfTextIsLiteralNull;
        }
        return result;
    }

    //--------------------------------------------------
    // Activity Methods
    //--------------------------------------------------

    public static void startActivity(Activity activity, Class clazz) {
        Intent intent = new Intent(activity, clazz);
        activity.startActivity(intent);
        Navigation.animate(activity, Navigation.Animation.GO);
    }

    public static void startActivityExtras(Activity activity, Class clazz, String key, Object value) {
        Intent intent = new Intent(activity, clazz);
        Bundle extras = getExtra(new Bundle(), key, value);
        intent.putExtras(extras);

        activity.startActivity(intent);
        Navigation.animate(activity, Navigation.Animation.GO);
    }

    private static Bundle getExtra(Bundle extras, String key, Object value) {
        if (value instanceof String) {
            extras.putString(key, (String)value);
        } else if (value instanceof Integer) {
            extras.putInt(key, (Integer)value);
        } else if (value instanceof Long) {
            extras.putLong(key, (Long)value);
        } else if (value instanceof Boolean) {
            extras.putBoolean(key, (Boolean) value);
        }
        return extras;
    }

    //--------------------------------------------------
    // Json Methods
    //--------------------------------------------------

    public static String parseString(JSONObject jsonObject, String key) {
        String value = "";
        try {
            value = jsonObject.getString(key);
            if (value == null) {
                value = "";
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return value;
    }
}