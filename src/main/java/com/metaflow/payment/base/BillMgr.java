package com.metaflow.payment.base;

import java.io.IOException;

import org.json.simple.JSONObject;

import com.metaflow.payment.config.ConstantsValue;
import com.metaflow.payment.config.PropUtil;
import com.metaflow.payment.model.BillPayment.BillPaymentInfo;
import com.metaflow.payment.model.Vendor.VendorInfo;

public class BillMgr {


	public BillPaymentInfo getBillDetailInfo(String billId) {

		PropUtil util = new PropUtil();
		BillPaymentInfo info = new BillPaymentInfo();
		
		BaseMgr mgr = new BaseMgr();
		try {
			String jsonStr = mgr.getBillDetail(billId);
			
			JSONObject jsonObject = util.getJsonObjFromStr(jsonStr);
			
         	JSONObject billObj = (JSONObject) jsonObject.get("bill");

        	info.setBillId((String)billObj.get("bill_id"));
        	info.setTransType(new ConstantsValue().TRANSACTIONTYPE[2]);
        	info.setVendorID((String)billObj.get("vendor_id"));
        	VendorInfo vInfo = mgr.getVendorInfo(info.getVendorID());
        	info.setFinancialNumber(vInfo.getFinancialNumber());
        	info.setBranchNumber(vInfo.getBranchNumber());
        	info.setAccountNumber(vInfo.getAccountNumber());
       		info.setVendorName((String)billObj.get("vendor_name"));
       		info.setAmount((Double)billObj.get("total"));
       		info.setTransDate((String)billObj.get("due_date"));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return info;

		
	}

	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		BillMgr mgr = new BillMgr();
		System.out.println(mgr.getBillDetailInfo("1256987000000213644"));
	}

}
