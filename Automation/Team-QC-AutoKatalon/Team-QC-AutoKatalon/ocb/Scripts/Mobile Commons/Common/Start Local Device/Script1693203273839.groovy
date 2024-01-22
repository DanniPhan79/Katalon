import com.kms.katalon.core.configuration.RunConfiguration as RunConfiguration
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory as MobileDriverFactory
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import internal.GlobalVariable as GlobalVariable
import io.appium.java_client.AppiumDriver as AppiumDriver

RunConfiguration.setMobileDriverPreferencesProperty('fullReset', false)

RunConfiguration.setMobileDriverPreferencesProperty('noReset', true)

'Start Existing Application'
Mobile.startExistingApplication(appId, FailureHandling.STOP_ON_FAILURE)

'Get Device size'
GlobalVariable.G_DeviceHeight = Mobile.getDeviceHeight()

GlobalVariable.G_DeviceWidth = Mobile.getDeviceWidth()

if (Mobile.getDeviceOS() == 'Android') {
    Mobile.startExistingApplication(appId, FailureHandling.STOP_ON_FAILURE)
}

'Get Device name'
AppiumDriver<?> driver = MobileDriverFactory.getDriver()

def deviceName = driver.getSessionDetails().get('deviceName').toString()

WebUI.comment('deviceName: ' + deviceName)

return [('deviceName') : deviceName]

