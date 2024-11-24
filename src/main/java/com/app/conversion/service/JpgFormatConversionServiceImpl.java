package com.app.conversion.service;

import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("resource")
@Service
public class JpgFormatConversionServiceImpl implements JpgFormatConversionService {

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Path> jpgFormatConversion(String inputFilePath, String outputDirectory) throws IOException {
        List<Path> convertedFiles = new ArrayList<>();

        // JPG -> PNG
        convertedFiles.add(convertJpgFileToPng(inputFilePath, outputDirectory));

        // JPG -> TIF
        convertedFiles.add(covertJpgFileToTif(inputFilePath, outputDirectory));

        return convertedFiles;
    }

    /**
     * jpgから拡張子を変換する処理(jpg -> png)
     *
     * @param jpgFilePath jpg格納先
     * @param outputDirectory 結果出力先
     * @return 結果が格納されているパスを返す
     * @throws IOException
     */
    private Path convertJpgFileToPng(String jpgFilePath, String outputDirectory) throws IOException {
        // 入力ファイルの読み込み
        File inputFile = new File(jpgFilePath);
        BufferedImage image = ImageIO.read(inputFile);
        if (image == null) {
            throw new IOException("JPGファイルの読み込みに失敗しました: " + jpgFilePath);
        }
        // 出力ファイル名を生成
        String outputFileName = inputFile.getName().replaceFirst("[.][^.]+$", "") + ".png";
        File outputFile = new File(outputDirectory, outputFileName);

        // 指定された形式で画像を出力
        ImageIO.write(image, "png", outputFile);

        return outputFile.toPath();
    }

    /**
     * jpgから拡張子を変換する処理(jpg -> tif)
     * @param jpgFilePath jpg格納先
     * @param outputDirectory 結果出力先
     * @return tifに変換したファイルのパス
     */
    private Path covertJpgFileToTif(String jpgFilePath, String outputDirectory) throws IOException {
        // jpgからtifに変換する処理
        File inputFile = new File(jpgFilePath);
        BufferedImage image = ImageIO.read(inputFile);

        if (image == null) {
            throw new IOException("JPGファイルの読み込みに失敗しました: " + jpgFilePath);
        }

        // 出力ファイル名
        String outputFileName = inputFile.getName().replaceFirst("[.][^.]+$", "") + ".tif";
        File outputFile = new File(outputDirectory, outputFileName);

        // tif形式での出力
        ImageIO.write(image, "tiff", outputFile);
        return outputFile.toPath();
    }

}
