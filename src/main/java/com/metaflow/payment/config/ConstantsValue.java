package com.metaflow.payment.config;

public class ConstantsValue {

	public String PROPERTIES_PATH="com\\metaflow\\zoho\\comm\\config.properties";
    public String[] TRANSACTIONTITLE = { "billPaymentCreditCard", "bill", "billPayment", "paycheck", "deposit", "vendorCredit" };
    public String[] TRANSACTIONlINK = { "/expense", "/billTrans", "/billPayment", "/paycheck", "/deposit", "/vendorCredit" };
//    public String TRANSACTIONTYPE1 = "Transaction";
    public String[] TRANSACTIONTYPE = { "Expense", "Bill", "BillPayment", "Paycheck", "Deposit", "VendorCredit" };
    public String[] JOBTYPE = { "Transaction"};

}
