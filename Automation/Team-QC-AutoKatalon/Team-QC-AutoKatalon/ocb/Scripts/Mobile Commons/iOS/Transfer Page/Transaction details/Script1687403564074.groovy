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

'Wait for Successful transaction is displayed'
Mobile.waitForElementPresent(findTestObject('iOS/Transfer Page/Transaction details/lblSuccessfulTransaction'), timeout)

amount = Mobile.callTestCase(findTestCase('Utils/convertMonetary'), [('isEn') : false, ('money') : amount], FailureHandling.STOP_ON_FAILURE)

'Amount'
Mobile.verifyElementExist(findTestObject('iOS/Transfer Page/Transaction details/txtAmount', [('text') :amount]), timeout)

'Click on button Hidden information'
Mobile.tap(findTestObject('iOS/Transfer Page/Transaction details/btnHidden'), timeout)

'From Account'
Mobile.verifyElementExist(findTestObject('iOS/Dynamic/lbl_text', [('text') : fromAccountNumber]), timeout, FailureHandling.CONTINUE_ON_FAILURE)

'From Account Name'
Mobile.verifyElementExist(findTestObject('iOS/Dynamic/lbl_text', [('text') : fromAccountName]), timeout, FailureHandling.CONTINUE_ON_FAILURE)

'From Bank Name'
Mobile.verifyElementExist(findTestObject('iOS/Dynamic/lbl_text contains', [('text') : fromBankName]), timeout, FailureHandling.CONTINUE_ON_FAILURE)

'To Account'
Mobile.verifyElementExist(findTestObject('iOS/Dynamic/lbl_text', [('text') : toAccountNumber]), timeout, FailureHandling.CONTINUE_ON_FAILURE)

'To Account Name'
Mobile.verifyElementExist(findTestObject('iOS/Dynamic/lbl_text', [('text') : toAccountName]), timeout, FailureHandling.CONTINUE_ON_FAILURE)

'To Bank Name'
Mobile.verifyElementExist(findTestObject('iOS/Dynamic/lbl_text contains', [('text') : toBankName]), timeout, FailureHandling.CONTINUE_ON_FAILURE)

'Transaction details'

'Transaction time'
Mobile.verifyElementExist(findTestObject('iOS/Transfer Page/Transaction details/txtTransactionTime'), timeout)

'Transaction code'
Mobile.verifyElementExist(findTestObject('iOS/Transfer Page/Transaction details/txtTransactionCode'), timeout)

'Transaction Type'
Mobile.verifyElementExist(findTestObject('iOS/Dynamic/lbl_text contains', [('text') : transactionType]), timeout, FailureHandling.CONTINUE_ON_FAILURE)

'Description'
Mobile.verifyElementExist(findTestObject('iOS/Transfer Page/Transaction details/txtDescription'), timeout)
