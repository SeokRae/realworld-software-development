package org.example.chapter_03.exporter;

import org.example.chapter_03.domain.SummaryStatistics;

public interface Exporter {
	String export(SummaryStatistics summaryStatistics);
}

