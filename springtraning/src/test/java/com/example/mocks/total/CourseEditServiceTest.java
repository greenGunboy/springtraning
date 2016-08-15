package com.example.mocks.total;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.is;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.sql.Date;
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
import mockit.Verifications;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CourseEditServiceTest {

	@Autowired
	private CourseEditService courseEdSvc;
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
		List<SearchCourseInfo> actual = courseEdSvc.getCourseInfo(new SearchForm());
		assertThat(actual, is(new ArrayList<SearchCourseInfo>()));
	}
	
	/**
	 * courseテーブルから講座情報を検索するメソッドの
	 * stateの振り分けテスト（終了）
	 * @throws Exception
	 */
	@Test
	public void testStateEnd() throws Exception {
		new Expectations() {{
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date courseDate = new Date(format.parse("2016-01-01").getTime());
			Date nowDate = new Date(format.parse("2017-01-01").getTime());
			// 検索結果リスト
			ArrayList<SearchCourseInfo> list = new ArrayList<SearchCourseInfo>();
			// 検索結果一件のデータの見立て。ここに講座開催日をセットし、stateを指定する
			SearchCourseInfo searchCourseInfo = new SearchCourseInfo();
			searchCourseInfo.setThedate(courseDate);
			searchCourseInfo.setNowtime(nowDate);
			// 検索結果リストへ追加し、想定結果とする
			list.add(searchCourseInfo);
			mockDao.getCourseInfo((SearchForm)any);
			result = list;
		}};
		List<SearchCourseInfo> list = courseEdSvc.getCourseInfo(new SearchForm());
		for(SearchCourseInfo info : list) {
			assertThat(info.getState(), is("終了"));
		}
	}
	
	/**
	 * courseテーブルから講座情報を検索するメソッドの
	 * stateの振り分けテスト（開催中）
	 * @throws Exception
	 */
	@Test
	public void testStateSession() throws Exception {
		new Expectations() {{
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date courseDate = new Date(format.parse("2016-01-01").getTime());
			Date nowDate = new Date(format.parse("2016-01-01").getTime());
			// 検索結果リスト
			ArrayList<SearchCourseInfo> list = new ArrayList<SearchCourseInfo>();
			// 検索結果一件のデータの見立て。ここに講座開催日をセットし、stateを指定する
			SearchCourseInfo searchCourseInfo = new SearchCourseInfo();
			searchCourseInfo.setThedate(courseDate);
			searchCourseInfo.setNowtime(nowDate);
			// 検索結果リストへ追加し、想定結果とする
			list.add(searchCourseInfo);
			mockDao.getCourseInfo((SearchForm)any);
			result = list;
		}};
		List<SearchCourseInfo> list = courseEdSvc.getCourseInfo(new SearchForm());
		for(SearchCourseInfo info : list) {
			assertThat(info.getState(), is("開催中"));
		}
	}
	
	/**
	 * courseテーブルから講座情報を検索するメソッドの
	 * stateの振り分けテスト（開催予定）
	 * @throws Exception
	 */
	@Test
	public void testStatePlan() throws Exception {
		new Expectations() {{
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			Date courseDate = new Date(format.parse("2017-01-01").getTime());
			Date nowDate = new Date(format.parse("2016-01-01").getTime());
			// 検索結果リスト
			ArrayList<SearchCourseInfo> list = new ArrayList<SearchCourseInfo>();
			// 検索結果一件のデータの見立て。ここに講座開催日をセットし、stateを指定する
			SearchCourseInfo searchCourseInfo = new SearchCourseInfo();
			searchCourseInfo.setThedate(courseDate);
			searchCourseInfo.setNowtime(nowDate);
			// 検索結果リストへ追加し、想定結果とする
			list.add(searchCourseInfo);
			mockDao.getCourseInfo((SearchForm)any);
			result = list;
		}};
		List<SearchCourseInfo> list = courseEdSvc.getCourseInfo(new SearchForm());
		for(SearchCourseInfo info : list) {
			assertThat(info.getState(), is("開催予定"));
		}
	}
	
	
	
	
	
	
	
	/**
	 * 選択講座削除メソッドテスト
	 * 成功時
	 * true.true
	 */
	@Test
	public void testDeleteCourseInfoSuccess() {
		new Expectations() {{
			mockDao.deleteCourseInfoFromcourse_apply((ConfigForm)any);
			result = true;
			mockDao.deleteCourseInfoFromcourse((ConfigForm)any);
			result = true;
		}};
		boolean actual = courseEdSvc.deleteCourseInfo(new ConfigForm());
		assertThat(actual, is(true));
	}
	
	/**
	 * 選択講座削除メソッドテスト失敗時
	 * false.false
	 */
	@Test
	public void testDeleteCourseInfoFailure1() {
		new Expectations() {{
			mockDao.deleteCourseInfoFromcourse_apply((ConfigForm)any);
			result = false;
			mockDao.deleteCourseInfoFromcourse((ConfigForm)any);
			result = false;
		}};
		mockDao.deleteCourseInfoFromcourse_apply(null);
		mockDao.deleteCourseInfoFromcourse(null);
		boolean actual = courseEdSvc.deleteCourseInfo(null);
		assertThat(actual, is(false));
	}
	/**
	 * 選択講座削除メソッドテスト失敗時
	 * true.false
	 */
	@Test
	public void testDeleteCourseInfoFailure2() {
		new Expectations() {{
			mockDao.deleteCourseInfoFromcourse_apply((ConfigForm)any);
			result = true;
			mockDao.deleteCourseInfoFromcourse((ConfigForm)any);
			result = false;
		}};
		mockDao.deleteCourseInfoFromcourse(new ConfigForm());
		boolean actual = courseEdSvc.deleteCourseInfo(null);
		assertThat(actual, is(false));
	}
	/**
	 * 選択講座削除メソッドテスト失敗時
	 * false.true
	 */
	@Test
	public void testDeleteCourseInfoFailure3() {
		new Expectations() {{
			mockDao.deleteCourseInfoFromcourse_apply((ConfigForm)any);
			result = false;
			mockDao.deleteCourseInfoFromcourse((ConfigForm)any);
			result = true;
		}};
		mockDao.deleteCourseInfoFromcourse_apply(new ConfigForm());
		boolean actual = courseEdSvc.deleteCourseInfo(null);
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
		ConfigForm form = new ConfigForm();
		form.setCoursename("coursename");
		boolean actual = courseEdSvc.updateCourseInfo(form);
		assertThat(actual, is(true));
		
		new Verifications() {{
			UpdateCourseInfo copied;
			mockDao.updateCourseInfo(copied = withCapture());
			assertThat(copied.getCoursename(), is(form.getCoursename()));
		}};
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
		boolean actual = courseEdSvc.updateCourseInfo(null);
		assertThat(actual, is(false));
	}
}
