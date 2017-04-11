package com.yahoo.sample.string;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Isomorphic {
	
	public static void main(String args[]) {
		Isomorphic isomorphic = new Isomorphic();
		
		System.out.println(isomorphic.isIsomorphic("egg", "add"));
		System.out.println(isomorphic.isIsomorphic("foo", "bar"));
		System.out.println(isomorphic.isIsomorphic("paper", "title"));
	}
	
    public boolean isIsomorphic(String s, String t) {
     
    	if(s.length() != t.length()) return false;
    	
    	Map<Character, Character> charMap = new HashMap<>();
    	Set<Character> seent = new HashSet<>();
    	
    	for(int i= 0 ; i< s.length() ; ++i) {
    		char sChar = s.charAt(i);
    		char tChar = t.charAt(i);
    		if(!charMap.containsKey(sChar) && seent.contains(tChar)) {
    			return false;
    		}
    		
    		if(charMap.containsKey(sChar) && charMap.get(sChar) != tChar) {
    			return false;
    		}
    		charMap.put(sChar, tChar);
    		seent.add(tChar);
    	}
    	
    	
    	return true;
    }

}
