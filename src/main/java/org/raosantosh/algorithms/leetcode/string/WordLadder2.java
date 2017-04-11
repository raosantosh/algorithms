package com.yahoo.sample.string;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;

public class WordLadder2 {

	public static void main(String args[]) {
		WordLadder2 wordLadder = new WordLadder2();
		
		String wordList = "kid,tag,pup,ail,tun,woo,erg,luz,brr,gay,sip,kay,per,val,mes,ohs,now,boa,cet,pal,bar,die,war,hay,eco,pub,lob,rue,fry,lit,rex,jan,cot,bid,ali,pay,col,gum,ger,row,won,dan,rum,fad,tut,sag,yip,sui,ark,has,zip,fez,own,ump,dis,ads,max,jaw,out,btu,ana,gap,cry,led,abe,box,ore,pig,fie,toy,fat,cal,lie,noh,sew,ono,tam,flu,mgm,ply,awe,pry,tit,tie,yet,too,tax,jim,san,pan,map,ski,ova,wed,non,wac,nut,why,bye,lye,oct,old,fin,feb,chi,sap,owl,log,tod,dot,bow,fob,for,joe,ivy,fan,age,fax,hip,jib,mel,hus,sob,ifs,tab,ara,dab,jag,jar,arm,lot,tom,sax,tex,yum,pei,wen,wry,ire,irk,far,mew,wit,doe,gas,rte,ian,pot,ask,wag,hag,amy,nag,ron,soy,gin,don,tug,fay,vic,boo,nam,ave,buy,sop,but,orb,fen,paw,his,sub,bob,yea,oft,inn,rod,yam,pew,web,hod,hun,gyp,wei,wis,rob,gad,pie,mon,dog,bib,rub,ere,dig,era,cat,fox,bee,mod,day,apr,vie,nev,jam,pam,new,aye,ani,and,ibm,yap,can,pyx,tar,kin,fog,hum,pip,cup,dye,lyx,jog,nun,par,wan,fey,bus,oak,bad,ats,set,qom,vat,eat,pus,rev,axe,ion,six,ila,lao,mom,mas,pro,few,opt,poe,art,ash,oar,cap,lop,may,shy,rid,bat,sum,rim,fee,bmw,sky,maj,hue,thy,ava,rap,den,fla,auk,cox,ibo,hey,saw,vim,sec,ltd,you,its,tat,dew,eva,tog,ram,let,see,zit,maw,nix,ate,gig,rep,owe,ind,hog,eve,sam,zoo,any,dow,cod,bed,vet,ham,sis,hex,via,fir,nod,mao,aug,mum,hoe,bah,hal,keg,hew,zed,tow,gog,ass,dem,who,bet,gos,son,ear,spy,kit,boy,due,sen,oaf,mix,hep,fur,ada,bin,nil,mia,ewe,hit,fix,sad,rib,eye,hop,haw,wax,mid,tad,ken,wad,rye,pap,bog,gut,ito,woe,our,ado,sin,mad,ray,hon,roy,dip,hen,iva,lug,asp,hui,yak,bay,poi,yep,bun,try,lad,elm,nat,wyo,gym,dug,toe,dee,wig,sly,rip,geo,cog,pas,zen,odd,nan,lay,pod,fit,hem,joy,bum,rio,yon,dec,leg,put,sue,dim,pet,yaw,nub,bit,bur,sid,sun,oil,red,doc,moe,caw,eel,dix,cub,end,gem,off,yew,hug,pop,tub,sgt,lid,pun,ton,sol,din,yup,jab,pea,bug,gag,mil,jig,hub,low,did,tin,get,gte,sox,lei,mig,fig,lon,use,ban,flo,nov,jut,bag,mir,sty,lap,two,ins,con,ant,net,tux,ode,stu,mug,cad,nap,gun,fop,tot,sow,sal,sic,ted,wot,del,imp,cob,way,ann,tan,mci,job,wet,ism,err,him,all,pad,hah,hie,aim,ike,jed,ego,mac,baa,min,com,ill,was,cab,ago,ina,big,ilk,gal,tap,duh,ola,ran,lab,top,gob,hot,ora,tia,kip,han,met,hut,she,sac,fed,goo,tee,ell,not,act,gil,rut,ala,ape,rig,cid,god,duo,lin,aid,gel,awl,lag,elf,liz,ref,aha,fib,oho,tho,her,nor,ace,adz,fun,ned,coo,win,tao,coy,van,man,pit,guy,foe,hid,mai,sup,jay,hob,mow,jot,are,pol,arc,lax,aft,alb,len,air,pug,pox,vow,got,meg,zoe,amp,ale,bud,gee,pin,dun,pat,ten,mob";	
		List<List<String>> result = wordLadder.findLadders("hit", "cog", getString("hot,dot,dog,lot,log,cog"));
		System.out.println("Result: " + result);
		result = wordLadder.findLadders("hit", "cog", getString("hot,dot,dog,lot,log"));
		System.out.println("Result: " + result);
		result = wordLadder.findLadders("cet", "ism", getString(wordList));
		System.out.println("Result: " + result);
		result = wordLadder.findLadders("hot", "dog", getString("hot,dog"));
		System.out.println("Result: " + result);
	}

	private static List<String> getString(String value) {
		String words[] = value.split(",");
		List<String> values = new ArrayList<>();
		for (String word : words) {
			values.add(word);
		}
		return values;
	}

	public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
		List<List<Integer>> paths = new ArrayList<>();
		List<List<String>> strPaths = new ArrayList<>();
		wordList.add(beginWord);
		
		if(beginWord.equals(endWord))
			return strPaths;
		
		Graph graph = new Graph(wordList);
		
		
		List<Integer> currentPath = new ArrayList<>();
		currentPath.add(beginWord.hashCode());
//		dfs(graph, beginWord.hashCode(), endWord.hashCode(), paths, currentPath);
		paths = bfs(graph, beginWord.hashCode(), endWord.hashCode());
		
		for(List<Integer> dummy: paths) {
			List<String> dummyPaths = new ArrayList<>();
			for(Integer hash: dummy) {
				dummyPaths.add(graph.getWord(hash));
			}
			strPaths.add(dummyPaths);
		}

		return strPaths;
	}

	public List<List<Integer>> bfs(Graph graph, Integer currentNode, Integer endNode) {
		Queue<List<Integer>> queue = new ArrayDeque<>();
		List<List<Integer>> paths = new ArrayList<>();

		List<Integer> path = new ArrayList<>();
		path.add(currentNode);
		queue.add(path);
		
		Map<Integer, Integer> visited = new HashMap<>();
		visited.put(currentNode, 0);
		
		while (!queue.isEmpty()) {
			List<Integer> currentPath = queue.poll();
			Set<Integer> adjacents = graph.getAdjacents(currentPath.get(currentPath.size() - 1));
			if(adjacents == null) continue;
			for (Integer adj : adjacents) {
				
				if(visited.containsKey(adj) && visited.get(adj) <= currentPath.size()) {
					 continue;
				}
				
				List<Integer> newPath = new ArrayList<>(currentPath);
				
				newPath.add(adj);
				if(!endNode.equals(adj))
				visited.put(adj, newPath.size());
				
				if(paths.size() > 0 && (newPath.size() > paths.get(0).size()) )
					return paths;
				if (adj.equals(endNode)) {
					paths.add(newPath);
				} else {
					queue.add(newPath);
				}
			}
		}
		
		return paths;
	}

//	public void dfs(Graph graph, String currentNode, String endNode, List<List<String>> paths,
//			List<String> currentPath) {
//
//		if (paths.size() > 0 && currentPath.size() > paths.get(0).size())
//			return;
//
//		if (currentNode.equals(endNode)) {
//			paths.removeIf(st -> (st.size() > currentPath.size()));
//			paths.add(currentPath);
//			return;
//		}
//
//		Set<String> adjacents = graph.getAdjacents(currentNode);
//
//		for (String adjWord : adjacents) {
//			if (!currentPath.contains(adjWord)) {
//				List<String> path = new ArrayList<>(currentPath);
//				path.add(adjWord);
//				dfs(graph, adjWord, endNode, paths, path);
//			}
//		}
//	}

	public static class Graph {
		Map<Integer, Set<Integer>> adjancencyList = new HashMap<>();
		Map<Integer,String> wordMap = new HashMap<>();

		public Graph(List<String> wordList1) {
			List<String> wordList = new ArrayList<>(new HashSet<>(wordList1));
			
			for(String word: wordList)  {
				wordMap.put(word.hashCode(), word);
			}
			
			
			for (int i = 0; i < wordList.size(); ++i) {
				for (int j=i+1 ; j< wordList.size(); j++) {
					if (isHammingOne(wordList.get(i), wordList.get(j))) {
						addToList(wordList.get(i).hashCode(), wordList.get(j).hashCode());
					}
				}
			}
		}
		
		private void addToList(Integer a, Integer b) {
			if(adjancencyList.containsKey(a)) {
				adjancencyList.get(a).add(b);
			} else {
				Set<Integer> adjacents = new HashSet<>();
				adjacents.add(b);
				adjancencyList.put(a, adjacents);
			}
			
			if(adjancencyList.containsKey(b)) {
				adjancencyList.get(b).add(a);
			} else {
				Set<Integer> adjacents = new HashSet<>();
				adjacents.add(a);
				adjancencyList.put(b, adjacents);
			}
		}

		public Set<Integer> getAdjacents(Integer hash) {
			return adjancencyList.get(hash);
		}
		
		public String getWord(Integer hashCode) {
			return wordMap.get(hashCode);
		}

		private boolean isHammingOne(String a, String b) {
			int diff = 0;
			for (int i = 0; i < a.length(); ++i) {
				if (a.charAt(i) != b.charAt(i))
					++diff;
				if (diff > 1)
					return false;
			}

			if (diff == 1)
				return true;
			return false;
		}
	}

}
