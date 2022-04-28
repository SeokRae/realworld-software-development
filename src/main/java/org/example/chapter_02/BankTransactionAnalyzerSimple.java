package org.example.chapter_02;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class BankTransactionAnalyzerSimple {

	private static final String RESOURCES = "src/main/resources/";

	public static void main(final String[] args) throws IOException {
		// Path 클래스는 파일 시스템의 경로를 가리킨다.
		final Path path = Paths.get(RESOURCES + "bank-data-simple.csv");
		// 파일 내에 모든 행을 가져온다.
		final List<String> lines = Files.readAllLines(path);
		double total = 0;
		for (final String line : lines) {
			String[] columns = line.split(",");
			// 각 라인의 금액을 추출
			double amount = Double.parseDouble(columns[1]);
			// 집계 처리
			total += amount;
		}

		System.out.println("The total for all transactions is " + total);
	}
}
