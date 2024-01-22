import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import internal.GlobalVariable as GlobalVariable

'Wait for Login page displayed'
Mobile.waitForElementPresent(findTestObject('Android/Login Page/btnLogin'), timeout)

'Check User already logins or not'
if (Mobile.verifyElementVisible(findTestObject('Android/Login Page/btnRefreshAccount', [('text') : accountName]), 5, FailureHandling.OPTIONAL)) {
    'Click on Refresh button'
    Mobile.tap(findTestObject('Android/Login Page/btnRefreshAccount', [('text') : accountName]), timeout)
}

'Input Username'
CustomKeywords.'ocb.keywords.Common.setText'(findTestObject('Android/Login Page/inputUsername'), username, timeout)

'Input Password'
Mobile.setEncryptedText(findTestObject('Android/Login Page/inputPassword'), password, timeout)

'Click on Login button'
Mobile.tap(findTestObject('Android/Login Page/btnLogin'), timeout)

'Click Allow location'
Mobile.tap(findTestObject('Android/Login Page/lbl_Allow Location'), 5, FailureHandling.OPTIONAL)

'Check Continue button exists or not'
if (Mobile.verifyElementVisible(findTestObject('Android/Login Page/btnContinue'), 5, FailureHandling.OPTIONAL)) {
    'Click Continue button'
    Mobile.tap(findTestObject('Android/Login Page/btnContinue'), 10, FailureHandling.OPTIONAL)

    'Click on Login button'
    Mobile.tap(findTestObject('Android/Login Page/btnLogin'), 10, FailureHandling.OPTIONAL)
}

'Click Allow location'
Mobile.tap(findTestObject('Android/Login Page/btnAllow'), 5, FailureHandling.OPTIONAL)

'Click Continue button'
Mobile.tap(findTestObject('Android/Login Page/btnContinue'), 5, FailureHandling.OPTIONAL)

