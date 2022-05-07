package org.example.chapter_05.domain;

import org.example.chapter_05.action.Action;
import org.example.chapter_05.condition.Condition;

public class RuleBuilder {

	private final Condition condition;

	private RuleBuilder(Condition condition) {
		this.condition = condition;
	}

	/**
	 * 메서드를 정적 팩토리 메서드로 제공하여 인스턴스 생성에 대한 책임을 짐<br/>
	 * 메서드 체이닝을 통해 Rule 객체를 설정하기 위해서 어떠한 메서드를 이용해야하는지 가이드
	 *
	 * @param condition
	 * 		비즈니스 수행 규칙에 대한 로직을 주입
	 * @return 메서드 체이닝을 통한 객체 생성을 돕기 위해 RuleBuilder를 반환
	 */
	public static RuleBuilder when(Condition condition) {
		return new RuleBuilder(condition);
	}

	/**
	 * DefaultRule에 대한 최종 생성을 책임
	 *
	 * @param action
	 * 		실행할 연산이나 코드 집합에 대한 로직을 주입
	 * @return 최종적인 Rule을 반환
	 */
	public DefaultRule then(Action action) {
		return new DefaultRule(condition, action);
	}
}