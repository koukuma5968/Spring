package com.model.sql.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.model.sql.dto.ArticleMNG;
import com.model.sql.dto.CategoryMng;
import com.model.sql.dto.ContentMng;
import com.model.sql.dto.LanguageMng;
import com.model.sql.dto.SideLink;
import com.param.HistoryBean;
import com.param.ParamBean;

@Mapper
public interface ArticleMapper {

	public List<ArticleMNG> SEL_ARTILCE_MNG(ParamBean param);

	public List<SideLink> SEL_SIDE_LINK(ParamBean param);

	public List<CategoryMng> SEL_CATEGORY_MNG();

	public List<LanguageMng> SEL_LANGUAGE_MNG();

	public ArticleMNG SEL_ARTILCE_MNG_NAME(ParamBean param);

	public CategoryMng SEL_CATEGORY_MNG_NAME(ParamBean param);

	public LanguageMng SEL_LANGUAGE_MNG_NAME(ParamBean param);

	public List<ArticleMNG> SEL_ARCHIVE_LIST();

	public List<ContentMng> SEL_CONTENT_MNG();

	public int UPD_CONTENT_DL_SEQ_RESET();

	public int UPD_CONTENT_DL_SEQ_INCRIMENT();

	public int INS_DL_HISTORY(HistoryBean bean);

	public int UPD_ACCSSES_HIST_SEQ_RESET();

	public int UPD_ACCSSES_HIST_SEQ_INCRIMENT();

	public int INS_ACCSSES_HISTORY(HistoryBean bean);
}
