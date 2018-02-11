


	/**
	 * @param args
	 */
	import java.util.Stack;
	 
	public class EvaluateString1
	{
	    public static String evaluate(String expression)
	    {
	        char[] tokens = expression.toCharArray();
	 
	         // Stack for numbers: 'values'
	        Stack<String> values = new Stack<String>();
	 
	        // Stack for Operators: 'ops'
	        Stack<Character> ops = new Stack<Character>();
	 
	        for (int i = 0; i < tokens.length; i++)
	        {
	             // Current token is a whitespace, skip it
	            if (tokens[i] == ' ')
	                continue;
	 
	            // Current token is a number, push it to stack for numbers
	            if (tokens[i] >= '0' && tokens[i] <= '9')
	            {
	                StringBuffer sbuf = new StringBuffer();
	                // There may be more than one digits in number
	                while (i < tokens.length && tokens[i] >= '0' && tokens[i] <= '9')
	                	sbuf.append(tokens[i++]);
	                values.push(sbuf.toString());
	            }
	 
	            // Current token is an opening brace, push it to 'ops'
	            else if (tokens[i] == '(')
	                ops.push(tokens[i]);
	 
	            // Closing brace encountered, solve entire brace
	            else if (tokens[i] == ')')
	            {
	                while (ops.peek() != '(')
	                  values.push(applyOp(ops.pop(), values.pop(), values.pop()));
	                ops.pop();
	            }
	 
	            // Current token is an operator.
	            else if (tokens[i] == '+' || tokens[i] == '-' ||
	                     tokens[i] == '*' || tokens[i] == '/')
	            {
	                // While top of 'ops' has same or greater precedence to current
	                // token, which is an operator. Apply operator on top of 'ops'
	                // to top two elements in values stack
	                while (!ops.empty() && hasPrecedence(tokens[i], ops.peek()))
	                  values.push(applyOp(ops.pop(), values.pop(), values.pop()));
	 
	                // Push current token to 'ops'.
	                ops.push(tokens[i]);
	            }
	        }
	 
	        // Entire expression has been parsed at this point, apply remaining
	        // ops to remaining values
	        while (!ops.empty())
	            values.push(applyOp(ops.pop(), values.pop(), values.pop()));
	 
	        // Top of 'values' contains result, return it
	        return values.pop();
	    }
	 
	    // Returns true if 'op2' has higher or same precedence as 'op1',
	    // otherwise returns false.
	    public static boolean hasPrecedence(char op1, char op2)
	    {
	        if (op2 == '(' || op2 == ')')
	            return false;
	        if ((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-'))
	            return false;
	        else
	            return true;
	    }
	 
	    // A utility method to apply an operator 'op' on operands 'a' 
	    // and 'b'. Return the result.
	    public static String applyOp(char op, String b, String a)
	    {
	        switch (op)
	        {
	        case '+':
	            return a.concat(b);
	        case '-':
	            return b.concat(a);
	        case '*':
	            return a.concat(b).concat("0");
	        case '/':	            
	        	return b.concat("0").concat(a);
	        }
	        return " ";
	    }
	 
	    // Driver method to test above methods
	    public static void main(String[] args)
	    {
	        System.out.println(EvaluateString1.evaluate("10 + 12 * 6"));
	        System.out.println(EvaluateString1.evaluate("((4+32)*(3+566))"));
	        System.out.println(EvaluateString1.evaluate("((4-32)/(3+566))"));
	        System.out.println(EvaluateString1.evaluate("14+32*3/566-2"));
	    }
	}


//*[@id="wxDetail"]/article/table[1]/tbody/tr[4]/td[3]/a