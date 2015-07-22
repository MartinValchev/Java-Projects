package CowsAndBullsProject;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeCounter {
	static Date dNow;
	static SimpleDateFormat ft;
	public TimeCounter() {
		ft = new SimpleDateFormat ("hh:mm:ss");
		dNow = new Date( );	      
	}

	public static void main(String[] args) throws InterruptedException {
		 Date dNow = new Date( );
	      SimpleDateFormat ft = 
	      new SimpleDateFormat ("hh:mm:ss");
		System.out.println(ft.format(dNow));
		while(true){
			System.out.println(ft.format(dNow));
			Thread.sleep(1000);
			dNow = new Date();
		}
	}
	public String getCounter(){
		String count =ft.format(dNow);
		return count;
	}

}
