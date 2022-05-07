package org.example.chapter_05.condition;

import org.example.chapter_05.domain.Facts;

/**
 * Action의 수행 시점을 관리할 수 있는 기능 명세
 */
@FunctionalInterface
public interface Condition {
	boolean evaluate(Facts facts);
}
