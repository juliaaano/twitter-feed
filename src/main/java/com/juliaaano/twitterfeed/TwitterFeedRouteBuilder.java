package com.juliaaano.twitterfeed;

import org.apache.camel.builder.RouteBuilder;

import static org.apache.camel.LoggingLevel.INFO;

public class TwitterFeedRouteBuilder extends RouteBuilder {

    @Override
    public void configure() {

        fromF("twitter://streaming/filter?type=polling&delay=%s&keywords=%s", "10000", "#POTUS")
            .routeId("twitter-polling-consumer")
            .routeDescription("Polls tweets with a given keyword using the Twitter Streaming API.")
            .to("seda:tweets");

        from("seda:tweets")
            .routeId("tweets-logger")
            .routeDescription("Logs out tweets from the queue.")
            .log(INFO, "${body}");
    }
}
