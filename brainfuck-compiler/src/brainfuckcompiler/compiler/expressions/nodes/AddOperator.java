package brainfuckcompiler.compiler.expressions.nodes;

import brainfuckcompiler.compiler.expressions.nodetypes.BinaryOperator;
import brainfuckcompiler.statics;

/**
 *
 * @author vrighter
 */
public class AddOperator extends BinaryOperator
{

    public AddOperator(int lineNumber)
    {
        super(lineNumber);
    }

    /**
     *
     * @return
     */
    public int generateBF()
    {
        int left = this.left.generateBF(), right = this.right.generateBF();
        int res = statics.t.add(left, right);
        statics.t.free(right);
        return res;
    }
}
