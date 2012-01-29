package brainfuckcompiler;

import brainfuckcompiler.code.BrainfuckTools;
import brainfuckcompiler.code.random.RandomNumberGenerator;
import brainfuckcompiler.compiler.Array;
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
        statics.ops.createRegex();
        Array a = new Array(5);
        int[]p=statics.t.alloc(5);
        statics.t.plus(p[0], 1);
        statics.t.plus(p[1], 2);
        statics.t.plus(p[2], 3);
        statics.t.plus(p[3], 4);
        statics.t.plus(p[4], 5);
        a.set(p[4], p[4]);
        statics.t.append("#\n");
        a.set(p[3], p[3]);
        statics.t.append("#\n");
        a.set(p[2], p[2]);
        statics.t.append("#\n");
        a.set(p[1], p[1]);
        statics.t.append("#\n");
        a.set(p[0], p[0]);
        statics.t.append("#\n");
        int testpos=statics.t.alloc();
        a.get(p[0], testpos);
        statics.t.plus(testpos, 48);
        statics.t.append(".");
        statics.t.clear(testpos);
        statics.t.append("#\n");
        a.get(p[1], testpos);
        statics.t.plus(testpos, 48);
        statics.t.append(".");
        statics.t.clear(testpos);
        statics.t.append("#\n");
        a.get(p[2], testpos);
        statics.t.plus(testpos, 48);
        statics.t.append(".");
        statics.t.clear(testpos);
        statics.t.append("#\n");
        a.get(p[3], testpos);
        statics.t.plus(testpos, 48);
        statics.t.append(".");
        statics.t.clear(testpos);
        statics.t.append("#\n");
        a.get(p[4], testpos);
        statics.t.plus(testpos, 48);
        statics.t.append(".");
        statics.t.clear(testpos);
        statics.t.append("#\n");
        for(int i:p)statics.t.clear(i);
        statics.t.free(p);
        statics.t.free(testpos);
        a.free();
        ExpressionGenerator.generateCodeForExpression("!!((2*2==4)&&(2*2!=5)&&(7/3==2)&&(8%3==2)&&(7+5==12)&&(7-5==2)&&(3<4)&&!(4<4)&&!(5<4)&&(3<=4)&&(4<=4)&&!(5<=4)&&!(3>4)&&!(4>4)&&(5>4)&&!(3>=4)&&(4>=4)&&(5>=4)&&(5==5)&&!(5==6)&&!(5!=5)&&(5!=6)&&((1==1)&&(1==1))&&!((1==1)&&(1==0))&&!((1==0)&&(1==1))&&!((1==0)&&(1==0))&&((1==1)||(1==1))&&((1==1)||(1==0))&&((1==0)||(1==1))&&!((1==0)||(1==0))&&!(!(1==1))&&(!(1==0))&&(2^7==128)&&(2^2^2==16))");
        System.out.println(statics.t.getB());
    }
}