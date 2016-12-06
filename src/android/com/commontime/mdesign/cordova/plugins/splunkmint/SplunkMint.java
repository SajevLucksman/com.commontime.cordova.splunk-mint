package com.commontime.mdesign.plugins.splunkmint;

import android.os.Handler;
import android.util.Log;

import com.splunk.mint.Mint;
import com.splunk.mint.MintLog;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.InvocationTargetException;
import java.util.Iterator;

public class SplunkMint extends CordovaPlugin {

    private static final String TAG = "SplunkMint";
    private static final String START = "start";
    private static final String CRASH = "crash";
    private static final String ENABLE_LOG_CAT = "enableLogCat";
    private static final String LEAVE_BREADCRUMB = "leaveBreadcrumb";
    private static final String LOG_EVENT = "logEvent";
    private static final String GET_TOTAL_CRASHES_NUM = "getTotalCrashesNum";
    private static final String ENABLE = "enable";
    private static final String LINES = "lines";
    private static final String FILTER = "filter";
    private static final String LOG = "log";
    private static final String ENABLE_DEBUG_LOG = "enableDebugLog";
    private static final String NAME = "name";
    private static final String REASON = "reason";
    private static final String TRANSACTION_CANCEL = "transactionCancel";
    private static final String TRANSACTION_STOP = "transactionStop";
    private static final String TRANSACTION_START = "transactionStart";
    private static final String MSG = "msg";
    private static final String PRIORITY = "priority";
    private static final String UNABLE_TO_LOG = "Unable to log.";
    private static final String GET_LAST_CRASH_ID = "getLastCrashId";
    private static final String LOG_VIEW = "logView";
    private static final String FLUSH = "flush";
    private static final String TAG1 = "tag";

    @Override
    protected void pluginInitialize() {
        String api_key = preferences.getString("splunk_android_api_key", "");
        String extra_data = preferences.getString("splunk_extra_data", "");
        initSplunk(api_key, extra_data);
    }

    private void initSplunk(String api_key, String extra_data) {
        if( api_key != null && !api_key.isEmpty() ) {
            try {
                Mint.disableNetworkMonitoring();
                Mint.initAndStartSession(cordova.getActivity(), api_key );
                if( extra_data != null && !extra_data.isEmpty() ) {
                    JSONObject jso = new JSONObject(extra_data);
                    Iterator<String> keyIter = jso.keys();
                    while( keyIter.hasNext() ) {
                        String key = keyIter.next();
                        String val = jso.getString(key);
                        Mint.addExtraData(key, val);
                    }
				}
            } catch( IllegalArgumentException e ) {
                Log.e(TAG, e.getMessage());
                e.printStackTrace();
            } catch (JSONException e) {
                Log.e(TAG, e.getMessage());
                e.printStackTrace();
            }
        }
    }

    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {

        if( action.equals(CRASH)) {
            Handler handler = new Handler();
            handler.post(new Runnable() {
                @Override
                public void run() {
                    run();
                }
            });
        } else if( action.equals(ENABLE_LOG_CAT)) {
            JSONObject jso = args.getJSONObject(0);
            boolean enable = jso.getBoolean(ENABLE);
            int loglines = jso.optInt(LINES, 0);
            String filter = jso.optString(FILTER, "");
            Mint.enableLogging(enable);
            if( loglines > 0 )
                Mint.setLogging(loglines);
            if( !filter.isEmpty() )
                Mint.setLogging(filter);
            callbackContext.success();
            return true;
        } else if( action.equals(LEAVE_BREADCRUMB)) {
            String crumb = args.getString(0);
            Mint.leaveBreadcrumb(crumb);
            callbackContext.success();package com.commontime.mdesign.plugins.splunkmint;

import android.os.Handler;
import android.util.Log;

import com.splunk.mint.Mint;
import com.splunk.mint.MintLog;
import com.splunk.mint.MintLogLevel;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

public class SplunkMint extends CordovaPlugin {

    private static final String TAG = "SplunkMint";
    private static final String START = "start";
    private static final String CRASH = "crash";
    private static final String ENABLE_LOG_CAT = "enableLogCat";
    private static final String LEAVE_BREADCRUMB = "leaveBreadcrumb";
    private static final String LOG_EVENT = "logEvent";
    private static final String GET_TOTAL_CRASHES_NUM = "getTotalCrashesNum";
    private static final String ENABLE = "enable";
    private static final String LINES = "lines";
    private static final String FILTER = "filter";
    private static final String LOG = "log";
    private static final String ENABLE_DEBUG_LOG = "enableDebugLog";
    private static final String NAME = "name";
    private static final String REASON = "reason";
    private static final String TRANSACTION_CANCEL = "transactionCancel";
    private static final String TRANSACTION_STOP = "transactionStop";
    private static final String TRANSACTION_START = "transactionStart";
    private static final String MSG = "msg";
    private static final String PRIORITY = "priority";
    private static final String UNABLE_TO_LOG = "Unable to log.";
    private static final String GET_LAST_CRASH_ID = "getLastCrashId";
    private static final String LOG_VIEW = "logView";
    private static final String FLUSH = "flush";
    private static final String TAG1 = "tag";

    @Override
    protected void pluginInitialize() {
        String api_key = preferences.getString("splunk_android_api_key", "");
        String extra_data = preferences.getString("splunk_extra_data", "");
        initSplunk(api_key, extra_data);
    }

    private void initSplunk(String api_key, String extra_data) {
        if( api_key != null && !api_key.isEmpty() ) {
            try {
                Mint.disableNetworkMonitoring();
                Mint.initAndStartSession(cordova.getActivity(), api_key );
                if( extra_data != null && !extra_data.isEmpty() ) {
                    JSONObject jso = new JSONObject(extra_data);
                    Iterator<String> keyIter = jso.keys();
                    while( keyIter.hasNext() ) {
                        String key = keyIter.next();
                        String val = jso.getString(key);
                        Mint.addExtraData(key, val);
                    }
				}
            } catch( IllegalArgumentException e ) {
                Log.e(TAG, e.getMessage());
                e.printStackTrace();
            } catch (JSONException e) {
                Log.e(TAG, e.getMessage());
                e.printStackTrace();
            }
        }
    }

    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {

        if( action.equals(CRASH)) {
            Handler handler = new Handler();
            handler.post(new Runnable() {
                @Override
                public void run() {
                    run();
                }
            });
        } else if( action.equals(ENABLE_LOG_CAT)) {
            JSONObject jso = args.getJSONObject(0);
            boolean enable = jso.getBoolean(ENABLE);
            int loglines = jso.optInt(LINES, 0);
            String filter = jso.optString(FILTER, "");
            Mint.enableLogging(enable);
            if( loglines > 0 )
                Mint.setLogging(loglines);
            if( !filter.isEmpty() )
                Mint.setLogging(filter);
            callbackContext.success();
            return true;
        } else if( action.equals(LEAVE_BREADCRUMB)) {
            String crumb = args.getString(0);
            Mint.leaveBreadcrumb(crumb);
            callbackContext.success();
            return true;
        } else if( action.equals(LOG_EVENT)) {
            String log = args.getString(0);
            //Parsing json string to json object
            JSONObject mintEventJsonObject = new JSONObject(log);
            String errorMessage = (String) mintEventJsonObject.get("errorMessage");
            HashMap<String, Object> mintExtraData = new HashMap<String, Object>();
            Iterator<?> keys = mintEventJsonObject.keys();
            while(keys.hasNext() ) {
                String key = (String)keys.next();
                mintExtraData.put(key, (String) mintEventJsonObject.get(key));
            }
            Mint.logEvent(errorMessage, MintLogLevel.Error, mintExtraData);
            callbackContext.success();
            return true;
        } else if( action.equals(LOG_VIEW)) {
            String log = args.getString(0);
            Mint.logView(log);
            callbackContext.success();
            return true;
        } else if( action.equals(GET_TOTAL_CRASHES_NUM)) {
            int crashes = Mint.getTotalCrashesNum();
            callbackContext.success(crashes);
            return true;
        } else if( action.equals(GET_LAST_CRASH_ID)) {
            String lastCrashId = Mint.getLastCrashID();
            callbackContext.success(lastCrashId);
            return true;
        } else if( action.equals(TRANSACTION_START)) {
            String name = args.getString(0);
            Mint.transactionStart(name);
            callbackContext.success();
            return true;
        } else if( action.equals(TRANSACTION_STOP)) {
            String name = args.getString(0);
            Mint.transactionStop(name);
            callbackContext.success();
            return true;
        } else if( action.equals(TRANSACTION_CANCEL)) {
            JSONObject jso = args.getJSONObject(0);
            String name = jso.getString(NAME);
            String reason = jso.getString(REASON);
            Mint.transactionCancel(name, reason);
            callbackContext.success();
            return true;
        } else if( action.equals(ENABLE_DEBUG_LOG)) {
            Mint.enableDebugLog();
            callbackContext.success();
            return true;
        } else if( action.equals(FLUSH)) {
            Mint.flush();
            callbackContext.success();
            return true;
        } else if( action.equals(LOG)) {
            JSONObject jso = args.getJSONObject(0);
            String tag = jso.getString(TAG1);
            String msg = jso.getString(MSG);
            String lvl = jso.optString(PRIORITY, "d");
            try {
                MintLog.class.getMethod(lvl, String.class, String.class).invoke(null, tag, msg);
                callbackContext.success();
                return true;
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            callbackContext.error(UNABLE_TO_LOG);
            return true;
        }

        return false;
    }
}
            return true;
        } else if( action.equals(LOG_EVENT)) {
            String log = args.getString(0);
            Mint.logEvent(log);
            callbackContext.success();
            return true;
        } else if( action.equals(LOG_VIEW)) {
            String log = args.getString(0);
            Mint.logView(log);
            callbackContext.success();
            return true;
        } else if( action.equals(GET_TOTAL_CRASHES_NUM)) {
            int crashes = Mint.getTotalCrashesNum();
            callbackContext.success(crashes);
            return true;
        } else if( action.equals(GET_LAST_CRASH_ID)) {
            String lastCrashId = Mint.getLastCrashID();
            callbackContext.success(lastCrashId);
            return true;
        } else if( action.equals(TRANSACTION_START)) {
            String name = args.getString(0);
            Mint.transactionStart(name);
            callbackContext.success();
            return true;
        } else if( action.equals(TRANSACTION_STOP)) {
            String name = args.getString(0);
            Mint.transactionStop(name);
            callbackContext.success();
            return true;
        } else if( action.equals(TRANSACTION_CANCEL)) {
            JSONObject jso = args.getJSONObject(0);
            String name = jso.getString(NAME);
            String reason = jso.getString(REASON);
            Mint.transactionCancel(name, reason);
            callbackContext.success();
            return true;
        } else if( action.equals(ENABLE_DEBUG_LOG)) {
            Mint.enableDebugLog();
            callbackContext.success();
            return true;
        } else if( action.equals(FLUSH)) {
            Mint.flush();
            callbackContext.success();
            return true;
        } else if( action.equals(LOG)) {
            JSONObject jso = args.getJSONObject(0);
            String tag = jso.getString(TAG1);
            String msg = jso.getString(MSG);
            String lvl = jso.optString(PRIORITY, "d");
            try {
                MintLog.class.getMethod(lvl, String.class, String.class).invoke(null, tag, msg);
                callbackContext.success();
                return true;
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
            callbackContext.error(UNABLE_TO_LOG);
            return true;
        }

        return false;
    }
}
