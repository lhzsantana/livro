package com.tt.livro.cap5;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

import java.util.Date;

import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;

import com.tt.livro.cap2._0_Abstract;
import com.tt.livro.cap2._211_Connection;

public class _513_Bulkload extends _0_Abstract{

	public _513_Bulkload() {
		_211_Connection connection = new _211_Connection();

		client = connection.createClient();
	}

	public void createBulk() throws Exception {

		BulkRequestBuilder bulkRequest = client.prepareBulk();

		//indexar novos elementos		
		bulkRequest.add(client.prepareIndex(index, type, "1").setSource(
				jsonBuilder().startObject().field("user", "kimchy")
						.field("postDate", new Date())
						.field("message", "trying out Elasticsearch")
						.endObject()));

		bulkRequest.add(client.prepareIndex("twitter", "tweet", "2").setSource(
				jsonBuilder().startObject().field("user", "kimchy")
						.field("postDate", new Date())
						.field("message", "another post").endObject()));

		//deletar elementos
		bulkRequest.add(client.prepareDelete(index, type, "1"));
		
		//update elementos
		bulkRequest.add(client.prepareUpdate(index, type, "2").setSource(
				jsonBuilder().startObject().field("user", "kimchy")
						.field("postDate", new Date())
						.field("message", "another post").endObject()));

		BulkResponse bulkResponse = bulkRequest.execute().actionGet();
		if (bulkResponse.hasFailures()) {
			// processa as falhas
		}
	}
}
