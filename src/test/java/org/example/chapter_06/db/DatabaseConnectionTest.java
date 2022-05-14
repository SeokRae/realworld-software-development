package org.example.chapter_06.db;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;

class DatabaseConnectionTest {

	@DisplayName("데이터베이스 접근 테스트")
	@Test
	void testCase1() throws SQLException {
		Connection connection = DatabaseConnection.get();
		assertThat(connection).isNotNull();
	}
}