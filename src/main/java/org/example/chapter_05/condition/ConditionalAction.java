package org.example.chapter_05.condition;

import org.example.chapter_05.domain.Facts;
import org.example.chapter_05.rule.Rule;

/**
 * LSP 를 위반하는 인터페이스
 */
public interface ConditionalAction extends Rule {
	boolean evaluate(Facts facts);
}
