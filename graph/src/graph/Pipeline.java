package graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Pipeline {

	public static void main(String[] args){
		Pipeline p = new Pipeline();
		List<int[]> keysList = new ArrayList<int[]>();
		Graph g = p.getGraph();
		
		int size = g.size();
		for(int i = 0; i < size; i++){
			Node n = g.removeNode(i);
			if(p.isKeyNode(g, n)){
				keysList.add(new int[]{i});
				g.restoreNode(n);
				break;
			}
			for(int j = 0; j < size; j++){
				if(j == i) continue;
				Node n2 = g.removeNode(j);
				if(p.isKeyNode(g, n2)){
					keysList.add(new int[]{i,j});
					g.restoreNode(n2);
					break;
				}
				for(int k = 0; k < size; k++){
					if(k == i || k == j) continue;
					Node n3 = g.removeNode(k);
					if(p.isKeyNode(g, n3)){
						keysList.add(new int[]{i,j,k});
						g.restoreNode(n3);						
						break;
					}
					g.restoreNode(n3);
				}
				g.restoreNode(n2);
			}
			g.restoreNode(n);
			
		}

		int[] min = keysList.get(0);
		for(int[] key : keysList){
			if(key.length < min.length){
				min = key;
			}
		}
		for(Integer k : min){
			System.out.print((k + 1) + ", ");
		}
		System.out.println();
	}
	
	public boolean isKeyNode(Graph g, Node n){		
		Node start = n.getNextNodes().get(0);
		List<Node> visited = new ArrayList();
		g.DFS(start, visited);
		return g.size() != visited.size();
	}
	
	public Graph getGraph(){
		int sizeV = 5;
		int sizeE = 7;
		int[][] edges = new int[][]{
				{0,1},{0,2},{1,2},{2,3},{2,4},{3,4},{1,3}
		};
		Graph g = new Graph(sizeV);
		g.buildConnections(edges);
		return g;
	}
}
