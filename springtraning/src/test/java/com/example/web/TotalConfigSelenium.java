package com.example.web;

import java.util.regex.Pattern;
import java.io.File;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
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
    FileUtils.copyFile(file, new File("totalCapture/complete/toppage.png"));
    
    // 「講座修正削除」ボタン押下（tatal/input.htmlへ遷移）
    driver.findElement(By.name("courseedit")).click();
    
    // 「検索」ボタン押下
    driver.findElement(By.cssSelector("input.btn")).click();
    
    // 「修正」ボタン押下
    driver.findElement(By.name("edit")).click();
    
    // 「講座名」に修正値を入力
    driver.findElement(By.id("coursename")).clear();
    driver.findElement(By.id("coursename")).sendKeys("coursename");
    
    // 「定員」に修正値を入力
    driver.findElement(By.id("vacantseats")).clear();
    driver.findElement(By.id("vacantseats")).sendKeys("50");
    
    // 「確認」ボタン押下
    driver.findElement(By.name("confirm")).click();
    
    // 「修正」ボタン押下
    driver.findElement(By.name("edit")).click();
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
