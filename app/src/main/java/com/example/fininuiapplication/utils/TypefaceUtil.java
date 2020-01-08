package com.example.fininuiapplication.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.util.Log;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class TypefaceUtil {

    /**
     * Using reflection to override default_user typeface
     * NOTICE: DO NOT FORGET TO SET TYPEFACE FOR APP THEME AS DEFAULT
     * TYPEFACE WHICH WILL BE OVERRIDDEN
     *
     * @param context to work with assets
     */
    public static void overrideFonts(Context context) {
        /*Typeface lightFontTypeface =
                Typeface.createFromAsset(context.getAssets(), "fonts/Muli-Light.ttf");*/
        Typeface regularFontTypeface =
                Typeface.createFromAsset(context.getAssets(), "fonts/Avenir-Book.ttf");
        Typeface regularFontTypefaceBold =
                Typeface.createFromAsset(context.getAssets(), "fonts/Avenir-Light.ttf");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Map<String, Typeface> newMap = new HashMap<String, Typeface>();
            newMap.put("sans-serif", regularFontTypeface);
            //newMap.put("sans-serif-light", regularFontTypeface);
            newMap.put("sans-serif-bold", regularFontTypefaceBold);

            try {
                final Field staticField = Typeface.class
                        .getDeclaredField("sSystemFontMap");
                staticField.setAccessible(true);
                staticField.set(null, newMap);
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        } else {
            try {
                final Field defaultFontTypefaceField =
                        Typeface.class.getDeclaredField("sans");
                defaultFontTypefaceField.setAccessible(true);
                defaultFontTypefaceField.set(null, regularFontTypeface);

                final Field lightFontTypefaceField =
                        Typeface.class.getDeclaredField("sans-serif-light");
                defaultFontTypefaceField.setAccessible(true);
                defaultFontTypefaceField.set(null, regularFontTypeface);
            } catch (Exception e) {
                Log.e(TypefaceUtil.class.getSimpleName(), "Can not set custom fonts " + regularFontTypeface.toString() + " and " +
                        regularFontTypeface.toString());
            }
        }
    }
}
