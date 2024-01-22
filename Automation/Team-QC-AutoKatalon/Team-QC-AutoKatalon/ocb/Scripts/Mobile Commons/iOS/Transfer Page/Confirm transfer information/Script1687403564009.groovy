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

'Verify information is displayed correctly'
Mobile.waitForElementPresent(findTestObject('iOS/Dynamic/lbl_text', [('text') : fromAccountNumber]), timeout, FailureHandling.STOP_ON_FAILURE)

Mobile.delay(3)

'From Account'
Mobile.verifyElementExist(findTestObject('iOS/Dynamic/lbl_text', [('text') : fromAccountNumber]), timeout, FailureHandling.CONTINUE_ON_FAILURE)

'To Nickname'
Mobile.verifyElementExist(findTestObject('iOS/Dynamic/lbl_text', [('text') : toNickname]), timeout, FailureHandling.CONTINUE_ON_FAILURE)

'To Account'
Mobile.verifyElementExist(findTestObject('iOS/Dynamic/lbl_text', [('text') : toAccountNumber]), timeout, FailureHandling.CONTINUE_ON_FAILURE)

'To Account Name'
Mobile.verifyElementExist(findTestObject('iOS/Dynamic/lbl_text', [('text') : toAccountName]), timeout, FailureHandling.CONTINUE_ON_FAILURE)

'To Bank Name'
Mobile.verifyElementExist(findTestObject('iOS/Dynamic/lbl_text contains', [('text') : toBankName]), timeout, FailureHandling.CONTINUE_ON_FAILURE)

amount = Mobile.callTestCase(findTestCase('Utils/convertMonetary'), [('isEn') : false, ('money') : amount], FailureHandling.STOP_ON_FAILURE)

'Amount'
Mobile.verifyElementExist(findTestObject('iOS/Dynamic/lbl_text', [('text') : amount]), timeout, FailureHandling.CONTINUE_ON_FAILURE)

'Transaction Detail'
Mobile.verifyElementExist(findTestObject('iOS/Dynamic/lbl_text', [('text') : transactionDetail]), timeout, FailureHandling.CONTINUE_ON_FAILURE)

'Fee'
Mobile.verifyElementExist(findTestObject('iOS/Dynamic/lbl_text', [('text') : fee + " VND"]), timeout, FailureHandling.CONTINUE_ON_FAILURE)

