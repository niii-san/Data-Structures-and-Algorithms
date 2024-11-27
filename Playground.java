
import java.util.Arrays;

import stacks.*;

public class Playground {
    public static void main(String[] args) {

        GenericStack<Integer> s1 = new GenericStack<Integer>(5);
        s1.push(20);
        s1.push(30);
        s1.push(40);
        s1.push(50);
        s1.push(60);


        System.out.println(s1.peek());
        System.out.println(Arrays.toString(s1.getFullStack()));

    }
}