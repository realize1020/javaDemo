package transforPDF2;

import java.io.File;
import java.util.concurrent.Callable;


public class TransferThread2 implements Runnable{

	private String inputFile;
	private String pdfFile;
	public TransferThread2(String inputFile, String pdfFile){
		this.inputFile = inputFile;
		this.pdfFile = pdfFile;
	}

	@Override
	public void run() {
		String suffix = TransferUtils.getFileSufix(inputFile);
		File file = new File(inputFile);
		if (!file.exists()) {
			System.out.println("文件不存在！");
		}
		if (suffix.equals("pdf")) {
			System.out.println("PDF not need to convert!");
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
			transFlag = TransferUtils.word2PDFAsFixedFormat(inputFile, pdf.getAbsolutePath());
			if (!transFlag) {
				transFlag = TransferUtils.wordSaveAsPdf(inputFile, pdf.getAbsolutePath());
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
			transFlag = TransferUtils.ppt2PDF(inputFile, pdf.getAbsolutePath());
			if (!transFlag) {
				// 调用远程openoffice
				TOPDFUtil util = new TOPDFUtil();
				transFlag = util.openOfficeToPDF(file, pdf);
			}
		} else if (suffix.equals("xls") || suffix.equals("xlsx")) {
			transFlag = TransferUtils.excel2PDFAsFixedFormat(inputFile, pdf.getAbsolutePath());
			if (!transFlag) {
				transFlag = TransferUtils.excelSaveAsPDF(inputFile, pdf.getAbsolutePath());
				System.out.println("文件格式不支持转换!");
			}
			if (!transFlag) {
				// 调用远程openoffice
				TOPDFUtil util = new TOPDFUtil();
				transFlag = util.openOfficeToPDF(file, pdf);
			}
		} else {
			System.out.println("文件格式不支持转换!");
		}

		if(null != pdf){
			System.out.println(pdf.getName()+"生成完毕");
		}else {
			System.out.println("转PDF失败！");
		}
	}
}
