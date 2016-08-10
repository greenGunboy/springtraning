package com.example.mocks;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

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
	 * 重複データがある場合
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
	
	/**
	 * 重複メソッド動作テスト
	 * 重複データが無かった場合
	 * @throws Exception
	 */
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
	
}
