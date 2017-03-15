package com.juliaaano.twitterfeed;

import org.apache.camel.component.twitter.TwitterComponent;
import org.apache.camel.main.Main;
import org.slf4j.MDC;

import static com.juliaaano.twitterfeed.AsciiBanner.asciiBanner;
import static com.juliaaano.twitterfeed.TwitterConfig.*;

public class AppBootstrap {

    public static void main(String... args) throws Exception {

        MDC.put("Correlation-Id", "correlation-id");
        asciiBanner("application-ascii-banner.txt").ifPresent(AsciiBanner::print);

        final Main main = new Main();

        main.bind("twitter", twitterComponent());
        main.addRouteBuilder(new TwitterFeedRouteBuilder());

        main.run(args);
    }

    private static TwitterComponent twitterComponent() {

        final TwitterComponent twitter = new TwitterComponent();

        twitter.setConsumerKey(TWITTER_CONSUMER_KEY);
        twitter.setConsumerSecret(TWITTER_CONSUMER_SECRET);
        twitter.setAccessToken(TWITTER_ACCESS_TOKEN);
        twitter.setAccessTokenSecret(TWITTER_ACCESS_TOKEN_SECRET);

        return twitter;
    }
}
