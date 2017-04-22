package com.ignitesolutions.omdb.Utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import java.io.File;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * This class contains Utility methods for this application
 *
 * @author Zen Bhatt
 */
public class Utility {

    static NoticeDialogListener mListener;
    public static int myPosition = 0;
    public static int delPosition = 0;
    public static boolean IsDeleting = false;

    public static int minFileSize = 500;
    public static int maxFileSize = 2000;

    public interface NoticeDialogListener {
        void onDialogPositiveClick(DialogInterface builder);

        void onDialogNegativeClick(DialogInterface dialog);
    }

    /**
     * method to check Internet connectivity
     *
     * @param context -context of activity
     * @return true if Internet is available
     */
    public static boolean checkInternetConnection(Context context) {

        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager.getActiveNetworkInfo() != null

                && connectivityManager.getActiveNetworkInfo().isAvailable()

                && connectivityManager.getActiveNetworkInfo().isConnected()) {

            return true;
        } else {
            Log.v("INTERNETWORKING", "Internet not present");
            return false;
        }

    }

    /***
     * method to check whether text of Edit text is empty
     *
     * @param editText -edtit text to be checked
     * @return -false if empty
     */
    public static boolean isTextEmpty(EditText editText) {
        if (editText.getText().toString().trim().equalsIgnoreCase("")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Method is for to check the email is valid or not
     *
     * @param email :- String from edit text
     * @return
     */

    public static boolean isEmailValid(String email) {
        boolean isValid;
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        CharSequence inputStr = email;

        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(inputStr);

        if (email.length() == 0) {
            return false;
        }

        if (matcher.matches()) {
            isValid = true;
        } else {

            Log.i(".....Email", "Not valid");

            isValid = false;

        }
        return isValid;
    }

    public static void applyFont(final Context context, final View root, final String fontName) {
        try {

            if (root instanceof ViewGroup) {

                ViewGroup viewGroup = (ViewGroup) root;
                for (int i = 0; i < viewGroup.getChildCount(); i++)
                    applyFont(context, viewGroup.getChildAt(i), fontName);

            } else if (root instanceof TextView)
                ((TextView) root).setTypeface(Typeface.createFromAsset(context.getAssets(), fontName));

        } catch (Exception e) {
            // Log.e(TAG, "Error occured ");

        }
    }

    public static void disableShowSoftInput(EditText editText) {
        if (android.os.Build.VERSION.SDK_INT <= 10) {
            editText.setInputType(InputType.TYPE_NULL);
        } else {
            Class<EditText> cls = EditText.class;
            Method method;
            try {
                method = cls.getMethod("setShowSoftInputOnFocus", boolean.class);
                method.setAccessible(true);
                method.invoke(editText, false);
            } catch (Exception e) {
                // TODO: handle exception
            }

            try {
                method = cls.getMethod("setSoftInputShownOnFocus", boolean.class);
                method.setAccessible(true);
                method.invoke(editText, false);
            } catch (Exception e) {
                // TODO: handle exception
            }
        }
    }

    public static int calculateFileSize(String filepath) {
        Log.i("filepath>>>>", filepath);
        File file = new File(filepath);
        long fileSizeInBytes = file.length();
        float fileSizeInKB = fileSizeInBytes / 1024;
        int size = (int) fileSizeInKB;
        //Convert the KB to MegaBytes (1 MB = 1024 KBytes)
        //float fileSizeInMB = fileSizeInKB / 1024;
        return size;
    }
}
