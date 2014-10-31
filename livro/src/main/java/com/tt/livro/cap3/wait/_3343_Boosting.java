package com.tt.livro.cap3.wait;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.sort.SortOrder;

import com.tt.livro.cap2._0_Abstract;
import com.tt.livro.cap2._211_Connection;

public class _3343_Boosting extends _0_Abstract {

	public _3343_Boosting() {
		_211_Connection connection = new _211_Connection();

		client = connection.createClient();
	}

	public void getSort() {

		SearchResponse response = client.prepareSearch(index).setTypes(type)
				.setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
				.setQuery(QueryBuilders.matchAllQuery().boost(200))
				.addSort("_source", SortOrder.ASC)
				.addSort("cidade", SortOrder.ASC)
				.execute()
				.actionGet();

		for (SearchHit hit : response.getHits().getHits()) {
			// Manusear os resultados
		}
	}
}
