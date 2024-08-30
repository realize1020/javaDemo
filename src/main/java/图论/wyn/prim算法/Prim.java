package 图论.wyn.prim算法;

import 图论.wyn.无向带权图邻接矩阵表示.WeightedMatrixNoScannerPlus;

public class Prim {
    public void minGenerTree(int [][] graphMatrix){

    }

    public static void main(String[] args) {
        Prim prim =new Prim();
        int[][] graphMatrix = WeightedMatrixNoScannerPlus.createGraphMatrix();
        prim.minGenerTree(graphMatrix);
    }
}
