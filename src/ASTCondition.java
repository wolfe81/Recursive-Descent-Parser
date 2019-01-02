
public class ASTCondition extends ASTNode
{
	ASTExpression ae1;
	ASTExpression ae2;
	String st;
	public ASTCondition(ASTExpression ae1, ASTExpression ae2, String st)
	{
		this.ae1 = ae1;
		this.ae2 = ae2;
		this.st = st;
	}

	public String convertToJava()
	{
		return ae1 + " " + st + " " + ae2;
	}
	public String toString()
	{
		return convertToJava();
	}
}
