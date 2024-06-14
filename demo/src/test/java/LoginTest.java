import static org.junit.jupiter.api.Assertions.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class LoginTest {
    private WebDriver navegador;

    @BeforeSuite
    public void setUp(){

        navegador = new ChromeDriver();
        navegador.get("https://the-internet.herokuapp.com/login");
    }

    @Test
    public void efetuarLogin(){

        WebElement loginForm = navegador.findElement(By.id("login"));

        //Preencher nome
        loginForm.findElement(By.id("username")).sendKeys("tomsmith");

        //Preencher senha
        loginForm.findElement(By.id("password")).sendKeys("SuperSecretPassword!");

        //Clicar no botão login
        loginForm.findElement(By.xpath("/html/body/div[2]/div/div/form/button/i")).click();

        //Extrair texto do elemento a ser verificado
        //String loginConfirma = navegador.getPageSource();
        String loginConfirma = navegador.findElement(By.id("flash")).getText();

        //Checar se texto de login aparece
        assertTrue(loginConfirma.contains("You logged into a secure area!"));

        navegador.findElement(By.xpath("/html/body/div[2]/div/div/a")).click();
    }

    @AfterSuite
    public void tearDown(){

        navegador.quit();
    }
}
