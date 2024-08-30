package FunctionalInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class test {

    private FactorClearBidContent  actorClearBidContent1  = null;
   // private final Prop<FactorClearBidContent> factorClearBidContent = new Prop<FactorClearBidContent>();
    static List<Supplier> supplierList = new ArrayList<Supplier>();
    static List<EvaluationFactor> factorList = new ArrayList<EvaluationFactor>();
    static List<SupplierEvaluationFactor> supplierEvaluationFactorList = new ArrayList<SupplierEvaluationFactor>();

    public static void main(String[] args) {

        constructData();

        final Prop<FactorClearBidContent> factorClearBidContent = new Prop<FactorClearBidContent>();
        FactorClearBidContent clearBidContent =new FactorClearBidContent();
        clearBidContent.setSupplierList(supplierList);
        clearBidContent.setFactorList(factorList);
        factorClearBidContent.set(clearBidContent);



        factorClearBidContent.bind(t -> {
                refresh("12345678",supplierList,factorList,supplierEvaluationFactorList);
        });
    }

    private static void constructData() {

        for(int i=0;i<5;i++){
            Supplier supplier=new Supplier();
            supplier.setId(i+"123");
            supplier.setAddress(i+"杭州");
            supplier.setOrgCode(i+"TP123");
            supplierList.add(supplier);

            EvaluationFactor factor =new EvaluationFactor();
            factor.setId(i+"123");
            factor.setAchievementNum(i);
            factor.setChildCount(0);
            factor.setFactorName("factor"+i);
            factorList.add(factor);

            SupplierEvaluationFactor supplierEvaluationFactor =new SupplierEvaluationFactor();
            supplierEvaluationFactor.setId(i+"123");
            supplierEvaluationFactor.setAuditType(i+"");
            supplierEvaluationFactor.setBidPriceCode(i+"123");
            supplierEvaluationFactor.setEvalContent(i+"lubingsun");
            supplierEvaluationFactorList.add(supplierEvaluationFactor);
        }
    }


    public static void refresh(String tenderId, List<Supplier> supplierList, List<EvaluationFactor> factorList, List<SupplierEvaluationFactor> supplierEvaluationFactorList){
        System.out.println("tenderId"+tenderId);
        Optional.ofNullable(supplierList).orElseGet(ArrayList<Supplier>::new);
        Optional.ofNullable(factorList).orElseGet(ArrayList<EvaluationFactor>::new);
        Optional.ofNullable(supplierEvaluationFactorList).orElseGet(ArrayList<SupplierEvaluationFactor>::new);

        supplierList.forEach(supplier -> {
            System.out.println(supplier.getId());
            System.out.println(supplier.getAddress());
            System.out.println(supplier.getOrgCode());
        });

        System.out.println("------------------------------------------");

        factorList.forEach(factor -> {
            System.out.println(factor.getId());
            System.out.println(factor.getAchievementNum());
            System.out.println(factor.getFactorName());
        });

        System.out.println("------------------------------------------");

        supplierEvaluationFactorList.forEach(supplierEvaluationFactor -> {
            System.out.println(supplierEvaluationFactor.getId());
            System.out.println(supplierEvaluationFactor.getAuditType());
            System.out.println(supplierEvaluationFactor.getBidPriceCode());
            System.out.println(supplierEvaluationFactor.getEvalContent());
        });

    }

}
