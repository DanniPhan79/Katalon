import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import javax.xml.bind.annotation.XmlElementDecl.GLOBAL as GLOBAL
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

WebUI.executeJavaScript('window.open();', [])

currentWindow = WebUI.getWindowIndex()

//Switches tab #1
WebUI.switchToWindowIndex(currentWindow + 1)

WebUI.navigateToUrl(urlGetOtp)

WebUI.delay(GlobalVariable.G_shortTimeout)

WebUI.click(findTestObject('Web/Get OTP Page/ddEnvironment'), FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Web/Get OTP Page/optEnvUAT'), FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Web/Get OTP Page/ddSmsTypes'), FailureHandling.STOP_ON_FAILURE)

WebUI.click(findTestObject('Web/Get OTP Page/optSmsSend', [('text') : 'SMS_GATEWAY']), FailureHandling.STOP_ON_FAILURE)

WebUI.setText(findTestObject('Web/Get OTP Page/inputSoDt'), soDt)

WebUI.delay(3)

WebUI.click(findTestObject('Web/Get OTP Page/btnExecute'), FailureHandling.STOP_ON_FAILURE)

WebUI.delay(3)

for (int i = 2; i < 9999; i++) {
    msgOTP = WebUI.getText(findTestObject('Web/Get OTP Page/txtOtpMessage', [('index') : i]), FailureHandling.STOP_ON_FAILURE)

    System.out.print('msg ' + msgOTP)

    if (msgOTP.contains(msgOTPValid)) {
        System.out.print(('Otp:' + msgOTP) + '.')

        Otp = CustomKeywords.'ocb.keywords.StringHelper.extractStrs'(msgOTP)

        System.out.print(('Otp:' + (Otp[0])) + '.')

        Otp = (Otp[0]).toString()

        break
    }
}

System.out.print(('Current Otp:' + Otp) + '.')

WebUI.switchToWindowIndex(currentWindow)

WebUI.delay(2)

WebUI.setText(findTestObject('Web/Login Page/inputOTP', [('text') : 'otp-1']), Otp)

WebUI.delay(3)

