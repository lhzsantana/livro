package com.tt.livro.cap3.wait;

import static org.elasticsearch.node.NodeBuilder.nodeBuilder;

import org.elasticsearch.client.Client;
import org.elasticsearch.node.Node;

import com.tt.livro.cap2._0_Abstract;

public class _354_RollingIndexes extends _0_Abstract {
		
	public Client createNode(){
		
		Node node =	nodeBuilder().clusterName("clusterJavaMagazine").
		client(true).node();
		client = node.client();
		
		return client;
	}
	
	public void createIndex(){
		
	}
	
	
	public void searchIndex(){
		
		
	}
}
