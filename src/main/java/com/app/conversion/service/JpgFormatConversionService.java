package com.app.conversion.service;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public interface JpgFormatConversionService {

    /**
     * jpgから任意の拡張子へ変換する処理の実行
     */
    public List<Path> jpgFormatConversion(String inputFilePath, String outputDirectory) throws IOException;
}
