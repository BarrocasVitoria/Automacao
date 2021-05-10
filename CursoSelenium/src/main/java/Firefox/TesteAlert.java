package Firefox;

import org.junit.After;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;	
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TesteAlert {
	
	private WebDriver driver;

	@Before
	public void inicializa() { 
		System.setProperty("webdriver.gecko.driver", "C:/devtools/apache-maven-3.6.3-bin/"
				+ "driver/geckodriver.exe");   	
		driver = new FirefoxDriver();
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
	}

	@After
	public void finaliza() {
		driver.quit();
	}
	
	
	@Test
	public void deveInteragirComAlertSimples(){
		driver.findElement(By.id("alert")).click();
		Alert alert = driver.switchTo().alert();
		String texto = alert .getText();
		//Assert.assertEquals("Alert Simples", texto);
		Assert.assertEquals("Alert Simples", alert.getText());
		//alert.accept();
		
		driver.findElement(By.id("elementosForm:nome")).sendKeys(texto);
		
	    }
	
	@Test
	public void deveInteragirComAlertConfirm(){
		//Aceitar
		driver.findElement(By.id("confirm")).click();
		Alert alert = driver.switchTo().alert();
		Assert.assertEquals("Confirm Simples", alert.getText());
		alert.accept();		
		Assert.assertEquals("Confirmado", alert.getText());
		alert.accept();
		
		//Negar
		driver.findElement(By.id("confirm")).click();
		alert = driver.switchTo().alert();
		Assert.assertEquals("Confirm Simples", alert.getText());
		alert.dismiss();	
		Assert.assertEquals("Negado", alert.getText());
		alert.dismiss();
		
		driver.quit();
	    }
	
	@Test
	public void deveInteragirComPrompt(){
		driver.findElement(By.id("prompt")).click();
		Alert alerta = driver.switchTo().alert(); 
		Assert.assertEquals("Digite um numero", alerta.getText());
		alerta.sendKeys("12");
		alerta.accept();
		Assert.assertEquals("Era 12?", alerta.getText());
		alerta.accept();
		Assert.assertEquals(":D", alerta.getText());
		alerta.accept();
		
		driver.quit();
        }
}