package org.example.chapter_06.domain;

import org.example.chapter_06.domain.Position;
import org.example.chapter_06.domain.TestData;
import org.example.chapter_06.domain.Twoot;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Twoot 클래스")
class TwootTest {

	private Twoot actual;

	@BeforeEach
	void setUp() {
		actual = Twoot.of("id", "targetId", "contents", new Position(1));
	}

	@Nested
	@DisplayName("다양한 방법")
	class ContextTwoot {
		@DisplayName("(팩토리 메서드 패턴)으로 생성")
		@Test
		void testCase1() {
			final Twoot expected = TestData.twootAt("id", new Position(1));
			assertThat(actual).isEqualTo(expected);
		}
	}

	@Nested
	@DisplayName("객체 간 Position 비교")
	class CompareTwootPosition {

		@DisplayName("source(1) < target = false")
		@Test
		void testCase1() {
			boolean expected = actual.isAfter(new Position(2));
			assertThat(expected).isFalse();
		}

		@DisplayName("source(1) > target = true")
		@Test
		void testCase2() {
			boolean expected = actual.isAfter(new Position(0));
			assertThat(expected).isTrue();
		}
	}

}