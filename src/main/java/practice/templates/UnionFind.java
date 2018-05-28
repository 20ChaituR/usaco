import java.util.Set;

public class UnionFind {

    Set<Element> union;

    void makeSet(Element x) {
        if (!union.contains(x)) {
            union.add(new Element(x.id, x, 0));
        }
    }

    Element find(Element x) {
        if (!x.parent.equals(x)) {
            x.parent.id = find(x.parent).id;
        }
        return x.parent;
    }

    void union(Element x, Element y) {
        Element xRoot = find(x);
        Element yRoot = find(y);
        if (xRoot == yRoot) {
            return;
        }
        if (xRoot.rank < yRoot.rank) {
            xRoot.parent = yRoot;
        } else if (xRoot.rank > yRoot.rank) {
            yRoot.parent = xRoot;
        } else {
            yRoot.parent = xRoot;
            xRoot.rank = xRoot.rank + 1;
        }
    }

    class Element {

        int id;
        Element parent;
        int rank;

        public Element(int id) {
            this.id = id;
        }

        public Element(int id, Element parent, int rank) {
            this.id = id;
            this.parent = parent;
            this.rank = rank;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Element element = (Element) o;

            return id == element.id;
        }

        @Override
        public int hashCode() {
            return id;
        }

    }

}
