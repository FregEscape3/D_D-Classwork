import java.awt.Graphics;

/**
 * Implements the list of messages for teletext
 */
public class TeletextList {
  private ListNode2 heading, topNode;
  // heading = sentinel
  // topNode = current displayed node

  /**
   * Creates a circular list of headlines.
   * First creates a circular list with one node, "Today's headlines:".
   * Saves a reference to that node in heading.
   * Adds a node holding an empty string before heading
   * and another node holding an empty string after heading.
   * Appends all the strings from headlines to the list, after
   * the blank line that follows heading,
   * preserving their order. Sets topNode equal to heading.
   */
  public TeletextList(String[] headlines) {
    heading = new ListNode2("Today's headlines:");
    heading.setNext(heading);
    heading.setPrevious(heading);
    addBefore(heading, "");
    ListNode2 last = addAfter(heading, "");
    for (int i = 0; i < headlines.length; i++) {
      last = addAfter(last, headlines[i]);
    }
    topNode = heading;
  }

  /**
   * Inserts a node with msg into the headlines list after the blank
   * line that follows heading.
   */
  public void insert(String msg) {
    ListNode2 blankSpace = heading.getNext();
    ListNode2 next = heading.getNext().getNext();
    ListNode2 insertion = new ListNode2(msg, heading.getNext(), next);
    blankSpace.setNext(insertion);
    next.setPrevious(insertion);
  }

  /**
   * Deletes the node that follows topNode from the headlines list,
   * unless that node happens to be heading or the node before or after
   * heading that holds a blank line.
   */
  public void delete() {
    ListNode2 target = topNode.getNext();
    if (!(target == heading || target == heading.getPrevious() || target == heading.getNext())) {
      remove(target);
    }
  }

  /**
   * Scrolls up the headlines list, advancing topNode to the next node.
   */
  public void scrollUp() {
    topNode = topNode.getNext();
  }

  /**
   * Adds a new node with msg to the headlines list before a given node.
   * Returns a reference to the added node.
   */
  private ListNode2 addBefore(ListNode2 node, String msg) {
    ListNode2 previous = node.getPrevious();
    ListNode2 addition = new ListNode2(msg, previous, node);
    previous.setNext(addition);
    node.setPrevious(addition);
    return addition;
  }

  /**
   * Adds a new node with msg to the headlines list after a given node.
   * Returns a reference to the added node.
   */
  private ListNode2 addAfter(ListNode2 node, String msg) {
    return addBefore(node.getNext(), msg);
  }

  /**
   * Removes a given node from the list.
   */
  private void remove(ListNode2 node) {
    node.getPrevious().setNext(node.getNext());
    node.getNext().setPrevious(node.getPrevious());
  }

  /**
   * Draws nLines headlines in g, starting with topNode at x, y
   * and incrementing y by lineHeight after each headline.
   */
  public void draw(Graphics g, int x, int y, int lineHeight, int nLines) {
    ListNode2 node = topNode;
    for (int k = 1; k <= nLines; k++) {
      g.drawString((String) node.getValue(), x, y);
      y += lineHeight;
      node = node.getNext();
    }
  }
}
