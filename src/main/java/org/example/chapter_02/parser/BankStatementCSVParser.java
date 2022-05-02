package org.example.chapter_02.parser;

import org.example.chapter_02.domain.BankTransaction;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class BankStatementCSVParser implements BankStatementParser {

    private static final DateTimeFormatter DATE_PATTERN = DateTimeFormatter.ofPattern("dd-MM-yyyy");

	/**
	 * 데이터 누적
	 * @param lines
	 * @return
	 */
	public List<BankTransaction> parseLinesFrom(final List<String> lines) {
		final List<BankTransaction> list = new ArrayList<>();
		for (String line : lines) {
			list.add(parseFrom(line));
		}
		return list;
	}

	/**
	 * 데이터 파싱
	 * @param line
	 * @return
	 */
    public BankTransaction parseFrom(final String line) {
        final String[] columns = line.split(",");

        final LocalDate date = LocalDate.parse(columns[0], DATE_PATTERN);
        final double amount = Double.parseDouble(columns[1]);
	    final String description = columns[2];

	    return new BankTransaction(date, amount, description);
    }
}
