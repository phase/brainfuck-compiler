package brainfuckcompiler;

import brainfuckcompiler.code.BrainfuckTools;
import brainfuckcompiler.expressions.ExpressionGenerator;
import brainfuckcompiler.expressions.operators.Operators;

/**
 *
 * @author vrighter
 */
public class Main
{

    /**
     *
     * @param args
     */
    public static void main(String[] args)
    {
        BrainfuckTools t = new BrainfuckTools(30000);
        Operators ops = new Operators();
        ExpressionGenerator.generateCodeForExpression("!!((2*2==4)&&(2*2!=5)&&(7/3==2)&&(8%3==2)&&(7+5==12)&&(7-5==2)&&(3<4)&&!(4<4)&&!(5<4)&&(3<=4)&&(4<=4)&&!(5<=4)&&!(3>4)&&!(4>4)&&(5>4)&&!(3>=4)&&(4>=4)&&(5>=4)&&(5==5)&&!(5==6)&&!(5!=5)&&(5!=6)&&((1==1)&&(1==1))&&!((1==1)&&(1==0))&&!((1==0)&&(1==1))&&!((1==0)&&(1==0))&&((1==1)||(1==1))&&((1==1)||(1==0))&&((1==0)||(1==1))&&!((1==0)||(1==0))&&!(!(1==1))&&(!(1==0))&&(2^7==128)&&(2^2^2==16))", ops, t);
        System.out.println(t.getB());
    }
}