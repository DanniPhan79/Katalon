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
Mobile.waitForElementPresent(findTestObject('Android/Transfer Page/Transaction details/lblSuccessfulTransaction'), timeout)

amount = WebUI.callTestCase(findTestCase('Utils/convertMoney'), [('money') : amount, ('delimiter') : ',', ('replace_delimiter') : '.'], 
    FailureHandling.STOP_ON_FAILURE)

System.out.print(amount)

'Amount'
Mobile.verifyElementExist(findTestObject('Android/Transfer Page/Transaction details/txtAmount', [('text') : amount]), timeout)

'Click on button Hidden information'
Mobile.tap(findTestObject('Android/Transfer Page/Transaction details/btnHidden'), timeout)

'From Account'
Mobile.verifyElementExist(findTestObject('Android/Dynamic/lbl_text', [('text') : fromAccountNumber]), timeout, FailureHandling.CONTINUE_ON_FAILURE)

'From Bank Name'
Mobile.verifyElementExist(findTestObject('Android/Dynamic/lbl_text contains', [('text') : fromBankName]), timeout, FailureHandling.CONTINUE_ON_FAILURE)

'To Account'
Mobile.verifyElementExist(findTestObject('Android/Dynamic/lbl_text', [('text') : toAccountNumber]), timeout, FailureHandling.CONTINUE_ON_FAILURE)

'To Account Name'
Mobile.verifyElementExist(findTestObject('Android/Dynamic/lbl_text', [('text') : toAccountName]), timeout, FailureHandling.CONTINUE_ON_FAILURE)

'To Bank Name'
Mobile.verifyElementExist(findTestObject('Android/Dynamic/lbl_text contains', [('text') : toBankName]), timeout, FailureHandling.CONTINUE_ON_FAILURE)

'Transaction details'

'Transaction time'
Mobile.verifyElementExist(findTestObject('Android/Transfer Page/Transaction details/txtTransactionTime'), timeout)

'Transaction code'
Mobile.verifyElementExist(findTestObject('Android/Transfer Page/Transaction details/txtTransactionCode'), timeout)

'Transaction Type'
Mobile.verifyElementExist(findTestObject('Android/Dynamic/lbl_text contains', [('text') : transactionType]), timeout, FailureHandling.CONTINUE_ON_FAILURE)

