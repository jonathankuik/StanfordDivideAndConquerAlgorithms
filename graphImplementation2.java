package minCut;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class graphImplementation2 {
	
		private static void transferEdges(vertex2 fromVertex, vertex2 toVertex, graph3 graph){
			
			for(Integer edge : fromVertex.getEdgeList()){
				if(!toVertex.containsEdge(edge)){
					toVertex.addEdge(edge);
					adjustEdge(fromVertex, toVertex, edge, graph);					
				} else{
					toVertex.removeEdge(edge);
					graph.removeEdge(edge);
				}
			}
		}

		
		private static void adjustEdge(vertex2 fromVertex, vertex2 toVertex, int edgeID, graph3 graph){
				edge edge = graph.getEdge(edgeID);
				if(edge.getFirstVertexId() == fromVertex.getVertexId()){
					edge.editFirstVertex(toVertex.getVertexId());
				} else {				
					edge.editSecondVertex(toVertex.getVertexId());
				}
		}
	
	public static HashSet<Integer> findMinCut(graph3 graph, int[] randomEdgeArray){
		int randomEdgeIndex = 0;
		
		while (graph.getVertexCount() > 2){
			edge randomEdge;
			vertex2 firstVertex, secondVertex;
			int randomEdgeId = randomEdgeArray[randomEdgeIndex];
			
			if (!graph.containsEdge(randomEdgeId)){
				randomEdgeIndex++;
				continue;
			}
			
			randomEdge = graph.getEdge(randomEdgeId);
			firstVertex = graph.getVertex(randomEdge.getFirstVertexId());
			secondVertex = graph.getVertex(randomEdge.getSecondVertexId());
			
			//Remove the random edge from the lists of the vertices
			firstVertex.removeEdge(randomEdge.getEdgeId());
			secondVertex.removeEdge(randomEdge.getEdgeId());
							
			//step 1 adjust each of the edges identified in the first vertex to now be the second vertex
			//Delete any self loops
			transferEdges(firstVertex, secondVertex, graph);
			
			//remove the edge from the graph
			graph.removeEdge(randomEdgeId);
			
			//remove the first vertex
			graph.removeVertex(firstVertex.getVertexId());
			
			randomEdgeIndex++;
		
		}
		
		return graph.getEdges();
		}
		
		//Method to read in graph and turn it into a graph object
		public static void readFile(graph3 graph){
			try{
	            BufferedReader buf = new BufferedReader(new FileReader("graph.txt"));
	            String lineJustFetched = null;
	            vertex2 firstVertex, secondVertex;
	            edge edgeToAdd;
	            int edgeIndex = 0; 
	            
	            while(true){
	                lineJustFetched = buf.readLine();
	                if(lineJustFetched == null){  
	                    break; 
	                }else{
	                	String[] wordsArray;
	                    wordsArray = lineJustFetched.split("\\s+");
	                    firstVertex = new vertex2(Integer.parseInt(wordsArray[0]));
	                    if(!graph.containsVertex(firstVertex.getVertexId())){
	                    	graph.addVertex(firstVertex);
	                    }
	                    for(int i = 1; i < wordsArray.length; i++){
	                    	secondVertex = new vertex2(Integer.parseInt(wordsArray[i]));
	                    	if(secondVertex.getVertexId() > firstVertex.getVertexId()){
	                    		edgeToAdd = new edge(edgeIndex, firstVertex.getVertexId(), secondVertex.getVertexId());
	                    		graph.addEdge(edgeToAdd);
	                    		if(!graph.containsVertex(secondVertex.getVertexId())){
	    	                    	graph.addVertex(secondVertex);
	    	                    }
	                    		graph.addEdgeToVertex(secondVertex, edgeToAdd.getEdgeId());
	                    		graph.addEdgeToVertex(firstVertex, edgeToAdd.getEdgeId());
	                    		edgeIndex++;
	                    		}
	                    	}
	                   // lineJustFetched = buf.readLine();
	                	}
	            }

	            buf.close();

	        }catch(Exception e){
	            e.printStackTrace();
	        }
	    }
		
		private static void shuffle(int[] array) {
	        int n = array.length;
	        Random random = new Random();
	        // Loop over array.
	        for (int i = 0; i < array.length; i++) {
	            // Get a random index of the array past the current index.
	            // ... The argument is an exclusive bound.
	            //     It will not go past the array's end.
	            int randomValue = i + random.nextInt(n - i);
	            // Swap the random element with the present element.
	            int randomElement = array[randomValue];
	            array[randomValue] = array[i];
	            array[i] = randomElement;
	        }
	    }
		
		

	public static void main(String[] args) {
			
			graph3 smallGraph = new graph3();
			graph3 graphForAnswers = new graph3();
			readFile(graphForAnswers);
			
			int[] randomEdge = graphForAnswers.getEdgeArray();
			//int[] randomEdge = {12, 4, 13, 2, 11, 1, 6, 3, 10, 5, 9, 7, 0, 8};
			int edgeCount = -1;
			HashSet<Integer> edgeIDs = new HashSet();
			HashSet<Integer> answerEdges = new HashSet();
								
			for (int i = 0; i<100000; i++){
				readFile(smallGraph);
				
				edgeIDs = findMinCut(smallGraph, randomEdge);
				
				//Track edge count and the specific edges
				if (edgeCount == -1){
					edgeCount = edgeIDs.size();
					answerEdges.addAll(edgeIDs);
				} else if(edgeCount > edgeIDs.size()){
					edgeCount = edgeIDs.size();
					answerEdges.clear();
					answerEdges.addAll(edgeIDs);
				}
				System.out.println("Number of edges " + edgeCount);
				shuffle(randomEdge);
				smallGraph.clearGraph();
			}
			
			//Print out which edges are included in the minCut
			System.out.print("These are the edges in the min cut ");
			for(Integer each: answerEdges){
				System.out.print(graphForAnswers.getEdgeString(each));
				
			}
		}

	}

	 
