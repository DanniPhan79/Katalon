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

'Select transfer To'
Mobile.tap(findTestObject('iOS/Transfer Page/optTo', [('text') : transferToType]), timeout)

switch (transferToType.toString().toUpperCase()) {
    case 'ACCOUNT':
	case 'TÀI KHOẢN':
        'Choose a bank'
        WebUI.callTestCase(findTestCase('Mobile Commons/iOS/Transfer Page/Choose a bank'), [('timeout') : timeout, ('bankName') : bankName], 
            FailureHandling.STOP_ON_FAILURE)

        'Add account to favorite'
        Mobile.tap(findTestObject('iOS/Transfer Page/optAddAccountToFavorites'), timeout)

		'Input Nickname'
		Mobile.setText(findTestObject('iOS/Transfer Page/inputNickname'), nickname, timeout)

		Mobile.hideKeyboard(FailureHandling.OPTIONAL)
		
        'Input Account Number'
        Mobile.setText(findTestObject('iOS/Transfer Page/inputAccountNumber'), accountNumber, timeout)
		Mobile.tap(findTestObject('iOS/Transfer Page/inputAccountNumber'), timeout)

       break
    case 'CARDS':
	case 'THẺ':
        'Input Card Number'
        Mobile.setText(findTestObject('iOS/Transfer Page/inputCardNumber'), cardNumber, timeout)

        'Add account to favorite'
        Mobile.tap(findTestObject('iOS/Transfer Page/optAddAccountToFavorites'), timeout)

        'Input Nickname'
        Mobile.setText(findTestObject('iOS/Transfer Page/inputNickname'), nickname, timeout)

        break
    case 'PHONENUMBER':
	case 'SỐ ĐIỆN THOẠI':
        'Input Phone Number'
        Mobile.setText(findTestObject('iOS/Transfer Page/inputPhoneNumber'), phoneNumber, timeout)

        break
    default:
        break
}

'Input amount'
Mobile.tap(findTestObject('iOS/Transfer Page/inputAmount'), timeout)
Mobile.setText(findTestObject('iOS/Transfer Page/inputAmount'), amount, timeout)
Mobile.tap(findTestObject('iOS/Transfer Page/inputAmount'), timeout)

Mobile.hideKeyboard(FailureHandling.OPTIONAL)
