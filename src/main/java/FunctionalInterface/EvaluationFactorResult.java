package FunctionalInterface;

import java.math.BigDecimal;

/**
 * @Description: 指标评标结果表
 * @author guoyr
 */

/**
 * @preserve private
 */
public class EvaluationFactorResult {
	
	private String id;

	private String stepId; 

	private String tenderId; 
	
	private String supplierId; 
	
	private String userId; 
	
	private String factorId; 
	
	private String factorName; 
	
//	@Column(name = "SYSTEM_SCORE", length = 8, columnDefinition = "系统得分")
	private BigDecimal systemScore;
	
	private BigDecimal evaluationScore;
	
	private BigDecimal evaluationFinalScore;
	
	private BigDecimal basePrice;

	private BigDecimal deviationRate;
	
	private String evaluationResult; 
	
	private String evaluationOpinion; 
	
	private BigDecimal evaluationPrice; 
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStepId() {
		return stepId;
	}

	public void setStepId(String stepId) {
		this.stepId = stepId;
	}

	public String getTenderId() {
		return tenderId;
	}

	public void setTenderId(String tenderId) {
		this.tenderId = tenderId;
	}

	public String getFactorId() {
		return factorId;
	}

	public void setFactorId(String factorId) {
		this.factorId = factorId;
	}

	public BigDecimal getSystemScore() {
		return systemScore;
	}

	public void setSystemScore(BigDecimal systemScore) {
		this.systemScore = systemScore;
	}

	public BigDecimal getEvaluationScore() {
		return evaluationScore;
	}

	public void setEvaluationScore(BigDecimal evaluationScore) {
		this.evaluationScore = evaluationScore;
	}

	public BigDecimal getEvaluationFinalScore() {
		if(null == evaluationFinalScore){
			evaluationFinalScore = evaluationScore; 
		}
		return evaluationFinalScore;
	}

	public void setEvaluationFinalScore(BigDecimal evaluationFinalScore) {
		this.evaluationFinalScore = evaluationFinalScore;
	}

	public String getEvaluationResult() {
		return evaluationResult;
	}

	public void setEvaluationResult(String evaluationResult) {
		this.evaluationResult = evaluationResult;
	}

	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public BigDecimal getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(BigDecimal basePrice) {
		this.basePrice = basePrice;
	}

	public BigDecimal getDeviationRate() {
		return deviationRate;
	}

//	public void setDeviationRate(BigDecimal deviationRate) {
//		this.deviationRate = deviationRate;
//	}
//	 public BigDecimal getBasePriceByUnit(String unitCn){
//     	if(null != basePrice){
//     		return MoneyUtils.getMoneyByUnit(basePrice, unitCn);
//     	}
//     	return basePrice;
//     }
//	public String getEvalPassTypeCN(String evalContent) {
//		if(null != evalContent && null != evaluationResult){
//			String[] evalContentArray = evalContent.split(",");
//			if(evalContentArray.length == 2){
//				if(evaluationResult.equals(EvaluationFactorEnum.CONFORM)){
//					return evalContentArray[0];
//				}else if(evaluationResult.equals(EvaluationFactorEnum.UNCONFORM)){
//					return evalContentArray[1];
//				}
//			}
//		}
//		return EvaluationFactorEnum.getEvalPassTypeCN(evaluationResult);
//	}

	public String getEvaluationOpinion() {
		return evaluationOpinion;
	}

	public void setEvaluationOpinion(String evaluationOpinion) {
		this.evaluationOpinion = evaluationOpinion;
	}

	public String getFactorName() {
		return factorName;
	}

	public void setFactorName(String factorName) {
		this.factorName = factorName;
	}
	public BigDecimal getEvaluationPrice() {
		return evaluationPrice;
	}

	public void setEvaluationPrice(BigDecimal evaluationPrice) {
		this.evaluationPrice = evaluationPrice;
	}
}
