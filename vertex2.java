package minCut;

import java.util.ArrayList;

public class vertex2 {

		
		private int vertexId;
		private ArrayList<Integer> vertexEdges;
		
		vertex2(int id){
			this(id, new ArrayList<Integer>());
		}
		
		vertex2(int x, ArrayList<Integer> edges){
			vertexId = x;
			vertexEdges = edges;
		}
		
		public void updateVertexId(int newVertexId){
			vertexId = newVertexId;
		}
		
		public int getVertexId(){
			return vertexId;
		}
		
		public String toString(){
			String vertexString = "";
			for (int each: vertexEdges){
				vertexString += each + " ";
				
			}
			return vertexString;
		}
		
		/*
		public String toStringDetailed(){
			String vertexString = "";
			vertexString += "[";
			for (int each : vertexEdges){
				vertexString += each.toString() + " ";			
			}
			vertexString += "]";
			return vertexString;
		}*/
		
		public void addEdge(int AddedEdge){
			vertexEdges.add(AddedEdge);
		}
		
		public boolean removeEdge(int RemovedEdge){
			if(vertexEdges.contains(RemovedEdge)){
				int indexOfEdge = vertexEdges.indexOf(Integer.valueOf(RemovedEdge));
				vertexEdges.remove(indexOfEdge);
				return true;
			} else {
				return false;
			}
		}
		
		public boolean containsEdge(int x){
			if(vertexEdges.contains(x)){
				return true;
			} else {
				return false;
			}
		}
		
		public ArrayList<Integer> getEdgeList(){
			return vertexEdges;
		}
		
		
		/*
		public void adjustEdge(){
			edge newEdge = new edge();
		}
		
		
			
		
		public void editFirstVertex(int x){
			this.buildEdge(x, second);
		}
		
		public void editSecondVertex(int x){
			this.buildEdge(first, x);
		}
		
		public int getFirstVertex(){
			return first;
		}
		
		public int getSecondVertex(){
			return second;
		
		}
		
		public String toString(){
			return vertex;
		}
		*/	
		

	}

