package transforPDF2;

import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.ComThread;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;
import org.apache.pdfbox.util.PDFMergerUtility;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class TransferUtils {

	private static final int wdFormatPDF = 17;
	private static final int xlTypePDF = 0;
	private static final int ppSaveAsPDF = 32;
	private static final int msoTrue = -1;
	private static final int msofalse = 0;

	/**
	 * <p>
	 * Title: convert2PDF
	 * </p>
	 * <p>
	 * Description: �ڵ�ǰĿ¼��ת��Ϊpdf
	 * </p>
	 * 
	 * @param inputFile
	 * @return
	 */
	public static File convert2PDF(String inputFile) {
		return convert2PDF(inputFile, null);
	}

	public static File convert2PDF(String inputFile, String pdfFile) {
		String suffix = getFileSufix(inputFile);
		File file = new File(inputFile);
		if (!file.exists()) {
			System.out.println("文件不存在！");
			return null;
		}
		if (suffix.equals("pdf")) {
			System.out.println("PDF not need to convert!");
			return null;
		}
		File pdf = null;
		if (null == pdfFile || "".equals(pdfFile)) {
			pdf = new File(file.getParentFile().getAbsolutePath() + File.separator
					+ file.getName().substring(0, file.getName().lastIndexOf(".")) + ".pdf");
		} else {
			pdf = new File(pdfFile);
			if (pdf.isDirectory()) {
				if (!pdf.exists()) {
					pdf.mkdirs();
				}
				pdf = new File(pdf.getAbsolutePath() + File.separator
						+ file.getName().substring(0, file.getName().lastIndexOf(".")) + ".pdf");
			} else {
				if (!pdf.getParentFile().exists()) {
					pdf.getParentFile().mkdirs();
				}
			}
			if (pdf.exists()) {
				pdf.delete();
			}
		}
		boolean transFlag = false;
		if (suffix.equals("doc") || suffix.equals("docx") || suffix.equals("txt")) {
			transFlag = word2PDFAsFixedFormat(inputFile, pdf.getAbsolutePath());
			if (!transFlag) {
				transFlag = wordSaveAsPdf(inputFile, pdf.getAbsolutePath());
			}
			if (!transFlag) {
				// 调用远程openoffice
				TOPDFUtil util = new TOPDFUtil();
				transFlag = util.openOfficeToPDF(file, pdf);
			}
			/* 暂时注释（避免版权问题） */
//	        	if(!transFlag) {
//	        		ObjectToPDFUtils.wordToPDFUtils(inputFile,pdf.getAbsolutePath());
//	        	}
		} else if (suffix.equals("ppt") || suffix.equals("pptx")) {
			transFlag = ppt2PDF(inputFile, pdf.getAbsolutePath());
			if (!transFlag) {
				// 调用远程openoffice
				TOPDFUtil util = new TOPDFUtil();
				transFlag = util.openOfficeToPDF(file, pdf);
			}
		} else if (suffix.equals("xls") || suffix.equals("xlsx")) {
			transFlag = excel2PDFAsFixedFormat(inputFile, pdf.getAbsolutePath());
			if (!transFlag) {
				transFlag = excelSaveAsPDF(inputFile, pdf.getAbsolutePath());
				System.out.println("文件格式不支持转换!");
			}
			if (!transFlag) {
				// 调用远程openoffice
				TOPDFUtil util = new TOPDFUtil();
				transFlag = util.openOfficeToPDF(file, pdf);
			}
		} else {
			System.out.println("文件格式不支持转换!");
			return null;
		}
		return transFlag ? pdf : null;
	}

	/**
	 * @Description: wrod to pdf
	 * @author mazc
	 * @param inputFile
	 * @param pdfFile
	 * @return
	 */
	public static boolean word2PDFAsFixedFormat(String inputFile, String pdfFile) {
		boolean changeFlag = false;
		Dispatch doc = null;
		ActiveXComponent app = null;
		try {
			//ComThread.InitSTA();
			// 打开word应用程序
			app = new ActiveXComponent("Word.Application");
			// 设置word不可见
			app.setProperty("Visible", false);
			// 获得word中所有打开的文档,返回Documents对象
			Dispatch docs = app.getProperty("Documents").toDispatch();
			// 调用Documents对象中Open方法打开文档，并返回打开的文档对象Document
			Object[] obj = new Object[] { inputFile, new Variant(false), new Variant(true), // �Ƿ�ֻ��
					new Variant(false), new Variant("pwd") };
			doc = Dispatch.invoke(docs, "Open", Dispatch.Method, obj, new int[1]).toDispatch();
//          Dispatch.put(doc, "Compatibility", false);  //兼容性检查,为特定值false不正确
			Dispatch.put(doc, "", new Object() );
			Dispatch.put(doc, "RemovePersonalInformation",false);
			Dispatch.call(doc, "ExportAsFixedFormat", pdfFile, wdFormatPDF); // word保存为pdf格式宏，值为17
			changeFlag = true;
		} catch (Exception e) {
			changeFlag = false;
			System.out.println("========Error:word2PDFAsFixedFormat fail:" + e.getMessage());
		} finally {
			if (!changeFlag) {
				System.out.println(inputFile + "转PDF（ExportAsFixedFormat）失败！");
			}
			if (doc != null) {
				// 关闭文档
				Dispatch.call(doc, "Close", false);
			}
			if (app != null) {
				// 关闭word应用程序
				app.invoke("Quit", 0);
			}
			//ComThread.Release();
		}
		return changeFlag;
	}

	/**
	 * @Description: wrod to pdf
	 * @author guoyr
	 * @param inputFile
	 * @param pdfFile
	 * @return
	 */
	public static boolean wordSaveAsPdf(String inputFile, String pdfFile) {
		boolean changeFlag = false;
		Dispatch doc = null;
		ActiveXComponent app = null;
		try {
			ComThread.InitSTA();
			// 打开word应用程序
			app = new ActiveXComponent("Word.Application");
			// 设置word不可见
			app.setProperty("Visible", false);
			// 获得word中所有打开的文档,返回Documents对象
			Dispatch docs = app.getProperty("Documents").toDispatch();
			doc = Dispatch.call(docs, "Open", inputFile).toDispatch();
			Dispatch.call(doc, "SaveAs", pdfFile, // FileName
					wdFormatPDF); // word保存为pdf格式宏，值为17
			changeFlag = true;
		} catch (Exception e) {
			System.out.println("========Error:wordSaveAsPdf fail:" + e.getMessage());
			changeFlag = false;
		} finally {
			if (!changeFlag) {
				System.out.println(inputFile + "转PDF（wordSaveAsPdf）失败！");
			}
			if (doc != null) {
				// 关闭文档
				Dispatch.call(doc, "Close", false);
			}
			if (app != null) {
				// 关闭word应用程序
				app.invoke("Quit", 0);
			}
			ComThread.Release();
		}
		return changeFlag;
	}

	private static boolean els2pdf(String els, String pdf) {
		System.out.println("Starting excel...");
		long start = System.currentTimeMillis();
		ComThread.InitSTA();
		ActiveXComponent app = new ActiveXComponent("Excel.Application");
		try {
			app.setProperty("Visible", false);
			Dispatch workbooks = app.getProperty("Workbooks").toDispatch();
			System.out.println("opening document:" + els);
			Dispatch workbook = Dispatch.invoke(workbooks, "Open", Dispatch.Method,
					new Object[] { els, new Variant(false), new Variant(false) }, new int[3]).toDispatch();
			Dispatch.invoke(workbook, "SaveAs", Dispatch.Method,
					new Object[] { pdf, new Variant(57), new Variant(false), new Variant(57), new Variant(57),
							new Variant(false), new Variant(true), new Variant(57), new Variant(true),
							new Variant(true), new Variant(true) },
					new int[1]);
			Variant f = new Variant(false);
			System.out.println("to pdf " + pdf);
			Dispatch.call(workbook, "Close", f);
			long end = System.currentTimeMillis();
			System.out.println("completed..used:" + (end - start) / 1000 + " s");
		} catch (Exception e) {
			System.out.println("========Error:Operation fail:" + e.getMessage());
			return false;
		} finally {
			if (app != null) {
				app.invoke("Quit", new Variant[] {});
			}
			ComThread.Release();
		}
		return true;
	}

	/**
	 * @Description:
	 * @author guoyr
	 * @param inputFile
	 * @param pdfFile
	 * @return
	 */
	public static boolean excel2PDFAsFixedFormat(String inputFile, String pdfFile) {
		boolean changeFlag = false;
		Dispatch excel = null;
		ActiveXComponent app = null;
		try {
			ComThread.InitSTA();
			app = new ActiveXComponent("Excel.Application");
			app.setProperty("Visible", false);
			app.setProperty("AutomationSecurity", new Variant(3)); // 禁用宏
			Dispatch excels = app.getProperty("Workbooks").toDispatch();

			Object[] obj = new Object[] { inputFile, new Variant(false), new Variant(true) };
			excel = Dispatch.invoke(excels, "Open", Dispatch.Method, obj, new int[9]).toDispatch();
			// 将excel表格 设置成A4的大小
//	            Dispatch sheets = Dispatch.call(com.gx.obe.util.word, "Worksheets").toDispatch();  
//	            Dispatch sheet = Dispatch.call(sheets, "Item", new Integer(1)).toDispatch();  
//	            Dispatch pageSetup = Dispatch.call(sheet, "PageSetup").toDispatch();
//	            Dispatch.put(pageSetup, "PaperSize", 9);;//A3是8，A4是9，A5是11等等

			// 转换格式
			Object[] obj2 = new Object[] { new Variant(xlTypePDF), // PDF格式=0
					pdfFile, new Variant(0) // 0=标准 (生成的PDF图片不会变模糊) ; 1=最小文件
			};
			Dispatch.invoke(excel, "ExportAsFixedFormat", Dispatch.Method, obj2, new int[1]);
			changeFlag = true;
		} catch (Exception e) {
			System.out.println("========Error:excel2PDF fail:" + e.getMessage());
			changeFlag = false;
		} finally {
			if (excel != null) {
				// 关闭文档
				Dispatch.call(excel, "Close", false);
			}
			if (app != null) {
				// 关闭word应用程序
				app.invoke("Quit");
			}
			ComThread.Release();
		}
		return changeFlag;
	}

	public static boolean excelSaveAsPDF(String inputFile, String pdfFile) {
		boolean changeFlag = false;
		Dispatch excel = null;
		ActiveXComponent app = null;
		List<File> tempFileList = null;
		try {
			ComThread.InitSTA();
			app = new ActiveXComponent("Excel.Application");
			app.setProperty("Visible", false);
			app.setProperty("AutomationSecurity", new Variant(3)); // 禁用宏
			Dispatch excels = app.getProperty("Workbooks").toDispatch();
//	    		com.gx.obe.util.word = Dispatch.call(excels,
//	    				"Open",
//	    				inputFile,
//	    				true,
//	    				true
//	    				).toDispatch();
//	    		com.gx.obe.util.word = Dispatch.call(excels,  "Open" , inputFile).toDispatch(); 
			Object[] obj = new Object[] { inputFile, new Variant(false), new Variant(true) };
			excel = Dispatch.invoke(excels, "Open", Dispatch.Method, obj, new int[9]).toDispatch();
//	    		com.gx.obe.util.word = Dispatch.call(excels,// 执行命令的对象
//						"Open", // 要执行的命令
//						inputFile,// 要打开的文件
//						false,// ConfirmConversions
//						true // ReadOnly
//						).toDispatch();

//	    		 Dispatch currentSheet = Dispatch.get((Dispatch) com.gx.obe.util.word,
//	                     "ActiveSheet").toDispatch();
//	             Dispatch pageSetup = Dispatch.get(currentSheet, "PageSetup")
//	                     .toDispatch();
//	             Dispatch.put(pageSetup, "Orientation", new Variant(2));
//	             Dispatch.put(pageSetup, "Zoom", false); // 值为100或false
//	             Dispatch.put(pageSetup, "FitToPagesWide", 1); // 所有列为一页(1或false)

			Dispatch sheets = Dispatch.get((Dispatch) excel, "Worksheets").toDispatch();
			int count = Dispatch.get((Dispatch) sheets, "Count").getInt();

			if (count > 1) {
				PDFMergerUtility mergePdf = new PDFMergerUtility();
				tempFileList = new ArrayList<File>();
				for (int i = 1; i <= count; i++) {

					Dispatch sheet = Dispatch
							.invoke(sheets, "Item", Dispatch.Get, new Object[] { new Integer(i) }, new int[i])
							.toDispatch();
					// String sheetname = Dispatch.get(sheet, "name").toString();
					Dispatch.call(sheet, "Activate");// 指定活动sheet
					Dispatch.call(sheet, "Select");

					int excelToPdf = 57;
					File tempFile = new File(pdfFile.substring(0, pdfFile.lastIndexOf(".")) + "_" + i
							+ pdfFile.substring(pdfFile.lastIndexOf(".")));
					if (tempFile.exists()) {
						tempFile.delete();
					}
					Dispatch.invoke(excel, "SaveAs", Dispatch.Method,
							new Object[] { tempFile.getAbsolutePath(), new Variant(excelToPdf), new Variant(true),
									new Variant(excelToPdf), new Variant(excelToPdf), new Variant(true),
									new Variant(true), new Variant(excelToPdf), new Variant(true), new Variant(true),
									new Variant(true) },
							new int[1]);
					if (tempFile.exists()) {
						mergePdf.addSource(tempFile);
						tempFileList.add(tempFile);
					} else {
						return false;
					}

				}
				mergePdf.setDestinationFileName(pdfFile);
				mergePdf.mergeDocuments();
				changeFlag = true;
			} else {

				int excelToPdf = 57;
				Dispatch.invoke(excel, "SaveAs", Dispatch.Method,
						new Object[] { pdfFile, new Variant(excelToPdf), new Variant(true), new Variant(excelToPdf),
								new Variant(excelToPdf), new Variant(true), new Variant(true), new Variant(excelToPdf),
								new Variant(true), new Variant(true), new Variant(true) },
						new int[1]);
			}

			changeFlag = true;
		} catch (Exception e) {
			System.out.println("========Error:excel2PDF fail:" + e.getMessage());
			changeFlag = false;
		} finally {
			if (excel != null) {
				// 关闭文档
				Dispatch.call(excel, "Close", false);
			}
			if (app != null) {
				// 关闭word应用程序
				app.invoke("Quit");
			}
			ComThread.Release();
			if (null != tempFileList) {
				for (File file : tempFileList) {
					file.delete();
				}
			}
		}
		return changeFlag;
	}

	/**
	 * @Description:
	 * @author guoyr
	 * @param inputFile
	 * @param pdfFile
	 * @return
	 */
	public static boolean ppt2PDF(String inputFile, String pdfFile) {
		boolean changeFlag = false;
		ActiveXComponent app = null;
		Dispatch ppt = null;
		try {
			app = new ActiveXComponent("PowerPoint.Application");
			// app.setProperty("Visible", msofalse);
			Dispatch ppts = app.getProperty("Presentations").toDispatch();

			ppt = Dispatch.call(ppts, "Open", inputFile, true, // ReadOnly
					true, // Untitled指定文件是否有标题
					false// WithWindow指定文件是否可见
			).toDispatch();

			Dispatch.call(ppt, "SaveAs", pdfFile, ppSaveAsPDF);
			changeFlag = true;
		} catch (Exception e) {
			changeFlag = false;
		} finally {
			if (ppt != null) {
				// 关闭文档
				Dispatch.call(ppt, "Close");
			}
			if (app != null) {
				// 关闭word应用程序
				app.invoke("Quit");
			}
			ComThread.Release();
		}
		return changeFlag;
	}

	public static String getFileSufix(String fileName) {
		int splitIndex = fileName.lastIndexOf(".");
		return fileName.substring(splitIndex + 1);
	}

	public static void main(String[] args) {
		//word2PDFAsFixedFormat("D:\\贾俊.docx", "D:\\贾俊.pdf");
		wordSaveAsPdf("D:\\贾俊.docx", "D:\\贾俊.pdf");
//		excel2PDFAsFixedFormat("E:\\test\\���۳���ģʽ��.xlsx", "E:\\test\\���۳���ģʽ��1.pdf");
		//excelSaveAsPDF("E:\\test\\���۳���ģʽ��.xlsx", "E:\\test\\���۳���ģʽ��2.pdf");
		//els2pdf("E:\\test\\���۳���ģʽ��.xlsx", "E:\\test\\���۳���ģʽ��3.pdf");
//		excelSaveAsPDF("E:\\test\\���۳���ģʽ��.xlsx", "E:\\test\\���۳���ģʽ��2.pdf");

//		TransferUtil tool = new TransferUtil();
//		tool.convert2PDF("C:\\template\\out\\b0a7a2a6d0b442bcbea7284d91d8c1e1\\���ֻ��ܱ�(Ͷ���˺�ר��).xls", "C:\\template\\out\\b0a7a2a6d0b442bcbea7284d91d8c1e1\\���ֻ��ܱ�(Ͷ���˺�ר��).pdf");
	}
}
//class test{
//	public static void main(String[] args) {
//	        TransferUtils tool = new TransferUtils();
//       		tool.convert2PDF("D:\\贾俊.docx", "D:\\贾俊.pdf");
//	}
//}
