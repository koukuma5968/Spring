package com.manager.task.util;

import java.util.UUID;

import com.manager.task.sql.dao.DataAccessObject;

public class KeyGenerater {

	public static String generationGateKey() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString();
	}

	public static int getGroupId() {
		return DataAccessObject.getGroupMNG_MaxID();
	}
}
