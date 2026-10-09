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

    // remove node's value from any LinkedList it might be in
    public static void removeValue(ListNode<String> node) {
        node.setValue(node.getNext().getValue());
        node.setNext(node.getNext().getNext());
    }

    // prints the value of every node in the singly linked list with the given head,
    // but in reverse (for a singlylinkedlist), print one on each line.
    public static void printListInReverse(ListNode<String> head) {
        if (head == null) {
            return;
        }
        printListInReverse(head.getNext());
        System.out.println(head.getValue());
    }
}
