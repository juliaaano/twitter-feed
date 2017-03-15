package com.juliaaano.twitterfeed;

import org.apache.camel.builder.RouteBuilder;

import static com.juliaaano.twitterfeed.TwitterConfig.TWITTER_POLL_DELAY;
import static com.juliaaano.twitterfeed.TwitterConfig.TWITTER_TRACK_KEYWORD;
import static org.apache.camel.LoggingLevel.INFO;

public class TwitterFeedRouteBuilder extends RouteBuilder {

    @Override
    public void configure() {

        fromF("twitter://streaming/filter?type=polling&delay=%s&keywords=%s", TWITTER_POLL_DELAY, TWITTER_TRACK_KEYWORD)
            .routeId("twitter-polling-consumer")
            .routeDescription("Polls tweets with a given keyword using the Twitter Streaming API.")
            .to("seda:tweets");

        from("seda:tweets")
            .routeId("tweets-logger")
            .routeDescription("Logs the tweets.")
            .log(INFO, "${body}");
    }
}
