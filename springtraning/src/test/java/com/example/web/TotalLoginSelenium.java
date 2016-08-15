package com.example.web;

import static org.junit.Assert.fail;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TotalLoginSelenium {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    baseUrl = "http://localhost:8080/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  /**
   * 全未入力で「ログイン」ボタン押下時のエラーチェック確認後、
   * ユーザ登録するまでの処理
   * @throws Exception
   */
  @Test
  public void testTotalLoginComplete() throws Exception {
    driver.get(baseUrl + "/total/login");
    
    // トップページをエビデンス
    File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(file, new File("totalCapture/complete/toppage.png"));
    
    // 「ログイン」ボタン押下（未入力の為エラー。total/login.htmlへ遷移）
    driver.findElement(By.name("login")).click();
    
    // エラーメッセージ表示画面をエビデンス
    file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(file, new File("totalCapture/complete/error.png"));
    
    // 「登録」ボタン押下（total/logininput.htmlへ遷移）
    driver.findElement(By.name("register")).click();
    
    // ユーザ登録画面をエビデンス
    file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(file, new File("totalCapture/complete/logininput.png"));
    
    // 「ユーザID」に値を入力
    driver.findElement(By.id("userid")).clear();
    driver.findElement(By.id("userid")).sendKeys("tsubasa");
    
    // 「ID重複チェック」ボタン押下
    driver.findElement(By.name("check")).click();
    
    // 「パスワード」に値を入力
    driver.findElement(By.id("passwd")).clear();
    driver.findElement(By.id("passwd")).sendKeys("tsubasa");
    
    // ユーザ登録情報入力完了画面をエビデンス
    file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(file, new File("totalCapture/complete/logininputFill.png"));
    
    // 「確認」ボタン押下（total/loginconf.htmlへ遷移）
    driver.findElement(By.name("confirm")).click();
    
    // ユーザ登録確認画面をエビデンス
    file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(file, new File("totalCapture/complete/loginconf.png"));
    
    // 「登録」ボタン押下（total/loginend.htmlへ遷移）
    driver.findElement(By.cssSelector("input.btn")).click();
    
    // ユーザ登録完了画面をエビデンス
    file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(file, new File("totalCapture/complete/loginend.png"));
  }
  
  /**
   * エラーメッセージ表示確認
   * "「ユーザID」と「パスワード」は必須項目です"
   * @throws Exception
   */
  @Test
  public void testRequiredLoginID() throws Exception {
    driver.get(baseUrl + "/total/login");
    
    // トップページをエビデンス
    File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(file, new File("totalCapture/testRequiredLoginID/toppage.png"));
    
    // 「ユーザID」は未入力
    driver.findElement(By.id("userid")).clear();
    driver.findElement(By.id("userid")).sendKeys("");
    
    // 「パスワード」に値を入力
    driver.findElement(By.id("passwd")).clear();
    driver.findElement(By.id("passwd")).sendKeys("tsubasa");
    
    // ユーザログイン情報入力完了画面をエビデンス
    file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(file, new File("totalCapture/testRequiredLoginID/loginFill.png"));
    
    // 「ログイン」ボタン押下（「ユーザID」未入力の為エラー。total/login.htmlへ遷移）
    driver.findElement(By.name("login")).click();
    
    // エラーメッセージ表示画面をエビデンス
    file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(file, new File("totalCapture/testRequiredLoginID/error.png"));
  }
  
  /**
   * エラーメッセージ表示確認
   * "「ユーザID」と「パスワード」は必須項目です"
   * @throws Exception
   */
  @Test
  public void testRequiredPasswd() throws Exception {
    driver.get(baseUrl + "/total/login");
    
    // トップページをエビデンス
    File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(file, new File("totalCapture/testRequiredPasswd/toppage.png"));
    
    // 「ユーザID」に値を入力
    driver.findElement(By.id("userid")).clear();
    driver.findElement(By.id("userid")).sendKeys("tsubasa");
    
    // 「パスワード」は未入力
    driver.findElement(By.id("passwd")).clear();
    driver.findElement(By.id("passwd")).sendKeys("");
    
    // ユーザログイン情報入力完了画面をエビデンス
    file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(file, new File("totalCapture/testRequiredPasswd/loginFill.png"));
    
    // 「ログイン」ボタン押下（「ユーザID」未入力の為エラー。total/login.htmlへ遷移）
    driver.findElement(By.name("login")).click();
    
    // エラーメッセージ表示画面をエビデンス
    file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(file, new File("totalCapture/testRequiredPasswd/error.png"));
  }
  
  /**
   * エラーメッセージ表示確認
   * "アカウントが存在しません"
   * @throws Exception
   */
  @Test
  public void testRequiredLoginInfo() throws Exception {
    driver.get(baseUrl + "/total/login");
    
    // トップページをエビデンス
    File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(file, new File("totalCapture/testRequiredLoginInfo/toppage.png"));
    
    // 「ユーザID」に値を入力
    driver.findElement(By.id("userid")).clear();
    driver.findElement(By.id("userid")).sendKeys("none");
    
    // 「パスワード」に値を入力
    driver.findElement(By.id("passwd")).clear();
    driver.findElement(By.id("passwd")).sendKeys("none");
    
    // ユーザログイン入力完了画面をエビデンス
    file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(file, new File("totalCapture/testRequiredLoginInfo/loginFill.png"));
    
    // 「ログイン」ボタン押下（「ユーザID」未入力の為エラー。total/login.htmlへ遷移）
    driver.findElement(By.name("login")).click();
    
    // エラーメッセージ表示画面をエビデンス
    file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(file, new File("totalCapture/testRequiredLoginInfo/error.png"));
  }

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}
