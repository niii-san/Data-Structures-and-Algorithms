package __practice;

import stacks.IntStack;

public class Q4_Queue2Stacks {
    IntStack stack1;
    IntStack stack2;

    public Q4_Queue2Stacks(int size) {
        stack1 = new IntStack(size);
        stack2 = new IntStack(size);
    }

    public void enqueue(int data) {
        if (stack1.isFull()) {
            System.out.println("queue is full");
            return;
        }
        stack1.push(data);
    }

    public int dequeue() {
        if (stack1.isEmpty()) {
            System.out.println("queue is empty");
            return -1;
        }
        // first pop--push in stack2 till stack 1 is empty
        while (!stack1.isEmpty()) {
            stack2.push(stack1.pop());
        }
        // the top element of the stack 2 becomes the item to be dequeue
        int dequeued = stack2.pop();

        // after dequeuing again pop-push form stack 2 to stack 1;

        while (!stack2.isEmpty()) {
            stack1.push(stack2.pop());
        }
        return dequeued;
    }

    public int peek() {
        while (!stack1.isEmpty()) {
            stack2.push(stack1.pop());
        }
        int peekItem = stack2.peek();

        while (!stack2.isEmpty()) {
            stack1.push(stack2.pop());
        }
        return peekItem;
    }

    public static void main(String[] args) {

        Q4_Queue2Stacks q1 = new Q4_Queue2Stacks(5);
         q1.enqueue(5);
         q1.enqueue(9);
         q1.enqueue(10);
         q1.enqueue(13);
         q1.enqueue(55);
         System.out.println(q1.peek());







    }
}
