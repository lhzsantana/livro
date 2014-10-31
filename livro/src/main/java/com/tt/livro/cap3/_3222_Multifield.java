package com.tt.livro.cap3;

import java.util.ArrayList;
import java.util.List;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;

import com.tt.livro.cap2._0_Abstract;
import com.tt.livro.cap2._211_Connection;

public class _3222_Multifield extends _0_Abstract{


	public _3222_Multifield() {
		_211_Connection connection = new _211_Connection();

		client = connection.createClient();
	}
		
	public List<String> retrieveMultifield(String titulo){

		SearchResponse response = client
				.prepareSearch(index)
				.setQuery(QueryBuilders.matchQuery("titulos.raw", titulo))
				.execute()
				.actionGet();

		List<String> titulos = new ArrayList<String>();
				
		for (SearchHit hit : response.getHits().getHits()) {
			titulos.add(hit.getSource().get("titulos.raw").toString());
		}
		
		return titulos;
	}
}
