
public class If extends ASTStatement
{
	ASTProgram ap;
	ASTCondition ac;
	String tab;
	public If(ASTProgram ap, ASTCondition ac)
	{
		this.ap = ap;
		this.ac = ac;
		tab = "";
		for (int i = 0; i < ASTNode.tab; i++)
		{
			tab += "    ";
		}
	}

	public String toString()
	{
		return convertToJava();
	}
	
	public String convertToJava()
	{
		return tab + "if(" + ac + ")\n" + tab + "{\n" + ap + tab +"}\n";
	}
}
