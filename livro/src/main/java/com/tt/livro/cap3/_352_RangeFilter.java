package com.tt.livro.cap3;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.index.query.FilterBuilder;
import org.elasticsearch.index.query.FilterBuilders;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;

import com.tt.livro.cap2._0_Abstract;
import com.tt.livro.cap2._211_Connection;

public class _352_RangeFilter extends _0_Abstract {

	public _352_RangeFilter() {
		_211_Connection connection = new _211_Connection();

		client = connection.createClient();
	}

	public void searchRangeFilter(String query) {
		
		SearchResponse response = client.prepareSearch(index).setTypes(type)
				.setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
				.setQuery(QueryBuilders.matchAllQuery())
				.setPostFilter(	
						FilterBuilders
						.rangeFilter("postDate")
						.from("2010-03-01")
						.to("2010-04-01")
				)
				.execute()
				.actionGet();

		for (SearchHit hit : response.getHits().getHits()) {
			// Manusear os resultados
		}
	}
}
