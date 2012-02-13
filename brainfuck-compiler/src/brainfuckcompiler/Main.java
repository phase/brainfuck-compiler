package brainfuckcompiler;

import brainfuckcompiler.code.BFStack;
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

    public static void main(String[] args)
    {
        int numberOfCells = 1;
        initializeCompiler();
        Block b = createBlockFromFile(args[0]);
        b.generateStatements();
        b.generate();
        BFStack.free();
        statics.gen.free();
        statics.t.to(0);
        BFStack.parseStackCalls();
        switch (numberOfCells)
        {
            case 1:
                output8Bit(statics.t.getB().toString());
                break;
            case 2:
                output16Bit(statics.t.getB().toString());
                break;
            case 4:
                output32Bit(statics.t.getB().toString());
                break;
            default:
                System.out.println("Invalid number of cells specified");
                break;
        }
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
        int startingLineNumber = -1;
        if (r.isOpen())
        {
            Line line;
            while ((line = r.readLine()) != null)
            {
                if (line.getIndentLevel() >= 0)
                {
                    l.add(line);
                    if (startingLineNumber == -1)
                    {
                        startingLineNumber = line.getLineNumber();
                    }
                }
            }
            r.closeFile();
        }
        return new Block(l, 0, null, startingLineNumber);
    }

    private static void output8Bit(String s)
    {
        for (int i = 0; i < s.length(); i++)
        {
            if (i > 0 && (i % 80 == 0))
            {
                System.out.println();
            }
            System.out.print(s.charAt(i));
        }
        System.out.println();
    }

    private static void output16Bit(String s)
    {
        System.out.print(">");
        for (int i = 0; i < s.length(); i++)
        {
            char c = s.charAt(i);
            switch (c)
            {
                case '[':
                    System.out.print("[>>+>>>+<<<<<-]>>>>>[<<<<<+>>>>>-]<<<[[-]<<<+>>>]<[>+>>>+<<<<-]>>>>[<<<<+>>>>-]<<<[[-]<<<+>>>]<<<[[-]>");
                    break;
                case ']':
                    System.out.print("[>>+>>>+<<<<<-]>>>>>[<<<<<+>>>>>-]<<<[[-]<<<+>>>]<[>+>>>+<<<<-]>>>>[<<<<+>>>>-]<<<[[-]<<<+>>>]<<<]>");
                    break;
                case '+':
                    System.out.print("+[<+>>>+<<-]<[>+<-]+>>>[<<<->>>[-]]<<<[->>+<<]>");
                    break;
                case '-':
                    System.out.print("[<+>>>+<<-]<[>+<-]+>>>[<<<->>>[-]]<<<[->>-<<]>-");
                    break;
                case '<':
                    System.out.print("<<<");
                    break;
                case '>':
                    System.out.print(">>>");
                    break;
                default:
                    System.out.print(c);
                    break;
            }
        }
        System.out.println();
    }

    private static void output32Bit(String s)
    {
        System.out.print(">");
        for (int i = 0; i < s.length(); i++)
        {
            char c = s.charAt(i);
            switch (c)
            {
                case '[':
                    System.out.print("[>>>>+>>>>>+<<<<<<<<<-]>>>>>>>>>[<<<<<<<<<+>>>>>>>>>-]<<<<<[[-]<<<<<+>>>>>]<<<[>>>+>>>>>+<<<<<<<<-]>>>>>>>>[<<<<<<<<+>>>>>>>>-]<<<<<[[-]<<<<<+>>>>>]<<[>>+>>>>>+<<<<<<<-]>>>>>>>[<<<<<<<+>>>>>>>-]<<<<<[[-]<<<<<+>>>>>]<[>+>>>>>+<<<<<<-]>>>>>>[<<<<<<+>>>>>>-]<<<<<[[-]<<<<<+>>>>>]<<<<<[[-]>");
                    break;
                case ']':
                    System.out.print("[>>>>+>>>>>+<<<<<<<<<-]>>>>>>>>>[<<<<<<<<<+>>>>>>>>>-]<<<<<[[-]<<<<<+>>>>>]<<<[>>>+>>>>>+<<<<<<<<-]>>>>>>>>[<<<<<<<<+>>>>>>>>-]<<<<<[[-]<<<<<+>>>>>]<<[>>+>>>>>+<<<<<<<-]>>>>>>>[<<<<<<<+>>>>>>>-]<<<<<[[-]<<<<<+>>>>>]<[>+>>>>>+<<<<<<-]>>>>>>[<<<<<<+>>>>>>-]<<<<<[[-]<<<<<+>>>>>]<<<<<]>");
                    break;
                case '+':
                    System.out.print("+[<+>>>>>+<<<<-]<[>+<-]+>>>>>[<<<<<->>>>>[-]]<<<<<[->>+[<<+>>>>>+<<<-]<<[>>+<<-]+>>>>>[<<<<<->>>>>[-]]<<<<<[->>>+[<<<+>>>>>+<<-]<<<[>>>+<<<-]+>>>>>[<<<<<->>>>>[-]]<<<<<[->>>>+<<<<]]]>");
                    break;
                case '-':
                    System.out.print("[<+>>>>>+<<<<-]<[>+<-]+>>>>>[<<<<<->>>>>[-]]<<<<<[->>[<<+>>>>>+<<<-]<<[>>+<<-]+>>>>>[<<<<<->>>>>[-]]<<<<<[->>>[<<<+>>>>>+<<-]<<<[>>>+<<<-]+>>>>>[<<<<<->>>>>[-]]<<<<<[->>>>-<<<<]>>>-<<<]>>-<<]>-");
                    break;
                case '<':
                    System.out.print("<<<<<");
                    break;
                case '>':
                    System.out.print(">>>>>");
                    break;
                default:
                    System.out.print(c);
                    break;
            }
        }
        System.out.println();
    }
}