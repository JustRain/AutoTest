package execute;

import com.ipinyou.pub.DBConn;

public class ChooseCase {

	
	public static void choose(int i){
		String caseid = null;
		switch(i)
		{
		case 1:
	     	caseid = "\\dspall\\dspall.xml";
	     	break;
		case 2:
		    caseid = "\\BatchUpload\\BatchAll\\batchall.xml";
		    break;
		case 3:
		    caseid = "\\mediummanager\\mediumall.xml";
		    break;
		case 4:
		    caseid = "\\projectmannage\\projectall.xml";
		    break;
		case 5:
		    caseid = "\\selfadvertiser\\selfall.xml";
		}
		ChanCase.edit(caseid);
	}
	public static void getid(){
		int a = DBConn.getcaseid();
		choose(a);
	}
	public static void main(String args[]){
		//choose(i);
		getid();
		
	}
}
