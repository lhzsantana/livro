package com.tt.livro.cap2;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;

public class _232_RetriveOportunidade  extends _0_Abstract{
	private Client client;

	public _232_RetriveOportunidade() {
		_211_Connection connection = new _211_Connection();

		client = connection.createClient();
	}

	public void retrieveOportunidadesByCidade(String cidade) {

		SearchResponse response = client.prepareSearch(index).setTypes(type)
				.setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
				.setQuery(QueryBuilders.termQuery("cidade", cidade)).execute()
				.actionGet();

		for (SearchHit hit : response.getHits().getHits()) {
			// Manusear os resultados
		}
	}

	public void retrieveOportunidadesByTitulo(String titulo) {

		SearchResponse response = client.prepareSearch(index).setTypes(type)
				.setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
				.setQuery(QueryBuilders.matchQuery("titulo", titulo)).execute()
				.actionGet();

		for (SearchHit hit : response.getHits().getHits()) {
			// Manusear os resultados
		}
	}
}
