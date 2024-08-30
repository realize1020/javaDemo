package 图论.wyn.无向带权图邻接矩阵表示;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class WeightedMatrixNoScanner {
    private static int vertexCount;

    private static int edgeCount;

    private static int weight;

    private static int[][] matrix;

    private static final int NO_CONNECTION = 0;


    public static void main(String[] args) {

        File file = new File("D:\\IdeaProjects\\java_file_test\\src\\main\\java\\图论\\wyn\\无向带权图邻接矩阵表示\\g.txt");
        try (Scanner scanner = new Scanner(file)) {

            vertexCount = scanner.nextInt();

            System.out.println("输入顶点的数量为："+vertexCount);

            edgeCount = scanner.nextInt();
            System.out.println("输入边的数量为："+edgeCount);



            matrix =new int [vertexCount][vertexCount];


            int i=1;
            while(i++<=edgeCount){

                int vertexRowNumber = scanner.nextInt();
                int vertexColNumber  = scanner.nextInt();
                weight = scanner.nextInt();


                matrix[vertexRowNumber][vertexColNumber] = weight;
                matrix[vertexColNumber][vertexRowNumber] = weight;


            }


            System.out.println("输入完毕！输出邻接矩阵：");

            for(int j=0;j<vertexCount;j++){
                for(int k=0;k<vertexCount;k++){
                    System.out.print(matrix[j][k]+" ");
                }
                System.out.println();
            }



        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}