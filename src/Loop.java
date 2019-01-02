
public class Loop extends ASTStatement
{
	ASTProgram ap;
	ASTCondition ac;
	String tab;
	public Loop(ASTProgram ap, ASTCondition ac)
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
		return tab + "do\n{\n" + ap + "}\nwhile(" + ac + ");\n";
	}
}
