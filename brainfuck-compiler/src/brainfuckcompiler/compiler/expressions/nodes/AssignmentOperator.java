package brainfuckcompiler.compiler.expressions.nodes;

import brainfuckcompiler.compiler.expressions.nodetypes.BinaryOperator;

public class AssignmentOperator extends BinaryOperator
{

    public AssignmentOperator(int lineNumber)
    {
        super(lineNumber);
    }

    @Override
    public int generateBF()
    {
        return -1;
    }
}
