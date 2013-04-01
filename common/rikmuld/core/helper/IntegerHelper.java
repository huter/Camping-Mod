package rikmuld.core.helper;

public class IntegerHelper {

	public static int getLimitedNumber(int number, int sum, int limit)
	{
		int returnNum = 0;
		returnNum = number+sum;
		while(returnNum>limit) returnNum -= limit;
		while(returnNum<0) returnNum += limit;
		return returnNum;
	}
}
