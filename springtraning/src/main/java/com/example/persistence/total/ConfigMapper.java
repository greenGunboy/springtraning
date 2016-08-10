package com.example.persistence.total;

import java.util.List;

import com.example.domain.admin.CourseInfo;
import com.example.web.control.total.ConfigForm;

public interface ConfigMapper {

	public List<CourseInfo> getCourseInfo(ConfigForm form);
}
