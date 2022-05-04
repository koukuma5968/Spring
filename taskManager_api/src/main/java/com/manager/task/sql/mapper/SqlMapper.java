package com.manager.task.sql.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.manager.task.sql.dto.DataTransferObject;

@Mapper
public interface SqlMapper {

	public List<DataTransferObject> SEL_PROJECT_MNG(DataTransferObject param);

	public int INS_PROJECT_MNG(DataTransferObject data);

	public int INS_TASK_MNG(DataTransferObject data);

	public List<DataTransferObject> SEL_PROCESS_MNG(DataTransferObject param);

	public List<DataTransferObject> SEL_USER_MNG(DataTransferObject user);

	public int INS_USER_MNG(DataTransferObject data);

	public int INS_GROUP_MNG(DataTransferObject data);

	public int INS_GROUP_AUTHORITY(DataTransferObject data);

	public List<DataTransferObject> SEL_GROUP_MNG(DataTransferObject data);

	public int SEL_GROUP_MNG_MAX_ID();

	public List<DataTransferObject> SEL_GROUP_OPTION(DataTransferObject data);

	public List<DataTransferObject> SEL_PROJECT_OPTION(DataTransferObject data);
}
