

import java.util.Arrays;

import linkedlists.*;
import queues.LinearQueue;

public class Playground {
    public static void main(String[] args) {

      LinearQueue q1 = new LinearQueue(5);

      q1.enqueue(1);
      q1.enqueue(2);
      q1.enqueue(3);
      q1.enqueue(4);
      q1.enqueue(5);




      System.out.println(Arrays.toString(q1.getFullQueue()));
      System.out.println("current elem at front > "+q1.currentFrontElement());

      System.out.println(q1.dequeue());
      System.out.println(q1.dequeue());
      System.out.println(q1.dequeue());
      System.out.println(q1.dequeue());

      System.out.println(Arrays.toString(q1.getFullQueue()));
      System.out.println("current elem at front > "+q1.currentFrontElement());
      q1.enqueue(4);
      q1.enqueue(4);
      q1.enqueue(4);

    }
}