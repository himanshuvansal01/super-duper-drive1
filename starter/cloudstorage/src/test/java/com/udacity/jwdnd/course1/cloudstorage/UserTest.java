package com.udacity.jwdnd.course1.cloudstorage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import java.util.concurrent.TimeUnit;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserTest {


    private WebDriver webDriver;

    @LocalServerPort
    protected int port;

    @BeforeAll
    static void BeforeAll(){
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void BeforeEach(){
        this.webDriver = new ChromeDriver();
    }

    @AfterEach
    public void AfterEach() {
        if(this.webDriver != null){
            webDriver.quit();
        }
    }

    @Test
    public void TestPageAccess(){
        webDriver.get("http://localhost:" + this.port +  "/login");
        Assertions.assertEquals("Login", webDriver.getTitle());


        webDriver.get("http://localhost:" + this.port +  "/signup");
        Assertions.assertEquals("Sign Up", webDriver.getTitle());


        webDriver.get("http://localhost:" + this.port +  "/home");
        Assertions.assertEquals("Login", webDriver.getTitle());


    }

    @Test
    public void TestSignUpLoginLogout(){

        webDriver.get("http://localhost:" + this.port +  "/signup");
        Assertions.assertEquals("Sign Up", webDriver.getTitle());

        SignupPage signupPage = new SignupPage(webDriver);
        signupPage.setFirstName("Himanshu");
        signupPage.setLastName("Vansal");
        signupPage.setUserName("Himanshuvansal");
        signupPage.setPassword("vansal1332");
        signupPage.SignUp();

        webDriver.get("http://localhost:" + this.port + "/login");
        Assertions.assertEquals("Login", webDriver.getTitle());

        LoginPage loginPage = new LoginPage(webDriver);
        loginPage.setUserName("Himanshuvansal");
        loginPage.setPassword("vansal1332");
        loginPage.Login();

        HomePage homePage = new HomePage(webDriver);
        homePage.logout();

        webDriver.get("http://localhost:" + this.port +  "/home");
        webDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Assertions.assertEquals("Login", webDriver.getTitle());

    }





}
