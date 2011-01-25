package brainfuckcompiler.expressions.nodes;

import brainfuckcompiler.code.BrainfuckTools;
import brainfuckcompiler.expressions.nodetypes.BinaryOperator;
import brainfuckcompiler.statics;

/**
 *
 * @author vrighter
 */
public class AddOperator extends BinaryOperator
{

    /**
     *
     * @param t
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
