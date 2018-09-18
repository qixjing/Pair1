import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
//创建一个SOP栈类
public class SopStacks {
    private LinkedList<String> list = new LinkedList<String>();
    public void push(String o){   // 入栈
     list.addFirst(o);
    }
    
    public String pop(){    // 出栈,指从栈顶删除该元素并返回
     return list.removeFirst();
    }
    
    public String peek(){    // 查看栈顶元素
     return list.getFirst();
    }
    
    public boolean isEmpty(){   // 判断栈是否为空
     return list.isEmpty();
    }
    
    public int getSize(){    // 得到栈的大小
     return list.size();
    }

}
