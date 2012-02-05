package brainfuckcompiler.compiler.expressions.nodes;

import brainfuckcompiler.compiler.expressions.nodetypes.BinaryOperator;
import brainfuckcompiler.statics;

/**
 *
 * @author vrighter
 */
public class AndOperator extends BinaryOperator
{

    /**
     *
     */
    public AndOperator(int lineNumber)
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
        int x = left.returnsBoolean ? left.generateBF() : statics.t.toBoolean(left.generateBF()),
                y = right.returnsBoolean ? right.generateBF() : statics.t.toBoolean(right.generateBF());
        int res = statics.t.and(x, y);
        statics.t.clear(y);
        statics.t.free(y);
        return res;
    }
}
