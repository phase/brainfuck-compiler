package brainfuckcompiler.expressions.nodes;

import brainfuckcompiler.expressions.nodetypes.BinaryOperator;
import brainfuckcompiler.statics;

/**
 *
 * @author vrighter
 */
public class SmallerThanOperator extends BinaryOperator
{

    /**
     *
     */
    public SmallerThanOperator()
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
        return res;
    }
}
