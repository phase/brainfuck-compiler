package brainfuckcompiler.compiler.expressions.nodes;

import brainfuckcompiler.compiler.expressions.nodetypes.BinaryOperator;
import brainfuckcompiler.statics;

/**
 *
 * @author vrighter
 */
public class NonEqualityOperator extends BinaryOperator
{

    /**
     *
     */
    public NonEqualityOperator(int lineNumber)
    {
        super(lineNumber);
        returnsBoolean = true;
    }

    /**
     *
     * @param t
     * @return
     */
    public int generateBF()
    {
        if (!((!left.returnsBoolean) && (!right.returnsBoolean)))
        {
            System.out.println("= must take two non boolean operands");
            System.exit(-1);
        }
        int x = left.generateBF(), y = right.generateBF();
        int res = statics.t.notequal(x, y);
        statics.t.clear(y);
        statics.t.free(y);
        return res;
    }
}
