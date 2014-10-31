package com.tt.livro.cap3;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;

import com.tt.livro.cap2._0_Abstract;
import com.tt.livro.cap2._211_Connection;

public class _3461_DisMax extends _0_Abstract {

	public _3461_DisMax() {
		_211_Connection connection = new _211_Connection();

		client = connection.createClient();
	}

	public void getDisMax(String cidade, String organizacao) {
		
		QueryBuilder query1 = QueryBuilders.matchQuery("cidade", cidade);
		QueryBuilder query2 = QueryBuilders.matchQuery("organizacao", organizacao);

		SearchResponse response = client.prepareSearch(index).setTypes(type)
				.setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
				.setQuery(QueryBuilders
						.disMaxQuery()
						.add(query1)
						.add(query2)
						.tieBreaker(3)
				)
				.execute()
				.actionGet();

		for (SearchHit hit : response.getHits().getHits()) {
			// Manusear os resultados
		}
	}
}
