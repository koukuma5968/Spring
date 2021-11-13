package com.manager.task.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.manager.task.sql.dao.DataAccessObject;
import com.manager.task.sql.dto.DataTransferObject;
import com.manager.task.sql.dto.GroupAuthority;
import com.manager.task.sql.dto.GroupMng;
import com.manager.task.sql.dto.ProjectMng;
import com.manager.task.sql.dto.UserMng;
import com.manager.task.sql.mapper.SqlMapper;
import com.manager.task.util.KeyGenerater;
import com.manager.task.util.RequestCipher;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping(path = "api/", method = RequestMethod.POST)
public class ApiController {

	public ApiController(SqlMapper sqlMapper) {
		DataAccessObject.sqlMapper = sqlMapper;
	}

	@PostMapping("login")
	public HashMap<String, Object> login (
			@RequestHeader("User-Agent") String userAgent, 
			@RequestBody UserMng userMng) {

		HashMap<String, Object> ret = new HashMap<String, Object>();
		ret.put("check", false);

		List<DataTransferObject> userlist = DataAccessObject.getUserMNG(userMng);
		if (userlist != null && userlist.size() != 0) {
			UserMng getUser = (UserMng) userlist.get(0);
			if (userMng.getPass_enc().equals(RequestCipher.decode(getUser.getPass_enc()))) {
				ret.put("check", true);
				ret.put("userdata", getUser);
				List<DataTransferObject> grouplist = DataAccessObject.getGroupOption(getUser);
				
				ret.put("groupoption", grouplist);
			}
		}
		return ret;
	}

	@PostMapping("createuser")
	public int userCreate (
			@RequestHeader("User-Agent") String userAgent, 
			@RequestBody UserMng userMng) {

		List<DataTransferObject> userlist = DataAccessObject.getUserMNG(userMng);
		if (userlist != null && userlist.size() != 0) {
			return 1;
		}

		int grid = KeyGenerater.getGroupId() + 1;
		String gatekey = KeyGenerater.generationGateKey();

		userMng.setGate_key(gatekey);
		userMng.setPass_enc(RequestCipher.encode(userMng.getPass_enc()));
		int ret = DataAccessObject.addUserMNG(userMng);

		GroupMng gm = new GroupMng();
		gm.setId(grid);
		gm.setName("private_group");
		gm.setGate_key(gatekey);
		gm.setScope(0);
		ret = DataAccessObject.addGroupMNG(gm);

		GroupAuthority ga = new GroupAuthority();
		ga.setGroup_id(gm.getId());
		ga.setGate_key(gatekey);
		ret = DataAccessObject.addGroupAuth(ga);

		if (ret == 0) {
			return 2;
		}

		return 0;
	}

	@PostMapping("getproject")
	public List<DataTransferObject> getProjectData (
			@RequestHeader("User-Agent") String userAgent, 
			@RequestBody UserMng userMng) {

		List<DataTransferObject> pm = DataAccessObject.getProjectMNG(userMng);

		return pm;
	}

	@PostMapping("createproject")
	public List<DataTransferObject> createProjectData (
			@RequestHeader("User-Agent") String userAgent, 
			@RequestBody Map<String, HashMap<String, String>> updateParam) {

		ProjectMng uppm = new ProjectMng();
		HashMap<String, String> pmparam = updateParam.get("projectMng");
		uppm.setName(pmparam.get("name"));
		uppm.setStartDay(pmparam.get("startDay").replace("/", "")); 
		uppm.setEndDay(pmparam.get("endDay").replace("/", "")); 
		uppm.setGroup_id(Integer.valueOf(pmparam.get("group_id")));

		DataAccessObject.addProjectMNG(uppm);

		UserMng userMng = new UserMng();
		userMng.setGate_key(updateParam.get("userMng").get("gate_key"));

		List<DataTransferObject> pm = DataAccessObject.getProjectMNG(userMng);

		return pm;
	}

}
