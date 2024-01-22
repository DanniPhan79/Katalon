import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling

switch (loginMethod.toString().toUpperCase()) {
    case 'PINCODE':
        'Select PIN CODE'
        Mobile.tap(findTestObject('Android/Login Method Page/optPinCode'), timeout)

        'Click on I Agree button'
        Mobile.tap(findTestObject('Android/Login Method Page/btnIAgree'), timeout)

        //Add pin code
        break
    case 'FACEID':
        'Select FaceID'
        Mobile.tap(findTestObject('Android/Login Method Page/optFaceId'), timeout)

        'Click on I Agree button'
        Mobile.tap(findTestObject('Android/Login Method Page/btnIAgree'), timeout)

        //Enable faceid
        break
    default:
        'Click on Skip button'
        Mobile.tap(findTestObject('Android/Login Method Page/btnSkip'), timeout)

        break
}

