package brainfuckcompiler.compiler.expressions.nodes;

import brainfuckcompiler.compiler.expressions.nodetypes.BinaryOperator;
import brainfuckcompiler.statics;

/**
 *
 * @author vrighter
 */
public class SubtractOperator extends BinaryOperator
{

    public SubtractOperator(int lineNumber)
    {
        super(lineNumber);
    }

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
