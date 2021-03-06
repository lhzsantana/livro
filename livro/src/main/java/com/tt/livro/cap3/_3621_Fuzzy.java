package com.tt.livro.cap3;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.index.query.FilterBuilders;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;

import com.tt.livro.cap2._0_Abstract;
import com.tt.livro.cap2._211_Connection;

public class _3621_Fuzzy extends _0_Abstract {

	public _3621_Fuzzy() {
		_211_Connection connection = new _211_Connection();

		client = connection.createClient();
	}

	public void searchBoolFuzzyQuery(String organizacao, String titulo, String cidade) {
		
		SearchResponse response = client.prepareSearch(index).setTypes(type)
				.setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
				.setQuery(
						QueryBuilders.boolQuery()
						.should(QueryBuilders
								.matchQuery("titulo", titulo)								
								.fuzziness(1))
						.must(QueryBuilders
								.matchQuery("organizacao.nome", organizacao)								
								.fuzziness(2))
						.mustNot(QueryBuilders
								.termQuery("endereco.cidade", cidade)
								)
						.minimumNumberShouldMatch(2)	
						)
				.execute()
				.actionGet();

		for (SearchHit hit : response.getHits().getHits()) {
			// Manusear os resultados
		}
	}
}
