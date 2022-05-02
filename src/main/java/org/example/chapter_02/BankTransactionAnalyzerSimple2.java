package org.example.chapter_02;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class BankTransactionAnalyzerSimple2 {
	private static final DateTimeFormatter DATE_PATTERN = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	private static final String RESOURCES = "src/main/resources/";

	/**
	 * 특정 달에 몇 건의 입출금 내역이 발생했는가?
	 */
	public static void main(final String[] args) throws IOException {
		// Path 클래스는 파일 시스템의 경로를 가리킨다.
		final Path path = Paths.get(RESOURCES + "bank-data-simple.csv");
		// 파일 내에 모든 행을 가져온다.
		final List<String> lines = Files.readAllLines(path);
		double total = 0;

		for (final String line : lines) {
			final String[] columns = line.split(",");
			final LocalDate date = LocalDate.parse(columns[0], DATE_PATTERN);
			if (date.getMonth() == Month.JANUARY) {
				final double amount = Double.parseDouble(columns[1]);
				total += amount;
			}
		}

		System.out.println("The total for all transactions in January is " + total);
	}
}
