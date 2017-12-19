package minCut;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class graph3 {
	

		private HashMap<Integer,edge> edges;
		private HashSet<Integer> returnEdges;
		private Integer edgesUniqueId;
		
		private HashMap<Integer,vertex2> vertices;
		private Integer verticesUniqueId;
		
		private int[] edgeArray;
		
		public graph3(){
			edges = new HashMap<Integer,edge>();
			vertices = new HashMap<Integer,vertex2>();
			returnEdges = new HashSet();
			edgesUniqueId = 0;
			verticesUniqueId = 0;
		}
		
		//This adds an edge, there can be parallel edges but they must have separate IDs
		//If the edge is a -1, then we add a Unique ID that is tracked by the object
		//If both vertices are not in the graph, then it throws and exception
		//Do we want
		public void addEdge(edge edge){
			if (edges.containsKey(edge.getEdgeId())){
				throw new IllegalArgumentException("The edge's UniqueID is already in the graph.");
			} 
			
			if (edge.getEdgeId()== -1){
				edge.editEdgeId(edgesUniqueId);
				edges.put(edgesUniqueId, edge);
				returnEdges.add(edgesUniqueId);
			} else {
				edges.put(edge.getEdgeId(), edge);
				returnEdges.add(edge.getEdgeId());
			}
			
		}
		
		public void addEdge(int firstVertex, int secondVertex){
			edge edge = new edge(edgesUniqueId, firstVertex, secondVertex);
			
			if (edges.containsKey(edge.getEdgeId())){
				throw new IllegalArgumentException("The edge's UniqueID is already in the graph.");
			} 
			
			
			edges.put(edge.getEdgeId(), edge);
			returnEdges.add(edge.getEdgeId());
			
			
			edgesUniqueId++;
			
		}
			
		
		public void addVertex(vertex2 x){
			if (vertices.containsKey(x.getVertexId())){
				throw new IllegalArgumentException("The vertex's UniqueID is already in the graph");
			} else{
			vertices.put(x.getVertexId(), x);
			verticesUniqueId++;
			}
		}
		
		public void addEdgeToVertex(vertex2 vertex, Integer edge){
			vertices.get(vertex.getVertexId()).addEdge(edge);
		}
		
		public boolean containsVertex(int x){
			return vertices.containsKey(x);
		}
		
		public boolean containsEdge(int x){
			return edges.containsKey(x);
		}
		
		public int[] getEdgeArray(){
			int[] returnEdgeArray = new int[edges.size()];
			int x = 0;
			for(Integer edge: edges.keySet()){
				returnEdgeArray[x] = edge;
				x++;
			}
			return returnEdgeArray;
		}
		
		public void printEdges(){
			System.out.println(edges.entrySet());
		}
		
		public void printVertices(){
			System.out.println(vertices.toString());
		}
		
		public HashSet<Integer> getEdges(){
			return returnEdges;
		}
		
		public HashMap<Integer,vertex2> getVertices(){
			return vertices;
		}
		
		
		public edge getEdgeObject(int edgeId){
			if(edges.containsKey(edgeId)){
				int id = edges.get(edgeId).getEdgeId();
				int firstVertexId = edges.get(edgeId).getFirstVertexId();
				int secondVertexId = edges.get(edgeId).getSecondVertexId();
				return new edge(id, firstVertexId, secondVertexId);
			} else{
				throw new IllegalArgumentException("The edge's UniqueID is not in the graph");
			}
		}
		
		
		public edge getEdge(int edgeId){
			if(edges.containsKey(edgeId)){
				return edges.get(edgeId);
			} else{
				throw new IllegalArgumentException("The edge's UniqueID is not in the graph");
			}
		}
		
		
		public String getEdgeString(int edgeId){
			return edges.get(edgeId).toString();
		}
		
		public vertex2 getVertex(int vertexId){
			if(vertices.containsKey(vertexId)){
				return vertices.get(vertexId);
			} else{
				throw new IllegalArgumentException("The vertices UniqueID is not in the graph");
			}
		}
		
		public vertex2 getVertexObject(int vertexId){
			if(vertices.containsKey(vertexId)){
				ArrayList<Integer> edges = new ArrayList<Integer>();
				edges.addAll(vertices.get(vertexId).getEdgeList());
				return new vertex2(vertexId, edges);
			} else{
				throw new IllegalArgumentException("The edge's UniqueID is not in the graph");
			}	
		}
		
		public ArrayList<vertex2> getMultipleVerticeObjects(int[] vertexIdList){
			ArrayList<vertex2> vertexList = new ArrayList<vertex2>();
			for(int x=0; x<vertexIdList.length; x++){
				vertexList.add(this.getVertexObject(vertexIdList[x]));
			}
			return vertexList;
		}
		
		public ArrayList<vertex2> getMultipleVertices(int[] vertexIdList){
			ArrayList<vertex2> vertexList = new ArrayList<vertex2>();
			for(int x=0; x<vertexIdList.length; x++){
				vertexList.add(this.getVertex(vertexIdList[x]));
			}
			return vertexList;
		}
		
		
		public ArrayList<edge> getMultipleEdges(int[] edgeIDList){
			ArrayList<edge> edgeList = new ArrayList<edge>();
			for(int x = 0; x<edgeIDList.length; x++){
				edgeList.add(this.getEdge(edgeIDList[x]));
			}
			return edgeList;
		}
		
		public ArrayList<edge> getMultipleEdgeObjects(int[] edgeIDList){
			ArrayList<edge> edgeList = new ArrayList<edge>();
			for(int x = 0; x<edgeIDList.length; x++){
				edgeList.add(this.getEdgeObject(edgeIDList[x]));
			}
			return edgeList;
		}
		
		public void editEdgeFirstVertex(edge edge, vertex2 newVertex){
			edge.editFirstVertex(newVertex.getVertexId());
			edges.put(edge.getEdgeId(), edge);
		}
		
		public int getVertexCount(){
			return vertices.size();
		}
		
		public int getEdgeCount(){
			return edges.size();
		}
		
		public ArrayList<edge> allNonSelfLoops(){
			ArrayList<edge> nonSelfLoops = new ArrayList<edge>();
			for (Integer edgeId: edges.keySet()){
				if(!edges.get(edgeId).isSelfLoop()){
					nonSelfLoops.add(edges.get(edgeId));
				}
			}
			return nonSelfLoops;
		}
		
		public ArrayList<Integer> allNonSelfLoopIDs(){
			ArrayList<Integer> nonSelfLoopIDs = new ArrayList<Integer>();
			for (Integer edgeId: edges.keySet()){
				if(!edges.get(edgeId).isSelfLoop()){
					nonSelfLoopIDs.add(edgeId);
				}
			}
			return nonSelfLoopIDs;
		}
		
		public void clearGraph(){
			edges.clear();
			vertices.clear();
			edgesUniqueId = 0;
			verticesUniqueId = 0;
			edgeArray = new int[0];
		}
		
		public void removeEdge(int edgeID){
			edges.remove(edgeID);
			returnEdges.remove(edgeID);
		}
		
		public void removeVertex(int vertexID){
			vertices.remove(vertexID);
		}


	}

