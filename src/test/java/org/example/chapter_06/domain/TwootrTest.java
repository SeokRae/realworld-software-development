package org.example.chapter_06.domain;

import org.example.chapter_06.memory.InMemoryTwootRepository;
import org.example.chapter_06.memory.InMemoryUserRepository;
import org.example.chapter_06.repository.TwootRepository;
import org.example.chapter_06.repository.UserRepository;
import org.example.chapter_06.domain.status.DeleteStatus;
import org.example.chapter_06.domain.status.FollowStatus;
import org.example.chapter_06.domain.status.RegistrationStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.example.chapter_06.domain.status.FollowStatus.ALREADY_FOLLOWING;
import static org.example.chapter_06.domain.status.FollowStatus.SUCCESS;
import static org.example.chapter_06.domain.TestData.TWOOT;
import static org.example.chapter_06.domain.TestData.twootAt;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.mock;

class TwootrTest {

	private static final Position POSITION_1 = new Position(0);

	// tag::mockReceiverEndPoint[]
	private final ReceiverEndPoint receiverEndPoint = mock(ReceiverEndPoint.class);
	// end::mockReceiverEndPoint[]

	private final TwootRepository twootRepository = spy(InMemoryTwootRepository.class);
	private final UserRepository userRepository = new InMemoryUserRepository();

	private Twootr twootr;
	private SenderEndPoint endPoint;

	@BeforeEach
	void setUp() {
		twootr = new Twootr(userRepository, twootRepository);

		assertEquals(RegistrationStatus.SUCCESS, twootr.onRegisterUser(TestData.USER_ID, TestData.PASSWORD));
		assertEquals(RegistrationStatus.SUCCESS, twootr.onRegisterUser(TestData.OTHER_USER_ID, TestData.PASSWORD));
	}

	@Test
	public void shouldNotRegisterDuplicateUsers() {
		assertEquals(RegistrationStatus.DUPLICATE, twootr.onRegisterUser(TestData.USER_ID, TestData.PASSWORD));
	}

	@Test
	public void shouldBeAbleToAuthenticateUser() {
		logon();
	}

	// tag::shouldNotAuthenticateUserWithWrongPassword[]
	@Test
	public void shouldNotAuthenticateUserWithWrongPassword() {
		final Optional<SenderEndPoint> endPoint = twootr.onLogon(
				TestData.USER_ID, "bad password", receiverEndPoint);

		assertThat(endPoint.isPresent()).isFalse();
	}
	// end::shouldNotAuthenticateUserWithWrongPassword[]

	@Test
	public void shouldNotAuthenticateUnknownUser() {
		final Optional<SenderEndPoint> endPoint = twootr.onLogon(
				TestData.NOT_A_USER, TestData.PASSWORD, receiverEndPoint);

		assertThat(endPoint.isPresent()).isFalse();
	}

	// tag::shouldFollowValidUser[]
	@Test
	public void shouldFollowValidUser() {
		logon();

		final FollowStatus followStatus = endPoint.onFollow(TestData.OTHER_USER_ID);

		assertEquals(SUCCESS, followStatus);
	}
	// end::shouldFollowValidUser[]

	// tag::shouldNotDuplicateFollowValidUser[]
	@Test
	public void shouldNotDuplicateFollowValidUser() {
		logon();

		endPoint.onFollow(TestData.OTHER_USER_ID);

		final FollowStatus followStatus = endPoint.onFollow(TestData.OTHER_USER_ID);
		assertThat(followStatus).isEqualTo(ALREADY_FOLLOWING);
	}
	// end::shouldNotDuplicateFollowValidUser[]

	@Test
	public void shouldNotFollowInValidUser() {
		logon();

		final FollowStatus followStatus = endPoint.onFollow(TestData.NOT_A_USER);

		assertThat(followStatus).isEqualTo(FollowStatus.INVALID_USER);
	}

	// tag::shouldReceiveTwootsFromFollowedUser[]
	@Test
	public void shouldReceiveTwootsFromFollowedUser() {
		final String id = "1";

		logon();

		endPoint.onFollow(TestData.OTHER_USER_ID);

		final SenderEndPoint otherEndPoint = otherLogon();
		otherEndPoint.onSendTwoot(id, TWOOT);

		verify(twootRepository).add(id, TestData.OTHER_USER_ID, TWOOT);
		verify(receiverEndPoint).onTwoot(Twoot.of(id, TestData.OTHER_USER_ID, TWOOT, new Position(0)));
	}
	// end::shouldReceiveTwootsFromFollowedUser[]

	@Test
	public void shouldNotReceiveTwootsAfterLogoff() {
		final String id = "1";

		userFollowsOtherUser();

		final SenderEndPoint otherEndPoint = otherLogon();
		otherEndPoint.onSendTwoot(id, TWOOT);

		verify(receiverEndPoint, never()).onTwoot(Twoot.of(id, TestData.OTHER_USER_ID, TWOOT, POSITION_1));
	}

	// tag::shouldReceiveReplayOfTwootsAfterLogoff[]
	@Test
	public void shouldReceiveReplayOfTwootsAfterLogoff() {
		final String id = "1";

		userFollowsOtherUser();

		final SenderEndPoint otherEndPoint = otherLogon();
		otherEndPoint.onSendTwoot(id, TWOOT);

		logon();

		verify(receiverEndPoint).onTwoot(twootAt(id, POSITION_1));
	}
	// end::shouldReceiveReplayOfTwootsAfterLogoff[]

	@Test
	public void shouldDeleteTwoots() {
		final String id = "1";

		userFollowsOtherUser();

		final SenderEndPoint otherEndPoint = otherLogon();
		otherEndPoint.onSendTwoot(id, TWOOT);
		final DeleteStatus status = otherEndPoint.onDeleteTwoot(id);

		logon();

		assertEquals(DeleteStatus.SUCCESS, status);
		verify(receiverEndPoint, never()).onTwoot(twootAt(id, POSITION_1));
	}

	@Test
	public void shouldNotDeleteFuturePositionTwoots() {
		logon();
		final DeleteStatus status = endPoint.onDeleteTwoot("DAS");
		assertEquals(DeleteStatus.UNKNOWN_TWOOT, status);
	}

	@Test
	public void shouldNotOtherUsersTwoots() {
		final String id = "1";

		logon();

		final SenderEndPoint otherEndPoint = otherLogon();
		otherEndPoint.onSendTwoot(id, TWOOT);

		final DeleteStatus status = endPoint.onDeleteTwoot(id);

		assertThat(twootRepository.get(id)).isPresent();
		assertEquals(DeleteStatus.NOT_YOUR_TWOOT, status);
	}

	private SenderEndPoint otherLogon() {
		return logon(TestData.OTHER_USER_ID, mock(ReceiverEndPoint.class));
	}

	private void userFollowsOtherUser() {
		logon();
		endPoint.onFollow(TestData.OTHER_USER_ID);
		endPoint.onLogoff();
	}

	private void logon() {
		this.endPoint = logon(TestData.USER_ID, receiverEndPoint);
	}

	private SenderEndPoint logon(final String userId, final ReceiverEndPoint receiverEndPoint) {
		final Optional<SenderEndPoint> endPoint = twootr.onLogon(userId, TestData.PASSWORD, receiverEndPoint);
		assertThat(endPoint).isPresent();
		return endPoint.get();
	}

}