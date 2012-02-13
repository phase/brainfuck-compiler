package brainfuckcompiler.compiler.expressions.nodetypes;

import brainfuckcompiler.code.random.RandomNumberGenerator;
import brainfuckcompiler.compiler.expressions.Node;
import brainfuckcompiler.compiler.program.structure.Block;
import brainfuckcompiler.statics;

public class RandomNode extends Node
{

    private String variableName;

    public RandomNode(int lineNumber, Block parentBlock)
    {
        super(lineNumber, parentBlock);
    }

    /**
     *
     * @param tokens
     * @param index
     * @return
     */
    public int populate(String[] tokens, int index)
    {
        variableName = tokens[index + 1];
        return index;
    }

    /**
     *
     * @return
     */
    public int generateBF()
    {
        int ret = statics.t.alloc();
        statics.gen.generateRandomNumber(ret);
        return ret;
    }
}
