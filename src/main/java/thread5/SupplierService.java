package thread5;

import java.util.ArrayList;
import java.util.List;

public class SupplierService {

    public List<String> getSupplierOpenBidList(){
        ArrayList<String> supplierList = new ArrayList<String>();
        String supplier1 = "供应商1价格标开标成功";
        String supplier2 = "供应商2价格标开标失败";
        String supplier3 = "供应商3价格标开标成功";
        String supplier4 = "供应商4价格标开标成功";
        String supplier5 = "供应商5价格标开标失败";
        String supplier6 = "供应商6废标";

        supplierList.add(supplier1);
        supplierList.add(supplier2);
        supplierList.add(supplier3);
        supplierList.add(supplier4);
        supplierList.add(supplier5);
        supplierList.add(supplier6);

        return supplierList;
    }


    public static void main(String[] args) {
        WhileThread thread = new WhileThread();
        thread.run();
    }

}
