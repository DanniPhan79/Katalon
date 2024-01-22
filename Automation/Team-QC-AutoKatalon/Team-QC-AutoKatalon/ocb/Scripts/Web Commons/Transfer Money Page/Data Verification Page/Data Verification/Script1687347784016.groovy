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

'Verify From Account'
WebUI.verifyElementText(findTestObject('Web/Transfer Money Page/Select Destination Bank Page/Data Verification Page/txtFromAccount'), 
    fromAccount)

'Verify Recipient Account'
WebUI.verifyElementText(findTestObject('Web/Transfer Money Page/Select Destination Bank Page/Data Verification Page/txtRecipientaccount'), 
    recipientAccount)

'Verify Recipient Name'
WebUI.verifyElementText(findTestObject('Web/Transfer Money Page/Select Destination Bank Page/Data Verification Page/txtRecipientname'), 
    recipientName)

'Verify Bank Name'
WebUI.verifyElementText(findTestObject('Web/Transfer Money Page/Select Destination Bank Page/Data Verification Page/txtBankname'), 
    bankName)

'Verify Fee'
WebUI.verifyElementText(findTestObject('Web/Transfer Money Page/Select Destination Bank Page/Data Verification Page/txtFee'), 
    fee)

'Verify Amount'
WebUI.verifyElementText(findTestObject('Web/Transfer Money Page/Select Destination Bank Page/Data Verification Page/txtAmount'), 
    amount)

'Verify Description'
WebUI.verifyElementText(findTestObject('Web/Transfer Money Page/Select Destination Bank Page/Data Verification Page/txtDescription'), 
    description)

