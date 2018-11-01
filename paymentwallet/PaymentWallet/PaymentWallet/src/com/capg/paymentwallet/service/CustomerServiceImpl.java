package com.capg.paymentwallet.service;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import com.capg.paymentwallet.bean.CustomerBean;
import com.capg.paymentwallet.bean.DepositBean;
import com.capg.paymentwallet.dao.CustomerDaoImpl;
import com.capg.paymentwallet.dao.ICustomerDao;
import com.capg.paymentwallet.exception.CustomerException;
import com.capg.paymentwallet.exception.CustomerExceptionMessage;

public class CustomerServiceImpl implements ICustomerService {
    ICustomerDao dao = new CustomerDaoImpl();
    CustomerBean customerbean = new CustomerBean();
	@Override
	public boolean createAccount(CustomerBean customerbean) throws CustomerException  {
		// TODO Auto-generated method stub
		validateCustomer(customerbean);
		return dao.createAccount(customerbean);
	}

	@Override
	public double showBalance(BigInteger phoneNo)  {
		// TODO Auto-generated method stub
		
//		validateShowBalance(phoneNo);
		return dao.showBalance(phoneNo);
	}

	@Override
	public double deposit(BigInteger phoneNo,double rupees )  {
		// TODO Auto-generated method stub
		  
//		validateDeposit(rupees, phoneNo);
		return dao.deposit(phoneNo, rupees);
		
	}

	@Override
	public double withdraw(BigInteger phoneNo, double rupees)  {
		// TODO Auto-generated method stub
//		validateWithdraw(rupees, phoneNo);
		return dao.withdraw(phoneNo, rupees);
	}

	@Override
	public double fundTransfer(BigInteger phoneNo,double balance,BigInteger phnum)  {
		// TODO Auto-generated method stub
//		validateFundTransfer(balance, phnum);
		return dao.fundTransfer(phoneNo,balance,phnum);
	}

	@Override
	public ArrayList<String> addTransaction(double amount, String msg,LocalDate date,LocalTime time) {
		// TODO Auto-generated method stub
		return dao.addTransaction(amount, msg,date,time);
	}

	@Override
	public ArrayList<String> printTransaction() {
		// TODO Auto-generated method stub
		
		return dao.printTransaction();
	}

	
	
	public boolean validateCustomer(CustomerBean customer) throws CustomerException {
		boolean isValid=true;
		
	if(!( customer.getFirstName().matches("[a-zA-Z]{3,}")))
	{
		isValid=false;
		throw new CustomerException(CustomerExceptionMessage.ERROR1);
	}
	if(!( customer.getLastName().matches("[a-zA-Z]{3,}")))
	{
		isValid=false;
		throw new CustomerException(CustomerExceptionMessage.ERROR2);
	}
	if(!(customer.getPhoneNo().toString().matches("^[6-9][0-9]{9}"))){
		
		isValid=false;
		throw new CustomerException(CustomerExceptionMessage.ERROR5);
	}
	if((customer.getEmailId()== null || !(customer.getEmailId().matches("[a-zA-Z][a-zA-z0-9-.]*@[a-zA-Z0-9]+([.][a-zA-Z)]+)+")))){

		isValid=false;
		throw new CustomerException(CustomerExceptionMessage.ERROR3);
	}
	if((customer.getPanNum()==null) || (!(customer.getPanNum().matches("^[A-Z][A-Z0-9]{9}")))){
		
		isValid=false;
		throw new CustomerException(CustomerExceptionMessage.ERROR4);
	}
	if((customer.getAddress()==null)||(!(customer.getAddress().matches("[A-Za-z]{5,50}"))))
	{
		isValid=false;
		throw new CustomerException(CustomerExceptionMessage.ERROR7);
	}
	if(customer.getBalance()==0||!(customer.getBalance()>0)){
		isValid=false;
		throw new CustomerException(CustomerExceptionMessage.ERROR6);
 
	}
	
		return isValid;
		
		
	}
	public boolean validateShowBalance(BigInteger phoneNo) throws Exception
	{
		boolean isValid = false;
		if(!(phoneNo.toString().matches("^[6-9][0-9]{9}"))){
			
			isValid=true;
			throw new CustomerException(CustomerExceptionMessage.ERROR5);
		}
		return isValid;
		
	}
	public boolean validateDeposit(double amount,BigInteger phoneNo) throws CustomerException {
		boolean isValid = true;
		//DepositBean depositBean = new DepositBean();
		if(!(phoneNo.toString().matches("^[6-9][0-9]{9}"))){
			
			isValid=false;
			throw new CustomerException(CustomerExceptionMessage.ERROR5);
		}
		if(amount==0||!(amount>0)){
			isValid=false;
			throw new CustomerException(CustomerExceptionMessage.ERROR6);
	 
		}
		
		return isValid;
		
	}
	public boolean validateWithdraw(double amount,BigInteger phoneNo) throws CustomerException {
		boolean isValid = true;
		if(!(phoneNo.toString().matches("^[6-9][0-9]{9}"))){
			
			isValid=false;
			throw new CustomerException(CustomerExceptionMessage.ERROR5);
		}
		if(amount==0||!(amount>0)){
			isValid=false;
			throw new CustomerException(CustomerExceptionMessage.ERROR6);
	 
		}
		
		return isValid;
	}
	
	public boolean validateFundTransfer(double amount,BigInteger anotherPhoneNo) throws Exception{
		boolean isValid = true;
		if(!(anotherPhoneNo.toString().matches("^[6-9][0-9]{9}"))){
			
			isValid=false;
			throw new CustomerException(CustomerExceptionMessage.ERROR5);
		}
		if(amount==0||(!(amount>0))){
			isValid=false;
			throw new CustomerException(CustomerExceptionMessage.ERROR6);
	 
		}
		
		return isValid;
	}
	
	
		
	}


	
	
    

		
		
			
	


