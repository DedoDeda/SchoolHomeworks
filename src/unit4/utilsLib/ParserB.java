package unit4.utilsLib;

import java.text.CharacterIterator;
import java.text.StringCharacterIterator;

import unit4.collectionsLib.BinNode;


class ParserB
{
    ParserB()
    {
    }

    private static int operatorPos(String expression, char operator)
    {
        int insideParens = 0;
        CharacterIterator it = new StringCharacterIterator(expression);
        for(char token = it.first(); token != '\uFFFF'; token = it.next())
        {
            if(token == '(')
            {
                insideParens++;
            } else
            if(token == ')')
            {
                insideParens--;
            } else
            if(token == operator && insideParens == 0)
            {
                return it.getIndex();
            }
        }

        return -1;
    }

    private static String convOps(String expr)
    {
        StringBuffer newExp = new StringBuffer(expr);
        for(int i = 0; i < newExp.length(); i++)
        {
            if(expr.charAt(i) == '-' && i > 0 && Character.isDigit(expr.charAt(i - 1)))
            {
                newExp.setCharAt(i, '-');
            }
        }

        return newExp.toString();
    }

    private static String trimParens(String expr)
    {
        int balance = 1;
        for(int i = 1; i < expr.length() - 1; i++)
        {
            if(expr.charAt(i) == '(')
                balance++;
            else
             if(expr.charAt(i) == ')')
              balance--;
            
            if(balance == 0)
                return expr;
        }

        return expr.substring(1, expr.length() - 1);
    }

    private static BinNode<String> doParse(String expr)
    {
        char ops[] = {'+', '-', '*', '/'};
        
        if(expr.length() <= 0)
            throw new RuntimeException("Expression error!");
        
        if(expr.charAt(0) == '(' && expr.charAt(expr.length() - 1) == ')')
            expr = trimParens(expr);
       
        for(int i = 0; i < ops.length; i++)
        {
            int opPos = operatorPos(expr, ops[i]);
            if(opPos != -1)
                return new BinNode<String>(doParse(expr.substring(0, opPos).trim()), (new Character(expr.charAt(opPos))).toString(), doParse(expr.substring(opPos + 1).trim()));
        }

        Integer num = new Integer(Integer.parseInt(expr));
        return new BinNode<String>(num.toString());
    }

    public static BinNode<String> parse(String expression)
    {
        return doParse(convOps(expression));
    }
}
