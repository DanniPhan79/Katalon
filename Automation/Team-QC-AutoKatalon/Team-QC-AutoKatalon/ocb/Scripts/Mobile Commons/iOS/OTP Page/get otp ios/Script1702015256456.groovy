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
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory as MobileDriverFactory
import io.appium.java_client.AppiumDriver as AppiumDriver

//Mobile.startExistingApplication('com.google.chrome.ios')
Mobile.delay(timeout)

AppiumDriver<?> driver = MobileDriverFactory.getDriver()

driver.activateApp('com.google.chrome.ios')

if (Mobile.verifyElementVisible(findTestObject('iOS/OTP Page/txtMsgOtp', [('index') : 2]), 10, FailureHandling.OPTIONAL)) {
    curSoDt = Mobile.getText(findTestObject('iOS/OTP Page/inputSoDt'), timeout)

    System.out.print(curSoDt)

    if (curSoDt != soDt.toString()) {
        Mobile.tap(findToestObject('iOS/OTP Page/inputSoDt'), 0)

        Mobile.delay(timeout, FailureHandling.STOP_ON_FAILURE)

        Mobile.tap(findTestObject('iOS/OTP Page/inputSoDt'), 0)

        Mobile.delay(timeout, FailureHandling.STOP_ON_FAILURE)

        Mobile.setText(findTestObject('iOS/OTP Page/inputSoDt'), soDt, 0)
    }
} else {
    if (Mobile.verifyElementVisible(findTestObject('Object Repository/iOS/OTP Page/Bar - Search or type URL'), 5, FailureHandling.OPTIONAL)) {
        'Click Url '
        Mobile.tap(findTestObject('Object Repository/iOS/OTP Page/Bar - Search or type URL'), 0)
    }
    
    if (Mobile.verifyElementVisible(findTestObject('iOS/OTP Page/Url - Bar'), 5, FailureHandling.OPTIONAL)) {
        'Click Url '
        Mobile.tap(findTestObject('iOS/OTP Page/Url - Bar'), 0)
    }
    
    Mobile.setText(findTestObject('Object Repository/iOS/OTP Page/Bar - Search Url'), urlGetOtp, 0)

    Mobile.tap(findTestObject('iOS/OTP Page/link - Address Get OTP'), 0)
}

Mobile.tap(findTestObject('Object Repository/iOS/OTP Page/btnFind'), 0)

Mobile.delay(2)

for (int i = 2; i < 9999; i++) {
    msgOtp = Mobile.getText(findTestObject('iOS/OTP Page/txtMsgOtp', [('index') : i]), 0)

    System.out.print(msgOtp)

    if (msgOtp.contains(msgOtpValid)) {
        System.out.print(('Otp:' + msgOtp) + '.')

        OtpLogin = CustomKeywords.'ocb.keywords.StringHelper.extractStrs'(msgOtp)

        System.out.print(('OtpLogin:' + OtpLogin) + '.')

        OtpLogin = (OtpLogin[0])

        break
    }
}

System.out.print(('OtpLogin:' + OtpLogin) + '.')

driver.activateApp('vn.com.ocb.awe.uat')

Mobile.setText(findTestObject('Object Repository/iOS/Transfer Page/inputOtpLogin'), OtpLogin, 60)

