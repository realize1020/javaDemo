package FunctionalInterface;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


/**
 * @Description: 评审指标
 * @author guoyr
 */
public class EvaluationFactor {
 
	
	private String id;
	
	
	private String factorName;
	
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
	
	
	private String evalGroup;
	
	
	private String factorType;
	
	
	private String containId;
	
	
	private String objective;
	
	
	private Integer sortNo;
	
	
	private String memo;
	
	
	private String scoreType;
	
	
	private String clauseType;
	
	
	private String evalContent;
	
	
	private byte[] objectiveItem;
	
	private String bidPriceCode;
	
	private byte[] computerParams;
	
	//00 是否性指标； 01 数量性指标
	
	private String scoreStatus;
	
	private Integer achievementNum;
	
	private String dataType;
	
	private String dataParams;
	
	private EvaluationFactorResult evalFactorResult;
	
	private EvaluationFactor containFactor;
	
//	@JsonBackReference
	private EvaluationFactor parentFactor;
	
//	@JsonManagedReference  
	private List<EvaluationFactor> childFactorList = new ArrayList<EvaluationFactor>();
	
	private Integer childCount = 0;
	
	private Integer level = 1;
	
	private List<EvaluationContent> evaluationContentList;
	/**
	 *  2022-10-26
	 * 智能评审规则存储属性（以json形式存储字符串）
	 */
	private String intelligentEval;
	
	/**
	 * 智能评审规则
	 */
	private String remarks;
	

	public void addChild(EvaluationFactor factor) {
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
		return null != factorWeight && factorWeight.compareTo(BigDecimal.ZERO) > 0 ? factorWeight : null;
	}
	
	public void setFactorWeight(BigDecimal factorWeight) {
		this.factorWeight = factorWeight;
	}
	
	public BigDecimal getFactorFinalWeight() {
		return null != factorFinalWeight && factorFinalWeight.compareTo(BigDecimal.ZERO) > 0 ? factorFinalWeight : getFactorWeight();
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
	
	public String getEvalGroup() {
		return evalGroup;
	}

	public void setEvalGroup(String evalGroup) {
		this.evalGroup = evalGroup;
	}

	public String getFactorType() {
		return factorType;
	}
	
	public void setFactorType(String factorType) {
		this.factorType = factorType;
	}
	
//	public String getObjective() {
//		if (EvaluationFactorEnum.CLAUSE_TYPE_DEVIATE.equals(clauseType)) {
//			objective = EvaluationFactorEnum.OBJECTIVE_YES;
//		}
//		return objective;
//	}
//
	public void setObjective(String objective) {
		this.objective = objective;
	}
	
	public Integer getSortNo() {
		return null != sortNo ? sortNo : 0;
	}
	
	public void setSortNo(Integer sortNo) {
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
	
	public EvaluationFactor getParentFactor() {
		return parentFactor;
	}
	
	public void setParentFactor(EvaluationFactor parentFactor) {
		this.parentFactor = parentFactor;
	}
	
	public EvaluationFactor getContainFactor() {
		return containFactor;
	}
	
	public void setContainFactor(EvaluationFactor containFactor) {
		this.containFactor = containFactor;
	}
	
	public List<EvaluationFactor> getChildFactorList() {
		return childFactorList;
	}
	
	public void setChildFactorList(List<EvaluationFactor> childFactorList) {
		this.childFactorList = childFactorList;
	}
	
	public Integer getChildCount() {
		return childCount;
	}
	
	public void setChildCount(Integer childCount) {
		this.childCount = childCount;
	}
	
	public Integer getLevel() {
		return level;
	}
	
	public void setLevel(Integer level) {
		this.level = level;
	}
	
	public EvaluationFactorResult getEvalFactorResult() {
		return evalFactorResult;
	}
	
	public void setEvalFactorResult(EvaluationFactorResult evalFactorResult) {
		this.evalFactorResult = evalFactorResult;
	}
	
	public String getScoreType() {
		return scoreType;
	}
	
	public void setScoreType(String scoreType) {
		this.scoreType = scoreType;
	}
	
	public String getClauseType() {
		return null == clauseType ? "01" : clauseType;
	}

	public void setClauseType(String clauseType) {
		this.clauseType = clauseType;
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
	
	public String getScoreStatus() {
		return scoreStatus;
	}
	
	public void setScoreStatus(String scoreStatus) {
		this.scoreStatus = scoreStatus;
	}
	
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	
	public String getDataType() {
		return dataType;
	}
	
	public Integer getAchievementNum() {
		return achievementNum;
	}

	public void setAchievementNum(Integer achievementNum) {
		this.achievementNum = achievementNum;
	}

	public String getDataParams() {
		return dataParams;
	}

	public void setDataParams(String dataParams) {
		this.dataParams = dataParams;
	}

	public List<EvaluationContent> getEvaluationContentList() {
		return evaluationContentList;
	}

	public void setEvaluationContentList(List<EvaluationContent> evaluationContentList) {
		this.evaluationContentList = evaluationContentList;
	}

	public String getIntelligentEval() {
		return intelligentEval;
	}

	public void setIntelligentEval(String intelligentEval) {
		this.intelligentEval = intelligentEval;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	
}
