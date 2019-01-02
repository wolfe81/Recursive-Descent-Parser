
public class Num extends SimpleExpression
{
	String st;

	public Num(int integer)
	{
		this.st = integer + "";
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
