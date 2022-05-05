package org.example.chapter_03.validator;

import org.example.chapter_03.exception.DateInTheFutureException;
import org.example.chapter_03.exception.DescriptionTooLongException;
import org.example.chapter_03.exception.InvalidAmountException;
import org.example.chapter_03.exception.InvalidDateFormat;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Objects;

public class OverlySpecificBankStatementValidator {
	private final String description;
	private final String date;
	private final String amount;

	public OverlySpecificBankStatementValidator(
			final String description, final String date, final String amount) {
		this.description = Objects.requireNonNull(description);
		this.date = Objects.requireNonNull(date);
		this.amount = Objects.requireNonNull(amount);
	}

	/**
	 * 과도한 커스텀 예외 처리
	 *
	 * @return 정상적인 데이터의 경우 true 반환
	 * @throws DescriptionTooLongException
	 * @throws InvalidDateFormat
	 * @throws DateInTheFutureException
	 * @throws InvalidAmountException
	 */
	public boolean validate() throws DescriptionTooLongException, InvalidDateFormat, DateInTheFutureException, InvalidAmountException {
		if (this.description.length() > 100) {
			throw new DescriptionTooLongException();
		}
		final LocalDate parsedDate;
		try {
			parsedDate = LocalDate.parse(this.date);
		} catch (DateTimeParseException e) {
			throw new InvalidDateFormat();
		}
		if (parsedDate.isAfter(LocalDate.now())) throw new DateInTheFutureException();
		try {
			Double.parseDouble(this.amount);
		} catch (NumberFormatException e) {
			throw new InvalidAmountException();
		}
		return true;
	}
}
