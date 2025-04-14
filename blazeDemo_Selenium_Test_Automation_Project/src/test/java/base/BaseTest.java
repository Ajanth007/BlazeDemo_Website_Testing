	
	package base;

	import org.openqa.selenium.WebDriver;
    import org.openqa.selenium.edge.EdgeDriver;
     import org.testng.annotations.*;

	public class BaseTest {
	    protected WebDriver driver;

	    public WebDriver getDriver() {
	        return driver;
	    }

	    @BeforeMethod
	    public void setUp() {
	        driver = new EdgeDriver();
	    }

	    @AfterMethod
	    public void tearDown() {
	        if (driver != null) {
	            driver.quit();
	        }
	    }
	}



