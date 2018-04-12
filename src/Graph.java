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

    private ArrayList<GraphNode<E>> nodes;

    class GraphNode<E>{
        private E data;
        private ArrayList<GraphNode<E>> edges;

        private GraphNode(E data){
            this.data = data;
        }

        private E getData(){
            return data;
        }

        private void setData(E data){
            this.data = data;
        }

        private ArrayList<GraphNode<E>> getEdges (){
            return edges;
        }

        private void addVertex(E newData){
            edges.add(new GraphNode<E>(newData));
        }

    }


    public Graph(){
        nodes = new ArrayList<GraphNode<E>>();
    }

    /**
     * Instance variables and constructors
     */

    /**
     * {@inheritDoc}
     */
    @Override
    public E addVertex(E vertex) {
        if(vertex.equals(null))
            return null;

        if(isInGraph(vertex))
           return null;

        nodes.add(new GraphNode<E>(vertex));
        return vertex;

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
        if(vertex1.equals(null) || vertex2.equals(null))
            return false;
        if(vertex1.equals(vertex2))
            return false;
        if(!isInGraph(vertex1) || !isInGraph(vertex2))
            return false;


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

    private boolean isInGraph(E vertex){
        for(int i = 0; i < nodes.size(); i++){
            if(nodes.get(i).data.equals(vertex))
                return true;
        }
        return false;
    }

}
