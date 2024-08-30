package FunctionalInterface;

/**
 * @Description: 评审内容
 * @author chenxw
 */
public class EvaluationContent {
	
	private String id;
	private String tenderId;
	private String factorCode;
	private String relChapterType;
	private String dataCategory;
	private String dataCode;
	private String evalRule;
	private String tenderStructName;
	private String evalPointName;
	private Integer sortNo;
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getTenderId() {
		return tenderId;
	}
	
	public void setTenderId(String tenderId) {
		this.tenderId = tenderId;
	}
	
	public String getFactorCode() {
		return factorCode;
	}
	
	public void setFactorCode(String factorCode) {
		this.factorCode = factorCode;
	}
	
	public String getRelChapterType() {
		return relChapterType;
	}
	
	public void setRelChapterType(String relChapterType) {
		this.relChapterType = relChapterType;
	}
	
	public String getDataCategory() {
		return dataCategory;
	}
	
	public void setDataCategory(String dataCategory) {
		this.dataCategory = dataCategory;
	}
	
	public String getDataCode() {
		return dataCode;
	}
	
	public void setDataCode(String dataCode) {
		this.dataCode = dataCode;
	}
	
	public String getEvalRule() {
		return evalRule;
	}
	
	public void setEvalRule(String evalRule) {
		this.evalRule = evalRule;
	}
	
	public String getTenderStructName() {
		return tenderStructName;
	}
	
	public void setTenderStructName(String tenderStructName) {
		this.tenderStructName = tenderStructName;
	}
	
	public String getEvalPointName() {
		return evalPointName;
	}
	
	public void setEvalPointName(String evalPointName) {
		this.evalPointName = evalPointName;
	}
	
	public Integer getSortNo() {
		return sortNo;
	}
	
	public void setSortNo(Integer sortNo) {
		this.sortNo = sortNo;
	}
	
}