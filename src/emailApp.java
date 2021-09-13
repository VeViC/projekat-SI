
import org.quartz.CronExpression;
import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class emailApp {

	public static void posaljiMejl() {
		
		try {
			JobDetail job1 = JobBuilder.newJob(Job1.class)
					.withIdentity("job1", "group1").build();
			// svakih 7 dana
			Trigger trigger1 = TriggerBuilder.newTrigger()
					.withIdentity("cronTrigger1", "group1")
					.withSchedule(CronScheduleBuilder.cronSchedule("0 0 12 */7 * ?"))
					.build();
			Scheduler scheduler1 = new StdSchedulerFactory().getScheduler();
			scheduler1.start();
			scheduler1.scheduleJob(job1, trigger1);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}