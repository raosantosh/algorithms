package com.yahoo.sample.lists;

public class ReorderList {

	public static void main(String args[]) {

		int[] elements = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		int[] elements1 = new int[] { 11, 12, 13, 14, 15, 16, 17, 18, 19, 20 };
		int[] ele = new int[] {1,2,3};

		ReorderList reoder = new ReorderList();
		ListNode head = reoder.createList(ele);
		reoder.reorderList(head);
		System.out.println("Output ");
		reoder.printList(head);

//		ListNode head = reoder.createList(elements);
//		System.out.println("Before ordering");
//		reoder.printList(head);
//
//		ListNode head1 = reoder.createList(elements1);
//		System.out.println("Before ordering 2");
//		reoder.printList(head1);
//
//		System.out.println("After Merge");
//		ListNode head2 = reoder.merge(head, head1);
//		reoder.printList(head2);
//		
//		System.out.println("Afer Split");
//		ListNode[] splits = reoder.splitlist(head2);
//		System.out.println("Split 1");
//		reoder.printList(splits[0]);
//		System.out.println("Split 2");
//		reoder.printList(splits[1]);
//
//		System.out.println("After Reversing");
//		ListNode newHead = reoder.reverse(head);
//		reoder.printList(newHead);
//
//		System.out.println("After ordering");
//		reoder.reorderList(newHead);
//		reoder.printList(newHead);
	}

	private void printList(ListNode shittHead) {
		while (shittHead != null) {
			System.out.println("Element :" + shittHead.val);
			shittHead = shittHead.next;
		}
	}

	private ListNode createList(int[] elements) {
		ListNode head = new ListNode(elements[0]);
		ListNode cursor = head;
		for (int i = 1; i < elements.length; ++i) {
			ListNode element = new ListNode(elements[i]);
			cursor.next = element;
			cursor = cursor.next;
		}
		return head;
	}
	
	public void reorderList(ListNode head) {

        if(head == null || head.next == null) return ;
        ListNode[] splits = splitList(head);
        
        System.out.println("Split 0 ");
        printList(splits[0]);
        System.out.println("Split 1");
        printList(splits[1]);
        
        ListNode headNode = splits[0];
        ListNode tailNode = reverse(splits[1]);
        merge(headNode, tailNode);
	}

	public void reorderList1(ListNode head) {

		ListNode cursor = head;

		while (cursor != null && cursor.next != null) {
			ListNode lastNode = removeAndGetLast(cursor);
			lastNode.next = cursor.next;
			cursor.next = lastNode;
			cursor = lastNode.next;
		}
	}

	public ListNode removeAndGetLast(ListNode head) {
		ListNode prevNode = head;

		while (head.next != null) {
			prevNode = head;
			head = head.next;
		}

		// This will be last for now
		prevNode.next = null;

		return head;
	}
	
	public ListNode[] splitList(ListNode head) {
		
		ListNode[] splitLists = new ListNode[2];
		
		int count = 0;
		ListNode cursor = head;
		while (cursor.next != null) {
			cursor = cursor.next;
			count ++;
		}
		
		int midCount = (count / 2) + 1;
		
		ListNode midNode = head;
		while (midCount-- > 1) {
			midNode = midNode.next ;
		}
		
		splitLists[0] = head;
		splitLists[1] = midNode.next;
		midNode.next = null;
		
		return splitLists;
		
	}

	public ListNode[] splitlist(ListNode head) {
		ListNode[] splitLists = new ListNode[2];

		ListNode curNode = head;
		ListNode nextNode = head.next;
		
		ListNode headFirst = curNode;
		ListNode headSecond = nextNode;

		while (curNode != null && nextNode != null) {
			curNode.next = nextNode.next;
			if (nextNode.next != null) {
				nextNode.next = nextNode.next.next;
				nextNode = nextNode.next;
			}else {
				nextNode = null;
			}
			curNode = curNode.next;
		}
		
		splitLists[0] = headFirst;
		splitLists[1] = headSecond;

		return splitLists;
	}

	public ListNode merge(ListNode oneHead, ListNode twoHead) {
		ListNode head = oneHead;
		while (oneHead != null && twoHead != null) {
			ListNode twoHeadNext = twoHead.next;
			twoHead.next = oneHead.next;
			oneHead.next = twoHead;
			oneHead = twoHead.next;
			twoHead = twoHeadNext;
		}

		return head;
	}

	public ListNode reverse(ListNode head) {
		ListNode curNode = head;
		ListNode nextNode = null;
		ListNode prevNode = null;
		while (curNode != null) {
			nextNode = curNode.next;
			curNode.next = prevNode;
			prevNode = curNode;
			curNode = nextNode;
		}

		return prevNode;
	}

	public void updateList(ListNode head, ListNode toRemove) {
		while (head.next != null) {
			if (head.next.val == toRemove.val) {
				removeNode(head);
			}
			head = head.next;
		}
	}

	public void removeNode(ListNode parent) {
		ListNode node = parent.next;
		parent.next = node.next;
		node.next = null;
	}

	public static class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}
	}
}
