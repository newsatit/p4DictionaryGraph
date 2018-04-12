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

    private ArrayList<ArrayList<Boolean>> matrix;
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
        if(vertex.equals(null))
            return null;
        if(isInGraph(vertex))
            return null;

        vertices.add(vertex);
        matrix.add(new ArrayList<Boolean>());
        matrix.get(matrix.size()-1).add(new Boolean(false));

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

        int index1 = vertices.indexOf(vertex1);
        int index2 = vertices.indexOf(vertex2);

        if(index1 == -1 || index2 == -1)
            return false;

        matrix.get(index1).set(index2, true);
        matrix.get(index2).set(index1, true);


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

    private boolean isInGraph(E vertex) {
        for(int i = 0; i<vertices.size(); i++)
            if(vertices.get(i).equals(vertex))
                return true;
        return false;
    }

}
