package org.example.chapter_05;

import org.example.chapter_05.domain.Facts;
import org.example.chapter_05.domain.Rule;

import java.util.ArrayList;
import java.util.List;

public class BusinessRuleEngine {

	private final List<Rule> rules;
	private final Facts facts;

	public BusinessRuleEngine(Facts facts) {
		this.facts = facts;
		this.rules = new ArrayList<>();
	}

	public void addRule(Rule rule) {
		this.rules.add(rule);
	}

	public void run() {
		this.rules.forEach(rule -> rule.perform(facts));
	}
}
