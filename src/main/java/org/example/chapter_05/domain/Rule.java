package org.example.chapter_05.domain;

import org.example.chapter_05.action.Action;
import org.example.chapter_05.condition.Condition;

public class Rule {

	private final Condition condition;
	private final Action action;

	public Rule(Condition condition, Action action) {
		this.condition = condition;
		this.action = action;
	}

	public void perform(Facts facts) {
		if (condition.evaluate(facts)) {
			action.execute(facts);
		}
	}
}
