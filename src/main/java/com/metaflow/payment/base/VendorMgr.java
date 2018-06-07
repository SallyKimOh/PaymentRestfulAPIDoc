package com.metaflow.payment.base;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.metaflow.payment.config.ConstantsValue;
import com.metaflow.payment.config.PropUtil;
import com.metaflow.payment.model.BillPayment.BillPaymentInfo;
import com.metaflow.payment.model.Vendor.VendorInfo;

public class VendorMgr {

	public List<BillPaymentInfo> createVendorList(String dueDate) {

		List<BillPaymentInfo> list = new ArrayList<>();
		
		PropUtil util = new PropUtil();
		
		BaseMgr mgr = new BaseMgr();
		try {
			String listStr = mgr.getVendorBill(dueDate);
			JSONObject jsonObject = util.getJsonObjFromStr(listStr);

			JSONArray billList = (JSONArray) jsonObject.get("bills");
            
            System.out.println(billList);;
            Iterator<JSONObject> iterator1 = billList.iterator();
            while (iterator1.hasNext()) {
            	BillPaymentInfo info = new BillPaymentInfo();
        		JSONObject billObj = iterator1.next();
            	
        		info.setBillId((String)billObj.get("bill_id"));
        		info.setTransType(new ConstantsValue().TRANSACTIONTYPE[2]);
        		info.setVendorID((String)billObj.get("vendor_id"));
        		info.setVendorName((String)billObj.get("vendor_name"));
        		info.setAmount((Double)billObj.get("total"));
        		info.setTransDate((String)billObj.get("due_date"));
        		info.setTransType(new ConstantsValue().TRANSACTIONTYPE[2]);

              System.out.println("VendorID:"+billObj.get("vendor_id"));
              System.out.println("info:"+info.getVendorID());
                
        		
              VendorInfo venInfo = mgr.getVendorInfo(info.getVendorID());
        		
        		info.setFinancialNumber(venInfo.getFinancialNumber());
        		info.setBranchNumber(venInfo.getBranchNumber());
        		info.setAccountNumber(venInfo.getAccountNumber());
        		
        		
        		
                System.out.println(info);
//                System.out.println(billObj.get("vendor_id"));
                
                list.add(info);
            }           
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;

		
	}

	public VendorInfo getVendorInfo(String billId) {

//		PropUtil util = new PropUtil();
//		BillPaymentInfo info = new BillPaymentInfo();
		VendorInfo info = new VendorInfo();
		
		BaseMgr mgr = new BaseMgr();
		try {
			String vendorID = mgr.getVendorID(billId);
			info = mgr.getVendorInfo(vendorID); 

//			JSONArray billList = (JSONArray) jsonObject.get("bills");
//            
//            System.out.println(billList);;
//            Iterator<JSONObject> iterator1 = billList.iterator();
//            while (iterator1.hasNext()) {
//            	BillPaymentInfo info = new BillPaymentInfo();
//        		JSONObject billObj = iterator1.next();
//            	
//        		info.setBillId((String)billObj.get("bill_id"));
//        		info.setTransType(new ConstantsValue().TRANSACTIONTYPE[2]);
//        		info.setVendorID((String)billObj.get("vendor_id"));
//        		info.setVendorName((String)billObj.get("vendor_name"));
//        		info.setAmount((Double)billObj.get("total"));
//        		info.setTransDate((String)billObj.get("due_date"));
//        		info.setTransType(new ConstantsValue().TRANSACTIONTYPE[2]);
//
//              System.out.println("VendorID:"+billObj.get("vendor_id"));
//              System.out.println("info:"+info.getVendorID());
//                
//        		
//              BillPaymentInfo payInfo = mgr.getVendorInfo(info.getVendorID());
//        		
//        		info.setFinancialNumber(payInfo.getFinancialNumber());
//        		info.setBranchNumber(payInfo.getBranchNumber());
//        		info.setAccountNumber(payInfo.getAccountNumber());
//        		
//        		
//        		
//                System.out.println(info);
////                System.out.println(billObj.get("vendor_id"));
//                
//                list.add(info);
//            }           
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return info;

		
	}

	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		VendorMgr mgr = new VendorMgr();
//		List<BillPaymentInfo> list = mgr.createVendorList("2018-05-15");
//		System.out.println(list);
		System.out.println(mgr.getVendorInfo("1256987000000213644"));
	}

}
