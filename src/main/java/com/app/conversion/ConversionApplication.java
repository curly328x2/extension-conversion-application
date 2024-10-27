package com.app.conversion;

import com.app.conversion.service.JpgFormatConversionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.nio.file.Path;
import java.util.List;

@SpringBootApplication
public class ConversionApplication implements CommandLineRunner {

	/** 対象ファイルの格納先パス */
	private static final String INPUT_DIR = "src/main/resources/input";

	/** 変換後のファイル格納先パス */
	private static final String OUTPUT_DIR = "src/main/resources/output";

	private final JpgFormatConversionService jpgFormatConversionService;

	@Autowired
	public ConversionApplication(JpgFormatConversionService jpgFormatConversionService) {
		this.jpgFormatConversionService = jpgFormatConversionService;
	}

	public static void main(String[] args) {
		SpringApplication.run(ConversionApplication.class, args);
	}

	@Override
	public void run(String... args) {
		// 変換する元ファイルと出力フォルダ、変換先のフォーマットを指定

		try {
			// 画像を変換
			List<Path> convertedFilePath = jpgFormatConversionService.jpgFormatConversion(INPUT_DIR, OUTPUT_DIR);
			System.out.println("変換が完了しました！保存先: " + convertedFilePath);
		} catch (Exception e) {
			System.err.println("変換中にエラーが発生しました: " + e.getMessage());
		}
	}

}
