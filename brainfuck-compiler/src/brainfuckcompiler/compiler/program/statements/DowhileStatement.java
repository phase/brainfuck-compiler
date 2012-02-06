package brainfuckcompiler.compiler.program.statements;

import brainfuckcompiler.compiler.expressions.ExpressionGenerator;
import brainfuckcompiler.compiler.expressions.Node;
import brainfuckcompiler.compiler.expressions.nodes.AssignmentOperator;
import brainfuckcompiler.compiler.program.structure.Block;
import brainfuckcompiler.compiler.program.structure.Item;
import brainfuckcompiler.compiler.program.structure.Line;
import java.util.ArrayList;

public class DowhileStatement extends Statement
{

    Node expression;
    Block loopBlock;

    public DowhileStatement(Block parentBlock)
    {
        super(parentBlock);
    }

    public int parseStatement(ArrayList<Item> items, int currentPosition)
    {
        Line l = (Line) items.get(currentPosition);
        expression = ExpressionGenerator.generateExpression(l.getLine().substring(8), l.getLineNumber(), parentBlock);
        if (expression instanceof AssignmentOperator)
        {
            System.out.println("Cannot assign a value to a variable on line " + l.getLineNumber());
            System.exit(0);
        }
        currentPosition++;
        if (currentPosition < items.size())
        {
            Item i = items.get(currentPosition);
            if (i instanceof Block)
            {
                if (i.getIndentLevel() == (l.getIndentLevel() + 1))
                {
                    loopBlock = (Block) i;
                    loopBlock.generateStatements();
                    currentPosition++;
                } else
                {
                    System.out.println("Invalid indent level at line " + (l.getIndentLevel() + 1));
                    System.exit(0);
                }
            } else
            {
                System.out.println("Expected code block at line " + (l.getLine() + 1));
                System.exit(0);
            }
        } else
        {
            System.out.println("Expected code block at line " + (l.getLine() + 1));
            System.exit(0);
        }
        return currentPosition;
    }
}
