package org.example.chapter_05;

import org.example.chapter_05.domain.DefaultRule;
import org.example.chapter_05.domain.Facts;

import java.util.ArrayList;
import java.util.List;

/**
 * 비즈니스의 규칙에 따른 워크플로우를 수행하는 클래스
 */
public class BusinessRuleEngine {

	private final List<DefaultRule> defaultRules;

	private final Facts facts;

	public BusinessRuleEngine(Facts facts) {
		this.facts = facts;
		this.defaultRules = new ArrayList<>();
	}

	public void addRule(DefaultRule defaultRule) {
		this.defaultRules.add(defaultRule);
	}

	public void run() {
		this.defaultRules.forEach(defaultRule -> defaultRule.perform(facts));
	}
}
