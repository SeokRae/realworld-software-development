package org.example.chapter_05.action;

import org.example.chapter_05.domain.Facts;

@FunctionalInterface
public
interface Action {
	void execute(Facts facts);
}
