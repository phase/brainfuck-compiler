package brainfuckcompiler.expressions.nodes;

import brainfuckcompiler.code.BrainfuckTools;
import brainfuckcompiler.expressions.nodetypes.BinaryOperator;

/**
 *
 * @author vrighter
 */
public class LargerThanOperator extends BinaryOperator
{

    /**
     *
     */
    public LargerThanOperator()
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
        res = t.not(res);
        return res;
    }
}
