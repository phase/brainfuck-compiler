package brainfuckcompiler.expressions.nodes;

import brainfuckcompiler.expressions.nodetypes.UnaryOperator;
import brainfuckcompiler.statics;

/**
 *
 * @author vrighter
 */
public class NotOperator extends UnaryOperator
{

    /**
     *
     */
    public NotOperator()
    {
        returnsBoolean = true;
    }

    /**
     *
     * @param t
     * @return
     */
    public int generateBF()
    {
        int x = operand.returnsBoolean ? operand.generateBF() : statics.t.toBoolean(operand.generateBF());
        return statics.t.not(x);
    }
}
