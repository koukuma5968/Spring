package com.model.sql.dao;

import java.util.List;

import com.model.sql.dto.SideLink;
import com.param.ParamBean;

public class SideLinkDao extends DaoBase {

	public static List<SideLink> SEL_SIDE_LINK(ParamBean param) {
		return articleMapper.SEL_SIDE_LINK(param);
	}
}
