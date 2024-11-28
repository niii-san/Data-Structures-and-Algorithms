package etcs;

public class CheckParenthesis {

    boolean chekcParenthesis(String exp) {

        int EXPLENGTH = exp.length();

        Stacks stk = new Stacks(EXPLENGTH);
        String openbraces = "[{(";
        String closedbraces = "]})";

        for (int i = 0; i < EXPLENGTH; i++) {
            char ch = exp.charAt(i);

            if (ch == '[' || ch == '{' || ch == '(') {
                stk.push(ch);
            } else {
                // closed brackets
                int indx = closedbraces.indexOf(ch);
                char openBracket = openbraces.charAt(indx);

                if (stk.isEmpty()) {
                    return false;
                }
                if (ch != stk.pop()) {
                    return false;
                }
            }
        }
        if (!stk.isEmpty()) {
            return false;
        }
        return true;
    }
}
