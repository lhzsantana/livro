package com.tt.livro.cap3;

import java.io.IOException;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.suggest.Suggest.Suggestion.Entry;
import org.elasticsearch.search.suggest.Suggest.Suggestion.Entry.Option;
import org.elasticsearch.search.suggest.term.TermSuggestionBuilder;

import com.tt.livro.cap2._0_Abstract;
import com.tt.livro.cap2._211_Connection;

public class _3724_Suggestions extends _0_Abstract {

	public _3724_Suggestions() {
		_211_Connection connection = new _211_Connection();

		client = connection.createClient();
	}

	public void suggest(String titulo) throws IOException {

		SearchResponse response = client
				.prepareSearch(index)
				.setTypes(type)
				.setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
				.setQuery(QueryBuilders.matchAllQuery())
				.addSuggestion(
						new TermSuggestionBuilder("suggest1")
						.text(titulo)
						.field("_all")
				).execute().actionGet();

		for (Entry<? extends Option> entry : response.getSuggest()
				.getSuggestion("suggest1").getEntries()) {

			for (Option option : entry.getOptions()) {
				//
			}
		}
	}
}
