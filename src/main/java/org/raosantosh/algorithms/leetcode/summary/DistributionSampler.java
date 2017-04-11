package com.yahoo.sample.summary;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/*
 * We could have 2 dice:
 *  Die 1: 4 sides, with probabilities of {0.2, 0.3, 0.1, 0.4}
 *  Die 2: 6 sides, with probabilities of {0.05, 0.1, 0.15, ... }
 * 
 * Take a random sample and return the results for each of the dice.
 * 
 * Sample from the above dice might be [0, 5]
 */

public class DistributionSampler {

	private List<List<Integer>> diceDistribution = new ArrayList<>();

	public static void main(String args[]) {
		double[] dist1 = new double[4];
		dist1[0] = 0.2;
		dist1[1] = 0.3;
		dist1[2] = 0.1;
		dist1[3] = 0.4;
		double[] dist2 = new double[6];
		dist2[0] = 0.05;
		dist2[1] = 0.1;
		dist2[2] = 0.15;
		dist2[3] = 0.25;
		dist2[4] = 0.2;
		dist2[5] = 0.25;
		List<double[]> dices = new ArrayList<>();
		dices.add(dist1);
		dices.add(dist2);
		DistributionSampler sampler = new DistributionSampler(dices);
		for (int i = 0; i < 100; ++i)
			System.out.println(sampler.getDistributions());
	}

	public DistributionSampler(List<double[]> dices) {

		for (double[] currentDice : dices) {
			diceDistribution.add(createDistributionlist(currentDice));
		}
		System.out.println(diceDistribution);
	}

	public List<Integer> getDistributions() {
		List<Integer> result = new ArrayList<>();

		for (List<Integer> distribution : diceDistribution) {
			result.add(sample(distribution));
		}

		return result;
	}

	private List<Integer> createDistributionlist(double[] dist) {
		List<Integer> distrList = new ArrayList<>(dist.length);

		for (int i = 0; i < dist.length; ++i) {
			int num = (int) (dist[i] * 100);
			for (int j = 0; j < num; ++j) {
				distrList.add(i);
			}
		}

		return distrList;
	}

	private int sample(List<Integer> distrList) {
		int random = (new Random()).nextInt(distrList.size() - 1);
		return distrList.get(random);
	}
}
