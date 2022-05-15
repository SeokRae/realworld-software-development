package org.example.chapter_06.client;

import org.example.chapter_06.domain.ReceiverEndPoint;
import org.example.chapter_06.domain.Twoot;

// tag::PrintingEndPoint[]
public class PrintingEndPoint implements ReceiverEndPoint {
    @Override
    public void onTwoot(final Twoot twoot) {
        System.out.println(twoot.getSenderId() + ": " + twoot.getContent());
    }
}
// end::PrintingEndPoint[]
