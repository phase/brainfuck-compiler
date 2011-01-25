package brainfuckcompiler.expressions.nodes;

import brainfuckcompiler.code.BrainfuckTools;
import brainfuckcompiler.expressions.nodetypes.BinaryOperator;

/**
 *
 * @author vrighter
 */
public class SubtractOperator extends BinaryOperator
{

    /**
     *
     * @param t
     * @return
     */
    public int generateBF(BrainfuckTools t)
    {
        int left = this.left.generateBF(t), right = this.right.generateBF(t);
        int res = t.subtract(left, right);
        t.free(right);
        return res;
    }
}
