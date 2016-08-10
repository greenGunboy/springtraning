package com.example;

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

public class CourseSelenium {
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
   * 全未入力で確認ボタン押下時のエラーチェックを確認した後、
   * 利用者情報と希望講座を入力し、登録するまでの処理
   * @throws Exception
   */
  @Test
  public void testCourseSelenium() throws Exception {
    driver.get(baseUrl + "/course/menu");
    
    // トップページをエビデンス
    File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(file, new File("courseCapture/complete/toppage.png"));
    
    // 「講座申込み」ボタンを押下（course/input.htmlへ遷移）
    driver.findElement(By.cssSelector("input.btn")).click();
    
    // 入力画面をエビデンス
    file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(file, new File("courseCapture/complete/input.png"));
    
    // 「確認」ボタンを押下（未入力の為、エラー。course/input.htmlへ遷移）
    driver.findElement(By.cssSelector("input.btn")).click();
    
    // エラーメッセージ表示画面をエビデンス
    file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(file, new File("courseCapture/complete/errormessage.png"));
    
    // 「お名前」に値を入力
    driver.findElement(By.id("name")).clear();
    driver.findElement(By.id("name")).sendKeys("高橋　翼");
    
    // 「フリガナ」に値を入力
    driver.findElement(By.id("furigana")).clear();
    driver.findElement(By.id("furigana")).sendKeys("タカハシ　ツバサ");
    
    // 「Eメールアドレス」に値を入力
    driver.findElement(By.id("mail")).clear();
    driver.findElement(By.id("mail")).sendKeys("takahashi@techfun.jp");
    
    // 「性別」に値を入力
    driver.findElement(By.id("gender1")).click();
    
    // 「生年月日」に値を入力
    new Select(driver.findElement(By.id("year"))).selectByVisibleText("1992");
    new Select(driver.findElement(By.id("month"))).selectByVisibleText("10");
    new Select(driver.findElement(By.id("day"))).selectByVisibleText("22");
    
    // 「電話番号」に値を入力
    driver.findElement(By.id("tel")).clear();
    driver.findElement(By.id("tel")).sendKeys("09012341234");
    
    // 「希望講座」に値を入力
    driver.findElement(By.id("applyCourse1")).click();
    driver.findElement(By.id("applyCourse2")).click();
    
    // 「備考欄」に値を入力
    driver.findElement(By.id("remarks")).clear();
    driver.findElement(By.id("remarks")).sendKeys("aaaaa\n\nbbb\ncccc");
    
    // 入力画面をエビデンス
    file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(file, new File("courseCapture/complete/inputfill.png"));
    
    // 「確認」ボタンを押下（course/conf.htmlへ遷移）
    driver.findElement(By.cssSelector("input.btn")).click();
    
    // 申込み確認画面をエビデンス
    file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(file, new File("courseCapture/complete/conf.png"));
    
    // 「申込み」ボタンを押下（course/end.htmlへ遷移）
    driver.findElement(By.name("apply")).click();
    
    // 申込み完了画面をエビデンス
    file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(file, new File("courseCapture/complete/end.png")); 
  }
  
  /**
   * エラーメッセージ"「お名前」は必須項目です。"を表示確認
   * @throws Exception
   */
  @Test
  public void testRequiredName() throws Exception {
    driver.get(baseUrl + "/course/menu");
    
    // トップページをエビデンス
    File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(file, new File("courseCapture/testRequiredName/toppage.png"));
    
    // 「講座申込み」ボタンを押下（course/input.htmlへ遷移）
    driver.findElement(By.cssSelector("input.btn")).click();
    
    // 入力画面をエビデンス
    file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(file, new File("courseCapture/testRequiredName/input.png"));
    
    // 「お名前」は未入力
    driver.findElement(By.id("name")).clear();
    driver.findElement(By.id("name")).sendKeys("");
    
    // 「フリガナ」に値を入力
    driver.findElement(By.id("furigana")).clear();
    driver.findElement(By.id("furigana")).sendKeys("タカハシ　ツバサ");
    
    // 「Eメールアドレス」に値を入力
    driver.findElement(By.id("mail")).clear();
    driver.findElement(By.id("mail")).sendKeys("takahashi@techfun.jp");
    
    // 「性別」に値を入力
    driver.findElement(By.id("gender1")).click();
    
    // 「生年月日」に値を入力
    new Select(driver.findElement(By.id("year"))).selectByVisibleText("1992");
    new Select(driver.findElement(By.id("month"))).selectByVisibleText("10");
    new Select(driver.findElement(By.id("day"))).selectByVisibleText("22");
    
    // 「電話番号」に値を入力
    driver.findElement(By.id("tel")).clear();
    driver.findElement(By.id("tel")).sendKeys("09012341234");
    
    // 「希望講座」に値を入力
    driver.findElement(By.id("applyCourse1")).click();
    driver.findElement(By.id("applyCourse2")).click();
    
    // 「備考欄」に値を入力
    driver.findElement(By.id("remarks")).clear();
    driver.findElement(By.id("remarks")).sendKeys("aaaaa\n\nbbb\ncccc");
    
    // 入力画面をエビデンス
    file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(file, new File("courseCapture/testRequiredName/inputfill.png"));
    
    // 「確認」ボタンを押下（course/conf.htmlへ遷移）
    driver.findElement(By.cssSelector("input.btn")).click();
    
    // エラーメッセージ表示をエビデンス
    file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(file, new File("courseCapture/testRequiredName/error.png"));
  }
  
  /**
   * エラーメッセージ"「フリガナ」は必須項目です。"を表示確認
   * @throws Exception
   */
  @Test
  public void testRequiredFurigana() throws Exception {
    driver.get(baseUrl + "/course/menu");
    
    // トップページをエビデンス
    File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(file, new File("courseCapture/testRequiredFurigana/toppage.png"));
    
    // 「講座申込み」ボタンを押下（course/input.htmlへ遷移）
    driver.findElement(By.cssSelector("input.btn")).click();
    
    // 入力画面をエビデンス
    file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(file, new File("courseCapture/testRequiredFurigana/input.png"));
    
    // 「お名前」は未入力
    driver.findElement(By.id("name")).clear();
    driver.findElement(By.id("name")).sendKeys("高橋　翼");
    
    // 「フリガナ」は未入力
    driver.findElement(By.id("furigana")).clear();
    driver.findElement(By.id("furigana")).sendKeys("");
    
    // 「Eメールアドレス」に値を入力
    driver.findElement(By.id("mail")).clear();
    driver.findElement(By.id("mail")).sendKeys("takahashi@techfun.jp");
    
    // 「性別」に値を入力
    driver.findElement(By.id("gender1")).click();
    
    // 「生年月日」に値を入力
    new Select(driver.findElement(By.id("year"))).selectByVisibleText("1992");
    new Select(driver.findElement(By.id("month"))).selectByVisibleText("10");
    new Select(driver.findElement(By.id("day"))).selectByVisibleText("22");
    
    // 「電話番号」に値を入力
    driver.findElement(By.id("tel")).clear();
    driver.findElement(By.id("tel")).sendKeys("09012341234");
    
    // 「希望講座」に値を入力
    driver.findElement(By.id("applyCourse1")).click();
    driver.findElement(By.id("applyCourse2")).click();
    
    // 「備考欄」に値を入力
    driver.findElement(By.id("remarks")).clear();
    driver.findElement(By.id("remarks")).sendKeys("aaaaa\n\nbbb\ncccc");
    
    // 入力画面をエビデンス
    file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(file, new File("courseCapture/testRequiredFurigana/inputfill.png"));
    
    // 「確認」ボタンを押下（course/conf.htmlへ遷移）
    driver.findElement(By.cssSelector("input.btn")).click();
    
    // エラーメッセージ表示をエビデンス
    file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(file, new File("courseCapture/testRequiredFurigana/error.png"));
  }
  
  /**
   * エラーメッセージ"「Eメールアドレス」は必須項目です。"を表示確認
   * @throws Exception
   */
  @Test
  public void testRequiredMail() throws Exception {
    driver.get(baseUrl + "/course/menu");
    
    // トップページをエビデンス
    File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(file, new File("courseCapture/testRequiredMail/toppage.png"));
    
    // 「講座申込み」ボタンを押下（course/input.htmlへ遷移）
    driver.findElement(By.cssSelector("input.btn")).click();
    
    // 入力画面をエビデンス
    file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(file, new File("courseCapture/testRequiredMail/input.png"));
    
    // 「お名前」は未入力
    driver.findElement(By.id("name")).clear();
    driver.findElement(By.id("name")).sendKeys("高橋　翼");
    
    // 「フリガナ」に値を入力
    driver.findElement(By.id("furigana")).clear();
    driver.findElement(By.id("furigana")).sendKeys("タカハシ　ツバサ");
    
    // 「Eメールアドレス」は未入力
    driver.findElement(By.id("mail")).clear();
    driver.findElement(By.id("mail")).sendKeys("");
    
    // 「性別」に値を入力
    driver.findElement(By.id("gender1")).click();
    
    // 「生年月日」に値を入力
    new Select(driver.findElement(By.id("year"))).selectByVisibleText("1992");
    new Select(driver.findElement(By.id("month"))).selectByVisibleText("10");
    new Select(driver.findElement(By.id("day"))).selectByVisibleText("22");
    
    // 「電話番号」に値を入力
    driver.findElement(By.id("tel")).clear();
    driver.findElement(By.id("tel")).sendKeys("09012341234");
    
    // 「希望講座」に値を入力
    driver.findElement(By.id("applyCourse1")).click();
    driver.findElement(By.id("applyCourse2")).click();
    
    // 「備考欄」に値を入力
    driver.findElement(By.id("remarks")).clear();
    driver.findElement(By.id("remarks")).sendKeys("aaaaa\n\nbbb\ncccc");
    
    // 入力画面をエビデンス
    file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(file, new File("courseCapture/testRequiredMail/inputfill.png"));
    
    // 「確認」ボタンを押下（course/conf.htmlへ遷移）
    driver.findElement(By.cssSelector("input.btn")).click();
    
    // エラーメッセージ表示をエビデンス
    file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(file, new File("courseCapture/testRequiredMail/error.png"));
  }
  
  /**
   * エラーメッセージ"「Eメールアドレス」の形式が不正です。"を表示確認
   * @throws Exception
   */
  @Test
  public void testMailForm() throws Exception {
    driver.get(baseUrl + "/course/menu");
    
    // トップページをエビデンス
    File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(file, new File("courseCapture/testMailForm/toppage.png"));
    
    // 「講座申込み」ボタンを押下（course/input.htmlへ遷移）
    driver.findElement(By.cssSelector("input.btn")).click();
    
    // 入力画面をエビデンス
    file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(file, new File("courseCapture/testMailForm/input.png"));
    
    // 「お名前」は未入力
    driver.findElement(By.id("name")).clear();
    driver.findElement(By.id("name")).sendKeys("高橋　翼");
    
    // 「フリガナ」に値を入力
    driver.findElement(By.id("furigana")).clear();
    driver.findElement(By.id("furigana")).sendKeys("タカハシ　ツバサ");
    
    // 「Eメールアドレス」には不正な形式のアドレスを入力
    driver.findElement(By.id("mail")).clear();
    driver.findElement(By.id("mail")).sendKeys("tsubasa");
    
    // 「性別」に値を入力
    driver.findElement(By.id("gender1")).click();
    
    // 「生年月日」に値を入力
    new Select(driver.findElement(By.id("year"))).selectByVisibleText("1992");
    new Select(driver.findElement(By.id("month"))).selectByVisibleText("10");
    new Select(driver.findElement(By.id("day"))).selectByVisibleText("22");
    
    // 「電話番号」に値を入力
    driver.findElement(By.id("tel")).clear();
    driver.findElement(By.id("tel")).sendKeys("09012341234");
    
    // 「希望講座」に値を入力
    driver.findElement(By.id("applyCourse1")).click();
    driver.findElement(By.id("applyCourse2")).click();
    
    // 「備考欄」に値を入力
    driver.findElement(By.id("remarks")).clear();
    driver.findElement(By.id("remarks")).sendKeys("aaaaa\n\nbbb\ncccc");
    
    // 入力画面をエビデンス
    file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(file, new File("courseCapture/testMailForm/inputfill.png"));
    
    // 「確認」ボタンを押下（course/conf.htmlへ遷移）
    driver.findElement(By.cssSelector("input.btn")).click();
    
    // エラーメッセージ表示をエビデンス
    file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(file, new File("courseCapture/testMailForm/error.png"));
  }
  
  /**
   * エラーメッセージ"「生年月日」は必須項目です。"を表示確認
   * @throws Exception
   */
  @Test
  public void testRequiredBirthday() throws Exception {
    driver.get(baseUrl + "/course/menu");
    
    // トップページをエビデンス
    File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(file, new File("courseCapture/testRequiredBirthday/toppage.png"));
    
    // 「講座申込み」ボタンを押下（course/input.htmlへ遷移）
    driver.findElement(By.cssSelector("input.btn")).click();
    
    // 入力画面をエビデンス
    file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(file, new File("courseCapture/testRequiredBirthday/input.png"));
    
    // 「お名前」は未入力
    driver.findElement(By.id("name")).clear();
    driver.findElement(By.id("name")).sendKeys("高橋　翼");
    
    // 「フリガナ」に値を入力
    driver.findElement(By.id("furigana")).clear();
    driver.findElement(By.id("furigana")).sendKeys("タカハシ　ツバサ");
    
    // 「Eメールアドレス」に値を入力
    driver.findElement(By.id("mail")).clear();
    driver.findElement(By.id("mail")).sendKeys("takahashi@techfun.jp");
    
    // 「性別」に値を入力
    driver.findElement(By.id("gender1")).click();
    
    // 「生年月日」は未入力
    new Select(driver.findElement(By.id("year"))).selectByVisibleText("");
    new Select(driver.findElement(By.id("month"))).selectByVisibleText("");
    new Select(driver.findElement(By.id("day"))).selectByVisibleText("");
    
    // 「電話番号」に値を入力
    driver.findElement(By.id("tel")).clear();
    driver.findElement(By.id("tel")).sendKeys("09012341234");
    
    // 「希望講座」に値を入力
    driver.findElement(By.id("applyCourse1")).click();
    driver.findElement(By.id("applyCourse2")).click();
    
    // 「備考欄」に値を入力
    driver.findElement(By.id("remarks")).clear();
    driver.findElement(By.id("remarks")).sendKeys("aaaaa\n\nbbb\ncccc");
    
    // 入力画面をエビデンス
    file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(file, new File("courseCapture/testRequiredBirthday/inputfill.png"));
    
    // 「確認」ボタンを押下（course/conf.htmlへ遷移）
    driver.findElement(By.cssSelector("input.btn")).click();
    
    // エラーメッセージ表示をエビデンス
    file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(file, new File("courseCapture/testRequiredBirthday/error.png"));
  }
  
  /**
   * エラーメッセージ"「電話番号」は数字で入力してください。"を表示確認
   * @throws Exception
   */
  @Test
  public void testNumberOfExceptionTel() throws Exception {
    driver.get(baseUrl + "/course/menu");
    
    // トップページをエビデンス
    File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(file, new File("courseCapture/testNumberOfExceptionTel/toppage.png"));
    
    // 「講座申込み」ボタンを押下（course/input.htmlへ遷移）
    driver.findElement(By.cssSelector("input.btn")).click();
    
    // 入力画面をエビデンス
    file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(file, new File("courseCapture/testNumberOfExceptionTel/input.png"));
    
    // 「お名前」は未入力
    driver.findElement(By.id("name")).clear();
    driver.findElement(By.id("name")).sendKeys("高橋　翼");
    
    // 「フリガナ」は未入力
    driver.findElement(By.id("furigana")).clear();
    driver.findElement(By.id("furigana")).sendKeys("タカハシ　ツバサ");
    
    // 「Eメールアドレス」に値を入力
    driver.findElement(By.id("mail")).clear();
    driver.findElement(By.id("mail")).sendKeys("takahashi@techfun.jp");
    
    // 「性別」に値を入力
    driver.findElement(By.id("gender1")).click();
    
    // 「生年月日」に値を入力
    new Select(driver.findElement(By.id("year"))).selectByVisibleText("1992");
    new Select(driver.findElement(By.id("month"))).selectByVisibleText("10");
    new Select(driver.findElement(By.id("day"))).selectByVisibleText("22");
    
    // 「電話番号」に値を入力
    driver.findElement(By.id("tel")).clear();
    driver.findElement(By.id("tel")).sendKeys("あ");
    
    // 「希望講座」に値を入力
    driver.findElement(By.id("applyCourse1")).click();
    driver.findElement(By.id("applyCourse2")).click();
    
    // 「備考欄」に値を入力
    driver.findElement(By.id("remarks")).clear();
    driver.findElement(By.id("remarks")).sendKeys("aaaaa\n\nbbb\ncccc");
    
    // 入力画面をエビデンス
    file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(file, new File("courseCapture/testNumberOfExceptionTel/inputfill.png"));
    
    // 「確認」ボタンを押下（course/conf.htmlへ遷移）
    driver.findElement(By.cssSelector("input.btn")).click();
    
    // エラーメッセージ表示をエビデンス
    file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(file, new File("courseCapture/testNumberOfExceptionTel/error.png"));
  }
  
  /**
   * エラーメッセージ"「希望講座」は必須項目です。"を表示確認
   * @throws Exception
   */
  @Test
  public void testRequiredApplyCourse() throws Exception {
    driver.get(baseUrl + "/course/menu");
    
    // トップページをエビデンス
    File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(file, new File("courseCapture/testRequiredApplyCourse/toppage.png"));
    
    // 「講座申込み」ボタンを押下（course/input.htmlへ遷移）
    driver.findElement(By.cssSelector("input.btn")).click();
    
    // 入力画面をエビデンス
    file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(file, new File("courseCapture/testRequiredApplyCourse/input.png"));
    
    // 「お名前」は未入力
    driver.findElement(By.id("name")).clear();
    driver.findElement(By.id("name")).sendKeys("高橋　翼");
    
    // 「フリガナ」に値を入力
    driver.findElement(By.id("furigana")).clear();
    driver.findElement(By.id("furigana")).sendKeys("タカハシ　ツバサ");
    
    // 「Eメールアドレス」に値を入力
    driver.findElement(By.id("mail")).clear();
    driver.findElement(By.id("mail")).sendKeys("takahashi@techfun.jp");
    
    // 「性別」に値を入力
    driver.findElement(By.id("gender1")).click();
    
    // 「生年月日」に値を入力
    new Select(driver.findElement(By.id("year"))).selectByVisibleText("1992");
    new Select(driver.findElement(By.id("month"))).selectByVisibleText("10");
    new Select(driver.findElement(By.id("day"))).selectByVisibleText("22");
    
    // 「電話番号」に値を入力
    driver.findElement(By.id("tel")).clear();
    driver.findElement(By.id("tel")).sendKeys("09012341234");
    
    // 「希望講座」は未入力
//    driver.findElement(By.id("applyCourse1")).click();
//    driver.findElement(By.id("applyCourse2")).click();
    
    // 「備考欄」に値を入力
    driver.findElement(By.id("remarks")).clear();
    driver.findElement(By.id("remarks")).sendKeys("aaaaa\n\nbbb\ncccc");
    
    // 入力画面をエビデンス
    file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(file, new File("courseCapture/testRequiredApplyCourse/inputfill.png"));
    
    // 「確認」ボタンを押下（course/conf.htmlへ遷移）
    driver.findElement(By.cssSelector("input.btn")).click();
    
    // エラーメッセージ表示をエビデンス
    file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(file, new File("courseCapture/testRequiredApplyCourse/error.png"));
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
