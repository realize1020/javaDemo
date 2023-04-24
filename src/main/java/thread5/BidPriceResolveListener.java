package thread5;

public class BidPriceResolveListener {

    private String tenderProject;
    private int poolMaxSize = 3;

    public BidPriceResolveListener(String tenderProject, int poolMaxSize){
        this.tenderProject = tenderProject;
        this.poolMaxSize = poolMaxSize;
    }
}
