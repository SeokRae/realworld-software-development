package org.example.chapter_04.impoter;

import org.example.chapter_04.domain.Document;
import org.example.chapter_04.domain.TextFile;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import static org.example.chapter_04.domain.Attributes.BODY;
import static org.example.chapter_04.domain.Attributes.PATIENT;
import static org.example.chapter_04.domain.Attributes.TYPE;

public class ReportImporter implements Importer {
	private static final String NAME_PREFIX = "Patient: ";

	@Override
	public Document importFile(final File file) throws IOException {
		final TextFile textFile = new TextFile(file);
		textFile.addLineSuffix(NAME_PREFIX, PATIENT);
		textFile.addLines(2, line -> false, BODY);

		final Map<String, String> attributes = textFile.getAttributes();
		attributes.put(TYPE, "REPORT");
		return new Document(attributes);
	}
}
