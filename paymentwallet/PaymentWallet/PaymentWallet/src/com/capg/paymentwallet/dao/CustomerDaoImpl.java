package com.capg.paymentwallet.dao;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;
import java.util.logging.LoggingPermission;

import com.capg.paymentwallet.bean.CustomerBean;
import com.capg.paymentwallet.bean.DepositBean;
import com.capg.paymentwallet.bean.FundTransferBean;
import com.capg.paymentwallet.bean.WithdrawBean;
import com.capg.paymentwallet.database.CustomerDataBase;
import com.capg.paymentwallet.exception.CustomerException;
import com.capg.paymentwallet.exception.CustomerExceptionMessage;
import com.capg.paymentwallet.service.CustomerServiceImpl;

public class CustomerDaoImpl implements ICustomerDao {

	private static ArrayList<CustomerBean> list1 = new ArrayList<CustomerBean>();
	double amt;
	double amt1;
	double amt2;
	private static ArrayList<String> printList1 = new ArrayList<String>();
	CustomerDataBase customerDatabase = new CustomerDataBase();
	CustomerBean customerbean = new CustomerBean();

	@Override
	public boolean createAccount(CustomerBean customerbean)
			throws CustomerException {

		return list1.add(customerbean);
	}

	@Override
	public double showBalance(BigInteger phoneNo) {

		if (phoneNo.equals(customerDatabase.getCustomerList().get(0).getPhoneNo())) {

			double amt = customerDatabase.getCustomerList().get(0).getBalance();
			System.out.println("show balance"+ customerDatabase.getCustomerList().get(0).getBalance());

		}
		return customerDatabase.getCustomerList().get(0).getBalance();
	}

	@Override
	public double deposit(BigInteger phoneNo, double rupees) {

		DepositBean depositBean = new DepositBean();

		if (phoneNo.equals(customerDatabase.getCustomerList().get(0).getPhoneNo())) {

			amt = customerDatabase.getCustomerList().get(0).getBalance()+ rupees;
			customerDatabase.getCustomerList().get(0).setBalance(amt);
			depositBean.setDepositAmt(amt);
			LocalDate date = LocalDate.now();
			depositBean.setDate(date);
			LocalTime time = LocalTime.now();
			depositBean.setTime(time);

			String msg = "deposited";
			//addTransaction(rupees, msg, date, time);
			
			String p = date + "   " + time + "    " + rupees + "  " + msg;
			printList1.add(p);

			
		}
		
		return customerDatabase.getCustomerList().get(0).getBalance();
	}

	@Override
	public double withdraw(BigInteger phoneNo, double rupees) {
		WithdrawBean withdrawBean = new WithdrawBean();

		if (phoneNo.equals(customerDatabase.getCustomerList().get(0).getPhoneNo())) {

			if (customerDatabase.getCustomerList().get(0).getBalance() > rupees) {

				amt1 = customerDatabase.getCustomerList().get(0).getBalance()- rupees;
				System.out.println(amt1);
				withdrawBean.setWithdrawAmt(amt1);
				LocalDate date = LocalDate.now();
				withdrawBean.setDate(date);
				LocalTime time = LocalTime.now();
				withdrawBean.setTime(time);
				customerDatabase.getCustomerList().get(0).setBalance(amt1);
				String msg = "withdrawl";
				//addTransaction(rupees, msg, date, time);
				String p = date + "   " + time + "    " + rupees + "  " + msg;
				printList1.add(p);

			} else {
				System.out.println("insufficient balance");
			}
			
		}
		return customerDatabase.getCustomerList().get(0).getBalance();

	}

	@Override
	public double fundTransfer(BigInteger phoneNum, double balance,
			BigInteger anotherphoneNum) {

		FundTransferBean fundTransferBean = new FundTransferBean();

		boolean isAdded = false;
		if (phoneNum.equals(customerDatabase.getCustomerList().get(0).getPhoneNo())) {

			fundTransferBean.setFundtransferAmt(balance);
			double amount = withdraw(phoneNum, balance);
			customerDatabase.getCustomerList().get(0).setBalance(amount);
			amt2 = customerDatabase.getCustomerList().get(1).getBalance()+ balance;
			
               

			

		}
		System.out.println("fundTransfer"+ customerDatabase.getCustomerList().get(0).getBalance());
		return customerDatabase.getCustomerList().get(0).getBalance();

	}

	@Override
	public ArrayList<String> addTransaction(double amount, String msg,
			LocalDate date, LocalTime time) {
		// TODO Auto-generated method stub

		
		printTransaction();
		return printList1;
  
	}
	//CustomerDaoImpl
	

	@Override
	public   ArrayList<String> printTransaction() {

		/*Iterator<String> iterator = printList1.iterator();
		// System.out.println("Date       " + "Time        " + "Amount   "
		// + " Operation   ");
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
		}*/
		System.out.println(printList1);
		return printList1;
	}

}
