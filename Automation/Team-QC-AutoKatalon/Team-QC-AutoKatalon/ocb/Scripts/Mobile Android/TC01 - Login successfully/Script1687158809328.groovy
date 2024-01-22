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
import com.kms.katalon.core.annotation.TearDown as TearDown


Mobile.startExistingApplication(GlobalVariable.Android_AppPackageId, FailureHandling.STOP_ON_FAILURE)

'Login with username and password'
WebUI.callTestCase(findTestCase('Mobile Commons/Android/Login Page/Login with username and password'), [('timeout') : timeout
        , ('username') : username, ('password') : password, ('accountName') : accountName], FailureHandling.STOP_ON_FAILURE)

'Skip select method'
WebUI.callTestCase(findTestCase('Mobile Commons/Android/Select Login Method Page/Select login method'), [('timeout') : timeout
        , ('loginMethod') : loginMethod], FailureHandling.STOP_ON_FAILURE)

'Verify Home page UI'
WebUI.callTestCase(findTestCase('Mobile Commons/Android/Home Page/Verify Home page UI'), [('timeout') : timeout, ('accountName') : accountName], 
    FailureHandling.STOP_ON_FAILURE)

'Open Side menu'
WebUI.callTestCase(findTestCase('Mobile Commons/Android/Home Page/Open side menu'), [('accountName') : accountName], FailureHandling.STOP_ON_FAILURE)

@TearDown
def tearDown() {
    'Close app'
    WebUI.callTestCase(findTestCase('Mobile Commons/Common/Close App'), [:], FailureHandling.STOP_ON_FAILURE)
}

