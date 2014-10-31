package com.tt.livro.cap2;

import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.node.Node;

import static org.elasticsearch.node.NodeBuilder.*;

public class _211_Connection {

	public Client createNode() {

		Node node = nodeBuilder().clusterName("cluster_voluntarios")
				.client(true).node();
		Client client = node.client();

		return client;
	}

	public Client createClient() {

		Settings settings = ImmutableSettings.settingsBuilder()
				.put("cluster.name", "cluster_voluntarios").build();
		TransportClient client = new TransportClient(settings);

		client.addTransportAddress(new InetSocketTransportAddress("127.0.0.1",
				9300));

		return client;
	}
}
