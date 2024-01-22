import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import com.kms.katalon.core.annotation.TearDown as TearDown
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory as MobileDriverFactory
import io.appium.java_client.AppiumDriver as AppiumDriver
import org.openqa.selenium.interactions.Actions as Actions

'Start App'
Mobile.startExistingApplication(appId)

'Get driver'
AppiumDriver<?> driver = MobileDriverFactory.getDriver()

'Login with username and password'
WebUI.callTestCase(findTestCase('Mobile Commons/Android/Login Page/android - login'), [('timeout') : GlobalVariable.timeout
        , ('username') : username, ('password') : password, ('soDTBM') : soDTBM], FailureHandling.STOP_ON_FAILURE)

'Get account balance before transfer'
accountBalanceBefore = WebUI.callTestCase(findTestCase('Mobile Commons/Android/Home Page/Get account balance'), [('timeout') : timeout], 
    FailureHandling.STOP_ON_FAILURE)

Mobile.delay(3)

'Select sub service = Transfer'
Mobile.tap(findTestObject('Android/Transfer Page/Sub-Service/lbl_Transfer'), timeout)

Mobile.delay(1)

'Input transfer information'
WebUI.callTestCase(findTestCase('Mobile Commons/Android/Transfer Page/Transfer to info - New Omni'), [('timeout') : 5, ('bankName') : toBankName
        , ('accountNumber') : toAccountNumber, ('receiverName') : toAccountName, ('amount') : amount, ('soDTBM') : soDTBM], 
    FailureHandling.STOP_ON_FAILURE)

Mobile.delay(2)

'Get account balance after transfer'
accountBalanceAfter = WebUI.callTestCase(findTestCase('Mobile Commons/Android/Home Page/Get account balance'), [('timeout') : timeout], 
    FailureHandling.STOP_ON_FAILURE)

System.out.print(accountBalanceAfter)

'Convert amount'
amount = WebUI.callTestCase(findTestCase('Utils/convertMoney'), [('money') : amount, ('delimiter') : ',', ('replace_delimiter') : ''], 
    FailureHandling.STOP_ON_FAILURE)

amount = Long.parseLong(amount.toString())

fee = Long.parseLong(fee.toString())

System.out.print(amount)

System.out.print(fee)

accountBalanceBefore = Long.parseLong(accountBalanceBefore.toString())

System.out.print(accountBalanceBefore)

accountBalanceAfter = Long.parseLong(accountBalanceAfter.toString())

System.out.print(accountBalanceAfter)

'Verify amount balance is updated correctly'
Mobile.verifyEqual((accountBalanceBefore - amount) - fee, accountBalanceAfter)


@TearDown
def tearDown() {
    'Close app'
    WebUI.callTestCase(findTestCase('Mobile Commons/Common/Close App'), [:], FailureHandling.STOP_ON_FAILURE)
}

driver.terminateApp(appId)

driver.terminateApp(chromeAppId)
