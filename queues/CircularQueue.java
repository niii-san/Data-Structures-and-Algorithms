package queues;

public class CircularQueue {

    int front = -1;
    int rear = -1;
    int size;
    int queue[];

    CircularQueue(int size) {
        this.size = size;
        this.queue = new int[size];
    }

    void enqueue(int data) {

        if (isFull()) {
            System.out.println("queue is full");
        } else {
            if (front == -1) {
                front = 0;
            }
            rear = (rear + 1) % size;
            queue[rear] = data;
        }
    }

    int dequeue() {
        if (isEmpty()) {
            System.out.println("queue is empty");
            return -1;
        } else {
            if (front == rear) {
                int temp = front;
                front = rear = -1;
                return queue[temp];
            }

            int temp = front;
            front = (front + 1) % size;
            return queue[temp];

        }
    }

    boolean isEmpty() {
        return front == -1 && rear == -1;
    }

    boolean isFull() {
        return (rear + 1) % size == front;
    }

}
