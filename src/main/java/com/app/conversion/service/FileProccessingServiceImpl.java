package com.app.conversion.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@SuppressWarnings("resource")
@Service
public class FileProccessingServiceImpl implements FileProccessingService {

    private static final Logger logger = LoggerFactory.getLogger(FileProccessingServiceImpl.class);

    private final JpgFormatConversionService jpgFormatConversionService;

    public FileProccessingServiceImpl(JpgFormatConversionService jpgFormatConversionService) {
        this.jpgFormatConversionService = jpgFormatConversionService;
    }

    public void processFiles(String inputDirectory, String outputDirectory) {
        try {
            List<String> inputFiles = Files.walk(Paths.get(inputDirectory))
                    .filter(Files::isRegularFile)
                    .filter(path -> path.toString().endsWith(".jpg"))
                    .map(Path::toString)
                    .toList();

            for (String filePath : inputFiles) {
                List<Path> convertedFiles = jpgFormatConversionService.jpgFormatConversion(filePath, outputDirectory);
                logger.info("変換が完了しました - 元のファイル: {}, 変換後のファイル: {}", filePath, convertedFiles);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
