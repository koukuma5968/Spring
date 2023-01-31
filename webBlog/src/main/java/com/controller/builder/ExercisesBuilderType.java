package com.controller.builder;

import java.util.HashMap;

import com.model.sql.dao.ExerciesDao;
import com.model.sql.dto.ExercisesMng;
import com.param.ParamBean;
import com.util.FileUtil;

public enum ExercisesBuilderType implements BuilderInterface {

	EXERCISES("exercisesBean") {
		@Override
		public Object createBody(ParamBean param) {

			ExercisesMng mng = ExerciesDao.getExercisesMng(param);

			String exercises = FileUtil.exercisesPass + mng.getLang() + mng.getPath();
			if (param.getType() == 0) {
				exercises = exercises.concat("problem.html");
			}else {
				exercises = exercises.concat("anser.html");
			}

			HashMap<String, String> exercisesBean = new HashMap<String, String>();

			exercisesBean.put("exercisesHtml", exercises);

			return exercisesBean;
		}
	},
	SIDE("exercisesMenu") {
		@Override
		public Object createBody(ParamBean param) {
			return ExerciesDao.getExercisesMenu(param);
		}
	};

	private ExercisesBuilderType(String key) {
		this.key = key;
	}

	private String key;

	public String getKey() {
		return this.key;
	}

}
