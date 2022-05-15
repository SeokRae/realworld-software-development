package org.example.chapter_06.repository;

import org.example.chapter_06.domain.Twoot;
import org.example.chapter_06.domain.TwootQuery;

import java.util.Optional;
import java.util.function.Consumer;

// tag::TwootRepository[]
public interface TwootRepository {
	Twoot add(String id, String userId, String content);

	Optional<Twoot> get(String id);

	void delete(Twoot twoot);

	void query(TwootQuery twootQuery, Consumer<Twoot> callback);

	void clear();
}
// end::TwootRepository[]
