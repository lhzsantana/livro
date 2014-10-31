package com.tt.livro.cap3;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;

import com.tt.livro.cap2._0_Abstract;
import com.tt.livro.cap2._211_Connection;

public class _3472_FuzzyLikeThisQuery extends _0_Abstract {

	public _3472_FuzzyLikeThisQuery() {
		_211_Connection connection = new _211_Connection();

		client = connection.createClient();
	}

	public void searchFuzzyLikeThis(String likeThis) {
		
		SearchResponse response = client.prepareSearch(index).setTypes(type)
				.setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
				.setQuery(QueryBuilders
						.fuzzyLikeThisQuery("cidade","organizacao")
				)
				.execute()
				.actionGet();

		for (SearchHit hit : response.getHits().getHits()) {
			// Manusear os resultados
		}
	}
}
