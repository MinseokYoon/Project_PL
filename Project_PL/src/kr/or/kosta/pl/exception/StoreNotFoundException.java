package kr.or.kosta.pl.exception;

public class StoreNotFoundException extends Exception{
	public StoreNotFoundException(){}
	
	public StoreNotFoundException(String message){
		super(message);
	}
}
