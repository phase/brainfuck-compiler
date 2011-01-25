package brainfuckcompiler.expressions.nodes;

import brainfuckcompiler.code.BrainfuckTools;
import brainfuckcompiler.expressions.nodetypes.BinaryOperator;

/**
 *
 * @author vrighter
 */
public class SmallerThanOrEqualOperator extends BinaryOperator
{

    /**
     *
     */
    public SmallerThanOrEqualOperator()
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
        int x = left.generateBF(t), y = right.generateBF(t);
        int res = t.smallerthanorequal(x, y);
        t.clear(y);
        t.free(y);
        return res;
    }
}
