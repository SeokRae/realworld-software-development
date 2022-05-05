package org.example.chapter_03.filter;

import org.example.chapter_03.domain.BankTransaction;

@FunctionalInterface
public interface BankTransactionFilter {
	boolean test(BankTransaction bankTransaction);
}