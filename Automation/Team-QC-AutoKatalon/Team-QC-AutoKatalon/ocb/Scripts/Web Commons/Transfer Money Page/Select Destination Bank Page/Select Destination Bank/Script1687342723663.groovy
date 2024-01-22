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

'Wait for button Create New Recipient'
WebUI.waitForElementPresent(findTestObject('Web/Transfer Money Page/Select Destination Bank Page/btnCreateNewRecipient'), 
    timeout)
'Click Button Create New Recipient'
WebUI.click(findTestObject('Web/Transfer Money Page/Select Destination Bank Page/btnCreateNewRecipient'))

'Enter Destination Bank'
WebUI.setText(findTestObject('Web/Transfer Money Page/Select Destination Bank Page/inputSearchDestinationBank'), destinationBank)

'Select Icon Bank'
WebUI.click(findTestObject('Web/Transfer Money Page/Select Destination Bank Page/iconBankName'))

'Wait for send money'
WebUI.waitForElementPresent(findTestObject('Web/Transfer Money Page/Select Destination Bank Page/Enter Recipient Information Page/buttonSendMoneyToTheRecicpient'), 
    GlobalVariable.G_longTimeout)

'Click Fast Fund Napas 247'
WebUI.click(findTestObject('Web/Transfer Money Page/optFastFundNapas247'))

