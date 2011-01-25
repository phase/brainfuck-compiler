package brainfuckcompiler.expressions.nodes;

import brainfuckcompiler.expressions.nodetypes.BinaryOperator;
import brainfuckcompiler.statics;

/**
 *
 * @author vrighter
 */
public class ModuloOperator extends BinaryOperator
{

    /**
     *
     * @param t
     * @return
     */
    public int generateBF()
    {
        int x = left.generateBF(), y = right.generateBF();
        int res = statics.t.mod(x, y);
        statics.t.clear(y);
        statics.t.free(y);
        return res;
    }
}
