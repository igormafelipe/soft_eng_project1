import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {

    // Run "java -ea Main" to run with assertions enabled (If you run
    // with assertions disabled, the default, then assert statements
    // will not execute!)
    
    public static void test1() {
		Graph g = new ListGraph();
		assert g.addNode("a");
		assert g.hasNode("a");
    }

	public static void test3() {
		Graph g = new ListGraph();
		assert g.addNode("a");
		assert g.addNode("b");
		assert g.addNode("c");
		assert g.addNode("d");
		for(String z : g.nodes()) {
			System.out.println(z);
		}
    }

	public static void test4() {
		Graph g = new ListGraph();
		System.out.println("Testing addNode\n");
		assert g.addNode("a");
		assert g.addNode("b");
		assert g.addNode("c");
		assert g.addNode("d");
		System.out.println("Testing hasNode\n");
		assert g.hasNode("a");
		assert g.hasNode("b");
		assert g.hasNode("c");
		assert g.hasNode("d");
		System.out.println("Testing nodes");
		for(String z : g.nodes()) {
			System.out.println(z);
		}
		System.out.println("Testing addEdge\nAdding edges that do not exist:\n");
		assert g.addEdge("a", "b");
		assert g.addEdge("a", "d");
		assert g.addEdge("b", "c");
		assert g.addEdge("b", "a");
		assert g.addEdge("c", "b");
		assert g.addEdge("c", "d");
		assert g.addEdge("d", "d");
		// System.out.println("Adding edges that already exist: \n");
		// assert g.addEdge("a", "b");
		// assert g.addEdge("d", "d");
		System.out.println("Printing out all edges: \n");
		for(String z : g.nodes()) {
			System.out.println("Node: " + z + "\nEdges:");
			for(String s : g.succ(z)) {
				System.out.println(s);
			}
		}
		System.out.println("\nTesting remove edge:");
		assert g.removeEdge("a", "b");
		assert g.removeEdge("b", "c");
		for(String z : g.nodes()) {
			System.out.println("Node: " + z + "\nEdges:");
			for(String s : g.succ(z)) {
				System.out.println(s);
			}
		}
		// assert g.removeEdge("b", "c");
		System.out.println("\nTesting remove node:");
		g.removeNode("d");
		for(String z : g.nodes()) {
			System.out.println("Node: " + z + "\nEdges:");
			for(String s : g.succ(z)) {
				System.out.println(s);
			}
		}
		// assert g.removeNode("f");
		g.addEdge("a", "a");
		g.addEdge("b", "a");
		g.addEdge("c", "a");
		g.addNode("d");
		g.addEdge("d", "a");
		g.addEdge("d", "d");
		g.addEdge("c", "c");
		System.out.println("Testing Pred: \n");
		for(String z : g.nodes()) {
			System.out.println("Node: " + z + "\nEdges:");
			for(String s : g.pred(z)) {
				System.out.println(s);
			}
		}
		System.out.println("Testando o union\n");
		Graph e = new ListGraph();
		Graph f = new ListGraph();
		e.addNode("a");
		e.addNode("b");
		e.addNode("c");

		f.addNode("c");
		f.addNode("d");
		f.addNode("e");

		e.addEdge("a", "b");
		e.addEdge("b", "c");
		e.addEdge("c", "a");

		f.addEdge("c", "d");
		f.addEdge("d", "e");
		f.addEdge("e", "c");
		e = f.union(e);
		for(String z : e.nodes()) {
			System.out.println("Node: " + z + "\nEdges:");
			for(String s : e.succ(z)) {
				System.out.println(s);
			}
		}

		Graph l = new ListGraph();
		l.addNode("a");
		l.addNode("b");
		l.addNode("c");
		l.addNode("d");
		l.addEdge("a", "c");
		l.addEdge("a", "b");
		l.addEdge("c", "d");
		
		Set<String> toTest = new HashSet<>(Arrays.asList("a", "b", "c", "e"));
		l = l.subGraph(toTest);

		System.out.print("Testing subGraph\n");
		for(String z : l.nodes()) {
			System.out.println("Node: " + z + "  Edges:");
			for(String s : l.succ(z)) {
				System.out.println(s);
			}
		}
		System.out.println(l.connected("a", "a"));
	}

    // public static void test2() {
	// Graph g = new ListGraph();
	// EdgeGraph eg = new EdgeGraphAdapter(g);
	// Edge e = new Edge("a", "b");
	// assert eg.addEdge(e);
	// assert eg.hasEdge(e);
    // }
    

	public static void test5(){
		Graph k = new ListGraph();
		EdgeGraphAdapter g = new EdgeGraphAdapter(k);

		assert g.addEdge(new Edge("a", "b"));
		assert g.addEdge(new Edge("a", "c"));
		assert g.addEdge(new Edge("a", "d"));
		assert g.addEdge(new Edge("b", "d"));
		assert g.addEdge(new Edge("b", "e"));
		assert g.addEdge(new Edge("d", "d"));
		// assert g.addEdge(new Edge("d", "d"));

		for (Edge edge : g.edges()) {
			System.out.println(edge.getSrc() + "->" + edge.getDst());
		}

		assert g.hasNode("a");
		assert g.hasNode("b");
		assert g.hasNode("c");
		assert g.hasNode("d");
		assert g.hasNode("e");

		assert g.hasEdge(new Edge("a", "b"));
		assert g.hasEdge(new Edge("a", "c"));
		assert g.hasEdge(new Edge("a", "d"));
		assert g.hasEdge(new Edge("b", "d"));
		assert g.hasEdge(new Edge("b", "e"));
		assert g.hasEdge(new Edge("d", "d"));
		// assert g.hasEdge(new Edge("a", "b"));

		assert g.removeEdge(new Edge("a", "b"));
		assert g.removeEdge(new Edge("a", "c"));
		assert g.removeEdge(new Edge("b", "d"));
		assert g.removeEdge(new Edge("b", "e"));

		assert g.hasNode("a");
		// assert g.hasNode("b");
		// assert g.hasNode("c");
		assert g.hasNode("d");
		// assert g.hasNode("e");

		System.out.println("\n\nTesting remove edge:\n");
		for (Edge edge : g.edges()) {
			System.out.println(edge.getSrc() + "->" + edge.getDst());
		}


		System.out.println("\n");
		assert g.addEdge(new Edge("a", "b"));
		assert g.addEdge(new Edge("a", "c"));
		assert g.addEdge(new Edge("b", "d"));
		assert g.addEdge(new Edge("b", "e"));

		for (Edge edge : g.edges()) {
			System.out.println(edge.getSrc() + "->" + edge.getDst());
		}
		System.out.println("\n\nTesting out Edges:\n");
		for (Edge edge : g.outEdges("a")) {
			System.out.println(edge.getSrc() + "->" + edge.getDst());
		}
		
		System.out.println("\n\nTesting in Edges:\n");
		for (Edge edge : g.inEdges("d")) {
			System.out.println(edge.getSrc() + "->" + edge.getDst());
		}

		System.out.println("\n Testing union: \n");

		Graph xz = new ListGraph();
		EdgeGraphAdapter h = new EdgeGraphAdapter(xz);

		assert h.addEdge(new Edge("a", "k"));
		assert h.addEdge(new Edge("a", "a"));
		assert h.addEdge(new Edge("a", "d"));
		assert h.addEdge(new Edge("b", "a"));
		assert h.addEdge(new Edge("k", "j"));
		assert h.addEdge(new Edge("j", "j"));


		// Adding a->k, a->a, b->a, b->c, k->j, j->j

		System.out.println("Graph g:");
		for (Edge edge : g.edges()) {
			System.out.println(edge.getSrc() + "->" + edge.getDst());
		}
		System.out.println("\nGraph h:");
		for (Edge edge : h.edges()) {
			System.out.println(edge.getSrc() + "->" + edge.getDst());
		}
		System.out.println("\nUnion:");
		for (Edge edge : h.union(g).edges()) {
			System.out.println(edge.getSrc() + "->" + edge.getDst());
		}



		Graph io = new ListGraph();
		EdgeGraphAdapter jk = new EdgeGraphAdapter(io);

		assert jk.addEdge(new Edge("a", "b"));
		assert jk.addEdge(new Edge("b", "c"));
		assert jk.addEdge(new Edge("c", "c"));
		assert jk.addEdge(new Edge("c", "d"));
		assert jk.addEdge(new Edge("d", "e"));

		System.out.println("Graph jk:\n");
		for (Edge edge : jk.edges()) {
			System.out.println(edge.getSrc() + "->" + edge.getDst());
		}

		List<Edge> e = new ArrayList<Edge>();
		e.add(new Edge("a", "b"));
		e.add(new Edge("b", "c"));
		e.add(new Edge("c", "c"));
		e.add(new Edge("c", "d"));
		assert jk.hasPath(e);
	}

    public static void main(String[] args) {
	//test1();
	//test4();
	test5();
	// test2();
    }
}