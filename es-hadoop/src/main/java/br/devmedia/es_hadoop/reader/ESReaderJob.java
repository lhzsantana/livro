package br.devmedia.es_hadoop.reader;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.MapWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.Job;
import org.elasticsearch.hadoop.mr.EsInputFormat;

public class ESReaderJob {
		
	public static void main(String[] args) throws Exception {
	    Configuration conf = new Configuration();
		conf.set("es.nodes","localhost");    
		conf.set("es-port","9200");    
	    conf.set("es.resource","santos/rss");
	    conf.set("es.query","santos/rss");
	    
	    Job job = new Job(conf, "RSS Reader");
	    
        job.setJarByClass(ESReaderJob.class);
        
		job.setInputFormatClass(EsInputFormat.class);
		job.setMapOutputKeyClass(Text.class);
		job.setMapOutputValueClass(MapWritable.class);

        FileOutputFormat.setOutputPath(job, new Path("/devmedia/output.txt"));
        
        boolean result = job.waitForCompletion(true);
        System.exit(result ? 0 : 1);
	}	
}
