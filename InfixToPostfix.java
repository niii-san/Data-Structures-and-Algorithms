public class InfixToPostfix {

    int precedence(char ch) {

        if (ch == '+' || ch == '-') {
            return 1;
        }

        if (ch == '*' || ch == '/') {
            return 2;
        }

        if (ch == '^') {
            return 3;
        }
        return -1;

    }

    String convertInfixToPostfix(String expression) {
        String result = "";
        int expLength = expression.length();
        Stacks stk = new Stacks(expLength);

        for (int i = 0; i < expLength; i++) {

            char ch = expression.charAt(i);

            if (precedence(ch) > 0) {
                while (!stk.isEmpty() && precedence(stk.peek()) >= precedence(ch)) {

                }

                // operator
            } else {
                // bracket or operand

                if (ch == '(') {
                    stk.push(ch);
                } else if (ch == ')') {
                    char x = stk.pop();
                    while (x != '(') {
                        result += ch;
                        x = stk.pop();
                    }
                } else {
                    // operand
                    result += ch;
                }

            }

        }

        return result;
    }

}
