package com.example.customermanagement.util;

import java.util.HashMap;
import java.util.Map;
import java.sql.Timestamp;

/**
 * Author: Aditya
 * Date: 08/08/20
 */
public class ResponseUtil {

    public static Map<String, String> createResponse(String message) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        Map<String, String> map = new HashMap<>();
        map.put("message", message);
        map.put("timestamp", timestamp.toString());
        return map;
    }
}
