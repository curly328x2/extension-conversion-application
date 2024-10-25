package com.app.conversion.service;

public interface JpgFormatConversionService {

    /**
     * jpgから任意の拡張子へ変換する処理の実行
     *
     * @param extension 変換後拡張子
     */
    public void jpgFormatConversion(String extension);
}
