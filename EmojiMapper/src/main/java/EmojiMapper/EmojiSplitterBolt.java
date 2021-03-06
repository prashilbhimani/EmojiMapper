package EmojiMapper;

import java.util.List;
import java.util.Map;

import org.apache.storm.task.OutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichBolt;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.vdurmont.emoji.EmojiParser;

public class EmojiSplitterBolt extends BaseRichBolt{
	private OutputCollector collector;
	JSONParser parser;
	
	public void execute(Tuple t) {
		String msg = (String)t.getString(0);
		String date=t.getString(1);
		String trend=t.getString(2);
		Long retweet_count = t.getLongByField("retweet_count");
		Long tweet_id = t.getLongByField("tweet_id");
		List<String> result=EmojiParser.extractEmojis(msg);
		for(String emoji : result) {
			collector.emit(new Values(emoji,date,trend,retweet_count));
		}
	
	}
	public void prepare(Map arg0, TopologyContext arg1, OutputCollector arg2) {
		// TODO Auto-generated method stub
		this.collector=arg2;
		this.parser=new JSONParser();
	}

	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		// TODO Auto-generated method stub
		declarer.declare(new Fields("emoji","date","trend","retweet_count"));
		
	}
	

}
