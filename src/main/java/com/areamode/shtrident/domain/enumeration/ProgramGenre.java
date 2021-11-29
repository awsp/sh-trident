package com.areamode.shtrident.domain.enumeration;

import com.fasterxml.jackson.annotation.JsonValue;

import java.util.HashMap;
import java.util.Map;

/**
 * Program Genre
 * <p>
 * 参照: http://anison.info/article/info/manual.pdf
 */
public enum ProgramGenre {
    TV("テレビアニメーション"),
    TS("テレビスペシャル(特番)"),
    VD("オリジナルビデオアニメーション"),
    MV("劇場用アニメーション"),
    WA("Webアニメーション"),
    SF("テレビ特撮"),
    SS("テレビ特撮スペシャル(特番)"),
    SV("オリジナル特撮ビデオ"),
    SM("劇場用特撮"),
    WS("Web特撮"),
    RD("ラジオ"),
    WR("Webラジオ"),
    GM("ゲーム"),
    KK("企画もの"),
    NG("人形劇"),
    DR("ドラマ"),
    DM("一般映画"),
    DV("ビデオ用ドラマ"),
    JD("時代劇"),
    JM("劇場用時代劇"),
    JV("ビデオ用時代劇"),
    OT("その他"),
    UK("不明"),
    GE("一般"),
    CF("広告"),
    DK("同人企画もの"),
    VA("バラエティ"),
    WV("Webバラエティ"),
    KIDS("子供向け情報/バラエティ"),
    SPT("スポーツ番組"),
    CL("カルチャー番組"),
    EV("イベント/舞台/公演");

    private final String genreName;
    private final static Map<String, ProgramGenre> BY_VALUE = new HashMap<>();

    static {
        for (ProgramGenre programGenre : values()) {
            BY_VALUE.put(programGenre.genreName, programGenre);
        }
    }

    ProgramGenre(final String genreName) {
        this.genreName = genreName;
    }

    public static ProgramGenre valueByName(final String key) {
        return BY_VALUE.getOrDefault(key, ProgramGenre.OT);
    }

    @JsonValue
    public String getGenreName() {
        return genreName;
    }
}