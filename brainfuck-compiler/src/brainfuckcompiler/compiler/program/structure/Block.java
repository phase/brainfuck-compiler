package brainfuckcompiler.compiler.program.structure;

import java.util.ArrayList;

public class Block extends Item
{

    ArrayList<Item> items;

    public Block(ArrayList<Item> items, int indentLevel, Block parentBlock)
    {
        super(indentLevel, parentBlock);
        for (Item i : items)
        {
            i.setParentBlock(this);
        }
        for (int i = 0; i < items.size(); i++)
        {
            int currentIndentLevel = items.get(i).getIndentLevel();
            if (currentIndentLevel > this.getIndentLevel())
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
                items.add(i, new Block(newBlockItems, currentIndentLevel, this));
            }
        }
        this.items = items;
    }

    public void output()
    {
        outputIndentLevel("Start block at indent level " + indentLevel, -1);
        for (Item i : items)
        {
            if (i instanceof Line)
            {
                Line l = (Line) i;
                outputIndentLevel(l.getLine(), l.getLineNumber());
            } else
            {
                ((Block) i).output();
            }
        }
    }

    private void outputIndentLevel(String s, int lineNumber)
    {
        if (lineNumber >= 0)
        {
            System.out.print(lineNumber);
        }
        System.out.print("\t");
        for (int i = 0; i < indentLevel; i++)
        {
            System.out.print("\t");
        }
        System.out.println(s);
    }
}