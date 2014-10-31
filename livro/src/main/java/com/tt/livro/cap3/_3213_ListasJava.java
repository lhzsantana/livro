package com.tt.livro.cap3;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;

import com.tt.livro.cap2._0_Abstract;
import com.tt.livro.cap2._211_Connection;

public class _3213_ListasJava extends _0_Abstract {

	public _3213_ListasJava() {
		_211_Connection connection = new _211_Connection();

		client = connection.createClient();
	}
	
	public void insertLista(String id, String titulo, 
			String organizacao, String cidade, String descricao, List<_3213_Habilidade> habilidades) throws IOException {
		
		XContentBuilder builder = jsonBuilder()
			    .startObject()
			        .field("titulo", titulo)
			        .field("descricao", descricao)
			        .field("organizacao",organizacao)
			        .field("cidade",cidade)
			        .startArray("habilidades");
		
				        for(_3213_Habilidade habilidade : habilidades){
				        	builder.startObject()
					        .field("nome", habilidade.getNome())	
					        .field("descricao", habilidade.getDescricao())				        	
				        	.endObject();
				        }					
			        
			        builder.endArray()
			    .endObject();
				
		client.prepareIndex(index, type, id)
				.setSource(builder).execute()
				.actionGet();
	}
	
	public List<_3213_Habilidade> retrieveAllLista(){

		SearchResponse response = client
				.prepareSearch(index)
				.setQuery(QueryBuilders.matchAllQuery())
				.execute()
				.actionGet();
		
		List<_3213_Habilidade> habilidades = new ArrayList<_3213_Habilidade>();
		
		for (SearchHit hit : response.getHits().getHits()) {
			_3213_Habilidade habilidade = new _3213_Habilidade();
			
			/*
			for (SearchHit hit : response.getHits().getHits()) {
			
				habilidade.setNome();
				habilidade.setDescricao(hit.getSource().get("habilidades.descricao"));
				habilidades.add(habilidade);
			}*/
		}
		
		return habilidades;
	}
}
