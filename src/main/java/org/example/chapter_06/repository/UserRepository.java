package org.example.chapter_06.repository;

import org.example.chapter_06.domain.User;
import org.example.chapter_06.domain.status.FollowStatus;

import java.util.Optional;

// tag::UserRepository[]
public interface UserRepository extends AutoCloseable {
	boolean add(User user);

	Optional<User> get(String userId);

	void update(User user);

	void clear();

	FollowStatus follow(User follower, User userToFollow);
}
// end::UserRepository[]
