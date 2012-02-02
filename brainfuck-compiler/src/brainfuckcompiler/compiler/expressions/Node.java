package brainfuckcompiler.compiler.expressions;

/**
 *
 * @author vrighter
 */
public abstract class Node
{
    /**
     *
     */
    public boolean returnsBoolean=false;
  /**
   *
   * @param t
   * @return
   */
  public abstract int generateBF();
  /**
   *
   * @param tokens
   * @param index
   * @return
   */
  public abstract int populate(String[]tokens,int index);
}
