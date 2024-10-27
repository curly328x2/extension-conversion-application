package com.app.conversion.service;

import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings("resource")
@Service
public class JpgFormatConversionServiceImpl implements JpgFormatConversionService {

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Path> jpgFormatConversion(String inputFilePath, String outputDirectory) throws IOException {
        String outputExtension = "png";

        // フォルダ内のファイルを読み込む
        List<String> readFiles = readAllFiles(inputFilePath);

        return readFiles.stream().map(jpgFilePath -> {
            try {
                return convertJpgFileToFormat(jpgFilePath, outputExtension, outputDirectory);
            } catch (IOException e) {
                System.err.println("ファイルの変換に失敗しました: " + jpgFilePath);
                return null;
            }
        }).collect(Collectors.toList());
    }

    /**
     * jpgから拡張子を変換する処理(jpg -> png)
     *
     * @param jpgFilePath jpg格納先
     * @param outputExtension 変換したい拡張子
     * @param outputDirectory 結果出力先
     * @return 結果が格納されているパスを返す
     * @throws IOException
     */
    private Path convertJpgFileToFormat(String jpgFilePath, String outputExtension, String outputDirectory) throws IOException {
        // 入力ファイルの読み込み
        File inputFile = new File(jpgFilePath);
        BufferedImage image = ImageIO.read(inputFile);
        if (image == null) {
            throw new IOException("JPGファイルの読み込みに失敗しました: " + jpgFilePath);
        }
        // 出力ファイル名を生成
        String outputFileName = inputFile.getName().replaceFirst("[.][^.]+$", "") + "." + outputExtension;
        File outputFile = new File(outputDirectory, outputFileName);

        // 指定された形式で画像を出力
        ImageIO.write(image, outputExtension, outputFile);

        return outputFile.toPath();
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
