package com.tt.livro.cap2;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.index.query.FilterBuilders;
import org.elasticsearch.search.SearchHit;

public class _241_Filter extends _0_Abstract {

	public _241_Filter() {
		_211_Connection connection = new _211_Connection();

		client = connection.createClient();
	}

	public void retrieveUsingFilter(String cidade, String empresa){		

		SearchResponse response = client.prepareSearch(index).setTypes(type)
				.setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
				.setPostFilter(
						FilterBuilders.boolFilter()
					    .must(FilterBuilders.termFilter("cidade", cidade))
					    .should(FilterBuilders.termFilter("empresa", empresa))						
				)
				.execute()
				.actionGet();		

		for (SearchHit hit : response.getHits()) {
			// Manusear resultados
		}
	}
}
