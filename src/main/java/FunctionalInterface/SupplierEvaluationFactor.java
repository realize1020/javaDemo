package FunctionalInterface;

/**
 * @Description: 评审指标
 * @author guoyr
 */

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @preserve private
 */
public class SupplierEvaluationFactor {

    
    private String id;

    
    private String factorName;

    private String supplierId;

    private String tenderId;

    
    private String factorCode;

    
    private String parentId;

    
    private BigDecimal factorScore;

    
    private BigDecimal factorMinScore;

    
    private BigDecimal factorMaxScore;

    
    private BigDecimal factorWeight;

    private BigDecimal factorFinalWeight;

    
    private String auditType;

    
    private String evalBidType;

    
    private String factorType;

    
    private String containId;

    
    private String objective;

    
    private int sortNo;

    
    private String memo;

    
    private String scoreType;

    
    private String evalContent;

    
    private byte[] objectiveItem;

    private String bidPriceCode;

    private byte[] computerParams;

    private SupplierEvaluationFactor parentFactor;
    private SupplierEvaluationFactor containFactor;
    private List<SupplierEvaluationFactor> childFactorList = new ArrayList<SupplierEvaluationFactor>();
    private int childCount = 0;
    private int level = 1;
    private String selectParamValue;
    private String respItemList;

    
    private String opinion;

    
    private String factorResponse;

    private String scoreStatus;

    
    private String finance;

    
    private String performance;

    
    private String creditGrade;

    
    private String paramValues;

    /**
     * 修改评议原因
     */
    private String modifyReason;

    /**
     * 修改评议时间
     */
    private Date modifyTime;

    /**
     * 修改评议人
     */
    private String modifyUserName;


    //private List<EvaluationFactorItem> evaluationFactorItemList = new ArrayList<EvaluationFactorItem>();

//    public void addChild(EvaluationFactorItem factor) {
//        evaluationFactorItemList.add(factor);
//    }

//    public List<EvaluationFactorItem> getEvaluationFactorItemList() {
//        return evaluationFactorItemList;
//    }

//    public void setEvaluationFactorItemList(List<EvaluationFactorItem> evaluationFactorItemList) {
//        this.evaluationFactorItemList = evaluationFactorItemList;
//    }

    public String getRespItemList() {
        return respItemList;
    }

    public void setRespItemList(String respItemList) {
        this.respItemList = respItemList;
    }

    private Map<String, Object> paramsMap = null;


//    public String getPageNumber() {
//        return null == getParamByKey("pageNumber") ? "" : getParamByKey("pageNumber").toString();
//    }

//    @SuppressWarnings("unchecked")
//    public <T> T getParamByKey(String paramKey) {
//        if (null != computerParams) {
//            if (null == paramsMap) {
//                paramsMap = SerializeUtils.deserializeObject(getComputerParams());
//            }
//            if (null != paramsMap) {
//                return (T) paramsMap.get(paramKey);
//            }
//        }
//        return null;
//    }


//    public void setParam(String paramKey, Object paramValue) {
//        if (null != computerParams) {
//            if (null == paramsMap) {
//                paramsMap = SerializeUtils.deserializeObject(getComputerParams());
//            }
//        }
//        if (null == paramsMap) {
//            paramsMap = new HashMap<String, Object>();
//        }
//        if (null != paramValue) {
//            paramsMap.put(paramKey, paramValue);
//        } else {
//
//            paramsMap.remove(paramKey);
//        }
//        if (paramsMap.isEmpty()) {
//            computerParams = null;
//            paramsMap = null;
//        } else {
//            computerParams = SerializeUtils.serializeObject(paramsMap);
//        }
//    }

    public void addChild(SupplierEvaluationFactor factor) {
        childFactorList.add(factor);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFactorName() {
        return factorName;
    }

    public void setFactorName(String factorName) {
        this.factorName = factorName;
    }

    public String getFactorCode() {
        return factorCode;
    }

    public void setFactorCode(String factorCode) {
        this.factorCode = factorCode;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public BigDecimal getFactorScore() {
        return null != factorScore ? factorScore : BigDecimal.ZERO;
    }

    public void setFactorScore(BigDecimal factorScore) {
        this.factorScore = factorScore;
    }

    public BigDecimal getFactorMinScore() {
        return null != factorMinScore ? factorMinScore : BigDecimal.ZERO;
    }

    public void setFactorMinScore(BigDecimal factorMinScore) {
        this.factorMinScore = factorMinScore;
    }

    public BigDecimal getFactorMaxScore() {
        return null != factorMaxScore ? factorMaxScore : getFactorScore();
    }

    public void setFactorMaxScore(BigDecimal factorMaxScore) {
        this.factorMaxScore = factorMaxScore;
    }

    public BigDecimal getFactorWeight() {
        return factorWeight;
    }

    public void setFactorWeight(BigDecimal factorWeight) {
        this.factorWeight = factorWeight;
    }

    public BigDecimal getFactorFinalWeight() {
        return factorFinalWeight;
    }

    public void setFactorFinalWeight(BigDecimal factorFinalWeight) {
        this.factorFinalWeight = factorFinalWeight;
    }

    public String getAuditType() {
        return auditType;
    }

    public void setAuditType(String auditType) {
        this.auditType = auditType;
    }

    public String getEvalBidType() {
        return evalBidType;
    }

    public void setEvalBidType(String evalBidType) {
        this.evalBidType = evalBidType;
    }

    public String getFactorType() {
        return factorType;
    }

    public void setFactorType(String factorType) {
        this.factorType = factorType;
    }

    public String getObjective() {
        return objective;
    }

    public void setObjective(String objective) {
        this.objective = objective;
    }

    public int getSortNo() {
        return sortNo;
    }

    public void setSortNo(int sortNo) {
        this.sortNo = sortNo;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getTenderId() {
        return tenderId;
    }

    public void setTenderId(String tenderId) {
        this.tenderId = tenderId;
    }

    public String getContainId() {
        return containId;
    }

    public void setContainId(String containId) {
        this.containId = containId;
    }

    public SupplierEvaluationFactor getParentFactor() {
        return parentFactor;
    }

    public void setParentFactor(SupplierEvaluationFactor parentFactor) {
        this.parentFactor = parentFactor;
    }

    public SupplierEvaluationFactor getContainFactor() {
        return containFactor;
    }

    public void setContainFactor(SupplierEvaluationFactor containFactor) {
        this.containFactor = containFactor;
    }

    public List<SupplierEvaluationFactor> getChildFactorList() {
        return childFactorList;
    }

    public void setChildFactorList(List<SupplierEvaluationFactor> childFactorList) {
        this.childFactorList = childFactorList;
    }

    public int getChildCount() {
        return childCount;
    }

    public void setChildCount(int childCount) {
        this.childCount = childCount;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getScoreType() {
        return scoreType;
    }

    public void setScoreType(String scoreType) {
        this.scoreType = scoreType;
    }

    public byte[] getObjectiveItem() {
        return objectiveItem;
    }

    public void setObjectiveItem(byte[] objectiveItem) {
        this.objectiveItem = objectiveItem;
    }

    public String getEvalContent() {
//		if(null != scoreType && scoreType.equals(EvaluationFactorEnum.SCORE_TYPE_PASS)){
//			return "通过,不通过";
//		}
        return evalContent;
    }

    public void setEvalContent(String evalContent) {
        this.evalContent = evalContent;
    }

    public String getBidPriceCode() {
        return bidPriceCode;
    }

    public void setBidPriceCode(String bidPriceCode) {
        this.bidPriceCode = bidPriceCode;
    }

    public byte[] getComputerParams() {
        return computerParams;
    }

    public void setComputerParams(byte[] computerParams) {
        this.computerParams = computerParams;
    }

    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }

    public String getFactorResponse() {
        return factorResponse;
    }

    public void setFactorResponse(String factorResponse) {
        this.factorResponse = factorResponse;
    }

    public String getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(String supplierId) {
        this.supplierId = supplierId;
    }

    public String getScoreStatus() {
        return scoreStatus;
    }

    public void setScoreStatus(String scoreStatus) {
        this.scoreStatus = scoreStatus;
    }

    public String getFinance() {
        return finance;
    }

    public void setFinance(String finance) {
        this.finance = finance;
    }

    public String getPerformance() {
        return performance;
    }

    public void setPerformance(String performance) {
        this.performance = performance;
    }

    public String getCreditGrade() {
        return creditGrade;
    }

    public void setCreditGrade(String creditGrade) {
        this.creditGrade = creditGrade;
    }

    public String getParamValues() {
        return paramValues;
    }

    public void setParamValues(String paramValues) {
        this.paramValues = paramValues;
    }

    public String getSelectParamValue() {
        return selectParamValue;
    }

    public void setSelectParamValue(String selectParamValue) {
        this.selectParamValue = selectParamValue;
    }

    public String getModifyReason() {
        return modifyReason;
    }

    public void setModifyReason(String modifyReason) {
        this.modifyReason = modifyReason;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getModifyUserName() {
        return modifyUserName;
    }

    public void setModifyUserName(String modifyUserName) {
        this.modifyUserName = modifyUserName;
    }

}
