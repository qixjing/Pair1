import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class File {
	
	public void Write(String FileName, String str) {
			PrintWriter writer;
			try {
				writer = new PrintWriter(FileName, "UTF-8");
				writer.println(str);
				writer.close();
			} catch (FileNotFoundException e) {
				// �ļ�δ�ҵ�
				System.out.print("Error:File Not Found");
			} catch (UnsupportedEncodingException e) {
				// ��֧�ֵ��ļ�����
				System.out.print("Error:Unsupported Encoding");
			}
	}
	
}
