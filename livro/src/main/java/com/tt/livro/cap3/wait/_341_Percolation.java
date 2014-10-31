package com.tt.livro.cap3.wait;

import java.io.IOException;

import org.elasticsearch.action.percolate.PercolateResponse;
import org.elasticsearch.action.percolate.PercolateResponse.Match;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;

import com.tt.livro.cap2._0_Abstract;
import com.tt.livro.cap2._211_Connection;

public class _341_Percolation extends _0_Abstract {

	public _341_Percolation() {
		_211_Connection connection = new _211_Connection();

		client = connection.createClient();
	}

	public void getPercolation() throws IOException {
		
		XContentBuilder docBuilder = XContentFactory.jsonBuilder()
				.startObject();
		docBuilder.field("doc").startObject();
		docBuilder.field("content", "This is amazing!");
		docBuilder.endObject();
		docBuilder.endObject();
		
		PercolateResponse response = client.preparePercolate()
				.setSource(docBuilder).execute().actionGet();
		// Iterate over the results
		for (Match match : response.getMatches()) {
			// Handle the result which is the name of
			// the query in the percolator
		}
	}
}
