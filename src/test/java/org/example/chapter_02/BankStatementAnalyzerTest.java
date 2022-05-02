package org.example.chapter_02;

import org.example.chapter_02.parser.BankStatementCSVParser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class BankStatementAnalyzerTest {

	@DisplayName("응집도 높은 객체 분리")
	@Test
	void testCase1() throws IOException {
		BankStatementAnalyzer analyzer = new BankStatementAnalyzer();
		analyzer.analyze("bank-data-simple.csv", new BankStatementCSVParser());
	}
}