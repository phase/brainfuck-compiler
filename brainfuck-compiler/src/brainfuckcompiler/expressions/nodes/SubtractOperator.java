package brainfuckcompiler.expressions.nodes;

import brainfuckcompiler.expressions.nodetypes.BinaryOperator;
import brainfuckcompiler.statics;

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
    public int generateBF()
    {
        int left = this.left.generateBF(), right = this.right.generateBF();
        int res = statics.t.subtract(left, right);
        statics.t.free(right);
        return res;
    }
}
