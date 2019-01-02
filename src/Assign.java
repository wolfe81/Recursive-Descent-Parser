
public class Assign extends ASTStatement
{

	String st;
	String tab;
	public Assign(String st)
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
		st = tab + st +";\n";
		return st.toLowerCase();
	}
	public String toString()
	{
		return convertToJava();
	}

}
