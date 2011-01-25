package brainfuckcompiler.expressions.nodes;

import brainfuckcompiler.expressions.nodetypes.BinaryOperator;
import brainfuckcompiler.statics;

/**
 *
 * @author vrighter
 */
public class LargerThanOrEqualOperator extends BinaryOperator
{

    /**
     *
     */
    public LargerThanOrEqualOperator()
    {
        returnsBoolean = true;
    }

    /**
     *
     * @param t
     * @return
     */
    public int generateBF()
    {
        int x = left.generateBF(), y = right.generateBF();
        int res = statics.t.smallerthan(x, y);
        statics.t.clear(y);
        statics.t.free(y);
        res = statics.t.not(res);
        return res;
    }
}
