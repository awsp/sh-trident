package com.areamode.shtrident.domain.enumeration;

import java.util.HashMap;
import java.util.Map;

/**
 * Song Code
 *
 * 参照: http://anison.info/article/info/manual.pdf
 */
public enum SongCode {
    OP("オープニング"),
    ED("エンディング"),
    TM("テーマソング"),
    NO("オープニング・エンディングに曲はなし"),
    IN("挿入歌"),
    IM("イメージソング"),
    KK("企画もの"),
    CF("CMソング"),
    UK("分類不明"),
    AR("カバー曲"),
    KA("カラオケ配信専用"),
    GE("一般曲"),
    HM("オマージュ曲(非公式関連曲)");

    private final String metaData;
    private final static Map<String, SongCode> BY_METADATA = new HashMap<>();

    static {
        for (SongCode songCode : values()) {
            BY_METADATA.put(songCode.metaData, songCode);
        }
    }

    SongCode(final String metaData) {
        this.metaData = metaData;
    }

    public static SongCode valueByMetadata(final String key) {
        return BY_METADATA.getOrDefault(key, SongCode.UK);
    }

}