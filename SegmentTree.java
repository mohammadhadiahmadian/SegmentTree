public class SegmentTree {

    private Node root;

    public SegmentTree(int arrayLength) {
        this.root = new Node(0, arrayLength - 1);
    }

    public class Node {

        private int start, end, data;
        private Node right, left;

        Node(int start, int end) {
            this.start = start;
            this.end = end;
            this.data = 0;
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

        public void addData(int x) {
            this.data += x;
        }

    }

    public Node getRoot() {
        return this.root;
    }

    public void constructTree(Node root, int start, int end) {
        
        if (start != end) {
            root.setLeft(new Node(start, (start + end) / 2));
            root.setRight(new Node(root.getLeft().getEnd() + 1, end));
            constructTree(root.getRight(), root.getRight().getStart(), root.getRight().getEnd());
            constructTree(root.getLeft(), root.getLeft().getStart(), root.getLeft().getEnd());
        }

    }

}
