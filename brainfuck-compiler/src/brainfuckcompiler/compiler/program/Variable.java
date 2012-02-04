package brainfuckcompiler.compiler.program;

import brainfuckcompiler.statics;

public class Variable
{

    private String name;
    private int memoryPosition;

    public Variable(String name)
    {
        this.name = name;
        memoryPosition = statics.t.alloc();
    }

    public String getName()
    {
        return name;
    }

    public int getMemoryPosition()
    {
        return memoryPosition;
    }
}
