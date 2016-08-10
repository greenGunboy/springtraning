package com.example.mocks.course;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.domain.admin.CourseInfo;
import com.example.persistence.course.ApplyDao;
import com.example.service.course.CourseApplyService;
import com.example.web.control.course.ApplicationForm;

import mockit.Expectations;
import mockit.Mocked;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CourseApplyServiceTest {

	@Autowired
	private CourseApplyService sut;
	
	@Mocked
	private ApplyDao mockDao;
	
	/**
	 * 講座情報を取得するメソッドのテスト
	 * @throws Exception
	 */
	@Test
	public void testSerchCourseInfo() throws Exception {
		new Expectations() {{
			mockDao.getCourseInfo();
			result = new ArrayList<CourseInfo>();
		}};
		List<CourseInfo> actual = sut.searchCourseInfo();
		assertThat(actual, is(new ArrayList<CourseInfo>()));
	}
	
	/**
	 * insertメソッドのテスト
	 * DB接続成功時
	 */
	@Test
	public void testInsertApplySuccess() {
		new Expectations() {{
			mockDao.insertApply((ApplicationForm)any);
			result = true;
		}};
		boolean actual = sut.insertApplyInfo(new ApplicationForm());
		assertThat(actual, is(true));
	}
	
	/**
	 * applicationテーブルへ利用者情報を登録するinsertメソッドのテスト
	 * DB接続失敗時
	 */
	@Test
	public void testInsertApplyFailure() {
		new Expectations() {{
			mockDao.insertApply((ApplicationForm)any);
			result = false;
		}};
		boolean actual = sut.insertApplyInfo(null);
		assertThat(actual, is(false));
	}
	
}
