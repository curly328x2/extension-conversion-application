package com.app.conversion.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings("resource")
public class JpgFormatConversionServiceImpl implements JpgFormatConversionService {

    /**
     * {@inheritDoc}
     */
    @Override
    public Path jpgFormatConversion(String inputFilePath, String outputDirectory) throws IOException {
        // フォルダ内のファイルを読み込む
        List<String> readFiles = readAllFiles(inputFilePath);
        return null;
    }

    /**
     * フォルダ内のすべてのファイルを読み込む
     *
     * @return 読み込まれたすべてのファイル
     */
    private List<String> readAllFiles(String inputFilePath) throws IOException {
        return Files.walk(Paths.get(inputFilePath))
                .filter(Files::isRegularFile)
                .map(Path::toString)
                .filter(string -> string.endsWith(".jpg"))
                .collect(Collectors.toList());
    }

}
