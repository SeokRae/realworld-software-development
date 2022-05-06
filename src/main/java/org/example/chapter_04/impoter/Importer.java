package org.example.chapter_04.impoter;

import org.example.chapter_04.domain.Document;

import java.io.File;
import java.io.IOException;

// tag::importer[]
public interface Importer {
	Document importFile(File file) throws IOException;
}
// end::importer[]
