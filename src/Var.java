
public class Var extends SimpleExpression
{
	String st;
	public Var(String st)
	{
		this.st = st;
	}

	public String convertToJava()
	{
		return st;
	}
	public String toString()
	{
		return convertToJava();
	}
}
