package org.example.chapter_06.db;

import org.example.chapter_06.domain.Position;
import org.example.chapter_06.domain.User;
import org.junit.jupiter.api.*;

import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class StatementRunnerTest {

	private static final String INSERT_TWOOTS_ROW = "INSERT INTO users (id, password, salt, position) VALUES (?,?,?,?)";
	private StatementRunner runner;

	@BeforeEach
	void setUp() throws SQLException {
		Connection connection = DatabaseConnection.get();
		connection.createStatement()
				.executeUpdate(
						"CREATE TABLE IF NOT EXISTS " +
								"users (" +
								"id VARCHAR(15) NOT NULL," +
								"password VARBINARY(20) NOT NULL," +
								"salt VARBINARY(16) NOT NULL," +
								"position INT NOT NULL" +
								")");
		runner = new StatementRunner(connection);
	}

	@Test
	@Order(1)
	@DisplayName("데이터 INSERT 확인 테스트")
	void testCase1() {
		runner.withStatement(INSERT_TWOOTS_ROW, stmt -> {
			stmt.setString(1, "userId");
			stmt.setBytes(2, "userId".getBytes(StandardCharsets.UTF_8));
			stmt.setBytes(3, "salt".getBytes(StandardCharsets.UTF_8));
			stmt.setInt(4, new Position(1).getValue());
			stmt.executeUpdate();
		});
	}

	@Test
	@Order(2)
	@DisplayName("쿼리문에 대한 테스트")
	void testCase2() {
		Map<String, User> users = new HashMap<>();
		runner.query("SELECT * FROM users", resultSet -> {
			var id = resultSet.getString(1);
			var password = resultSet.getBytes(2);
			var salt = resultSet.getBytes(3);
			var position = new Position(resultSet.getInt(4));
			var user = new User(id, password, salt, position);
			users.put(id, user);
		});

		User userId = users.get("userId");
		assertThat(userId.getId()).isEqualTo("userId");
	}

	@Test
	@Order(2)
	@DisplayName("")
	void testCase3() {

	}
}