package com.tt.livro.cap3;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.index.query.FilterBuilder;
import org.elasticsearch.index.query.FilterBuilders;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;

import com.tt.livro.cap2._0_Abstract;
import com.tt.livro.cap2._211_Connection;

public class _3412_FilteredQuery extends _0_Abstract {

	public _3412_FilteredQuery() {
		_211_Connection connection = new _211_Connection();

		client = connection.createClient();
	}

	public void searchBoolQuery(String organizacao, String titulo, String cidade) {

		QueryBuilder queryBuilder = QueryBuilders.boolQuery()
		.should(QueryBuilders.matchQuery("titulo", titulo))
		.must(QueryBuilders.matchQuery("organizacao.nome", organizacao))
		.mustNot(QueryBuilders.termQuery("endereco.cidade", cidade))
		.minimumNumberShouldMatch(2);
		
		FilterBuilder filterBuilder = FilterBuilders.existsFilter("descricao");
		
		SearchResponse response = client.prepareSearch(index).setTypes(type)
				.setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
				.setQuery(
						QueryBuilders.filteredQuery(queryBuilder, filterBuilder)
				)
				.execute()
				.actionGet();

		for (SearchHit hit : response.getHits().getHits()) {
			// Manusear os resultados
		}
	}
}
