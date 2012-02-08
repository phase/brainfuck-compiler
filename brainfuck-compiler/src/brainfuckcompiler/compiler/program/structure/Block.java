package brainfuckcompiler.compiler.program.structure;

import brainfuckcompiler.compiler.program.Array;
import brainfuckcompiler.compiler.program.Variable;
import brainfuckcompiler.compiler.program.statements.*;
import java.util.ArrayList;

public class Block extends Item
{

    private ArrayList<Item> items;
    private ArrayList<Statement> statements;
    private ArrayList<Variable> variableScope;
    private ArrayList<Array> arrayScope;
    private boolean subLock;

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
        subLock = false;
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
                Statement s = new BlockStatement((Block) i, this, i.getLineNumber());
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
            if (l.getLine().startsWith("declare "))
            {
                Statement s = new DeclareStatement(this, l.getLineNumber());
                pos = s.parseStatement(items, pos);
                statements.add(s);
                continue;
            }
            if (l.getLine().equals("debug"))
            {
                Statement s = new DebugStatement(this, l.getLineNumber());
                pos = s.parseStatement(items, pos);
                statements.add(s);
                continue;
            }
            if (l.getLine().startsWith("dim "))
            {
                Statement s = new DimStatement(this, l.getLineNumber());
                pos = s.parseStatement(items, pos);
                statements.add(s);
                continue;
            }
            if (l.getLine().startsWith("sub "))
            {
                if (indentLevel != 0)
                {
                    System.out.println("Subroutines must be declared at indent level 0. Line " + l.getLineNumber());
                    System.exit(0);
                }
                if (subLock)
                {
                    System.out.println("Only declare, dim and debug statements are allowed before function or subroutine declarations. Line " + l.getLineNumber());
                    System.exit(0);
                }
                System.out.println("sub is not implemented yet. Line " + l.getLineNumber());
                System.exit(0);
                continue;
            }
            if (l.getLine().startsWith("func "))
            {
                if (indentLevel != 0)
                {
                    System.out.println("Functions must be declared at indent level 0. Line " + l.getLineNumber());
                    System.exit(0);
                }
                if (subLock)
                {
                    System.out.println("Only declare, dim and debug statements are allowed before function or subroutine declarations. Line " + l.getLineNumber());
                    System.exit(0);
                }
                System.out.println("func is not implemented yet. Line " + l.getLineNumber());
                System.exit(0);
                continue;
            }
            if (l.getLine().startsWith("arrfunc "))
            {
                if (indentLevel != 0)
                {
                    System.out.println("Functions must be declared at indent level 0. Line " + l.getLineNumber());
                    System.exit(0);
                }
                if (subLock)
                {
                    System.out.println("Only declare, dim and debug statements are allowed before function or subroutine declarations. Line " + l.getLineNumber());
                    System.exit(0);
                }
                System.out.println("arrfunc is not implemented yet. Line " + l.getLineNumber());
                System.exit(0);
                continue;
            }
            subLock = true;
            if (l.getLine().startsWith("if "))
            {
                Statement s = new IfStatement(this, l.getLineNumber());
                pos = s.parseStatement(items, pos);
                statements.add(s);
                continue;
            }
            if (l.getLine().startsWith("while "))
            {
                Statement s = new WhileStatement(this, l.getLineNumber());
                pos = s.parseStatement(items, pos);
                statements.add(s);
                continue;
            }
            if (l.getLine().startsWith("dowhile "))
            {
                Statement s = new DowhileStatement(this, l.getLineNumber());
                pos = s.parseStatement(items, pos);
                statements.add(s);
                continue;
            }
            Statement s = new AssignmentStatement(this, l.getLineNumber());
            pos = s.parseStatement(items, pos);
            statements.add(s);
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

    public void generate()
    {
        for (int i = 0; i < statements.size(); i++)
        {
            Statement s = statements.get(i);
            s.generate();
        }
        while (!variableScope.isEmpty())
        {
            Variable v = variableScope.remove(variableScope.size() - 1);
            v.free();
        }
        while (!arrayScope.isEmpty())
        {
            Array a = arrayScope.remove(arrayScope.size() - 1);
            a.free();
        }
    }
}