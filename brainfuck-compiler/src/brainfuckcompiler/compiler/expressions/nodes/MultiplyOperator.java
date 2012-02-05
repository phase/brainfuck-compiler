package brainfuckcompiler.compiler.expressions.nodes;

import brainfuckcompiler.compiler.expressions.nodetypes.BinaryOperator;
import brainfuckcompiler.statics;

/**
 *
 * @author vrighter
 */
public class MultiplyOperator extends BinaryOperator
{

    public MultiplyOperator(int lineNumber)
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
        int x = this.left.generateBF(), y = this.right.generateBF();
        int res = statics.t.multiply(x, y);
        statics.t.clear(y);
        statics.t.free(y);
        return res;
    }
}
