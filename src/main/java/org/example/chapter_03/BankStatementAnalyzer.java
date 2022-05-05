package org.example.chapter_03;

import org.example.chapter_03.domain.BankTransaction;
import org.example.chapter_03.domain.SummaryStatistics;
import org.example.chapter_03.exporter.Exporter;
import org.example.chapter_03.parser.BankStatementParser;
import org.example.chapter_03.processor.BankStatementProcessor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class BankStatementAnalyzer {
	private static final String RESOURCES = "src/main/resources/";

	public void analyze(
			final String fileName
			, final BankStatementParser bankStatementParser
			, final Exporter exporter
			) throws IOException {

		final Path path = Paths.get(RESOURCES + fileName);
		final List<String> lines = Files.readAllLines(path);

		final List<BankTransaction> bankTransactions = bankStatementParser.parseLinesFrom(lines);
		final BankStatementProcessor bankStatementProcessor = new BankStatementProcessor(bankTransactions);
		final SummaryStatistics summaryStatistics = bankStatementProcessor.summarizeTransactions();

		System.out.println(exporter.export(summaryStatistics));

	}
}