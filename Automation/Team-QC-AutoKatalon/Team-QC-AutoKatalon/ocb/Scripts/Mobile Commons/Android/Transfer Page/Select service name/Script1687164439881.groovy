import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling

'Wait for service name is present'
Mobile.waitForElementPresent(findTestObject('Android/Home Page/optServiceName', [('text') : serviceName]), timeout, FailureHandling.OPTIONAL)

'Select service name'
Mobile.tap(findTestObject('Android/Transfer Page/otpServiceName', [('text') : serviceName]), timeout, FailureHandling.OPTIONAL)

