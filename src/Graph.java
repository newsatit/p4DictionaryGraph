import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Undirected and unweighted graph implementation
 * 
 * @param <E> type of a vertex
 * 
 * @author sapan (sapan@cs.wisc.edu)
 * 
 */
public class Graph<E> implements GraphADT<E> {

    class GraphNode<E>{
        private E data;
        private ArrayList<GraphNode<E>> adjacencyList;

        private GraphNode(E data){
            this.data = data;
        }

        private E getData(){
            return data;
        }

        private void setData(E data){
            this.data = data;
        }

        private ArrayList<GraphNode<E>> getAdjacencyList (){
            return adjacencyList;
        }

        private void addVertex(E newData){
            adjacencyList.add(new GraphNode<E>(newData));
        }

    }

    /**
     * Instance variables and constructors
     */

    /**
     * {@inheritDoc}
     */
    @Override
    public E addVertex(E vertex) {
        
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public E removeVertex(E vertex) {
        
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean addEdge(E vertex1, E vertex2) {
        
    }    

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean removeEdge(E vertex1, E vertex2) {
        
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isAdjacent(E vertex1, E vertex2) {
        
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Iterable<E> getNeighbors(E vertex) {
        
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Iterable<E> getAllVertices() {
        
    }

}
