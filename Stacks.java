
public class Stacks {

    int[] stk;
    int top = -1;
    int size;

    Stacks(int size) {

        stk = new int[size];
        this.size = size;

    }

    void push(int data) {

        if (isFull()) {
            System.out.println("Stack Overflow");
        } else {
            stk[++top] = data;
        }
    }

    int pop() {

        if (isEmpty()) {
            return -1;
        } else {
            return stk[top--];
        }

    }

    int getTopOfStack() {
        return stk[top];
    }

    boolean isFull() {
        return top == size - 1;
    }

    boolean isEmpty() {
        return top == -1;
    }

    public static void main(String args[]) {

        Stacks s1 = new Stacks(10);
        s1.push(2);
        s1.push(50);
        s1.pop();
        s1.push(100);

        System.out.println("The curr element at top: " + s1.getTopOfStack());

    }
}
