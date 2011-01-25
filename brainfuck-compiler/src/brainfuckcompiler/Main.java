package brainfuckcompiler;

import brainfuckcompiler.code.BrainfuckTools;
import brainfuckcompiler.code.random.RandomNumberGenerator;
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
        statics.t = new BrainfuckTools(30000);
        statics.ops = new Operators();
        statics.gen = new RandomNumberGenerator();
        ExpressionGenerator.generateCodeForExpression("!!((2*2==4)&&(2*2!=5)&&(7/3==2)&&(8%3==2)&&(7+5==12)&&(7-5==2)&&(3<4)&&!(4<4)&&!(5<4)&&(3<=4)&&(4<=4)&&!(5<=4)&&!(3>4)&&!(4>4)&&(5>4)&&!(3>=4)&&(4>=4)&&(5>=4)&&(5==5)&&!(5==6)&&!(5!=5)&&(5!=6)&&((1==1)&&(1==1))&&!((1==1)&&(1==0))&&!((1==0)&&(1==1))&&!((1==0)&&(1==0))&&((1==1)||(1==1))&&((1==1)||(1==0))&&((1==0)||(1==1))&&!((1==0)||(1==0))&&!(!(1==1))&&(!(1==0))&&(2^7==128)&&(2^2^2==16))");
        System.out.println(statics.t.getB());
    }
}
