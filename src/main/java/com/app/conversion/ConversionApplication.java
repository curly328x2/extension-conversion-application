package com.app.conversion;

import com.app.conversion.service.FileProccessingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ConversionApplication implements CommandLineRunner {

	/** 対象ファイルの格納先パス */
	private static final String INPUT_DIR = "src/main/resources/input";

	/** 変換後のファイル格納先パス */
	private static final String OUTPUT_DIR = "src/main/resources/output";

	private final FileProccessingService fileProccessingService;

	@Autowired
	public ConversionApplication(FileProccessingService fileProccessingService) {
		this.fileProccessingService = fileProccessingService;
	}

	public static void main(String[] args) {
		SpringApplication.run(ConversionApplication.class, args);
	}

	@Override
	public void run(String... args) {
		// 変換する元ファイルと出力フォルダ、変換先のフォーマットを指定
		fileProccessingService.processFiles(INPUT_DIR, OUTPUT_DIR);
	}

}
