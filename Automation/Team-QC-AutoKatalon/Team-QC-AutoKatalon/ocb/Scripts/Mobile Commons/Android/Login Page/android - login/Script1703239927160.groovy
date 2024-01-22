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


'Chrome open new '
if (Mobile.verifyElementVisible(findTestObject('Android/Login Page/inputUsername'), 5, FailureHandling.OPTIONAL)) {
	
	Mobile.setText(findTestObject('Android/Login Page/inputUsername'), username, 0)
	
	Mobile.setText(findTestObject('Android/Login Page/inputPassword'), password, 0)
	
	Mobile.tap(findTestObject('Android/Login Page/btnLogin'), 0)
	
	WebUI.callTestCase(findTestCase('Mobile Commons/Android/OTP Page/Get OTP'), [('appId') : appID, ('chromeAppId') : chromeAppId
			, ('msgOtpValid') : msgOtpValid, ('soDt') : soDt, ('Otp') : '', ('urlOtp') : urlOtp], FailureHandling.STOP_ON_FAILURE)
	
	Mobile.setText(findTestObject('Android/OTP Page/inputSecurityPassword'), soDTBM, 0)
	
	Mobile.delay(3, FailureHandling.STOP_ON_FAILURE)
	
	Mobile.setText(findTestObject('Android/OTP Page/inputSecurityPassword'), soDTBM, 0)
	
}else {
	'Enter URL'
	Mobile.tap(findTestObject('Android/Login Page/btnLogin'), 0)
	Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)
	
	Mobile.setText(findTestObject('Android/OTP Page/inputSecurityPassword'), soDTBM, 0)
	Mobile.delay(1, FailureHandling.STOP_ON_FAILURE)
}

