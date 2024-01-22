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
import com.kms.katalon.core.annotation.TearDownTestCase as TearDownTestCase

'Open OCB Website'
WebUI.callTestCase(findTestCase('Web Commons/Login Page/Open OCB Website'), [('url') : urlOCB], FailureHandling.STOP_ON_FAILURE)

'Login to OCB'
WebUI.callTestCase(findTestCase('Web Commons/Login Page/Login'), [('username_login') : username, ('password_login') : password], 
    FailureHandling.STOP_ON_FAILURE)

'Get OTP'
WebUI.callTestCase(findTestCase('Web Commons/Login Page/Get And Enter OTP To Login'), [('urlGetOtp') : urlGetOtp, ('soDt') : soDt
        , ('msgOTPValid') : msgOTPValid], FailureHandling.STOP_ON_FAILURE)

'Wait Logo OCB'
WebUI.waitForElementPresent(findTestObject('Web/Login Page/logoOCB'), timeout)

'Verify Logo OCB'
WebUI.verifyElementPresent(findTestObject('Web/Login Page/logoOCB'), timeout)

@TearDown
def Teardown() {
    'Close browser'
    WebUI.closeBrowser()
}

