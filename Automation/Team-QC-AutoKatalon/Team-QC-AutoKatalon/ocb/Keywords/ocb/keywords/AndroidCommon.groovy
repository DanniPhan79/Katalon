package ocb.keywords
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import java.text.DecimalFormat

import com.google.common.collect.ImmutableMap
import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil

import internal.GlobalVariable

class AndroidCommon {

	/**
	 * Enter OTP
	 * @param to
	 * @param OTP
	 * @param timeout
	 * @return
	 */
	@Keyword
	def enterOTP(TestObject to, String OTP, int timeout){
		def command = 'mobile:shell'
		def list = ['text', OTP].asImmutable()
		Map<String, Object> args = new HashMap<>()
		args['command'] = 'input'
		args['args'] = list

		try {
			KeywordUtil.logInfo('Finding element with id:' + to.getObjectId())

			KeywordUtil.logInfo('Tap OTP Input field')
			Mobile.tap(to, timeout)

			Mobile.delay(2)

			KeywordUtil.logInfo('Set OTP into OTP Input field')
			Mobile.executeMobileCommand(command, args)

			KeywordUtil.markPassed('Enter OTP ' + OTP + ' successfully')
			return true
		} catch (Exception ignored) {
			KeywordUtil.markFailed('Can\'t enter OTP')
		}
		return false
	}

	/**
	 * Close Android app
	 * @return
	 */
	@Keyword
	def closeApp(String appPackage) {
		String command = 'mobile:shell'

		Map args = ImmutableMap.of('command', 'am force-stop ' + appPackage)

		try {
			KeywordUtil.logInfo('Close appPackage: ' + appPackage)
			Mobile.executeMobileCommand(command, args)
			KeywordUtil.markPassed('Close app successfully')
			return true
		} catch (Exception e) {
			KeywordUtil.markFailed('Close app ' + e.toString())
		}
		return false
	}

	/**
	 * Set Text
	 * @param to
	 * @param input
	 * @param timeout
	 * @return
	 */
	@Keyword
	def setText(TestObject to, String input, int timeout){
		def command = 'mobile:shell'
		def list = ['text', input].asImmutable()
		Map<String, Object> args = new HashMap<>() as Map<String, Object>
		args['command'] = 'input'
		args['args'] = list

		try {
			KeywordUtil.logInfo('Finding element with id:' + to.getObjectId())

			KeywordUtil.logInfo('Tap Input field')
			Mobile.tap(to, timeout)

			KeywordUtil.logInfo('Set Input field:' + input)
			Mobile.executeMobileCommand(command, args)

			KeywordUtil.markPassed('Enter text ' + input + ' successfully')
			return true
		} catch (Exception e) {
			KeywordUtil.markFailed('Can\'t enter text ' + e.toString())
		}
		return false
	}

	/**
	 * Convert to Amount
	 * @param debitAmount
	 * @param Amount
	 * @param fee
	 * @return convertAmt
	 */
	@Keyword
	def convertAmt(String debitAmount, String Amount, String fee = '') {
		if (fee == '') {
			def newAmount = Long.parseLong(debitAmount) - Long.parseLong(Amount)

			DecimalFormat formatter = new DecimalFormat('#,###')

			def convertAmt = formatter.format(newAmount).toString()

			GlobalVariable.newDebitAmount = convertAmt
			KeywordUtil.markPassed(('Số tiền sau khi chuyển khoản: ' + convertAmt) + ' VND')
			return convertAmt
		}
		else {
			def newAmount = Long.parseLong(debitAmount) - Long.parseLong(Amount) - Long.parseLong(fee)

			DecimalFormat formatter = new DecimalFormat('#,###')

			def convertAmt = formatter.format(newAmount).toString()

			GlobalVariable.newDebitAmount = convertAmt
			KeywordUtil.markPassed(('Số tiền sau khi chuyển khoản: ' + convertAmt) + ' VND')

			return convertAmt
		}
	}


	/**
	 * wait For Element Not Present
	 * @param to
	 * @param timeout
	 * @return
	 */
	@Keyword
	def waitForElementNotPresent(TestObject to, int timeout, FailureHandling failureHandling=FailureHandling.STOP_ON_FAILURE){
		try {
			KeywordUtil.logInfo('Finding element: ' + to.getObjectId())
			for(int i = 1; i <= 20; i++) {
				boolean exist = Mobile.waitForElementPresent(to, timeout, FailureHandling.OPTIONAL)
				if(exist) {
				} else {
					break
				}
			}
			KeywordUtil.markPassed('The element was not Present')
		} catch (ignored) {
			KeywordUtil.markFailed('The element still Present')
		}
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
		month = month.toString()
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
		TestObject currentObj = findTestObject('Android/Calendar/lblDay')
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
		TestObject currentObj = findTestObject('Android/Calendar/lblMonth')
		def currentText = Mobile.getText(currentObj, timeout, failureHandling)

		def currentMonth = currentText.toString().replace('Th', '').replace("thg ", '')
		KeywordUtil.logInfo("Current Month: ${currentMonth}")

		def expectedMonth = month.toString()
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

		if (currentText.equals('Th' + month) || currentText.equals('thg ' + month)) {
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
		TestObject currentObj = findTestObject('Android/Calendar/lblYear')
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
