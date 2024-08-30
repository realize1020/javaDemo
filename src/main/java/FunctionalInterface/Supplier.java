package FunctionalInterface;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * @Description: 投标人
 * @author guoyr
 */

/**
 * @preserve private
 */
public class Supplier {

	private String id;

	private String supplierName;// 投标人名称

	private String tenderId;// 所属投标项目或标段

	private String orgCode;// 投标人编号

	private String linker;// 联系人

	private String idCard;// 身份证号

	private String linkerTel;// 联系电话

	private String address;// 联系地址

	private String zipcode;// 邮编
	
	private String memo;
	
	private String applyUrl;// 该字段没用，暂时存放临时添加的邮箱地址

	private Date applyTime; // 报名时间

	private Date tenderTime; // 投标时间

	private String signType;

	private String bidBondType;
	
	private BigDecimal tenderFee;
	
	private Date createTime; // 创建时间

	private Date modifyTime; // 修改时间

	private String useStatus;// 使用状态
	
	private Integer sortNo;
	
	private String bidNumber;
	
	/**
	 * 供应商合格状态 (00非合格 01合格 03查询失败)
	 */
	private String qualifiedStatus ; // 供应商合格状态
	
	private String anonymName ="";// 投标人匿名名称
	
	//private TenderProjectRuleEntity tenderProjectRuleEntity;// 项目规则
	
	private boolean isAnonymous = false;// 是否匿名
	private Integer anonymousIndex  = 1;// 匿名投标人序号
	
	//private SupplierOpeningResult supplierOpeningResult;
	//private SupplierEvaluationResult supplierEvaluationResult;
	
	
	private String bidFileType;
	
	private String signPerson;
	
	private Date signTime;
	
	private String signPersonTel;
	
	private String signPersonCard;
	
	private String priceSignPerson;
	
	private Date priceSignTime;
	
	private String priceSignPersonTel;
	
	private String priceSignPersonCard;
	
	private String email;
	
	private Date bidFileDownloadTime;
	
    /**
     * 标书下载时记录的投标时间（用于验证已下载的是否最新的标书）
     */
    private Date downloadTimeTenderTime;

	private byte[] signFile;
	
	private String sealedBidCode;
	
	private String orgId;
	
	private BigDecimal bidBond;
	
	private String isBasicAccount;
	
	private String inDate; // 保证金到账时间
	
	/**
	 * 开标一览表签章状态01:已签章 00：未签章
	 */
	private String openFileSignStatus;
	
	private String invitationStatus;//视频会议邀请状态//00，取消，01，邀请
	
	private long fileSize;
	
	private String supplierJointName;
	
	private String correctedSupplierJointName;
	
	private String corporation;

	private String correctedCorporation;

	
	public long getFileSize() {
		return fileSize;
	}
	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}
	
	
	
	public String getInvitationStatus() {
		return invitationStatus;
	}
	public void setInvitationStatus(String invitationStatus) {
		this.invitationStatus = invitationStatus;
	}
	
	public String getOpenFileSignStatus() {
		return openFileSignStatus;
	}
	public void setOpenFileSignStatus(String openFileSignStatus) {
		this.openFileSignStatus = openFileSignStatus;
	}
	
	
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
//	private List<BidPriceResult> bidPriceResultList;
//
//	private List<RemoteFile> downLoadFTPFiles;
	private long totalSize;

//	public List<BidPriceResult> getBidPriceResultList() {
//		return bidPriceResultList;
//	}
//	public void setBidPriceResultList(List<BidPriceResult> bidPriceResultList) {
//		this.bidPriceResultList = bidPriceResultList;
//	}
	public String getBidBondType() {
		return bidBondType;
	}
	public void setBidBondType(String bidBondType) {
		this.bidBondType = bidBondType;
	}
	public byte[] getSignFile() {
		return signFile;
	}
	public void setSignFile(byte[] signFile) {
		this.signFile = signFile;
	}
	public String getSignPersonCard() {
		return signPersonCard;
	}
	public Date getSignTime() {
		return signTime;
	}
	public void setSignTime(Date signTime) {
		this.signTime = signTime;
	}
	public void setSignPersonCard(String signPersonCard) {
		this.signPersonCard = signPersonCard;
	}
	public String getSignPersonTel() {
		return signPersonTel;
	}
	public void setSignPersonTel(String signPersonTel) {
		this.signPersonTel = signPersonTel;
	}
	public String getSignPerson() {
		return signPerson;
	}
	public void setSignPerson(String signPerson) {
		this.signPerson = signPerson;
	}
	
	public String getPriceSignPerson() {
		return priceSignPerson;
	}
	public void setPriceSignPerson(String priceSignPerson) {
		this.priceSignPerson = priceSignPerson;
	}
	public Date getPriceSignTime() {
		return priceSignTime;
	}
	public void setPriceSignTime(Date priceSignTime) {
		this.priceSignTime = priceSignTime;
	}
	public String getPriceSignPersonTel() {
		return priceSignPersonTel;
	}
	public void setPriceSignPersonTel(String priceSignPersonTel) {
		this.priceSignPersonTel = priceSignPersonTel;
	}
	public String getPriceSignPersonCard() {
		return priceSignPersonCard;
	}
	public void setPriceSignPersonCard(String priceSignPersonCard) {
		this.priceSignPersonCard = priceSignPersonCard;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getBidFileType() {
		return bidFileType;
	}
	public void setBidFileType(String bidFileType) {
		this.bidFileType = bidFileType;
	}
	public boolean isAnonymous(){
		return isAnonymous;
	}
	public void setAnonymous(boolean isAnonymous){
		setAnonymous(isAnonymous, 0);
	}
	public void setAnonymous(boolean isAnonymous, Integer anonymousIndex){
		this.isAnonymous = isAnonymous;
		this.anonymousIndex = anonymousIndex;
	}
	public String getAnonymName() {
//		if(StringUtils.isEmpty(anonymName)){
//			anonymName = "******";
//		}
		return anonymName;
	}

	public void setAnonymName(String anonymName) {
//		if(StringUtils.isEmpty(anonymName)){
//			anonymName = "";
//		}
		this.anonymName = anonymName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSupplierName() {
		if(isAnonymous){
//			if(null != anonymousIndex && anonymousIndex > 0) {
//				return "第"+NumericChineseUtils.getChinese(anonymousIndex)+"家";
//			}else {
//				return getAnonymName();
//			}
		}
		if(null != supplierJointName && supplierJointName.length() > 0) {
			return supplierName.concat("&").concat(supplierJointName);
		}
		return supplierName;
	}
	
	public String getCorrectedSupplierName() {
		if(isAnonymous){
			if(null != anonymousIndex && anonymousIndex > 0) {
//				return "第"+NumericChineseUtils.getChinese(anonymousIndex)+"家";
			}else {
				return getAnonymName();
			}
		}
		if(null != correctedSupplierJointName && correctedSupplierJointName.length() > 0) {
			return supplierName.concat("&").concat(correctedSupplierJointName);
		}
		return supplierName;
	}
	
	public String getSupplierNameNoTWithJoint() {
		if(isAnonymous){
			if(null != anonymousIndex && anonymousIndex > 0) {
//				return "第"+NumericChineseUtils.getChinese(anonymousIndex)+"家";
			}else {
				return getAnonymName();
			}
		}
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getLinker() {
		return linker;
	}

	public void setLinker(String linker) {
		this.linker = linker;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getLinkerTel() {
		return linkerTel;
	}

	public void setLinkerTel(String linkerTel) {
		this.linkerTel = linkerTel;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public Date getApplyTime() {
		return applyTime;
	}

	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}

	public Date getTenderTime() {
		return tenderTime;
	}

	public void setTenderTime(Date tenderTime) {
		this.tenderTime = tenderTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getUseStatus() {
		return useStatus;
	}

	public void setUseStatus(String useStatus) {
		this.useStatus = useStatus;
	}

	public String getTenderId() {
		return tenderId;
	}

	public void setTenderId(String tenderId) {
		this.tenderId = tenderId;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getApplyUrl() {
		return applyUrl;
	}

	public void setApplyUrl(String applyUrl) {
		this.applyUrl = applyUrl;
	}

	public String getSignType() {
		return signType;
	}

	public void setSignType(String signType) {
		this.signType = signType;
	}

	public BigDecimal getBidBond() {
		return bidBond;
	}

	public void setBidBond(BigDecimal bidBond) {
		this.bidBond = bidBond;
	}
	
	public BigDecimal getTenderFee() {
		return tenderFee;
	}

	public void setTenderFee(BigDecimal tenderFee) {
		this.tenderFee = tenderFee;
	}

//	public SupplierOpeningResult getSupplierOpeningResult() {
//		return supplierOpeningResult;
//	}
//
//	public void setSupplierOpeningResult(SupplierOpeningResult supplierOpeningResult) {
//		this.supplierOpeningResult = supplierOpeningResult;
//	}
//
//	public String getSignTypeCN() {
//		return SupplierEnum.getSignTypeCN(signType);
//	}
//
//	public SupplierEvaluationResult getSupplierEvaluationResult() {
//		return supplierEvaluationResult;
//	}
//
//	public void setSupplierEvaluationResult(SupplierEvaluationResult supplierEvaluationResult) {
//		this.supplierEvaluationResult = supplierEvaluationResult;
//	}

	public Integer getSortNo() {
		return sortNo;
	}

	public void setSortNo(Integer sortNo) {
		this.sortNo = sortNo;
	}
	
//	public TenderProjectRuleEntity getProjectRule() {
//		return tenderProjectRuleEntity;
//	}
//	public void setProjectRule(TenderProjectRuleEntity tenderProjectRuleEntity) {
//		this.tenderProjectRuleEntity = tenderProjectRuleEntity;
//	}
	public Date getBidFileDownloadTime() {
		return bidFileDownloadTime;
	}
	public void setBidFileDownloadTime(Date bidFileDownloadTime) {
		this.bidFileDownloadTime = bidFileDownloadTime;
	}

	public Date getDownloadTimeTenderTime() {
		return downloadTimeTenderTime;
	}

	public void setDownloadTimeTenderTime(Date downloadTimeTenderTime) {
		this.downloadTimeTenderTime = downloadTimeTenderTime;
	}

	public String getSealedBidCode() {
		return sealedBidCode;
	}
	public void setSealedBidCode(String sealedBidCode) {
		this.sealedBidCode = sealedBidCode;
	}
	public String getBidNumber() {
		return bidNumber;
	}
	public void setBidNumber(String bidNumber) {
		this.bidNumber = bidNumber;
	}
//	public void setDownLoadFTPFiles(List<RemoteFile> downLoadFTPFiles) {
//		this.downLoadFTPFiles = downLoadFTPFiles;
//	}
//	public List<RemoteFile> getDownLoadFTPFiles() {
//		return downLoadFTPFiles;
//	}
	public void setTotalSize(long totalSize) {
		this.totalSize = totalSize;
	}
	public long getTotalSize() {
		return totalSize;
	}
	public void setQualifiedStatus(String qualifiedStatus) {
		this.qualifiedStatus = qualifiedStatus;
	}
	public String getQualifiedStatus() {
		return qualifiedStatus;
	}
	public String getIsBasicAccount() {
		return isBasicAccount;
	}
	public void setIsBasicAccount(String isBasicAccount) {
		this.isBasicAccount = isBasicAccount;
	}
	public String getInDate() {
		return inDate;
	}
	public void setInDate(String inDate) {
		this.inDate = inDate;
	}
	public String getSupplierJointName() {
		return supplierJointName;
	}
	public void setSupplierJointName(String supplierJointName) {
		this.supplierJointName = supplierJointName;
	}
	
	public String getCorrectedSupplierJointName() {
		return correctedSupplierJointName;
	}
	public void setCorrectedSupplierJointName(String correctedSupplierJointName) {
		this.correctedSupplierJointName = correctedSupplierJointName;
	}
	public String getCorporation() {
		return corporation;
	}
	public void setCorporation(String corporation) {
		this.corporation = corporation;
	}
	public String getCorrectedCorporation() {
		return correctedCorporation;
	}
	public void setCorrectedCorporation(String correctedCorporation) {
		this.correctedCorporation = correctedCorporation;
	}
	public String getSupplierNameByJointName() {
		if(isAnonymous){
			if(null != anonymousIndex && anonymousIndex > 0) {
//				return "第"+NumericChineseUtils.getChinese(anonymousIndex)+"家";
			}else {
				return getAnonymName();
			}
		}
		
		String jointName = getSupplierJointName();
		if(null != jointName && jointName.length() > 0) {
			return jointName;
		}
		return supplierName;
		
	}
}
