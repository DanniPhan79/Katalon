import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory as MobileDriverFactory
import io.appium.java_client.AppiumDriver as AppiumDriver
import org.openqa.selenium.interactions.Actions as Actions

'Get driver'
AppiumDriver<?> driver = MobileDriverFactory.getDriver()

Actions a = new Actions(driver)

'Select Diff Bank'
Mobile.tap(findTestObject('Android/Transfer Page/New Omni/buttonDiffBank'), timeout)

'Enter Bank Name'
Mobile.setText(findTestObject('Android/Transfer Page/New Omni/inputBankName'), bankName, timeout)

Mobile.delay(1, FailureHandling.STOP_ON_FAILURE)

Mobile.tap(findTestObject('Android/Transfer Page/New Omni/selectBankName'), timeout)

'Enter Account Number'
Mobile.setText(findTestObject('Android/Transfer Page/New Omni/inputAccountNumber'), accountNumber, timeout)

Mobile.delay(1, FailureHandling.STOP_ON_FAILURE)

driver.hideKeyboard()

Mobile.delay(1, FailureHandling.STOP_ON_FAILURE)

a.sendKeys(Keys.TAB).build().perform()

Mobile.delay(6, FailureHandling.STOP_ON_FAILURE)

'Click Normal Tranfer '
if (Mobile.verifyElementVisible(findTestObject('Android/Transfer Page/New Omni/buttonNormalTranferMoney'), 6, FailureHandling.OPTIONAL)) {
    Mobile.tap(findTestObject('Android/Transfer Page/New Omni/buttonNormalTranferMoney'), timeout)

    'Enter Bank Name'
    Mobile.setText(findTestObject('Android/Transfer Page/New Omni/inputReceiverName'), receiverName, timeout)

    a.sendKeys(Keys.TAB).build().perform()

    Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)
}

Mobile.delay(1, FailureHandling.STOP_ON_FAILURE)

'Click Continue'
Mobile.tap(findTestObject('Android/Transfer Page/New Omni/buttonContinue'), timeout)

'Enter Amount'
Mobile.setText(findTestObject('Android/Transfer Page/New Omni/inputAmount'), amount, timeout)

Mobile.delay(1, FailureHandling.STOP_ON_FAILURE)

driver.hideKeyboard()

Mobile.delay(1, FailureHandling.STOP_ON_FAILURE)

a.sendKeys(Keys.TAB).build().perform()

Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

'Click Continue'
Mobile.tap(findTestObject('Android/Transfer Page/New Omni/buttonContinue'), timeout)

'Click Confrim'
Mobile.tap(findTestObject('Android/Transfer Page/New Omni/buttonConfirm'), timeout)

Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

Mobile.setText(findTestObject('Android/OTP Page/inputSecurityPassword'), soDTBM, 0)

Mobile.delay(10, FailureHandling.STOP_ON_FAILURE)

'Click Back to Home Page'
Mobile.tap(findTestObject('Android/Transfer Page/New Omni/buttonBackHomePage'), timeout)

Mobile.delay(2, FailureHandling.STOP_ON_FAILURE)

