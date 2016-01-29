package org.jbehave.web.selenium;


import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.openqa.selenium.By.id;

import org.jbehave.business.StockAlertStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"selenium-applicationContext.xml"})
public class TraderIsAlertedSelenium {

	@Autowired
	private WebDriver driver;
	
	@Test
	public void stockStatusShouldBeShowedInWebPage()  {

        driver.get("http://localhost:8080/web/");
		
		WebElement name = driver.findElement(id("name"));
		WebElement threshold = driver.findElement(id("threshold"));
		WebElement tradeAt = driver.findElement(id("tradeAt"));
		WebElement submit = driver.findElement(id("submit"));
		
		name.sendKeys("STK");
		threshold.clear();
		threshold.sendKeys("15");
		tradeAt.clear();
		tradeAt.sendKeys("5");
		
		submit.submit();
		
		WebElement element = driver.findElement(id("result"));
		assertThat(element.getText(), is(StockAlertStatus.OFF.name()));
		driver.quit();
	}
	
}
