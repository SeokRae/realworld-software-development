package org.example.chapter_03.summerizer;

import org.example.chapter_03.domain.BankTransaction;

@FunctionalInterface
public interface BankTransactionSummarizer {
	double summarize(double accumulator, BankTransaction bankTransaction);
}

