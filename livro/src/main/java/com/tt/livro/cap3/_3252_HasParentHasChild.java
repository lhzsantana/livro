package com.tt.livro.cap3;

import java.io.IOException;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;

import com.tt.livro.cap2._0_Abstract;
import com.tt.livro.cap2._211_Connection;

public class _3252_HasParentHasChild extends _0_Abstract {

	public _3252_HasParentHasChild() {
		_211_Connection connection = new _211_Connection();

		client = connection.createClient();
	}

	public void queryHasChild() throws IOException {
		
		SearchResponse response = client
				.prepareSearch(index)
				.setQuery(
						QueryBuilders.hasChildQuery("organizacao", QueryBuilders.matchAllQuery()))
				.execute()
				.actionGet();
				
		for (SearchHit hit : response.getHits().getHits()) {
				hit.getSource().get("endereco.logradouro");
				hit.getSource().get("endereco.cidade");
				hit.getSource().get("endereco.estado");
				hit.getSource().get("contato.nome");
				hit.getSource().get("contato.sobrenome");
		}
	}
	
	public void queryHasParent(){

		SearchResponse response = client
				.prepareSearch(index)
				.setQuery(
						QueryBuilders.hasParentQuery("oportunidade", QueryBuilders.matchAllQuery()))
				.execute()
				.actionGet();
				
		for (SearchHit hit : response.getHits().getHits()) {
				hit.getSource().get("endereco.logradouro");
				hit.getSource().get("endereco.cidade");
				hit.getSource().get("endereco.estado");
				hit.getSource().get("contato.nome");
				hit.getSource().get("contato.sobrenome");
		}
	}
}
