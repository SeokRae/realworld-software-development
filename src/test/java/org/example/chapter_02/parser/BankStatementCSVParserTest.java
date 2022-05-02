package org.example.chapter_02.parser;

import org.example.chapter_02.domain.BankTransaction;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Month;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

class BankStatementCSVParserTest {

	private static final String RESOURCES = "src/main/resources/";

	@DisplayName("CSV 파서를 통한 데이터 조회 테스트")
	@Test
	void testCase1() throws IOException {
		final Path path = Paths.get(RESOURCES + "bank-data-simple.csv");
		// 파일 내에 모든 행을 가져온다.
		final List<String> lines = Files.readAllLines(path);

		final BankStatementCSVParser csvParser = new BankStatementCSVParser();
		final List<BankTransaction> bankTransactions = csvParser.parseLinesFrom(lines);

		assertThat(bankTransactions).isNotEmpty();
	}

	@DisplayName("입출금 내역 목록 처리 테스트")
	@Test
	void testCase2() throws IOException {
		final BankStatementCSVParser bankStatementParser = new BankStatementCSVParser();

		final Path path = Paths.get(RESOURCES + "bank-data-simple.csv");
		final List<String> lines = Files.readAllLines(path);

		final List<BankTransaction> bankTransactions = bankStatementParser.parseLinesFrom(lines);

		System.out.println("The total for all transactions is " + calculateTotalAmount(bankTransactions));
		System.out.println("Transactions in January " + selectInMonth(bankTransactions, Month.JANUARY));
	}

	private double calculateTotalAmount(final List<BankTransaction> bankTransactions) {
		return bankTransactions.stream()
				.mapToDouble(BankTransaction::getAmount)
				.sum();
	}

	private List<BankTransaction> selectInMonth(
			final List<BankTransaction> bankTransactions, final Month month) {
		return bankTransactions.stream()
				.filter(bankStatement -> month.equals(bankStatement.getDate().getMonth()))
				.collect(Collectors.toList());
	}
}