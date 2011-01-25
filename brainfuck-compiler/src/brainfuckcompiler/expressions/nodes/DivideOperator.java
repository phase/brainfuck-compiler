package brainfuckcompiler.expressions.nodes;

import brainfuckcompiler.code.BrainfuckTools;
import brainfuckcompiler.expressions.nodetypes.BinaryOperator;

/**
 *
 * @author vrighter
 */
public class DivideOperator extends BinaryOperator
{

    /**
     *
     * @param t
     * @return
     */
    public int generateBF(BrainfuckTools t)
    {
        int x = left.generateBF(t), y = right.generateBF(t);
        int res = t.divide(x, y);
        t.clear(y);
        t.free(y);
        return res;
    }
}
