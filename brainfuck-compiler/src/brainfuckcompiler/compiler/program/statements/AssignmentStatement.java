package brainfuckcompiler.compiler.program.statements;

import brainfuckcompiler.compiler.expressions.ExpressionGenerator;
import brainfuckcompiler.compiler.expressions.Node;
import brainfuckcompiler.compiler.expressions.nodes.AssignmentOperator;
import brainfuckcompiler.compiler.program.structure.Block;
import brainfuckcompiler.compiler.program.structure.Item;
import brainfuckcompiler.compiler.program.structure.Line;
import java.util.ArrayList;

public class AssignmentStatement extends Statement
{

    Node expression;

    public AssignmentStatement(Block parentBlock, int LineNumber)
    {
        super(parentBlock, LineNumber);
    }

    @Override
    public int parseStatement(ArrayList<Item> items, int currentPosition)
    {
        expression = ExpressionGenerator.generateExpression(((Line) items.get(currentPosition)).getLine(), lineNumber, parentBlock);
        if (!(expression instanceof AssignmentOperator))
        {
            System.out.println("Expression on line " + lineNumber + " must be an assignment");
            System.exit(0);
        }
        currentPosition++;
        return currentPosition;
    }

    @Override
    public void generate()
    {
        expression.generateBF();
    }
}
