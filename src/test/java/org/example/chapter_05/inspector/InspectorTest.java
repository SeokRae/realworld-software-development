package org.example.chapter_05.inspector;

import org.example.chapter_05.condition.ConditionalAction;
import org.example.chapter_05.domain.Facts;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class InspectorTest {

	@Test
	void inspectOneConditionEvaluatesTrue() {

		final Facts facts = new Facts();
		facts.setFact("jobTitle", "CEO");
		final ConditionalAction conditionalAction = new JobTitleCondition();
		final Inspector inspector = new Inspector(conditionalAction);

		final List<Diagnosis> diagnosisList = inspector.inspect(facts);

		assertThat(diagnosisList).hasSize(1);
		assertThat(diagnosisList.get(0).isPositive()).isTrue();
	}

	private static class JobTitleCondition implements ConditionalAction {

		@Override
		public void perform(Facts facts) {
			throw new UnsupportedOperationException();
		}

		@Override
		public boolean evaluate(Facts facts) {
			return "CEO".equals(facts.getFact("jobTitle"));
		}
	}
}