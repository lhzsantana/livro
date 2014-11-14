package com.tt.livro.cap3;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;

import com.tt.livro.cap2._0_Abstract;
import com.tt.livro.cap2._211_Connection;

public class _3432_RegexQuery extends _0_Abstract {

	public _3432_RegexQuery() {
		_211_Connection connection = new _211_Connection();

		client = connection.createClient();
	}

	public void searchRegexQuery(String regex) {
				
		SearchResponse response = client.prepareSearch(index).setTypes(type)
				.setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
				.setQuery(
						QueryBuilders.regexpQuery("descricao", regex)
				)
				.execute()
				.actionGet();

		for (SearchHit hit : response.getHits().getHits()) {
			// Manusear os resultados
		}
	}
}
