package brainfuckcompiler.compiler.program.statements;

import brainfuckcompiler.compiler.program.structure.Block;
import brainfuckcompiler.compiler.program.structure.Item;
import java.util.ArrayList;

public class BlockStatement extends Statement
{

    private Block block;

    public BlockStatement(Block block, Block parentBlock)
    {
        super(parentBlock);
        this.block = block;
        this.block.generateStatements();
    }

    public int parseStatement(ArrayList<Item> items, int currentPosition)
    {
        return currentPosition;
    }
}
