package brainfuckcompiler.expressions;

import brainfuckcompiler.expressions.operators.Operator;
import brainfuckcompiler.expressions.operators.Operators;
import brainfuckcompiler.statics;
import java.util.Stack;
import java.util.regex.Matcher;

/**
 *
 * @author vrighter
 */
public class ExpressionGenerator
{

    private static String convertInfixToPostfix(String e)
    {
        Operator paren = new Operator("(","\\(",Integer.MAX_VALUE, true), temp;
        Stack s = new Stack();
        StringBuilder b = new StringBuilder();
        String[] tokens = tokenize(e);
        if (tokens == null)
        {
            return "";
        }
        for (int i = 0; i < tokens.length; i++)
        {
            String t = tokens[i];
            if (t.matches("[0-9]+"))
            {
                b.append(t);
                b.append(" ");
            } else if (t.equals("("))
            {
                s.push(paren);
            } else if (t.equals(")"))
            {
                while (!(temp = (Operator) s.pop()).getOp().equals("("))
                {
                    b.append(temp.getOp());
                    b.append(' ');
                }
            } else
            {
                Operator o = statics.ops.getOperator(t);
                if (s.empty())
                {
                    s.push(o);
                } else
                {
                    while (true)
                    {
                        if (s.empty())
                        {
                            s.push(o);
                            break;
                        }
                        Operator topStack = (Operator) s.peek();
                        if ((topStack.isLeftAssociative() && topStack.getPrecedence() <= o.getPrecedence()) || (!topStack.isLeftAssociative() && topStack.getPrecedence() < o.getPrecedence()))
                        {
                            b.append(((Operator) s.pop()).getOp());
                            b.append(' ');
                        } else
                        {
                            s.push(o);
                            break;
                        }
                    }
                }
            }
        }
        while (!s.empty())
        {
            b.append(((Operator) s.pop()).getOp());
            b.append(" ");
        }
        return b.substring(0, b.length() - 1);
    }

    private static String[] tokenize(String e)
    {
        Matcher m = statics.p.matcher(e);
        java.util.ArrayList<String> l = new java.util.ArrayList<String>();
        while(m.find())l.add(m.group());
        String[] ret = new String[l.size()];
        l.toArray(ret);
        return ret;
    }

    /**
     *
     * @param s
     * @return
     */
    public static int generateCodeForExpression(String s)
    {
        return generateCodeForPostfixExpression(convertInfixToPostfix(s));
    }

    private static int generateCodeForPostfixExpression(String s)
    {
        String[] tokens = s.split(" ");
        Node tree = Operators.createNode(tokens[tokens.length - 1]);
        tree.populate(tokens, tokens.length - 2);
        return tree.generateBF();
    }
}
