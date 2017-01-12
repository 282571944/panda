package com.watermelon.luomi.panda.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

import com.watermelon.luomi.panda.MainConstant;
import com.watermelon.luomi.panda.R;
import com.watermelon.luomi.panda.activity.PublishActivity;
import com.watermelon.luomi.panda.entity.User;
import com.watermelon.luomi.panda.view.CustomDialog;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * common utils
 * Created by luomi on 2016-09-11.
 */
public class CommonUtils {

    /**
     * get random length string
     *
     * @param length length
     * @return random string
     */
    public static String getRandomForLen(int length) {
        StringBuffer stringBuffer = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            stringBuffer.append(random.nextInt(9) + "");
        }
        return stringBuffer.toString();
    }

    /**
     * toast
     *
     * @param activity
     * @param announcement
     */
    public static void toast(Activity activity, String announcement) {
        Toast.makeText(activity, announcement, Toast.LENGTH_SHORT).show();
    }

    /**
     * format date
     *
     * @param date
     * @return
     */
    public static String formatDate(String date) {
        if (date == null || date.equals("")) return "";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        Date date1 = null;
        try {
            date1 = simpleDateFormat.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return "日期格式异常";
        }
        Date date2 = new Date();
        long dateDistance = (date1.getTime() - date2.getTime()) / (1000 * 60 * 60 * 24);
        switch ((int) Math.floor(dateDistance)) {
            case 0:
                return "今天";
            case 1:
                return "昨天";
            default:
                return date1.getMonth() + "月" + date1.getDay() + "日";
        }
    }

    /**
     * check token
     *
     * @return
     */
    public static void checkToken(Activity activity, OnClickListener confirm) {
        boolean login = false;
        try {
            User objectFromShare = getObjectFromShare(activity, MainConstant.USER, MainConstant.USER_OBJ);
            if (objectFromShare != null) {
                login = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (login) {
            activity.startActivity(new Intent(activity, PublishActivity.class));
        } else {
            showLogin(activity, confirm);
        }
    }

    /**
     * show login
     *
     * @param activity
     * @param confirm
     */
    public static void showLogin(Activity activity, OnClickListener confirm) {
        View view = LayoutInflater.from(activity).inflate(R.layout.alter_dialog, null);
        CustomDialog customDialog = new CustomDialog(activity, 0, 0, view, R.style.dialog);
        view.findViewById(R.id.confirm).setOnClickListener(o -> {
            customDialog.dismiss();
            confirm.onClick(view);
        });
        view.findViewById(R.id.cancel).setOnClickListener(o -> customDialog.dismiss());
        customDialog.show();
    }

    /**
     * set toolbar
     *
     * @param activity
     * @param title
     */
    @SuppressWarnings("deprecation")
    public static void setToolbar(AppCompatActivity activity, int title) {
        Toolbar toolbar = (Toolbar) activity.findViewById(R.id.toolbar);
        toolbar.setTitle("");
        toolbar.setNavigationIcon(activity.getResources().getDrawable(R.drawable.ic_chevron_left_black_24dp));
        ((TextView) activity.findViewById(R.id.title)).setText(activity.getResources().getString(title));
        activity.setSupportActionBar(toolbar);
    }

    /**
     * save object to share
     *
     * @param context
     * @param name
     * @param key
     * @param t
     * @param <T>
     * @return
     * @throws IOException
     */
    public static <T> boolean saveObjectToShare(Context context, String name, String key, T t) throws IOException {
        boolean success = false;
        SharedPreferences sharedPreferences = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        Editor editor = sharedPreferences.edit();
        if (t == null) {
            editor.putString(key, "");
            editor.commit();
        } else {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(t);
            String objectStr = new String(Base64.encode(byteArrayOutputStream.toByteArray(), Base64.DEFAULT));
            editor.putString(key, objectStr);
            editor.commit();
            success = true;
        }
        return success;
    }

    /**
     * get object from share
     *
     * @param context
     * @param name
     * @param key
     * @param <T>
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static <T> T getObjectFromShare(Context context, String name, String key) throws IOException, ClassNotFoundException {
        Object o = null;
        SharedPreferences sharedPreferences = context.getSharedPreferences(name, Context.MODE_PRIVATE);
        String sharedPreferencesString = sharedPreferences.getString(key, "");
        if (sharedPreferencesString.equals("")) {
            return null;
        } else {
            byte[] base64Bytes = Base64.decode(sharedPreferencesString, Base64.DEFAULT);
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(base64Bytes);
            ObjectInputStream ois = new ObjectInputStream(byteArrayInputStream);
            return (T) ois.readObject();
        }
    }
}
