import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * This class adds additional functionality to the graph as a whole.
 * 
 * Contains an instance variable, {@link #graph}, which stores information for all the vertices and edges.
 * @see #populateGraph(String)
 *  - loads a dictionary of words as vertices in the graph.
 *  - finds possible edges between all pairs of vertices and adds these edges in the graph.
 *  - returns number of vertices added as Integer.
 *  - every call to this method will add to the existing graph.
 *  - this method needs to be invoked first for other methods on shortest path computation to work.
 * @see #shortestPathPrecomputation()
 *  - applies a shortest path algorithm to precompute data structures (that store shortest path data)
 *  - the shortest path data structures are used later to 
 *    to quickly find the shortest path and distance between two vertices.
 *  - this method is called after any call to populateGraph.
 *  - It is not called again unless new graph information is added via populateGraph().
 * @see #getShortestPath(String, String)
 *  - returns a list of vertices that constitute the shortest path between two given vertices, 
 *    computed using the precomputed data structures computed as part of {@link #shortestPathPrecomputation()}.
 *  - {@link #shortestPathPrecomputation()} must have been invoked once before invoking this method.
 * @see #getShortestDistance(String, String)
 *  - returns distance (number of edges) as an Integer for the shortest path between two given vertices
 *  - this is computed using the precomputed data structures computed as part of {@link #shortestPathPrecomputation()}.
 *  - {@link #shortestPathPrecomputation()} must have been invoked once before invoking this method.
 *  
 * @author sapan (sapan@cs.wisc.edu)
 * 
 */
public class GraphProcessor {


    private class Word{
        private String str;
        private ArrayList<ArrayList<String>> paths;
        private ArrayList<Integer> distance;

        Word(String str){
            this.str = str;
            this.paths = new ArrayList<ArrayList<String>>();
            this.distance = new ArrayList<Integer>();
        }
    }
    GraphADT<String> graph;
    ArrayList<Word> words;
    /**
     * Graph which stores the dictionary words and their associated connections
     */
    

    /**
     * Constructor for this class. Initializes instances variables to set the starting state of the object
     */
    public GraphProcessor() {
        this.graph = new Graph<>();
        this.words = new ArrayList<Word>();
    }
        
    /**
     * Builds a graph from the words in a file. Populate an internal graph, by adding words from the dictionary as vertices
     * and finding and adding the corresponding connections (edges) between 
     * existing words.
     * 
     * Reads a word from the file and adds it as a vertex to a graph.
     * Repeat for all words.
     * 
     * For all possible pairs of vertices, finds if the pair of vertices is adjacent {@link WordProcessor#isAdjacent(String, String)}
     * If a pair is adjacent, adds an undirected and unweighted edge between the pair of vertices in the graph.
     * 
     * @param filepath file path to the dictionary
     * @return Integer the number of vertices (words) added
     */
    public Integer populateGraph(String filepath) {
    		try {
    			Stream<String> wordStream = WordProcessor.getWordStream(filepath); 
    			
    		} catch (IOException e) {
    			
    			return 0;
    		}
    }

    
    /**
     * Gets the list of words that create the shortest path between word1 and word2
     * 
     * Example: Given a dictionary,
     *             cat
     *             rat
     *             hat
     *             neat
     *             wheat
     *             kit
     *  shortest path between cat and wheat is the following list of words:
     *     [cat, hat, heat, wheat]
     * 
     * @param word1 first word
     * @param word2 second word
     * @return List<String> list of the words
     */
    public List<String> getShortestPath(String word1, String word2) {
        return null;
    
    }
    
    /**
     * Gets the distance of the shortest path between word1 and word2
     * 
     * Example: Given a dictionary,
     *             cat
     *             rat
     *             hat
     *             neat
     *             wheat
     *             kit
     *  distance of the shortest path between cat and wheat, [cat, hat, heat, wheat]
     *   = 3 (the number of edges in the shortest path)
     * 
     * @param word1 first word
     * @param word2 second word
     * @return Integer distance
     */
    public Integer getShortestDistance(String word1, String word2) {
        return null;
    }
    
    /**
     * Computes shortest paths and distances between all possible pairs of vertices.
     * This method is called after every set of updates in the graph to recompute the path information.
     * Any shortest path algorithm can be used (Djikstra's or Floyd-Warshall recommended).
     */
    public void shortestPathPrecomputation() {
    		
    		// intialize words
    		Iterable<String> vertices = graph.getAllVertices();
    		words = new ArrayList<Word>();
    		for(String str : vertices) {
    			words.add(new Word(str));
    		}
    		boolean[] visited = new boolean[words.size()];
    		
    		// Find shortest from each word
    		for(int i = 0; i < words.size(); i++) {
    			Word word = words.get(i);
    			
    			// initialize word
    			for(int j = 0; j < word.distance.size(); j++) {
    				word.paths.add(new ArrayList<String>());
    				word.distance.add(-1);
    				visited[i] = false;
    			}
    			Queue<Integer> q = new LinkedList<Integer>();
    			// Add the source vertex
    			q.add(i);
    			visited[i] = true;
    			word.distance.set(i, 0);
    			ArrayList<String> startingPath = new ArrayList<String>();
    			startingPath.add(word.str);
    			word.paths.set(i, startingPath);
    			
    			while(!q.isEmpty()) {
    				int curIndex = q.poll();
    				String curStr = words.get(curIndex).str;
    				ArrayList<String> curPaths = word.paths.get(curIndex);
    				Integer curDis = word.distance.get(curIndex);
    				Iterable<String> neighbors = graph.getNeighbors(curStr);
    				
    				for(String neiStr : neighbors) {
    					int neiIndex = getWordIndex(neiStr);
    					if(!visited[neiIndex]) {
    						word.distance.set(neiIndex, curDis + 1);
    						ArrayList<String> neiPaths = new ArrayList<String>(curPaths);
    						neiPaths.add(words.get(neiIndex).str);
    						word.paths.set(neiIndex, neiPaths);
    						visited[neiIndex] = true;
    					}
    				}
    				
    			}
    			
    			// resetting the base case
    			for(int j = 0; j < words.size(); j++) {
    				ArrayList<String> paths = word.paths.get(j);
    				if(paths.size() == 1) {
    					word.paths.set(j, new ArrayList<String>());
    				} else if(paths.size() == 0) {
    					word.paths.set(j, null);
    				}
    			}
    			words.set(i, word);
    		}
    }
    
    private int getWordIndex(String str) {
    		for(int i = 0; i < words.size(); i++) {
    			if(words.get(i).str.equals(str)) {
    				return i;
    			}
    		}
    		return -1;
    }
}
