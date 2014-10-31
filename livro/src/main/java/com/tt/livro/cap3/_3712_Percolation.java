package com.tt.livro.cap3;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

import java.io.IOException;

import org.elasticsearch.action.percolate.PercolateResponse;
import org.elasticsearch.action.percolate.PercolateResponse.Match;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;

import com.tt.livro.cap2._0_Abstract;
import com.tt.livro.cap2._211_Connection;

public class _3712_Percolation extends _0_Abstract {

	public _3712_Percolation() {
		_211_Connection connection = new _211_Connection();

		client = connection.createClient();
	}

	public void percolate(String id, String titulo, String organizacao,
			String cidade, String descricao) throws IOException {

		XContentBuilder builder = jsonBuilder().startObject()
				.field("titulo", titulo).field("descricao", descricao)
				.field("organizacao", organizacao).field("cidade", cidade)
				.endObject();

		PercolateResponse response = client.preparePercolate()
				.setSource(builder).execute().actionGet();

		for (Match match : response.getMatches()) {
		}
	}
}
