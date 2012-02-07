package brainfuckcompiler.compiler.program;

import brainfuckcompiler.statics;

public class Array
{

    private int mempos, size;
    private String name;

    public Array(String name, int s)
    {
        size = s * 2 + 5;
        mempos = statics.t.allocContiguous(size);
        this.name = name;
    }

    public String getName()
    {
        return name;
    }

    public void free()
    {
        int amt = (size - 5) / 2;
        for (int j = 0; j < amt; j++)
        {
            statics.t.clear(mempos + 5 + (2 * j));
        }
        statics.t.freeContiguous(mempos, size);
    }

    /**
     * Inserts a value into an array. Preserves the values of both pos and val
     *
     * @param pos the array position to insert to
     * @param val the value to insert
     */
    public void set(int pos, int val)
    {
        statics.t.copy(mempos + 2, pos, mempos + 3);
        statics.t.copy(mempos + 1, val, mempos + 3);
        statics.t.to(mempos);
        statics.t.append(">>[[>>]+[<<]>>-]+[>>]<[-]<[<<]>[>[>>]<+<[<<]>-]>[>>]<<[-<<]");
    }

    /**
     * Gets a value from the array. Assumes the destination cell is 0, preserves
     * the value of pos
     *
     * @param pos The position to extract
     * @param dest The destination cell
     */
    public void get(int pos, int dest)
    {
        statics.t.copy(mempos + 2, pos, mempos + 1);
        statics.t.to(mempos);
        statics.t.append(">>[[>>]+[<<]>>-]+[>>]<[<[<<]>+<");
        statics.t.inc(dest);
        statics.t.to(mempos);
        statics.t.append(">>[>>]<-]<[<<]>[>[>>]<+<[<<]>-]>[>>]<<[-<<]");
    }
}
