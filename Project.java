import java.util.*;

public class Project {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        int arrayLength, instructionsCount;

        //Getting array length
        arrayLength = Integer.parseInt(input.next());

        //Getting instructions count
        instructionsCount = Integer.parseInt(input.next());

        SegmentTree segmentTree = new SegmentTree(0, arrayLength - 1, 0);
        segmentTree.constructTree(segmentTree.getRoot(), segmentTree.getRoot().getStart(), segmentTree.getRoot().getEnd());

        //Getting and executing instructions
        for (int i = 0 ; i < instructionsCount ; i++) {

            //Detecting instruction
            String string = input.next();

            //Executing instruction of first kind (adding x to array[i])
            if (Integer.parseInt(string) == 1) {
                int index = Integer.parseInt(input.next());
                int x = Integer.parseInt(input.next());
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

            //Executing instruction of second kind (printing sum a[i] (s <= i <= t))
            else if (Integer.parseInt(string) == 2) {
                int sum = 0;
                int start = Integer.parseInt(input.next());
                int end = Integer.parseInt(input.next());
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

        }

    }

}