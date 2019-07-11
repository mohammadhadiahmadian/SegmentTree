import java.util.*;

public class Project {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        int arrayLength, instructionsCount;

        //Getting array length
        arrayLength = input.nextInt();

        //Getting instructions count
        instructionsCount = input.nextInt();

        SegmentTree segmentTree = new SegmentTree(arrayLength);
        segmentTree.constructTree(segmentTree.getRoot(), segmentTree.getRoot().getStart(), segmentTree.getRoot().getEnd());

        //Getting and executing instructions
        for (int i = 0 ; i < instructionsCount ; i++) {
            int instruction = input.nextInt();

            //Executing instruction of first kind (adding x to array[index])
            if (instruction == 1) {
                int index = input.nextInt();
                int x = input.nextInt();
                new Project().addToTree(segmentTree, index, x);
            }

            //Executing instruction of second kind (printing sum array[index] (start <= index <= end))
            else if (instruction == 2) {
                int start = input.nextInt();
                int end = input.nextInt();
                int sum = 0;
                SegmentTree.Node p = new Project().findStartNode(segmentTree.getRoot(), start);

                if (p.getEnd() == end) {
                    sum += p.getData();
                }

                else if (p.getEnd() > end) {
                    sum += new Project().findEndNode(p, end);
                }

                else {
                    sum += p.getData();
                    SegmentTree.Node q = new Project().findStartNode(segmentTree.getRoot(), p.getEnd() + 1);

                    while (true) {
                        if (q.getEnd() == end) {
                            sum += q.getData();
                            break;
                        }

                        else if (q.getEnd() > end) {
                            sum += new Project().findEndNode(q, end);
                            break;
                        }

                        else {
                            sum += q.getData();
                            q = new Project().findStartNode(segmentTree.getRoot(), q.getEnd() + 1);
                        }
                    }
                }

                System.out.println(sum);
            }

            //Executing instruction of first kind (adding x to array[index] (start <= index <= end))
            else {
                int start = input.nextInt();
                int end = input.nextInt();
                int x = input.nextInt();
                for (int j = start ; j <= end ; j++) {
                    new Project().addToTree(segmentTree, j, x);
                }
            }

        }

    }

    public void addToTree(SegmentTree segmentTree, int index, int x) {
        SegmentTree.Node p = segmentTree.getRoot();
        while (p.getStart() != p.getEnd()) {
            p.addData(x);
            if (index < p.getRight().getStart()) {
                p = p.getLeft();
            }
            else {
                p = p.getRight();
            }
        }
        p.addData(x);
    }

    public SegmentTree.Node findStartNode(SegmentTree.Node node, int start) {
        SegmentTree.Node result = node;
        while (result.getStart() != start) {
            if (start < result.getRight().getStart()) {
                result = result.getLeft();
            }
            else {
                result = result.getRight();
            }
        }
        return result;
    }

    public int findEndNode(SegmentTree.Node node, int end) {
        SegmentTree.Node q = node;
        int sum = 0;
        while (q.getEnd() != end) {
            if (end > q.getLeft().getEnd()) {
                sum += q.getLeft().getData();
                q = q.getRight();
            }
            else {
                q = q.getLeft();
            }
        }
        sum += q.getData();
        return sum;
    }

}
