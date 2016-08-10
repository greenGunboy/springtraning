package com.example;

import static org.junit.Assert.assertEquals;
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
import org.openqa.selenium.support.ui.Select;

public class AdminSelenium {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
//	File pathToBinary = new File("C:\\Program Files\\Mozilla Firefox\\firefox.exe");
//	FirefoxBinary ffBinary = new FirefoxBinary(pathToBinary);
//	FirefoxProfile firefoxProfile = new FirefoxProfile();
//	driver = new FirefoxDriver(ffBinary, firefoxProfile);
	  
	// 引数にfirefoxのパスを指定している為、この処理は実行可能になる
	// 上記処理よりも引数に書くことで汎用性が上がる
    driver = new FirefoxDriver();
    baseUrl = "http://localhost:8080/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  /**
   * 全未入力で確認ボタン押下時のエラーチェックを確認した後、
   * 講座情報を入力し、登録するまでの処理
   * @throws Exception
   */
  @Test
  public void testSelenium() throws Exception {
    driver.get(baseUrl + "/admin/menu");
    
 /**********************************エビデンス*****************************/
    // エビデンス（証跡）。画面操作処理の合間にその時の場面を画像として残す処理
    File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(file, new File("capture/complete/topPage.png"));
 /**********************************************************************/
    
    // 「講座登録」ボタンを押下（admin/input.htmlへ遷移）
    driver.findElement(By.name("courseregister")).click();
    
    // 講座登録画面へ遷移しているかの確認 
    assertEquals("講座登録", getCurrentFunctionName());
    
    // 入力画面をエビデンス
    File file1 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(file1, new File("capture/complete/input.png"));
    
    // 「確認」ボタンを押下（未入力の為、エラー。admin/input.htmlへ遷移）
    driver.findElement(By.name("confirm")).click();
    
    // 講座登録画面へ遷移しているかの確認 
    assertEquals("講座登録", getCurrentFunctionName());
    
    // エラー表示画面をエビデンス
    file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(file, new File("capture/complete/errormassage.png"));
    
    // 「講座番号」に値を入力
    driver.findElement(By.id("courseno")).clear();
    driver.findElement(By.id("courseno")).sendKeys("@@@@");
    
    // 「講座名」に値を入力
    driver.findElement(By.id("coursename")).clear();
    driver.findElement(By.id("coursename")).sendKeys("12324");
    
    // 「講座開催日」に年月日を入力
    new Select(driver.findElement(By.id("year"))).selectByVisibleText("2017");
    new Select(driver.findElement(By.id("month"))).selectByVisibleText("1");
    new Select(driver.findElement(By.id("day"))).selectByVisibleText("1");
    
    // 「開始時刻」に時分を入力
    new Select(driver.findElement(By.id("sthour"))).selectByVisibleText("11");
    new Select(driver.findElement(By.id("stmin"))).selectByVisibleText("00");
    
    // 「終了時刻」に時分を入力
    new Select(driver.findElement(By.id("endhour"))).selectByVisibleText("12");
    new Select(driver.findElement(By.id("endmin"))).selectByVisibleText("00");
    
    // 「定員」に値を入力
    driver.findElement(By.id("vacantseats")).clear();
    driver.findElement(By.id("vacantseats")).sendKeys("5");
    
    // 「確認」ボタンを押下（admin/conf.htmlへ遷移）
    driver.findElement(By.name("confirm")).click();
    
    // 登録確認画面へ遷移しているかの確認 
    assertEquals("登録確認", getCurrentFunctionName());
    
    // 確認画面をエビデンス
    file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(file, new File("capture/complete/conf.png"));
 
    // 「登録」ボタンを押下（admin/end.htmlへ遷移）
    driver.findElement(By.name("register")).click();
    
    // 登録完了画面へ遷移しているかの確認 
    assertEquals("登録完了", getCurrentFunctionName());
    
    // 登録完了画面をエビデンス
    file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(file, new File("capture/complete/endPage.png"));
  }
  
  
  /**
   * エラーメッセージ"「講座番号」は必須項目です。"を表示確認
   * @throws Exception
   */
  @Test
  public void testRequiredCourseno() throws Exception {
    driver.get(baseUrl + "/admin/menu");
    
    // トップページをエビデンス
    File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(file, new File("capture/errormessage/requiredCourseno/topPage.png"));
    
    // 「講座登録」ボタンを押下（admin/input.htmlへ遷移）
    driver.findElement(By.name("courseregister")).click();
    
    // 講座登録画面へ遷移しているかの確認 
    assertEquals("講座登録", getCurrentFunctionName());
    
    // 「講座番号」は未入力
    driver.findElement(By.id("courseno")).clear();
    driver.findElement(By.id("courseno")).sendKeys("");
    
    // 「講座名」に値を入力
    driver.findElement(By.id("coursename")).clear();
    driver.findElement(By.id("coursename")).sendKeys("12345");
    
    // 「講座開催日」に年月日を入力
    new Select(driver.findElement(By.id("year"))).selectByVisibleText("2016");
    new Select(driver.findElement(By.id("month"))).selectByVisibleText("1");
    new Select(driver.findElement(By.id("day"))).selectByVisibleText("1");
    
    // 「開始時刻」に時分を入力
    new Select(driver.findElement(By.id("sthour"))).selectByVisibleText("10");
    new Select(driver.findElement(By.id("stmin"))).selectByVisibleText("00");
    
    // 「終了時刻」に時分を入力
    new Select(driver.findElement(By.id("endhour"))).selectByVisibleText("11");
    new Select(driver.findElement(By.id("endmin"))).selectByVisibleText("00");
    
    // 「定員」に値を入力
    driver.findElement(By.id("vacantseats")).clear();
    driver.findElement(By.id("vacantseats")).sendKeys("5");
    // 入力画面をエビデンス
    file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(file, new File("capture/errormessage/requiredCourseno/inputPage.png"));
    
    // 「確認」ボタンを押下（「講座番号」が未記入な為、admin/input.htmlへ遷移）
    driver.findElement(By.name("confirm")).click();
    // エラー画面をエビデンス
    file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(file, new File("capture/errormessage/requiredCourseno/errorPage.png"));
  }
  
  /**
   * エラーメッセージ"「講座番号」が重複しています。"を表示確認
   * @throws Exception
   */
  @Test
  public void testIsExistCourseno() throws Exception {
    driver.get(baseUrl + "/admin/menu");
    // トップページをエビデンス
    File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(file, new File("capture/errormessage/existCourseno/topPage.png"));
    
    // 「講座登録」ボタンを押下（admin/input.htmlへ遷移）
    driver.findElement(By.name("courseregister")).click();
    
    // 講座登録画面へ遷移しているかの確認 
    assertEquals("講座登録", getCurrentFunctionName());
    
    // 「講座番号」に重複している値を入力
    driver.findElement(By.id("courseno")).clear();
    driver.findElement(By.id("courseno")).sendKeys("test");
    
    // 「講座名」に値を入力
    driver.findElement(By.id("coursename")).clear();
    driver.findElement(By.id("coursename")).sendKeys("12345");
    
    // 「講座開催日」に年月日を入力
    new Select(driver.findElement(By.id("year"))).selectByVisibleText("2016");
    new Select(driver.findElement(By.id("month"))).selectByVisibleText("1");
    new Select(driver.findElement(By.id("day"))).selectByVisibleText("1");
    
    // 「開始時刻」に時分を入力
    new Select(driver.findElement(By.id("sthour"))).selectByVisibleText("10");
    new Select(driver.findElement(By.id("stmin"))).selectByVisibleText("00");
    
    // 「終了時刻」に時分を入力
    new Select(driver.findElement(By.id("endhour"))).selectByVisibleText("11");
    new Select(driver.findElement(By.id("endmin"))).selectByVisibleText("00");
    
    // 「定員」に値を入力
    driver.findElement(By.id("vacantseats")).clear();
    driver.findElement(By.id("vacantseats")).sendKeys("5");
    // 入力画面をエビデンス
    file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(file, new File("capture/errormessage/existCourseno/inputPage.png"));
    
    // 「確認」ボタンを押下（「講座番号」が重複している為、admin/input.htmlへ遷移）
    driver.findElement(By.name("confirm")).click();
    
    // 講座登録画面へ遷移しているかの確認 
    assertEquals("講座登録", getCurrentFunctionName());
    
    // エラー画面をエビデンス
    file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(file, new File("capture/errormessage/existCourseno/errorPage.png"));
  }
  
  /**
   * エラーメッセージ"「講座名」は必須項目です。"を表示確認
   * @throws Exception
   */
  @Test
  public void testRequiredCoursename() throws Exception {
    driver.get(baseUrl + "/admin/menu");
    // トップページをエビデンス
    File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(file, new File("capture/errormessage/requiredCoursename/topPage.png"));
    
    // 「講座登録」ボタンを押下（admin/input.htmlへ遷移）
    driver.findElement(By.name("courseregister")).click();
    
    // 講座登録画面へ遷移しているかの確認 
    assertEquals("講座登録", getCurrentFunctionName());
    
    // 「講座番号」に値を入力
    driver.findElement(By.id("courseno")).clear();
    driver.findElement(By.id("courseno")).sendKeys("@@@@");
    
    // 「講座名」は未入力
    driver.findElement(By.id("coursename")).clear();
    driver.findElement(By.id("coursename")).sendKeys("");
    
    // 「講座開催日」に年月日を入力
    new Select(driver.findElement(By.id("year"))).selectByVisibleText("2016");
    new Select(driver.findElement(By.id("month"))).selectByVisibleText("1");
    new Select(driver.findElement(By.id("day"))).selectByVisibleText("1");
    
    // 「開始時刻」に時分を入力
    new Select(driver.findElement(By.id("sthour"))).selectByVisibleText("10");
    new Select(driver.findElement(By.id("stmin"))).selectByVisibleText("00");
    
    // 「終了時刻」に時分を入力
    new Select(driver.findElement(By.id("endhour"))).selectByVisibleText("11");
    new Select(driver.findElement(By.id("endmin"))).selectByVisibleText("00");
    
    // 「定員」に値を入力
    driver.findElement(By.id("vacantseats")).clear();
    driver.findElement(By.id("vacantseats")).sendKeys("5");
    // 入力画面をエビデンス
    file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(file, new File("capture/errormessage/requiredCoursename/inputPage.png"));
    
    // 「確認」ボタンを押下（「講座名」が未記入な為、admin/input.htmlへ遷移）
    driver.findElement(By.name("confirm")).click();
    
    // 講座登録画面へ遷移しているかの確認 
    assertEquals("講座登録", getCurrentFunctionName());
    
    // エラー画面をエビデンス
    file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(file, new File("capture/errormessage/requiredCoursename/errorPage.png"));
  }
  
  /**
   * エラーメッセージ"「講座開催日」は必須項目です。"を表示確認
   * @throws Exception
   */
  @Test
  public void testRequiredTheDate() throws Exception {
    driver.get(baseUrl + "/admin/menu");
    // トップページをエビデンス
    File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(file, new File("capture/errormessage/requiredTheDate/topPage.png"));

    // 「講座登録」ボタンを押下（admin/input.htmlへ遷移）
    driver.findElement(By.name("courseregister")).click();
    
    // 講座登録画面へ遷移しているかの確認 
    assertEquals("講座登録", getCurrentFunctionName());
    
    // 「講座番号」に値を入力
    driver.findElement(By.id("courseno")).clear();
    driver.findElement(By.id("courseno")).sendKeys("@@@@");
    
    // 「講座名」に値を入力
    driver.findElement(By.id("coursename")).clear();
    driver.findElement(By.id("coursename")).sendKeys("12345");
    
    // 「講座開催日」は未入力
    new Select(driver.findElement(By.id("year"))).selectByVisibleText("");
    new Select(driver.findElement(By.id("month"))).selectByVisibleText("");
    new Select(driver.findElement(By.id("day"))).selectByVisibleText("");
    
    // 「開始時刻」に時分を入力
    new Select(driver.findElement(By.id("sthour"))).selectByVisibleText("10");
    new Select(driver.findElement(By.id("stmin"))).selectByVisibleText("00");
    
    // 「終了時刻」に時分を入力
    new Select(driver.findElement(By.id("endhour"))).selectByVisibleText("11");
    new Select(driver.findElement(By.id("endmin"))).selectByVisibleText("00");
    
    // 「定員」に値を入力
    driver.findElement(By.id("vacantseats")).clear();
    driver.findElement(By.id("vacantseats")).sendKeys("5");
    // 入力画面をエビデンス
    file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(file, new File("capture/errormessage/requiredTheDate/requiredDatePage6.png"));
    
    // 「確認」ボタンを押下（「講座開催日」が未記入な為、エラー。admin/input.htmlへ遷移）
    driver.findElement(By.name("confirm")).click();
    
    // 講座登録画面へ遷移しているかの確認 
    assertEquals("講座登録", getCurrentFunctionName());
    
    // エラー画面をエビデンス
    file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(file, new File("capture/errormessage/requiredTheDate/errorPage6.png"));
  }
  
  @Test
  public void testRequiredMonthAndDay() throws Exception {
	  driver.get(baseUrl + "/admin/menu");
	    // トップページをエビデンス
	    File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	    FileUtils.copyFile(file, new File("capture/errormessage/requiredTheDate/topPage.png"));
	    
	    // 「講座登録」ボタンを押下（admin/input.htmlへ遷移）
	    driver.findElement(By.name("courseregister")).click();
	    
	    // 講座登録画面へ遷移しているかの確認 
	    assertEquals("講座登録", getCurrentFunctionName());
	    
	    // 「講座番号」に値を入力
	    driver.findElement(By.id("courseno")).clear();
	    driver.findElement(By.id("courseno")).sendKeys("@@@@");
	    
	    // 「講座名」に値を入力
	    driver.findElement(By.id("coursename")).clear();
	    driver.findElement(By.id("coursename")).sendKeys("12345");
	    
	    // 「講座開催日」に年のみ入力
	    new Select(driver.findElement(By.id("year"))).selectByVisibleText("2016");
	    new Select(driver.findElement(By.id("month"))).selectByVisibleText("");
	    new Select(driver.findElement(By.id("day"))).selectByVisibleText("");
	    
	    // 「開始時刻」に時分を入力
	    new Select(driver.findElement(By.id("sthour"))).selectByVisibleText("10");
	    new Select(driver.findElement(By.id("stmin"))).selectByVisibleText("00");
	    
	    // 「終了時刻」に時分を入力
	    new Select(driver.findElement(By.id("endhour"))).selectByVisibleText("11");
	    new Select(driver.findElement(By.id("endmin"))).selectByVisibleText("00");
	    
	    // 「定員」に値を入力
	    driver.findElement(By.id("vacantseats")).clear();
	    driver.findElement(By.id("vacantseats")).sendKeys("5");
	    // 入力画面をエビデンス
	    file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	    FileUtils.copyFile(file, new File("capture/errormessage/requiredTheDate/requiredMonthDayPage1.png"));
	    
	    // 「確認」ボタンを押下（「講座開催日」の「月」と「日」が未記入な為、エラー。admin/input.htmlへ遷移）
	    driver.findElement(By.name("confirm")).click();
	    
	    // 講座登録画面へ遷移しているかの確認 
	    assertEquals("講座登録", getCurrentFunctionName());
	    
	    // エラー画面をエビデンス
	    file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	    FileUtils.copyFile(file, new File("capture/errormessage/requiredTheDate/errorPage1.png"));
  }
  
  @Test
  public void testRequiredDay() throws Exception {
	  driver.get(baseUrl + "/admin/menu");
	    // トップページをエビデンス
	    File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	    FileUtils.copyFile(file, new File("capture/errormessage/requiredTheDate/topPage.png"));
	    
	    // 「講座登録」ボタンを押下（admin/input.htmlへ遷移）
	    driver.findElement(By.name("courseregister")).click();
	    
	    // 講座登録画面へ遷移しているかの確認 
	    assertEquals("講座登録", getCurrentFunctionName());
	 	    
	    // 「講座番号」に値を入力
	    driver.findElement(By.id("courseno")).clear();
	    driver.findElement(By.id("courseno")).sendKeys("@@@@");
	    
	    // 「講座名」に値を入力
	    driver.findElement(By.id("coursename")).clear();
	    driver.findElement(By.id("coursename")).sendKeys("12345");
	    
	    // 「講座開催日」に年と月を入力
	    new Select(driver.findElement(By.id("year"))).selectByVisibleText("2016");
	    new Select(driver.findElement(By.id("month"))).selectByVisibleText("1");
	    new Select(driver.findElement(By.id("day"))).selectByVisibleText("");
	    
	    // 「開始時刻」に時分を入力
	    new Select(driver.findElement(By.id("sthour"))).selectByVisibleText("10");
	    new Select(driver.findElement(By.id("stmin"))).selectByVisibleText("00");
	    
	    // 「終了時刻」に時分を入力
	    new Select(driver.findElement(By.id("endhour"))).selectByVisibleText("11");
	    new Select(driver.findElement(By.id("endmin"))).selectByVisibleText("00");
	    
	    // 「定員」に値を入力
	    driver.findElement(By.id("vacantseats")).clear();
	    driver.findElement(By.id("vacantseats")).sendKeys("5");
	    // 入力画面をエビデンス
	    file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	    FileUtils.copyFile(file, new File("capture/errormessage/requiredTheDate/requiredDayPage2.png"));
	    
	    // 「確認」ボタンを押下（「講座開催日」の「日」が未記入な為、エラー。admin/input.htmlへ遷移）
	    driver.findElement(By.name("confirm")).click();
	    
	    // 講座登録画面へ遷移しているかの確認 
	    assertEquals("講座登録", getCurrentFunctionName());
	    
	    // エラー画面をエビデンス
	    file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	    FileUtils.copyFile(file, new File("capture/errormessage/requiredTheDate/errorPage2.png"));
  }
  
  @Test
  public void testRequiredYearAndDay() throws Exception {
	  driver.get(baseUrl + "/admin/menu");
	    // トップページをエビデンス
	    File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	    FileUtils.copyFile(file, new File("capture/errormessage/requiredTheDate/topPage.png"));
	    
	    // 「講座登録」ボタンを押下（admin/input.htmlへ遷移）
	    driver.findElement(By.name("courseregister")).click();
	    
	    // 講座登録画面へ遷移しているかの確認 
	    assertEquals("講座登録", getCurrentFunctionName());
	    
	    // 「講座番号」に値を入力
	    driver.findElement(By.id("courseno")).clear();
	    driver.findElement(By.id("courseno")).sendKeys("@@@@");
	    
	    // 「講座名」に値を入力
	    driver.findElement(By.id("coursename")).clear();
	    driver.findElement(By.id("coursename")).sendKeys("12345");
	    
	    // 「講座開催日」に月のみ入力
	    new Select(driver.findElement(By.id("year"))).selectByVisibleText("");
	    new Select(driver.findElement(By.id("month"))).selectByVisibleText("1");
	    new Select(driver.findElement(By.id("day"))).selectByVisibleText("");
	    
	    // 「開始時刻」に時分を入力
	    new Select(driver.findElement(By.id("sthour"))).selectByVisibleText("10");
	    new Select(driver.findElement(By.id("stmin"))).selectByVisibleText("00");
	    
	    // 「終了時刻」に時分を入力
	    new Select(driver.findElement(By.id("endhour"))).selectByVisibleText("11");
	    new Select(driver.findElement(By.id("endmin"))).selectByVisibleText("00");
	    
	    // 「定員」に値を入力
	    driver.findElement(By.id("vacantseats")).clear();
	    driver.findElement(By.id("vacantseats")).sendKeys("5");
	    // 入力画面をエビデンス
	    file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	    FileUtils.copyFile(file, new File("capture/errormessage/requiredTheDate/requiredYearDayPage3.png"));
	    
	    // 「確認」ボタンを押下（「講座開催日」の「年」と「日」が未記入な為、エラー。admin/input.htmlへ遷移）
	    driver.findElement(By.name("confirm")).click();
	    
	    // 講座登録画面へ遷移しているかの確認 
	    assertEquals("講座登録", getCurrentFunctionName());
	    
	    // エラー画面をエビデンス
	    file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	    FileUtils.copyFile(file, new File("capture/errormessage/requiredTheDate/errorPage3.png"));
  }
  
  @Test
  public void testRequiredYear() throws Exception {
	  driver.get(baseUrl + "/admin/menu");
	    // トップページをエビデンス
	    File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	    FileUtils.copyFile(file, new File("capture/errormessage/requiredTheDate/topPage.png"));
	    
	    // 「講座登録」ボタンを押下（admin/input.htmlへ遷移）
	    driver.findElement(By.name("courseregister")).click();
	    
	    // 講座登録画面へ遷移しているかの確認 
	    assertEquals("講座登録", getCurrentFunctionName());
	    
	    // 「講座番号」に値を入力
	    driver.findElement(By.id("courseno")).clear();
	    driver.findElement(By.id("courseno")).sendKeys("@@@@");
	    
	    // 「講座名」に値を入力
	    driver.findElement(By.id("coursename")).clear();
	    driver.findElement(By.id("coursename")).sendKeys("12345");
	    
	    // 「講座開催日」に月と日を入力
	    new Select(driver.findElement(By.id("year"))).selectByVisibleText("");
	    new Select(driver.findElement(By.id("month"))).selectByVisibleText("1");
	    new Select(driver.findElement(By.id("day"))).selectByVisibleText("1");
	    
	    // 「開始時刻」に時分を入力
	    new Select(driver.findElement(By.id("sthour"))).selectByVisibleText("10");
	    new Select(driver.findElement(By.id("stmin"))).selectByVisibleText("00");
	    
	    // 「終了時刻」に時分を入力
	    new Select(driver.findElement(By.id("endhour"))).selectByVisibleText("11");
	    new Select(driver.findElement(By.id("endmin"))).selectByVisibleText("00");
	    
	    // 「定員」に値を入力
	    driver.findElement(By.id("vacantseats")).clear();
	    driver.findElement(By.id("vacantseats")).sendKeys("5");
	    // 入力画面をエビデンス
	    file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	    FileUtils.copyFile(file, new File("capture/errormessage/requiredTheDate/requiredYearPage4.png"));
	    
	    // 「確認」ボタンを押下（「講座開催日」の「年」が未記入な為、エラー。admin/input.htmlへ遷移）
	    driver.findElement(By.name("confirm")).click();
	    
	    // 講座登録画面へ遷移しているかの確認 
	    assertEquals("講座登録", getCurrentFunctionName());
	    
	    // エラー画面をエビデンス
	    file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	    FileUtils.copyFile(file, new File("capture/errormessage/requiredTheDate/errorPage4.png"));
	    
  }
  
  @Test
  public void testRequiredYearAndMonth() throws Exception {
	  driver.get(baseUrl + "/admin/menu");
	    // トップページをエビデンス
	    File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	    FileUtils.copyFile(file, new File("capture/errormessage/requiredTheDate/topPage.png"));
	    
	    // 「講座登録」ボタンを押下（admin/input.htmlへ遷移）
	    driver.findElement(By.name("courseregister")).click();
	    
	    // 講座登録画面へ遷移しているかの確認 
	    assertEquals("講座登録", getCurrentFunctionName());
	    
	    // 「講座番号」に値を入力
	    driver.findElement(By.id("courseno")).clear();
	    driver.findElement(By.id("courseno")).sendKeys("@@@@");
	    
	    // 「講座名」に値を入力
	    driver.findElement(By.id("coursename")).clear();
	    driver.findElement(By.id("coursename")).sendKeys("12345");
	    
	    // 「講座開催日」に日のみ入力
	    new Select(driver.findElement(By.id("year"))).selectByVisibleText("");
	    new Select(driver.findElement(By.id("month"))).selectByVisibleText("");
	    new Select(driver.findElement(By.id("day"))).selectByVisibleText("1");
	    
	    // 「開始時刻」に時分を入力
	    new Select(driver.findElement(By.id("sthour"))).selectByVisibleText("10");
	    new Select(driver.findElement(By.id("stmin"))).selectByVisibleText("00");
	    
	    // 「終了時刻」に時分を入力
	    new Select(driver.findElement(By.id("endhour"))).selectByVisibleText("11");
	    new Select(driver.findElement(By.id("endmin"))).selectByVisibleText("00");
	    
	    // 「定員」に値を入力
	    driver.findElement(By.id("vacantseats")).clear();
	    driver.findElement(By.id("vacantseats")).sendKeys("5");
	    // 入力画面をエビデンス
	    file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	    FileUtils.copyFile(file, new File("capture/errormessage/requiredTheDate/requiredYearMonthPage5.png"));
	    
	    // 「確認」ボタンを押下（「講座開催日」の「年」と「月」が未記入な為、エラー。admin/input.htmlへ遷移）
	    driver.findElement(By.name("confirm")).click();
	    
	    // 講座登録画面へ遷移しているかの確認 
	    assertEquals("講座登録", getCurrentFunctionName());
	    
	    // エラー画面をエビデンス
	    file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	    FileUtils.copyFile(file, new File("capture/errormessage/requiredTheDate/errorPage5.png"));
  }
  
  /**
   * エラーメッセージ"「開始時刻」は必須項目です。"を表示確認
   * @throws Exception
   */
  @Test
  public void testRequiredStarttime() throws Exception {
    driver.get(baseUrl + "/admin/menu");
    // トップページをエビデンス
    File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(file, new File("capture/errormessage/requiredStartTime/topPage.png"));
    
    // 「講座登録」ボタンを押下（admin/input.htmlへ遷移）
    driver.findElement(By.name("courseregister")).click();
    
    // 講座登録画面へ遷移しているかの確認 
    assertEquals("講座登録", getCurrentFunctionName());
    
    // 「講座番号」に値を入力
    driver.findElement(By.id("courseno")).clear();
    driver.findElement(By.id("courseno")).sendKeys("@@@@");
    
    // 「講座名」に値を入力
    driver.findElement(By.id("coursename")).clear();
    driver.findElement(By.id("coursename")).sendKeys("12345");
    
    // 「講座開催日」に年月日を入力
    new Select(driver.findElement(By.id("year"))).selectByVisibleText("2016");
    new Select(driver.findElement(By.id("month"))).selectByVisibleText("1");
    new Select(driver.findElement(By.id("day"))).selectByVisibleText("1");
    
    // 「開始時刻」は未入力
    new Select(driver.findElement(By.id("sthour"))).selectByVisibleText("");
    new Select(driver.findElement(By.id("stmin"))).selectByVisibleText("");
    
    // 「終了時刻」に時分を入力
    new Select(driver.findElement(By.id("endhour"))).selectByVisibleText("11");
    new Select(driver.findElement(By.id("endmin"))).selectByVisibleText("00");
    
    // 「定員」に値を入力
    driver.findElement(By.id("vacantseats")).clear();
    driver.findElement(By.id("vacantseats")).sendKeys("5");
    // 入力画面をエビデンス
    file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(file, new File("capture/errormessage/requiredStartTime/requiredTimePage1.png"));
    
    // 「確認」ボタンを押下（「開始時刻」が未記入な為、、エラー。admin/input.htmlへ遷移）
    driver.findElement(By.name("confirm")).click();
    
    // 講座登録画面へ遷移しているかの確認 
    assertEquals("講座登録", getCurrentFunctionName());
    
    // エラー画面をエビデンス
    file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(file, new File("capture/errormessage/requiredStartTime/errorPage1.png"));
      
    }
  
  @Test
  public void testRequiredStartHour() throws Exception {
	  	driver.get(baseUrl + "/admin/menu");
	    // トップページをエビデンス
	    File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	    FileUtils.copyFile(file, new File("capture/errormessage/requiredStartTime/topPage.png"));
	    
	    // 「講座登録」ボタンを押下（admin/input.htmlへ遷移）
	    driver.findElement(By.name("courseregister")).click();
	    
	    // 講座登録画面へ遷移しているかの確認 
	    assertEquals("講座登録", getCurrentFunctionName());
	    
	    // 「講座番号」に値を入力
	    driver.findElement(By.id("courseno")).clear();
	    driver.findElement(By.id("courseno")).sendKeys("@@@@");
	    
	    // 「講座名」に値を入力
	    driver.findElement(By.id("coursename")).clear();
	    driver.findElement(By.id("coursename")).sendKeys("12345");
	    
	    // 「講座開催日」に年月日を入力
	    new Select(driver.findElement(By.id("year"))).selectByVisibleText("2016");
	    new Select(driver.findElement(By.id("month"))).selectByVisibleText("1");
	    new Select(driver.findElement(By.id("day"))).selectByVisibleText("1");
	    
	    // 「開始時刻」に分のみ入力
	    new Select(driver.findElement(By.id("sthour"))).selectByVisibleText("");
	    new Select(driver.findElement(By.id("stmin"))).selectByVisibleText("00");
	    
	    // 「終了時刻」に時分を入力
	    new Select(driver.findElement(By.id("endhour"))).selectByVisibleText("11");
	    new Select(driver.findElement(By.id("endmin"))).selectByVisibleText("00");
	    
	    // 「定員」に値を入力
	    driver.findElement(By.id("vacantseats")).clear();
	    driver.findElement(By.id("vacantseats")).sendKeys("5");
	    // 入力画面をエビデンス
	    file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	    FileUtils.copyFile(file, new File("capture/errormessage/requiredStartTime/requiredHourPage3.png"));
	    
	    // 「確認」ボタンを押下（「開始時刻」の「時」が未記入な為、、エラー。admin/input.htmlへ遷移）
	    driver.findElement(By.name("confirm")).click();
	    
	    // 講座登録画面へ遷移しているかの確認 
	    assertEquals("講座登録", getCurrentFunctionName());
	    
	    // エラー画面をエビデンス
	    file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	    FileUtils.copyFile(file, new File("capture/errormessage/requiredStartTime/errorPage3.png"));
  }
  
  @Test
  public void testRequiredStartMin() throws Exception {
	  	driver.get(baseUrl + "/admin/menu");
	    // トップページをエビデンス
	    File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	    FileUtils.copyFile(file, new File("capture/errormessage/requiredStartTime/topPage.png"));
	    
	    // 「講座登録」ボタンを押下（admin/input.htmlへ遷移）
	    driver.findElement(By.name("courseregister")).click();
	    
	    // 講座登録画面へ遷移しているかの確認 
	    assertEquals("講座登録", getCurrentFunctionName());
	    	    
	    // 「講座番号」に値を入力
	    driver.findElement(By.id("courseno")).clear();
	    driver.findElement(By.id("courseno")).sendKeys("@@@@");
	    
	    // 「講座名」に値を入力
	    driver.findElement(By.id("coursename")).clear();
	    driver.findElement(By.id("coursename")).sendKeys("12345");
	    
	    // 「講座開催日」に年月日を入力
	    new Select(driver.findElement(By.id("year"))).selectByVisibleText("2016");
	    new Select(driver.findElement(By.id("month"))).selectByVisibleText("1");
	    new Select(driver.findElement(By.id("day"))).selectByVisibleText("1");
	    
	    // 「開始時刻」に時のみ入力
	    new Select(driver.findElement(By.id("sthour"))).selectByVisibleText("10");
	    new Select(driver.findElement(By.id("stmin"))).selectByVisibleText("");
	    
	    // 「終了時刻」に時分を入力
	    new Select(driver.findElement(By.id("endhour"))).selectByVisibleText("11");
	    new Select(driver.findElement(By.id("endmin"))).selectByVisibleText("00");
	    
	    // 「定員」に値を入力
	    driver.findElement(By.id("vacantseats")).clear();
	    driver.findElement(By.id("vacantseats")).sendKeys("5");
	    // 入力画面をエビデンス
	    file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	    FileUtils.copyFile(file, new File("capture/errormessage/requiredStartTime/requiredMinPage2.png"));
	    
	    // 「確認」ボタンを押下（「開始時刻」の「分」が未記入な為、、エラー。admin/input.htmlへ遷移）
	    driver.findElement(By.name("confirm")).click();

	    // 講座登録画面へ遷移しているかの確認 
	    assertEquals("講座登録", getCurrentFunctionName());
	    
	    // エラー画面をエビデンス
	    file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	    FileUtils.copyFile(file, new File("capture/errormessage/requiredStartTime/errorPage2.png"));
  }
  
  /**
   * エラーメッセージ"「終了時刻」は必須項目です。"を表示確認
   * @throws Exception
   */
  @Test
  public void testRequiredEndtime() throws Exception {
    driver.get(baseUrl + "/admin/menu");
    // トップページをエビデンス
    File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(file, new File("capture/errormessage/requiredEndTime/topPage.png"));
    
    // 「講座登録」ボタンを押下（admin/input.htmlへ遷移）
    driver.findElement(By.name("courseregister")).click();
    
    // 講座登録画面へ遷移しているかの確認 
    assertEquals("講座登録", getCurrentFunctionName());
    
    // 「講座番号」に値を入力
    driver.findElement(By.id("courseno")).clear();
    driver.findElement(By.id("courseno")).sendKeys("@@@@");
    
    // 「講座名」に値を入力
    driver.findElement(By.id("coursename")).clear();
    driver.findElement(By.id("coursename")).sendKeys("12345");
    
    // 「講座開催日」に年月日を入力
    new Select(driver.findElement(By.id("year"))).selectByVisibleText("2016");
    new Select(driver.findElement(By.id("month"))).selectByVisibleText("1");
    new Select(driver.findElement(By.id("day"))).selectByVisibleText("1");
    
    // 「開始時刻」に時分を入力
    new Select(driver.findElement(By.id("sthour"))).selectByVisibleText("10");
    new Select(driver.findElement(By.id("stmin"))).selectByVisibleText("00");
    
    // 「終了時刻」は未入力
    new Select(driver.findElement(By.id("endhour"))).selectByVisibleText("");
    new Select(driver.findElement(By.id("endmin"))).selectByVisibleText("");
    
    // 「定員」に値を入力
    driver.findElement(By.id("vacantseats")).clear();
    driver.findElement(By.id("vacantseats")).sendKeys("5");
    // 入力画面をエビデンス
    file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(file, new File("capture/errormessage/requiredEndTime/requiredTimePagePage1.png"));
    
    // 「確認」ボタンを押下（「終了時刻」が未記入な為、、エラー。admin/input.htmlへ遷移）
    driver.findElement(By.name("confirm")).click();
    
    // 講座登録画面へ遷移しているかの確認 
    assertEquals("講座登録", getCurrentFunctionName());
    
    // エラー画面をエビデンス
    file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(file, new File("capture/errormessage/requiredEndTime/errorPage1.png"));
  }
  
  /**
   * エラーメッセージ"「終了時刻」は必須項目です。"を表示確認
   * 時のみ未入力の場合
   * @throws Exception
   */
  @Test
  public void testRequiredEndHour() throws Exception {
    driver.get(baseUrl + "/admin/menu");
    // トップページをエビデンス
    File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(file, new File("capture/errormessage/requiredEndTime/topPage.png"));
    
    // 「講座登録」ボタンを押下（admin/input.htmlへ遷移）
    driver.findElement(By.name("courseregister")).click();
    
    // 講座登録画面へ遷移しているかの確認 
    assertEquals("講座登録", getCurrentFunctionName());
    
    // 「講座番号」に値を入力
    driver.findElement(By.id("courseno")).clear();
    driver.findElement(By.id("courseno")).sendKeys("@@@@");
    
    // 「講座名」に値を入力
    driver.findElement(By.id("coursename")).clear();
    driver.findElement(By.id("coursename")).sendKeys("12345");
    
    // 「講座開催日」に年月日を入力
    new Select(driver.findElement(By.id("year"))).selectByVisibleText("2016");
    new Select(driver.findElement(By.id("month"))).selectByVisibleText("1");
    new Select(driver.findElement(By.id("day"))).selectByVisibleText("1");
    
    // 「開始時刻」に時分を入力
    new Select(driver.findElement(By.id("sthour"))).selectByVisibleText("10");
    new Select(driver.findElement(By.id("stmin"))).selectByVisibleText("00");
    
    // 「終了時刻」に分のみ入力
    new Select(driver.findElement(By.id("endhour"))).selectByVisibleText("");
    new Select(driver.findElement(By.id("endmin"))).selectByVisibleText("00");
    
    // 「定員」に値を入力
    driver.findElement(By.id("vacantseats")).clear();
    driver.findElement(By.id("vacantseats")).sendKeys("5");
    // 入力画面をエビデンス
    file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(file, new File("capture/errormessage/requiredEndTime/requiredHourPage3.png"));
    
    // 「確認」ボタンを押下（「終了時刻」の「時」が未記入な為、、エラー。admin/input.htmlへ遷移）
    driver.findElement(By.name("confirm")).click();
    
    // 講座登録画面へ遷移しているかの確認 
    assertEquals("講座登録", getCurrentFunctionName());
    
    // エラー画面をエビデンス
    file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(file, new File("capture/errormessage/requiredEndTime/errorPage3.png"));
  }
  
  /**
   * エラーメッセージ"「終了時刻」は必須項目です。"を表示確認
   * 分のみ未入力の場合
   * @throws Exception
   */
  @Test
  public void testRequiredEndMin() throws Exception {
    driver.get(baseUrl + "/admin/menu");
    // トップページをエビデンス
    File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(file, new File("capture/errormessage/requiredEndTime/topPage.png"));
    
    // 「講座登録」ボタンを押下（admin/input.htmlへ遷移）
    driver.findElement(By.name("courseregister")).click();
    
    // 講座登録画面へ遷移しているかの確認 
    assertEquals("講座登録", getCurrentFunctionName());
    
    // 「講座番号」に値を入力
    driver.findElement(By.id("courseno")).clear();
    driver.findElement(By.id("courseno")).sendKeys("@@@@");
    
    // 「講座名」に値を入力
    driver.findElement(By.id("coursename")).clear();
    driver.findElement(By.id("coursename")).sendKeys("12345");
    
    // 「講座開催日」に年月日を入力
    new Select(driver.findElement(By.id("year"))).selectByVisibleText("2016");
    new Select(driver.findElement(By.id("month"))).selectByVisibleText("1");
    new Select(driver.findElement(By.id("day"))).selectByVisibleText("1");
    
    // 「開始時刻」に時分を入力
    new Select(driver.findElement(By.id("sthour"))).selectByVisibleText("10");
    new Select(driver.findElement(By.id("stmin"))).selectByVisibleText("00");
    
    // 「終了時刻」に時のみ入力
    new Select(driver.findElement(By.id("endhour"))).selectByVisibleText("11");
    new Select(driver.findElement(By.id("endmin"))).selectByVisibleText("");
    
    // 「定員」に値を入力
    driver.findElement(By.id("vacantseats")).clear();
    driver.findElement(By.id("vacantseats")).sendKeys("5");
    // 入力画面をエビデンス
    file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(file, new File("capture/errormessage/requiredEndTime/requiredMinPage2.png"));
    
    // 「確認」ボタンを押下（「終了時刻」の「分」が未記入な為、、エラー。admin/input.htmlへ遷移）
    driver.findElement(By.name("confirm")).click();
    
    // 講座登録画面へ遷移しているかの確認 
    assertEquals("講座登録", getCurrentFunctionName());
    
    // エラー画面をエビデンス
    file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(file, new File("capture/errormessage/requiredEndTime/errorPage2.png"));
  }
  
  /**
   * エラーメッセージ"「終了時刻」は「開始時刻」よりも後の時刻を入力してください。"を表示確認
   * @throws Exception
   */
  @Test
  public void testContradictionTime() throws Exception {
    driver.get(baseUrl + "/admin/menu");
    // トップページをエビデンス
    File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(file, new File("capture/errormessage/contradictionTime/topPage.png"));
    
    // 「講座登録」ボタンを押下（admin/input.htmlへ遷移）
    driver.findElement(By.name("courseregister")).click();
    
    // 講座登録画面へ遷移しているかの確認 
    assertEquals("講座登録", getCurrentFunctionName());
    
    // 「講座番号」に値を入力
    driver.findElement(By.id("courseno")).clear();
    driver.findElement(By.id("courseno")).sendKeys("@@@@");
    
    // 「講座名」に値を入力
    driver.findElement(By.id("coursename")).clear();
    driver.findElement(By.id("coursename")).sendKeys("12345");
    
    // 「講座開催日」に年月日を入力
    new Select(driver.findElement(By.id("year"))).selectByVisibleText("2016");
    new Select(driver.findElement(By.id("month"))).selectByVisibleText("1");
    new Select(driver.findElement(By.id("day"))).selectByVisibleText("1");
    
    // 「開始時刻」に「終了時刻」よりも後の時分を入力
    new Select(driver.findElement(By.id("sthour"))).selectByVisibleText("12");
    new Select(driver.findElement(By.id("stmin"))).selectByVisibleText("00");
    
    // 「終了時刻」に「開始時刻」よりも前の時分を入力
    new Select(driver.findElement(By.id("endhour"))).selectByVisibleText("11");
    new Select(driver.findElement(By.id("endmin"))).selectByVisibleText("00");
    
    // 「定員」に値を入力
    driver.findElement(By.id("vacantseats")).clear();
    driver.findElement(By.id("vacantseats")).sendKeys("5");
    // 入力画面をエビデンス
    file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(file, new File("capture/errormessage/contradictionTime/inputPage.png"));
    
    // 「確認」ボタンを押下（「開始時刻」が「終了時刻」よりも後な為、エラー。admin/input.htmlへ遷移）
    driver.findElement(By.name("confirm")).click();
    
    // 講座登録画面へ遷移しているかの確認 
    assertEquals("講座登録", getCurrentFunctionName());
    
    // エラー画面をエビデンス
    file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(file, new File("capture/errormessage/contradictionTime/errorPage.png"));
  }
  
  /**
   * エラーメッセージ "「定員」は必須項目です。"を表示確認
   * @throws Exception
   */
  @Test
  public void testRequiredVacantseats() throws Exception {
    driver.get(baseUrl + "/admin/menu");
    // トップページをエビデンス
    File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(file, new File("capture/errormessage/requiredVacantseats/topPage.png"));
    
    // 「講座登録」ボタンを押下（admin/input.htmlへ遷移）
    driver.findElement(By.name("courseregister")).click();
    
    // 講座登録画面へ遷移しているかの確認 
    assertEquals("講座登録", getCurrentFunctionName());
    
    // 「講座番号」に値を入力
    driver.findElement(By.id("courseno")).clear();
    driver.findElement(By.id("courseno")).sendKeys("@@@@");
    
    // 「講座名」に値を入力
    driver.findElement(By.id("coursename")).clear();
    driver.findElement(By.id("coursename")).sendKeys("12345");
    
    // 「講座開催日」に年月日を入力
    new Select(driver.findElement(By.id("year"))).selectByVisibleText("2016");
    new Select(driver.findElement(By.id("month"))).selectByVisibleText("1");
    new Select(driver.findElement(By.id("day"))).selectByVisibleText("1");
    
    // 「開始時刻」に時分を入力
    new Select(driver.findElement(By.id("sthour"))).selectByVisibleText("10");
    new Select(driver.findElement(By.id("stmin"))).selectByVisibleText("00");
    
    // 「終了時刻」に時分を入力
    new Select(driver.findElement(By.id("endhour"))).selectByVisibleText("11");
    new Select(driver.findElement(By.id("endmin"))).selectByVisibleText("00");
    
    // 「定員」は未入力
    driver.findElement(By.id("vacantseats")).clear();
    driver.findElement(By.id("vacantseats")).sendKeys("");
    // 入力画面をエビデンス
    file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(file, new File("capture/errormessage/requiredVacantseats/inputPage.png"));
    
    // 「確認」ボタンを押下（「定員」が未記入な為、、エラー。admin/input.htmlへ遷移）
    driver.findElement(By.name("confirm")).click();
    
    // 講座登録画面へ遷移しているかの確認 
    assertEquals("講座登録", getCurrentFunctionName());
    
    // エラー画面をエビデンス
    file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(file, new File("capture/errormessage/requiredVacantseats/errorPage.png"));
  }
  
  /**
   * エラーメッセージ"「定員」は数字で入力してください。"を表示確認
   * @throws Exception
   */
  @Test
  public void testNumberOfExceptionVacantseats() throws Exception {
    driver.get(baseUrl + "/admin/menu");
    // トップページをエビデンス
    File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(file, new File("capture/errormessage/numberOfExceptionVacantseats/topPage.png"));
    
    // 「講座登録」ボタンを押下（admin/input.htmlへ遷移）
    driver.findElement(By.name("courseregister")).click();
    
    // 講座登録画面へ遷移しているかの確認 
    assertEquals("講座登録", getCurrentFunctionName());
    
    // 「講座番号」に値を入力
    driver.findElement(By.id("courseno")).clear();
    driver.findElement(By.id("courseno")).sendKeys("@@@@");
    
    // 「講座名」に値を入力
    driver.findElement(By.id("coursename")).clear();
    driver.findElement(By.id("coursename")).sendKeys("12345");
    
    // 「講座開催日」に年月日を入力
    new Select(driver.findElement(By.id("year"))).selectByVisibleText("2016");
    new Select(driver.findElement(By.id("month"))).selectByVisibleText("1");
    new Select(driver.findElement(By.id("day"))).selectByVisibleText("1");
    
    // 「開始時刻」に時分を入力
    new Select(driver.findElement(By.id("sthour"))).selectByVisibleText("10");
    new Select(driver.findElement(By.id("stmin"))).selectByVisibleText("00");
    
    // 「終了時刻」に時分を入力
    new Select(driver.findElement(By.id("endhour"))).selectByVisibleText("11");
    new Select(driver.findElement(By.id("endmin"))).selectByVisibleText("00");
    
    // 「定員」に文字を入力
    driver.findElement(By.id("vacantseats")).clear();
    driver.findElement(By.id("vacantseats")).sendKeys("あ");
    // 入力画面をエビデンス
    file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(file, new File("capture/errormessage/numberOfExceptionVacantseats/inputPage.png"));
    
    // 「確認」ボタンを押下（ 「定員」に文字が入力された為、、エラー。admin/input.htmlへ遷移）
    driver.findElement(By.name("confirm")).click();
    
    // 講座登録画面へ遷移しているかの確認 
    assertEquals("講座登録", getCurrentFunctionName());
    
    // エラー画面をエビデンス
    file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(file, new File("capture/errormessage/numberOfExceptionVacantseats/errorPage.png"));
  }
  
  /**
   * エラーメッセージ"「定員」は1以上、50以下で入力してください。"を表示確認
   * 下限を下回り「0」を入力した場合
   * @throws Exception
   */
  @Test
  public void testRangeMinVacantseats() throws Exception {
    driver.get(baseUrl + "/admin/menu");
    // トップページをエビデンス
    File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(file, new File("capture/errormessage/rangeVacantseats/topPage.png"));
    
    // 「講座登録」ボタンを押下（admin/input.htmlへ遷移）
    driver.findElement(By.name("courseregister")).click();
    
    // 講座登録画面へ遷移しているかの確認 
    assertEquals("講座登録", getCurrentFunctionName());
    
    // 「講座番号」に値を入力
    driver.findElement(By.id("courseno")).clear();
    driver.findElement(By.id("courseno")).sendKeys("@@@@");
    
    // 「講座名」に値を入力
    driver.findElement(By.id("coursename")).clear();
    driver.findElement(By.id("coursename")).sendKeys("12345");
    
    // 「講座開催日」に年月日を入力
    new Select(driver.findElement(By.id("year"))).selectByVisibleText("2016");
    new Select(driver.findElement(By.id("month"))).selectByVisibleText("1");
    new Select(driver.findElement(By.id("day"))).selectByVisibleText("1");
    
    // 「開始時刻」に時分を入力
    new Select(driver.findElement(By.id("sthour"))).selectByVisibleText("10");
    new Select(driver.findElement(By.id("stmin"))).selectByVisibleText("00");
    
    // 「終了時刻」に時分を入力
    new Select(driver.findElement(By.id("endhour"))).selectByVisibleText("11");
    new Select(driver.findElement(By.id("endmin"))).selectByVisibleText("00");
    
    // 「定員」に指定範囲外である「0」を入力
    driver.findElement(By.id("vacantseats")).clear();
    driver.findElement(By.id("vacantseats")).sendKeys("0");
    // 入力画面をエビデンス
    file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(file, new File("capture/errormessage/rangeVacantseats/vacantseatsIsZeroPage1.png"));
    
    // 「確認」ボタンを押下（「定員」の値が1以上でない為、、エラー。admin/input.htmlへ遷移）
    driver.findElement(By.name("confirm")).click();
    
    // 講座登録画面へ遷移しているかの確認 
    assertEquals("講座登録", getCurrentFunctionName());
    
    // エラー画面をエビデンス
    file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(file, new File("capture/errormessage/rangeVacantseats/errorPage1.png"));
  }
  
  /**
   * エラーメッセージ"「定員」は1以上、50以下で入力してください。"を表示確認
   * 上限を上回る「51」を入力した場合
   * @throws Exception
   */
  @Test
  public void testRangeMaxVacantseats() throws Exception {
    driver.get(baseUrl + "/admin/menu");
    // トップページをエビデンス
    File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(file, new File("capture/errormessage/rangeVacantseats/topPage.png"));
    
    // 「講座登録」ボタンを押下（admin/input.htmlへ遷移）
    driver.findElement(By.name("courseregister")).click();
    
    // 講座登録画面へ遷移しているかの確認 
    assertEquals("講座登録", getCurrentFunctionName());
    
    // 「講座番号」に値を入力
    driver.findElement(By.id("courseno")).clear();
    driver.findElement(By.id("courseno")).sendKeys("@@@@");
    
    // 「講座名」に値を入力
    driver.findElement(By.id("coursename")).clear();
    driver.findElement(By.id("coursename")).sendKeys("12345");
    
    // 「講座開催日」に年月日を入力
    new Select(driver.findElement(By.id("year"))).selectByVisibleText("2016");
    new Select(driver.findElement(By.id("month"))).selectByVisibleText("1");
    new Select(driver.findElement(By.id("day"))).selectByVisibleText("1");
    
    // 「開始時刻」に時分を入力
    new Select(driver.findElement(By.id("sthour"))).selectByVisibleText("10");
    new Select(driver.findElement(By.id("stmin"))).selectByVisibleText("00");
    
    // 「終了時刻」に時分を入力
    new Select(driver.findElement(By.id("endhour"))).selectByVisibleText("11");
    new Select(driver.findElement(By.id("endmin"))).selectByVisibleText("00");
    
    // 「定員」に指定範囲外である「51」を入力
    driver.findElement(By.id("vacantseats")).clear();
    driver.findElement(By.id("vacantseats")).sendKeys("51");
    // 入力画面をエビデンス
    file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(file, new File("capture/errormessage/rangeVacantseats/vacantseatsIsFiftyOnePage2.png"));
    
    // 「確認」ボタンを押下（「定員」の値が50以下でない為、、エラー。admin/input.htmlへ遷移）
    driver.findElement(By.name("confirm")).click();
    
    // 講座登録画面へ遷移しているかの確認 
    assertEquals("講座登録", getCurrentFunctionName());
    
    // エラー画面をエビデンス
    file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(file, new File("capture/errormessage/rangeVacantseats/errorPage2.png"));
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
  
  // 遷移した画面のh1テキストを取得
  public String getCurrentFunctionName() {
	  return driver.findElement(By.cssSelector("body h1")).getText();
  }
}
