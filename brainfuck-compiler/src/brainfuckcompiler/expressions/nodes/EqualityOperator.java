package brainfuckcompiler.expressions.nodes;

import brainfuckcompiler.code.BrainfuckTools;
import brainfuckcompiler.expressions.nodetypes.BinaryOperator;

/**
 *
 * @author vrighter
 */
public class EqualityOperator extends BinaryOperator
{

    /**
     *
     */
    public EqualityOperator()
    {
        returnsBoolean = true;
    }

    /**
     *
     * @param t
     * @return
     */
    public int generateBF(BrainfuckTools t)
    {
        if (!((!left.returnsBoolean) && (!right.returnsBoolean)))
        {
            System.out.println("= must take two non boolean operands");
            System.exit(-1);
        }
        int x = left.generateBF(t), y = right.generateBF(t);
        int res = t.equal(x, y);
        t.clear(y);
        t.free(y);
        return res;
    }
}
