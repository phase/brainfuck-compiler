package brainfuckcompiler;

import brainfuckcompiler.code.BrainfuckTools;
import brainfuckcompiler.code.random.RandomNumberGenerator;
import brainfuckcompiler.compiler.expressions.operators.Operators;
import brainfuckcompiler.compiler.program.LineReader;
import brainfuckcompiler.compiler.program.structure.Block;
import brainfuckcompiler.compiler.program.structure.Item;
import brainfuckcompiler.compiler.program.structure.Line;
import java.util.ArrayList;

public class Main
{

    /**
     *
     * @param args
     */
    public static void main(String[] args)
    {
        initializeCompiler();
        //ExpressionGenerator.generateCodeForExpression("!!((2*2==4)&&(2*2!=5)&&(7/3==2)&&(8%3==2)&&(7+5==12)&&(7-5==2)&&(3<4)&&!(4<4)&&!(5<4)&&(3<=4)&&(4<=4)&&!(5<=4)&&!(3>4)&&!(4>4)&&(5>4)&&!(3>=4)&&(4>=4)&&(5>=4)&&(5==5)&&!(5==6)&&!(5!=5)&&(5!=6)&&((1==1)&&(1==1))&&!((1==1)&&(1==0))&&!((1==0)&&(1==1))&&!((1==0)&&(1==0))&&((1==1)||(1==1))&&((1==1)||(1==0))&&((1==0)||(1==1))&&!((1==0)||(1==0))&&!(!(1==1))&&(!(1==0))&&(2^7==128)&&(2^2^2==16))");
        //ExpressionGenerator.generateCodeForExpression("!!((2*2==4)&&(2*2!=5)&&(7/3==2)&&(8%3==2)&&(7+5==12)&&(7-5==2)&&(3<4)&&!(4<4)&&!(5<4)&&(3<=4)&&(4<=4)&&!(5<=4)&&!(3>4)&&!(4>4)&&(5>4)&&!(3>=4)&&(4>=4)&&(5>=4)&&(5==5)&&!(5==6)&&!(5!=5)&&(5!=6)&&((1==1)&&(1==1))&&!((1==1)&&(1==0))&&!((1==0)&&(1==1))&&!((1==0)&&(1==0))&&((1==1)||(1==1))&&((1==1)||(1==0))&&((1==0)||(1==1))&&!((1==0)||(1==0))&&!(!(1==1))&&(!(1==0))&&(2^7==pow(calc(1+ 1),_calculateSeven()))&&(xyz^2^array[2+abc]==16))");
        //System.out.println(statics.t.getB());
        Block b = createBlockFromFile(args[0]);
        b.output();
    }

    private static void initializeCompiler()
    {
        statics.t = new BrainfuckTools(30000);
        statics.ops = new Operators();
        statics.gen = new RandomNumberGenerator();
        statics.ops.createRegex();
    }

    private static Block createBlockFromFile(String path)
    {
        LineReader r = new LineReader(path);
        ArrayList<Item> l = new ArrayList<Item>();
        if (r.isOpen())
        {
            Line line;
            while ((line = r.readLine()) != null)
            {
                if (line.getIndentLevel() >= 0)
                {
                    l.add(line);
                }
            }
            r.closeFile();
        }
        return new Block(l, 0, null);
    }
}