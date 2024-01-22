package ocb.keywords

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import org.openqa.selenium.Keys as Keys
import org.openqa.selenium.WebElement
import org.openqa.selenium.remote.DesiredCapabilities

import com.google.common.collect.ImmutableMap as ImmutableMap
import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.appium.driver.AppiumDriverManager
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.driver.MobileDriverType
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import groovy.json.JsonSlurper
import internal.GlobalVariable
import io.appium.java_client.AppiumDriver
import io.appium.java_client.HasSettings
import io.appium.java_client.Setting
import java.util.regex.Matcher
import java.util.regex.Pattern
import com.kms.katalon.core.mobile.helper.MobileElementCommonHelper
import io.appium.java_client.MobileElement
import languages.EnVn


public class iOSCommon {
	int timeout = GlobalVariable.G_longTimeout

	@Keyword
	def superWait(TestObject to, def timeout = timeout) {
		Mobile.waitForElementPresent(to, timeout, FailureHandling.OPTIONAL)
		Mobile.waitForElementAttributeValue(to, 'visible', 'true', timeout, FailureHandling.OPTIONAL)
		Mobile.waitForElementAttributeValue(to, 'accessible', 'true', timeout, FailureHandling.OPTIONAL)
		Mobile.waitForElementAttributeValue(to, 'enabled', 'true', timeout, FailureHandling.OPTIONAL)
	}

	@Keyword
	def tapByRelativeXY(TestObject to, xRelative = 1/2, yRelative = 1/2) {

		Mobile.waitForElementPresent(to, timeout, FailureHandling.OPTIONAL)
		Mobile.waitForElementAttributeValue(to, 'visible', 'true', timeout, FailureHandling.OPTIONAL)

		int x = Mobile.getElementLeftPosition(to, timeout)
		int y = Mobile.getElementTopPosition(to, timeout)
		Mobile.comment('Element\'s position: x-' + x + ', y-' + y)

		int eH = Mobile.getElementHeight(to, timeout)
		int eW = Mobile.getElementWidth(to, timeout)
		Mobile.comment('Element\'s size: h-' + eH + ', w-' + eW)

		Mobile.comment('tapAtPosition x: ' + (x + eW*(xRelative)) + ', y: ' + (y + eH*(yRelative)))
		Mobile.waitForElementPresent(to, timeout)
		Mobile.tapAtPosition(x + eW*(xRelative), y + eH*(yRelative))
	}

	@Keyword
	def doubleTapByRelativeXY(TestObject to, xRelative, yRelative) {

		int x = Mobile.getElementLeftPosition(to, timeout)
		int y = Mobile.getElementTopPosition(to, timeout)
		Mobile.comment('Element\'s position: x-' + x + ', y-' + y)

		int eH = Mobile.getElementHeight(to, timeout)
		int eW = Mobile.getElementWidth(to, timeout)
		Mobile.comment('Element\'s size: h-' + eH + ', w-' + eW)

		Mobile.comment('doubleTapAtPosition x: ' + (x + eW*(xRelative)) + ', y: ' + (y + eH*(yRelative)))
		Mobile.waitForElementPresent(to, timeout)
		Mobile.waitForElementAttributeValue(to, 'visible', 'true', timeout, FailureHandling.CONTINUE_ON_FAILURE)
		Mobile.tapAtPosition(x + eW*(xRelative), y + eH*(yRelative))
		Mobile.tapAtPosition(x + eW*(xRelative), y + eH*(yRelative))
	}

	@Keyword
	def longTapByRelativeXY(TestObject to, xRelative, yRelative, def duration = 1, FailureHandling failureHandling = FailureHandling.CONTINUE_ON_FAILURE) {

		int x = Mobile.getElementLeftPosition(to, timeout, failureHandling)
		int y = Mobile.getElementTopPosition(to, timeout, failureHandling)
		Mobile.comment('Element\'s position: x-' + x + ', y-' + y)

		int eH = Mobile.getElementHeight(to, timeout)
		int eW = Mobile.getElementWidth(to, timeout)
		Mobile.comment('Element\'s size: h-' + eH + ', w-' + eW)
		Mobile.tapAndHoldAtPosition(x + eW*(xRelative), y + eH*(yRelative), duration, failureHandling)
	}

	@Keyword
	def tapByPercentage(xRelative, yRelative) {
		Mobile.tapAtPosition(xRelative*GlobalVariable.G_DeviceWidth, yRelative*GlobalVariable.G_DeviceHeight)
	}

	@Keyword
	def findElementBySwipe(TestObject to, x1Relative, y1Relative, x2Relative, y2Relative, int loop) {
		int start = 0
		int eH = GlobalVariable.G_DeviceHeight
		int eW = GlobalVariable.G_DeviceWidth
		Mobile.comment('Device size: h-' + eH + ', w-' + eW)

		while(start < loop && Mobile.waitForElementAttributeValue(to, 'visible', 'false', timeout, FailureHandling.OPTIONAL)) {
			//swipe each 5s if object can not found
			Mobile.comment("Can not found element, start swipe: startY ${x1Relative*eW}, startY ${y1Relative*eH}, endX ${x2Relative*eW}, endY ${y2Relative*eH}")
			Mobile.swipe(x1Relative*eW, y1Relative*eH, x2Relative*eW, y2Relative*eH)
			start++
			Mobile.delay(1)
		}
		Mobile.verifyElementAttributeValue(to, 'visible', 'true', timeout)
	}

	@Keyword
	def enterTextByKeyboardEnhancedByAppium(String text) {
		AppiumDriver<?> driver = MobileDriverFactory.getDriver()
		driver.getKeyboard().sendKeys(text)
	}

	@Keyword
	/**
	 * Simulate swipe action on Screen
	 */
	void swipeOnScreen(x1Relative = 1/2, y1Relative = 1/2, x2Relative = 0, y2Relative = -1/2) {
		//Declare Device's size
		int dHeight = GlobalVariable.G_DeviceHeight
		int dWidth = GlobalVariable.G_DeviceWidth

		int startX = dWidth*x1Relative
		int startY = dHeight*y1Relative
		int swipeToX = startX + dWidth*x2Relative
		int swipeToY = startY + dHeight*y2Relative + 10

		Mobile.comment("Swipe from (${startX} , ${startY}) to (${startX} , ${swipeToY})")
		Mobile.swipe(startX, startY, swipeToX, swipeToY)
	}

	/**
	 * LEFT, RIGHT, TOP, BOTTOM
	 */
	@Keyword
	void swipeBySide(String side) {
		int h = GlobalVariable.G_DeviceHeight
		int w = GlobalVariable.G_DeviceWidth

		switch(side) {
			case 'LEFT':
				Mobile.swipe(1/2*w as Integer, 1/2*h as Integer, 0*w as Integer, 1/2*h as Integer)
				break

			case 'RIGHT':
				Mobile.swipe(1/2*w as Integer, 1/2*h as Integer, 1*w as Integer, 1/2*h as Integer)
				break

			case 'BOTTOM':
				Mobile.swipe(1/2*w as Integer, 1/2*h as Integer, 1/2*w as Integer, 1*h as Integer)
				break

			default: //TOP
				Mobile.swipe(1/2*w as Integer, 1/2*h as Integer, 1/2*w as Integer, 0*h as Integer)
				break
		}
	}

	/**
	 * Tap on element relative
	 * @param by 'AccessibilityId, Id, ClassName. Name, Xpath(default)'
	 * xRelative, yRelative 1/4, 1/4
	 */
	@Keyword
	void tapElementRelative(String by = 'Xpath', String propertyValue, xRelative = 1/2, yRelative=1/2, duration = 0.01) {
		AppiumDriver<?> driver = MobileDriverFactory.getDriver()

		MobileElement element
		switch(by) {
			case "Xpath":
				element = driver.findElementByXPath(propertyValue)
				break
			case "Id":
				element = driver.findElementById(propertyValue)
				break
			case "ClassName":
				element = driver.findElementByClassName(propertyValue)
				break
			case "AccessibilityId":
				element = driver.findElementByAccessibilityId(propertyValue)
				break
			case "Name":
				element = driver.findElementByName(propertyValue)
				break
			default:
				element = driver.findElementByXPath(propertyValue)
				break
		}

		if(element.isDisplayed()) {
			Mobile.comment("Element found at: (${element.getLocation().x} , ${element.getLocation().y})")
			Mobile.comment("Element size at: (${element.getSize().width} , ${element.getSize().height})")

			int tapX =  element.getLocation().x + ((element.getSize().width)*xRelative).intValue()
			int tapY = element.getLocation().y + ((element.getSize().height)*yRelative).intValue()
			Mobile.comment("Tap element at (${tapX.toString()} , ${tapY.toString()}).")
			Mobile.tapAndHoldAtPosition(tapX, tapY, duration, FailureHandling.STOP_ON_FAILURE)
		}
		else
			KeywordUtil.markFailed("Not found element with xpath ${propertyValue}")
	}

	@Keyword
	def enhancedTap(TestObject to, int timeout = timeout) {
		superWait(to, timeout)
		tapByRelativeXY(to)
	}


	/**
	 * Hold and swipe element from right to left
	 * @param testObject Represent the mobile element
	 * @param timeout Maximum period of time (in seconds) that system will wait to return result
	 * @return
	 */
	@Keyword
	def swipeElementFromRightToLeft(TestObject to, int timeout = GlobalVariable.timeout) {
		try {
			KeywordUtil.logInfo("Hold and swipe on element from right to left")

			def elementHeight = Mobile.getElementHeight(to, timeout)
			def elementPosition = Mobile.getElementTopPosition(to, timeout)
			int pointY = elementPosition + elementHeight / 2
			int startX = GlobalVariable.G_DeviceWidth * 0.8
			int endX = GlobalVariable.G_DeviceHeight * 0.2
			Mobile.tapAndHold(to, 3, timeout)
			Mobile.swipe(startX, pointY, endX, pointY)

			KeywordUtil.markPassed('The element was swiped')
		} catch (Exception ex) {
			KeywordUtil.markFailed("Can't swipe element from right to left")
		}
	}
	/**
	 * Select Datetime
	 * @param day
	 * @param month
	 * @param year
	 * @return
	 */
	@Keyword
	def selectDateTime(String date, int timeout=GlobalVariable.timeout,  FailureHandling failureHandling=FailureHandling.STOP_ON_FAILURE) {
		def expectedDateSplit = date.split('/')
		def year = expectedDateSplit[2]
		def month = expectedDateSplit[1]
		month = Integer.parseInt(month)
		month = 'Th' + month
		def day = expectedDateSplit[0]
		selectYear(year, timeout, failureHandling)
		selectMonth(month, timeout, failureHandling)
		selectDay(day, timeout, failureHandling)
	}

	/**
	 * Select Day
	 * @param day
	 * @param timeout
	 * @param failureHandling
	 * @return
	 */
	def selectDay(String day, int timeout=GlobalVariable.timeout,  FailureHandling failureHandling=FailureHandling.STOP_ON_FAILURE) {

		KeywordUtil.logInfo("Get the current value of Day locator")
		TestObject currentObj = findTestObject('iOS/Calendar/lblDay')
		def currentDay = Mobile.getText(currentObj, timeout, failureHandling)

		KeywordUtil.logInfo("Current Day: ${currentDay}")
		KeywordUtil.logInfo("Expected Day: ${day}")

		def currentDayNo = currentDay as Integer
		def expectedDayNo = day as Integer

		if (currentDayNo != expectedDayNo) {

			def height = GlobalVariable.G_DeviceHeight
			def width = GlobalVariable.G_DeviceWidth
			def center_x = width/2 as Integer
			def center_y = height/2  as Integer
			def endY = 0
			def loop = Math.abs(expectedDayNo-currentDayNo)

			if (expectedDayNo > currentDayNo) {
				KeywordUtil.logInfo('Kéo từ giữa màn hình kéo xuống')
				endY = (center_y-150) as Integer
			} else {
				KeywordUtil.logInfo('Kéo từ giữa màn hình kéo lên')
				endY = (center_y+150) as Integer
			}

			KeywordUtil.logInfo('Số lần kéo: ' + loop)
			for (int i = 0; i < loop; i++) {
				def startX = (center_x-200) as Integer
				def startY = center_y as Integer
				def endX = (center_x-200) as Integer

				KeywordUtil.logInfo("Thực hiện kéo startX: ${startX}; startY: ${startY}; endX: ${endX} ; endY: ${endY}")
				Mobile.swipe(startX, startY, endX, endY)
				Mobile.delay(1)
			}
		}

		KeywordUtil.logInfo("Get the expected value of Day locator")
		currentDay = Mobile.getText(currentObj, timeout, failureHandling)

		if (currentDay.equals(day) ) {
			KeywordUtil.markPassed('Select Day passed')
		} else {
			KeywordUtil.markFailed('Select Day failed')
		}
	}

	/**
	 * Select Month
	 * @param month
	 * @param timeout
	 * @param failureHandling
	 * @return
	 */
	def selectMonth(String month, int timeout = GlobalVariable.timeout,  FailureHandling failureHandling=FailureHandling.STOP_ON_FAILURE) {
		KeywordUtil.logInfo("Get the current value of Month locator")
		TestObject currentObj = findTestObject('iOS/Calendar/lblMonth')
		def currentText = Mobile.getText(currentObj, timeout, failureHandling)

		def currentMonth = currentText.toString().replace("Th", '')
		KeywordUtil.logInfo("Current Month: ${currentMonth}")

		def expectedMonth =  month.toString().replace("Th", '')
		KeywordUtil.logInfo("Expected Month: ${expectedMonth}")

		def currentMonthNo = currentMonth as Integer
		def expectedMonthNo = expectedMonth as Integer

		if (currentMonthNo != expectedMonthNo) {

			expectedMonth = expectedMonth as Integer
			def height = GlobalVariable.G_DeviceHeight
			def width = GlobalVariable.G_DeviceWidth
			def center_x = width/2 as Integer
			def center_y = height/2  as Integer
			def endY = 0
			def loop = Math.abs(expectedMonth-currentMonthNo)

			if (expectedMonth > currentMonthNo) {
				KeywordUtil.logInfo('Kéo từ giữa màn hình kéo xuống')
				endY = (center_y-150) as Integer
			} else {
				KeywordUtil.logInfo('Kéo từ giữa màn hình kéo lên')
				endY = (center_y+150) as Integer
			}

			KeywordUtil.logInfo('Số lần kéo: ' + loop)
			for (int i = 0; i < loop; i++) {
				def startX = center_x as Integer
				def startY = center_y as Integer
				def endX = center_x as Integer

				KeywordUtil.logInfo("Thực hiện kéo startX: ${startX}; startY: ${startY}; endX: ${endX} ; endY: ${endY}")
				Mobile.swipe(startX, startY, endX, endY)
				Mobile.delay(1)
			}
		}

		KeywordUtil.logInfo("Get the expected value of Month locator")
		currentText = Mobile.getText(currentObj, timeout, failureHandling)

		if (currentText.equals(month) ) {
			KeywordUtil.markPassed('Select Month passed')
		} else {
			KeywordUtil.markFailed('Select Month failed')
		}
	}

	/**
	 * Select Year
	 * @param year
	 * @param timeout
	 * @param failureHandling
	 * @return
	 */
	def selectYear(String year, int timeout = GlobalVariable.timeout,  FailureHandling failureHandling=FailureHandling.STOP_ON_FAILURE) {
		KeywordUtil.logInfo("Get the current value of Year locator")
		TestObject currentObj = findTestObject('iOS/Calendar/lblYear')
		def currentText = Mobile.getText(currentObj, timeout, failureHandling)
		KeywordUtil.logInfo("Current Year: ${currentText}")
		KeywordUtil.logInfo("Expected Year: ${year}")

		def currentNo = currentText as Integer
		def expectedYear = year as Integer


		if (expectedYear != currentNo) {

			expectedYear = expectedYear as Integer
			def height = GlobalVariable.G_DeviceHeight
			def width = GlobalVariable.G_DeviceWidth
			def center_x = width/2 as Integer
			def center_y = height/2  as Integer
			def endY = 0
			def loop = Math.abs(expectedYear-currentNo)

			if (expectedYear > currentNo) {
				KeywordUtil.logInfo('Kéo từ giữa màn hình kéo xuống')
				endY = (center_y-150) as Integer
			} else {
				KeywordUtil.logInfo('Kéo từ giữa màn hình kéo lên')
				endY = (center_y+150) as Integer
			}

			KeywordUtil.logInfo('Số lần kéo: ' + loop)
			for (int i = 0; i < loop; i++) {
				def startX = (center_x+200) as Integer
				def startY = center_y as Integer
				def endX = (center_x+200) as Integer

				KeywordUtil.logInfo("Thực hiện kéo startX: ${startX}; startY: ${startY}; endX: ${endX} ; endY: ${endY}")
				Mobile.swipe(startX, startY, endX, endY)
				Mobile.delay(1)
			}
		}

		KeywordUtil.logInfo("Get the expected value of Year locator")
		currentText = Mobile.getText(currentObj, timeout, failureHandling)

		if (currentText.equals(year) ) {
			KeywordUtil.markPassed('Select Year passed')
		} else {
			KeywordUtil.markFailed('Select Year failed')
		}
	}
}
