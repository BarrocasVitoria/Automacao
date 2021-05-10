package Firefox;

import org.junit.After;


import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class ExecutarFirefox {	
	
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
	public void teste(){	
		driver.get("http://www.google.com");
		System.out.println(driver.getTitle());
	}	
}										