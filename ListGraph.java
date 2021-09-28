import java.util.*;

public class ListGraph implements Graph {
    private HashMap<String, LinkedList<String>> nodes = new HashMap<>();

    // Does put override the node?
    public boolean addNode(String n) {
        if (nodes.containsKey(n)) { return false; }
        nodes.put(n, new LinkedList<String>());
        return true;
    }

    public boolean addEdge(String n1, String n2) {
        if ((nodes.containsKey(n1) == false || nodes.containsKey(n2) == false)) {
            throw new NoSuchElementException();
        }
         for (String n : nodes.get(n1)) { 
            if (n == n2) {
                return false;
            }
        }
        LinkedList<String> temp = nodes.get(n1);
        temp.add(n2);
        nodes.put(n1, temp);
        return true;
    }

    public boolean hasNode(String n) {
	     return nodes.containsKey(n);
    }

    public boolean hasEdge(String n1, String n2) {
        LinkedList<String> tempEdges = nodes.get(n1);
        if (tempEdges == null) { return false; }
        for (String n : nodes.get(n1)) { 
            if (n == n2) {
                return true;
            }
        }
        return false;
    }

    public boolean removeNode(String n) {
        if (!nodes.containsKey(n)) { return false; }
        for (String s : nodes.keySet()) {
            LinkedList<String> temp = nodes.get(s);
            temp.remove(n);
            nodes.put(s, temp);
        }
        nodes.remove(n);
        return true;
    }

    public boolean removeEdge(String n1, String n2) {
        if ((nodes.containsKey(n1) == false || nodes.containsKey(n2) == false)) {
            throw new NoSuchElementException();
        }
        LinkedList<String> temp = nodes.get(n1);
        int temp_size = temp.size();
        temp.removeFirstOccurrence(n2);
        if (temp.size() == temp_size-1) {
            nodes.put(n1, temp);
            return true;
        }
        return false;
    }

    public List<String> nodes() {
	    List<String> toRet = new ArrayList<String>();
        HashSet<String> hset = new HashSet<String>(nodes.keySet());
        Iterator<String> it = hset.iterator();

        while(it.hasNext()) {
            toRet.add(it.next());
        }
        return toRet;
    }

    public List<String> succ(String n) {
        if (!nodes.containsKey(n)) { throw new NoSuchElementException(); }
        return nodes.get(n);
    }

    public List<String> pred(String n) {
        if (!nodes.containsKey(n)) { throw new NoSuchElementException(); }
        List<String> edgeList = new ArrayList<String>();
        for (String s : nodes.keySet()) {
            if (nodes.get(s).contains(n)) {
                edgeList.add(s);
            }
        }
        return edgeList;
    }

    public Graph union(Graph g) {
        // throw new UnsupportedOperationException();
        Graph toRet = new ListGraph();
        for (String node : this.nodes()) {
            toRet.addNode(node);
            for (String edge : this.succ(node)) {
                if (!toRet.hasNode(edge)){
                    toRet.addNode(edge);
                }
                toRet.addEdge(node, edge);
            }
        }
        for(String s : g.nodes()) {
            if (!toRet.hasNode(s)) {
                toRet.addNode(s);
            }
            for (String edge : g.succ(s)) {
                if (!toRet.hasNode(edge)){
                    toRet.addNode(edge);
                }
                toRet.addEdge(s, edge);
            }
        }
        return toRet;
    }

    public Graph subGraph(Set<String> nodes) {
        Graph toRet = new ListGraph();
        for(String s : nodes) {
            if (this.hasNode(s)) { toRet.addNode(s); }
        }
        for(String s : toRet.nodes()) {
            for (String edge : this.succ (s)) {
                if (toRet.hasNode(edge)) { toRet.addEdge(s, edge); }   
            }
        }
        return toRet;
    }

    public boolean connected(String n1, String n2) {
        if (!(this.hasNode(n1) && this.hasNode(n1))) {
            throw new NoSuchElementException();
        }
        Queue<String> q = new LinkedList<>();
        HashSet<String> h = new HashSet<String>();

        q.add(n1);
        h.add(n1);
        while(!q.isEmpty()) {
            for (String edge : this.succ(q.poll())) {
                if (edge == n2) { return true; }
                if (!h.contains(edge)) {
                    q.add(edge);
                    h.add(edge);
                }
            }
        }
        return false;
    }
}