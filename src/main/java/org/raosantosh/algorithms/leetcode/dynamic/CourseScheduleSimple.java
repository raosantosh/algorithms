package com.yahoo.sample.dynamic;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CourseScheduleSimple {

	public static void main(String args[]) {
		int[][] dependencies = new int[4][2];
		dependencies[0][0] = 1;
		dependencies[0][1] = 0;
		dependencies[1][0] = 2;
		dependencies[1][1] = 0;
		dependencies[2][0] = 3;
		dependencies[2][1] = 1;
		dependencies[3][0] = 3;
		dependencies[3][1] = 1;

		CourseScheduleSimple courseScheduleSimple = new CourseScheduleSimple();
		System.out.println(courseScheduleSimple.canFinish(5, dependencies));
	}

	public boolean canFinish(int numCourses, int[][] prerequisites) {
		Map<Integer, Set<Integer>> adjacencyList = new HashMap<>();

		for (int i = 0; i < prerequisites.length; ++i) {

			int source = prerequisites[i][0];
			int destination = prerequisites[i][1];

			Set<Integer> set = null;
			if (adjacencyList.containsKey(source)) {
				set = adjacencyList.get(source);
			} else {
				set = new HashSet<>();
				adjacencyList.put(source, set);
			}
			set.add(destination);
		}

		Set<Integer> visited = new HashSet<>();
		for (Integer currentNode : adjacencyList.keySet()) {
			if (!visited.contains(currentNode)) {
				if (!dfs(adjacencyList, currentNode, new HashSet<>(), visited))
					return false;
			}
		}

		return true;
	}

	public boolean dfs(Map<Integer, Set<Integer>> adjacencyList, int num, Set<Integer> path, Set<Integer> visited) {
		if (!adjacencyList.containsKey(num)) {
			visited.add(num);
			return true;
		}

		for (Integer sides : adjacencyList.get(num)) {
			if (visited.contains(sides))
				continue;
			if (path.contains(sides)) {
				return false;
			}
			path.add(sides);
			if (!dfs(adjacencyList, sides, path, visited))
				return false;

		}
		visited.add(num);

		return true;
	}
}
