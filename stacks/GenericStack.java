package stacks;

@SuppressWarnings("unchecked")

// Using generic type
public class GenericStack<T> {

    T stack[];
    int size;
    int top = -1;

    public GenericStack(int size) {
        this.size = size;
        stack = (T[]) new Object[size];
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public boolean isFull() {
        return top == size - 1;
    }

    public void push(T element) {
        if (isFull()) {
            System.out.println("Cannot push " + element + " stack is full");

        } else {

            stack[++top] = element;
        }
    }

    public T pop() {
        if (isEmpty()) {
            return null;
        } else {
            return stack[top--];
        }
    }

    public T peek() {
        if (isEmpty()) {
            return null;
        }
        return stack[top];
    }

    public T[] getFullStack() {
        return stack;
    }

}
