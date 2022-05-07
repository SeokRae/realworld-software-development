package org.example.chapter_05.domain;

import org.example.chapter_05.action.Action;
import org.example.chapter_05.condition.Condition;
import org.example.chapter_05.rule.Rule;

/**
 * 실행하려는 비즈니스 규칙을 지정 <br/>
 * 보통 Facts, Action 조건을 한 그룹으로 묶어 규칙을 만듦
 */
public class DefaultRule implements Rule {
	private final Condition condition;
	private final Action action;

	public DefaultRule(Condition condition, Action action) {
		this.condition = condition;
		this.action = action;
	}

	public void perform(Facts facts) {
		if (condition.evaluate(facts)) {
			action.execute(facts);
		}
	}
}
