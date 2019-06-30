import java.util.*;

public class Project {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        int arrayLength, instructionsCount;

        //Getting array length
        arrayLength = input.nextInt();

        //Getting instructions count
        instructionsCount = input.nextInt();

        SegmentTree segmentTree = new SegmentTree(0, arrayLength - 1, 0);
        segmentTree.constructTree(segmentTree.getRoot(), segmentTree.getRoot().getStart(), segmentTree.getRoot().getEnd());

        //Getting and executing instructions
        for (int i = 0 ; i < instructionsCount ; i++) {

            int instruction = input.nextInt();

            //Executing instruction of first kind (adding x to array[i])
            if (instruction == 1) {
                int index = input.nextInt();
                int x = input.nextInt();
                new Project().addToTree(segmentTree, index, x);
            }

            //Executing instruction of second kind (printing sum a[i] (s <= i <= t))
            else if (instruction == 2) {
                int sum = 0;
                int start = input.nextInt();
                int end = input.nextInt();
                for (int j = start ; j <= end ; j++) {
                    SegmentTree.Node p = segmentTree.getRoot();
                    while (p.getStart() != p.getEnd()) {
                        if (j < p.getRight().getStart()) {
                            p = p.getLeft();
                        }
                        else {
                            p = p.getRight();
                        }
                    }
                    sum += p.getData();
                }
                System.out.format("%d\n", sum);
            }

            //Executing instruction of first kind (adding x to array[i] (s <= i <= t))
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
            p.setData(x);
            if (index < p.getRight().getStart()) {
                p = p.getLeft();
            }
            else {
                p = p.getRight();
            }
        }
        p.setData(x);
    }

}
