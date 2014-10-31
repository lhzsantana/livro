package com.tt.livro.cap2;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

import java.io.IOException;

import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.common.xcontent.XContentBuilder;

public class _212_InsertUpdateOportunidade extends _0_Abstract{

	public _212_InsertUpdateOportunidade() {
		_211_Connection connection = new _211_Connection();

		client = connection.createClient();
	}
	
	public void create(String id, String titulo, 
			String organizacao, String cidade, String descricao) throws IOException {
		
		XContentBuilder builder = jsonBuilder()
			    .startObject()
			        .field("titulo", titulo)
			        .field("descricao", descricao)
			        .field("organizacao",organizacao)
			        .field("cidade",cidade)
			    .endObject();
				
		client.prepareIndex(index, type, id)
				.setSource(builder).execute()
				.actionGet();
	}
	
	public void update(String id, String titulo, 
			String organizacao, String cidade, String descricao)  throws ElasticsearchException, Exception {

		XContentBuilder builder = jsonBuilder()
			    .startObject()
			        .field("titulo", titulo)
			        .field("descricao", descricao)
			        .field("organizacao",organizacao)
			        .field("cidade",cidade)
			    .endObject();

		client.prepareUpdate(index, type, id)
				.setSource(builder).execute()
				.actionGet();
	}

	public void delete(String id) {
		client.prepareDelete(index, type, id);
	}
}
