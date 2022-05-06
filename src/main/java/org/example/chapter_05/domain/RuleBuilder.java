package org.example.chapter_05.domain;

import org.example.chapter_05.action.Action;
import org.example.chapter_05.condition.Condition;

public class RuleBuilder {
	private final Condition condition;

	private RuleBuilder(Condition condition) {
		this.condition = condition;
	}

	public static RuleBuilder when(Condition condition) {
		return new RuleBuilder(condition);
	}

	public Rule then(Action action) {
		return new Rule(condition, action);
	}
}