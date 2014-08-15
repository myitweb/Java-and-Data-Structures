/* Design a class in java for implementing the graph */
// Source : https://sites.google.com/site/rickcreamer/Home/java/basicjavagraphclasses/source-code


import java.util.*;
import java.io.*;

public class GraphTest {
    public static void main( String [] args ) {
        test1();
        test2();
    }
    ////////////////////////
    private static void test1() {
        for ( int nodeCount = 0; nodeCount <  5; ++nodeCount ) {
            for ( int edgeCount = -1; edgeCount <= nodeCount * 10; ++edgeCount ) {
                try {
                    System.out.println( "\n----- Test case nodeCount: " + nodeCount + " edgeCount: " + edgeCount + " -----" );
                    System.out.flush();
                    test( nodeCount, edgeCount, true );
                }
                catch ( Exception e ) {                   
                    System.out.println( "Graph creation failed: " + e.getMessage() );
                }               
            }
        }
        System.out.flush();
    }
    ////////////////////////
    private static void test2() {
        int nodeCount = 10000;
        int edgeCount = nodeCount * 10;
        System.out.println( "\n----- Test case nodeCount: " + nodeCount + " edgeCount: " + edgeCount + " -----" );
        test( nodeCount, edgeCount, false );       
    }
    ////////////////////////
    private static void test( int nodeCount, int edgeCount, boolean dumpGraph ) {
        Graph rg = Graph.createRandomGraph( nodeCount, edgeCount );       
        if ( !dumpGraph )
            System.out.print( rg.getGraphSummary() );       
        // Dump degree historgram
        int maxDegree = rg.computeMaxDegree();
        for ( int i = 0; i  <= maxDegree; ++i ) {
            int nodeCountWithDegree = rg.countNodesWithDegree( i );
            System.out.println( "Nodes with degree " + i + ": " + nodeCountWithDegree );
        }       
        // Test for self-looping nodes
        System.out.println( "Exists self-loops: " + rg.hasSelfLoops() );
        if ( dumpGraph )
            System.out.print( rg.toStringVerbose() );
    }
}

//////////////////////////////////////////////////////////////////////////////

final class Graph {
   
    private SortedMap< String, Node > nodeMap = null;
    private Map< String, Edge > edgeMap = null;
   
    public Graph() {
        nodeMap = new TreeMap< String, Node >( new Comparator< String >() {
            public int compare( String s1, String s2 ) {
                return s1.compareTo( s2 );
            }
        } );
        edgeMap = new HashMap< String, Edge >();
    }
    ////////////////////////
    public static Graph createRandomGraph( int nodeCount, int edgeCount ) {
        if ( nodeCount < 1 || edgeCount < 0 ) throw new IllegalArgumentException( "nodeCount must be >= 1 and edgeCount must be >= 0!" );
        Random rnGen = new Random( System.currentTimeMillis() );
        int maxEdges = getMaxEdgesForGraph( nodeCount );
        if ( edgeCount > maxEdges )
            throw new IllegalArgumentException( "Input edgeCount (" + edgeCount + ") exceeds maximum possible edges for graph with " + nodeCount + " nodes!" );
        // Create empty Graph object
        Graph g = new Graph();
        // Create temp array to hold node keys - required for getRandomEdge()
        String [] nodeKeys = new String [ nodeCount ];
        // Create and add nodeList
        for ( int i = 0; i < nodeCount; ++i ) {
            String nodeId = Integer.toString( g.getNodeCount() );
            nodeKeys[ i ] = nodeId;
            Node n = new Node( nodeId );
            g.addNode( n ); // Let list index be node's id
        }
        // Create and add edgeList
        for ( int i = 0; i < edgeCount; ++i ) {
            Edge e = Graph.getRandomEdge( rnGen, g, nodeKeys );
            g.addEdge( e );
        }
        return g;
    }
    ////////////////////////
    public void addNode( Node n ) {
        if ( n == null ) throw new IllegalArgumentException( "Argument must be non-null!" );
        if ( nodeMap.get( n.getId() ) != null ) throw new IllegalArgumentException( "Attempt to add node with duplicate id <" + n.getId() + ">" );
        nodeMap.put( n.getId(), n);
    }
    ////////////////////////
    private static Edge getRandomEdge( Random rnGen, Graph g, String [] keys ) {
        if ( g.getNodeCount() < 2 ) throw new IllegalStateException( "Attempt to add edge when < 2 nodes are in graph!" );
        if ( keys == null || keys.length != g.getNodeCount() ) throw new IllegalArgumentException( "keys argument null or wrong size!" );
        Node n1 = null;
        Node n2 = null;
        Edge retEdge = null;
        while ( true ) {
            n1 = g.nodeMap.get( keys[ rnGen.nextInt( g.getNodeCount() ) ] );
            n2 = g.nodeMap.get( keys[ rnGen.nextInt( g.getNodeCount() ) ] );
            if ( n1 == n2 ) // Skip if already have edge between these two nodes
                continue;
            String id = Edge.computeDefaultEdgeId( n1, n2 );
            if ( g.edgeMap.get( id ) != null )
                continue;
            retEdge = new Edge( n1, n2, id );
            break;
        }
        return retEdge;
    }
    ////////////////////////
    public void addEdge( Edge e ) {
        if ( edgeMap.get( e.getId() ) != null ) throw new IllegalStateException( "Attemp to add edge wiith duplicate id <" + e.getId() + ">" );
        edgeMap.put( e.getId(), e );
        e.getN1().incrementDegree();
        e.getN2().incrementDegree();
    }
    ////////////////////////
    public int getNodeCount() {
        return nodeMap.size();
    }
    ////////////////////////
    public int getEdgeCount() {
        return edgeMap.size();
    }
    ////////////////////////
    public int countNodesWithDegree( int degree ) {
        int sum = 0;
        for ( Node n : nodeMap.values() )
            if ( n.getDegree() == degree )
                ++sum;
        return sum;
    }
    ////////////////////////
    public int computeMaxDegree() {
        int maxDegree = 0;
        for ( Node n : nodeMap.values() ) {
            if ( maxDegree < n.getDegree() )
                maxDegree = n.getDegree();
        }
        return maxDegree;
    }
    ////////////////////////
    public String getGraphSummary() {
        StringBuffer sb = new StringBuffer();
        sb.append( "Graph Object Summary:\n" );
        sb.append( "\tNode Count: " + getNodeCount() + "\n" );
        sb.append( "\tEdge Count: "  + getEdgeCount() + "\n" );
        return sb.toString();
    }
    ////////////////////////
    public String toStringVerbose() {
        StringBuffer sb = new StringBuffer();
        sb.append( "Graph Object Dump:\n" );
        sb.append( "\tNode Count: " + getNodeCount() + "\n" );
        sb.append( "\tEdge Count: "  + getEdgeCount() + "\n" );
        sb.append( "\tNodes: \n" );
        int nodeIndex = 0;
        for ( Node n : nodeMap.values() )
            sb.append( "\t\tNode[ " + nodeIndex++ + " ]: " + n.toString() + "\n" );
        sb.append( "\tEdges: \n" );
        int edgeIndex = 0;
        for ( Edge e : edgeMap.values() )
            sb.append( "\t\tEdge[ " + edgeIndex++ + " ]: " + e.toString() + "\n" );
        return sb.toString();
    }
    ////////////////////////
    public static int getMaxEdgesForGraph( int nodeCount ) {
        if  ( nodeCount < 0 ) throw new IllegalArgumentException( "nodeCount must be >= 0!" );
        if ( nodeCount == 0 ) return 0;
        int n = nodeCount - 1;
        // Use math formula sum of first n integers where n here is nodeCount - 1
        int maxEdges = ( n * n + n )/2;
        return maxEdges;
    }
    ////////////////////////
    public boolean hasSelfLoops() {
        for ( Edge e : edgeMap.values() )
            if ( e.getN1() == e.getN2() )
                return true;
        return false;
    }
}

//////////////////////////////////////////////////////////////////////////////

final class Node implements Comparable< Node > {
   
    private final String id;
    private int degree = 0;
   
    private Node() {
        id = null;
    }
    ////////////////////////
    public Node( String id ) {
        this.id = id;
    }
    ////////////////////////
    public String getId() {
        return id;
    }
    ////////////////////////
    public synchronized int getDegree() {
        return degree;
    }
    ////////////////////////
    public int compareTo( Node n ) {
        return getId().compareTo( n.getId() );
    }
    ////////////////////////
    public synchronized void incrementDegree() {
        ++degree;
    }
    ////////////////////////
    @Override
    public synchronized String toString() {
        return "Node: id: " + id + " degree: " + degree;
    }
}

//////////////////////////////////////////////////////////////////////////////

final class Edge {
    private final Node n1;
    private final Node n2;
    private final String id;
    private Edge() {
        n1 = n2 = null;
        id = null;
    }
    ////////////////////////
    public Edge( Node n1, Node n2, String id ) {
        if ( n1 == null || n2== null ) throw new IllegalArgumentException( "Nodes must not be null!" );
        if ( n1 == n2 ) throw new IllegalArgumentException( "Argument nodes must not be the same node!" );
        this.n1 = n1;
        this.n2 = n2;
        this.id = ( id == null ) ? computeDefaultEdgeId( n1, n2 ) : id;
    }
    ////////////////////////
    public String getId() {
        return id;
    }
    ////////////////////////
    public static String computeDefaultEdgeId( Node n1, Node n2 ) {
        if ( n1 == null || n2 == null )
            throw new IllegalArgumentException( "Arguments must not be null!" );
        if ( n1 == n2 )
            throw new IllegalArgumentException( "Argument nodes must be for different nodes!" );
        if ( n1.compareTo( n2 ) < 0 )
            return n1.getId() + ":" + n2.getId();
        else
            return n2.getId() + ":" + n1.getId();
    }
    ////////////////////////
    public Node getN1() {
        return n1;
    }
    ////////////////////////
    public Node getN2() {
        return n2;
    }
    ////////////////////////
    @Override
    public String toString() {
        return "Edge id: " + id + " n1: " + n1.getId() + " n2: " + n2.getId();
    }
}
