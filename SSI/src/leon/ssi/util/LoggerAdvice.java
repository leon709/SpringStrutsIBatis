package leon.ssi.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;

public class LoggerAdvice {
	private final Log logger = LogFactory.getLog(getClass());

	private String getSTime() {
		String startTime = "";
		SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SS");
		try {
			startTime = sim.format(new Date());
		} catch (Exception e) {
			startTime = "0";
		}
		return startTime;
	}

	private String getETime() {
		String endTime = "";
		SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SS");
		try {
			endTime = sim.format(new Date());
		} catch (Exception e) {
			endTime = "0";
		}
		return endTime;
	}

	private long getSpentTime(String sT, String eT) {
		SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SS");
		long spentSecs = 0;
		try {
			// spentSecs = (sim.parse(eT).getTime() - sim.parse(sT).getTime()) /
			// 1000;
			spentSecs = (sim.parse(eT).getTime() - sim.parse(sT).getTime());
			//logger.info("start time=====" + sT + "   endTime=====" + eT);
		} catch (Exception e) {
			spentSecs = 0;
		}
		return spentSecs;
	}

	public void beforeAdvice(String name, int age) throws Throwable {

	}

	public Object aroundAdvice(ProceedingJoinPoint call) throws Throwable {
		String startTime = "";
		String endTime = "";
		long totalTaskTime = 0;
		Object ret = null;
		startTime = this.getSTime();
		try {
			ret = call.proceed();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new Exception(e);
		}
		endTime = this.getETime();
		totalTaskTime = getSpentTime(startTime, endTime);
		logger.debug("execute [" + call.toLongString() + "] spent["
				+ totalTaskTime + "] millsec");
		return ret;
	}
}
