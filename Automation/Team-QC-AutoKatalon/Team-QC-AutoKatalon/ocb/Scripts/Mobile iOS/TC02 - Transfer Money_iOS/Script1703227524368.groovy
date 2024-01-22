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

'Start App'
Mobile.startExistingApplication(appId)
if (Mobile.verifyElementVisible(findTestObject('Object Repository/iOS/Transfer Page/btnLogin'), 5, FailureHandling.OPTIONAL)) {
	'Click Url '
	Mobile.tap(findTestObject('Object Repository/iOS/Transfer Page/btnLogin'), 0)
	Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)
	Mobile.setText(findTestObject('iOS/Transfer Page/inputSecurityPass'), soDTBM, 0)
}
else {
	WebUI.callTestCase(findTestCase('Mobile Commons/iOS/Login Page/iOS - Login'), [('timeout') : GlobalVariable.timeout, ('username') : username
		, ('password') : password, ('soDTBM') : soDTBM], FailureHandling.STOP_ON_FAILURE)
}

Mobile.delay(timeout, FailureHandling.STOP_ON_FAILURE)

WebUI.callTestCase(findTestCase('Mobile Commons/iOS/Transfer Page/Choose a bank'), [('timeout') : GlobalVariable.timeout
        , ('bankName') : bankName, ('accountNumber') : accountNumber], FailureHandling.STOP_ON_FAILURE)

Mobile.setText(findTestObject('Object Repository/iOS/Transfer Page/inputSoTienChuyen'), numberMoneyTranfer, 0)

Mobile.tap(findTestObject('iOS/Transfer Page/btnInputTranferMoneyDone'), 0)

Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('iOS/Transfer Page/btnNext'), 0)

Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('iOS/Transfer Page/btnConfirm'), 0)

Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

Mobile.setText(findTestObject('iOS/Transfer Page/inputSecurityPass'), soDTBM, 0)

Mobile.delay(GlobalVariable.G_shortTimeout, FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Object Repository/iOS/Transfer Page/btnBackToHomePage'), 0)

Mobile.delay(GlobalVariable.G_shortTimeout, FailureHandling.STOP_ON_FAILURE)

Mobile.closeApplication()

