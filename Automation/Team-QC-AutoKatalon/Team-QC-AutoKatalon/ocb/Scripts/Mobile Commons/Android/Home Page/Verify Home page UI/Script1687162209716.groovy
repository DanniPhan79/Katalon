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

'Wait for Home page displayed'
Mobile.waitForElementPresent(findTestObject('Android/Home Page/btnHome'), timeout)

'Verify Account Name is displayed'
Mobile.verifyElementExist(findTestObject('Android/Home Page/txtAccountName', [('text') : accountName]), timeout, FailureHandling.CONTINUE_ON_FAILURE)

'Verify Total Available Account Balance is displayed'
Mobile.verifyElementExist(findTestObject('Android/Home Page/lblTotalAvailableAccountBalance'), timeout, FailureHandling.CONTINUE_ON_FAILURE)

'Verify Favorite Transactions is displayed'
Mobile.verifyElementExist(findTestObject('Android/Home Page/lblFavoriteTransactions'), timeout, FailureHandling.CONTINUE_ON_FAILURE)

'Verify Products and Services is displayed'
Mobile.verifyElementExist(findTestObject('Android/Home Page/lblProductsAndServices'), timeout, FailureHandling.CONTINUE_ON_FAILURE)

