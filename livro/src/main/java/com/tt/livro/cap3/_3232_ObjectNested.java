package com.tt.livro.cap3;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

import java.io.IOException;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;

import com.tt.livro.cap2._0_Abstract;
import com.tt.livro.cap2._211_Connection;

public class _3232_ObjectNested extends _0_Abstract {

	public _3232_ObjectNested() {
		_211_Connection connection = new _211_Connection();

		client = connection.createClient();
	}

	public void insertObjectNested(String id, 
			String titulo, 
			String organizacao, 
			String descricao,
			String logradouro,
			String cidade,
			String estado,
			String nomeContato,
			String sobrenomeContato) throws IOException {
		
		XContentBuilder builder = jsonBuilder()
			    .startObject()
			        .field("titulo", titulo)
			        .field("descricao", descricao)
			        .field("organizacao",organizacao)
			        .field("endereco.logradouro",logradouro)
			        .field("endereco.cidade",cidade)
			        .field("endereco.estado",estado)
			        .field("contato.nome",nomeContato)
			        .field("contato.sobrenome",sobrenomeContato)
			    .endObject();
				
		client.prepareIndex(index, type, id)
				.setSource(builder).execute()
				.actionGet();
	}
	
	public void retriveObjectNested(){

		SearchResponse response = client
				.prepareSearch(index)
				.setQuery(QueryBuilders.matchAllQuery())
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
