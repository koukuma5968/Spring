package com.manager.task.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.manager.task.sql.dao.DataAccessObject;
import com.manager.task.sql.dto.DataTransferObject;
import com.manager.task.sql.dto.DownchartBean;
import com.manager.task.sql.dto.GroupAuthority;
import com.manager.task.sql.dto.GroupMng;
import com.manager.task.sql.dto.ProjectMng;
import com.manager.task.sql.dto.TaskMng;
import com.manager.task.sql.dto.UserMng;
import com.manager.task.sql.mapper.SqlMapper;
import com.manager.task.util.KeyGenerater;
import com.manager.task.util.RequestCipher;
import com.manager.task.util.constant.TASK_KEYS;

//@CrossOrigin(origins = "http://scaguih.clear-net.jp")
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
	public Map<String, List<DataTransferObject>> getProjectData (
			@RequestHeader("User-Agent") String userAgent, 
			@RequestBody UserMng userMng) {

		List<DataTransferObject> pm = DataAccessObject.getProjectMNG(userMng);
		List<DataTransferObject> pmop = DataAccessObject.getProjectOption(userMng);
		pm.forEach(project -> {
			ProjectMng p = (ProjectMng) project;

			List<DataTransferObject> task = DataAccessObject.getTaskMNG(project);
			p.setTask_count(task.size());
			p.setTask_remain_count((int) task.stream().filter(ob -> {
				TaskMng tm = (TaskMng) ob;
				return tm.getStatus() != 3;
			}).count());

			LinkedHashMap<String, List<DataTransferObject>> taskMap = new LinkedHashMap<String, List<DataTransferObject>>();
			setTaskMap(TASK_KEYS.INCOMPATIBLE, taskMap, task);
			setTaskMap(TASK_KEYS.WORKING, taskMap, task);
			setTaskMap(TASK_KEYS.CONFIRMING, taskMap, task);
			setTaskMap(TASK_KEYS.COMPLETED, taskMap, task);
			p.setTask(taskMap);

			String sYear = p.getStartDay().substring(0, 4);
			String sMonth = p.getStartDay().substring(5, 7);
			String sDay = p.getStartDay().substring(8, 10);
			String eYear = p.getEndDay().substring(0, 4);
			String eMonth = p.getEndDay().substring(5, 7);
			String eDay = p.getEndDay().substring(8, 10);

			LocalDate aDate = LocalDate.of(Integer.parseInt(sYear), Integer.parseInt(sMonth), Integer.parseInt(sDay));

			int year = aDate.getYear();
			int month = aDate.getMonthValue();
			int day = aDate.getDayOfMonth();
			int lastDay = aDate.lengthOfMonth();

			int taskSum = 0;
			int expectedNum = 0;
			int resultNum = 0;
			LinkedList<DownchartBean> dcList = new LinkedList<DownchartBean>();
			boolean brFlg = false;
			for (; day <= lastDay;) {
				if (brFlg) {
					break;
				}
				if (year == Integer.parseInt(eYear) && month == Integer.parseInt(eMonth) && day == Integer.parseInt(eDay)) {
					brFlg = true;
				}
				DownchartBean dcBean = new DownchartBean();
				StringBuffer yyyymmdd = new StringBuffer()
						.append(aDate.getYear())
						.append("/")
						.append(String.format("%02d", aDate.getMonthValue()))
						.append("/")
						.append(String.format("%02d", aDate.getDayOfMonth()));
				dcBean.setDay(yyyymmdd.toString());
				dcList.add(dcBean);
				aDate = aDate.plusDays(1);
				year = aDate.getYear();
				month = aDate.getMonthValue();
				day = aDate.getDayOfMonth();

				for (DataTransferObject t : task) {
					TaskMng tm = (TaskMng) t;
					dcBean.setTaskSum(dcBean.getTaskSum());

					if (yyyymmdd.toString().replace("/", "").equals(tm.getStartDay())) {
						taskSum++;
						expectedNum++;
						resultNum++;
					}
					if (yyyymmdd.toString().replace("/", "").equals(tm.getEndDay())) {
						expectedNum--;
					}
					if (yyyymmdd.toString().replace("/", "").equals(tm.getModDate()) && tm.getStatus() == TASK_KEYS.COMPLETED.getStatus()) {
						resultNum--;
					}
				}
				dcBean.setTaskSum(taskSum);
				dcBean.setExpectedNum(expectedNum);
				dcBean.setResultNum(resultNum);
			}
			p.setDownchartList(dcList);

		});

		Map<String, List<DataTransferObject>> ret = new HashMap<String, List<DataTransferObject>>();
		ret.put("list", pm);
		ret.put("option", pmop);

		return ret;
	}

	@PostMapping("createproject")
	public Map<String, List<DataTransferObject>> createProjectData (
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

		return getProjectData(userAgent, userMng);
	}

	@PostMapping("createtask")
	public Map<String, List<DataTransferObject>> createTaskData (
			@RequestHeader("User-Agent") String userAgent, 
			@RequestBody Map<String, HashMap<String, String>> updateParam) {

		TaskMng uppm = new TaskMng();
		HashMap<String, String> pmparam = updateParam.get("taskMng");
		uppm.setName(pmparam.get("name"));
		uppm.setStartDay(pmparam.get("startDay").replace("/", "")); 
		uppm.setEndDay(pmparam.get("endDay").replace("/", "")); 
		uppm.setProject_id(Integer.valueOf(pmparam.get("project_id")));
		uppm.setProc(Integer.valueOf(pmparam.get("procsel")));

		DataAccessObject.addTaskMNG(uppm);

		UserMng userMng = new UserMng();
		userMng.setGate_key(updateParam.get("userMng").get("gate_key"));

		return getProjectData(userAgent, userMng);
	}

	@SuppressWarnings("unchecked")
	@PostMapping("updateTaskStatus")
	public boolean updateTaskStatus (
			@RequestHeader("User-Agent") String userAgent, 
			@RequestBody HashMap<String, Object> taskparam) {

		String statusType = (String) taskparam.get("statusType");
		List<LinkedHashMap<String, Object>> taskList = (List<LinkedHashMap<String, Object>>) taskparam.get("task");
		taskList.stream().forEach(action -> {
			TaskMng tm = new TaskMng();
			tm.setId((int) action.get("id"));
			tm.setProject_id((int) action.get("project_id"));
			tm.setStatus(TASK_KEYS.valueOf(statusType.toUpperCase()).getStatus());
			DataAccessObject.updateTaskStatus(tm);
		});
		return true;
	}

	private void setTaskMap(TASK_KEYS keys, LinkedHashMap<String, List<DataTransferObject>> taskMap, List<DataTransferObject> task) {

		List<DataTransferObject> taskList = new ArrayList<DataTransferObject>();
		task.stream().filter(predicate -> {
			TaskMng tm = (TaskMng) predicate;
			return tm.getStatus() == keys.getStatus();
		}).forEach(action -> {
			taskList.add(action);
		});
		taskMap.put(keys.name().toLowerCase(), taskList);

	}
}
