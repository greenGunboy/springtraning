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
import org.openqa.selenium.support.ui.Select;

public class TotalConfigSelenium {
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
   * 講座検索から修正までの処理
   * @throws Exception
   */
  @Test
  public void testTotalConfigComplete() throws Exception {
    driver.get(baseUrl + "/admin/menu");
    
    // トップページをエビデンス
    File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(file, new File("totalCapture/completeConfig/toppage.png"));
    
    // 「講座修正削除」ボタン押下（total/input.htmlへ遷移）
    driver.findElement(By.name("courseedit")).click();
    
    // 検索画面をエビデンス
    file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(file, new File("totalCapture/completeConfig/searchPage.png"));
    
    // 「検索」ボタン押下
    driver.findElement(By.cssSelector("input.btn")).click();
    
    // 検索一覧表示画面をエビデンス
    file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(file, new File("totalCapture/completeConfig/searchListPage.png"));
    
    // 「修正」ボタン押下
    driver.findElement(By.name("edit")).click();
    
    // 講座修正画面をエビデンス
    file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(file, new File("totalCapture/completeConfig/courseEditPage.png"));
    
    // 「講座名」に修正値を入力
    driver.findElement(By.id("coursename")).clear();
    driver.findElement(By.id("coursename")).sendKeys("coursename");
    
    // 「定員」に修正値を入力
    driver.findElement(By.id("vacantseats")).clear();
    driver.findElement(By.id("vacantseats")).sendKeys("50");
    
    // 入力完了画面をエビデンス
    file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(file, new File("totalCapture/completeConfig/courseEditFillPage.png"));
    
    // 「確認」ボタン押下
    driver.findElement(By.name("confirm")).click();
    
    // 修正確認画面をエビデンス
    file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(file, new File("totalCapture/completeConfig/courseEditConfirmPage.png"));
    
    // 「修正」ボタン押下
    driver.findElement(By.name("edit")).click();
    
    // 修正完了画面をエビデンス
    file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(file, new File("totalCapture/completeConfig/endPage.png"));
  }
  
  /**
   * 検索画面、エラーメッセージ表示確認
   * "「定員」は形式が不正です"
   * @throws Exception
   */
  @Test
  public void testNumberFormatVacantseats() throws Exception {
    driver.get(baseUrl + "/admin/menu");
    
    // 「講座修正削除」ボタン押下（total/input.htmlへ遷移）
    driver.findElement(By.name("courseedit")).click();
    
    // 講座検索画面をエビデンス
    File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(file, new File("totalCapture/testNumberFormatVacantseats/topPage.png"));
    
    // 「定員」に不正形式値を入力
    driver.findElement(By.id("minvacantseats")).clear();
    driver.findElement(By.id("minvacantseats")).sendKeys("あ");
    
    // 「定員」に不正形式値を入力
    driver.findElement(By.id("maxvacantseats")).clear();
    driver.findElement(By.id("maxvacantseats")).sendKeys("あ");
    
    // 検索条件入力完了画面をエビデンス
    file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(file, new File("totalCapture/testNumberFormatVacantseats/inputPage.png"));
    
    // 「検索」ボタン押下(「定員」の形式が不正な為、total/input.htmlへ遷移）
    driver.findElement(By.cssSelector("input.btn")).click();
    
    // エラーメッセージ表示画面をエビデンス
    file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(file, new File("totalCapture/testNumberFormatVacantseats/errorPage.png"));
  }
  
  /**
   * 修正画面、エラーメッセージ表示確認
   * "「講座名」は必須項目です"
   * @throws Exception
   */
  @Test
  public void testRequiredCoursename() throws Exception {
    driver.get(baseUrl + "/admin/menu");
    
    // 「講座修正削除」ボタン押下（total/input.htmlへ遷移）
    driver.findElement(By.name("courseedit")).click();
    // 「検索」ボタン押下(total/conf.htmlへ遷移）
    driver.findElement(By.cssSelector("input.btn")).click();
    // 「修正」ボタン押下（total/editconf.htmlへ遷移）
    driver.findElement(By.name("edit")).click();
    
    // 講座修正画面をエビデンス
    File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(file, new File("totalCapture/testRequiredCoursename/inputPage.png"));
    
    // 「講座名」に修正値を入力
    driver.findElement(By.id("coursename")).clear();
    driver.findElement(By.id("coursename")).sendKeys("");
    
    // 入力完了画面をエビデンス
    file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(file, new File("totalCapture/testRequiredCoursename/fillInputPage.png"));
    
    // 「確認」ボタン押下
    driver.findElement(By.name("confirm")).click();
    
    // エラーメッセージ表示画面をエビデンス
    file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(file, new File("totalCapture/testRequiredCoursename/error.png"));
  }
  
  /**
   * 修正画面、エラーメッセージ表示確認
   * "「講座開催日」は必須項目です"
   * @throws Exception
   */
  @Test
  public void testRequiredTheDate() throws Exception {
    driver.get(baseUrl + "/admin/menu");
    
    // 「講座修正削除」ボタン押下（total/input.htmlへ遷移）
    driver.findElement(By.name("courseedit")).click();
    // 「検索」ボタン押下(total/conf.htmlへ遷移）
    driver.findElement(By.cssSelector("input.btn")).click();
    // 「修正」ボタン押下（total/editconf.htmlへ遷移）
    driver.findElement(By.name("edit")).click();
    
    // 講座修正画面をエビデンス
    File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(file, new File("totalCapture/testRequiredTheDate/inputPage.png"));
    
    // 「講座開催日」は未入力
    new Select(driver.findElement(By.id("year"))).selectByVisibleText("");
    new Select(driver.findElement(By.id("month"))).selectByVisibleText("");
    new Select(driver.findElement(By.id("day"))).selectByVisibleText("");
    
    // 入力完了画面をエビデンス
    file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(file, new File("totalCapture/testRequiredTheDate/fillInputPage.png"));
    
    // 「確認」ボタン押下
    driver.findElement(By.name("confirm")).click();
    
    // エラーメッセージ表示画面をエビデンス
    file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(file, new File("totalCapture/testRequiredTheDate/error.png"));
  }
  
  /**
   * 修正画面、エラーメッセージ表示確認
   * "「開始時刻」は必須項目です"
   * @throws Exception
   */
  @Test
  public void testRequiredStartTime() throws Exception {
    driver.get(baseUrl + "/admin/menu");
    
    // 「講座修正削除」ボタン押下（total/input.htmlへ遷移）
    driver.findElement(By.name("courseedit")).click();
    // 「検索」ボタン押下(total/conf.htmlへ遷移）
    driver.findElement(By.cssSelector("input.btn")).click();
    // 「修正」ボタン押下（total/editconf.htmlへ遷移）
    driver.findElement(By.name("edit")).click();
    
    // 講座修正画面をエビデンス
    File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(file, new File("totalCapture/testRequiredStartTime/inputPage.png"));
    
    // 「開始時刻」は未入力
    new Select(driver.findElement(By.id("sthour"))).selectByVisibleText("");
    new Select(driver.findElement(By.id("stmin"))).selectByVisibleText("");
    
    // 入力完了画面をエビデンス
    file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(file, new File("totalCapture/testRequiredStartTime/fillInputPage.png"));
    
    // 「確認」ボタン押下
    driver.findElement(By.name("confirm")).click();
    
    // エラーメッセージ表示画面をエビデンス
    file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(file, new File("totalCapture/testRequiredStartTime/error.png"));
  }
  
  /**
   * 修正画面、エラーメッセージ表示確認
   * "「終了時刻」は必須項目です"
   * @throws Exception
   */
  @Test
  public void testRequiredEndTime() throws Exception {
    driver.get(baseUrl + "/admin/menu");
    
    // 「講座修正削除」ボタン押下（total/input.htmlへ遷移）
    driver.findElement(By.name("courseedit")).click();
    // 「検索」ボタン押下(total/conf.htmlへ遷移）
    driver.findElement(By.cssSelector("input.btn")).click();
    // 「修正」ボタン押下（total/editconf.htmlへ遷移）
    driver.findElement(By.name("edit")).click();
    
    // 講座修正画面をエビデンス
    File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(file, new File("totalCapture/testRequiredEndTime/inputPage.png"));
    
    // 「終了時刻」は未入力
    new Select(driver.findElement(By.id("endhour"))).selectByVisibleText("");
    new Select(driver.findElement(By.id("endmin"))).selectByVisibleText("");
    
    // 入力完了画面をエビデンス
    file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(file, new File("totalCapture/testRequiredEndTime/fillInputPage.png"));
    
    // 「確認」ボタン押下
    driver.findElement(By.name("confirm")).click();
    
    // エラーメッセージ表示画面をエビデンス
    file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(file, new File("totalCapture/testRequiredEndTime/error.png"));
  }
  
  /**
   * 修正画面、エラーメッセージ表示確認
   * "「終了時刻」は「開始時刻」よりも後の時刻を入力してください"
   * @throws Exception
   */
  @Test
  public void testTimeContradiction() throws Exception {
    driver.get(baseUrl + "/admin/menu");
    
    // 「講座修正削除」ボタン押下（total/input.htmlへ遷移）
    driver.findElement(By.name("courseedit")).click();
    // 「検索」ボタン押下(total/conf.htmlへ遷移）
    driver.findElement(By.cssSelector("input.btn")).click();
    // 「修正」ボタン押下（total/editconf.htmlへ遷移）
    driver.findElement(By.name("edit")).click();
    
    // 講座修正画面をエビデンス
    File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(file, new File("totalCapture/testTimeContradiction/inputPage.png"));
    
    // 「開始時刻」に「終了時刻」よりも後の時分を入力
    new Select(driver.findElement(By.id("sthour"))).selectByVisibleText("12");
    new Select(driver.findElement(By.id("stmin"))).selectByVisibleText("00");
    
    // 「終了時刻」に「開始時刻」よりも前の時分を入力
    new Select(driver.findElement(By.id("endhour"))).selectByVisibleText("11");
    new Select(driver.findElement(By.id("endmin"))).selectByVisibleText("00");
    
    // 入力完了画面をエビデンス
    file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(file, new File("totalCapture/testTimeContradiction/fillInputPage.png"));
    
    // 「確認」ボタン押下
    driver.findElement(By.name("confirm")).click();
    
    // エラーメッセージ表示画面をエビデンス
    file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(file, new File("totalCapture/testTimeContradiction/error.png"));
  }
  
  /**
   * 修正画面、エラーメッセージ表示確認
   * "「定員」は必須項目です"
   * @throws Exception
   */
  @Test
  public void testRequiredVacantseats() throws Exception {
    driver.get(baseUrl + "/admin/menu");
    
    // 「講座修正削除」ボタン押下（total/input.htmlへ遷移）
    driver.findElement(By.name("courseedit")).click();
    // 「検索」ボタン押下(total/conf.htmlへ遷移）
    driver.findElement(By.cssSelector("input.btn")).click();
    // 「修正」ボタン押下（total/editconf.htmlへ遷移）
    driver.findElement(By.name("edit")).click();
    
    // 講座修正画面をエビデンス
    File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(file, new File("totalCapture/testRequiredVacantseats/inputPage.png"));
    
    // 「定員」は未入力
    driver.findElement(By.id("vacantseats")).clear();
    driver.findElement(By.id("vacantseats")).sendKeys("");
    
    // 入力完了画面をエビデンス
    file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(file, new File("totalCapture/testRequiredVacantseats/fillInputPage.png"));
    
    // 「確認」ボタン押下
    driver.findElement(By.name("confirm")).click();
    
    // エラーメッセージ表示画面をエビデンス
    file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(file, new File("totalCapture/testRequiredVacantseats/error.png"));
  }
  
  /**
   * 修正画面、エラーメッセージ表示確認
   * "「定員」は1以上、50以下で入力してください"
   * @throws Exception
   */
  @Test
  public void testMinVacantseats() throws Exception {
    driver.get(baseUrl + "/admin/menu");
    
    // 「講座修正削除」ボタン押下（total/input.htmlへ遷移）
    driver.findElement(By.name("courseedit")).click();
    // 「検索」ボタン押下(total/conf.htmlへ遷移）
    driver.findElement(By.cssSelector("input.btn")).click();
    // 「修正」ボタン押下（total/editconf.htmlへ遷移）
    driver.findElement(By.name("edit")).click();
    
    // 講座修正画面をエビデンス
    File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(file, new File("totalCapture/testMinVacantseats/inputPage.png"));
    
    // 「定員」に指定範囲を下回る修正値を入力
    driver.findElement(By.id("vacantseats")).clear();
    driver.findElement(By.id("vacantseats")).sendKeys("0");
    
    // 入力完了画面をエビデンス
    file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(file, new File("totalCapture/testMinVacantseats/fillInputPage.png"));
    
    // 「確認」ボタン押下
    driver.findElement(By.name("confirm")).click();
    
    // エラーメッセージ表示画面をエビデンス
    file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(file, new File("totalCapture/testMinVacantseats/error.png"));
  }
  
  /**
   * 修正画面、エラーメッセージ表示確認
   * "「定員」は1以上、50以下で入力してください"
   * @throws Exception
   */
  @Test
  public void testMaxVacantseats() throws Exception {
    driver.get(baseUrl + "/admin/menu");
    
    // 「講座修正削除」ボタン押下（total/input.htmlへ遷移）
    driver.findElement(By.name("courseedit")).click();
    // 「検索」ボタン押下(total/conf.htmlへ遷移）
    driver.findElement(By.cssSelector("input.btn")).click();
    // 「修正」ボタン押下（total/editconf.htmlへ遷移）
    driver.findElement(By.name("edit")).click();
    
    // 講座修正画面をエビデンス
    File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(file, new File("totalCapture/testMaxVacantseats/inputPage.png"));
    
    // 「定員」に指定範囲を上回る修正値を入力
    driver.findElement(By.id("vacantseats")).clear();
    driver.findElement(By.id("vacantseats")).sendKeys("51");
    
    // 入力完了画面をエビデンス
    file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(file, new File("totalCapture/testMaxVacantseats/fillInputPage.png"));
    
    // 「確認」ボタン押下
    driver.findElement(By.name("confirm")).click();
    
    // エラーメッセージ表示画面をエビデンス
    file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(file, new File("totalCapture/testMaxVacantseats/error.png"));
  }
  
  /**
   * 選択された講座情報を削除する処理
   * @throws Exception
   */
  @Test
  public void testDelete() throws Exception {
    driver.get(baseUrl + "/admin/menu");
    
    // 「講座修正削除」ボタン押下（total/input.htmlへ遷移）
    driver.findElement(By.name("courseedit")).click();
    
    // トップ画面をエビデンス
    File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(file, new File("totalCapture/testDelete/topPage.png"));
    
    // 「検索」ボタン押下(total/conf.htmlへ遷移）
    driver.findElement(By.cssSelector("input.btn")).click();
    
    // 講座検索画面をエビデンス
    file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(file, new File("totalCapture/testDelete/inputPage.png"));
    
    // 「削除」ボタン押下（total/deleteconf.htmlへ遷移）
    driver.findElement(By.name("delete")).click();
    
    // 講座削除画面をエビデンス
    file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(file, new File("totalCapture/testDelete/confPage.png"));
    
    // 「削除」ボタン押下（total/deleteend.htmlへ遷移）
    driver.findElement(By.name("delete")).click();
    
    // 削除完了画面をエビデンス
    file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(file, new File("totalCapture/testDelete/endPage.png"));
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
