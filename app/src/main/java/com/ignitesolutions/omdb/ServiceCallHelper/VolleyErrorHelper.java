package com.ignitesolutions.omdb.ServiceCallHelper;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.ignitesolutions.omdb.R;

/**
 * ----------------------------------------------------------------------
 * Created by Amish Lakkad on 4/4/2016 5:56 PM.
 * ----------------------------------------------------------------------
 * Change History
 * ----------------------------------------------------------------------
 * SR NO |    DATE       |        Changed By       |  Description
 * ----------------------------------------------------------------------
 * 1   |
 */
public class VolleyErrorHelper {



    /**
     *
     * @param error
     * @param context
     * @return Returns appropriate message which is to be displayed to the user against
     * the specified error object.
     */
    public static String getMessage(Object error, Context context) {
        if (error instanceof TimeoutError) {
            return context.getResources().getString(R.string.generic_server_down);
        } /*else if (isServerProblem(error)) {
            return handleServerError(error, context);
        }*/
        else if (isNetworkProblem(error)) {
            return context.getResources().getString(R.string.no_internet);
        }
        return context.getResources().getString(R.string.generic_error);
    }

    /**
     *
     * @param error
     * @param context
     * @return Return generic message for errors
     */
    public static String getErrorType(Context context,Object error) {
        if (error instanceof TimeoutError) {
            return context.getResources().getString(R.string.generic_server_timeout);
        } else if (error instanceof ServerError) {
            return context.getResources().getString(R.string.generic_server_down);
        } else if (error instanceof AuthFailureError) {
            return context.getResources().getString(R.string.auth_failed);
        } else if (error instanceof NetworkError) {
            return context.getResources().getString(R.string.no_internet);
        } else if (error instanceof NoConnectionError) {
            return context.getResources().getString(R.string.no_network_connection);
        } else if (error instanceof ParseError) {
            return context.getResources().getString(R.string.parsing_failed);
        }
        else
        {
            return context.getResources().getString(R.string.generic_error);
        }

    }

    /**
     * Determines whether the error is related to network
     *
     * @param error
     * @return
     */
    private static boolean isNetworkProblem(Object error) {
        return (error instanceof NetworkError) || (error instanceof NoConnectionError);
    }

    /**
     * Determines whether the error is related to server
     *
     * @param
     * @return
     */
   /* private static boolean isServerProblem(Object error) {
        return (error instanceof ServerError) || (error instanceof AuthFailureError);
    }*/


    /*private static String handleServerError(Object err, Context context) {
        VolleyError error = (VolleyError) err;

        NetworkResponse response = error.networkResponse;

        if (response != null) {
            switch (response.statusCode) {
                case 404:
                case 422:
                case 401:
                    try {
                        // server might return error like this { "error":
                        // "Some error occured" }
                        // Use "Gson" to parse the result
                        HashMap<String, String> result = new Gson().fromJson(new String(response.data),
                                new TypeToken<Map<String, String>>() { }.getType());

                        if (result != null && result.containsKey("error")) {
                            return result.get("error");
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    // invalid request
                    return error.getMessage();

                default:
                    return context.getResources().getString(R.string.generic_server_down);
            }
        }
        return context.getResources().getString(R.string.generic_error);
    }*/

    public  static String getErrorMessage(Context mContext,int errCode,String msg,String leadNo)
    {
        String errMessage="";

        switch (errCode)
        {

            case 1:
                errMessage="Invalid login";
                break;

            case 2:
                errMessage="Your password has been expired please change your password";
                break;

            case 3:
                errMessage="Your UserId has been blocked due to multiple failed login attempts.please contact LMSsupport to Reset/Retreive Password or UserId will be unblocked automatically after 15 minute User blocked !!!";
                break;

            case 4:
                errMessage="Passcode Expired";
                /*Pref.setValue(mContext, Const.IS_LOGIN,false);
                Pref.setValue(mContext, Const.USER_NAME,"");
                Pref.setValue(mContext, Const.PASS_CODE,"");
                Pref.setValue(mContext, Const.ROLE_NAME,"");
                Pref.setValue(mContext, Const.USER_ID,"");*/

                break;

            case 444:
                errMessage="Invalid PassCode Authentication";

                break;

            case 5:
                errMessage="Your password has been expired please change your password";
                break;

            case 6:
                errMessage="Your UserId has been blocked due to multiple failed login attempts.please contact LMSsupport to Reset/Retreive Password or UserId will be unblocked automatically after 15 minute User blocked !!!";
                break;

            case 7:
                errMessage="Sorry! Password matches with one of last 3 Passwords!";
                break;

            case 8:
                errMessage="Sorry! Old Password is Incorrect!";
                break;

            case 9:
                errMessage="Technical error (0009)";
                break;

            case 10:
                errMessage="Your Session has expired please login again";
                break;

            case 11:
                errMessage="Technical error (0011)";
                break;

            case 12:
                errMessage="Lead Details not found";
                break;

            case 13:
                errMessage="No villages assigned";
                break;

            case 14:
                errMessage="Leads for selected village not available (0014)";
                break;

            case 15:
                errMessage="Search result not found";
                break;

            case 16:
                errMessage=msg+"(0016)";
                break;

            case 17:
                errMessage=msg+"(0017)";
                break;

            case 18:
                errMessage="Device tokenid cannot be empty";
                break;

            case 19:
                errMessage="New Password cannot be empty";
                break;

            case 20:
                errMessage="Old Password cannot be empty";
                break;

            case 21:
                errMessage="Invalid Login (0021)";
                break;

            case 22:
                errMessage="Technical Error (0022)";
                break;

            case 23:
                errMessage="";
                break;

            case 24:
                errMessage="Please enter proper lead number";
                break;

            case 25:
                errMessage="Image not found (0025)";
                break;

            case 26:
                errMessage="Image not found (0026)";
                break;

            case 27:
                errMessage="Image not found (0027)";
                break;

            case 28:
                errMessage="Image not found (0028)";
                break;

            case 29:
                errMessage="Image not found (0029)";
                break;

            case 30:
                errMessage="Image not found (0030)";
                break;

            case 31:
                errMessage="Technical error (0031)";
                break;

            case 32:
                errMessage="Your Session has expired please login again";
                break;

            case 33:
                errMessage=msg;
                break;

            case 34:
                errMessage=msg;
                break;

            case 35:
                errMessage="Technical Error (0035)";
                break;

            case 36:
                errMessage="Technical Error (0036)";
                break;

            case 37:
                errMessage="Technical Error (0037)";
                break;

            case 38:
                errMessage="Not resend by bank";
                break;

            case 39:
                errMessage="Technical Error (0039)";
                break;

            case 40:
                errMessage="Technical Error (0040)";
                break;


            case 41:
                errMessage="Technical Error (0041)";
                break;

            case 42:
                errMessage="All fields are mandatory except reason";
                break;

            case 43:
                errMessage="Rate not found. Contact Ergo operation(0043)";
                break;

            case 44:
                errMessage="Milk yeild accepts only numeric";
                break;

            case 45:
                errMessage="No of lactations accepts only numeric";
                break;

            case 46:
                errMessage="Market value accepts only numeric";
                break;

            case 47:
                errMessage="Technical Error (0047)";
                break;

            case 48:
                errMessage="Technical Error (0048)";
                break;

            case 49:
                errMessage="Technical Error (0049)";
                break;

            case 50:
                errMessage="Technical Error (0050)";
                break;

            case 51:
                errMessage=msg;
                break;

            case 52:
                errMessage="Image Count Mismatch for Lead Number "+leadNo+" Please Upload Images again (0052)";
                break;

            case 53:
                errMessage="Mandatory image missing for Lead Number "+leadNo+" Please Upload Images again (0053)";
                break;

            case 54:
                errMessage="Signature mandatory for Lead Number "+leadNo+" Please Upload Images again (0054)";
                break;

            case 9999:
                errMessage="Technical Error (9999)";
                break;

            default:
                return errMessage;
        }

        return errMessage;

    }


}