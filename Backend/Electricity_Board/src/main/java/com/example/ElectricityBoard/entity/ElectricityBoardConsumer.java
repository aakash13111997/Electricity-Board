package com.example.ElectricityBoard.entity;

import java.util.Date;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="ELECTRICITY_BOARD_CONSUMER",schema="bcg")
public class ElectricityBoardConsumer {
	
	@Id
	@GeneratedValue(strategy= GenerationType.SEQUENCE,generator="board_consumer_seq")
	@SequenceGenerator(name="board_consumer_seq",sequenceName="board_consumer_seq",schema="bcg",allocationSize=1)
	@Column(name="\"applicant_id\"")
	private Long applicantId;
	
	@Column(name="\"Applicant_Name\"")
	private String applicantName;

	@Column(name="\"Gender\"")
	private String gender;
	
	@Column(name="\"District\"")
	private String district;
	
	@Column(name="\"State\"")
	private String state;
	
	@Column(name="\"Pincode\"")
	private String pincode;
	
	@Column(name="\"Ownership\"")
	private String ownership;
	
	@Column(name="\"GovtID_Type\"")
	private String govtIdType;
	
	@Column(name="\"ID_Number\"")
	private String idNumber;
	
	@Column(name="\"Category\"")
	private String category;
	
	@Column(name="\"Load_Applied\"")
	private String loadApplied;
	
	@Column(name="\"Status\"")
	private String status;
	
	@Column(name="\"Reviewer_Id\"")
	private String reviewerId;
	
	@Column(name="\"Reviewer_Name\"")
	private String reviewerName;
	
	@Column(name="\"Reviewer_Comments\"")
	private String reviewerComments;
	
	@Column(name="\"Date_Of_Application\"")
	private Date dateOfApplication;
	
	@Column(name="\"Date_Of_Approval\"")
	private Date dateOfApproval;
	
	@Column(name="\"Modified_Date\"")
	private Date modifiedDate;
	
//	@OneToOne(mappedBy="electricityBoardConsumer",cascade=CascadeType.ALL)
//	private ReviewerDetails reviewerDetails;

	public Long getApplicantId() {
		return applicantId;
	}

	public void setApplicantId(Long applicantId) {
		this.applicantId = applicantId;
	}

	public String getGovtIdType() {
		return govtIdType;
	}

	public void setGovtIdType(String govtIdType) {
		this.govtIdType = govtIdType;
	}

	public String getLoadApplied() {
		return loadApplied;
	}

	public void setLoadApplied(String loadApplied) {
		this.loadApplied = loadApplied;
	}

	public Date getDateOfApplication() {
		return dateOfApplication;
	}

	public void setDateOfApplication(Date dateOfApplication) {
		this.dateOfApplication = dateOfApplication;
	}

	public String getApplicantName() {
		return applicantName;
	}

	public void setApplicantName(String applicantName) {
		this.applicantName = applicantName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public String getOwnership() {
		return ownership;
	}

	public void setOwnership(String ownership) {
		this.ownership = ownership;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String id_Number) {
		this.idNumber = id_Number;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getReviewerId() {
		return reviewerId;
	}

	public void setReviewerId(String reviewerId) {
		this.reviewerId = reviewerId;
	}

	public String getReviewerName() {
		return reviewerName;
	}

	public void setReviewerName(String reviewerName) {
		this.reviewerName = reviewerName;
	}

	public String getReviewerComments() {
		return reviewerComments;
	}

	public void setReviewerComments(String reviewerComments) {
		this.reviewerComments = reviewerComments;
	}

	public Date getDateOfApproval() {
		return dateOfApproval;
	}

	public void setDateOfApproval(Date dateOfApproval) {
		this.dateOfApproval = dateOfApproval;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}	
	
	
	
	

}
