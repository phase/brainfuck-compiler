package brainfuckcompiler.expressions.nodes;

import brainfuckcompiler.code.BrainfuckTools;
import brainfuckcompiler.expressions.nodetypes.UnaryOperator;

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
    public int generateBF(BrainfuckTools t)
    {
        int x = operand.returnsBoolean ? operand.generateBF(t) : t.toBoolean(operand.generateBF(t));
        return t.not(x);
    }
}
