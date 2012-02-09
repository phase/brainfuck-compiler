package brainfuckcompiler.compiler.program.structure;

import brainfuckcompiler.compiler.program.Array;
import brainfuckcompiler.compiler.program.Variable;
import java.util.ArrayList;

public class Subroutine
{

    private Block codeBlock;
    private String[] variableNames;
    private String name;

    public Subroutine(String name, String[] variableNames, Block b)
    {
        this.variableNames = variableNames;
        this.codeBlock = b;
        this.codeBlock.setParentBlock(null);
        this.name = name;
    }

    public void setExternalAccessibleVariables(ArrayList<Variable> variables, ArrayList<Array> arrays, ArrayList<Subroutine> subroutines, ArrayList<Function> functions)
    {
        ArrayList<String> variablesNotToFree = new ArrayList<String>();
        for (Variable v : variables)
        {
            boolean isExternal = true;
            for (String s : variableNames)
            {
                if (s.equals(v.getName()))
                {
                    isExternal = false;
                    break;
                }
            }
            if (isExternal)
            {
                variablesNotToFree.add(v.getName());
                codeBlock.getVariableScope().add(v);
            }
        }
        for (Array a : arrays)
        {
            boolean isExternal = true;
            for (String s : variableNames)
            {
                if (s.equals(a.getName()))
                {
                    isExternal = false;
                    break;
                }
            }
            if (isExternal)
            {
                variablesNotToFree.add(a.getName());
                codeBlock.getArrayScope().add(a);
            }
        }
        for (Subroutine s : subroutines)
        {
            codeBlock.getSubScope().add(s);
        }
        for (Function f : functions)
        {
            codeBlock.getFuncScope().add(f);
        }
        codeBlock.setVariablesNotToFree(variablesNotToFree);
    }

    public void generate(int lineNumber, int... memoryPositions)
    {
        if (memoryPositions.length != variableNames.length)
        {
            System.out.println("Invalid number of arguments at " + lineNumber);
            System.exit(0);
        }
        for (int i = 0; i < memoryPositions.length; i++)
        {
            codeBlock.getVariableScope().add(new Variable(variableNames[i], memoryPositions[i]));
        }
        codeBlock.generate();
    }

    public String getName()
    {
        return name;
    }
}
