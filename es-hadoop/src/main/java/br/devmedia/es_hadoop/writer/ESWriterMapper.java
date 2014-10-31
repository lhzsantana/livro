package br.devmedia.es_hadoop.writer;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.MapWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.log4j.Logger;

public class ESWriterMapper extends
		Mapper<LongWritable, Text, NullWritable, MapWritable> {

	private static Logger logger = Logger.getLogger(ESWriterMapper.class);

	@Override
	protected void map(LongWritable key, Text value, Context context)
			throws IOException, InterruptedException {

		logger.debug(value);

		String[] splitValue = value.toString().split("|");

		String title = splitValue[0];

		MapWritable doc = new MapWritable();

		if (title.toLowerCase().contains("santos")) {

			doc.put(new Text("title"), new Text(title));
			doc.put(new Text("description"), new Text(splitValue[1]));
			doc.put(new Text("link"), new Text(splitValue[2]));
			doc.put(new Text("category"), new Text(splitValue[3]));

			context.write(NullWritable.get(), doc);
		}
	}
}
