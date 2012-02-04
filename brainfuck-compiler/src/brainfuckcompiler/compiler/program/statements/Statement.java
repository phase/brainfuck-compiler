package brainfuckcompiler.compiler.program.statements;

import brainfuckcompiler.compiler.program.structure.Block;
import brainfuckcompiler.compiler.program.structure.Item;
import java.util.ArrayList;

public abstract class Statement
{

    Block parentBlock;

    public Statement(Block parentBlock)
    {
        this.parentBlock = parentBlock;
    }

    public abstract int parseStatement(ArrayList<Item> items, int currentPosition);
}
