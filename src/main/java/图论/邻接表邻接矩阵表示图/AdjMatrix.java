package 图论.邻接表邻接矩阵表示图;

import java.io.*;
import java.util.Scanner;

/**
 * 图的基本表示：邻接矩阵
 */
public class AdjMatrix {

    private int V;
    private int E;
    private int[][] adj;

    public AdjMatrix(String filename){

        File file = new File(filename);

        try(Scanner scanner = new Scanner(file)){

            V = scanner.nextInt();
            adj = new int[V][V];

            E = scanner.nextInt();
            for(int i = 0; i < E; i ++){
                int a = scanner.nextInt();
                int b = scanner.nextInt();
                adj[a][b] = 1;
                adj[b][a] = 1;
            }
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("V = %d, E = %d\n", V, E));
        for(int i = 0; i < V; i ++){
            for(int j = 0; j < V; j ++)
                sb.append(String.format("%d ", adj[i][j]));
            sb.append('\n');
        }
        return sb.toString();
    }

    public static void main(String[] args){

        AdjMatrix adjMatrix = new AdjMatrix("D:\\IdeaProjects\\java_file_test\\src\\main\\java\\图论\\g.txt");
        System.out.print(adjMatrix);
    }
}
