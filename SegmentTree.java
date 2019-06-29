public class SegmentTree {

    private Node root;

    public SegmentTree(int start, int end, int data) {
        this.root = new Node(start, end, data);
    }

    public class Node {

        private int start, end, data;
        private Node right, left;

        Node(int start, int end, int data)
        {
            this.start = start;
            this.end = end;
            this.data = data;
            this.setRight(null);
            this.setLeft(null);
        }

        public int getStart() {
            return this.start;
        }

        public int getEnd() {
            return this.end;
        }

        public Node getRight() {
            return this.right;
        }

        public Node getLeft() {
            return this.left;
        }

        public void setRight(Node right) {
            this.right = right;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public int getData() {
            return this.data;
        }

        public void setData(int x) {
            this.data += x;
        }

    }

    public Node getRoot() {
        return this.root;
    }

    public void constructTree(Node root, int start, int end) {
        if (start != end) {
            root.setRight(new Node(start + ((end - start) / 2) + 1, end, 0));
            root.setLeft(new Node(start, start + ((end - start) / 2), 0));
            constructTree(root.getRight(), root.getRight().getStart(), root.getRight().getEnd());
            constructTree(root.getLeft(), root.getLeft().getStart(), root.getLeft().getEnd());
        }
    }

}