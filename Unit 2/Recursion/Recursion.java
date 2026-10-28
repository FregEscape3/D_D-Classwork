import java.util.ArrayList;

public class Recursion {

	// Prints the value of every node in the singly linked list with the given head,
	// but in reverse
	public static void printListInReverse(ListNode head) {
		if (head == null) {
			return;
		}
		printListInReverse(head.getNext());
		System.out.println(head.getValue());
	}

	// For the given 2D array of Strings, replaces the String at index[r][c]
	// with "infected" unless the String is "vaccinated";
	// also, any Strings they are orthogonally adjacent to
	// that are not "vaccinated" will also be infected, and any adjacent to
	// them as well etc.
	// Infecting someone who is already infected has no effect
	// Trying to infect outside the confines of the grid also has no effect
	// Precondition: grid has no null entries
	public static void infect(String[][] grid, int r, int c) {
		if (r < 0 || r >= grid.length || c < 0 || c >= grid[r].length) {
			return;
		}
		if (grid[r][c].equals("vaccinated") || grid[r][c].equals("infected")) {
			return;
		}
		grid[r][c] = "infected";
		infect(grid, r + 1, c);
		infect(grid, r - 1, c);
		infect(grid, r, c + 1);
		infect(grid, r, c - 1);
	}

	// How many subsets are there of the numbers 1...n
	// that don't contain any consecutive integers?
	// e.g. for n = 4, the subsets are {}, {1}, {2}, {3}, {4},
	// {1, 3}, {1, 4}, {2, 4}
	// The other subsets of 1,2,3,4 that DO contain consecutive integers are
	// {1,2}, {2,3}, {3,4}, {1,2,3}, {1,2,4}, {1,3,4}, {1,2,3,4}
	// Precondition: n > 0
	public static long countNonConsecutiveSubsets(int n) {
		if (n == 0) {
			return 1;
		} else if (n == 1) {
			return 2;
		}
		return countNonConsecutiveSubsets(n - 1) + countNonConsecutiveSubsets(n - 2);
	}

	// A kid at the bottom of the stairs can jump up 1, 2, or 3 stairs at a time.
	// How many different ways can they jump up n stairs?
	// Jumping 1-1-2 is considered different than jumping 1-2-1
	// Precondition: n > 0
	public static long countWaysToJumpUpStairs(int n) {
		if (n == 0) {
			return 1;
		} else if (n == 1) {
			return 1;
		} else if (n == 2) {
			return 2;
		}
		return countWaysToJumpUpStairs(n - 1) + countWaysToJumpUpStairs(n - 2) + countWaysToJumpUpStairs(n - 3);
	}

	// Everything above this line does NOT require a recursive helper method
	// ----------------------------------
	// Everything below this line requires a recursive helper method
	// Any recursive helper method you write MUST have a comment describing:
	// 1) what the helper method does/returns
	// 2) your description must include role of each parameter in the helper method

	// Prints all the subsets of str on separate lines
	// You may assume that str has no repeated characters
	// For example, subsets("abc") would print out "", "a", "b", "c", "ab", "ac",
	// "bc", "abc"
	// Order is your choice
	public static void printSubsets(String str) {
		printSubsetofChar(str, "");
	}

	// We start with the empty set and then choose to include or exclude the first
	// char, and then do the same for the next char, and then repeat until you don't
	// have any more
	public static void printSubsetofChar(String str, String subset) {
		if (str.length() == 0) {
			System.out.println(subset);
			return;
		}
		printSubsetofChar(str.substring(1), subset);
		printSubsetofChar(str.substring(1), subset + str.charAt(0));
	}

	// List contains a single String to start.
	// Prints all the permutations of str on separate lines
	// You may assume that str has no repeated characters
	// For example, permute("abc") could print out "abc", "acb", "bac", "bca",
	// "cab", "cba"
	// Order is your choice
	public static void printPermutations(String str) {
		findPermutations(str, "");
	}

	// Basically it selects a char and then chooses another char without replacement
	// until there are no chars left
	public static void findPermutations(String str, String permutation) {
		if (str.length() == 0) {
			System.out.println(permutation);
			return;
		}
		for (int i = 0; i < str.length(); i++) {
			String remaining = str.substring(0, i) + str.substring(i + 1);
			findPermutations(remaining, permutation + str.charAt(i));
		}
	}

	// Performs a mergeSort on the given array of ints
	// Precondition: you may assume there are NO duplicates!!!
	public static void mergeSort(int[] ints) {
		sort(ints, 0, ints.length - 1);
	}

	// do a binary split on the array with startIndex and an endIndex until you have
	// one value left in each subarray
	// and then you merge all of the subarrays together in a sorted order
	public static void sort(int[] ints, int startIndex, int endIndex) {
		if (startIndex >= endIndex) {
			return;
		}
		int mid = startIndex + (endIndex - startIndex) / 2;
		sort(ints, startIndex, mid);
		sort(ints, mid + 1, endIndex);
		merge(ints, startIndex, mid, endIndex);
	}

	// for each array if one value if less than the other that value is added before
	// the other value (and vice versa). we start at the startIndex and then end at
	// the endIndex, where the middleIndex is where the two sets are split
	public static void merge(int[] ints, int startIndex, int middleIndex, int endIndex) {
		int[] temp = new int[endIndex - startIndex + 1];
		int i = startIndex;
		int j = middleIndex + 1;
		int k = 0;
		while (i <= middleIndex && j <= endIndex) {
			if (ints[i] <= ints[j]) {
				temp[k++] = ints[i++];
			} else {
				temp[k++] = ints[j++];
			}
		}
		while (i <= middleIndex) {
			temp[k++] = ints[i++];
		}
		while (j <= endIndex) {
			temp[k++] = ints[j++];
		}
		for (int l = 0; l < temp.length; l++) {
			ints[startIndex + l] = temp[l];
		}
	}

	// Performs a quickSort on the given array of ints
	// Use the middle element (index n/2) as the pivot
	// Precondition: you may assume there are NO duplicates!!!
	public static void quickSort(int[] ints) {
		quicklySort(ints, 0, ints.length - 1);
	}

	// defines the pivot, and does the sorting/swapping with the two indices, then
	// calls it on the left and right ends
	public static void quicklySort(int[] ints, int startIndex, int endIndex) {
		if (startIndex >= endIndex)
			return;
		int pivot = ints[startIndex + (endIndex - startIndex + 1) / 2];
		int left = startIndex;
		int right = endIndex;
		while (left <= right) {
			while (ints[left] < pivot)
				left++;
			while (ints[right] > pivot)
				right--;
			if (left <= right) {
				int temp = ints[left];
				ints[left] = ints[right];
				ints[right] = temp;
				left++;
				right--;
			}
		}
		quicklySort(ints, startIndex, right);
		quicklySort(ints, left, endIndex);
	}

	// three indices: one at pivot, one at start, one at end
	// if one is in right place then no switch, if two not in the right then swap
	// both. if one is in the right place indices move to the next one

	// Prints a sequence of moves (one on each line)
	// to complete a Towers of Hanoi problem:
	// disks start on tower 0 and must end on tower 2.
	// The towers are number 0, 1, 2, and each move should be of
	// the form "1 -> 2", meaning "take the top disk of tower 1 and
	// put it on tower 2" etc.
	public static void solveHanoi(int startingDisks) {
		hanoi(startingDisks, 0, 1, 2);
	}

	// Prints the moves to transfer numDisks disks from source to target,
	// using extra as the spare tower
	// Otherwise, move the smaller disks to extra, move the largest disk to target,
	// then move the smaller disks from extra to target. The recursive calls swap
	// the tower roles so each smaller stack has the right spare and destination
	public static void hanoi(int numDisks, int source, int extra, int target) {
		if (numDisks <= 0) {
			return;
		}
		hanoi(numDisks - 1, source, target, extra);
		System.out.println(source + " -> " + target);
		hanoi(numDisks - 1, extra, source, target);
	}

	// You are partaking in a scavenger hunt!
	// You've gotten a secret map to find many of the more difficult
	// items, but they are only available at VERY specific times at
	// specific places. You have an array, times[], that lists at which
	// MINUTE an item is available. Times is sorted in ascending order.
	// Items in the ScavHunt are worth varying numbers of points.
	// You also have an array, points[], same length as times[],
	// that lists how many points each of the corresponding items is worth.
	// Problem is: to get from one location to the other takes 5 minutes,
	// so if there is an item, for example, available at time 23 and another
	// at time 27, it's just not possible for you to make it to both: you'll
	// have to choose!
	// (but you COULD make it from a place at time 23 to another at time 28)
	// Write a method that returns the maximum POINTS you can get.
	// For example, if times = [3, 7, 9]
	// and points = [10, 15, 10]
	// Then the best possible result is getting the item at time 3 and the one at
	// time 9
	// for a total of 20 points, so it would return 20.
	public static int scavHunt(int[] times, int[] points) {
		ArrayList<int[]> nodes = new ArrayList<>();
		nodes.add(new int[] { 0, 0, 0 });
		for (int i = 0; i < times.length; i++) {
			nodes.add(new int[] { times[i], points[i], i + 1 });
		}
		nodes.add(new int[] { 0, 0, times.length + 1 });
		int[] scores = new int[nodes.size()];
		for (int i = 0; i < scores.length; i++) {
			scores[i] = Integer.MIN_VALUE;
		}
		scores[0] = 0;
		return findPath(connectNodes(nodes), scores, 0, 0, nodes.size() - 1);
	}

	// Returns {from, to, points earned} edges for nodes holding {time, points, id}
	// (basically creating an ArrayList of edges)
	public static ArrayList<int[]> connectNodes(ArrayList<int[]> nodes) {
		ArrayList<int[]> connections = new ArrayList<>();
		int end = nodes.size() - 1;
		for (int i = 1; i < end; i++) {
			connections.add(new int[] { 0, nodes.get(i)[2], nodes.get(i)[1] });
		}
		connections.add(new int[] { 0, nodes.get(end)[2], 0 });
		for (int i = 1; i < end; i++) {
			for (int j = i + 1; j < end; j++) {
				if (nodes.get(j)[0] - nodes.get(i)[0] >= 5) {
					connections.add(new int[] { nodes.get(i)[2], nodes.get(j)[2], nodes.get(j)[1] });
				}
			}
			connections.add(new int[] { nodes.get(i)[2], nodes.get(end)[2], 0 });
		}
		return connections;
	}

	// Returns the best score at exitNode by recursively processing currentNode in
	// time order, connections are grouped by source; scores tracks best totals, and
	// edgeIndex marks the next edge
	// basically running a max djikstra's on the scavHunt on each end and keep going
	// to the last node
	public static int findPath(ArrayList<int[]> connections, int[] scores, int currentNode, int edgeIndex,
			int exitNode) {
		if (currentNode == exitNode) {
			return scores[exitNode];
		}
		while (edgeIndex < connections.size() && connections.get(edgeIndex)[0] == currentNode) {
			int[] edge = connections.get(edgeIndex);
			scores[edge[1]] = Math.max(scores[edge[1]], scores[currentNode] + edge[2]);
			edgeIndex++;
		}
		return findPath(connections, scores, currentNode + 1, edgeIndex, exitNode);
	}

}
