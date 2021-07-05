package com.areamode.shtrident.service;

import com.areamode.shtrident.data.model.Anison;
import com.areamode.shtrident.data.model.Program;

import java.io.IOException;
import java.nio.file.Path;

public interface AnisonService {
    void saveProgram(Path path) throws IOException;

    String getHash(Program program);
    String getHash(Anison anison);
}
