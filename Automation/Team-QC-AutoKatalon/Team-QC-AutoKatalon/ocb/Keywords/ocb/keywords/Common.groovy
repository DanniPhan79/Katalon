package ocb.keywords

import java.util.concurrent.ThreadLocalRandom

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.configuration.RunConfiguration
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil

class Common {

	/**
	 * Start up an application
	 * @param appName Absolute path or Relative path (since 5.0) of the application installation file.
	 * @param uninstallAfterCloseApp true if uninstalling the application automatically after run
	 * @return
	 */
	@Keyword
	def startApplication(String appName, boolean uninstallAfterCloseApp=false) {
		KeywordUtil.logInfo('Start Application:' + appName)
		String appPath = RunConfiguration.getProjectDir() + '/Apps/' + appName
		try {
			Mobile.startApplication(appPath, uninstallAfterCloseApp)
			KeywordUtil.markPassed('App ' + appName + ' is started')
		} catch (Exception ignored) {
			KeywordUtil.markFailed('App ' + appName + ' can\'t start')
		}
	}

	/**
	 * This keyword is to start an Appium driver and to activate an installed application by its given application ID.
	 * @param appId ID of the tested application that’s either the package name of an Android app or the bundle identifier of an iOS app.
	 * @return
	 */
	@Keyword
	def startExistingApplication(String appId) {
		KeywordUtil.logInfo('Start Application ID:' + appId)
		try {
			Mobile.startExistingApplication(appId)
			KeywordUtil.markPassed('App ID: ' + appId + ' is started')
		} catch (Exception ignored) {
			KeywordUtil.markFailed('App ID: ' + appId + ' can\'t start')
		}
	}

	/**
	 * Tap on a mobile element
	 * @param testObject Represent a mobile element
	 * @param timeout Maximum period of time (in seconds) that system will wait to return result
	 * @return
	 */
	@Keyword
	def tap(TestObject testObject, int timeout, FailureHandling failureHandling=FailureHandling.STOP_ON_FAILURE) {
		try {
			KeywordUtil.logInfo('Tap on element')
			int index = 0
			while ((!Mobile.verifyElementExist(testObject, 5, FailureHandling.OPTIONAL)) && (index <= 5)) {
				Mobile.swipe(300,800,300,300)
				index++
			}
			Mobile.waitForElementPresent(testObject, timeout, failureHandling)
			Mobile.tap(testObject, timeout, failureHandling)
			KeywordUtil.markPassed('Tap on element successfully')
		} catch (Exception ignored) {
			KeywordUtil.markFailed('Can\'t tap on element')
		}
	}

	/**
	 * Set the text on a mobile element
	 * @param testObject
	 * @param text
	 * @param timeout
	 * @return
	 */
	@Keyword
	def setText(TestObject testObject, String text, int timeout, FailureHandling failureHandling=FailureHandling.STOP_ON_FAILURE) {
		try {
			KeywordUtil.logInfo('Set text: "' + text + '" into the element')
			int index = 0
			while ((Mobile.verifyElementNotExist(testObject, 5, FailureHandling.OPTIONAL)) && (index <= 5)) {
				Mobile.swipe(300,800,300,300)
				index++
			}
			Mobile.waitForElementPresent(testObject, timeout, failureHandling)
			Mobile.clearText(testObject, timeout, failureHandling)
			Mobile.setText(testObject, text, timeout, failureHandling)
			Mobile.hideKeyboard()
		} catch (Exception ignored) {
			KeywordUtil.markFailed('Can\'t set text into the element')
		}
	}

	/**
	 * Set setEncryptedText
	 * @param testObject
	 * @param text
	 * @param timeout
	 * @param failureHandling
	 * @return
	 */
	@Keyword
	def setEncryptedText(TestObject testObject, String text, int timeout, FailureHandling failureHandling=FailureHandling.STOP_ON_FAILURE) {
		try {
			KeywordUtil.logInfo('Set Encrypted Text: "' + text + '" into the element')
			Mobile.waitForElementPresent(testObject, timeout, failureHandling)
			Mobile.clearText(testObject, timeout, failureHandling)
			Mobile.setEncryptedText(testObject, text, timeout, failureHandling)
			Mobile.hideKeyboard()
		} catch (Exception ignored) {
			KeywordUtil.markFailed('Can\'t set text into the element')
		}
	}

	/**
	 * Clear text of a mobile element
	 * @param testObject Represent a mobile element
	 * @param timeout Maximum period of time (in seconds) that system will wait to return a result
	 * @return
	 */
	@Keyword
	def clearText(TestObject testObject, int timeout, FailureHandling failureHandling=FailureHandling.STOP_ON_FAILURE) {
		try {
			KeywordUtil.logInfo('Clear text on the element')
			Mobile.waitForElementPresent(testObject, timeout, failureHandling)
			Mobile.clearText(testObject, timeout, failureHandling)
		} catch (Exception ignored) {
			KeywordUtil.markFailed('Can\'t clear text on the element')
		}
	}


	/**
	 * Compare the actual text with expected text
	 * @param actual
	 * @param expected
	 * @return
	 */
	@Keyword
	def verifyContain(String actual, String expected) {
		if (actual.trim().contains(expected)) {
			KeywordUtil.markPassed('Actual Text \'' + actual + '\' contains \'' +  expected+'\'.')
		} else {
			KeywordUtil.markFailed('Actual Text \'' + actual + '\' doesn\'t contains \'' +  expected+'\'.')
		}
	}

	/** * Get random number int
	 * @param min minimum value
	 * @param max maximum value
	 * @return random number
	 */
	@Keyword
	def randomNumber(String minimum, String maximum) {

		String maximumString = maximum.replaceAll('\\D+', '')
		String minimumString = minimum.replaceAll('\\D+', '')

		long minimumNumber = Long.parseLong(minimumString)
		long maximumNumber = Long.parseLong(maximumString)

		def randomNo = ThreadLocalRandom.current().nextLong(minimumNumber+1, maximumNumber)
		KeywordUtil.markPassed('Random Number: '+ randomNo)
		return randomNo.toString()
	}

	/** * Get random characters
	 * @param number characters want to random
	 * @param type of String
	 * @return random chars
	 */
	@Keyword
	def randomString(int length, String TypeString) {
		'chose a Character random from this String'
		String AlphaNumericString = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvxyz'

		switch (TypeString) {
			//Number: random number'
			case 'number':
				AlphaNumericString = '0123456789'
				break
			//charsNum: random characters and number'
			case 'charsNum':
				AlphaNumericString += '0123456789'
				break
			default:
				break
		}

		'create StringBuffer size of AlphaNumericString'
		StringBuilder sb = new StringBuilder(length)

		for (int i = 0; i < length; i++) {

			'generate a random number between'
			'0 to AlphaNumericString variable length'
			int index = (int)(AlphaNumericString.length()* Math.random())
			'add Character one by one in end of sb'
			sb.append(AlphaNumericString.charAt(index))
		}

		String randomString = sb.toString()
		KeywordUtil.markPassed('Random String: '+ randomString)
		return randomString
	}

	/** * Get random UUID
	 * @return random string
	 */
	@Keyword
	public String randomUUID() {
		return UUID.randomUUID().toString()
	}

	/** * Get random string with date time
	 * @return random string with date time
	 */
	@Keyword
	public String randomStringWithDatetime() {
		Date today = new Date()
		String todayDate = today.format('MMddyyhhmmss')
		String randomStringWithDatetime = 'autoEKYC' + todayDate
		return randomStringWithDatetime
	}
	/** * Using Regex to Extract Text
	 * @return string
	 */
	@Keyword
	public String stripSshPrefix(String gitUrl){
		def match = (gitUrl =~ /ssh:\/\/(.+)/)
		if (match.find()) {
			return match.group(1)
		}
		return gitUrl
	}

	/** * Get random email
	 * @return random email
	 */
	@Keyword
	public String getEmail(String suffix,String prefix){
		int randomNo = (int)(Math.random() * 10000000);
		return suffix + randomNo + "@" + prefix;
	}

	private final List<String> VN_PHONE_PREFIX = [
		"086",
		"096",
		"097",
		"098",
		"039",
		"038",
		"037",
		"036",
		"035",
		"034",
		"033",
		"032",
		"091",
		"094",
		"088",
		"083",
		"084",
		"085",
		"081",
		"082",
		"070",
		"079",
		"077",
		"076",
		"078",
		"089",
		"090",
		"093",
		"092",
		"099"
	]
}
