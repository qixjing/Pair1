import java.util.HashMap;
import java.util.Map;
//优先级算法
public class Operator implements Comparable <Operator > {
	public static final String PLUS = "+" ;
    public static final String MINUS = "-" ;
    public static final String MULTIPLE = "*" ;
    public static final String DIVIDE = "/" ;
    private  int priority ;
    protected static final Map <String , Integer> operatorPiority = new HashMap <> ();
    static
    {
          operatorPiority .put ( PLUS, 1) ;
          operatorPiority .put ( MINUS, 1) ;
          operatorPiority .put ( MULTIPLE, 2) ;
          operatorPiority .put ( DIVIDE, 2) ;
    }
    protected void isLegalOperator (String content)
    {
          if (content.equals(PLUS))
        	  priority = operatorPiority .get (PLUS);
          else if(content.equals(MINUS)) {
        	  priority = operatorPiority .get (MINUS);
          }
          else if(content.equals(MULTIPLE)) {
        	  priority = operatorPiority .get (MULTIPLE);
          }
          else if(content.equals(DIVIDE)) {
        	  priority = operatorPiority .get (DIVIDE);
          }
    }
    Operator(String content)
    {
    	isLegalOperator (content);
    }
	@Override
	public int compareTo(Operator o) {
		return this . priority - o . priority;
	}
	}


