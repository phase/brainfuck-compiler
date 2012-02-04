package brainfuckcompiler.compiler.program.structure;

public abstract class Item
{

    protected int indentLevel;
    protected Block parentBlock;

    public Item(int indentLevel, Block parentBlock)
    {
        this.indentLevel = indentLevel;
        this.parentBlock = parentBlock;
    }

    public void setParentBlock(Block parentBlock)
    {
        this.parentBlock = parentBlock;
    }

    public Block getParentBlock()
    {
        return parentBlock;
    }

    public int getIndentLevel()
    {
        return indentLevel;
    }
}