package com.example.mocks.total;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.domain.total.SearchCourseInfo;
import com.example.domain.total.UpdateCourseInfo;
import com.example.persistence.total.ConfigDao;
import com.example.service.total.CourseEditService;
import com.example.web.control.total.ConfigForm;
import com.example.web.control.total.SearchForm;

import mockit.Expectations;
import mockit.Mocked;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CourseEditServiceTest {

	@Autowired
	private CourseEditService sut;
	@Mocked
	private ConfigDao mockDao;
	
	/**
	 * courseテーブルから講座情報検索メソッドテスト
	 * @throws Exception
	 */
	@Test
	public void testGetCourseInfo() throws Exception {
		new Expectations() {{
			mockDao.getCourseInfo((SearchForm)any);
			result = new ArrayList<SearchCourseInfo>();
		}};
		List<SearchCourseInfo> actual = sut.getCourseInfo(new SearchForm());
		assertThat(actual, is(new ArrayList<SearchForm>()));
	}
	
	/**
	 * 選択講座削除メソッドテスト
	 * 成功時
	 */
	@Test
	public void testDeleteCourseInfoSuccess() {
		new Expectations() {{
			mockDao.deleteCourseInfoFromcourse_apply((ConfigForm)any);
			result = true;
		}};
		boolean actual = sut.deleteCourseInfo(new ConfigForm());
		assertThat(actual, is(true));
	}
	
	/**
	 * 選択講座削除メソッドテスト
	 * 失敗時
	 */
	@Test
	public void testDeleteCourseInfoFailure() {
		new Expectations() {{
			mockDao.deleteCourseInfoFromcourse_apply((ConfigForm)any);
			mockDao.deleteCourseInfoFromcourse((ConfigForm)any);
			result = false;
		}};
		boolean actual = sut.deleteCourseInfo(null);
		assertThat(actual, is(false));
	}
	
	/**
	 * 選択講座修正メソッドテスト
	 * 成功時
	 */
	@Test
	public void testUpdateCourseInfoSuccess() {
		new Expectations() {{
			mockDao.updateCourseInfo((UpdateCourseInfo)any);
			result = true;
		}};
		boolean actual = sut.updateCourseInfo(new ConfigForm());
		assertThat(actual, is(true));
	}
	
	/**
	 * 選択講座修正メソッドテスト
	 * 失敗時
	 */
	@Test
	public void testUpdateCourseInfoFailure() {
		new Expectations() {{
			mockDao.updateCourseInfo((UpdateCourseInfo)any);
			result = false;
		}};
		boolean actual = sut.updateCourseInfo(null);
		assertThat(actual, is(false));
	}
}
