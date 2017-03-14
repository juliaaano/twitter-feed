package com.juliaaano.twitterfeed;

import org.apache.camel.main.Main;
import org.slf4j.MDC;

public class AppBootstrap {

    public static void main(String... args) throws Exception {

        MDC.put("Correlation-Id", "correlation-id");
        AsciiBanner.asciiBanner("application-ascii-banner.txt").ifPresent(AsciiBanner::print);

        final Main main = new Main();

        main.run(args);
    }
}
