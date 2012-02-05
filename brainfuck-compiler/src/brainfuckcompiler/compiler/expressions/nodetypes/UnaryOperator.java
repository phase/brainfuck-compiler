package brainfuckcompiler.compiler.expressions.nodetypes;

import brainfuckcompiler.compiler.expressions.Node;
import brainfuckcompiler.compiler.expressions.operators.Operators;

/**
 *
 * @author vrighter
 */
public abstract class UnaryOperator extends Node
{

    public UnaryOperator(int lineNumber)
    {
        super(lineNumber);
    }
    public Node operand;

    /**
     *
     * @param tokens
     * @param index
     * @return
     */
    public int populate(String[] tokens, int index)
    {
        operand = Operators.createNode(tokens[index--], lineNumber);
        operand.setParentNode(this);
        index = operand.populate(tokens, index);
        return index;
    }
}
