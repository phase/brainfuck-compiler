package brainfuckcompiler.expressions.nodes;

import brainfuckcompiler.code.BrainfuckTools;
import brainfuckcompiler.expressions.nodetypes.BinaryOperator;

/**
 *
 * @author vrighter
 */
public class AndOperator extends BinaryOperator
{

    /**
     *
     */
    public AndOperator()
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
        int x = left.returnsBoolean ? left.generateBF(t) : t.toBoolean(left.generateBF(t)),
                y = right.returnsBoolean ? right.generateBF(t) : t.toBoolean(right.generateBF(t));
        int res = t.and(x, y);
        t.clear(y);
        t.free(y);
        return res;
    }
}
