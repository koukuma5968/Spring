package com.util;

import com.param.AgentType;

public class UserAgentType {

	public static boolean checkMobile(String agent) {

		for (AgentType type : AgentType.values()) {
			if (agent.indexOf(type.name()) > 0) {
				return true;
			}
		}
		return false;
	}
}
