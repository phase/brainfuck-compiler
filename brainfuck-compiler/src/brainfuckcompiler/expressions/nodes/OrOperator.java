package brainfuckcompiler.expressions.nodes;

import brainfuckcompiler.code.BrainfuckTools;
import brainfuckcompiler.expressions.nodetypes.BinaryOperator;
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
    public OrOperator()
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
        int x = left.returnsBoolean ? left.generateBF() : statics.t.toBoolean(left.generateBF()),
                y = right.returnsBoolean ? right.generateBF() : statics.t.toBoolean(right.generateBF());
        return statics.t.or(x, y);
    }
}
