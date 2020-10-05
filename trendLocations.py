import json

# Import the necessary methods from "twitter" library
from twitter import Twitter, OAuth, TwitterHTTPError, TwitterStream

ACCESS_TOKEN = 'ACCESSTOKEN'
ACCESS_SECRET = 'ACCESSSECRET'
CONSUMER_KEY = 'CONSUMERKEY'
CONSUMER_SECRET = 'CONSUMERSECRET'

oauth = OAuth(ACCESS_TOKEN, ACCESS_SECRET, CONSUMER_KEY, CONSUMER_SECRET)

twitter_stream = TwitterStream(auth=oauth)

twitter = Twitter(auth=oauth)

world_trends = twitter.trends.available()
with open('data/trend_available.json', 'w') as outfile:
    json.dump(world_trends, outfile)
