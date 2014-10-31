package com.tt.livro.cap4;

import static org.elasticsearch.node.NodeBuilder.nodeBuilder;

import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.node.Node;
import org.elasticsearch.search.SearchHit;

public class _324_Nested {

	private Client client;
		
	public Client createNode(){
		
		Node node =	nodeBuilder().clusterName("clusterJavaMagazine").
		client(true).node();
		client = node.client();
		
		return client;
	}
	
	
	public void mapNested(){
		
	}

	public void insertNested(){
		
	}
	
	public void searchNested(){
		
		 SearchRequestBuilder builder = client.prepareSearch("tests").setTypes("country")
		            .setSearchType(SearchType.DFS_QUERY_THEN_FETCH).addFields("countryName", "states");


		    MatchQueryBuilder mqb;

		    mqb = QueryBuilders.matchPhrasePrefixQuery("name", "ind");
		    builder.setQuery(mqb);

		    SearchResponse response = builder.execute().actionGet();
		    SearchHit[] documents = response.getHits().getHits();

		    System.out.println(documents.length);
		
		
	}
}
