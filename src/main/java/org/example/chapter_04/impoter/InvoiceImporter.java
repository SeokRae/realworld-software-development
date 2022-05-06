package org.example.chapter_04.impoter;

import org.example.chapter_04.domain.Document;
import org.example.chapter_04.domain.TextFile;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import static org.example.chapter_04.domain.Attributes.AMOUNT;
import static org.example.chapter_04.domain.Attributes.TYPE;
import static org.example.chapter_04.domain.Attributes.PATIENT;

public class InvoiceImporter implements Importer {
	private static final String NAME_PREFIX = "Dear ";
	private static final String AMOUNT_PREFIX = "Amount: ";

	// tag::importFile[]
	@Override
	public Document importFile(final File file) throws IOException {
		final TextFile textFile = new TextFile(file);

		textFile.addLineSuffix(NAME_PREFIX, PATIENT);
		textFile.addLineSuffix(AMOUNT_PREFIX, AMOUNT);

		final Map<String, String> attributes = textFile.getAttributes();
		attributes.put(TYPE, "INVOICE");
		return new Document(attributes);
	}
	// end::importFile[]
}
