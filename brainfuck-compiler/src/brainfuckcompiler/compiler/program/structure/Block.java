package brainfuckcompiler.compiler.program.structure;

import brainfuckcompiler.compiler.program.Variable;
import brainfuckcompiler.compiler.program.statements.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Block extends Item
{

    private ArrayList<Item> items;
    private ArrayList<Statement> statements;
    private ArrayList<Variable> variableScope;
    private ArrayList<Array> arrayScope;

    public Block(ArrayList<Item> items, int indentLevel, Block parentBlock, int lineNumber)
    {
        super(indentLevel, parentBlock, lineNumber);
        for (Item i : items)
        {
            i.setParentBlock(this);
        }
        for (int i = 0; i < items.size(); i++)
        {
            Item item = items.get(i);
            int currentIndentLevel = item.getIndentLevel();
            int blockLineNumber = item.getLineNumber();
            if (currentIndentLevel > this.getIndentLevel())
            {
                if (currentIndentLevel == (this.getIndentLevel() + 1))
                {
                    int amtitems = 1;
                    for (int j = i + 1; j < items.size(); j++)
                    {
                        if (items.get(j).getIndentLevel() >= currentIndentLevel)
                        {
                            amtitems++;
                        } else
                        {
                            break;
                        }
                    }
                    ArrayList<Item> newBlockItems = new ArrayList<Item>();
                    for (int j = 0; j < amtitems; j++)
                    {
                        newBlockItems.add(items.remove(i));
                    }
                    items.add(i, new Block(newBlockItems, currentIndentLevel, this, blockLineNumber));
                } else
                {
                    System.out.println("Invalid indent level at line " + blockLineNumber);
                    System.exit(0);
                }
            }
        }
        this.items = items;
        variableScope = new ArrayList<Variable>();
        arrayScope = new ArrayList<Array>();
    }

    public void generateStatements()
    {
        statements = new ArrayList<Statement>();
        int pos = 0;
        while (pos < items.size())
        {
            Item i = items.get(pos);
            if (i instanceof Block)
            {
                Statement s = new BlockStatement((Block) i, this);
                statements.add(s);
                pos++;
                continue;
            }
            Line l = (Line) i;
            if (l.getLine().equals("else"))
            {
                System.out.println("else without an if found at line " + l.getLineNumber());
                System.exit(0);
            }
            if (l.getLine().startsWith("if "))
            {
                Statement s = new IfStatement(this);
                pos = s.parseStatement(items, pos);
                continue;
            }
            if (l.getLine().startsWith("while "))
            {
                Statement s = new WhileStatement(this);
                pos = s.parseStatement(items, pos);
                continue;
            }
            if (l.getLine().startsWith("dowhile "))
            {
                Statement s = new DowhileStatement(this);
                pos = s.parseStatement(items, pos);
                continue;
            }
        }
    }

    public ArrayList<Variable> getVariableScope()
    {
        return variableScope;
    }

    public ArrayList<Array> getArrayScope()
    {
        return arrayScope;
    }
}