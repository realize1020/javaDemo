//package transforPDF2;
//
//import java.io.File;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//import java.util.concurrent.*;
//
//public class test {
//
//    public static int THREAD_NUM = 10;
//    private static ExecutorService executor =  Executors.newFixedThreadPool(THREAD_NUM);
//
//    public static void main(String[] args) {
//
//        long start = System.currentTimeMillis();
//
//        //FutureToPDF();
//        //ComparableFutureToPDF();
//        //parallelStreamToPDF();
//        //parallelStreamToPDF2();
//
//        ComparableFutureToPDF3();
//
//        System.err.println("转化pdf一共时间："+(System.currentTimeMillis()-start)+"ms");
//    }
//
//
//
//    public static void FutureToPDF(){
//        String filePath = "c:\\bidFile\\418e771efb8a41e3bd9023d01de37b72\\reportFile\\";
//        File reportDir = new File(filePath);
//        File[] myReportFiles =  reportDir.listFiles();
//        if(null != myReportFiles){
//            if(null != myReportFiles && myReportFiles.length > 0){
//                //List<Future<File>> futureList =new ArrayList<Future<File>>();
//                ArrayList<TransferThread> transferThreadList =new ArrayList<TransferThread>();
//                for(File reportFile : myReportFiles) {
//                    if (reportFile.isFile()) {
//                        String fileName = reportFile.getName();
//                        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
//                        if (suffix.contains("pdf") || suffix.contains("PDF")) {
//                            continue;
//                        } else {
//
//                            TransferThread transferThread = new TransferThread(reportFile.getAbsolutePath(), null);
//                            transferThreadList.add(transferThread);
//
//                        }
//                    }
//                }
//
////                for(Future<File> fileFuture : futureList){
////                    File pdfFile = null;
////                    try {
////                        pdfFile = fileFuture.get();
////                    }catch (Exception e) {
////                    }
////                }
//
//                List<Future<File>> futureList=null;
//                try {
//                    futureList = executor.invokeAll(transferThreadList);
//                } catch (InterruptedException e1) {
//                    // TODO Auto-generated catch block
//                    e1.printStackTrace();
//                }
//
//                for(Future<File> fileFuture : futureList){
//                    File pdfFile = null;
//                    try {
//                        pdfFile = fileFuture.get();
//                    }catch (Exception e) {
//                        // TODO Auto-generated catch block
//                        System.out.println("fileFutrue.get()方法出错："+e.getMessage());
//                    }
//                    if(null != pdfFile){
//                        System.out.println(pdfFile.getName()+"生成完毕");
//                    }else {
//                        System.out.println("转PDF失败！");
//                    }
//                }
//
//            }
//        }
//
//    }
//
//
//
//
//    public static void parallelStreamToPDF() {
//        String filePath = "c:\\bidFile\\418e771efb8a41e3bd9023d01de37b72\\reportFile\\";
//        File reportDir = new File(filePath);
//        File[] myReportFiles = reportDir.listFiles();
//        if (null != myReportFiles) {
//            if (null != myReportFiles && myReportFiles.length > 0) {
//
//                List<File> fileList = Arrays.asList(myReportFiles);
//
//                fileList.parallelStream().forEach(reportFile -> {
//                    if (reportFile.isFile()) {
//                        String fileName = reportFile.getName();
//                        String suffi = fileName.substring(fileName.lastIndexOf(".") + 1);
//                        if (!suffi.contains("pdf") && !suffi.contains("PDF")) {
//                            String suffix = TransferUtils.getFileSufix(reportFile.getAbsolutePath());
//                            File file = new File(reportFile.getAbsolutePath());
//                            if (!file.exists()) {
//                                System.out.println("文件不存在！");
//                            }
//                            if (suffix.equals("pdf")) {
//                                System.out.println("PDF not need to convert!");
//                            }
//                            File pdf = null;
//
//                            pdf = new File(file.getParentFile().getAbsolutePath() + File.separator
//                                    + file.getName().substring(0, file.getName().lastIndexOf(".")) + ".pdf");
//
//                            boolean transFlag = false;
//                            if (suffix.equals("doc") || suffix.equals("docx") || suffix.equals("txt")) {
//                                transFlag = TransferUtils.word2PDFAsFixedFormat(reportFile.getAbsolutePath(), pdf.getAbsolutePath());
//                                if (!transFlag) {
//                                    transFlag = TransferUtils.wordSaveAsPdf(reportFile.getAbsolutePath(), pdf.getAbsolutePath());
//                                }
//                                if (!transFlag) {
//                                    // 调用远程openoffice
//                                    TOPDFUtil util = new TOPDFUtil();
//                                    transFlag = util.openOfficeToPDF(file, pdf);
//                                }
//                                /* 暂时注释（避免版权问题） */
////	        	if(!transFlag) {
////	        		ObjectToPDFUtils.wordToPDFUtils(reportFile.getAbsolutePath(),pdf.getAbsolutePath());
////	        	}
//                            } else if (suffix.equals("ppt") || suffix.equals("pptx")) {
//                                transFlag = TransferUtils.ppt2PDF(reportFile.getAbsolutePath(), pdf.getAbsolutePath());
//                                if (!transFlag) {
//                                    // 调用远程openoffice
//                                    TOPDFUtil util = new TOPDFUtil();
//                                    transFlag = util.openOfficeToPDF(file, pdf);
//                                }
//                            } else if (suffix.equals("xls") || suffix.equals("xlsx")) {
//                                transFlag = TransferUtils.excel2PDFAsFixedFormat(reportFile.getAbsolutePath(), pdf.getAbsolutePath());
//                                if (!transFlag) {
//                                    transFlag = TransferUtils.excelSaveAsPDF(reportFile.getAbsolutePath(), pdf.getAbsolutePath());
//                                    System.out.println("文件格式不支持转换!");
//                                }
//                                if (!transFlag) {
//                                    // 调用远程openoffice
//                                    TOPDFUtil util = new TOPDFUtil();
//                                    transFlag = util.openOfficeToPDF(file, pdf);
//                                }
//                            } else {
//                                System.out.println("文件格式不支持转换!");
//                            }
//
//                            if (null != pdf) {
//                                System.out.println(pdf.getName() + "生成完毕");
//                            } else {
//                                System.out.println("转PDF失败！");
//                            }
//                        }
//                    }
//                });
//
//            }
//        }
//    }
//
//
//
//    public static void parallelStreamToPDF2() {
//        String filePath = "c:\\bidFile\\418e771efb8a41e3bd9023d01de37b72\\reportFile\\";
//        File reportDir = new File(filePath);
//        File[] myReportFiles = reportDir.listFiles();
//        if (null != myReportFiles) {
//            if (null != myReportFiles && myReportFiles.length > 0) {
//
//                List<File> fileList = Arrays.asList(myReportFiles);
//
//                ArrayList<CompletableFuture<Void>> completableFutureList =new ArrayList<>();
//
//                fileList.parallelStream().forEach(reportFile -> {
//
//                    CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(() -> {
//                        String suffix = TransferUtils.getFileSufix(reportFile.getAbsolutePath());
//                        File file = new File(reportFile.getAbsolutePath());
//                        if (!file.exists()) {
//                            System.out.println("文件不存在！");
//                        }
//                        if (suffix.equals("pdf")) {
//                            System.out.println("PDF not need to convert!");
//                        }
//                        File pdf = null;
//
//                        pdf = new File(file.getParentFile().getAbsolutePath() + File.separator
//                                + file.getName().substring(0, file.getName().lastIndexOf(".")) + ".pdf");
//
//                        boolean transFlag = false;
//                        if (suffix.equals("doc") || suffix.equals("docx") || suffix.equals("txt")) {
//                            transFlag = TransferUtils.word2PDFAsFixedFormat(reportFile.getAbsolutePath(), pdf.getAbsolutePath());
//                            if (!transFlag) {
//                                transFlag = TransferUtils.wordSaveAsPdf(reportFile.getAbsolutePath(), pdf.getAbsolutePath());
//                            }
//                            if (!transFlag) {
//                                // 调用远程openoffice
//                                TOPDFUtil util = new TOPDFUtil();
//                                transFlag = util.openOfficeToPDF(file, pdf);
//                            }
//                            /* 暂时注释（避免版权问题） */
////	        	if(!transFlag) {
////	        		ObjectToPDFUtils.wordToPDFUtils(reportFile.getAbsolutePath(),pdf.getAbsolutePath());
////	        	}
//                        } else if (suffix.equals("ppt") || suffix.equals("pptx")) {
//                            transFlag = TransferUtils.ppt2PDF(reportFile.getAbsolutePath(), pdf.getAbsolutePath());
//                            if (!transFlag) {
//                                // 调用远程openoffice
//                                TOPDFUtil util = new TOPDFUtil();
//                                transFlag = util.openOfficeToPDF(file, pdf);
//                            }
//                        } else if (suffix.equals("xls") || suffix.equals("xlsx")) {
//                            transFlag = TransferUtils.excel2PDFAsFixedFormat(reportFile.getAbsolutePath(), pdf.getAbsolutePath());
//                            if (!transFlag) {
//                                transFlag = TransferUtils.excelSaveAsPDF(reportFile.getAbsolutePath(), pdf.getAbsolutePath());
//                                System.out.println("文件格式不支持转换!");
//                            }
//                            if (!transFlag) {
//                                // 调用远程openoffice
//                                TOPDFUtil util = new TOPDFUtil();
//                                transFlag = util.openOfficeToPDF(file, pdf);
//                            }
//                        } else {
//                            System.out.println("文件格式不支持转换!");
//                        }
//
//                        if (null != pdf) {
//                            System.out.println(pdf.getName() + "生成完毕");
//                        } else {
//                            System.out.println("转PDF失败！");
//                        }
//                    },executor);
//
//                    completableFutureList.add(completableFuture);
//
//                });
//
//
//                   completableFutureList.parallelStream().forEach(completableFuture -> {
//                    try {
//                        completableFuture.get();
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    } catch (ExecutionException e) {
//                        e.printStackTrace();
//                    }
//                });
//
//
//            }
//        }
//    }
//
//
//    public static void ComparableFutureToPDF(){
//        String filePath = "c:\\bidFile\\418e771efb8a41e3bd9023d01de37b72\\reportFile\\";
//        File reportDir = new File(filePath);
//        File[] myReportFiles =  reportDir.listFiles();
//        if(null != myReportFiles){
//            if(null != myReportFiles && myReportFiles.length > 0){
//                ArrayList<CompletableFuture<Void>> completableFutureList =new ArrayList<>();
//                for(File reportFile : myReportFiles) {
//                    if (reportFile.isFile()) {
//                        String fileName = reportFile.getName();
//                        String suffi = fileName.substring(fileName.lastIndexOf(".") + 1);
//                        if (suffi.contains("pdf") || suffi.contains("PDF")) {
//                            continue;
//                        } else {
//                            CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(() -> {
//                                String suffix = TransferUtils.getFileSufix(reportFile.getAbsolutePath());
//                                File file = new File(reportFile.getAbsolutePath());
//                                if (!file.exists()) {
//                                    System.out.println("文件不存在！");
//                                }
//                                if (suffix.equals("pdf")) {
//                                    System.out.println("PDF not need to convert!");
//                                }
//                                File pdf = null;
//
//                                pdf = new File(file.getParentFile().getAbsolutePath() + File.separator
//                                        + file.getName().substring(0, file.getName().lastIndexOf(".")) + ".pdf");
//
//                                boolean transFlag = false;
//                                if (suffix.equals("doc") || suffix.equals("docx") || suffix.equals("txt")) {
//                                    transFlag = TransferUtils.word2PDFAsFixedFormat(reportFile.getAbsolutePath(), pdf.getAbsolutePath());
//                                    if (!transFlag) {
//                                        transFlag = TransferUtils.wordSaveAsPdf(reportFile.getAbsolutePath(), pdf.getAbsolutePath());
//                                    }
//                                    if (!transFlag) {
//                                        // 调用远程openoffice
//                                        TOPDFUtil util = new TOPDFUtil();
//                                        transFlag = util.openOfficeToPDF(file, pdf);
//                                        System.out.println("失败！要调用远程openoffice");
//                                    }
//                                    /* 暂时注释（避免版权问题） */
////	        	if(!transFlag) {
////	        		ObjectToPDFUtils.wordToPDFUtils(reportFile.getAbsolutePath(),pdf.getAbsolutePath());
////	        	}
//                                } else if (suffix.equals("ppt") || suffix.equals("pptx")) {
//                                    transFlag = TransferUtils.ppt2PDF(reportFile.getAbsolutePath(), pdf.getAbsolutePath());
//                                    if (!transFlag) {
//                                        // 调用远程openoffice
//                                        TOPDFUtil util = new TOPDFUtil();
//                                        transFlag = util.openOfficeToPDF(file, pdf);
//                                    }
//                                } else if (suffix.equals("xls") || suffix.equals("xlsx")) {
//                                    transFlag = TransferUtils.excel2PDFAsFixedFormat(reportFile.getAbsolutePath(), pdf.getAbsolutePath());
//                                    if (!transFlag) {
//                                        transFlag = TransferUtils.excelSaveAsPDF(reportFile.getAbsolutePath(), pdf.getAbsolutePath());
//                                        System.out.println("文件格式不支持转换!");
//                                    }
//                                    if (!transFlag) {
//                                        // 调用远程openoffice
//                                        TOPDFUtil util = new TOPDFUtil();
//                                        transFlag = util.openOfficeToPDF(file, pdf);
//                                    }
//                                } else {
//                                    System.out.println("文件格式不支持转换!");
//                                }
//
//                                if (null != pdf) {
//                                    System.out.println(pdf.getName() + "生成完毕");
//                                } else {
//                                    System.out.println("转PDF失败！");
//                                }
//                            });
//                            completableFutureList.add(completableFuture);
//                        }
//                    }
//                }
//
//                try {
//                    for(CompletableFuture<Void> completableFuture : completableFutureList){
//                        completableFuture.get();
//                    }
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                } catch (ExecutionException e) {
//                    e.printStackTrace();
//                }
//
////                completableFutureList.parallelStream().forEach(completableFuture -> {
////                    try {
////                        completableFuture.get();
////                    } catch (InterruptedException e) {
////                        e.printStackTrace();
////                    } catch (ExecutionException e) {
////                        e.printStackTrace();
////                    }
////                });
//
//
//            }
//        }
//
//    }
//
//
//
//    public static void ComparableFutureToPDF2(){
//        String filePath = "c:\\bidFile\\418e771efb8a41e3bd9023d01de37b72\\reportFile\\";
//        File reportDir = new File(filePath);
//        File[] myReportFiles =  reportDir.listFiles();
//        if(null != myReportFiles){
//            if(null != myReportFiles && myReportFiles.length > 0){
//                //List<Future<File>> futureList =new ArrayList<Future<File>>();
//                ArrayList<TransferThread> transferThreadList =new ArrayList<TransferThread>();
//                ArrayList<CompletableFuture<Void>> completableFutureList =new ArrayList<>();
//                for(File reportFile : myReportFiles) {
//                    if (reportFile.isFile()) {
//                        String fileName = reportFile.getName();
//                        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
//                        if (suffix.contains("pdf") || suffix.contains("PDF")) {
//                            continue;
//                        } else {
//                            TransferThread2 transferThread2 = new TransferThread2(reportFile.getAbsolutePath(), null);
//                            CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(transferThread2,executor);
//                            completableFutureList.add(completableFuture);
//                        }
//                    }
//                }
//
//                try {
//                    for(CompletableFuture<Void> completableFuture : completableFutureList){
//                        completableFuture.get();
//                    }
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                } catch (ExecutionException e) {
//                    e.printStackTrace();
//                }
//
//            }
//        }
//
//    }
//
//
//    public static void ComparableFutureToPDF3(){
//        String filePath = "c:\\bidFile\\418e771efb8a41e3bd9023d01de37b72\\reportFile\\";
//        File reportDir = new File(filePath);
//        File[] myReportFiles =  reportDir.listFiles();
//        if(null != myReportFiles){
//            if(null != myReportFiles && myReportFiles.length > 0){
//                //List<Future<File>> futureList =new ArrayList<Future<File>>();
//                ArrayList<TransferThread> transferThreadList =new ArrayList<TransferThread>();
//                ArrayList<CompletableFuture<Void>> completableFutureList =new ArrayList<>();
//                for(File reportFile : myReportFiles) {
//                    if (reportFile.isFile()) {
//                        String fileName = reportFile.getName();
//                        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
//                        if (suffix.contains("pdf") || suffix.contains("PDF")) {
//                            continue;
//                        } else {
//                            TransferThread2 transferThread2 = new TransferThread2(reportFile.getAbsolutePath(), null);
//                            CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(transferThread2,executor);
//                            completableFutureList.add(completableFuture);
//
//                        }
//                    }
//                }
//
//
//                //等待全部执行完成
//                CompletableFuture.allOf(completableFutureList.toArray(new CompletableFuture[0])).join();
//
//
//
//                try {
//                    for(CompletableFuture<Void> completableFuture : completableFutureList){
//                        completableFuture.get();
//                    }
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                } catch (ExecutionException e) {
//                    e.printStackTrace();
//                }
//
//            }
//        }
//
//    }
//
//
//
//}
//
