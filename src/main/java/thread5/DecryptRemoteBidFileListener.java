package thread5;

public interface DecryptRemoteBidFileListener {

    public void stopRemoteDecrypt();

    public void refreshProgress(int totalCount, int successCount, int noDecCount, int failedDecCout, int errorCount);

    public void refreshBidOpeningStatus(String supplier, String supplierOpeningResult, boolean hasBidPrice);

}
