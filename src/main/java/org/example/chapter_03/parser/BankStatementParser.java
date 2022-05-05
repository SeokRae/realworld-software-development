package org.example.chapter_03.parser;


import org.example.chapter_03.domain.BankTransaction;

import java.util.List;

public interface BankStatementParser {
	BankTransaction parseFrom(String line);

	List<BankTransaction> parseLinesFrom(List<String> lines);
}
