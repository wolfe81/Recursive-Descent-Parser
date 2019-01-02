
public class VarDeclare extends ASTStatement
{
	String st;
	String tab;
	public VarDeclare(String st)
	{
		this.st = st;
		tab = "";
		for (int i = 0; i < ASTNode.tab; i++)
		{
			tab += "    ";
		}
	}
	public String convertToJava()
	{
		return tab + "int " + st.toLowerCase() + ";\n";
	}
	public String toString()
	{
		return convertToJava();
	}
}
