
public class Node {
	double val;
	char   opt;
	
	
	Node(double val, char opt)
	{
		this.val = val;
		this.opt = opt;
	}
	
	Node(double val)
	{
		this.val = val;
		this.opt = ' ';
	}
	
	Node(char opt)
	{
		this.val = 0;
		this.opt = opt;
	}
	
	Node()
	{
		this.val = 0;
		this.opt = ' ';
	}
}
