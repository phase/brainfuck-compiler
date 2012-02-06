package brainfuckcompiler.compiler.program.statements;

import brainfuckcompiler.compiler.program.structure.Block;
import brainfuckcompiler.compiler.program.structure.Item;
import java.util.ArrayList;

public abstract class Statement
{

    Block parentBlock;
    int lineNumber;

    public Statement(Block parentBlock, int LineNumber)
    {
        this.parentBlock = parentBlock;
        this.lineNumber = lineNumber;
    }

    public abstract int parseStatement(ArrayList<Item> items, int currentPosition);

    public abstract void generate();
}
