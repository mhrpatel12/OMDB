package com.ignitesolutions.omdb.ServiceCallHelper;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.ignitesolutions.omdb.Utils.AppController;
import com.ignitesolutions.omdb.Utils.LogUtils;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by patelmih on 12/18/2015.
 */
public class WebServiceCall {

    private String TAG = this.getClass().getSimpleName();

    private Context mContext;
    private String requestUrl;
    private WebserviceResponseListener mWebServiceResponseListener;
    private ProgressDialog pDialog;
    private JSONObject requestJsonPara;
    //private int TIMEOUT = (2 * 60 * 1000);
    private int TIMEOUT = (10000);
    private int TIMEOUT_DOCUMENT_BACKGROUND = (50000);
    private SharedPreferences pref;

    private HashMap<String, String> params;

    public WebServiceCall(Context mContext, String requestUrl, WebserviceResponseListener mWebServiceListener) {
        this.mContext = mContext;
        this.requestUrl = requestUrl;
        this.mWebServiceResponseListener = mWebServiceListener;

        pDialog = new ProgressDialog(mContext);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);
    }

    public WebServiceCall(Context mContext, String requestUrl, WebserviceResponseListener mWebServiceListener, JSONObject jobj) {
        this.mContext = mContext;
        this.requestUrl = requestUrl;
        this.mWebServiceResponseListener = mWebServiceListener;
        this.requestJsonPara = jobj;

        pDialog = new ProgressDialog(mContext);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);
    }

    public WebServiceCall(Context mContext, String requestUrl, WebserviceResponseListener mWebServiceListener, JSONObject jobj, String dialogMsg) {
        this.mContext = mContext;
        this.requestUrl = requestUrl;
        this.mWebServiceResponseListener = mWebServiceListener;
        this.requestJsonPara = jobj;

        pDialog = new ProgressDialog(mContext);
        pDialog.setMessage(dialogMsg + "");
        pDialog.setCancelable(false);
    }

    public WebServiceCall(Context mContext, String requestUrl, WebserviceResponseListener mWebServiceListener, HashMap<String, String> para) {
        this.mContext = mContext;
        this.requestUrl = requestUrl;
        this.mWebServiceResponseListener = mWebServiceListener;
        this.params = para;

        pDialog = new ProgressDialog(mContext);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);
    }


    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }

    public void getRequest(final int retFrom) {

        showDialog();

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET, requestUrl, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {

                try {
                    LogUtils.LOGV(TAG, response + "");
                    hideDialog();

                    mWebServiceResponseListener.response(retFrom, response + "");

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }, new ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

                LogUtils.LOGE(TAG, "-------------" + error.toString());

                hideDialog();

                mWebServiceResponseListener.onError(retFrom, error.getMessage());
            }
        });

        LogUtils.LOGE(TAG, "=====>TIMEOUT : " + TIMEOUT);
        // Adding request to request queue
        jsonObjReq.setRetryPolicy(new DefaultRetryPolicy(
                TIMEOUT,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        AppController.getInstance().addToRequestQueue(jsonObjReq);
    }

    public void postRequest(final int retFrom, final String request_tag, final boolean withHeader) {

        showDialog();

        LogUtils.LOGV(TAG, "-------------URL : " + requestUrl);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, requestUrl, requestJsonPara, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                LogUtils.LOGV(TAG, "-------------Response : " + jsonObject + "");

                pDialog.dismiss();
                //hideDialog();

                mWebServiceResponseListener.response(retFrom, jsonObject.toString());
            }
        }, new ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

                String errMsg = VolleyErrorHelper.getErrorType(mContext, volleyError);
                LogUtils.LOGE(TAG, errMsg);

                hideDialog();

                mWebServiceResponseListener.onError(retFrom, errMsg);
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
               /* headers.put("Content-Type", "application/json");
                headers.put("charset", "utf-8");*/
                headers.put("Content-Type", "application/json; charset=utf-8");
                if (withHeader) {
                    pref = mContext.getSharedPreferences("Session Data", Activity.MODE_PRIVATE);
                    LogUtils.LOGV(TAG, "oAuthkey :: " + pref.getString("oAuthkey", ""));
                    headers.put("oAuthkey", pref.getString("oAuthkey", ""));
                }
                return headers;
            }
        };

        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(TIMEOUT, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        AppController.getInstance().addToRequestQueue(jsonObjectRequest, request_tag);

    }

    public void postRequestBackground(final int retFrom, final String request_tag, final boolean withHeader) {

        LogUtils.LOGV(TAG, "-------------URL : " + requestUrl);
        LogUtils.LOGV(TAG, "-------------requestPara ::: " + requestJsonPara.toString());

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, requestUrl, requestJsonPara, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                LogUtils.LOGV(TAG, "-------------Response : " + jsonObject + "");

                mWebServiceResponseListener.response(retFrom, jsonObject.toString());
            }
        }, new ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                LogUtils.LOGE(TAG, volleyError.getMessage() + "");

                mWebServiceResponseListener.onError(retFrom, volleyError.getMessage() + "");
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                HashMap<String, String> headers = new HashMap<String, String>();
               /* headers.put("Content-Type", "application/json");
                headers.put("charset", "utf-8");*/
                headers.put("Content-Type", "application/json; charset=utf-8");
                if (withHeader) {
                    pref = mContext.getSharedPreferences("Session Data", Activity.MODE_PRIVATE);
                    LogUtils.LOGV(TAG, "oAuthkey :: " + pref.getString("oAuthkey", ""));
                    headers.put("oAuthkey", pref.getString("oAuthkey", ""));
                }
                return headers;
            }
        };

        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(TIMEOUT_DOCUMENT_BACKGROUND,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        AppController.getInstance().addToRequestQueue(jsonObjectRequest, request_tag);

    }

}
