package com.Vilingo.util;

import java.util.Map;
import java.util.HashMap;

// 你可以放在 com.Vilingo.util 或 com.Vilingo.config 包下
public class FileMapping {

    // 静态 Map 模拟数据库映射：id -> fileName
    private static final Map<String, String> FILE_MAP = new HashMap<>();

    static {
        FILE_MAP.put("1", "Rethinking infidelity ... a talk for anyone who has ever loved-English.srt");
        FILE_MAP.put("2", "example2.json");
        FILE_MAP.put("3", "example3.json");
    }

    public static String getFileNameById(String id) {
        return FILE_MAP.get(id);
    }
}
