package org.example.chapter_05.condition;

import org.example.chapter_05.domain.Facts;

public interface ConditionalAction {
	void perform(Facts facts);

	boolean evaluate(Facts facts);

}
