package id.atumdev.applib.networkhandler.request;

import android.support.annotation.NonNull;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Angga.Prasetiyo on 12/10/2015.
 */
public class GsonMutipartRequest<T> extends Request<T> {
    private static final String TAG = GsonMutipartRequest.class.getSimpleName();

    private MultipartEntityBuilder mBuilder = MultipartEntityBuilder.create();
    private final Response.Listener<T> mListener;
    private final ParamMultipart paramMultipart;
    protected Map<String, String> headers;
    private Class<T> clazz;
    private Gson mGson = new Gson();

    public GsonMutipartRequest(String url, @NonNull ParamMultipart paramMultipart,
                               Class<T> clazz, Response.Listener<T> listener,
                               Response.ErrorListener errorListener) {
        super(Method.POST, url, errorListener);
        this.mListener = listener;
        this.paramMultipart = paramMultipart;
        this.clazz = clazz;
        buildMultipartEntity();
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        Map<String, String> headers = super.getHeaders();
        if (headers == null
                || headers.equals(Collections.emptyMap())) {
            headers = new HashMap<String, String>();
        }
        headers.put("Accept", "application/json");
        return headers;
    }

    @Override
    protected Map<String, String> getParams() throws AuthFailureError {
        return paramMultipart.getOtherParams();
    }

    private void buildMultipartEntity() {
        mBuilder.addBinaryBody(paramMultipart.getParamFile(), paramMultipart.getFile(),
                paramMultipart.getContentType(), paramMultipart.getFile().getName());
        mBuilder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
        mBuilder.setLaxMode().setBoundary("xx").setCharset(Charset.forName("UTF-8"));
    }

    @Override
    public String getBodyContentType() {
        return mBuilder.build().getContentType().getValue();
    }

    @Override
    public byte[] getBody() throws AuthFailureError {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            mBuilder.build().writeTo(bos);
        } catch (IOException e) {
            VolleyLog.e("IOException writing to ByteArrayOutputStream bos," +
                    " building the multipart request.");
        }

        return bos.toByteArray();
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {
            String json = new String(
                    response.data, HttpHeaderParser.parseCharset(response.headers));
            Log.d(TAG, "RESPONSE = " + json);
            return Response.success(
                    mGson.fromJson(json, clazz), HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JsonSyntaxException e) {
            return Response.error(new ParseError(e));
        }
    }

    @Override
    protected void deliverResponse(T response) {
        mListener.onResponse(response);
    }

}
