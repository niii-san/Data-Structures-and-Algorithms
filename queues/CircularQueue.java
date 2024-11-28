package queues;

public class CircularQueue {

    int size;
    int[] queue;
    int front = -1;
    int rear = -1;

    public CircularQueue(int size) {
        this.size = size;
        this.queue = new int[size];
    }

    public void enqueue(int data) {
        if (isFull()) {
            System.out.println("queue is currently full");
        } else {
            if (isEmpty()) {
                front++;
            }
            rear = (rear + 1) % size;
            queue[rear] = data;
        }
    }

    public int dequeue() {
        if (isEmpty()) {
            System.out.println("queue is empty");
            return -1;
        } else {
            if (front == rear) {
                front = rear = -1;
            }
            int temp = front;
            front = (front + 1) % size;
            return queue[temp];
        }
    }

    public int[] getFullQueue() {
        return queue;
    }

    public int peek() {
        if (isEmpty()) {
            System.out.println("queue is empty");
            return -1;
        }
        return queue[front];
    }

    public boolean isEmpty() {
        return front == -1;
    }

    public boolean isFull() {
        return (rear + 1) % size == front;
    }

}
