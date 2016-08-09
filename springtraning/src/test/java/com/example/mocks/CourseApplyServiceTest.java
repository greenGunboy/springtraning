package com.example.mocks;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.domain.CourseInfo;
import com.example.persistence.ApplyDao;
import com.example.service.CourseApplyService;
import com.example.web.control.ApplicationForm;

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
		List<CourseInfo> actual = sut.serchCourseInfo();
		assertThat(actual, is(new ArrayList<CourseInfo>()));
	}
	
	/**
	 * applicationテーブルへ利用者情報を登録するinsertメソッドのテスト
	 * DB接続成功時
	 */
	@Test
	public void testInsertApplySuccess() {
		new Expectations() {{
			mockDao.insertApply((ApplicationForm)any);
			result = true;
		}};
		boolean actual = sut.insertApply(new ApplicationForm());
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
		boolean actual = sut.insertApply(null);
		assertThat(actual, is(false));
	}
	
	/**
	 * insertされたIDを取得するメソッドのテスト
	 * @throws Exception
	 */
	@Test
	public void testSerchLastInsertId() throws Exception {
		new Expectations() {{
			mockDao.lastInsertId();
			result = anyString;
		}};
		String actual = sut.lastInsertId();
		assertThat(actual, is(""));
	}
	
	/**
	 * course_applyテーブルへ希望講座を登録するinsertメソッドのテスト
	 * DB接続成功時
	 */
	@Test
	public void testInsertCourseApplySuccess() {
		new Expectations() {{
			mockDao.insertCourseApply(anyString, (String[])any);
			result = true;
		}};
		String[] any = {"6565", "0098"};
		boolean actual = sut.insertCourseApply("22", any);
		assertThat(actual, is(true));
	}
	
	/**
	 * course_applyテーブルへ希望講座を登録するinsertメソッドのテスト
	 * DB接続失敗時
	 */
	@Test
	public void testInsertCourseApplyFailure() {
		new Expectations() {{
			mockDao.insertCourseApply(anyString, (String[])any);
			result = false;
		}};
		boolean actual = sut.insertCourseApply(null, null);
		assertThat(actual, is(false));
	}
}
