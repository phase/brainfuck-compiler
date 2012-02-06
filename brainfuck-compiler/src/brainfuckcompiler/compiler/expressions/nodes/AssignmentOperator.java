package brainfuckcompiler.compiler.expressions.nodes;

import brainfuckcompiler.compiler.expressions.nodetypes.BinaryOperator;
import brainfuckcompiler.compiler.expressions.nodetypes.VariableNode;
import brainfuckcompiler.compiler.program.Variable;
import brainfuckcompiler.compiler.program.structure.Block;
import brainfuckcompiler.statics;

public class AssignmentOperator extends BinaryOperator
{

    public AssignmentOperator(int lineNumber, Block parentBlock)
    {
        super(lineNumber, parentBlock);
    }

    @Override
    public int generateBF()
    {
        if (parentNode != null)
        {
            System.out.println("Assignment operator must be root node of expression tree at line " + lineNumber);
            System.exit(0);
        }
        if (left instanceof VariableNode)
        {
            Variable v = ((VariableNode) left).getVariable();
            int address = right.generateBF();
            statics.t.clear(v.getMemoryPosition());
            statics.t.move(v.getMemoryPosition(), address);
            statics.t.free(address);
        }
        return -1;
    }
}
