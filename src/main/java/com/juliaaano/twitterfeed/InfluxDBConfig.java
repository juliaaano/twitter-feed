package com.juliaaano.twitterfeed;

class InfluxDBConfig {

    static final String INFLUXDB_URL = env("INFLUXDB_URL", "http://127.0.0.1:8086");
    static final String INFLUXDB_USERNAME = env("INFLUXDB_USERNAME", "root");
    static final String INFLUXDB_PASSWORD = env("INFLUXDB_PASSWORD", "root");

    private static String env(final String key, final String defaultValue) {

        final String value = System.getenv(key);
        return value != null ? value : defaultValue;
    }
}
