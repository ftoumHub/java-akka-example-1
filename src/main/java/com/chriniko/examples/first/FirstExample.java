package com.chriniko.examples.first;

import akka.actor.AbstractLoggingActor;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.japi.pf.ReceiveBuilder;

public class FirstExample {


    public static void main(String[] args) {

        ActorSystem actorSystem = ActorSystem.create("first-example");

        ActorRef greeterActor = actorSystem.actorOf(Greeter.props(), "greeter-1");

        greeterActor.tell(new Greet(), ActorRef.noSender());
        greeterActor.tell(new Greet(), ActorRef.noSender());
        greeterActor.tell(new Greet(), ActorRef.noSender());
        greeterActor.tell(new Greet(), ActorRef.noSender());

        greeterActor.tell(new Foo(), ActorRef.noSender());


        try {
            Thread.sleep(10000);
            actorSystem.terminate();
        } catch (InterruptedException e) {
            e.printStackTrace(System.err);
        }
    }

    // ---- ACTORS DEFINITION ----
    static class Greeter extends AbstractLoggingActor {

        private int counter = 0;
        private final Receive receive;

        public Greeter() {

            receive = ReceiveBuilder
                    .create()
                    .match(Greet.class, this::onGreet)
                    .matchAny(__ -> fallback())
                    .build();
        }

        @Override
        public Receive createReceive() {
            return receive;
        }

        private void onGreet(Greet greet) {
            counter++;
            log().info("Hello there, counter is: " + counter);
        }

        private void fallback() {
            log().info("Fallback method called!");
        }

        public static Props props() {
            return Props.create(Greeter.class);
        }
    }


    // ---- MESSAGES DEFINITION ----
    static class Greet {
        // Note: no data for the moment.
    }

    static class Foo {
        // Note: no data for the moment.
    }

}
