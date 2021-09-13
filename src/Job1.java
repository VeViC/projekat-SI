import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;


public class Job1 implements Job{ 
	public static String mailAddress;
	public static String petName;
	public static String race;
		public void execute(JobExecutionContext context) throws JobExecutionException {
			System.out.println("Job1 --->>> Time is " + new Date());
			sendEmail.sendEmail(mailAddress, "Vakcinacija", "Potrebno je vakcinisati ljubimca: "+ petName +", rase: "+ race);
			} 
}