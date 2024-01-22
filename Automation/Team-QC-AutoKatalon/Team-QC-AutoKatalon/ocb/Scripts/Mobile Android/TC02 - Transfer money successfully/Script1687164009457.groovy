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
import io.appium.java_client.InteractsWithApps as InteractsWithApps

Mobile.startExistingApplication(GlobalVariable.Android_AppPackageId, FailureHandling.STOP_ON_FAILURE)

'Login with username and password'
WebUI.callTestCase(findTestCase('Mobile Commons/Android/Login Page/Login with username and password'), [('timeout') : timeout
        , ('username') : username, ('password') : password, ('accountName') : accountName], FailureHandling.STOP_ON_FAILURE)

'Skip select method'
WebUI.callTestCase(findTestCase('Mobile Commons/Android/Select Login Method Page/Select login method'), [('timeout') : timeout
        , ('loginMethod') : loginMethod], FailureHandling.STOP_ON_FAILURE)

'Get account balance before transfer'
accountBalanceBefore = WebUI.callTestCase(findTestCase('Mobile Commons/Android/Home Page/Get account balance'), [('timeout') : timeout], 
    FailureHandling.STOP_ON_FAILURE)

'Select service name = Transfer'
WebUI.callTestCase(findTestCase('Mobile Commons/Android/Home Page/Select service name'), [('timeout') : timeout, ('serviceName') : serviceName], 
    FailureHandling.STOP_ON_FAILURE)

'Select sub service = Transfer'
Mobile.tap(findTestObject('Android/Transfer Page/Sub-Service/lbl_Transfer'), timeout)

'Select sub service = Transfer'
Mobile.tap(findTestObject('Android/Transfer Page/Sub-Service/lbl_Transfer'), timeout, FailureHandling.OPTIONAL)

Mobile.delay(GlobalVariable.G_loadTimeout, FailureHandling.STOP_ON_FAILURE)

'Click on + New beneficiary'
Mobile.tap(findTestObject('Android/Transfer Page/btnAddNewBeneficiary'), timeout)

'Input transfer information'
WebUI.callTestCase(findTestCase('Mobile Commons/Android/Transfer Page/Transfer to info'), [('timeout') : timeout, ('transferToType') : transferToType
        , ('bankName') : toBankName, ('accountNumber') : toAccountNumber, ('cardNumber') : toCardNumber, ('phoneNumber') : toPhoneNumber
        , ('toAccountName') : toAccountName, ('amount') : amount, ('nickname') : nickname], FailureHandling.STOP_ON_FAILURE)

'Click on Transfer button'
Mobile.tap(findTestObject('Android/Transfer Page/btnTransfer'), timeout, FailureHandling.STOP_ON_FAILURE)

'Click on Transfer button'
Mobile.tap(findTestObject('Android/Transfer Page/btnTransfer'), timeout, FailureHandling.STOP_ON_FAILURE)

Mobile.delay(GlobalVariable.G_loadTimeout)

Mobile.tap(findTestObject('Android/Transfer Page/Confirm transfer information/btnGetOtp'), timeout, FailureHandling.STOP_ON_FAILURE)

'Get and Enter Otp'
WebUI.callTestCase(findTestCase('Mobile Commons/Android/OTP Page/Get OTP'), [('appId') : GlobalVariable.Android_AppPackageId, ('chromeAppId') : GlobalVariable.Android_ChromeApp_PackageId], 
    FailureHandling.STOP_ON_FAILURE)

'manual get otp'
Mobile.delay(GlobalVariable.G_loadTimeout)

'Click on Confirm button'
Mobile.tap(findTestObject('Android/Transfer Page/btnConfirm'), timeout, FailureHandling.STOP_ON_FAILURE)

'Verify transaction details'
Mobile.callTestCase(findTestCase('Mobile Commons/Android/Transfer Page/Transaction details'), [('timeout') : timeout, ('fromAccountNumber') : fromAccountNumber
        , ('fromAccountName') : fromAccountName, ('fromBankName') : fromBankName, ('toAccountNumber') : toAccountNumber, ('toAccountName') : toAccountName
        , ('toBankName') : toBankName, ('transactionType') : transactionType, ('amount') : amount], FailureHandling.STOP_ON_FAILURE)

'Click on Back to home button'
Mobile.tap(findTestObject('Android/Transfer Page/Transaction details/btnBackToHome'), timeout, FailureHandling.STOP_ON_FAILURE)

'Click on Back to home button'
Mobile.tap(findTestObject('Android/Home Page/btnHome'), timeout, FailureHandling.STOP_ON_FAILURE)

'Wait for Home page displayed'
Mobile.waitForElementPresent(findTestObject('Android/Home Page/btnHome'), timeout)

Mobile.delay(GlobalVariable.G_TimeoutS)

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

