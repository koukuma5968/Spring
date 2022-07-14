package com.manager.task.sql.dao;

import java.util.List;

import com.manager.task.sql.dto.DataTransferObject;
import com.manager.task.sql.mapper.SqlMapper;

public class DataAccessObject {

	public static SqlMapper sqlMapper = null;

	public static List<DataTransferObject> getProjectMNG(DataTransferObject param) {
		return sqlMapper.SEL_PROJECT_MNG(param);
	}

	public static int addProjectMNG(DataTransferObject data) {
		return sqlMapper.INS_PROJECT_MNG(data);
	}

	public static List<DataTransferObject> getTaskMNG(DataTransferObject param) {
		return sqlMapper.SEL_TASK_MNG(param);
	}

	public static int addTaskMNG(DataTransferObject data) {
		return sqlMapper.INS_TASK_MNG(data);
	}

	public static List<DataTransferObject> getProcessMNG(DataTransferObject param) {
		return sqlMapper.SEL_PROCESS_MNG(param);
	}

	public static List<DataTransferObject> getUserMNG(DataTransferObject user) {
		return sqlMapper.SEL_USER_MNG(user);
	}

	public static int addUserMNG(DataTransferObject data) {
		return sqlMapper.INS_USER_MNG(data);
	}

	public static List<DataTransferObject> getGroupMNG(DataTransferObject data) {
		return sqlMapper.SEL_GROUP_MNG(data);
	}

	public static int addGroupMNG(DataTransferObject data) {
		return sqlMapper.INS_GROUP_MNG(data);
	}

	public static int addGroupAuth(DataTransferObject data) {
		return sqlMapper.INS_GROUP_AUTHORITY(data);
	}

	public static int getGroupMNG_MaxID() {
		return sqlMapper.SEL_GROUP_MNG_MAX_ID();
	}

	public static List<DataTransferObject> getGroupOption(DataTransferObject data) {
		return sqlMapper.SEL_GROUP_OPTION(data);
	}

	public static List<DataTransferObject> getProjectOption(DataTransferObject data) {
		return sqlMapper.SEL_PROJECT_OPTION(data);
	}

	public static int updateTaskStatus(DataTransferObject data) {
		return sqlMapper.UPD_TASK_STATUS(data);
	}

}
