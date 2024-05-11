
# BDD - Automation


Tutorial BDD Automation dengan Maven, Cucumber, dan Gherkin
Tutorial ini akan memandu Anda dalam menyiapkan dan menjalankan tes automasi Behavior-Driven Development (BDD) menggunakan Maven, Cucumber, dan Gherkin. Website yang akan di Uji adalah https://www.saucedemo.com/


## Environment Variables

Untuk menjalakan tutorial ini, ada beberapa persiapan yang perlu di persiakan :

1. Java Development Kit (JDK) terinstal (https://www.oracle.com/java/technologies/downloads/)
2. Maven terinstal (https://maven.apache.org/download.cgi)
3. IntelliJ IDEA 
4. Plugin Cucumbar dan Gherkin


## Memulai Project

### 1. Buat project baru 

#### screenshoot
![Screenshot 2024-05-11 093539](https://github.com/SatriaBPY/BDD-automation/assets/26727925/6c199bb4-d7fa-445a-8157-a4de3488c77c)

### 2. Install Plugin Cucumber dan Gherkin 

#### screenshoot
![Screenshot 2024-05-11 100913](https://github.com/SatriaBPY/BDD-automation/assets/26727925/5105d325-c90e-4b70-9d5e-de230df229dd)
![Body](https://github.com/SatriaBPY/BDD-automation/assets/26727925/80c94509-1d16-492f-b934-318517450672)

### 3. Konfigurasi pom.xml dengan menambahkan dependensi berikut ke file pom.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xmlns="http://maven.apache.org/POM/4.0.0"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>

  <groupId>org.example</groupId>
  <artifactId>bdd-automation-testing</artifactId>
  <version>1.0-SNAPSHOT</version>
  <packaging>jar</packaging>

  <properties>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
  </properties>

  <dependencyManagement>
    <dependencies>
      <dependency>
        <groupId>io.cucumber</groupId>
        <artifactId>cucumber-bom</artifactId>
        <version>7.17.0</version>
        <type>pom</type>
        <scope>import</scope>
      </dependency>
      <dependency>
        <groupId>org.junit</groupId>
        <artifactId>junit-bom</artifactId>
        <version>5.10.2</version>
        <type>pom</type>
        <scope>import</scope>
      </dependency>
    </dependencies>
  </dependencyManagement>

  <dependencies>
    <dependency>
      <groupId>io.cucumber</groupId>
      <artifactId>cucumber-java</artifactId>
      <scope>test</scope>
    </dependency>

    <dependency>
      <groupId>io.cucumber</groupId>
      <artifactId>cucumber-junit-platform-engine</artifactId>
      <scope>test</scope>
    </dependency>

    <dependency>
      <groupId>org.junit.platform</groupId>
      <artifactId>junit-platform-suite</artifactId>
      <scope>test</scope>
    </dependency>

    <dependency>
      <groupId>org.junit.jupiter</groupId>
      <artifactId>junit-jupiter</artifactId>
      <scope>test</scope>
    </dependency>

    <dependency>
      <groupId>io.github.bonigarcia</groupId>
      <artifactId>webdrivermanager</artifactId>
      <version>5.8.0</version>
    </dependency>

    <dependency>
      <groupId>org.seleniumhq.selenium</groupId>
      <artifactId>selenium-java</artifactId>
      <version>4.20.0</version>
    </dependency>

    <dependency>
      <groupId>net.masterthought</groupId>
      <artifactId>cucumber-reporting</artifactId>
      <version>5.8.0</version>
    </dependency>

    <!-- https://mvnrepository.com/artifact/io.cucumber/cucumber-junit -->
    <dependency>
      <groupId>io.cucumber</groupId>
      <artifactId>cucumber-junit</artifactId>
      <version>7.17.0</version>
      <scope>test</scope>
    </dependency>


  </dependencies>

  <build>
    <plugins>
      <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-compiler-plugin</artifactId>
        <version>3.13.0</version>
        <configuration>
          <encoding>UTF-8</encoding>
          <source>1.8</source>
          <target>1.8</target>
        </configuration>
      </plugin>

      <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-surefire-plugin</artifactId>
        <version>3.2.5</version>
      </plugin>
      <plugin>
        <groupId>net.masterthought</groupId>
        <artifactId>maven-cucumber-reporting</artifactId>
        <version>2.8.0</version>
        <executions>
          <execution>
            <id>execution</id>
            <phase>verify</phase>
            <goals>
              <goal>generate</goal>
            </goals>
            <configuration>
              <projectName>bdd-automation-testing</projectName>
              <outputDirectory>${project.build.directory}/cucumber-report-html</outputDirectory>
              <cucumberOutput>${project.build.directory}/cucumber.json</cucumberOutput>
            </configuration>
          </execution>
        </executions>
      </plugin>
    </plugins>

  </build>
</project>
```

### 4. Buat folder baru di bawah folder "test" dengan nama "java"
```project
  src/test/java
```
, juga buat Package baru bernama "StepDef" dan "TestRunner" di dalam folder "java"
```project
  src/test/java/StepDef
```
```project
  src/test/java/TestRunner
```
#### screenshoot
![Screenshot 2024-05-11 110259](https://github.com/SatriaBPY/BDD-automation/assets/26727925/062a34bc-9faa-46d8-abf1-b9f9241c8003)

### 5. Pada folder "test" Buat folder baru bernama "resources" kemudian didalam folder tersebut buat folder bernama "Features", folder Ini akan berisi skenario dalam bahasa Gherkin
```project
  src/test/resuorces/Features
```
![Screenshot 2024-05-11 102728](https://github.com/SatriaBPY/BDD-automation/assets/26727925/4435a063-8f62-4e12-b86f-8f3bc98af32c)

### 6. Kemudian buat file di dalam foleder "Features" dengen ekstensi .feature, berikut adalah contoh "LoginTesting.feature"
```gherkin
  Feature: Login Feature
  As a user i want to login
  with credential and navigate
  to Homepage

  Scenario: Login with valid credential
    Given I am on Saucedemo website
    When I enter a valid usernma "<username>"
    And I enter a valid password "<password>"
    And Click the "Login" button
    Then I should rederected to homepage
```
#### screenshoot
![Screenshot 2024-05-11 103948](https://github.com/SatriaBPY/BDD-automation/assets/26727925/d81a2477-5707-4025-bd0d-e26f9236eaa6)

### 7. Setelah itu untuk mendefinisikan langkah tersbut dapat menekan "Alt+Enter" kemudian pilih "Create all steps definition"
#### screenshoot
![Screenshot 2024-05-11 104657](https://github.com/SatriaBPY/BDD-automation/assets/26727925/7814059e-5c36-497a-a98e-88742ba968a6)

kemudian isi filename sesuai keinginan sebagai contoh saya mengeisi nya dengan "Logintest" dan file type "Java" kemudian untuk File location taruh di folder "StepDef" dan tekan OK, maka secara otomatis akan membuat file baru bernama "Logintest.java" pada folder src/test/java/StepDef
#### screenshoot
![Screenshot 2024-05-11 104911](https://github.com/SatriaBPY/BDD-automation/assets/26727925/e848d743-53a0-4b16-81d2-75c07447cef0)

### 8. Maka akan terlihat file seperti ini :

#### screenshoot
![Screenshot 2024-05-11 110602](https://github.com/SatriaBPY/BDD-automation/assets/26727925/bf95f866-d564-4d93-a46a-b45bf64dc72b)

### 9. lengkah berikutnya lengkapi kode seperti setup selenium dan menentukan elemen locator dari web yang akan di uji, berikut contoh nya : 
```java

package StepDef;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Login {
    private WebDriver driver;
    @Given("I am on Saucedemo website")
    public void iAmOnSaucedemoWebsite() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://www.saucedemo.com/");
        Thread.sleep(1000);
        driver.findElement(By.cssSelector("#login-button")).isDisplayed();
    }

    @When("I enter a valid usernma {string}")
    public void iEnterAValidUsernma(String arg0) {
        driver.findElement(By.cssSelector("#user-name"))
                .sendKeys("standard_user");
    }

    @And("I enter a valid password {string}")
    public void iEnterAValidPassword(String arg0) {
        driver.findElement(By.cssSelector("#password"))
                .sendKeys("secret_sauce");
    }

    @And("Click the {string} button")
    public void clickTheButton(String arg0) {
        driver.findElement(By.name("login-button"))
                .click();
    }

    @Then("I should rederected to homepage")
    public void iShouldRederectedToHomepage() {
        driver.findElement(By.cssSelector("[alt='Sauce Labs Backpack']"))
                .isDisplayed();

        driver.quit();

    }


}
```
### 10. Membuat file runner "TestRunner.java" untuk menjalankan scenario tersebut pada folder 
```project
  src/test/java/TestRunner
```
berikut adalah contoh nya :

```java
package TestRunner;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/resources/Features",
        glue= {"StepDef"},
        plugin ={"pretty","json:target/cucumber.json"})
public class TestRunner {
}
```
#### screenshoot
![Screenshot 2024-05-11 112142](https://github.com/SatriaBPY/BDD-automation/assets/26727925/218013ab-ced9-49bf-b5b9-20e1a980c0b0)

### 11. Jalankan test dengan klik test pada file "LoginTesting.feature" atau tekan "Ctrl+shift+f10"

#### screenshoot
![Screenshot 2024-05-11 113143](https://github.com/SatriaBPY/BDD-automation/assets/26727925/734b5249-d443-4880-bdba-a8657caf2c90)

### 12. Jika status nya passed maka selamat test berhasil di Jalankan
#### screenshoot
![Screenshot 2024-05-11 113823](https://github.com/SatriaBPY/BDD-automation/assets/26727925/63db784b-4dfc-4d29-a915-7d9cca0cea1b)

### 13. Cucumber juga dapat menghasilkan report berformat .html yagn cantik dengan bantuan plugin cucumber-report, caranya adalah, pada terminal ketikan 

```project
  mvn varrify -DskipTest
```
#### screenshoot
![Screenshot 2024-05-11 113617](https://github.com/SatriaBPY/BDD-automation/assets/26727925/f58b413a-488e-476b-86ab-0f165742b02f)

maka secara otomatis akan menghsilkan fle "file-src-test-resources-Features-LoginTesting-feature.html" pada folder target/cucumber-report-html, 

#### screenshoot
![Screenshot 2024-05-11 113714](https://github.com/SatriaBPY/BDD-automation/assets/26727925/522cb2a6-c5b0-4fdf-8fa3-7b6c55293fda)

buka file tersebut di browser anda :
![Screenshot 2024-05-11 114434](https://github.com/SatriaBPY/BDD-automation/assets/26727925/d850f6eb-6aa2-4091-b0d2-8589581a6361)

