import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
You are given a tree (a simple connected graph with no cycles).You have to remove as many edges from the tree as possible to obtain a forest with the condition that : Each connected component of the forest contains even number of vertices

Your task is to calculate the number of removed edges in such a forest.

Input:
The first line of input contains two integers N and M. N is the number of vertices and M is the number of edges. 2 <= N <= 100. 
Next M lines contains two integers ui and vi which specifies an edge of the tree. (1-based index)

Output:
Print a single integer which is the answer

Sample Input 

10 9
2 1
3 1
4 3
5 2
6 1
7 2
8 6
9 8
10 8
 
Sample Output :
2
 
Explanation : On removing the edges (1, 3) and (1, 6), we can get the desired result.
Original tree: 


Decomposed tree:


Note: The tree in the input will be such that it can always be decomposed into components containing even number of nodes. 
*/

public class Solution {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line;
		
		line = br.readLine();
		
		String[] tokens = line.split(" ");
		int vertices = Integer.parseInt(tokens[0]);
		int edges = Integer.parseInt(tokens[1]);
		int edgecount = 0;
		
		TreeNode[] nodeList = new TreeNode[vertices];
		
		for (int i = 0; i < edges; i++){
			
			line = br.readLine();
			String[] nodes = line.split(" ");
			
			int dest = Integer.parseInt(nodes[0]);
			int src = Integer.parseInt(nodes[1]);
			
			if (nodeList[src - 1] == null)
				nodeList[src - 1] = new TreeNode(src);
			if (nodeList[dest - 1] == null)
				nodeList[dest - 1] = new TreeNode(dest);
			
			nodeList[src - 1].addChild(nodeList[dest - 1]);
			nodeList[dest - 1].addParent(nodeList[src - 1]);
		}
		
		for (int i = nodeList.length - 1; i >= 0; i--){
			if (nodeList[i].children.size() % 2 != 0){
				if (nodeList[i].parent != null){
					nodeList[i].parent.children.remove(nodeList[i]);
					edgecount++;
				}
			}
		}
		
		System.out.println(edgecount);

	}
	
	public static class TreeNode {
		
		private ArrayList<TreeNode> children = new ArrayList<TreeNode>();;
		private int numDescendants;
		private int value;
		private TreeNode parent;
		
		public TreeNode(int i) {
			value = i;
		}
		
		public void addChild(TreeNode child){
			children.add(child);
		}
		
		public void addParent(TreeNode parent){
			this.parent = parent;
		}
	}

}
