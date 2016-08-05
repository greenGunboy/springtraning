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
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.Select;

public class Selenium {
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
	  
    driver = new FirefoxDriver();
    
    baseUrl = "http://localhost:8080/";
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  }

  @Test
  public void testSelenium() throws Exception {
    driver.get(baseUrl + "/admin/menu");
    
 /**********************************エビデンス*****************************/
    File file = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(file, new File("capture/topPage.png"));
 /**********************************************************************/
    driver.findElement(By.name("courseregister")).click();
    
    File file1 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(file1, new File("capture/input.png"));
    driver.findElement(By.name("confirm")).click();
    
    driver.findElement(By.id("courseno")).clear();
    driver.findElement(By.id("courseno")).sendKeys("RRRR");
    driver.findElement(By.id("coursename")).clear();
    driver.findElement(By.id("coursename")).sendKeys("12324");
    new Select(driver.findElement(By.id("year"))).selectByVisibleText("2017");
    new Select(driver.findElement(By.id("month"))).selectByVisibleText("1");
    new Select(driver.findElement(By.id("day"))).selectByVisibleText("1");
    new Select(driver.findElement(By.id("sthour"))).selectByVisibleText("11");
    new Select(driver.findElement(By.id("stmin"))).selectByVisibleText("00");
    new Select(driver.findElement(By.id("endhour"))).selectByVisibleText("12");
    new Select(driver.findElement(By.id("endmin"))).selectByVisibleText("00");
    
    driver.findElement(By.id("vacantseats")).clear();
    File file2 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(file2, new File("capture/errormassage.png"));
    
    driver.findElement(By.id("vacantseats")).sendKeys("5");
    
    driver.findElement(By.name("confirm")).click();
    File file3 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(file3, new File("capture/conf.png"));
 
    driver.findElement(By.name("register")).click();
    File file4 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
    FileUtils.copyFile(file4, new File("capture/endPage.png"));
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
