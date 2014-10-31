package com.tt.livro.cap2;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;

public class _231_232_233_224_225_Mappings extends _0_Abstract{

	public _231_232_233_224_225_Mappings() {
		_211_Connection connection = new _211_Connection();

		client = connection.createClient();
	}
	
	public void create(String id, String titulo, 
			String organizacao, String cidade, String descricao) throws IOException {
		
		//Mapeamento com texto
		String jsonTxt = "{" +
		        "\"titulo\":\""+titulo+"\"," +
		        "\"organizacao\":\""+organizacao+"\"," +
		        "\"cidade\":\""+cidade+"\"," +
		        "\"descricao\":\""+descricao+"\"," +
		"}";
	
		client.prepareIndex(index, type, id)
				.setSource(jsonTxt).execute()
				.actionGet();

		//Mapeamento com map
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		jsonMap.put("titulo",titulo);
		jsonMap.put("descricao",descricao);
		jsonMap.put("organizacao",organizacao);
		jsonMap.put("cidade",cidade);;
	
		client.prepareIndex(index, type, id)
				.setSource(jsonMap).execute()
				.actionGet();
		
		//Mapeamento com Jackson
		ObjectMapper mapper = new ObjectMapper();
		
		_225_Oportunidade oportunidade = new _225_Oportunidade();
		oportunidade.setCidade(cidade);
		oportunidade.setDescricao(descricao);
		oportunidade.setTitulo(titulo);
		oportunidade.setOrganizacao(organizacao);

		String jsonJackson = mapper.writeValueAsString(oportunidade);
		
		client.prepareIndex(index, type, id)
				.setSource(jsonJackson).execute()
				.actionGet();
	}
}
