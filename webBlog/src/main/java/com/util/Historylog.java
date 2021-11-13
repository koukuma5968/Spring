package com.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.model.sql.dao.HistoryDao;
import com.param.HistoryBean;

public class Historylog {

	public static final DateTimeFormatter OUTPUT_FORMAT = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
	private static LocalDateTime systemday = LocalDateTime.now();

	public static void setDownloadHistory(String userAgent) {

		LocalDateTime today = LocalDateTime.now();

		if (systemday.getDayOfYear() != today.getDayOfYear()) {
			HistoryDao.updateDownloadSeqReset();
		}

		HistoryDao.updateDownloadSeqIncriment();

		HistoryBean bean = new HistoryBean();

		bean.setDate(today.format(OUTPUT_FORMAT));
		bean.setAgent(userAgent);

		HistoryDao.insertDownloadHistory(bean);
	}

	public static void setAccessHistory(String userAgent, String page) {

		LocalDateTime today = LocalDateTime.now();

		if (systemday.getDayOfYear() != today.getDayOfYear()) {
			HistoryDao.updateAccssesSeqReset();
		}

		HistoryDao.updateAccssesSeqIncriment();

		HistoryBean bean = new HistoryBean();

		bean.setDate(today.format(OUTPUT_FORMAT));
		bean.setPage(page);
		bean.setAgent(userAgent);

		HistoryDao.insertAccssesHistory(bean);

	}
}
