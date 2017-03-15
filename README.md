# Twitter Feed

Process Twitter messages with **Apache Camel**.

You'll need your own [app credentials](https://apps.twitter.com/) to access Twitter's API.

Export them to your host if running local or with docker:

```
export TWITTER_CONSUMER_KEY=your-twitter-consumer-key
export TWITTER_CONSUMER_SECRET=your-twitter-consumer-secret
export TWITTER_ACCESS_TOKEN=your-twitter-access-token
export TWITTER_ACCESS_TOKEN_SECRET=your-twitter-access-token-secret
```
In Kubernetes, create a secret named twitter-secret.
