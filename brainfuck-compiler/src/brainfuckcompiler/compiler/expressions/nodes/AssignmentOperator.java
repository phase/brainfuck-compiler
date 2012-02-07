package brainfuckcompiler.compiler.expressions.nodes;

import brainfuckcompiler.compiler.expressions.nodetypes.ArrayNode;
import brainfuckcompiler.compiler.expressions.nodetypes.BinaryOperator;
import brainfuckcompiler.compiler.expressions.nodetypes.VariableNode;
import brainfuckcompiler.compiler.program.Array;
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
        } else if (left instanceof ArrayNode)
        {
            Array a = ((ArrayNode) left).getArray();
            int pos = ((ArrayNode) left).getExpression().generateBF();
            int val = right.generateBF();
            a.set(pos, val);
            statics.t.clear(pos);
            statics.t.clear(val);
            statics.t.free(pos, val);
        } else
        {
            System.out.println("Assignment must be done to an array or a variable at line " + lineNumber);
            System.exit(0);
        }
        return -1;
    }
}
