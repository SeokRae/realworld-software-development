package org.example.chapter_06.client;


import org.example.chapter_06.domain.Position;
import org.example.chapter_06.domain.Twoot;

import java.util.List;
import java.util.function.BinaryOperator;

import static java.util.Comparator.comparingInt;
import static java.util.function.BinaryOperator.maxBy;
import static org.example.chapter_06.domain.Position.INITIAL_POSITION;

public class Reduce {

    // tag::ReduceExample[]
    private final BinaryOperator<Position> maxPosition = maxBy(comparingInt(Position::getValue));

    Twoot combineTwootsBy(final List<Twoot> twoots, final String senderId, final String newId) {
        return twoots
            .stream()
            .reduce(
                Twoot.of(newId, senderId, "", INITIAL_POSITION),
                (acc, twoot) -> Twoot.of(
                    newId,
                    senderId,
                    twoot.getContent() + acc.getContent(),
                    maxPosition.apply(acc.getPosition(), twoot.getPosition())));
    }
    // end::ReduceExample[]
}
