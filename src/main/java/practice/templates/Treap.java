/*
 * Created by cravuri on 5/27/18
 */

public class Treap {

    int size;
    Node root;

    private void rotateLeft(Node n) {
        if (n.getRight() == null) {
            return;
        }
        Node oldRight = n.getRight();
        n.setRight(oldRight.getLeft());
        if (n.getParent() == null) {
            root = oldRight;
        } else if (n.getParent().getLeft() == n) {
            n.getParent().setLeft(oldRight);
        } else {
            n.getParent().setRight(oldRight);
        }
        oldRight.setLeft(n);
    }

    private void rotateRight(Node n) {
        if (n.getLeft() == null) {
            return;
        }
        Node oldLeft = n.getLeft();
        n.setLeft(oldLeft.getRight());
        if (n.getParent() == null) {
            root = oldLeft;
        } else if (n.getParent().getLeft() == n) {
            n.getParent().setLeft(oldLeft);
        } else {
            n.getParent().setRight(oldLeft);
        }
        oldLeft.setRight(n);
    }

    void rotateUp(Node cur) {
        if (cur.parent.left == cur) {
            rotateRight(cur);
        } else {
            rotateLeft(cur);
        }
    }

    private void addBSTNode(Node cur, Node n) {
        if (n.b > cur.b) {
            if (cur.right == null) {
                n.parent = cur;
                cur.right = n;
                return;
            }
            addBSTNode(cur.right, n);
        } else {
            if (cur.left == null) {
                n.parent = cur;
                cur.left = n;
                return;
            }
            addBSTNode(cur.left, n);
        }
    }

    void addNode(Node n) {
        if (root == null) {
            root = n;
            size++;
            return;
        }
        addBSTNode(root, n);
        while (n.parent != null && n.parent.h > n.h) {
            rotateUp(n);
        }
        size++;
    }

    class Node {
        int h; // heap value
        int b; // bst value
        Node left;
        Node right;
        Node parent;

        public Node(int h, int b) {
            this.h = h;
            this.b = b;
        }

        public int getH() {
            return h;
        }

        public void setH(int h) {
            this.h = h;
        }

        public int getB() {
            return b;
        }

        public void setB(int b) {
            this.b = b;
        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }

        public Node getParent() {
            return parent;
        }

        public void setParent(Node parent) {
            this.parent = parent;
        }
    }

}
