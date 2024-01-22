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
import bsh.Variable as Variable
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import com.kms.katalon.core.annotation.TearDown as TearDown
import com.kms.katalon.core.annotation.TearDownTestCase as TearDownTestCase

'Open OCB Website'
WebUI.callTestCase(findTestCase('Web Commons/Login Page/Open OCB Website'), [('url') : urlOCB], FailureHandling.STOP_ON_FAILURE)

'Login to OCB'
WebUI.callTestCase(findTestCase('Web Commons/Login Page/Login'), [('username_login') : username, ('password_login') : password], 
    FailureHandling.STOP_ON_FAILURE)

'Get OTP'
WebUI.callTestCase(findTestCase('Web Commons/Login Page/Get And Enter OTP To Login'), [('urlGetOtp') : urlGetOtp, ('soDt') : soDt
        , ('msgOTPValid') : msgOTPLogin], FailureHandling.STOP_ON_FAILURE)

WebUI.navigateToUrl(urlHomePage)

'Wait Logo OCB'
WebUI.waitForElementPresent(findTestObject('Web/Login Page/logoOCB'), timeout)

'Verify Logo OCB'
WebUI.verifyElementPresent(findTestObject('Web/Login Page/logoOCB'), timeout)

'Get Available Funds Before'
availableFundsBefore = WebUI.callTestCase(findTestCase('Web Commons/Home Page/Get Available funds'), [:], FailureHandling.STOP_ON_FAILURE)

'Select Transfer Money'
WebUI.callTestCase(findTestCase('Web Commons/Transfer Money Page/Select Expense Type'), [:], FailureHandling.STOP_ON_FAILURE)

'Select Destination Bank'
WebUI.callTestCase(findTestCase('Web Commons/Transfer Money Page/Select Destination Bank Page/Enter and Select Bank Name'), 
    [('destinationBank') : destinationBANK], FailureHandling.STOP_ON_FAILURE)

'Enter Account Number'
WebUI.callTestCase(findTestCase('Web Commons/Transfer Money Page/Enter Recipient Information Page/Enter Account Number'), 
    [('accountNumber') : recipientAccount], FailureHandling.STOP_ON_FAILURE)

'Entering Data'
WebUI.callTestCase(findTestCase('Web Commons/Transfer Money Page/Entering Data Page/Entering Data'), [('transferAmount') : amount
        , ('description') : description], FailureHandling.STOP_ON_FAILURE)

'Click Button Dashboard'
WebUI.click(findTestObject('Web/Transfer Money Page/buttonConfirm'), FailureHandling.STOP_ON_FAILURE)

'Enter Account Number'
WebUI.callTestCase(findTestCase('Web Commons/Transfer Money Page/Data Verification Page/Get And Enter OTP To Transfer Money'), 
    [('timeout') : GlobalVariable.G_longTimeout, ('urlGetOtp') : 'http://10.102.60.7:8060/sms.aspx ', ('soDt') : '111', ('url') : 'http://10.102.61.126:9080/frontend-web/app/auth.html#/login'
        , ('Otp') : '', ('msgOTPValid') : 'OTP giao dich CHUYEN TIEN'], FailureHandling.STOP_ON_FAILURE)

'Wait for get otp'
WebUI.delay(GlobalVariable.G_shortTimeout, FailureHandling.STOP_ON_FAILURE)

'Click Button Dashboard'
WebUI.click(findTestObject('Web/Home Page/btnDashboard'), FailureHandling.STOP_ON_FAILURE)

WebUI.delay(3)

'Get Available Funds After'
availableFundsAfter = WebUI.callTestCase(findTestCase('Web Commons/Home Page/Get Available funds'), [:], FailureHandling.STOP_ON_FAILURE)

'Convert Available Funds Before'
availableFundsBefore = Long.parseLong(availableFundsBefore.toString())

'Convert Available Funds After'
availableFundsAfter = Long.parseLong(availableFundsAfter.toString())

'Get Transfer Amount'
transferAmount = WebUI.callTestCase(findTestCase('Utils/getNumber'), [('value') : amount], FailureHandling.STOP_ON_FAILURE)

'Convert Transfer Amount'
transferAmount = Long.parseLong(transferAmount.toString())

'Verify amount balance is updated correctly'
WebUI.verifyEqual(availableFundsBefore - transferAmount, availableFundsAfter)

@TearDown
def Teardown() {
    'Close browser'
    WebUI.closeBrowser()
}

