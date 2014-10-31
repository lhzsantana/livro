package com.tt.livro.cap2;

import java.util.Collection;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.bucket.terms.Terms.Bucket;

public class _251_Agreggation  extends _0_Abstract {

	public _251_Agreggation() {
		_211_Connection connection = new _211_Connection();

		client = connection.createClient();
	}

	public void contarCidades() {

		SearchResponse response = client
				.prepareSearch(index)
				.setTypes(type)
				.setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
				.setQuery(QueryBuilders.matchAllQuery())
				.addAggregation(
						AggregationBuilders
							.terms("keys")
							.field("cidade")
				)
				.execute().actionGet();
		
		
		Terms  terms = response.getAggregations().get("keys");
		Collection<Bucket> buckets = terms.getBuckets();

		for (Bucket bucket : buckets) {
			System.out.println(bucket.getKey());
			System.out.println(bucket.getDocCount());			
		}
	}
}
