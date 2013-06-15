package leon.ssi.util;

import java.sql.Date;
import java.text.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class StringUtil {
	private static Log logger = LogFactory.getLog(StringUtil.class);

	public static String getString(Object val) {
		String rtnVal = "";
		try {
			rtnVal = (String) val;
			rtnVal = rtnVal.trim();
		} catch (Exception e) {
			rtnVal = "";
		}
		return rtnVal;
	}

	public static Date convertStrToDateByFormat(String dateStr) {
		String inputDateStr = "";
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			inputDateStr = dateStr;
			if (dateStr == null || dateStr.trim().length() < 1) {
				inputDateStr = "1900-01-01";
			}
			java.util.Date d = sf.parse(inputDateStr.toString().trim());
			date = new Date(d.getTime());
		} catch (Exception e) {
			logger.error(
					"convertStrToDateByFormat(" + dateStr + ") error:"
							+ e.getMessage(), e);
		}
		return date;
	}

	public static boolean isEmpty(String s) {
		boolean r=false;
		if (s==null){
			r=true;
		}
		if("".equals(s)){
			r=true;
		}
		return r;
	}
	
}
