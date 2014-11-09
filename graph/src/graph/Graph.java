package graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class Graph {

	private int size;
	private Node[] nodes;
	
	public Graph(int size){
		this.size = size;
		initNodes();
	}
	
	public void initNodes(){
		nodes = new Node[size];
		for(int i = 0; i < size; i++){
			nodes[i] = new Node(i);
		}
	}
	
	public void buildConnections(int[][] edges){
		for(int i = 0; i < edges.length; i++){
			int a = edges[i][0];
			int b = edges[i][1];
			nodes[a].addNextNode(nodes[b]);
			nodes[b].addNextNode(nodes[a]);
		}
	} 
	
	public void DFS(Node node, List<Node> visited){
		if(visited.contains(node)) return;
		visited.add(node);
		List<Node> nexts = node.getNextNodes();
		for(Node next : nexts){
			DFS(next, visited);
		}
	}
	
	public void WFS(Node node, List<Node> visited){
		Queue<Node> toVisit = new LinkedList<Node>();
		toVisit.offer(node);
		while(!toVisit.isEmpty()){
			Node visitingNode = toVisit.poll();
			if(!visited.contains(visitingNode)){
				visited.add(visitingNode);
				List<Node> nexts = visitingNode.getNextNodes();
				for(Node next : nexts){
					toVisit.offer(next);
				}
			}
		}
	} 
	
	public Node removeNode(int id){
		Node node = nodes[id];
		List<Node> nexts = node.getNextNodes();
		for(Node next : nexts){
			next.removeNextNode(node);
		}
		nodes[id] = null;
		this.size -= 1;
		return node;
	}
	
	public void restoreNode(Node node){
		List<Node> nexts = node.getNextNodes();
		for(Node next : nexts){
			next.addNextNode(node);
		}
		nodes[node.getId()] = node;
		this.size += 1;
	}
	
	public int size(){
		return size;
	}
	
	public Node getNode(int id){
		return nodes[id];
	}
	
}

class Node{
	
	private int id;
	private List<Node> nextNodes;
	
	public Node(int id){
		this.id = id;
		nextNodes = new LinkedList<Node>();
	}
	
	public int getId(){
		return id;
	}
	
	public void addNextNode(Node node){
		nextNodes.add(node);
	}
	
	public List<Node> getNextNodes(){
		return nextNodes;
	}
	
	public void removeNextNode(Node node){
		nextNodes.remove(node);
	}
}