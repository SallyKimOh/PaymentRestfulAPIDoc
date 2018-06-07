package com.metaflow.payment.base;

import java.io.IOException;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;

import org.json.simple.JSONObject;

import com.metaflow.payment.config.PropUtil;
import com.metaflow.payment.model.BillPayment.BillPaymentInfo;
import com.metaflow.payment.model.Common.BankInfo;
import com.metaflow.payment.model.Vendor.VendorInfo;


public class BaseMgr {
	
	private final static String url = new PropUtil().getPropValue().getProperty("SERVER_URL");
	private final static String authKey = new PropUtil().getPropValue().getProperty("AUTH_KEY");
	private final static String organizationID = new PropUtil().getPropValue().getProperty("ORGANIZATION_ID");
	private final static String billPath = new PropUtil().getPropValue().getProperty("BILL_PATH");
	private final static String vendorPath = new PropUtil().getPropValue().getProperty("VENDOR_PATH");

	public String getVendorBill(String dueDate) throws IOException {

//		String url = new PropUtil().getPropValue().getProperty("SERVER_URL");
//		String authKey = new PropUtil().getPropValue().getProperty("AUTH_KEY");
//		String organizationID = new PropUtil().getPropValue().getProperty("ORGANIZATION_ID");
//		String billPath = new PropUtil().getPropValue().getProperty("BILL_PATH");
		
		System.out.println(url);
		
		Client client = ClientBuilder.newClient();
		String jsonStr = client
	            .target(url+billPath+"?organization_id="+organizationID+"&due_date="+dueDate)
	            .request(MediaType.APPLICATION_JSON)
	            .header("Authorization", "Zoho-authtoken "+authKey)

	            .get(String.class);		
		
		
		System.out.println(jsonStr);
		return jsonStr;

	}	
/*	
	public BillPaymentInfo getVendorInfo(String contactID) throws IOException {

		Client client = ClientBuilder.newClient();
		String jsonStr = client
				.target(url+vendorPath+contactID+"?organization_id="+organizationID)
	            .request(MediaType.APPLICATION_JSON)
	            .header("Authorization", "Zoho-authtoken "+authKey)
	            .get(String.class);		
		
		
		System.out.println("json:"+jsonStr);
		
		BillPaymentInfo info = new BillPaymentInfo();
		PropUtil util = new PropUtil();
		
		JSONObject jsonObject = util.getJsonObjFromStr(jsonStr);
        Long code = (Long) jsonObject.get("code");
           
        if (code == 0) {
         	JSONObject jsonObjectContact = (JSONObject) jsonObject.get("contact");
           	info.setVendorID((String)jsonObjectContact.get("contact_id"));
           	info.setVendorName((String)jsonObjectContact.get("contact_name"));
           	info.setFinancialNumber((String)jsonObjectContact.get("cf_bank_institution_number"));
           	info.setBranchNumber((String)jsonObjectContact.get("cf_bank_branch_number"));
           	info.setAccountNumber((String)jsonObjectContact.get("cf_bank_account_number"));
        }

		return info;
		
	}		
*/
	
	public VendorInfo getVendorInfo(String contactID) throws IOException {

		Client client = ClientBuilder.newClient();
		String jsonStr = client
				.target(url+vendorPath+contactID+"?organization_id="+organizationID)
	            .request(MediaType.APPLICATION_JSON)
	            .header("Authorization", "Zoho-authtoken "+authKey)
	            .get(String.class);		
		
		
		System.out.println("json:"+jsonStr);
		
		VendorInfo info = new VendorInfo();
		PropUtil util = new PropUtil();
		
		JSONObject jsonObject = util.getJsonObjFromStr(jsonStr);
        Long code = (Long) jsonObject.get("code");
           
        if (code == 0) {
         	JSONObject jsonObjectContact = (JSONObject) jsonObject.get("contact");
           	info.setVendorID((String)jsonObjectContact.get("contact_id"));
           	info.setVendorName((String)jsonObjectContact.get("contact_name"));
           	info.setFinancialNumber((String)jsonObjectContact.get("cf_bank_institution_number"));
           	info.setBranchNumber((String)jsonObjectContact.get("cf_bank_branch_number"));
           	info.setAccountNumber((String)jsonObjectContact.get("cf_bank_account_number"));
        }

		return info;
		
	}		

	public String getVendorID(String billId) throws IOException {

		Client client = ClientBuilder.newClient();
		String jsonStr = client
				.target(url+billPath+"/"+billId+"?organization_id="+organizationID)
	            .request(MediaType.APPLICATION_JSON)
	            .header("Authorization", "Zoho-authtoken "+authKey)
	            .get(String.class);		
		
				
		BillPaymentInfo info = new BillPaymentInfo();
		PropUtil util = new PropUtil();
		
		JSONObject jsonObject = util.getJsonObjFromStr(jsonStr);
        Long code = (Long) jsonObject.get("code");
        
        String vendorID = "";
           
        if (code == 0) {
         	JSONObject jsonObjectContact = (JSONObject) jsonObject.get("bill");
           	info.setFinancialNumber((String)jsonObjectContact.get("vendor_id"));
           	vendorID = (String)jsonObjectContact.get("vendor_id");
//           	info.setBranchNumber((String)jsonObjectContact.get("cf_bank_branch_number"));
//           	info.setAccountNumber((String)jsonObjectContact.get("cf_bank_account_number"));
        }

		return vendorID;
		
	}	
	
	public String getBillDetail(String billId) throws IOException {

		Client client = ClientBuilder.newClient();
		String jsonStr = client
				.target(url+billPath+"/"+billId+"?organization_id="+organizationID)
	            .request(MediaType.APPLICATION_JSON)
	            .header("Authorization", "Zoho-authtoken "+authKey)
	            .get(String.class);		
		
		return jsonStr;
		
	}		
	
	
	
	public static void main(String[] args) throws IOException {
		
		BaseMgr mgr = new BaseMgr();
//		String result = mgr.getVendorBill("2018-05-15");
		
//		System.out.println(result);;
		
		System.out.println("vendorinfo:"+mgr.getVendorInfo("1256987000000092164"));
//		System.out.println("vendorinfo:"+mgr.getBillDetail("1256987000000213644"));
		
	}
}
