package com.yahoo.sample;

import java.util.HashMap;
import java.util.Map;

public class FreedomTrail {

	public static void main(String args[]) {
		// String ring = "gd";
		// String key = "godding";

		FreedomTrail freedomTrail = new FreedomTrail();
		 System.out.println(freedomTrail.findRotateSteps("godding", "gd"));
//		System.out.println(freedomTrail.findRotateSteps(
//				"jogduakfgovnolkbjwelatfgfunqgvajvwtrzguyydiqaucqrzzcuhxcpkilfebqyytaxikigemzatzgmcdbodriddnrvgffsriv",
//				"sqlfcudiojjzmdmvbqgtkudggbazwtqgzrbxlooxcfnvzkvyvrroakdhnwcfyzyefiuatefegiragiqdrggictalanfupkuvjyid"));
		System.out.println(freedomTrail.findRotateSteps("abcde", "ade"));
		System.out.println(freedomTrail.findRotateSteps("aaaaa", "aaaaa"));
		
		System.out.println(freedomTrail.findRotateSteps("cwqie", "qqwiciqwceecewi"));
	}

	public int findRotateSteps(String ring, String key) {
		Map<Integer, Integer> memoMap = new HashMap<>();
		Map<Integer, Map<Integer, Integer>> pathMap = new HashMap<>();
		best(ring, key, 0, 0, memoMap, 0, pathMap);
		return memoMap.get(1);
	}

	public void best(String ring, String key, int keyPosition, int ringPosition, Map<Integer, Integer> memoMap,
			int steps, Map<Integer, Map<Integer, Integer>> pathMap) {

//		 System.out.println("Next Ring" + ringPosition + " Key:" + keyPosition
//		 + "steps :" + steps);
		
		if (pathMap.containsKey(ringPosition) && pathMap.get(ringPosition).containsKey(keyPosition) && steps > pathMap.get(ringPosition).get(keyPosition)) {
//			 System.out.println("Putting " + steps + " for : " + keyPosition + " and "+ ringPosition);
			 return;
		}

		while (keyPosition < key.length() && ring.charAt(ringPosition) == key.charAt(keyPosition)) {
			// System.out.println(pathMap + " step : " + steps);
			if (pathMap.containsKey(ringPosition) && pathMap.get(ringPosition).containsKey(keyPosition)
					&& steps >= pathMap.get(ringPosition).get(keyPosition))
				return;

			if (!pathMap.containsKey(ringPosition)) {
				pathMap.put(ringPosition, new HashMap<>());
			}

			if (!pathMap.get(ringPosition).containsKey(keyPosition) || steps < pathMap.get(ringPosition).get(keyPosition)) {
				pathMap.get(ringPosition).put(keyPosition, steps);
//				 System.out.println("Putting " + steps + " for : " + keyPosition + " and "+ ringPosition);
			}

			steps++;
			keyPosition++;
//			System.out.println(
//					"Found: " + ring.charAt(ringPosition) + " step : " + pathMap.get(ringPosition).get(keyPosition) + " step " + steps);
		}

		if (keyPosition >= key.length()) {
//			 System.out.println("DONE " + steps);
			if (!memoMap.containsKey(1) || memoMap.get(1) > steps)
				memoMap.put(1, steps);
			return;
		}

		// Right
		// System.out.println("Right: " + steps);
		if (!memoMap.containsKey(1) || memoMap.get(1) > steps) {
			int nextRightPosition = (ringPosition + 1 > ring.length() - 1) ? 0 : ringPosition + 1;
			best(ring, key, keyPosition, nextRightPosition, memoMap, steps + 1, pathMap);
		}

		// Left
		// System.out.println("Left: " + steps + " map size" + memoMap.get(1));
		if (!memoMap.containsKey(1) || memoMap.get(1) > steps) {

			int nextLeftPosition = (ringPosition == 0) ? ring.length() - 1 : ringPosition - 1;
			best(ring, key, keyPosition, nextLeftPosition, memoMap, steps + 1, pathMap);
		}
	}
}
