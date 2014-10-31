package br.devmedia.es_hadoop.writer;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.MapWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.KeyValueTextInputFormat;
import org.elasticsearch.hadoop.mr.EsOutputFormat;

public class ESWriterJob {
		
	public static void main(String[] args) throws Exception {
	    Configuration conf = new Configuration();
		conf.set("es.nodes","localhost");    
		conf.set("es-port","9200");    
	    conf.set("es.resource","santos/rss");
	    
	    Job job = new Job(conf, "RSS Writer");
	    
        job.setJarByClass(ESWriterJob.class);
        
        //es-hadoop configs
        job.setOutputFormatClass(EsOutputFormat.class);
        job.setMapOutputValueClass(MapWritable.class);
        job.setMapperClass(ESWriterMapper.class);        
        job.setSpeculativeExecution(false);

        FileInputFormat.addInputPath(job, new Path("/devmedia/input.txt"));
        job.setInputFormatClass(KeyValueTextInputFormat.class);
            
        boolean result = job.waitForCompletion(true);
        System.exit(result ? 0 : 1);
	  }	
}
