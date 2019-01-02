import java.util.ArrayList;

public class ASTProgram extends ASTNode
{
	
	ArrayList<ASTStatement> sl;
	public ASTProgram()
	{
		sl = new ArrayList<ASTStatement>();
	}
	
	public String convertToJava()
	{
		String results = "";
		for(ASTStatement s: sl)
		{
			results+= s;
		}
		return results;
	}
	
	public String toString()
	{
		return convertToJava();
	}
	
}
