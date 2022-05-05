package org.example.chapter_03.validator;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Objects;

public class SimpleBankStatementValidator {
	private final String description;
	private final String date;
	private final String amount;

	public SimpleBankStatementValidator(
			final String description, final String date, final String amount) {
		this.description = Objects.requireNonNull(description);
		this.date = Objects.requireNonNull(date);
		this.amount = Objects.requireNonNull(amount);
	}

	public boolean validate() {
		if (this.description.length() > 100) {
			throw new IllegalArgumentException("The description is too long");
		}
		final LocalDate parsedDate;
		try {
			parsedDate = LocalDate.parse(this.date);
		} catch (DateTimeParseException e) {
			throw new IllegalArgumentException("Invalid format for date", e);
		}
		if (parsedDate.isAfter(LocalDate.now())) throw new IllegalArgumentException("date cannot be in the future");
		try {
			Double.parseDouble(this.amount);
		} catch (NumberFormatException e) {
			throw new IllegalArgumentException("Invalid format for amount", e);
		}
		return true;
	}
}
