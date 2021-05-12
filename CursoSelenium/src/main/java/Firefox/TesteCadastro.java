package Firefox;

import org.junit.After;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class TesteCadastro {
	
	private WebDriver driver;
	private DSL dsl;

	@Before
	public void inicializa() { 
		System.setProperty("webdriver.gecko.driver", "C:/devtools/apache-maven-3.6.3-bin/"
				+ "driver/geckodriver.exe");   	
		driver = new FirefoxDriver();
		driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
		dsl = new DSL(driver);
	}

	@After
	public void finaliza() {
		driver.quit();
	}
	
	@Test
	public void deveRealizarCadastroComSucesso(){
		dsl.escreve("elementosForm:nome", "Pablo");
		dsl.escreve("elementosForm:sobrenome", "Escobar");
		dsl.clicarRadio("elementosForm:sexo:0");
	
		dsl.clicarRadio("elementosForm:comidaFavorita:2");
		dsl.selecionarCombo("elementosForm:escolaridade", "Mestrado");
		dsl.selecionarCombo("elementosForm:esportes", "Natacao");
		dsl.clicarBotao("elementosForm:cadastrar");
		
		Assert.assertTrue(dsl.obterTexto("resultado").startsWith("Cadastrado!"));
		Assert.assertTrue(dsl.obterTexto("descNome").endsWith("Pablo"));
		Assert.assertEquals("Sobrenome: Escobar", dsl.obterTexto("descSobrenome"));
		Assert.assertEquals("Sexo: Masculino", dsl.obterTexto("descSexo"));
		Assert.assertEquals("Comida: Pizza", dsl.obterTexto("descComida"));	
		Assert.assertEquals("Escolaridade: mestrado", dsl.obterTexto("descEscolaridade"));
		Assert.assertEquals("Esportes: Natacao", dsl.obterTexto("descEsportes"));
	}
	
	@Test
	public void deveValidarNomeObrigatorio() {	
	driver.findElement(By.id("elementosForm:cadastrar")).click();
    Alert alert = driver.switchTo().alert();
    Assert.assertEquals("Nome eh obrigatorio", alert.getText());
    driver.quit();
	}
	
	@Test
	public void deveValidarSobrenomeObrigatorio() {
	driver.findElement(By.id("elementosForm:nome")).sendKeys("Nome qualquer");
	driver.findElement(By.id("elementosForm:cadastrar")).click();
    Alert alert = driver.switchTo().alert();
    Assert.assertEquals("Sobrenome eh obrigatorio", alert.getText());
    driver.quit();
	}
	
	@Test
	public void deveValidarSexoObrigatorio() {
	driver.findElement(By.id("elementosForm:nome")).sendKeys("Nome qualquer");
	driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Sobrenome qualquer");
	driver.findElement(By.id("elementosForm:cadastrar")).click();
    Alert alert = driver.switchTo().alert();
    Assert.assertEquals("Sexo eh obrigatorio", alert.getText());
    driver.quit();
	}
	
	@Test
	public void deveValidarComidaVegetariana() {
	driver.findElement(By.id("elementosForm:nome")).sendKeys("Nome qualquer");
	driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Sobrenome qualquer");
	driver.findElement(By.id("elementosForm:sexo:1")).click();
	driver.findElement(By.id("elementosForm:comidaFavorita:0")).click();
	driver.findElement(By.id("elementosForm:comidaFavorita:3")).click();
	driver.findElement(By.id("elementosForm:cadastrar")).click();
    Alert alert = driver.switchTo().alert();
    Assert.assertEquals("Tem certeza que voce eh vegetariano?", alert.getText());
    driver.quit();
	}
	
	@Test
	public void deveValidarEsportistaIndeciso() {
	driver.findElement(By.id("elementosForm:nome")).sendKeys("Nome qualquer");
	driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Sobrenome qualquer");
	driver.findElement(By.id("elementosForm:sexo:1")).click();
	driver.findElement(By.id("elementosForm:comidaFavorita:0")).click();
	Select combo = new Select(driver.findElement(By.id("elementosForm:esportes")));
	combo.selectByVisibleText("Karate");
	combo.selectByVisibleText("O que eh esporte?");
	driver.findElement(By.id("elementosForm:cadastrar")).click();
    Alert alert = driver.switchTo().alert();
    Assert.assertEquals("Voce faz esporte ou nao?", alert.getText());
    driver.quit();
	}

}