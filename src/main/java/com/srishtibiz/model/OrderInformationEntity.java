package com.srishtibiz.model;

public class OrderInformationEntity {
	String customerName;
	String customerPhone;
	String customerMail;
	String customerAddress;
	String orderNumber;
	

	

	Double orderAmount;
	String generalInstruction;
	String orderStatus;
	String resturantID;
	String paymentStatus;
	int lastFourDigitOfTheCard;
	String paymentTransactionID;
	String statusDetails;
	String userRating;
	String userRatingComments;
	String restarentName;
	String menuItemName;
	int quantity;
	String order_session_id;
	public String getOrder_session_id() {
		return order_session_id;
	}
	public void setOrder_session_id(String order_session_id) {
		this.order_session_id = order_session_id;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public String getOrderNumber() {
		return orderNumber;
	}
	public String getMenuItemName() {
		return menuItemName;
	}

	public void setMenuItemName(String menuItemName) {
		this.menuItemName = menuItemName;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getRestarentName() {
		return restarentName;
	}

	public void setRestarentName(String restarentName) {
		this.restarentName = restarentName;
	}

	public OrderInformationEntity() {
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerPhone() {
		return customerPhone;
	}

	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}

	public String getCustomerMail() {
		return customerMail;
	}

	public void setCustomerMail(String customerMail) {
		this.customerMail = customerMail;
	}

	public String getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}

	

	public Double getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(Double orderAmount) {
		this.orderAmount = orderAmount;
	}

	public String getGeneralInstruction() {
		return generalInstruction;
	}

	public void setGeneralInstruction(String generalInstruction) {
		this.generalInstruction = generalInstruction;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getResturantID() {
		return resturantID;
	}

	public void setResturantID(String resturantID) {
		this.resturantID = resturantID;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public int getLastFourDigitOfTheCard() {
		return lastFourDigitOfTheCard;
	}

	public void setLastFourDigitOfTheCard(int lastFourDigitOfTheCard) {
		this.lastFourDigitOfTheCard = lastFourDigitOfTheCard;
	}

	public String getPaymentTransactionID() {
		return paymentTransactionID;
	}

	public void setPaymentTransactionID(String paymentTransactionID) {
		this.paymentTransactionID = paymentTransactionID;
	}

	public String getStatusDetails() {
		return statusDetails;
	}

	public void setStatusDetails(String statusDetails) {
		this.statusDetails = statusDetails;
	}

	public String getUserRating() {
		return userRating;
	}

	public void setUserRating(String userRating) {
		this.userRating = userRating;
	}

	public String getUserRatingComments() {
		return userRatingComments;
	}

	public void setUserRatingComments(String userRatingComments) {
		this.userRatingComments = userRatingComments;
	}

}
