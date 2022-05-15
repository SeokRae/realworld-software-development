package org.example.chapter_06.domain;

import org.example.chapter_06.domain.KeyGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("KeyGenerator 클래스")
class KeyGeneratorTest {

	private byte[] newSalt;

	@BeforeEach
	void setUp() {
		newSalt = KeyGenerator.newSalt();
	}

	@Nested
	@DisplayName("Salt")
	class Salt {
		@Test
		void testCase1() {
			System.out.println("newSalt = " + Arrays.toString(newSalt));
		}
	}
	@Nested
	@DisplayName("해싱")
	class Hash {
		@DisplayName("값 비교 테스트")
		@Test
		void testCase1() {
			byte[] actual = KeyGenerator.hash("1234", newSalt);
			byte[] expected = KeyGenerator.hash("1234", newSalt);
			assertThat(actual).isEqualTo(expected);
		}
	}

}