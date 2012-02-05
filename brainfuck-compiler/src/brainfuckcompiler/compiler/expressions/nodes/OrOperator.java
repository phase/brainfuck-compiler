package brainfuckcompiler.compiler.expressions.nodes;

import brainfuckcompiler.compiler.expressions.nodetypes.BinaryOperator;
import brainfuckcompiler.statics;

/**
 *
 * @author vrighter
 */
public class OrOperator extends BinaryOperator
{

    /**
     *
     */
    public OrOperator(int lineNumber)
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
        return statics.t.or(x, y);
    }
}
