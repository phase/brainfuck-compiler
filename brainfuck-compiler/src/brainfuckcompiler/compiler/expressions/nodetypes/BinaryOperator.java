package brainfuckcompiler.compiler.expressions.nodetypes;

import brainfuckcompiler.compiler.expressions.Node;
import brainfuckcompiler.compiler.expressions.operators.Operators;

/**
 *
 * @author vrighter
 */
public abstract class BinaryOperator extends Node
{

    public BinaryOperator(int lineNumber)
    {
        super(lineNumber);
    }
    /**
     *
     */
    public Node left = null, right = null;

    /**
     *
     * @param tokens
     * @param index
     * @return
     */
    public int populate(String[] tokens, int index)
    {
        right = Operators.createNode(tokens[index--], lineNumber);
        right.setParentNode(this);
        index = right.populate(tokens, index);
        left = Operators.createNode(tokens[index--], lineNumber);
        left.setParentNode(this);
        index = left.populate(tokens, index);
        return index;
    }
}
