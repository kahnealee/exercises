package lc;

/**
 * Created by kpan on 9/17/16.
 */
public class L147InsertionSortList {
    public ListNode insertionSortList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode a = head.next;
        ListNode b = head;
        ListNode previousNode = head;
        while (a != null) {
            ListNode nextNode = a.next;
            if (a.val < previousNode.val) {
                if (a.val < head.val) {
                    a.next = head;
                    head = a;
                    b = a;
                } else {
                    while (b != null && b != previousNode) {
                        if (b.next.val > a.val) {
                            a.next = b.next;
                            b.next = a;
                            break;
                        }
                        b = b.next;
                    }
                    b = head;
                }
                previousNode.next = nextNode;
            } else {
                previousNode = a;
            }
            a = nextNode;
        }
        return head;
    }
}
