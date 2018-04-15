
/////////////////////////////////////////////////////////////////////////////
// Semester:         CS400 Spring 2018
// PROJECT:          X Team #4 Dictionary Graph
// FILES:            GraphProcessor.java, GraphProcessorTest.java, Graph.java, GraphTest.java
//					 WordProcessor.java, 
//
// USER:             Jake Rymsza, Eden Schuette, Ben Schulman, Andrew Schaefer, Dawanit Satitsumpun
//
// Instructor:       Deb Deppeler (deppeler@cs.wisc.edu)
// Bugs:             no known bugs
//
//////////////////////////// 80 columns wide //////////////////////////////////

import java.util.ArrayList;


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

    // Initializes the adjacency matrix and list of vertices
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
        // if the vertex is already in the graph then return null
        if(isInGraph(vertex))
            return null;

        // if we are adding the first vertex we have special conditions
        if(vertices.size() == 0){
            vertices.add(vertex); // add the vertex to the list
            matrix.add(new ArrayList<Boolean>()); // add the first row to the matrix
            matrix.get(0).add(new Boolean(false)); // add the first "column" and assign the boolean value to false
            return vertex;
        }
        // If it is not the first vertex we do the following

        //add the vertex to the list
        vertices.add(vertex);
        //add a new row to the adjacency matrix
        matrix.add(new ArrayList<Boolean>());

        // add existing columns to the new row to keep the matrix square
        for(int j = 0; j < matrix.get(0).size(); j++){
            // set the new values to false because there is not an edge
            matrix.get(matrix.size()-1).add(new Boolean(false));
        }

        // add one new column to all rows
        for(int i = 0; i<matrix.size();i++){
            // set the initial values to false because there is no edge
            matrix.get(i).add(new Boolean(false));
        }


        // return the vertex that was added
        return vertex;
    }



    /**
     * {@inheritDoc}
     */
    @Override
    public E removeVertex(E vertex) {
        if(vertex == null)
            return null;

        // get the index of the vertex in the list
        int index = vertices.indexOf(vertex);

        // if the vertex is not in the graph return null
        if(index == -1)
            return null;

        //remove from all places

        //remove the vertex from the list
        vertices.remove(index);

        // remove the row vertex
        matrix.remove(index);
        // remove the columns from each row
        for(int i = 0; i<matrix.size(); i++){
            matrix.get(i).remove(index);
        }

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

        // if either are not in the graph return false
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

        // if either of the vertices are not in the graph return null
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

        // find the vertices
        int index1 = vertices.indexOf(vertex1);
        int index2 = vertices.indexOf(vertex2);

        // if they are not in the graph return false
        if(index1 == -1 || index2 == -1)
            return false;

        // returns whatever is stored in the adjacency matrix
        return matrix.get(index1).get(index2);

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Iterable<E> getNeighbors(E vertex) {
        if(vertex == null)
            return null;

        // get the index of the vertex
        int index = vertices.indexOf(vertex);

        // return null if the vertex is not in the graph
        if(index == -1)
            return null;

        // create a new list that stores neighbors
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
        return vertices; // returns the list of vertices
    }

    /**
     * Checks if a vertex is in a graph
     *
     * @param vertex the vertex to check
     * @return true if vertex is in the graph
     */
    private boolean isInGraph(E vertex) {
        //Loops through the vertex array to find vertex
        for(int i = 0; i<vertices.size(); i++)
            if(vertices.get(i).equals(vertex)) // returns true if it is found
                return true;
        return false;
    }

    /**
     * Debug method to help. Prints the adjacency matrix
     */
    private void printMatrix(){
        for(int i =0; i<matrix.size(); i++){
            for(int j =0; j<matrix.get(i).size(); j++){
                System.out.print(matrix.get(i).get(j) + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
