package stacks;

public class CharStack {

    char[] stack;
    int size;
    int top = -1;

    public CharStack(int size) {
        this.size = size;
        stack = new char[size];
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public boolean isFull() {

        return top == size - 1;
    }

    public void push(char element) {
        if (isFull()) {
            System.out.println("Stack is full for char: " + element);
        } else {
            stack[++top] = element;
        }
    }

    public char pop() {
        if (isEmpty()) {
            System.out.println("Stack is empty! nothing to pop");
            throw new Error("Empty stack");
        } else {
            return stack[top--];
        }
    }

    public char peek() {
        if (isEmpty()) {
            throw new Error("Stack is empty< nothing to peek");
        } else {
            return stack[top];
        }
    }

    public char[] getFullStack() {
        return stack;
    }

}
