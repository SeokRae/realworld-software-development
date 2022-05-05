package org.example.chapter_03.validator;

import org.example.chapter_03.notification.Notification;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class NotificationBankStatementValidator {
	private final String description;
	private final String date;
	private final String amount;

	public NotificationBankStatementValidator(String description, String date, String amount) {
		this.description = description;
		this.date = date;
		this.amount = amount;
	}

	public Notification validate() {
		final Notification notification = new Notification();
		if (this.description.length() > 100) {
			notification.addError("The description is too long");
		}
		final LocalDate parsedDate;
		try {
			parsedDate = LocalDate.parse(this.date);
			if (parsedDate.isAfter(LocalDate.now()))
				notification.addError("Invalid format for date");
		} catch (DateTimeParseException e) {
			notification.addError("date cannot be in the future");
		}

		try {
			Double.parseDouble(this.amount);
		} catch (NumberFormatException e) {
			notification.addError("Invalid format for amount");
		}
		return notification;
	}
}
