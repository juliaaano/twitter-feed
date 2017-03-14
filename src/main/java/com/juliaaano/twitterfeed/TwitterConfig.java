package com.juliaaano.twitterfeed;

import static java.lang.System.getenv;

class TwitterConfig {

    static final String TWITTER_CONSUMER_KEY = getenv("TWITTER_CONSUMER_KEY");
    static final String TWITTER_CONSUMER_SECRET = getenv("TWITTER_CONSUMER_SECRET");
    static final String TWITTER_ACCESS_TOKEN = getenv("TWITTER_ACCESS_TOKEN");
    static final String TWITTER_ACCESS_TOKEN_SECRET = getenv("TWITTER_ACCESS_TOKEN_SECRET");
}
