package Firefox;

import org.junit.After;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class TesteFramesEJanelas {
	
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
    public void deveInteragirComFrames() {
		driver.switchTo().frame("frame1");
		driver.findElement(By.id("frameButton")).click();
		Alert alert = driver.switchTo().alert();
		String msg = alert.getText(); 
		Assert.assertEquals("Frame Ok!", msg);
		alert.accept(); 
		
		driver.switchTo().defaultContent();
		driver.findElement(By.id("elementosForm;nome")).sendKeys(msg);
    }
    
    @Test
    public void deveInteragirComJanelas() {
		driver.findElement(By.id("buttonPopUpEasy")).click();
		driver.switchTo().window("Popup");
    	driver.findElement(By.tagName("textarea")).sendKeys("Deu certo?");
    	driver.close();
    	driver.switchTo().window("");
    	driver.findElement(By.tagName("textarea")).sendKeys("e agora?");
    }
    
    @Test
    public void deveInteragirComJanelaSemTitulo() {
		driver.findElement(By.id("buttonPopUpHard")).click();
		System.out.println(driver.getWindowHandle()); //retorna o id de uma janela, valor no console
		System.out.println(driver.getWindowHandles()); //reotrna os id de todas as janelas, valor no console
		driver.switchTo().window((String)driver.getWindowHandles().toArray()[1]);
		driver.findElement(By.tagName("textarea")).sendKeys("Deu certo?");
		driver.switchTo().window((String)driver.getWindowHandles().toArray()[0]);
		driver.findElement(By.tagName("textarea")).sendKeys("E agora?");
    }
}