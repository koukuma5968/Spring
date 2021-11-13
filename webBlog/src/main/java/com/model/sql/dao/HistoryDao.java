package com.model.sql.dao;

import com.param.HistoryBean;

public class HistoryDao extends DaoBase {

	public static int updateDownloadSeqReset() {
		return articleMapper.UPD_CONTENT_DL_SEQ_RESET();
	}

	public static int updateDownloadSeqIncriment() {
		return articleMapper.UPD_CONTENT_DL_SEQ_INCRIMENT();
	}

	public static int insertDownloadHistory(HistoryBean bean) {
		return articleMapper.INS_DL_HISTORY(bean);
	}

	public static int updateAccssesSeqReset() {
		return articleMapper.UPD_ACCSSES_HIST_SEQ_RESET();
	}

	public static int updateAccssesSeqIncriment() {
		return articleMapper.UPD_ACCSSES_HIST_SEQ_INCRIMENT();
	}

	public static int insertAccssesHistory(HistoryBean bean) {
		return articleMapper.INS_ACCSSES_HISTORY(bean);
	}

}
