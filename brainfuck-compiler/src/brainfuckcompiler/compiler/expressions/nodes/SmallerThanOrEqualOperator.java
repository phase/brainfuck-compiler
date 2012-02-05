package brainfuckcompiler.compiler.expressions.nodes;

import brainfuckcompiler.compiler.expressions.nodetypes.BinaryOperator;
import brainfuckcompiler.statics;

/**
 *
 * @author vrighter
 */
public class SmallerThanOrEqualOperator extends BinaryOperator
{

    /**
     *
     */
    public SmallerThanOrEqualOperator(int lineNumber)
    {
        super(lineNumber);
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
        return res;
    }
}
