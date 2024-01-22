import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.annotation.TearDown as TearDown
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
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory as MobileDriverFactory
import io.appium.java_client.AppiumDriver as AppiumDriver
import org.openqa.selenium.interactions.Actions as Actions

Mobile.startExistingApplication(chromeAppId)

AppiumDriver<?> driver = MobileDriverFactory.getDriver()

Actions a = new Actions(driver)

'Click New Tab'
if (Mobile.verifyElementVisible(findTestObject('Android/OTP Page/buttonNewTab'), 5, FailureHandling.OPTIONAL)) {
    'Enter URL'
    Mobile.setText(findTestObject('Android/OTP Page/buttonNewTab'), urlOtp, 0)
}

'Chrome open new '
if (Mobile.verifyElementVisible(findTestObject('Android/OTP Page/searchBarChrome'), 5, FailureHandling.OPTIONAL)) {
    'Enter URL'
    Mobile.tap(findTestObject('Android/OTP Page/searchBarChrome'), 0)

    Mobile.setText(findTestObject('Android/OTP Page/inputUrl'), urlOtp, 0)

    Mobile.tap(findTestObject('Android/OTP Page/selecLineOne'), 0)
}
else {
	'Check User already logins or not'
	if (Mobile.verifyElementVisible(findTestObject('Android/OTP Page/inputUrlBar'), 5, FailureHandling.OPTIONAL)) {
		'Enter URL'
		Mobile.tap(findTestObject('Android/OTP Page/inputUrlBar'), 0)
	
		Mobile.setText(findTestObject('Android/OTP Page/inputUrlBar'), urlOtp, 0)
	
		Mobile.tap(findTestObject('Android/OTP Page/selecLineOne'), 0)
	}
}

Mobile.tap(findTestObject('Android/OTP Page/ddSmsSend'), 0)

Mobile.tap(findTestObject('Android/OTP Page/otpSmsSend', [('text') : 'SMS_GATEWAY']), 0)

Mobile.setText(findTestObject('Android/OTP Page/inputMobileNumber'), soDt, 1)

Mobile.delay(1, FailureHandling.STOP_ON_FAILURE)

a.sendKeys(Keys.TAB).build().perform()

Mobile.tap(findTestObject('Android/buttonFindOtp'), 0)

for (int i = 2; i < 9999; i++) {
    msgOtp = Mobile.getText(findTestObject('Android/OTP Page/txtMsgOtp', [('index') : i]), 0)

    System.out.print(msgOtp)

    if (msgOtp.contains(msgOtpValid)) {
        System.out.print(('Otp:' + msgOtp) + '.')

        Otp = CustomKeywords.'ocb.keywords.StringHelper.extractStrs'(msgOtp)

        System.out.print(('Otp:' + (Otp[0])) + '.')

        Otp = (Otp[0]).toString()

        break
    }
}

driver.activateApp(appId)

System.out.print('input OTP: ' + msgOtp)

Mobile.delay(3, FailureHandling.STOP_ON_FAILURE)

Mobile.setText(findTestObject('Android/OTP Page/inputOtp'), Otp, 0)

