import java.util.*;

public class EdgeGraphAdapter implements EdgeGraph {

    private Graph g;

    EdgeGraphAdapter(Graph g) { this.g = g; }

    public boolean addEdge(Edge e) {
	    if (this.g.hasEdge(e.getSrc(), e.getDst())) {
        return false;
      }
      this.g.addNode(e.getSrc());
      this.g.addNode(e.getDst());

      this.g.addEdge(e.getSrc(), e.getDst());
      return true;
    }

    public boolean hasNode(String n) {
	    return g.hasNode(n);
    }

    public boolean hasEdge(Edge e) {
      return g.hasEdge(e.getSrc(), e.getDst());
    }

    public boolean removeEdge(Edge e) {
      if (!g.hasEdge(e.getSrc(), e.getDst())) { return false; }
      g.removeEdge(e.getSrc(), e.getDst());
      if (g.succ(e.getSrc()).size() == 0) { g.removeNode(e.getSrc());}
      if (g.succ(e.getDst()).size() == 0) { g.removeNode(e.getDst());}
      return true;
    }

    public List<Edge> outEdges(String n) {
      ArrayList<Edge> toRet = new ArrayList<Edge>();
      if (!g.hasNode(n)) { return toRet; }
      for (String dest : g.succ(n)) {
        toRet.add(new Edge(n, dest));
      }
      return toRet;
    }

    public List<Edge> inEdges(String n) {
      ArrayList<Edge> toRet = new ArrayList<Edge>();
      if (!g.hasNode(n)) { return toRet; }
      for (String src : g.pred(n)) {
        toRet.add(new Edge(src, n));
      }
      return toRet;
    }

    public List<Edge> edges() {
      ArrayList<Edge> toRet = new ArrayList<Edge>();
      for (String src : g.nodes()) {
        for (String dest : g.succ(src)) {
          toRet.add(new Edge(src, dest));
        }
      }
      return toRet;
    }

    // EdgeGraph does not have a nodes funct, have to do it manually???
    // How to create a new edge graph? It complains about no initializator???
    // How to get 
    public EdgeGraph union(EdgeGraph g) {
      EdgeGraph toRet = new EdgeGraphAdapter(new ListGraph());

      for (Edge edge : g.edges()) {
        toRet.addEdge(edge);
      }
      for (Edge edge : this.edges()) {
        toRet.addEdge(edge);
      }
      return toRet;
    }

    public boolean hasPath(List<Edge> e) {

      System.out.println("hasPath\n");
      // Forst check if the edge list sent makes sense, that is, if the 
      // source of each is the dest of the next. IF not, raise exception.

      for (int i = 0; i < e.size()-1; i++) {
        if(e.get(i).getDst() != e.get(i+1).getSrc()) { throw new BadPath(); } 
      }

      // Then check if path is on the graph (what i did bellow.)
      // If so, return true, otherwise false.

      Queue<String> q = new LinkedList<String>();
      q.add(e.get(0).getSrc());
      for (Edge edge : e) {
        q.add(edge.getDst());
      }
      String src = q.poll();
      while(!q.isEmpty()) {
        System.out.println(src + "->" + q.peek());
        if (this.g.hasEdge(src, q.peek())) {
          src = q.poll();
        } else { return false; }
      }
      return true;
    }
}
