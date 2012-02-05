package brainfuckcompiler.compiler.expressions;

public abstract class Node
{

    protected Node parentNode = null;
    public boolean returnsBoolean = false;
    protected boolean stored = false;
    protected int lineNumber;

    public Node(int lineNumber)
    {
        this.lineNumber = lineNumber;
    }

    public abstract int generateBF();

    public abstract int populate(String[] tokens, int index);

    public Node getParentNode()
    {
        return parentNode;
    }

    public void setParentNode(Node parentNode)
    {
        this.parentNode = parentNode;
    }
}