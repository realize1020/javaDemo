package 图论.wyn.prim算法;

public class Vertex {


    public Vertex() {

    }

    public Vertex(int startVertex, int endVertex, int weight) {
        this.startVertex = startVertex;
        this.endVertex = endVertex;
        this.weight = weight;
    }


    //起始顶点
    private int startVertex;

    //终止顶点
    private int endVertex;

    //权重
    private int weight;

}
