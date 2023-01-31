package com.model.sql.dao;

import java.util.List;

import com.model.sql.dto.ExercisesMng;
import com.param.ParamBean;

public class ExerciesDao extends DaoBase {

	public static ExercisesMng getExercisesMng(ParamBean param) {
		return articleMapper.SEL_EXERCISES_MNG(param);
	}

	public static List<ExercisesMng> getExercisesMenu(ParamBean param) {
		return articleMapper.SEL_EXERCISES_MENU(param);
	}

	public static List<ExercisesMng> getExercisesList() {
		return articleMapper.SEL_EXERCISES_LIST();
	}

}
