package transforPDF2;

import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.StreamOpenOfficeDocumentConverter;

import java.io.File;


public class TOPDFUtil {
	/**
	 * @Description: ת��PDF
	 * @author mazc
	 * @param sourceFile ת���ļ�·��
	 * @param destFile   �����ļ�·��
	 * @return 
	 */
	public  boolean openOfficeToPDF(String sourceFile, String destFile) {
		return openOfficeToPDF(new File(sourceFile),new File(destFile));
	}
	
	/**
	 * @Description:  ת��PDF
	 * @author mazc
	 * @param inputFile 转换文件
	 * @param outputFile 目标存储文件
	 */
	public  boolean openOfficeToPDF(File inputFile, File outputFile) {
		OpenOfficeConnection connection = null;
 		try {
 			if (!inputFile.exists()) {
				return false;
			}
  			if (!outputFile.getParentFile().exists()) {
 				outputFile.getParentFile().mkdirs();
			}
			connection = new SocketOpenOfficeConnection("39.97.30.133", Integer.valueOf("8100"));
			connection.connect();
			DocumentConverter converter = new StreamOpenOfficeDocumentConverter(connection);
			converter.convert(inputFile, outputFile);
			return true;
		} catch (Exception e) {
			System.out.println("openOfficeToPDF 转换异常"+e.getMessage());
			e.printStackTrace();
 			return false;
		} finally {
			if(null != connection) {
				connection.disconnect();
			}
		}
	}
}
