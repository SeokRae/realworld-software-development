package org.example.chapter_04.domain;

import java.util.Map;

/**
 * 상속 아닌 조합을 통한 기능 구현
 * Document 라는 객체를 구현함으로써 색인, 캐싱 기능을 구헌할 수 있다.
 */
// tag::document[]
public class Document {
	private final Map<String, String> attributes;

	public Document(final Map<String, String> attributes) {
		this.attributes = attributes;
	}

	public String getAttribute(final String attributeName) {
		return attributes.get(attributeName);
	}
}
// end::document[]
