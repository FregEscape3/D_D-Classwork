public class RecursionTester {
    public static void main(String[] args) {
        // print list in reverse
        ListNode node5 = new ListNode("bum5");
        ListNode node4 = new ListNode("bum4", node5);
        ListNode node3 = new ListNode("bum3", node4);
        ListNode node2 = new ListNode("bum2", node3);
        ListNode node1 = new ListNode("bum1", node2);

        Recursion.printListInReverse(node1);
        System.out.println("-----");
        Recursion.printListInReverse(node3);
        System.out.println("-----");
        Recursion.printListInReverse(node5);
        System.out.println("-----");
        // infect
        int[][] grid = new int[2][3];
        // count non-consecutive subsets
        long output1 = Recursion.countNonConsecutiveSubsets(6);
        long output2 = Recursion.countNonConsecutiveSubsets(20);
        System.out.println(output1);
        System.out.println("-----");
        System.out.println(output2);
        System.out.println("-----");
        // count ways to jump stairs
        long output3 = Recursion.countWaysToJumpUpStairs(6);
        long output4 = Recursion.countWaysToJumpUpStairs(20);
        System.out.println(output3);
        System.out.println("-----");
        System.out.println(output4);
        System.out.println("-----");
        // print permutations
        Recursion.printPermutations("son");
        System.out.println("-----");
        Recursion.printPermutations("a b c");
        System.out.println("-----");
        // merge sort
        int[] arrayToSort = new int[] { 42, -17, 89, -63, 5, -94, 28, 71, -36, 0, -82, 56, -11, 97, -45, 33, -70, 14,
                62, -8, 100, -29, 47, -56, 19, -91, 74, -3, 38, -65, 12, -48, 83, -21, 6, -77, 54, -14, 91, -39, 25,
                -100, 68, -32, 44, -59, 2, 79, -6, 31 };
        Recursion.mergeSort(arrayToSort);
        for (int i = 0; i < arrayToSort.length - 1; i++) {
            System.out.print(arrayToSort[i] + ", ");
        }
        System.out.print(arrayToSort[arrayToSort.length - 1]);
        System.out.println();
        System.out.println("-----");
        // quick sort
        int[] arrayToSort2 = new int[] { 37, -82, 14, 96, -5, 61, -43, 78, -100, 22, -67, 49, -18, 85, -31, 7, 54, -91,
                30, -56, 100, -12, 68, -74, 41, -2, 89, -39, 16, -63, 73, -25, 58, -87, 4, -48, 92, -14, 33, -70, 26,
                -95, 64, -35, 11, -59, 81, -8, 45, -21 };
        Recursion.quickSort(arrayToSort2);
        for (int i = 0; i < arrayToSort2.length - 1; i++) {
            System.out.print(arrayToSort2[i] + ", ");
        }
        System.out.print(arrayToSort2[arrayToSort2.length - 1]);
        System.out.println();
        System.out.println("-----");
        // tower of hanoi
        Recursion.solveHanoi(5);
        System.out.println("-----");
        // scav hunt
        int maxPoints1 = Recursion.scavHunt(new int[] { 2, 7, 11, 14, 19 }, new int[] { 3, 8, 12, 16, 20 });
        int maxPoints2 = Recursion.scavHunt(new int[] { 3, 8, 12, 16, 20 }, new int[] { 2, 7, 11, 14, 19 });
        System.out.println(maxPoints1);
        System.out.println("-----");
        System.out.println(maxPoints2);
    }
}
