import java.awt.image.AreaAveragingScaleFilter;
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

    //keeps track of edges
    private ArrayList<ArrayList<Boolean>> matrix;
    //keeps track of vertices
    private ArrayList<E> vertices;

    public Graph(){
        matrix = new ArrayList<ArrayList<Boolean>>();
        vertices = new ArrayList<E>();
    }

    /**
     * Instance variables and constructors
     */

    /**
     * {@inheritDoc}
     */
    @Override
    public E addVertex(E vertex) {
        if(vertex == null)
            return null;
        if(isInGraph(vertex))
            return null;

        //add the element to the graph
        vertices.add(vertex);
        //add a new row to the adjacency matrix and set the new values equal to false
        matrix.add(new ArrayList<Boolean>());
        for(int i = 0; i<matrix.size();i++){
            matrix.get(i).add(new Boolean(false));
        }

        return vertex;
    }



    /**
     * {@inheritDoc}
     */
    @Override
    public E removeVertex(E vertex) {
        if(vertex == null)
            return null;

        int index = vertices.indexOf(vertex);

        if(index == -1)
            return null;

        //remove from all places
        vertices.remove(index);
        matrix.get(index).remove(index);
        matrix.remove(index);

        return vertex;

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean addEdge(E vertex1, E vertex2) {
        if(vertex1 == null || vertex2 == null)
            return false;
        if(vertex1.equals(vertex2))
            return false;

        //get the indexes of the nodes
        int index1 = vertices.indexOf(vertex1);
        int index2 = vertices.indexOf(vertex2);

        if(index1 == -1 || index2 == -1)
            return false;

        //find them in the matrix to add the edge
        matrix.get(index1).set(index2, true);
        matrix.get(index2).set(index1, true);

        return true;

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean removeEdge(E vertex1, E vertex2) {
        if(vertex1 == null || vertex2==null)
            return false;
        if(vertex1.equals(vertex2))
            return false;

        //find the indexes
        int index1 = vertices.indexOf(vertex1);
        int index2 = vertices.indexOf(vertex2);

        if(index1 == -1 || index2 == -1)
            return false;

        //find the vertexes in the matrix and remove the edge
        matrix.get(index1).set(index2, false);
        matrix.get(index2).set(index1, false);

        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isAdjacent(E vertex1, E vertex2) {
        if(vertex1 == null || vertex2 == null)
            return false;
        if(vertex1.equals(vertex2))
            return false;

        int index1 = vertices.indexOf(vertex1);
        int index2 = vertices.indexOf(vertex2);

        if(index1 == -1 || index2 == -1)
            return false;

        // searches the matrix to see if the location has a true
        return matrix.get(index1).get(index2);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Iterable<E> getNeighbors(E vertex) {
        if(vertex == null)
            return null;

        int index = vertices.indexOf(vertex);

        if(index == -1)
            return null;

        ArrayList<E> neighbors = new ArrayList<E>();

        //loops through the matrix and adds to the list if there is an edge
        for(int i=0; i<matrix.get(index).size(); i++){
            if(matrix.get(index).get(i)) {
                neighbors.add(vertices.get(i));
            }
        }

        return neighbors;

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Iterable<E> getAllVertices() {
        return vertices;
    }

    private boolean isInGraph(E vertex) {
        for(int i = 0; i<vertices.size(); i++)
            if(vertices.get(i).equals(vertex))
                return true;
        return false;
    }

}
