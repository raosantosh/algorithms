package org.raosantosh.algorithms.leetcode.sort;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by s.rao on 7/21/18. Given a char array representing tasks CPU need to do. It contains capital letters A to Z
 * where different letters represent different tasks.Tasks could be done without original order. Each task could be done
 * in one interval. For each interval, CPU could finish one task or just be idle.
 *
 * However, there is a non-negative cooling interval n that means between two same tasks, there must be at least n
 * intervals that CPU are doing different tasks or just be idle.
 *
 * You need to return the least number of intervals the CPU will take to finish all the given tasks.
 *
 * Example 1: Input: tasks = ["A","A","A","B","B","B"], n = 2 Output: 8 Explanation: A -> B -> idle -> A -> B -> idle ->
 * A -> B. Note: The number of tasks is in the range [1, 10000]. The integer n is in the range [0, 100].
 */
public class TimeScheduler {

  public static class TaskItemPair implements Comparable<TaskItemPair> {
    int lastEpoc;
    Character task;
    int count;

    public TaskItemPair(int lastEpoc, Character c) {
      this.task = c;
      this.lastEpoc = lastEpoc;
    }

    @Override
    public boolean equals(Object obj) {
      TaskItemPair item = (TaskItemPair)obj;
      return (item.lastEpoc == lastEpoc && item.task == task);
    }

    @Override
    public int compareTo(TaskItemPair o) {
      return lastEpoc - o.lastEpoc;
    }
  }

  public static class TaskCountPair implements Comparable<TaskCountPair> {
    Character task;
    int count;

    public TaskCountPair(int count, Character c) {
      this.task = c;
      this.count = count;
    }

    @Override
    public boolean equals(Object obj) {
      TaskItemPair item = (TaskItemPair)obj;
      return (item.lastEpoc == count && item.task == task);
    }

    @Override
    public int compareTo(TaskCountPair o) {
      return o.count - count;
    }
  }

  public int leastInterval(char[] tasks, int n) {

    Queue<TaskItemPair> items = new PriorityQueue<>();

    Map<Character, Integer> taskCounts = new HashMap<>();

    for(char task: tasks) {
      if(taskCounts.containsKey(task)) {
        taskCounts.put(task, taskCounts.get(task) + 1);
      }
      else {
        items.add(new TaskItemPair(0, task));
        taskCounts.put(task, 1);
      }
    }
    int epoc = 0;
    while(taskCounts.size() > 0) {
      epoc++;
      Queue<TaskCountPair> eligibleElements = new PriorityQueue<>();

      List<TaskItemPair> eligibleItems = new ArrayList<>();

      while (!items.isEmpty() && ((items.peek().lastEpoc == 0) || epoc - items.peek().lastEpoc > n)) {
        TaskItemPair item = items.poll();
        eligibleItems.add(item);
        eligibleElements.add(new TaskCountPair(taskCounts.get(item.task), item.task));
      }

      if (eligibleItems.size() > 0) {
        TaskCountPair item = eligibleElements.poll();
        int currentCount = taskCounts.get(item.task);
        if (currentCount == 1)
          taskCounts.remove(item.task);
        else {
          taskCounts.put(item.task, taskCounts.get(item.task) - 1);
          items.add(new TaskItemPair(epoc, item.task));
        }

        for (TaskItemPair tempItems : eligibleItems) {
          if (tempItems.task != item.task) {
            items.add(tempItems);
          }
        }

      }
    }
    return epoc;
  }

  public static void main(String args[]) {
    char [] tasks = new char[6];
    tasks[0] = 'A';
    tasks[1] = 'A';
    tasks[2] = 'A';
    tasks[3] = 'B';
    tasks[4] = 'B';
    tasks[5] = 'B';

    TimeScheduler scheduler = new TimeScheduler();
    System.out.println("Interval " + scheduler.leastInterval(tasks, 50));

    tasks[0] = 'A';
    tasks[1] = 'A';
    tasks[2] = 'A';
    tasks[3] = 'A';
    tasks[4] = 'A';
    tasks[5] = 'A';
   System.out.println("Interval " + scheduler.leastInterval(tasks, 5));
    tasks = new char[12];
    tasks[0] = 'A';
    tasks[1] = 'A';
    tasks[2] = 'A';
    tasks[3] = 'A';
    tasks[4] = 'A';
    tasks[5] = 'A';
    tasks[6] = 'B';
    tasks[7] = 'C';
    tasks[8] = 'D';
    tasks[9] = 'E';
    tasks[10] = 'F';
    tasks[11] = 'G';
    System.out.println("Interval " + scheduler.leastInterval(tasks, 2));

  }

}
