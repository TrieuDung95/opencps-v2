package org.opencps.auth.utils;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.DateFormatFactoryUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.TimeZoneUtil;
import com.liferay.portal.kernel.util.Validator;

/**
 * @author binhth
 * DateTimeUtils.class
 */
public class APIDateTimeUtils {

	public static final String _TIMESTAMP = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
	
	public static final String _NORMAL_PARTTERN = "dd/MM/yyyy HH:mm:ss";

	public static String convertDateToString(Date date) {
		return convertDateToString(date, _NORMAL_PARTTERN);
	}

	public static String convertDateToString(Date date, String pattern) {
		DateFormat dateFormat = DateFormatFactoryUtil.getSimpleDateFormat(
			pattern);

		if (Validator.isNull(date) || Validator.isNull(pattern)) {
			return StringPool.BLANK;
		}

		dateFormat.setTimeZone(TimeZoneUtil.getTimeZone("Asia/Ho_Chi_Minh"));

		Calendar calendar = Calendar.getInstance();

		calendar.setTime(date);

		return dateFormat.format(calendar.getTime());
	}

	private static Log _log = LogFactoryUtil.getLog(APIDateTimeUtils.class);

}