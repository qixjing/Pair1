import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
//创建一个SOP栈类
public class SopStacks {
    private LinkedList<String> list = new LinkedList<String>();
    Stack<String> s = new Stack<String>();
    
    public void push(String o){   // 入栈
     list.addLast(o);
     
    }
    
    public String pop(){    // 出栈,指从栈顶删除该元素并返回
     return list.removeLast();
    }
    
    public String peek(){    // 查看栈顶元素
     return list.getLast();
    }
    
    public boolean isEmpty(){   // 判断栈是否为空
     return list.isEmpty();
    }
    
    public int getSize(){    // 得到栈的大小
     return list.size();
    }

}
