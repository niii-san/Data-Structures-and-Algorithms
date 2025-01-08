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
        if (isEmpty()) {
            front = 0;
            rear = 0;
            queue[rear] = data;

        } else {
            if (isFull()) {
                System.out.println("Queue is full");
            } else {
                rear = (rear + 1) % size;
                queue[rear] = data;
            }

        }
    }

    public int dequeue() {

        if (isEmpty()) {
            System.out.println("queue is empty");
            return -1;
        } else {
            int temp = queue[front];
            if (front == rear) {
                front = rear = -1;
            } else {
                front = (front + 1) % size;
            }
            return temp;
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
