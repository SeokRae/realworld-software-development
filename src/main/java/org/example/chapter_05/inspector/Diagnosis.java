package org.example.chapter_05.inspector;

import org.example.chapter_05.condition.ConditionalAction;
import org.example.chapter_05.domain.Facts;

public class Diagnosis {

	private final ConditionalAction conditionalAction;
	private final Facts facts;
	private final boolean isPositive;

	public Diagnosis(final Facts facts,
	                 final ConditionalAction conditionalAction,
	                 final boolean isPositive) {
		this.facts = facts;
		this.conditionalAction = conditionalAction;
		this.isPositive = isPositive;
	}

	public ConditionalAction getConditionalAction() {
		return conditionalAction;
	}

	public Facts getFacts() {
		return facts;
	}

	public boolean isPositive() {
		return isPositive;
	}

	@Override
	public String toString() {
		return "Diagnosis{" +
				"conditionalAction=" + conditionalAction +
				", facts=" + facts +
				", result=" + isPositive +
				'}';
	}
}
