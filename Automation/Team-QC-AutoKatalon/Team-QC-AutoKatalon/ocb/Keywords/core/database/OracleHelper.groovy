package core.database

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
import com.kms.katalon.util.CryptoUtil
import com.kms.katalon.core.util.KeywordUtil

import groovy.sql.Sql
import internal.GlobalVariable

public class OracleHelper {

	/**
	 * Connect Oracle DB
	 * @param url
	 * @param port
	 * @param dbName
	 * @param username
	 * @param password
	 * @return a static connection
	 */
	def connectDB(String url, String port, String dbName, String username, String password) {
		String connectionString = 'jdbc:oracle:thin:@' + url + ':' + port + '/' + dbName
		return Sql.newInstance(connectionString, username, (CryptoUtil.decode(CryptoUtil.getDefault(password))))
	}

	/**
	 * Execute Query to get first row
	 * @param url
	 * @param port
	 * @param dbName
	 * @param username
	 * @param password
	 * @param query
	 * @return
	 */
	@Keyword
	def findOne(String url, String port, String dbName, String username, String password, String query) {
		Sql sql = connectDB(url, port, dbName, username, password)
		try {
			return sql.firstRow(query)
		} finally {
			sql.close()
		}
	}

	/**
	 * Execute query
	 * @param query
	 * @param params
	 * @return
	 */
	@Keyword
	def findOne(String query, params = []) {
		Map conf = getDBConfiguration()

		Sql sql = Sql.newInstance(conf)
		try {
			return sql.firstRow(query, params)
		} finally {
			sql.close()
		}
	}

	/**
	 * Execute Query to get all rows
	 * @param url
	 * @param port
	 * @param dbName
	 * @param username
	 * @param password
	 * @param query
	 * @return
	 */
	@Keyword
	def findAll(String url, String port, String dbName, String username, String password, String query) {
		Sql sql = connectDB(url, port, dbName, username, password)
		try {
			return sql.rows(query)
		} finally {
			sql.close()
		}
	}

	/**
	 * Execute query
	 * @param query
	 * @param params
	 * @return
	 */
	@Keyword
	def findAll(String query, params = []) {
		Map conf = getDBConfiguration()

		Sql sql = Sql.newInstance(conf)
		try {
			return sql.rows(query, params)
		} finally {
			sql.close()
		}
	}

	/**
	 * Execute Query
	 * @param url
	 * @param port
	 * @param dbName
	 * @param username
	 * @param password
	 * @param query
	 * @return
	 */
	@Keyword
	def execute(String url, String port, String dbName, String username, String password, String query) {
		Sql sql = connectDB(url, port, dbName, username, password)
		try {
			return sql.execute(query)
		} finally {
			sql.close()
		}
	}

	/**
	 * Execute query
	 * @param query
	 * @param params
	 * @return
	 */
	@Keyword
	def execute(String query, params = []) {
		Map conf = getDBConfiguration()

		Sql sql = Sql.newInstance(conf)
		try {
			return sql.execute(query, params)
		} finally {
			sql.close()
		}
	}

	def getDBConfiguration() {
		String connectionString = 'jdbc:oracle:thin:@' +  GlobalVariable.G_oracleHost + ':' +  GlobalVariable.G_oraclePort + '/' + GlobalVariable.G_oracleDB
		return [
			url: connectionString,
			user: GlobalVariable.G_oracleUser,
			password: CryptoUtil.decode(CryptoUtil.getDefault(GlobalVariable.G_oracleEncryptPass))
		]
	}
}
