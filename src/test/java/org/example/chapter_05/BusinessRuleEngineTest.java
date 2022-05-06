package org.example.chapter_05;

import org.example.chapter_05.domain.Facts;
import org.example.chapter_05.domain.Rule;
import org.example.chapter_05.domain.RuleBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BusinessRuleEngineTest {

	@DisplayName("전체 테스트")
	@Test
	void testCase1() {

		var env = new Facts();
		env.setFact("name", "Bob");
		env.setFact("jobTitle", "CEO");

		final var businessRuleEngine = new BusinessRuleEngine(env);

		final Rule ruleSendEmailToSalesWhenCEO =
				RuleBuilder
						.when(facts -> "CEO".equals(facts.getFact("jobTitle")))
						.then(facts -> {
							var name = facts.getFact("name");
							System.out.println("Relevant customer!!!: " + name);
						});

		businessRuleEngine.addRule(ruleSendEmailToSalesWhenCEO);
		businessRuleEngine.run();
	}
}