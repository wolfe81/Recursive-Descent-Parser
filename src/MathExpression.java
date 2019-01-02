
public class MathExpression extends ASTExpression
{
	private ASTExpression next;
	private SimpleExpression se;
	char op;
	
	public MathExpression(SimpleExpression se)
	{
		this.se = se;
		op = ' ';
	}
	public String convertToJava()
	{
		String results = "";
		if(next == null)
		{
			results = se + "";
		}
		else
		{
			results = se + " " + op + " " + next;
		}
		return results.toLowerCase();
	}
	public ASTExpression getNext()
	{
		return next;
	}
	public void setNext(ASTExpression ae)
	{
		next = ae;
	}
	public String toString()
	{
		return convertToJava();
	}

}
