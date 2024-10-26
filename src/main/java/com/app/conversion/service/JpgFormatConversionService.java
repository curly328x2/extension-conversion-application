package com.app.conversion.service;

import java.nio.file.Path;

public interface JpgFormatConversionService {

    /**
     * jpgから任意の拡張子へ変換する処理の実行
     */
    public Path jpgFormatConversion(String inputFilePath, String outputDirectory);
}
