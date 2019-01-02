
public class Print extends ASTStatement
{
	ASTExpression e;
	private String tab;
	public Print(ASTExpression e)
	{
		this.e = e;

		tab = "";
		for (int i = 0; i < ASTNode.tab; i++)
		{
			tab += "    ";
		}
	}

	public String convertToJava()
	{
		String st = tab + "System.out.println(" + e + ");\n";
		return st;
	}

	public String toString()
	{
		return convertToJava();
	}
}
