package brainfuckcompiler.expressions.operators;

import brainfuckcompiler.expressions.Node;
import brainfuckcompiler.expressions.nodes.*;
import brainfuckcompiler.expressions.nodetypes.Constant;
import java.util.Arrays;

/**
 *
 * @author vrighter
 */
public class Operators
{

    private Operator[] operators;

    /**
     *
     */
    public Operators()
    {
        operators = new Operator[]
                {
                    new Operator("!", 0, false),
                    new Operator("^", 1, false),
                    new Operator("*", 2, true),
                    new Operator("/", 2, true),
                    new Operator("%", 2, true),
                    new Operator("+", 3, true),
                    new Operator("-", 3, true),
                    new Operator("<", 4, true),
                    new Operator("<=", 4, true),
                    new Operator(">", 4, true),
                    new Operator(">=", 4, true),
                    new Operator("==", 5, true),
                    new Operator("!=", 5, true),
                    new Operator("&&", 6, true),
                    new Operator("||", 7, true)
                };
        Arrays.sort(operators);
    }

    /**
     *
     * @param token
     * @return
     */
    public Operator getOperator(String token)
    {
        for (int i = 0; i < operators.length; i++)
        {
            if (operators[i].getOp().equals(token))
            {
                return operators[i];
            }
        }
        return null;
    }

    /**
     *
     * @param exp
     * @param index
     * @return
     */
    public String findOperator(String exp, int index)
    {
        for (int i = 0; i < operators.length; i++)
        {
            String s = operators[i].getOp();
            if ((index + s.length()) > exp.length())
            {
                continue;
            }
            if (exp.substring(index, index + s.length()).equals(s))
            {
                return s;
            }
        }
        return null;
    }

    /**
     *
     * @param token
     * @return
     */
    public static Node createNode(String token)
    {
        if (token.equals("*"))
        {
            return new MultiplyOperator();
        }
        if (token.equals("/"))
        {
            return new DivideOperator();
        }
        if (token.equals("+"))
        {
            return new AddOperator();
        }
        if (token.equals("-"))
        {
            return new SubtractOperator();
        }
        if (token.equals("=="))
        {
            return new EqualityOperator();
        }
        if (token.equals("!"))
        {
            return new NotOperator();
        }
        if (token.equals("&&"))
        {
            return new AndOperator();
        }
        if (token.equals("||"))
        {
            return new OrOperator();
        }
        if (token.equals("<"))
        {
            return new SmallerThanOperator();
        }
        if (token.equals(">"))
        {
            return new LargerThanOperator();
        }
        if (token.equals("<="))
        {
            return new SmallerThanOrEqualOperator();
        }
        if (token.equals(">="))
        {
            return new LargerThanOrEqualOperator();
        }
        if (token.equals("!="))
        {
            return new NonEqualityOperator();
        }
        if (token.equals("%"))
        {
            return new ModuloOperator();
        }
        if (token.equals("^"))
        {
            return new ExponentiationOperator();
        }
        if ((token.length() > 0) && ((token.charAt(0) >= '0' && token.charAt(0) <= '9') || (token.charAt(0) == '.')))
        {
            return new Constant();
        }
        return null;
    }
}
