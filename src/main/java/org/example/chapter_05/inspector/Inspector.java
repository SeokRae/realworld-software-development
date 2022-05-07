package org.example.chapter_05.inspector;

import org.example.chapter_05.condition.ConditionalAction;
import org.example.chapter_05.domain.Facts;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 비즈니스 워크플로우에 대한 액션과 조건을 검사할 수 있는 클래스
 */
public class Inspector {

	private final List<ConditionalAction> conditionalActionList;

	public Inspector(final ConditionalAction... conditionalActions) {
		this.conditionalActionList = Arrays.asList(conditionalActions);
	}

	public List<Diagnosis> inspect(final Facts facts) {
		final List<Diagnosis> diagnosisList = new ArrayList<>();
		for (ConditionalAction conditionalAction : conditionalActionList) {
			final boolean conditionResult = conditionalAction.evaluate(facts);
			final Diagnosis diagnosis = new Diagnosis(facts, conditionalAction, conditionResult);
			diagnosisList.add(diagnosis);
		}
		return diagnosisList;
	}
}
