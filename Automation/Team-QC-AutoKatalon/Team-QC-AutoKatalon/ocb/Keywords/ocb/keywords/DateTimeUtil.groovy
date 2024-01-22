package ocb.keywords
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testcase.TestCaseFactory
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testdata.TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords

import internal.GlobalVariable

import org.openqa.selenium.WebElement
import org.openqa.selenium.WebDriver
import org.openqa.selenium.By

import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory
import com.kms.katalon.core.webui.driver.DriverFactory

import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObjectProperty

import com.kms.katalon.core.mobile.helper.MobileElementCommonHelper
import com.kms.katalon.core.util.KeywordUtil

import com.kms.katalon.core.webui.exception.WebElementNotFoundException

import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import java.text.DateFormat
import java.text.SimpleDateFormat

public class DateTimeUtil {

	/**
	 * Get the day
	 * @param date
	 * @param format
	 * @return
	 */
	@Keyword
	def getDay(String date, String format='dd/MM/yyyy') {
		SimpleDateFormat sdf = new SimpleDateFormat(format)
		Date dt = sdf.parse(date)
		int day = dt.getCalendarDate().getDayOfMonth()

		WebUI.comment(day.toString())
		return day.toString()
	}

	/**
	 * Add days to current date with default format 'dd/MM/yyyy'
	 * @param format
	 * @param day
	 * @return
	 */
	@Keyword
	def addDate(int day=0) {
		Date date = new Date().plus(day)
		SimpleDateFormat sdf = new SimpleDateFormat('dd/MM/yyyy')
		def result = sdf.format(date)
		WebUI.comment(result)
		return result
	}

	/**
	 * Minus days to current date with default format 'dd/MM/yyyy'
	 * @param format
	 * @param day
	 * @return
	 */
	@Keyword
	def minusDate(int day=0) {
		Date date = new Date().minus(day)
		SimpleDateFormat sdf = new SimpleDateFormat('dd/MM/yyyy')
		def result = sdf.format(date)
		WebUI.comment(result)
		return result
	}

	/**
	 * Add days to current date with default format 'dd/MM/yyyy'
	 * @param date
	 * @param formatadd
	 * @param day
	 * @return
	 */
	@Keyword
	def addDate(String date, String format='dd/MM/yyyy', int day=0) {
		SimpleDateFormat sdf = new SimpleDateFormat(format)
		Date dFormat = new SimpleDateFormat(format).parse(date)
		def result = sdf.format(dFormat.plus(day))
		WebUI.comment(result)
		return result
	}

	/**
	 * Get the current date string as 'dd/MM/yyyy'.
	 * @return the current date
	 */
	@Keyword
	def getCurrentDate(String format='dd/MM/yyyy') {
		Date date = new Date()
		SimpleDateFormat sdf = new SimpleDateFormat(format)
		def currentDate = sdf.format(date)
		KeywordUtil.logInfo('Current Date: ' + currentDate)
		WebUI.comment(currentDate)
		return currentDate
	}

	/**
	 * Get the current time string with formatter (default = 'HH:mm:ss' - E.g. 20:00:00)
	 * @return the current time
	 */
	@Keyword
	def getCurrentTime(String formatter = 'HH:mm:ss') {
		Date date = new Date()
		SimpleDateFormat sdf = new SimpleDateFormat(formatter)
		def currentTime = sdf.format(date)
		KeywordUtil.logInfo('Current Time: ' + currentDate)
		WebUI.comment(currentTime)
		return currentTime
	}
}
