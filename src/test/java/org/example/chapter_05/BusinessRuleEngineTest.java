package org.example.chapter_05;

import org.example.chapter_05.condition.Condition;
import org.example.chapter_05.domain.Facts;
import org.example.chapter_05.domain.DefaultRule;
import org.example.chapter_05.domain.RuleBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BusinessRuleEngineTest {

	private static final Condition CONDITION = facts -> "execute".equals(facts.getFact("test"));

	@DisplayName("비즈니스 워크플로우를 빌더 패턴으로 구현 테스트")
	@Test
	void testCase1() {

		var env = new Facts();
		env.setFact("name", "Bob");
		env.setFact("jobTitle", "CEO");

		final var businessRuleEngine = new BusinessRuleEngine(env);

		final DefaultRule defaultRuleSendEmailToSalesWhenCEO =
				RuleBuilder
						.when(facts -> "CEO".equals(facts.getFact("jobTitle")))
						.then(facts -> {
							var name = facts.getFact("name");
							System.out.println("Relevant customer!!!: " + name);
						});

		businessRuleEngine.addRule(defaultRuleSendEmailToSalesWhenCEO);
		businessRuleEngine.run();
	}

	@DisplayName("절차적인 프로그램에 대한 비즈니스 워크플로우 실행 테스트")
	@Test
	void testCase2() {
		// 비즈니스 규칙(key, value)에 대한 정보를 RuleEngine 에 주입하여 수행
		Facts facts = new Facts();
		facts.setFact("test", "execute");
		facts.setFact("prod", "");
		BusinessRuleEngine businessRuleEngine = new BusinessRuleEngine(facts);

		DefaultRule defaultRule = new DefaultRule(
				facts1 -> "execute".equals(facts1.getFact("test"))
				, facts12 -> System.out.println("facts = " + facts12.getFact("test")));
		businessRuleEngine.addRule(defaultRule);
		businessRuleEngine.run();
	}

	@DisplayName("절차적인 워크플로우 빌더로 실행 테스트")
	@Test
	void testCase3() {
		Facts facts = new Facts();
		facts.setFact("test", "execute");
		facts.setFact("prod", "");
		BusinessRuleEngine businessRuleEngine = new BusinessRuleEngine(facts);

		DefaultRule defaultRule = RuleBuilder
				.when(CONDITION)
				.then(facts1 -> System.out.println("facts = " + facts1.getFact("test")));
		businessRuleEngine.addRule(defaultRule);
		businessRuleEngine.run();
	}
}