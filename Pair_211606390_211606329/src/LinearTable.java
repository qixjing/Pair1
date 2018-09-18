
import java.util.ArrayList;
import java.util.List;
//创建一个线性表类
public class LinearTable {
    private List<String> list=new ArrayList<String>();
    public void append(String str) {
		list.add(str);
    }
    public void append(int i,String str) {
		list.add(i,str);
    }
    //将数据添加到表中
    public List<String> getAll(){
    	return list;
    }
    //将表中数据全部输出
    public String get(int index) {
    	return list.get(index);
    }
    //输出表中第index位的数据
    public int size() {
    	return list.size();
    }
    //线性表的大小
    public void remove() {
    	for(int i=0;i<=list.size();i++)
    		list.remove(i);
    }
    //删除线性表中的数据
}
