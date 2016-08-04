package com.example.mocks;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.domain.CourseInfo;
import com.example.persistence.CourseDao;
import com.example.service.CourseResisterService;

import mockit.Expectations;
import mockit.Mocked;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CourseResisterServiceTest {

	// テスト対象
	@Autowired
	private CourseResisterService sut;
	
	// モック化したDao（DBアクセス）
	@Mocked
	private CourseDao mockDao;
	
	 /**
	  * ↓Daoも初期化され後々の処理が意図したモノに動かない
	  * テストが行われる度に最初に呼ばれる処理
	  */
//	@Before
//	public void setUp() throws Exception {
//		sut = new CourseResisterService();
//	}
	
	/**
	 * 重複メソッド動作テスト
	 * @throws Exception
	 */
	@Test
	public void testIsExistCourseno() throws Exception {
		
		new Expectations() {{
			mockDao.isExistCourse(anyString);
			result = 1;
		}};
		boolean actual = sut.isDuplicateCourseno("4444");
		assertThat(actual, is(true));
	}
	
	@Test
	public void testNotExistCourseno() throws Exception {
		
		new Expectations() {{
			mockDao.isExistCourse(anyString);
			result = 0;
		}};
		boolean actual = sut.isDuplicateCourseno("0000");
		assertThat(actual, is(false));
	}

	
	/**
	 * insertメソッドのテスト
	 * DB接続成功時
	 * Exceptionの発生をモック化
	 * 
	 * springの管理下（@Repository）にあるmapperから例外が発生するので
	 * 期待する例外値（SQLException）にならずundeclaredthrowableexceptionが返ってくる
	 * そのためExceptionで対応
	 * 
	 * @throws Exception　例外
	 */
	@Test
	public void testInsertSuccessException() throws Exception {
		
		new Expectations() {{
			mockDao.insertCourseinfo((CourseInfo)any);
			result = true;
		}};
		boolean actual = sut.insertCourseInfo(new CourseInfo());
		assertThat(actual, is(true));
	}
	
	
	/**
	 * insertメソッドのテスト
	 * DB接続失敗時
	 * @throws Exception
	 */
	@Test
	public void testInsertFailureException() throws Exception {
		
		new Expectations() {{
			mockDao.insertCourseinfo((CourseInfo)any);
			result = false;
		}};
		boolean actual = sut.insertCourseInfo(null);
		assertThat(actual, is(false));
	}

//	@Test
//	public void getCourseList() {
//		List<CourseInfo> arg1 = new ArrayList<>();
//		arg1.add(createCourseInfo());
//		arg1.add(createCourseInfo("部品Cd1", "部品Nm1", 100, "1111-11-11", "11:11", "11:11"));
//		arg1.add(createCourseInfo("部品Cd2", "部品Nm2", -10, "2222-22-22", "22:22", "22:22"));
//		
//		// 実測値
//		List<CourseInfo> actual = sut.getDataList(arg1, 0);
//
//		// 期待値
//		List<CourseInfo> expected = new ArrayList<>();
//		expected.add(createCourseInfo());
//		expected.add(createCourseInfo("部品Cd1", "部品Nm1", 100, "1111-11-11", "11:11", "11:11"));
//		expected.add(createCourseInfo("部品Cd2", "部品Nm2", -10, "2222-22-22", "22:22", "22:22"));
//
//		for(int i = 0; i < actual.size(); i++) {
//			assertThat(actual.get(i).getCourseno(), is(expected.get(i).getCourseno()));
//			assertThat(actual.get(i).getCoursename(), is(expected.get(i).getCoursename()));
//			assertThat(actual.get(i).getVacantseats(), is(expected.get(i).getVacantseats()));
//			assertThat(actual.get(i).getThedate(), is(expected.get(i).getThedate()));
//			assertThat(actual.get(i).getStarttime(), is(expected.get(i).getThedate()));
//			assertThat(actual.get(i).getEndtime(), is(expected.get(i).getEndtime()));
//		}
//
//		assertThat(actual.size(), is(3));
//	
//	}
//	
//	private CourseInfo createCourseInfo() {
//		CourseInfo ci = new CourseInfo();
//		ci.setCourseno("TEST");
//		ci.setCoursename("TEST");
//		ci.setVacantseats(1);
//		ci.setThedate("0000-00-00");
//		ci.setStarttime("00:00");
//		ci.setEndtime("00:00");
//		return ci;
//	}
//	
//	private CourseInfo createCourseInfo(String courseno, 
//			String coursename, 
//			Integer vacantseats, 
//			String thedate, 
//			String starttime, 
//			String endtime) {
//		CourseInfo ci = new CourseInfo();
//		ci.setCourseno(courseno);
//		ci.setCoursename(coursename);
//		ci.setVacantseats(vacantseats);
//		ci.setThedate(thedate);
//		ci.setStarttime(starttime);
//		ci.setEndtime(endtime);
//		return ci;
//	}
	
}
