package com.juliaaano.twitterfeed;

import org.apache.camel.component.twitter.TwitterComponent;
import org.apache.camel.main.Main;
import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.slf4j.MDC;

import static com.juliaaano.twitterfeed.AsciiBanner.asciiBanner;
import static com.juliaaano.twitterfeed.InfluxDBConfig.*;
import static com.juliaaano.twitterfeed.TwitterConfig.*;

public class AppBootstrap {

    public static void main(String... args) throws Exception {

        MDC.put("Correlation-Id", "correlation-id");
        asciiBanner("application-ascii-banner.txt").ifPresent(AsciiBanner::print);

        final Main main = new Main();

        main.bind("influxdbBean", influxdbBean());
        main.bind("twitter", twitterComponent());
        main.addRouteBuilder(new TwitterFeedRouteBuilder());

        main.run(args);
    }

    private static InfluxDB influxdbBean() {

        return InfluxDBFactory.connect(
                INFLUXDB_URL,
                INFLUXDB_USERNAME,
                INFLUXDB_PASSWORD
        );
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
