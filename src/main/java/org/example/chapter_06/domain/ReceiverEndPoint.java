package org.example.chapter_06.domain;

import org.example.chapter_06.domain.Twoot;

/**
 * Adapter interface for pushing information out to a UI port.
 */
// tag::ReceiverEndPoint[]
public interface ReceiverEndPoint {
	void onTwoot(Twoot twoot);
}
// end::ReceiverEndPoint[]
