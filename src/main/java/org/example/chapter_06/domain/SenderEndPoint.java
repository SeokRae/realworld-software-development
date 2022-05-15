package org.example.chapter_06.domain;

import org.example.chapter_06.domain.status.DeleteStatus;
import org.example.chapter_06.domain.status.FollowStatus;

import java.util.Objects;

/**
 * 사용자가 시스템으로 전송한 이벤트에 대응하는 메시지를 포함한다.
 */
// tag::SenderEndPoint[]
public class SenderEndPoint {
	private final User user;
	private final Twootr twootr;

	SenderEndPoint(final User user, final Twootr twootr) {
		Objects.requireNonNull(user, "user");
		Objects.requireNonNull(twootr, "twootr");

		this.user = user;
		this.twootr = twootr;
	}

	public FollowStatus onFollow(final String userIdToFollow) {
		Objects.requireNonNull(userIdToFollow, "userIdToFollow");

		return twootr.onFollow(user, userIdToFollow);
	}
// end::SenderEndPoint[]

	public Position onSendTwoot(final String id, final String content) {
		Objects.requireNonNull(content, "content");

		return twootr.onSendTwoot(id, user, content);
	}

	public void onLogoff() {
		user.onLogoff();
	}

	public DeleteStatus onDeleteTwoot(final String id) {
		return twootr.onDeleteTwoot(user.getId(), id);
	}

}
