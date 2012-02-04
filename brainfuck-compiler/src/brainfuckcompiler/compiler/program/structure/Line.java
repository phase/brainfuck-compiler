package brainfuckcompiler.compiler.program.structure;

public class Line extends Item
{

    private String line;
    private int lineNumber;

    public Line(int indentLevel, String line, Block parentBlock, int lineNumber)
    {
        super(indentLevel, parentBlock);
        this.line = line;
        this.lineNumber = lineNumber;
    }

    public String getLine()
    {
        return line;
    }

    public int getLineNumber()
    {
        return lineNumber;
    }
}