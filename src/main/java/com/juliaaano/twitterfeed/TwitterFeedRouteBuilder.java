package com.juliaaano.twitterfeed;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.influxdb.dto.Point;
import twitter4j.Status;

import static com.juliaaano.twitterfeed.TwitterConfig.TWITTER_TRACK_KEYWORD;
import static java.lang.System.currentTimeMillis;
import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static org.apache.camel.LoggingLevel.INFO;
import static org.apache.camel.component.influxdb.InfluxDbConstants.DBNAME_HEADER;
import static org.apache.camel.component.influxdb.InfluxDbConstants.RETENTION_POLICY_HEADER;

public class TwitterFeedRouteBuilder extends RouteBuilder {

    @Override
    public void configure() {

        fromF("twitter://streaming/filter?type=event&keywords=%s", TWITTER_TRACK_KEYWORD)
                .routeId("twitter-polling-consumer")
                .routeDescription("Polls tweets with a given keyword using the Twitter Streaming API.")
                .multicast()
                    .to("direct:tweets-logs")
                    .to("direct:tweets-influxdb")
                .end();

        from("direct:tweets-logs")
                .routeId("tweets-logger")
                .routeDescription("Logs the tweets.")
                .log(INFO, "${body.text}");

        from("direct:tweets-influxdb")
                .bean(TwitterFeedRouteBuilder.class, "translateTo")
                .setHeader(DBNAME_HEADER, constant("twitterFeedDB"))
                .setHeader(RETENTION_POLICY_HEADER, constant("autogen"))
                .to("influxdb://influxdbBean");
    }

    public Point translateTo(final Exchange exchange) {

        final Status tweet = exchange.getIn().getBody(Status.class);

        return Point.measurement("tyro_tweets")
                .time(currentTimeMillis(), MILLISECONDS)
                .addField("text", tweet.getText())
                .addField("user", tweet.getUser() != null ? tweet.getUser().getScreenName() : "")
                .build();
    }
}
