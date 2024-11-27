package stacks;

public class IntStack {

    int size;
    int[] stack;
    int top = -1;

    public IntStack(int size) {
        this.size = size;
        this.stack = new int[size];
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public boolean isFull() {
        return top == size - 1;
    }

    public void push(int element) {

        if (isFull()) {
            System.out.println("Stack is full for element " + element);
        } else {
            stack[++top] = element;
        }
    }

    public int pop() {
        return stack[top--];
    }

    public int peek() {
        if (isEmpty()) {
            System.out.println("Stack is empty");
            return -1;
        } else {
            return stack[top];
        }
    }

    public int[] getFullStack() {
        return stack;
    }

}
