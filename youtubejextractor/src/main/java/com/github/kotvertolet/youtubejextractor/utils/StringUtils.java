package com.github.kotvertolet.youtubejextractor.utils;

import android.util.Log;

import com.github.kotvertolet.youtubejextractor.exception.ExtractionException;
import com.github.kotvertolet.youtubejextractor.models.enums.Extension;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.LinkedHashMap;
import java.util.Map;

public class StringUtils {

    static String escapeRegExSpecialCharacters(String inputString) {
        final String[] metaCharacters = {"\\", "^", "$", "{", "}", "[", "]", "(", ")", ".", "*", "+", "?", "|", "<", ">", "-", "&", "%"};

        for (String metaCharacter : metaCharacters) {
            if (inputString.contains(metaCharacter)) {
                inputString = inputString.replace(metaCharacter, "\\" + metaCharacter);
            }
        }
        return inputString;
    }

    public static Map<String, String> splitUrlParams(String url) throws ExtractionException {
        final String[] queryParams = url.split("&");
        return splitUrlParams(queryParams);
    }

    public static Map<String, String> splitUrlParams(URL url) throws ExtractionException {
        final String[] queryParams = url.getQuery().split("&");
        return splitUrlParams(queryParams);
    }

    public static Extension extractExtension(String rawExtension) {
        if (rawExtension.equals(Extension.WEBM.toString())) {
            return Extension.WEBM;
        } else if (rawExtension.equals(Extension.GPP.toString())) {
            return Extension.GPP;
        } else if (rawExtension.equals(Extension.FLV.toString())) {
            return Extension.FLV;
        } else if (rawExtension.equals(Extension.M4A.toString())) {
            return Extension.M4A;
        } else if (rawExtension.equals(Extension.MP4.toString())) {
            return Extension.MP4;
        } else {
            Log.e(StringUtils.class.getSimpleName(), "Unknown extension found: " + rawExtension);
            return Extension.UNKNOWN;
        }
    }

    private static Map<String, String> splitUrlParams(String[] queryParamsArr) throws ExtractionException {
        final Map<String, String> queryPairs = new LinkedHashMap<>();
        for (String queryParam : queryParamsArr) {
            final int idx = queryParam.indexOf("=");
            try {
                final String key = idx > 0 ? URLDecoder.decode(queryParam.substring(0, idx), "UTF-8") : queryParam;
                if (!queryPairs.containsKey(key)) {
                    final String value = idx > 0 && queryParam.length() > idx + 1
                            ? URLDecoder.decode(queryParam.substring(idx + 1), "UTF-8") : null;
                    queryPairs.put(key, value);
                }
            } catch (UnsupportedEncodingException e) {
                throw new ExtractionException(e);
            }
        }
        return queryPairs;
    }

}
