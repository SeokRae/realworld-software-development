package org.example.chapter_05.condition;

import org.example.chapter_05.domain.Facts;

@FunctionalInterface
public interface Condition {

	boolean evaluate(Facts facts);
}
