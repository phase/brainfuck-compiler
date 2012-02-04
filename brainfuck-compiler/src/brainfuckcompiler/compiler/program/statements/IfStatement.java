package brainfuckcompiler.compiler.program.statements;

import brainfuckcompiler.compiler.expressions.ExpressionGenerator;
import brainfuckcompiler.compiler.expressions.Node;
import brainfuckcompiler.compiler.program.structure.Block;
import brainfuckcompiler.compiler.program.structure.Item;
import brainfuckcompiler.compiler.program.structure.Line;
import java.util.ArrayList;

public class IfStatement extends Statement
{

    Block ifBlock = null;
    Block elseBlock = null;
    Node expression;

    public IfStatement(Block parentBlock)
    {
        super(parentBlock);
    }

    public int parseStatement(ArrayList<Item> items, int currentPosition)
    {
        Line l = (Line) items.get(currentPosition);
        expression = ExpressionGenerator.generateExpression(l.getLine().substring(3));
        currentPosition++;
        if (currentPosition < items.size())
        {
            Item i = items.get(currentPosition);
            if (i instanceof Block)
            {
                if (i.getIndentLevel() == (l.getIndentLevel() + 1))
                {
                    ifBlock = (Block) i;
                    ifBlock.generateStatements();
                } else
                {
                    System.out.println("Invalid indent level at line " + i.getLineNumber());
                    System.exit(0);
                }
            } else
            {
                System.out.println("Expected code block at line " + (l.getLineNumber() + 1));
                System.exit(0);
            }
        } else
        {
            System.out.println("Expected code block at line " + (l.getLineNumber() + 1));
            System.exit(0);
        }
        currentPosition++;
        if (currentPosition < items.size())
        {
            Item i = items.get(currentPosition);
            if (i instanceof Line)
            {
                Line elseLine = (Line) i;
                if (elseLine.getLine().equals("else"))
                {
                    currentPosition++;
                    if (currentPosition < items.size())
                    {
                        Item eb = items.get(currentPosition);
                        if (eb instanceof Block)
                        {
                            if (eb.getIndentLevel() == (elseLine.getIndentLevel() + 1))
                            {
                                elseBlock = (Block) eb;
                                elseBlock.generateStatements();
                                currentPosition++;
                            } else
                            {
                                System.out.println("Invalid indent level at line " + eb.getLineNumber());
                            }
                        } else
                        {
                            System.out.println("Expected code block at line " + (elseLine.getLineNumber() + 1));
                        }
                    } else
                    {
                        System.out.println("Expected code block at line " + (elseLine.getLineNumber() + 1));
                    }
                }
            }
        }
        return currentPosition;
    }
}
