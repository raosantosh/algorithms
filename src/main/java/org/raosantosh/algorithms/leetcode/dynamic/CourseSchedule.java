package com.yahoo.sample.dynamic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CourseSchedule {

	private static final int DEPENDENT_INDEX = 0;
	private static final int DEPENDENCY_INDEX = 1;

	public static void main(String args[]) {
		int courses = 4;
		int[][] dependencies = new int[4][2];
		dependencies[0][0] = 1;
		dependencies[0][1] = 0;
		dependencies[1][0] = 2;
		dependencies[1][1] = 0;
		dependencies[2][0] = 3;
		dependencies[2][1] = 1;
		dependencies[3][0] = 3;
		dependencies[3][1] = 2;
		int[] order = findOrder(4, dependencies);
//		for (int i = 0; i < order.length; ++i) {
//			System.out.println("Order: " + order[i]);
//		}

		int[][] anotherONe = new int[2][2];
		anotherONe[0][0] = 0;
		anotherONe[0][1] = 1;
		anotherONe[1][0] = 1;
		anotherONe[1][1] = 0;
		
		int [][] anotherTwo = new int[3][2];
		anotherTwo[0][0] = 0;
		anotherTwo[0][1] = 1;
		anotherTwo[1][0] = 0;
		anotherTwo[1][1] = 2;
		anotherTwo[2][0] = 1;
		anotherTwo[2][1] = 2;
//		[[0,1],[0,2],[1,2]]
		
		int [][] anotherThree = new int[1][2];
		anotherThree[0][0] = 1;
		anotherThree[0][1] = 0;
		

//		order = findOrder(2, anotherONe);
//		for (int i = 0; i < order.length; ++i) {
//			System.out.println("Order: " + order[i]);
//		}
//
//		order = findOrder2(4, dependencies);
//		for (int i = 0; i < order.length; ++i) {
//			System.out.println("Order: " + order[i]);
//		}
		
		System.out.println("***** 1 ***** ");
		order = findOrder2(2, anotherONe);
		for (int i = 0; i < order.length; ++i) {
			System.out.println("Order: " + order[i]);
		}
		
		System.out.println("*****  2 ***** " );
		order = findOrder2(3, anotherTwo);
		for (int i = 0; i < order.length; ++i) {
			System.out.println("Order: " + order[i]);
		}
		
		System.out.println("*****  3 ***** ");
		order = findOrder2(2, anotherThree);
		for (int i = 0; i < order.length; ++i) {
			System.out.println("Order: " + order[i]);
		}
	}

	private static int[] findOrder2(int numCourses, int[][] prerequisites) {
		Map<Integer, Set<Integer>> adjacencyList = new HashMap<>();

		for (int i = 0; i < numCourses; ++i)
			adjacencyList.put(i, new HashSet<>());

		for (int i = 0; i < prerequisites.length; ++i) {
			adjacencyList.get(prerequisites[i][DEPENDENT_INDEX]).add(prerequisites[i][DEPENDENCY_INDEX]);
		}

		List<Integer> completedTimes = new ArrayList<>();
		boolean result = dfs(adjacencyList, completedTimes);
		if(!result) return new int[0];
		
		int[] order = new int[numCourses];
		
		for(int i=0 ; i< numCourses ; ++i) {
			order[i] = completedTimes.get(i);
		}
		
		return order;
		
	}

	private static boolean dfs(Map<Integer, Set<Integer>> adjacencyList, List<Integer> completedTimes) {

		Set<Integer> allVisited = new HashSet<>();
		int startIndex = 0;

		System.out.println("Adjacency " + adjacencyList);
		
		while (true) {
			Set<Integer> visited = new HashSet<>();
			if (!doDFS(startIndex, adjacencyList, visited, completedTimes))
				return false;
			allVisited.addAll(visited);
			if (allVisited.size() != adjacencyList.size()) {
				for (int i = 0; i < adjacencyList.size(); ++i) {
					if (!allVisited.contains(i))
						startIndex = i;
				}
			} else {
				break;
			}
		}

		return true;
	}

	private static boolean doDFS(int startVertex, Map<Integer, Set<Integer>> adjacencyList, Set<Integer> visited,
			List<Integer> completeTimes) {
		
		System.out.println("Visited "+startVertex);
		visited.add(startVertex);
		for (Integer vertex : adjacencyList.get(startVertex)) {
			System.out.println("Checking " + vertex);
			if(!completeTimes.contains(vertex) && visited.contains(vertex))
				return false;
			
			if (!visited.contains(vertex)) {
				if (!doDFS(vertex, adjacencyList, visited, completeTimes))
					return false;
			}
		}
		System.out.println("Completed " + startVertex);
		if(!completeTimes.contains(startVertex))
		completeTimes.add(startVertex);
		return true;
	}

	private static int[] findOrder(int numCourses, int[][] prerequisites) {
		Map<Integer, Set<Integer>> adjacencyList = new HashMap<>();

		for (int i = 0; i < numCourses; ++i)
			adjacencyList.put(i, new HashSet<>());

		for (int i = 0; i < prerequisites.length; ++i) {
			adjacencyList.get(prerequisites[i][DEPENDENT_INDEX]).add(prerequisites[i][DEPENDENCY_INDEX]);
		}

		int[] order = new int[numCourses];
		int orderIndex = 0;

		for (int i = 0; i < numCourses && adjacencyList.size() != 0; ++i) {
			int toUseIndex = -1;
			for (Map.Entry<Integer, Set<Integer>> entryValue : adjacencyList.entrySet()) {
				if (entryValue.getValue().size() == 0) {
					toUseIndex = entryValue.getKey();
					break;
				}
			}

			if (toUseIndex != -1) {
				order[orderIndex++] = toUseIndex;
				for (Map.Entry<Integer, Set<Integer>> entryValue : adjacencyList.entrySet()) {
					entryValue.getValue().remove(toUseIndex);
				}
				adjacencyList.remove(toUseIndex);
			} else {
				break;
			}
		}

		if (orderIndex != numCourses)
			return new int[0];

		return order;
	}
}
