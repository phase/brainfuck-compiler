package brainfuckcompiler.expressions.nodes;

import brainfuckcompiler.code.BrainfuckTools;
import brainfuckcompiler.expressions.nodetypes.BinaryOperator;

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
    public int generateBF(BrainfuckTools t)
    {
        int x = left.returnsBoolean ? left.generateBF(t) : t.toBoolean(left.generateBF(t)),
                y = right.returnsBoolean ? right.generateBF(t) : t.toBoolean(right.generateBF(t));
        return t.or(x, y);
    }
}
