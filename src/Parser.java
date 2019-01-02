public class Parser extends Object
{

	private Chario chario;
	private Scanner scanner;
	private Token token;

	public Parser(Chario c, Scanner s)
	{
		chario = c;
		scanner = s;
		this.token = scanner.nextToken();
	}

	public void reset()
	{
		scanner.reset();
		this.token = scanner.nextToken();
	}

	private void accept(int expected, String errorMessage)
	{
		if (this.token.code != expected)
			fatalError(errorMessage);
		this.token = scanner.nextToken();
	}

	private void fatalError(String errorMessage)
	{
		chario.putError(errorMessage);
		throw new RuntimeException("Fatal error");
	}

	public void parse()
	{
		ASTProgram ap = statement_list();
		System.out.print(ap);
	}

	/*---------------------------------------------------------------
	  statement_list ::= statement { statement }
	----------------------------------------------------------------*/
	private ASTProgram statement_list()
	{
		ASTProgram ap = new ASTProgram();
		ap.sl.add(statement());
		while (token.code == Token.DECLARE || token.code == Token.PRINT || token.code == Token.IF
				|| token.code == Token.LOOP || token.code == Token.LET)
		{
			ap.sl.add(statement());
		}
		return ap;
	}

	/*---------------------------------------------------------------
	  statement ->  var-decl
					print-statement
	          		if-statement 
		   			loop-statement
		   			assign-statement
	----------------------------------------------------------------*/
	private ASTStatement statement()
	{
		ASTStatement st;
		switch (token.code)
		{
			case Token.DECLARE:
				return st = var_decl();
			case Token.IF:
				return st = if_statement();
			case Token.PRINT:
				return st = print_statement();
			case Token.LOOP:
				return st = loop_statement();
			case Token.LET:
				return st = assign_statement();
			default:
				fatalError("error in statement");
		}
		return null;
	}

	/*---------------------------------------------------------------
	  var-decl ::= DECLARE var AS type [= expression]
	----------------------------------------------------------------*/
	private VarDeclare var_decl()
	{
		token = scanner.nextToken();
		String cd = token.string;
		accept(Token.VAR, "Var expected");
		VarDeclare vd = new VarDeclare(cd);
		accept(Token.AS, "As expected");
		accept(Token.INTEGER, "Integer expected");
		if (token.code == Token.ASSIGN)
		{
			token = scanner.nextToken();
			vd.st += " = " + expression();
		}
		return vd;
	}

	/*---------------------------------------------------------------
	  print-statement ::= PRINT expression
	----------------------------------------------------------------*/
	private Print print_statement()
	{
		Print p;
		token = scanner.nextToken();
		p = new Print(expression());
		return p;
	}

	/*---------------------------------------------------------------------------
	  if-statement ::= IF condition THEN statement_list ENDIF
	--------------------------------------1---------------------------------------*/
	private If if_statement()
	{
		token = scanner.nextToken();
		ASTCondition ac = condition();
		accept(Token.THEN, "Then expected");
		ASTNode.tab++;
		ASTProgram ap  = statement_list();
		ASTNode.tab--;
		accept(Token.ENDIF, "EndIf expected");
		If f = new If(ap, ac);
		return f;
	}

	/*-------------------------------------------------------------------
	  loop-statement ::= LOOP statement_list UNTIL condition
	---------------------------------------------------------------------*/
	private Loop loop_statement()
	{
		
		token = scanner.nextToken();
		ASTNode.tab++;
		ASTProgram ap = statement_list();
		ASTNode.tab--;
		accept(Token.UNTIL, "Until expected");
		ASTCondition ac = condition();
		Loop l = new Loop(ap,ac);
		return l;
	}

	/*---------------------------------------------------------------
	  assign-statement ::= LET var = expression
	----------------------------------------------------------------*/
	private Assign assign_statement()
	{
		Assign a;
		token = scanner.nextToken();
		String s = token.string +" = ";
		accept(Token.VAR, "Var expected");
		accept(Token.ASSIGN, "Assign expected");
		a = new Assign(s);
		a.st += expression();
		return a;
	}

	/*---------------------------------------------------------------
	  condition ::= expression relop expression
	----------------------------------------------------------------*/
	private ASTCondition condition()
	{
		ASTExpression ae1 = expression();
		String st = relop();
		ASTExpression ae2 = expression();
		ASTCondition ac = new ASTCondition(ae1, ae2, st);
		return ac;
	}

	/*-----------------------------------------------------------------------------------
	  expression ::= simple-expression | simple-expression ( + | - | * | / ) expression 
	------------------------------------------------------------------------------------*/
	private ASTExpression expression()
	{
		MathExpression me = new MathExpression(simple_expression());
		switch (token.code)
		{
			case Token.PLUS:
				token = scanner.nextToken();
				me.op = '+';
				me.setNext(expression());
				break;
			case Token.MINUS:
				token = scanner.nextToken();
				me.op = '-';
				me.setNext(expression());
				break;
			case Token.TIMES:
				token = scanner.nextToken();
				me.op = '*';
				me.setNext(expression());
				break;
			case Token.DIV:
				token = scanner.nextToken();
				me.op = '/';
				me.setNext(expression());
				break;
			case Token.MOD:
				token = scanner.nextToken();
				me.op = '%';
				me.setNext(expression());
				break;
		}
		return me;
	}

	/*---------------------------------------------------------------
	  simple-expression ::= var | number
	----------------------------------------------------------------*/
	private SimpleExpression simple_expression()
	{
		if (token.code == Token.VAR)
		{
			Var var = new Var(token.string);
			token = scanner.nextToken();
			return var;
			
		}
		else if (token.code == Token.NUMBER)
		{
			Num num = new Num(token.integer);
			token = scanner.nextToken();
			return num;
		}
		else
		{
			fatalError("Error in simple expression");
		}
		return null;
	}

	/*---------------------------------------------------------------
	  relop ::= < [>|=] | > [ = ] | = =
	----------------------------------------------------------------*/
	private String relop()
	{
		String st = token.string;
		switch (token.code)
		{
			case Token.LT:
				token = scanner.nextToken();
				return st;
			case Token.NOTEQ:
				token = scanner.nextToken();
				return st;
			case Token.LTE:
				token = scanner.nextToken();
				return st;
			case Token.GT:
				token = scanner.nextToken();
				return st;
			case Token.GTE:
				token = scanner.nextToken();
				return st;
			case Token.EQ:
				token = scanner.nextToken();
				return st;
			default:
				fatalError("Error in relop");
		}
		return null;
	}
}
