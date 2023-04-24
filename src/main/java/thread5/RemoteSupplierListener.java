package thread5;

public interface RemoteSupplierListener {

    public void refreshBidOpeningStatus(String supplier, String supplierOpeningResult,boolean hasBidPrice);

}
