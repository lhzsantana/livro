package com.tt.livro.cap2;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;

public class _233_RetriveOportunidadePaginacao  extends _0_Abstract{
	private Client client;

	public _233_RetriveOportunidadePaginacao() {
		_211_Connection connection = new _211_Connection();

		client = connection.createClient();
	}
	
	public void retrieveOportunidades(String query) {

		SearchResponse response;

		while (true) {
			response = client
					.prepareSearch(index)
					.setTypes(type)
					.setSearchType(SearchType.SCAN)
					.setQuery(
							QueryBuilders.multiMatchQuery(query, 
									"titulo",
									"descricao", 
									"organizacao", 
									"cidade")).setSize(100).execute()
					.actionGet();

			for (SearchHit hit : response.getHits()) {
				// Manusear resultados
			}
			// Condição de parada
			if (response.getHits().getHits().length == 0) {
				break;
			}
		}
	}
}
