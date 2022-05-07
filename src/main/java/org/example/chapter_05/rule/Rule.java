package org.example.chapter_05.rule;

import org.example.chapter_05.domain.Facts;

@FunctionalInterface
public interface Rule {
	void perform(Facts facts);
}
