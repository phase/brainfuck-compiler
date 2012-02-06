package brainfuckcompiler.compiler.expressions.nodes;

import brainfuckcompiler.compiler.expressions.nodetypes.BinaryOperator;
import brainfuckcompiler.compiler.program.structure.Block;

public class AssignmentOperator extends BinaryOperator
{

    public AssignmentOperator(int lineNumber, Block parentBlock)
    {
        super(lineNumber, parentBlock);
    }

    @Override
    public int generateBF()
    {
        return -1;
    }
}
