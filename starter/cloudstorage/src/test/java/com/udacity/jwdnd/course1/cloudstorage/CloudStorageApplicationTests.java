package com.udacity.jwdnd.course1.cloudstorage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CloudStorageApplicationTests {

	protected  WebDriver webDriver;


	@LocalServerPort
	protected int port;



	@BeforeAll
	static void beforeAll() {
		WebDriverManager.chromedriver().setup();
	}

	@BeforeEach
	public void beforeEach() {
		this.webDriver = new ChromeDriver();
	}

	@AfterEach
	public void afterEach() {
		if (this.webDriver != null) {
			webDriver.quit();
		}
	}

	@Test
	public void getLoginPage() {
		webDriver.get("http://localhost:" + this.port + "/login");
		Assertions.assertEquals("Login", webDriver.getTitle());
	}

	protected HomePage SignUpAndLogin(){

		webDriver.get("http://localhost:" + this.port + "/signup");
		SignupPage signupPage = new SignupPage(webDriver);
		signupPage.setFirstName("Himanshu");
		signupPage.setLastName("vansal");
		signupPage.setUserName("Himanshuvansal");
		signupPage.setPassword("vansal1332");
		signupPage.SignUp();
		webDriver.get("http://localhost:" + this.port + "/login");
		LoginPage loginPage = new LoginPage(webDriver);
		loginPage.setUserName("Himanshuvansal");
		loginPage.setPassword("vansal1332");
		loginPage.Login();

		return new HomePage(webDriver);

	}

}
