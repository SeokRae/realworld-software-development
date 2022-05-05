package org.example.chapter_03.processor;

import org.example.chapter_03.domain.BankTransaction;
import org.example.chapter_03.domain.SummaryStatistics;
import org.example.chapter_03.filter.BankTransactionFilter;
import org.example.chapter_03.summerizer.BankTransactionSummarizer;

import java.time.Month;
import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;

public class BankStatementProcessor {
	private final List<BankTransaction> bankTransactions;

	public BankStatementProcessor(final List<BankTransaction> bankTransactions) {
		this.bankTransactions = bankTransactions;
	}

	public List<BankTransaction> findTransactionsGreaterThanEqual(final int amount) {
		return findTransactions(bankTransaction -> bankTransaction.getAmount() >= amount);
	}

	public List<BankTransaction> findTransactions(final BankTransactionFilter bankTransactionFilter) {
		final List<BankTransaction> result = new ArrayList<>();
		for (final BankTransaction bankTransaction : bankTransactions) {
			if (bankTransactionFilter.test(bankTransaction)) {
				result.add(bankTransaction);
			}
		}
		return result;
	}

	public double calculateTotalInMonth(final Month month) {
		return summarizeTransactions((acc, bankTransaction) ->
				bankTransaction.getDate().getMonth() == month ? acc + bankTransaction.getAmount() : acc);
	}

	public double summarizeTransactions(final BankTransactionSummarizer bankTransactionSummarizer) {
		double result = 0;
		for (final BankTransaction bankTransaction : bankTransactions) {
			result = bankTransactionSummarizer.summarize(result, bankTransaction);
		}
		return result;
	}

	public SummaryStatistics summarizeTransactions() {

		final DoubleSummaryStatistics doubleSummaryStatistics = bankTransactions.stream()
				.mapToDouble(BankTransaction::getAmount)
				.summaryStatistics();

		return new SummaryStatistics(doubleSummaryStatistics.getSum(),
				doubleSummaryStatistics.getMax(),
				doubleSummaryStatistics.getMin(),
				doubleSummaryStatistics.getAverage());
	}
}
