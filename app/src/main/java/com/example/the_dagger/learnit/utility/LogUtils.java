package com.example.the_dagger.learnit.utility;

import android.util.Log;

import com.example.the_dagger.learnit.BuildConfig;


public class LogUtils {
    private static final String LOG_PREFIX = "lEarnIt";
    private static final int LOG_PREFIX_LENGTH = LOG_PREFIX.length();
    private static final int MAX_LOG_TAG_LENGTH = 23;

    private static boolean LOGGING_ENABLED = false;
    private static final String DEBUG_BUILD_TYPE = "debug";
    private static String mLogTag;

    static {
        if (BuildConfig.BUILD_TYPE.equals(DEBUG_BUILD_TYPE)) {
            LOGGING_ENABLED = true;
        }
    }

    public static String makeLogTag(String str) {
        if (str.length() > MAX_LOG_TAG_LENGTH - LOG_PREFIX_LENGTH) {
            return LOG_PREFIX + str.substring(0, MAX_LOG_TAG_LENGTH - LOG_PREFIX_LENGTH - 1);
        }

        return LOG_PREFIX + str;
    }

    public static void configureTag(String mTag){
        mLogTag=mTag;
    }

    /**
     * Don't use this when obfuscating class names!
     */
    public static String makeLogTag(Class cls) {
        return makeLogTag(cls.getSimpleName());
    }

    public static void LOGD(String message) {
        if (LOGGING_ENABLED) {
            if (Log.isLoggable(mLogTag, Log.DEBUG)) {
                Log.d(mLogTag, message);
            }
        }
    }

    public static void LOGD(String message, Throwable cause) {
        if (LOGGING_ENABLED) {
            if (Log.isLoggable(mLogTag, Log.DEBUG)) {
                Log.d(mLogTag, message, cause);
            }
        }
    }

    public static void LOGV( String message) {
        if (LOGGING_ENABLED) {
            if (Log.isLoggable(mLogTag, Log.VERBOSE)) {
                Log.v(mLogTag, message);
            }
        }
    }

    public static void LOGV( String message, Throwable cause) {
        if (LOGGING_ENABLED) {
            if (Log.isLoggable(mLogTag, Log.VERBOSE)) {
                Log.v(mLogTag, message, cause);
            }
        }
    }

    public static void LOGI( String message) {
        if (LOGGING_ENABLED) {
            Log.i(mLogTag, message);
        }
    }

    public static void LOGI( String message, Throwable cause) {
        if (LOGGING_ENABLED) {
            Log.i(mLogTag, message, cause);
        }
    }

    public static void LOGW( String message) {
        if (LOGGING_ENABLED) {
            Log.w(mLogTag, message);
        }
    }

    public static void LOGW(final String tag, String message, Throwable cause) {
        if (LOGGING_ENABLED) {
            Log.w(mLogTag, message, cause);
        }
    }

    public static void LOGE( String message) {
        if (LOGGING_ENABLED) {
            Log.e(mLogTag, message);
        }
    }

    public static void LOGE( String message, Throwable cause) {
        if (LOGGING_ENABLED) {
            Log.e(mLogTag, message, cause);
        }
    }

    private LogUtils() {
    }
}