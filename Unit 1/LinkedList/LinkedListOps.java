public class LinkedListOps {
    // returns whether or not the singly linked list which begins at start has a
    // loop in it (i.e. one of the nodes points to an earlier node)
    public static boolean hasLoop(ListNode<String> start) {
        ListNode<String> oneSkip = start;
        ListNode<String> twoSkip = start;
        while (oneSkip != null && twoSkip.getNext() != null) {
            oneSkip = oneSkip.getNext();
            twoSkip = twoSkip.getNext().getNext();
            if (oneSkip == twoSkip) {
                return true;
            }
        }
        return false;
    }
}
