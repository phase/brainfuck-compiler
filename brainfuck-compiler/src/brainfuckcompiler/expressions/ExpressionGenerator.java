package brainfuckcompiler.expressions;

import brainfuckcompiler.code.BrainfuckTools;
import brainfuckcompiler.expressions.operators.Operator;
import brainfuckcompiler.expressions.operators.Operators;
import java.util.Stack;

/**
 *
 * @author vrighter
 */
public class ExpressionGenerator
{

    private static String convertInfixToPostfix(String e, Operators ops)
    {
        Operator paren = new Operator("(", Integer.MAX_VALUE, true), temp;
        Stack s = new Stack();
        StringBuilder b = new StringBuilder();
        String[] tokens = tokenize(e,ops);
        if (tokens == null)
        {
            return "";
        }
        for (int i = 0; i < tokens.length; i++)
        {
            String t = tokens[i];
            char c = t.charAt(0);
            if ((c >= '0' && c <= '9') || (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || (c == '.'))
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
                Operator o = ops.getOperator(t);
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

    private static String[] tokenize(String e, Operators ops)
    {
        StringBuilder b = new StringBuilder();
        e = e.trim();
        if (e.length() == 0)
        {
            return null;
        }
        int index = 0;
        char prev = e.charAt(index);
        if (((prev >= '0') && (prev <= '9')) || ((prev >= 'a') && (prev <= 'z')) || ((prev >= 'A') && (prev <= 'Z')) || (prev == '(') || (prev == ')'))
        {
            b.append(prev);
        } else
        {
            String s = ops.findOperator(e, index);
            if (s == null)
            {
                System.out.println("Unexpected token at position " + index);
                System.exit(-1);
            }
            index += (s.length() - 1);
            b.append(s);
            b.append(' ');
            prev = ' ';
        }
        while ((++index) < e.length())
        {
            char c = e.charAt(index);
            if ((c == ' ') && (prev != ' '))
            {
                b.append(c);
                prev = c;
            } else if ((c >= '0' && c <= '9') && (prev >= '0' && prev <= '9'))
            {
                b.append(c);
                prev = c;
            } else if ((c >= '0' && c <= '9') && ((!(prev >= '0' && prev <= '9')) || (prev != ' ')))
            {
                if (prev != ' ')
                {
                    b.append(' ');
                }
                b.append(c);
                prev = c;
            } else if (((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) && ((prev >= 'a' && prev <= 'z') || (prev >= 'A' && prev <= 'Z')))
            {
                b.append(c);
                prev = c;
            } else if (((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) && ((!((prev >= 'a' && prev <= 'z') || (prev >= 'A' && prev <= 'Z'))) || (prev != ' ')))
            {
                if (prev != ' ')
                {
                    b.append(' ');
                }
                b.append(c);
                prev = c;
            } else if (c == '(' || c == ')')
            {
                if (prev != ' ')
                {
                    b.append(' ');
                }
                b.append(c);
                prev = c;
            } else
            {
                String s = ops.findOperator(e, index);
                if (s == null)
                {
                    System.out.println("Unexpected token at position " + index);
                    System.exit(-1);
                }
                index += (s.length() - 1);
                if (prev != ' ')
                {
                    b.append(' ');
                }
                b.append(s);
                b.append(' ');
                prev = ' ';
            }
        }
        return b.toString().split(" ");
    }

    /**
     *
     * @param s
     * @param ops
     * @param t
     * @return
     */
    public static int generateCodeForExpression(String s,Operators ops,BrainfuckTools t)
    {
        return generateCodeForPostfixExpression(t,convertInfixToPostfix(s,ops));
    }

    private static int generateCodeForPostfixExpression(BrainfuckTools t, String s)
    {
        String[] tokens = s.split(" ");
        Node tree = Operators.createNode(tokens[tokens.length - 1]);
        tree.populate(tokens, tokens.length - 2);
        return tree.generateBF(t);
    }
}
