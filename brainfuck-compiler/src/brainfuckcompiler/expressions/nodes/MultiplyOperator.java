package brainfuckcompiler.expressions.nodes;

import brainfuckcompiler.code.BrainfuckTools;
import brainfuckcompiler.expressions.nodetypes.BinaryOperator;

/**
 *
 * @author vrighter
 */
public class MultiplyOperator extends BinaryOperator
{

    /**
     *
     * @param t
     * @return
     */
    public int generateBF(BrainfuckTools t)
    {
        int x = this.left.generateBF(t), y = this.right.generateBF(t);
        int res = t.multiply(x, y);
        t.clear(y);
        t.free(y);
        return res;
    }
}
