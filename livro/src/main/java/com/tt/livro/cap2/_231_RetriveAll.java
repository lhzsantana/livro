package com.tt.livro.cap2;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;

public class _231_RetriveAll  extends _0_Abstract{
	private Client client;

	public _231_RetriveAll() {
		_211_Connection connection = new _211_Connection();

		client = connection.createClient();
	}

	public void retrieveAll() {

		SearchResponse response = client
				.prepareSearch(index)
				.setQuery(QueryBuilders.matchAllQuery())
				.execute()
				.actionGet();

		for (SearchHit hit : response.getHits().getHits()) {
			System.out.println("Id: " + hit.getId());
			System.out.println("Título: " + hit.getSource().get("titulo"));
			System.out.println("Cidade: " + hit.getSource().get("cidade"));
			System.out.println("Descrição: " + hit.getSource().get("descricao"));
			System.out.println("Organização: " + hit.getSource().get("organizacao"));
		}
	}
}
