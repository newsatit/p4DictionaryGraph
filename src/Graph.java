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
        if(vertex == null)
            return null;
        if(isInGraph(vertex))
            return null;

        vertices.add(vertex);
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

        int index1 = vertices.indexOf(vertex1);
        int index2 = vertices.indexOf(vertex2);

        if(index1 == -1 || index2 == -1)
            return false;

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

        int index1 = vertices.indexOf(vertex1);
        int index2 = vertices.indexOf(vertex2);

        if(index1 == -1 || index2 == -1)
            return false;

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
