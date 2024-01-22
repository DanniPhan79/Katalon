import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile

'Verify Account Name'
Mobile.verifyElementExist(findTestObject('Android/Home Page/txtAccountName', [('text') : accountName]), timeout, FailureHandling.CONTINUE_ON_FAILURE)

'Verify Total Available Account Balance is displayed'
Mobile.verifyElementExist(findTestObject('Android/Home Page/lblTotalAvailableAccountBalance'), timeout, FailureHandling.CONTINUE_ON_FAILURE)

