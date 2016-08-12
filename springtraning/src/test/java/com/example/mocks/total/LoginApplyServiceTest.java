package com.example.mocks.total;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.persistence.total.LoginDao;
import com.example.service.total.LoginApplyService;
import com.example.web.control.total.LoginForm;

import mockit.Expectations;
import mockit.Mocked;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LoginApplyServiceTest {

	@Autowired
	private LoginApplyService sut;
	
	@Mocked
	private LoginDao mockDao;
	
	/**
	 * ログインデータのinsertメソッドテスト
	 * 成功時
	 */
	@Test
	public void testInsertLoginInfoSuccess() {
		new Expectations() {{
			mockDao.insertLoginInfo((LoginForm)any);
			result = true;
		}};
		boolean actual = sut.insertLoginInfo(new LoginForm());
		assertThat(actual, is(true));
	}
	
	/**
	 * ログインデータのinsertメソッドテスト
	 * 失敗時
	 */
	@Test
	public void testInsertLoginInfoFailure() {
		new Expectations() {{
			mockDao.insertLoginInfo((LoginForm)any);
			result = false;
		}};
		boolean actual = sut.insertLoginInfo(null);
		assertThat(actual, is(false));
	}
	
	@Test
	public void testGetUserInfoSuccess() {
		new Expectations() {{
			mockDao.getUserInfo((LoginForm)any);
			result = 1;
		}};
		int actual = sut.getUserInfo(new LoginForm());
		assertThat(actual, is(1));
	}
	
	@Test
	public void testGetUserInfoFailure() {
		new Expectations() {{
			mockDao.getUserInfo((LoginForm)any);
			result = -1;
		}};
		int actual = sut.getUserInfo(null);
		assertThat(actual, is(-1));
	}
	
	@Test
	public void testSearchIsExistUserIDSuccess() {
		new Expectations() {{
			mockDao.searchIsExistUserID((LoginForm)any);
			result = true;
		}};
		boolean actual = sut.searchIsExistUserID(new LoginForm());
		assertThat(actual, is(true));
	}
	
	@Test
	public void testSearchIsExistUserIDFailure() {
		new Expectations() {{
			mockDao.searchIsExistUserID((LoginForm)any);
			result = false;
		}};
		boolean actual = sut.searchIsExistUserID(null);
		assertThat(actual, is(false));
	}
	
}
