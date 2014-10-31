package com.tt.livro.cap3;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.sort.SortOrder;

import com.tt.livro.cap2._0_Abstract;
import com.tt.livro.cap2._211_Connection;

public class _3631_Boost extends _0_Abstract {

	public _3631_Boost() {
		_211_Connection connection = new _211_Connection();

		client = connection.createClient();
	}

	public void searchBoolBoostQuery(String organizacao, String titulo, String cidade) {
		
		SearchResponse response = client.prepareSearch(index).setTypes(type)
				.setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
				.setQuery(
						QueryBuilders.boolQuery()
						.should(QueryBuilders
								.matchQuery("titulo", titulo)								
								.boost(1.2f))
						.must(QueryBuilders
								.matchQuery("organizacao.nome", organizacao)								
								.boost(2.3f))
						.mustNot(QueryBuilders
								.termQuery("endereco.cidade", cidade)
								.boost(4.5f))
						.minimumNumberShouldMatch(2)	
						)
				.addSort("titulo", SortOrder.ASC)
				.addSort("_score", SortOrder.ASC)
				.addSort("endereco.cidade", SortOrder.DESC)
				.execute()
				.actionGet();

		for (SearchHit hit : response.getHits().getHits()) {
			// Manusear os resultados
		}
	}
}
