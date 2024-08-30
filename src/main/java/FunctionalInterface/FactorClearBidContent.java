package FunctionalInterface;

import java.util.List;

public class FactorClearBidContent {
    private List<Supplier> supplierList;
    private List<EvaluationFactor> factorList;
//    private List<SupplierEvaluationFactor> supplierEvaluationFactorList;

    public List<Supplier> getSupplierList() {
        return supplierList;
    }

    public void setSupplierList(List<Supplier> supplierList) {
        this.supplierList = supplierList;
    }

    public List<EvaluationFactor> getFactorList() {
        return factorList;
    }

    public void setFactorList(List<EvaluationFactor> factorList) {
        this.factorList = factorList;
    }

//    public List<SupplierEvaluationFactor> getSupplierEvaluationFactorList() {
//        return supplierEvaluationFactorList;
//    }
//
//    public void setSupplierEvaluationFactorList(List<SupplierEvaluationFactor> supplierEvaluationFactorList) {
//        this.supplierEvaluationFactorList = supplierEvaluationFactorList;
//    }
}
