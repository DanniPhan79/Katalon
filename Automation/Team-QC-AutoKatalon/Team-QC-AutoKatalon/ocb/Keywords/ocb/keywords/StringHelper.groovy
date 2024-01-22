package ocb.keywords

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import internal.GlobalVariable

import java.text.DecimalFormat
import java.util.regex.*

public class StringHelper {
	@Keyword
	/**
	 * Convert data into monetary value with currency
	 * @param amountString: Amount with digit only - 10000
	 * @param format decimal format for Amount - Default 1,000
	 * @param currency - Default "VND"
	 * @return
	 */
	def convertMonetary(String amountString, isEn = true) {
		try {
			isEn = isEn.toBoolean()

			DecimalFormat formatter = new DecimalFormat("#,###")
			amountString = (formatter.format(Double.parseDouble(amountString))).toString()
			if(!isEn) {
				amountString = amountString.replace(',','.')
			}
			Mobile.comment(amountString)
			return amountString
		} catch(Exception ex) {
			return ''
		}
	}

	@Keyword
	def convertToNumberValue(String inputString) {
		return inputString.replaceAll("\\D+", '')
	}
	@Keyword
	def extractStrs( String inputString ) {
		return inputString.findAll( /\d+/ )*.toString()
	}
	@Keyword
	def findStringByRegex(String inputString , String regexPattern) {
		Pattern pattern = Pattern.compile(regexPattern)
		Matcher matcher = pattern.matcher(inputString)

		List<String> listMatches = new ArrayList<String>()
		while(matcher.find()) {
			listMatches.add(matcher.group(1))
		}
		if(listMatches.size()>0) {
			Mobile.comment("Found out ${listMatches.size().toString()} result(s)")
			for(String s : listMatches) {
				Mobile.comment(s)
			}
			return listMatches
		}
		else {
			Mobile.comment("Not found.")
		}
	}
}
