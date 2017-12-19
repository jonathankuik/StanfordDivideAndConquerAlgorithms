package minCut;

public class edge {

		private int edgeId;
		private int first;
		private int second;
		private String vertex;
		private boolean selfLoop;
		
		//This constructor builds an edge with an edgeId of -1 and assumes that it is not a self loop
		//The -1 is set to the unique id of that graph if we're just adding an edge
		public edge(int firstVertex, int secondVertex){
			buildEdge(-1, firstVertex, secondVertex);	
		}
		
		//This constructor allows for passing along a specific edge Id
		public edge(int edgeId, int firstVertex, int secondVertex){
			buildEdge(edgeId, firstVertex, secondVertex);
		}
		
		private void buildEdge(int edgeId, int firstVertex, int secondVertex){
			this.edgeId = edgeId;
			this.first = firstVertex;
			this.second = secondVertex;
			this.vertex = "(" + Integer.toString(first) + "," + Integer.toString(second) + ")";
			if(this.first == this.second){
				this.selfLoop = true;
			} else {
				this.selfLoop = false;
			}
		}
			
		
		public void editFirstVertex(int x){
			this.first = x;
			this.vertex = "(" + Integer.toString(first) + "," + Integer.toString(second) + ")";
			if(this.first == this.second){
				this.selfLoop = true;
			} else {
				this.selfLoop = false;
			}
		}
		
		public void editSecondVertex(int x){
			this.second = x;
			this.vertex = "(" + Integer.toString(first) + "," + Integer.toString(second) + ")";
			if(this.first == this.second){
				this.selfLoop = true;
			} else {
				this.selfLoop = false;
			}
		}
		
		public int getFirstVertexId(){
			return first;
		}
		
		public int getSecondVertexId(){
			return second;
		}
		
		public int getEdgeId(){
			return edgeId;
		}
		
		public String toString(){
			return vertex;
		}
		
		public void editEdgeId(int x){
			this.edgeId = x;
		}
	
		public boolean isSelfLoop(){
			return this.selfLoop;
		}
}
