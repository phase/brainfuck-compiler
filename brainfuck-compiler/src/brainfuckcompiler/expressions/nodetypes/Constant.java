package brainfuckcompiler.expressions.nodetypes;


import brainfuckcompiler.code.BrainfuckTools;
import brainfuckcompiler.expressions.Node;

/**
 *
 * @author vrighter
 */
public class Constant extends Node
{
  int value=0;
  /**
   * 
   * @param tokens
   * @param index
   * @return
   */
  public int populate(String[]tokens,int index)
  {
    value=Integer.parseInt(tokens[index+1]);
    return index;
  }
  /**
   * 
   * @param t
   * @return
   */
  public int generateBF(BrainfuckTools t)
  {
    int address=t.alloc();
    if(value!=0){
      t.to(address);
      t.plus(value);
    }
    return address;
  }
}