package brainfuckcompiler.compiler.expressions.nodes;

import brainfuckcompiler.compiler.expressions.nodetypes.BinaryOperator;
import brainfuckcompiler.statics;

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
    public int generateBF()
    {
        int x = left.generateBF(), y = right.generateBF();
        int res = statics.t.smallerthanorequal(x, y);
        statics.t.clear(y);
        statics.t.free(y);
        res = statics.t.not(res);
        return res;
    }
}
