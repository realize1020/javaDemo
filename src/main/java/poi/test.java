package poi;

import org.apache.poi.POIXMLDocument;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTBookmark;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTDocument1;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.CTP;
import org.w3c.dom.Node;

import java.io.*;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class test {
    public static final String RUN_NODE_NAME = "w:r";
    public static final String TEXT_NODE_NAME = "w:t";
    public static final String BOOKMARK_START_TAG = "w:bookmarkStart";
    public static final String BOOKMARK_END_TAG = "w:bookmarkEnd";
    public static final String BOOKMARK_ID_ATTR_NAME = "w:id";
    public static final String STYLE_NODE_NAME = "w:rPr";


    public static void writer(String inputSrc, String outSrc, Map<String,String> map) {

        try {
            //XWPFDocument doc = new XWPFDocument(POIXMLDocument.openPackage(inputSrc));
            File file = new File(inputSrc);
            // 获取文件输出流
            FileInputStream  fileInputStream  = new FileInputStream (file);

            // 创建操作word的对象
            XWPFDocument doc = new XWPFDocument(fileInputStream);
            List<XWPFParagraph> paragraphs = doc.getParagraphs();
            /**
             * 替换段落中指定的文本
             */
            //XWPFParagraph p = paragraphs.get(38);
            for(XWPFParagraph p :paragraphs){
                CTP ctp = p.getCTP();
                for(int i=0;i<ctp.sizeOfBookmarkStartArray();i++){
                    CTBookmark ctBookmark = ctp.getBookmarkStartArray(i);
                    if(ctBookmark.getName().equals("formula_param")){
                        List<XWPFRun> runs = p.getRuns();
                        if (runs != null) {
                            for (XWPFRun r : runs) {
                                //需要替换的文本
                                String text = r.getText(0);
                                //替换指定的文本
                                r.setText("",0);
                                String[] split = text.split("\\n");
                                for(String textSlice : split){
                                    r.setText(textSlice);
                                    r.addBreak();
                                }
                            }
                        }
                    }
                }

            }
//            List<XWPFRun> runs = p.getRuns();
//            if (runs != null) {
//                for (XWPFRun r : runs) {
//                    //需要替换的文本
//                    String text = r.getText(0);
//                    //替换指定的文本
//                    r.setText("",0);
//                    String[] split = text.split("\\n");
//                    for(String textSlice : split){
//                        r.setText(textSlice);
//                        r.addBreak();
//                    }
//                }
//            }



            /**
             * 替换表格中指定的文字
             */
//            for (XWPFTable tab : doc.getTables()) {
//                for (XWPFTableRow row : tab.getRows()) {
//                    for (XWPFTableCell cell : row.getTableCells()) {
//                        //注意，getParagraphs一定不能漏掉
//                        //因为一个表格里面可能会有多个需要替换的文字
//                        //如果没有这个步骤那么文字会替换不了
//                        for (XWPFParagraph p : cell.getParagraphs()) {
//                            for (XWPFRun r : p.getRuns()) {
//                                String text = r.getText(0);
//                                for (String key : map.keySet()) {
//                                    if (text.equals(key)) {
//                                        r.setText(map.get(text), 0);
//                                    }
//                                }
//                            }
//                        }
//                    }
//                }
//            }
            doc.write(new FileOutputStream(outSrc));
            System.out.println("替换完成");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) throws IOException, InvalidFormatException {
        Map<String, String> map = new HashMap<String, String>();


        map.put("================", "同意！ CE2988/张三  2019-01-21");

        //文件路径
        String srcPath = "C:/bidFile/e6e67506e92c4b7b80aa4581fd1a4808/reportFile/临时报表/评标报告 .docx";
        srcPath = URLDecoder.decode(srcPath, "UTF-8");
        //替换后新文件的路径
        String destPath = "C:/bidFile/e6e67506e92c4b7b80aa4581fd1a4808/reportFile/临时报表/评标报告 .docx";
        writer(srcPath,destPath,map);
        //dealDocx(srcPath,destPath);
//        File inputFile = new File(srcPath);
//        FileInputStream inputStream=new FileInputStream(inputFile);
//        File outputFile = new File(destPath);
//        FileOutputStream outputStream=new FileOutputStream(outputFile);
        //docxOperate(srcPath,destPath);

    }




    public static void docxOperate(String inputSrc, String outSrc) {

        try {
            File inputFile = new File(inputSrc);
            FileInputStream inputStream=new FileInputStream(inputFile);
            File outputFile = new File(outSrc);
            FileOutputStream outputStream=new FileOutputStream(outputFile);

            //XWPFDocument document = new XWPFDocument(inputStream);
            XWPFDocument document = new XWPFDocument(inputStream);
            List<XWPFParagraph> paragraphList =  document.getParagraphs();
            for(XWPFParagraph xwpfParagraph:paragraphList){
                CTP ctp = xwpfParagraph.getCTP();

                for(int dwI = 0;dwI < ctp.sizeOfBookmarkStartArray();dwI++){
                    CTBookmark bookmark = ctp.getBookmarkStartArray(dwI);
                    if("formula_param".equals(bookmark.getName())){

                        List<XWPFRun> runs = xwpfParagraph.getRuns();
                        XWPFRun afterRun=null;
                        String text ="";
                        for(XWPFRun run: runs){
                            text = run.toString();
                            //xwpfParagraph.removeRun(0);

                        }
                        afterRun= xwpfParagraph.createRun();
                        String[] split = text.split("\\n");
                        for(String textSlice : split){
                            afterRun.setText(textSlice);
                            afterRun.addBreak();
                        }

                        Node firstNode = bookmark.getDomNode();
                        Node nextNode = firstNode.getNextSibling();
                        while(nextNode != null){
                            // 循环查找结束符
                            String nodeName = nextNode.getNodeName();
                            if(nodeName.equals(BOOKMARK_END_TAG)){
                                break;
                            }

                            // 删除中间的非结束节点，即删除原书签内容
                            Node delNode = nextNode;
                            nextNode = nextNode.getNextSibling();

                            ctp.getDomNode().removeChild(delNode);
                        }

                        if(nextNode == null){
                            // 始终找不到结束标识的，就在书签前面添加
                            ctp.getDomNode().insertBefore(afterRun.getCTR().getDomNode(),firstNode);
                        }else{
                            // 找到结束符，将新内容添加到结束符之前，即内容写入bookmark中间
                            ctp.getDomNode().insertBefore(afterRun.getCTR().getDomNode(),nextNode);
                        }
                    }
                }
            }

            document.write(outputStream);
            System.out.println("替换完成");
        }catch (Exception e){
            e.printStackTrace();
        }


    }









    private static void dealDocx(String inputSrc,String outputStr){

        //XWPFDocument doc = new XWPFDocument(POIXMLDocument.openPackage(inputSrc));
        File file = new File(inputSrc);

        File outPutfile = new File(outputStr);

        // 获取文件输出流
        FileInputStream  fileInputStream  = null;
        try {
            fileInputStream = new FileInputStream(file);
            FileOutputStream fileOutputStream = new FileOutputStream(outPutfile);

            // 创建操作word的对象
            XWPFDocument wordInput = new XWPFDocument(fileInputStream);
            XWPFDocument wordoutput = new XWPFDocument();

            List<XWPFParagraph> paragraphs = wordInput.getParagraphs();

            for(XWPFParagraph paragraph : paragraphs){

                XWPFParagraph wordoutputParagraph = wordoutput.createParagraph();

                List<XWPFRun> runs = paragraph.getRuns();

                for(XWPFRun run : runs){
                    XWPFRun wordoutputParagraphRun = wordoutputParagraph.createRun();
                    wordoutputParagraphRun.setText(run.getText(0));
                }
            }



            // 获取所有表格
            List<XWPFTable> xwpfTables = wordInput.getTables();
            for (XWPFTable xwpfTable : xwpfTables) {
                XWPFTable wordOutputTable = wordoutput.createTable();
                // 获取一个表格中的所有行
                List<XWPFTableRow> xwpfTableRows = xwpfTable.getRows();
                System.out.println("xwpfTableRows个数"+xwpfTableRows.size());
                for (XWPFTableRow xwpfTableRow : xwpfTableRows) {

                    XWPFTableRow wordOutputTableRow = wordOutputTable.createRow();
                    // 获取一行的所有列
                    List<XWPFTableCell> xwpfTableCell = xwpfTableRow.getTableCells();
                    System.out.println("xwpfTableCell个数"+xwpfTableCell.size());
                    int index = 0;
                    for (XWPFTableCell tableCell : xwpfTableCell) {
                        index++;
                        XWPFTableCell wordOutputTableRowCell = wordOutputTableRow.createCell();
                        // 获取单个列
                        //wordOutputTableRowCell.setText("哈哈哈哈~我修改过了");
                        System.out.println(tableCell.getText());
                        wordOutputTableRowCell.setText(tableCell.getText());
                        System.out.println("index:"+index);
                    }

                    wordOutputTable.removeRow(0);
                }
                //wordOutputTable.removeBorders(); 虚线边框

            }

            CTDocument1 document = wordInput.getDocument();
            System.out.println();


            wordoutput.write(fileOutputStream);
//            wordInput.close();
//            wordoutput.close();
            fileInputStream.close();
            fileOutputStream.close();






        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }



}
