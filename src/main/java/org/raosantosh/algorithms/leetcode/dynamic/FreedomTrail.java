package com.yahoo.sample.dynamic;

import java.util.HashMap;
import java.util.Map;

public class FreedomTrail {

	public static void main(String args[]) {
		// String ring = "gd";
		// String key = "godding";

		FreedomTrail freedomTrail = new FreedomTrail();
//		System.out.println(freedomTrail.findRotateSteps("godding", "gd"));
//		 System.out.println(freedomTrail.findRotateSteps(
//		 "jogduakfgovnolkbjwelatfgfunqgvajvwtrzguyydiqaucqrzzcuhxcpkilfebqyytaxikigemzatzgmcdbodriddnrvgffsriv",
//		 "sqlfcudiojjzmdmvbqgtkudggbazwtqgzrbxlooxcfnvzkvyvrroakdhnwcfyzyefiuatefegiragiqdrggictalanfupkuvjyid"));
//		System.out.println(freedomTrail.findRotateSteps("abcde", "ade"));
//		System.out.println(freedomTrail.findRotateSteps("aaaaa", "aaaaa"));
//
//		System.out.println(freedomTrail.findRotateSteps("cwqie", "qqwiciqwceecewi"));
		
		System.out.println(freedomTrail.findRotateSteps("godding", "godding"));
		
		
	}

	public int findRotateSteps(String ring, String key) {
		Map<Integer, Map<Integer, Integer>> pathMap = new HashMap<>();
		return best(ring, key, 0, 0, pathMap);
	}

	public int best(String ring, String key, int keyPosition, int ringPosition,
			Map<Integer, Map<Integer, Integer>> pathMap) {

		if (keyPosition >= key.length())
			return 0;
		
		if (pathMap.containsKey(ringPosition) && pathMap.get(ringPosition).containsKey(keyPosition)) {
			return pathMap.get(ringPosition).get(keyPosition);
		}
		
		int leftPosition = ringPosition;
		int leftMoves = 0;

		while (key.charAt(keyPosition) != ring.charAt(leftPosition)) {
			leftPosition = (leftPosition == 0) ? ring.length() - 1 : leftPosition - 1;
			leftMoves++;
		}

		int rightPosition = ringPosition;
		int rightMoves = 0;
		while (key.charAt(keyPosition) != ring.charAt(rightPosition)) {
			rightPosition = (rightPosition + 1 > ring.length() - 1) ? 0 : rightPosition + 1;
			rightMoves++;
		}
		
//		System.out.println("Key: "+key.charAt(keyPosition) + " left:"+leftMoves + " right " + rightMoves);

		int best = Math.min(rightMoves + 1 + best(ring, key, keyPosition + 1, rightPosition, pathMap),
				leftMoves + 1 + best(ring, key, keyPosition + 1, leftPosition, pathMap));

		if (!pathMap.containsKey(ringPosition)) {
			pathMap.put(ringPosition, new HashMap<>());
		}
		
//		System.out.println("ring: " + ringPosition + " key:" + keyPosition + " best "+best);

		pathMap.get(ringPosition).put(keyPosition, best);

		return best;

	}
}
