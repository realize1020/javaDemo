package 图论.wyn.无向图邻接矩阵表示;

import java.util.Scanner;

public class Matrix {

    private static int vertexCount;

    private static int edgeCount;

    private static int [][] matrix;

    private static final int NO_CONNECTION = 0;

    private static final int CONNECTION = 1;

    public static void main(String[] args) {




        for(int a=0;a<vertexCount;a++){
            for(int b=0;b<vertexCount;b++){
                matrix[a][b]=NO_CONNECTION;
            }
        }

        System.out.println("请输入顶点数量：");
        Scanner scanner =new Scanner(System.in);
        vertexCount = scanner.nextInt();

        System.out.println("请输入边的数量：");
        edgeCount = scanner.nextInt();

        matrix =new int [vertexCount][vertexCount];


        System.out.println("请从1开始输入与其他数字的连接关系，中间用逗号隔开,按回车换行，然后继续输入，例如：1,2");


        int i=1;
        while(i++<=edgeCount){

            String group = scanner.next();

            String[] vertex = group.split(",");
            int vertexRowNumber  = Integer.valueOf(vertex[0]);
            int vertexColNumber  = Integer.valueOf(vertex[1]);

            matrix[vertexRowNumber][vertexColNumber] = CONNECTION;
            matrix[vertexColNumber][vertexRowNumber] = CONNECTION;


        }


        System.out.println("输入完毕！输出邻接矩阵：");

        for(int j=0;j<vertexCount;j++){
            for(int k=0;k<vertexCount;k++){
                System.out.print(matrix[j][k]+" ");
            }
            System.out.println();
        }


    }



}
