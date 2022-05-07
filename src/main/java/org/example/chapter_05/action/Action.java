package org.example.chapter_05.action;

import org.example.chapter_05.domain.Facts;

/**
 * Runnable 인터페이스를 사용하는 것보다, Action이라는 인터페이스 작성하는 것이 도메인을 반영할 수 있음
 */
@FunctionalInterface
public interface Action {
	void execute(Facts facts);
}
