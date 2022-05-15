package org.example.chapter_06.domain;

import java.util.Objects;

/**
 * 사용자가 시스템에서 발송한 하나의 메시지 인스턴스
 */
// Value object
public final class Twoot {
	private final String id;
	private final String senderId;
	private final String content;
	private final Position position;

	private Twoot(final String id, final String senderId, final String content, final Position position) {
		Objects.requireNonNull(id, "id");
		Objects.requireNonNull(senderId, "senderId");
		Objects.requireNonNull(content, "content");
		Objects.requireNonNull(position, "position");

		this.id = id;
		this.position = position;
		this.senderId = senderId;
		this.content = content;
	}

	public static Twoot of(final String id, final String senderId, final String content, final Position position) {
		return new Twoot(id, senderId, content, position);
	}

	public boolean isAfter(final Position otherPosition) {
		return position.getValue() > otherPosition.getValue();
	}

	public String getId() {
		return id;
	}

	public String getSenderId() {
		return senderId;
	}

	public String getContent() {
		return content;
	}

	public Position getPosition() {
		return position;
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		final Twoot twoot = (Twoot) o;

		return id.equals(twoot.id);
	}

	@Override
	public int hashCode() {
		return id.hashCode();
	}

	@Override
	public String toString() {
		return "Twoot{" +
				"id='" + id + '\'' +
				", senderId='" + senderId + '\'' +
				", content='" + content + '\'' +
				", position=" + position +
				'}';
	}
}
