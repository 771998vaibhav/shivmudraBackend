package com.shivmudra.utils;


import static java.util.Calendar.DATE;
import static java.util.Calendar.MONTH;
import static java.util.Calendar.YEAR;

import java.math.RoundingMode;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.Format;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;



public class CommonUtils {

	private static DecimalFormat df2 = new DecimalFormat("#.##");

	public static String toString(Object value) {
		if (value == null) {
			return "";
		}
		return value.toString();
	}

	public static String getStringValue(String s) {
		if (CommonUtils.isEmpty(s)) {
			return null;
		} else {
			return "'" + s + "'";
		}
	}

	public static Date convertStringDateymd(String value) {

		try {
			return new SimpleDateFormat("yyyy-MM-dd").parse(value);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String getSqlDateFormatWithNUllValues(Date date) {
		if (date == null) {
			return null;
		}
		return new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(date);
	}
//	public String convertSqlDateToStringDate(java.sql.Date sqlDate) {
//		
//		if (sqlDate != null) {
//			Date date = new Date(sqlDate.getTime());
//			return DateUtils.convertDateToString(date, DateUtils.DdmmyyyyFormat);
//		} 
//		
//		return null;
//		
//	}
//	
//	public String convertSqlDateToStringDateWithTime(java.sql.Date sqlDate) {
//		
//		if (sqlDate != null) {
//			Date date = new Date(sqlDate.getTime());
//			return DateUtils.convertDateToString(date, DateUtils.DdmmyyyyHHmmssFormat);
//		} 
//		
//			return null;
//		
//	}

	public static Date convertSqlDateToDateWithTime(java.sql.Date sqlDate) {
		if (sqlDate != null) {
			Date date = new Date(sqlDate.getTime());
			return date;
		}
		return null;
	}

//	public static String mapClaimTypeToDisplay(String claimType) {
//		if (claimType == null) {
//			return "";
//		}
//		if (CommonUtils.isEqualIgnoreCase(claimType, ClaimType.CASHLESS.name())) {
//			return "Cashless";
//		}
//		if (CommonUtils.isEqualIgnoreCase(claimType, ClaimType.CASHLESS_OPD.name())) {
//			return "Cashless OPD";
//		}
//		if (CommonUtils.isEqualIgnoreCase(claimType, ClaimType.REIMBURSEMENT.name())) {
//			return "Reimbursement";
//		}
//		if (CommonUtils.isEqualIgnoreCase(claimType, ClaimType.REIMBURSEMENT_OPD.name())) {
//			return "Reimbursement OPD";
//		}
//		if (CommonUtils.isEqualIgnoreCase(claimType, ClaimType.PRE_POST.name())) {
//			return "Pre-Post";
//		}
//		return "";
//	}

	public static java.sql.Timestamp convertUtilDateToSqlDate(Date date) {
		if (date != null) {
			java.sql.Timestamp sqlDate = new java.sql.Timestamp(date.getTime());
			return sqlDate;
		}
		return null;
	}

//	public static String decryptValue(String value, String keyPassPhrase) {
//		if (isEmpty(value)) {
//			return null;
//		}
//		PasswordUtils passwordUtils = new PasswordUtils(256, 100);
//		String salt = value.substring(0, 32);
//		String iv = value.substring(32, 64);
//		String encrypted = value.substring(64);
//		String decy = passwordUtils.decrypt(salt, iv, keyPassPhrase, encrypted);
//		return decy;
//	}

	public static String mapToDisplay(String value) {
		if (value == null) {
			return "";
		}
		value = value.toLowerCase();
		value = value.replace("_", " ");
		value = StringUtils.capitalize(value);
		return value;
	}

	public static String getGenderCodeOfUiic(String gender) {
		String response = "";
		if (gender.equalsIgnoreCase("Male")) {
			response = "100";
		} else if (gender.equalsIgnoreCase("Female")) {
			response = "101";
		}
		return response;
	}

	public static String getGenderCodeForOIC(String gender) {
		String response = "";
		if (gender.equalsIgnoreCase("Male")) {
			response = "M";
		} else if (gender.equalsIgnoreCase("Female")) {
			response = "F";
		}
		return response;
	}

//	public static String mapClaimTypeToDisplay(ClaimType claimType) {
//		if (claimType == null) {
//			return "";
//		}
//		if (claimType == ClaimType.CASHLESS) {
//			return "Cashless";
//		}
//		if (claimType == ClaimType.CASHLESS_OPD) {
//			return "Cashless OPD";
//		}
//		if (claimType == ClaimType.REIMBURSEMENT) {
//			return "Reimbursement";
//		}
//		if (claimType == ClaimType.REIMBURSEMENT_OPD) {
//			return "Reimbursement OPD";
//		}
//		if (claimType == ClaimType.PRE_POST) {
//			return "Pre-Post";
//		}
//		return "";
//	}
//
//	public static String displayDeductionFrequency(DeductionFrequency deductionFrequency) {
//		if (deductionFrequency == null) {
//			return null;
//		}
//		if (DeductionFrequency.ANNUALLY.equals(deductionFrequency)) {
//			return "Annually";
//		} else if (DeductionFrequency.BIANNUALLY.equals(deductionFrequency)) {
//			return "Half Yearly";
//		} else if (DeductionFrequency.CONSECUTIVE_THREE_MONTHS.equals(deductionFrequency)) {
//			return "Consecutive Three Months";
//		} else if (DeductionFrequency.MONTHLY.equals(deductionFrequency)) {
//			return "Monthly";
//		} else if (DeductionFrequency.QUARTERLY.equals(deductionFrequency)) {
//			return "Quaterly";
//		} else if (DeductionFrequency.ONE_INSTALLMENT.equals(deductionFrequency)) {
//			return "One Installment";
//		} else if (DeductionFrequency.TWO_INSTALLMENTS.equals(deductionFrequency)) {
//			return "Two Installments";
//		} else if (DeductionFrequency.THREE_INSTALLMENTS.equals(deductionFrequency)) {
//			return "Three Installments";
//		} else if (DeductionFrequency.FOUR_INSTALLMENTS.equals(deductionFrequency)) {
//			return "Four Installments";
//		} else if (DeductionFrequency.FIVE_INSTALLMENTS.equals(deductionFrequency)) {
//			return "Five Installments";
//		} else if (DeductionFrequency.SIX_INSTALLMENTS.equals(deductionFrequency)) {
//			return "Six Installments";
//		} else if (DeductionFrequency.SEVEN_INSTALLMENTS.equals(deductionFrequency)) {
//			return "Seven Installments";
//		} else if (DeductionFrequency.EIGHT_INSTALLMENTS.equals(deductionFrequency)) {
//			return "Eight Installments";
//		} else if (DeductionFrequency.NINE_INSTALLMENTS.equals(deductionFrequency)) {
//			return "Nine Installments";
//		} else if (DeductionFrequency.TEN_INSTALLMENTS.equals(deductionFrequency)) {
//			return "Ten Installments";
//		} else if (DeductionFrequency.ELEVEN_INSTALLMENTS.equals(deductionFrequency)) {
//			return "Eleven Installments";
//		} else if (DeductionFrequency.TWELVE_INSTALLMENTS.equals(deductionFrequency)) {
//			return "Twelve Installments";
//		}
//		return null;
//	}
//
//	public static Double noDeductionFrequency(DeductionFrequency deductionFrequency) {
//		if (deductionFrequency == null) {
//			return null;
//		}
//		if (DeductionFrequency.ANNUALLY.equals(deductionFrequency)) {
//			return 1.0;
//		} else if (DeductionFrequency.BIANNUALLY.equals(deductionFrequency)) {
//			return 2.0;
//		} else if (DeductionFrequency.CONSECUTIVE_THREE_MONTHS.equals(deductionFrequency)) {
//			return 3.0;
//		} else if (DeductionFrequency.MONTHLY.equals(deductionFrequency)) {
//			return 12.0;
//		} else if (DeductionFrequency.QUARTERLY.equals(deductionFrequency)) {
//			return 3.0;
//		} else if (DeductionFrequency.ONE_INSTALLMENT.equals(deductionFrequency)) {
//			return 1.0;
//		} else if (DeductionFrequency.TWO_INSTALLMENTS.equals(deductionFrequency)) {
//			return 2.0;
//		} else if (DeductionFrequency.THREE_INSTALLMENTS.equals(deductionFrequency)) {
//			return 3.0;
//		} else if (DeductionFrequency.FOUR_INSTALLMENTS.equals(deductionFrequency)) {
//			return 4.0;
//		} else if (DeductionFrequency.FIVE_INSTALLMENTS.equals(deductionFrequency)) {
//			return 5.0;
//		} else if (DeductionFrequency.SIX_INSTALLMENTS.equals(deductionFrequency)) {
//			return 6.0;
//		} else if (DeductionFrequency.SEVEN_INSTALLMENTS.equals(deductionFrequency)) {
//			return 7.0;
//		} else if (DeductionFrequency.EIGHT_INSTALLMENTS.equals(deductionFrequency)) {
//			return 8.0;
//		} else if (DeductionFrequency.NINE_INSTALLMENTS.equals(deductionFrequency)) {
//			return 9.0;
//		} else if (DeductionFrequency.TEN_INSTALLMENTS.equals(deductionFrequency)) {
//			return 10.0;
//		} else if (DeductionFrequency.ELEVEN_INSTALLMENTS.equals(deductionFrequency)) {
//			return 11.0;
//		} else if (DeductionFrequency.TWELVE_INSTALLMENTS.equals(deductionFrequency)) {
//			return 12.0;
//		}
//		return null;
//	}

	public static String convertNumberToCommaSeparated(Long value) {
		if (CommonUtils.isEmpty(value)) {
			return null;
		}
		Format format = com.ibm.icu.text.NumberFormat.getCurrencyInstance(new Locale("en", "in"));
		String y = format.format(value);
		String z = org.apache.commons.lang3.StringUtils.replace(y, ".00", "");
		return z;
	}

	public static String convertNumberToCommaSeparated(Double value1) {
		if (CommonUtils.isEmpty(value1)) {
			return null;
		}
		// Long value = value1.longValue();
		Double value = value1;
		Format format = com.ibm.icu.text.NumberFormat.getCurrencyInstance(new Locale("en", "in"));
		String y = format.format(value);
		String z = org.apache.commons.lang3.StringUtils.replace(y, ".00", "");
		return z;
	}

	public static String convertNumberToCommaSeparatedWithRupeeSymbol(Long value) {
		if (CommonUtils.isEmpty(value)) {
			return null;
		}
		Format format = com.ibm.icu.text.NumberFormat.getCurrencyInstance(new Locale("en", "in"));
		String y = format.format(value);
		String z = org.apache.commons.lang3.StringUtils.replace(y, ".00", "");
		String res = z.replace("Rs.", "₹");
		return res;
	}

	public static String convertNumberToCommaSeparatedWithoutRs(Double value) {
		if (CommonUtils.isEmpty(value)) {
			return null;
		}
		Format format = com.ibm.icu.text.NumberFormat.getCurrencyInstance(new Locale("en", "in"));
		String y = format.format(value);
		String z = org.apache.commons.lang3.StringUtils.replace(y, ".00", "");
		z = org.apache.commons.lang3.StringUtils.replace(z, "Rs. ", "");
		z = org.apache.commons.lang3.StringUtils.replace(z, "₹ ", "");
		z = org.apache.commons.lang3.StringUtils.replace(z, "₹", "");
		z = org.apache.commons.lang3.StringUtils.replace(z, "₹ ", "");
		// z = StringUtils.substring(z, 2);

		return z;
	}

	public static String convertNumberToCommaSeparated(String value) {
		if (CommonUtils.isEmpty(value)) {
			return null;
		}

		Long number = Long.parseLong(value);

		Format format = com.ibm.icu.text.NumberFormat.getCurrencyInstance(new Locale("en", "in"));
		String y = format.format(number);
		String z = org.apache.commons.lang3.StringUtils.replace(y, ".00", "");
		return z;
	}

	public static String setAmountWords(Double value) {
		if (value == null) {
			return null;
		}
		if (value < 999) {
			return Double.toString(value);
		}
		if (value > 99999D && value < 10000000) {
			if (value % 100000 == 0) {
				Double val = value / 100000D;
				return val.longValue() + " lacs";
			} else {
				Double val = value / 100000D;
				return val + " lacs";
			}
		}

		if (value > 999D && value < 100000) {
			return convertNumberToCommaSeparated(value.longValue());
		}

		if (value >= 10000000) {
			if (value % 10000000 == 0) {
				Double val = value / 10000000;
				return val.longValue() + " crore";
			} else {
				Double val = value / 10000000;
				return val + " crore";
			}
		}

		return null;
	}

	public static String setAmountWords1(Double value) {
		if (value == null) {
			return null;
		}
		if (value < 999) {
			return Double.toString(value);
		}
		if (value > 99999D && value < 10000000) {
			if (value % 100000 == 0) {
				Double val = value / 100000D;
				return val.longValue() + " lacs";
			} else {
				Double val = value / 100000D;
				return val + " lacs";
			}
		}

		if (value > 999D && value < 100000) {
			return convertNumberToCommaSeparated(value.longValue());
		}

		if (value >= 10000000) {
			if (value % 10000000 == 0) {
				Double val = value / 10000000;
				return val.longValue() + " crore";
			} else {
				Double val = value / 10000000;
				return val + " crore";
			}
		}

		return null;
	}

	public static String setAmountWords(Long value) {
		if (value == null) {
			return null;
		}
		if (value < 999) {
			return Long.toString(value);
		}

		if (value > 99999 && value < 10000000) {
			if (value % 100000 == 0) {
				Double val = value / 100000D;
				return val.longValue() + " lacs";
			} else {
				Double val = value / 100000D;
				return val + " lacs";
			}
		}

		if (value > 999D && value < 100000) {

			return convertNumberToCommaSeparated(value.longValue());
		}

		if (value >= 10000000) {
			if (value % 10000000 == 0) {
				Double val = value / 10000000D;
				return val.longValue() + " crore";
			} else {
				Double val = value / 10000000D;
				return val + " crore";
			}
		}

		return null;
	}

	public static Long roundOff(Double value) {
		if (value == null) {
			return null;
		}
		return Math.round(value);
	}

//	public static String mapRelationshipTypeToDisplay(RelationshipType relationshipType) {
//
//		if (relationshipType != null) {
//			if (relationshipType.equals(RelationshipType.BROTHER)) {
//				return "Brother";
//			} else if (relationshipType.equals(RelationshipType.SISTER)) {
//				return "Sister";
//			} else if (relationshipType.equals(RelationshipType.COUSIN)) {
//				return "Cousin";
//			} else if (relationshipType.equals(RelationshipType.FATHER)) {
//				return "Father";
//			} else if (relationshipType.equals(RelationshipType.MOTHER)) {
//				return "Mother";
//			} else if (relationshipType.equals(RelationshipType.GRAND_FATHER)) {
//				return "Grand Father";
//			} else if (relationshipType.equals(RelationshipType.GRAND_MOTHER)) {
//				return "Grand Mother";
//			} else if (relationshipType.equals(RelationshipType.FATHER_IN_LAW)) {
//				return "Father in Law";
//			} else if (relationshipType.equals(RelationshipType.MOTHER_IN_LAW)) {
//				return "Mother in Law";
//			} else if (relationshipType.equals(RelationshipType.DAUGHTER)) {
//				return "Daughter";
//			} else if (relationshipType.equals(RelationshipType.SON)) {
//				return "Son";
//			} else if (relationshipType.equals(RelationshipType.SELF_EMPLOYEE)
//					|| relationshipType.equals(RelationshipType.SELF)) {
//				return "Employee";
//			} else if (relationshipType.equals(RelationshipType.SPOUSE)) {
//				return "Spouse";
//			} else if (relationshipType.equals(RelationshipType.PARTNER)) {
//				return "Partner";
//			} else if (relationshipType.equals(RelationshipType.BROTHER_IN_LAW)) {
//				return "Brother in Law";
//			} else if (relationshipType.equals(RelationshipType.SISTER_IN_LAW)) {
//				return "Sister in Law";
//			} else if (relationshipType.equals(RelationshipType.SON_IN_LAW)) {
//				return "Son in Law";
//			} else if (relationshipType.equals(RelationshipType.DAUGHTER_IN_LAW)) {
//				return "Daughter in Law";
//			}
//		}
//		return null;
//	}
//
//	public static String mapCoverTypeToDisplay(String value) {
//		if (CoverType.BASE.name().equals(value)) {
//			return "Health";
//		}
//		if (CoverType.AMC.name().equals(value)) {
//			return "Parents Cover";
//		}
//		if (CoverType.AMC_TOPUP.name().equals(value)) {
//			return "Parents Top Up";
//		}
//		if (CoverType.BASE_TOPUP.name().equals(value)) {
//			return "Health Topup";
//		}
//		if (CoverType.BUFFER.name().equals(value)) {
//			return "Buffer";
//		}
//		if (CoverType.DRG.name().equals(value)) {
//			return "DRG";
//		}
//		if (CoverType.OPD.name().equals(value)) {
//			return "OPD";
//		}
//		if (CoverType.ADD_ON.name().equals(value)) {
//			return "Add On";
//		}
//		if (CoverType.WSA.name().equals(value)) {
//			return "WSA";
//		}
//		if ("TL".equals(value)) {
//			return "Term Life";
//		}
//		if ("CI".equals(value)) {
//			return "Critical Illness";
//		}
//		if ("PA".equals(value)) {
//			return "Personal Accident";
//		}
//
//		return null;
//	}

	public static String toString(String value) {
		if (value == null) {
			return "";
		}
		if (value.contains(",")) {
			return value.toString().replace(',', ' ');
		}
		return value.toString();
	}

	public static Boolean isEqualIgnoreCase(String value1, String value2) {
		if (isEmpty(value1) && isEmpty(value2)) {
			return Boolean.TRUE;
		}
		if (isNull(value1) || isNull(value2)) {
			return Boolean.FALSE;
		}
		if (isEmpty(value1) || isEmpty(value2)) {
			return Boolean.FALSE;
		}
		if (value1.equalsIgnoreCase(value2)) {
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

	public static Boolean isEqualIgnoreCase(Integer value1, Integer value2) {
		if (isEmpty(value1) && isEmpty(value2)) {
			return Boolean.TRUE;
		}
		if (isEmpty(value1) || isEmpty(value2)) {
			return Boolean.FALSE;
		}
		if (Integer.toString(value1).equalsIgnoreCase(Integer.toString(value2))) {
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

	public static Boolean isNotEqualIgnoreCase(String value1, String value2) {
		return !isEqualIgnoreCase(value1, value2);
	}

	public static Boolean isNotEqualIgnoreCase(Object value1, Object value2) {
		if (value1 == null && value2 == null) {
			return Boolean.FALSE;
		}
		if (value1 == null || value2 == null) {
			return Boolean.TRUE;
		}
		return !isEqualIgnoreCase(value1.toString(), value2.toString());
	}

	public static Boolean isNull(String value) {
		if (value == null) {
			return Boolean.TRUE;
		} else {
			return Boolean.FALSE;
		}
	}

	public static Boolean hasBannedSpecialChar(String value) {
		if (value != null && StringUtils.containsAny(value, '@', '#', '$')) {
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

	public static Boolean isOnlyCharacters(String value) {
		if (value != null && StringUtils.isAlpha(value)) {
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

	public static Boolean isOnlyNumber(String value) {
		if (value != null && StringUtils.isNumeric(value)) {
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

	public static Boolean isAlphaNumeric(String value) {
		if (value != null && StringUtils.isAlphanumeric(value)) {
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

	public static Boolean isAlphaNumericWithSpace(String value) {
		if (value != null && value.trim().length() > 0 && StringUtils.isAlphanumericSpace(value)) {
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

	public static Boolean isAlphaWithSpace(String value) {
		if (value != null && value.trim().length() > 0 && StringUtils.isAlphaSpace(value)) {
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

	public static Boolean isNumericWithSpace(String value) {
		if (value != null && value.trim().length() > 0 && StringUtils.isNumericSpace(value)) {
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

	public static Boolean isNumeric(String value) {
		if (value != null && value.trim().length() > 0 && StringUtils.isNumeric(value)) {
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

	public static Boolean isNotNull(String value) {
		return !isNull(value);
	}

	public static Boolean isNullOrZero(Double value) {
		if (value == null || value == 0) {
			return Boolean.TRUE;
		} else {
			return Boolean.FALSE;
		}
	}

	public static Boolean isNotNullOrZero(Double value) {
		return !isNullOrZero(value);
	}

	public static Boolean isNotNullOrZero(Long value) {
		return !isNullOrZero(value);
	}

	public static Boolean isNotNull(String[] value) {
		return !isNull(value);
	}

	public static Boolean isEmpty(String value) {
		if (value == null) {
			return Boolean.TRUE;
		} else if (value != null && value.trim().length() == 0) {
			return Boolean.TRUE;
		} else {
			return Boolean.FALSE;
		}
	}

	public static Boolean isEmpty(Double value) {
		if (value == null) {
			return Boolean.TRUE;
		} else {
			return Boolean.FALSE;
		}
	}

	public static Boolean isEmpty(Integer value) {
		if (value == null) {
			return Boolean.TRUE;
		} else {
			return Boolean.FALSE;
		}
	}

	public static Boolean isNotEmpty(String value) {
		return !isEmpty(value);
	}

	public static Boolean isNotEmpty(Double value) {
		return !isEmpty(value);
	}

	public static Boolean isNull(Object value) {
		if (value == null) {
			return Boolean.TRUE;
		} else {
			return Boolean.FALSE;
		}
	}

	public static Boolean isNotNull(Object value) {
		return !isNull(value);
	}

	public static Boolean isEmpty(Long value) {
		if (value == null) {
			return Boolean.TRUE;
		} else {
			return Boolean.FALSE;
		}
	}

	public static Boolean isNotEmpty(Long value) {
		return !isEmpty(value);
	}

	/**
	 * 
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static String formatDate(String date, String existingFormat, String requiredFormat, Locale locale)
			throws ParseException {
		DateFormat originalFormat = new SimpleDateFormat(existingFormat, locale);
		DateFormat targetFormat = new SimpleDateFormat(requiredFormat);
		Date dateInterim = originalFormat.parse(date);
		return targetFormat.format(dateInterim);
	}

	public static String formatDateToRequiredFormat(String date, String existingFormat, String requiredFormat)
			throws ParseException {
		SimpleDateFormat format1 = new SimpleDateFormat(existingFormat);
		SimpleDateFormat format2 = new SimpleDateFormat(requiredFormat);
		Date date1 = format1.parse(date);
		return format2.format(date1);
	}

//	public static ExtractedDate extractDateFormatFromString(String date) {
//
//		ExtractedDate exDate = null;
//
//		for (Map.Entry<String, String> entry : regexDateFormats.entrySet()) {
//
//			String dateRegex = entry.getKey();
//			Matcher m = Pattern.compile(dateRegex).matcher(date);
//			if (m.find() && m.group(1) != null) {
//				exDate = new ExtractedDate();
//				exDate.setDate(m.group(1));
//				exDate.setFormat(entry.getValue());
//			}
//		}
//		return exDate;
//	}

	public static String getSecurePassword(String passwordToHash) {
		String generatedPassword = null;
		try {

			String f = "[B@7d4991ad";
			// Create MessageDigest instance for MD5
			MessageDigest md = MessageDigest.getInstance("MD5");
			// Add password bytes to digest
			md.update(f.getBytes());
			// Get the hash's bytes
			byte[] bytes = md.digest(passwordToHash.getBytes());
			// This bytes[] has bytes in decimal format;
			// Convert it to hexadecimal format
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < bytes.length; i++) {
				sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
			}
			// Get complete hashed password in hex format
			generatedPassword = sb.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return generatedPassword;
	}

	/**
	 * 
	 * @return
	 */
	public static byte[] getSalt() throws NoSuchAlgorithmException, NoSuchProviderException {
		// Always use a SecureRandom generator
		SecureRandom sr = SecureRandom.getInstance("SHA1PRNG", "SUN");
		// Create array for salt
		byte[] salt = new byte[16];
		// Get a random salt
		sr.nextBytes(salt);
		// return salt
		return salt;
	}

	public static Boolean isNotZero(Double value) {
		if (value != null && value != 0) {
			return true;
		}
		return false;
	}

	public static Boolean isNotZero(String value) {
		if (value != null && !value.equalsIgnoreCase("0") && isNotEmpty(value)) {
			return true;
		}
		return false;
	}

	public static int getDiffYears(Date first, Date last) {
		Calendar a = getCalendar(first);
		Calendar b = getCalendar(last);
		int diff = b.get(YEAR) - a.get(YEAR);
		if (a.get(MONTH) > b.get(MONTH) || (a.get(MONTH) == b.get(MONTH) && a.get(DATE) > b.get(DATE))) {
			diff--;
		}
		return diff;
	}

	public static Calendar getCalendar(Date date) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		return cal;
	}

	public static Date convertString2Date(String value) {

		try {
			return new SimpleDateFormat("dd/MM/yyyy").parse(value);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Date convertString2Date2(String value) {

		try {
			return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").parse(value);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Date convertString2Date3(String value) {

		try {
			return new SimpleDateFormat("dd-MM-yyyy hh:mm:ss a").parse(value);
		} catch (ParseException e) {
			e.printStackTrace();

		}
		return null;
	}

	public static Date convertStringDate(String value) {

		try {
			return new SimpleDateFormat("dd-MM-yyyy").parse(value);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String getDateFormat(String date) {
		String currentFormat = null;

		if (isValidFormat("dd/MM/yyyy", date, Locale.ENGLISH)) {
			currentFormat = "dd/MM/yyyy";
		} else if (isValidFormat("dd-MM-yyyy", date, Locale.ENGLISH)) {
			currentFormat = "dd-MM-yyyy";
		} else if (isValidFormat("dd-MMM-yyyy", date, Locale.ENGLISH)) {
			currentFormat = "dd-MMM-yyyy";
		} else if (isValidFormat("MM dd, yyyy", date, Locale.ENGLISH)) {
			currentFormat = "MM dd, yyyy";
		} else if (isValidFormat("E, MMM dd yyyy", date, Locale.ENGLISH)) {
			currentFormat = "E, MMM dd yyyy";
		} else if (isValidFormat("E, MMM dd yyyy HH:mm:ss", date, Locale.ENGLISH)) {
			currentFormat = "E, MMM dd yyyy HH:mm:ss";
		} else if (isValidFormat("dd-MMM-yyyy HH:mm:ss", date, Locale.ENGLISH)) {
			currentFormat = "dd-MMM-yyyy HH:mm:ss";
		} else if (isValidFormat("dd-MM-yyyy HH:mm", date, Locale.ENGLISH)) {
			currentFormat = "dd-MM-yyyy HH:mm";
		} else if (isValidFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", date, Locale.ENGLISH)) {
			currentFormat = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
		} else if (isValidFormat("EEE MMM dd HH:mm:ss z yyyy", date, Locale.ENGLISH)) {
			currentFormat = "EEE MMM dd HH:mm:ss z yyyy";
		} else if (isValidFormat("E MMM dd HH:mm:ss z yyyy", date, Locale.ENGLISH)) {
			currentFormat = "E MMM dd HH:mm:ss z yyyy";
		}
		return currentFormat;
	}

	public static Boolean isValidFormat(String format, String value, Locale locale) {
		LocalDateTime ldt = null;
		DateTimeFormatter fomatter = DateTimeFormatter.ofPattern(format, locale);

		try {
			ldt = LocalDateTime.parse(value, fomatter);
			String result = ldt.format(fomatter);
			return result.equals(value);
		} catch (DateTimeParseException e) {
			try {
				LocalDate ld = LocalDate.parse(value, fomatter);
				String result = ld.format(fomatter);
				return result.equals(value);
			} catch (DateTimeParseException exp) {
				try {
					LocalTime lt = LocalTime.parse(value, fomatter);
					String result = lt.format(fomatter);
					return result.equals(value);
				} catch (DateTimeParseException e2) {
				}
			}
		}

		return false;
	}

	public static String convertDateToString(Date date) {
		try {
			if (isNotNull(date)) {
				DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
				String strDate = dateFormat.format(date);
				return strDate;
			}
			return "";
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String convertDateYYYYMMDDToString(Date date) {
		if (isNotNull(date)) {
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String strDate = dateFormat.format(date);
			return strDate;
		}
		return "";
	}

	private static String getDayNumberSuffix(int day) {
		if (day >= 11 && day <= 13) {
			return "th";
		}
		switch (day % 10) {
		case 1:
			return "st";
		case 2:
			return "nd";
		case 3:
			return "rd";
		default:
			return "th";
		}
	}

	public static String convertDateToStringBigFormat(Date date) {
		if (isNotNull(date)) {
			String dayNumberSuffix = getDayNumberSuffix(Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
			DateFormat dateFormat = new SimpleDateFormat("d'" + dayNumberSuffix + "' MMMM yyyy");
			String strDate = dateFormat.format(date);
			return strDate;
		}
		return "";
	}

	public static String convertDateToStringSlash(Date date) {
		if (isNotNull(date)) {
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			String strDate = dateFormat.format(date);
			return strDate;
		}
		return "";
	}

	public static String convertDateToStringymd(Date date) {
		if (isNotNull(date)) {
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String strDate = dateFormat.format(date);
			return strDate;
		}
		return "";
	}

	public static String convertDateToStringymdhms(Date date) {
		if (isNotNull(date)) {
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String strDate = dateFormat.format(date);
			return strDate;
		}
		return "";
	}

	public static String convertDateToStringDateTime(Date date) {
		if (isNotNull(date)) {
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss a");
			String strDate = dateFormat.format(date);
			return strDate;
		}
		return "";
	}

	public static String convertDateToStringDateTimeBC(Date date) {
		if (isNotNull(date)) {
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
			String strDate = dateFormat.format(date);
			return strDate;
		}
		return "";
	}

	public static String convertDateToStringDateTimeTillMin(Date date) {
		if (isNotNull(date)) {
			DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm");
			String strDate = dateFormat.format(date);
			return strDate;
		}
		return "";
	}

	public static String convertDateToTimeString(Date date) {
		if (isNotNull(date)) {
			String strDateFormat = "hh:mm:ss a";
			DateFormat dateFormat = new SimpleDateFormat(strDateFormat);
			String time = dateFormat.format(date);
			return time;
		}
		return "";
	}

	/**
	 * 
	 * @param date
	 * @return
	 * @throws ParseException
	 */
	public static Date formatDateInDate(String date, String existingFormat, String requiredFormat, Locale locale)
			throws ParseException {
		DateFormat originalFormat = new SimpleDateFormat(existingFormat, locale);
		DateFormat targetFormat = new SimpleDateFormat(requiredFormat);
		Date dateInterim = originalFormat.parse(date);
		String targetFormatDateStr = targetFormat.format(dateInterim);
		Date targetFormatDate = new SimpleDateFormat(requiredFormat, locale).parse(targetFormatDateStr);
		return targetFormatDate;
	}

	public static Date converToCommonDate(String date) throws ParseException {
		String currentDateFormat = getDateFormat(date);
		Date commonDate = formatDateInDate(date, currentDateFormat, "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.ENGLISH);
		return commonDate;
	}

	public static int hoursDifference(Date date1, Date date2) {

		final int MILLI_TO_HOUR = 1000 * 60 * 60;
		return (int) (date1.getTime() - date2.getTime()) / MILLI_TO_HOUR;
	}

	public static Boolean isNullOrZero(Long value) {
		if (value == null || value == 0) {
			return Boolean.TRUE;
		} else {
			return Boolean.FALSE;
		}
	}

//	public static ResponseEntity<TrueClaimResponse<String>> returnTrueClaimResponse(String string,
//			HttpStatus httpStatus, int status, String message) {
//		TrueClaimResponse<String> tcr = new TrueClaimResponse<>();
//		tcr.setStatus(status);
//		tcr.setMessage(message);
//		tcr.setBody(string);
//		return new ResponseEntity<TrueClaimResponse<String>>(tcr, httpStatus);
//	}
//
//	public static TrueClaimResponse<String> createResponse(String string, HttpStatus httpStatus, int status,
//			String message) {
//		TrueClaimResponse<String> tcr = new TrueClaimResponse<>();
//		tcr.setStatus(status);
//		tcr.setMessage(message);
//		tcr.setBody(string);
//		return tcr;
//	}

	public static int getAgefromDOB(Date dob) {
		try {
			Calendar c = Calendar.getInstance();
			c.setTime(dob);
			int year = c.get(Calendar.YEAR);
			int month = c.get(Calendar.MONTH) + 1;
			int date = c.get(Calendar.DATE);
			LocalDate l1 = LocalDate.of(year, month, date);
			LocalDate now1 = LocalDate.now();
			Long diff1 = ChronoUnit.YEARS.between(l1, now1);
			return diff1.intValue();
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	public static int getAgefromDOBTillOtherDate(Date dob, Date otherDate) {

		if (otherDate == null) {
			otherDate = new Date();
		}

		Calendar c = Calendar.getInstance();
		c.setTime(dob);
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH) + 1;
		int date = c.get(Calendar.DATE);
		LocalDate l1 = LocalDate.of(year, month, date);

		Calendar c1 = Calendar.getInstance();
		c1.setTime(otherDate);
		int year1 = c1.get(Calendar.YEAR);
		int month1 = c1.get(Calendar.MONTH) + 1;
		int date1 = c1.get(Calendar.DATE);
		LocalDate l2 = LocalDate.of(year1, month1, date1);

		System.out.println(l1.toString());
		System.out.println(l2.toString());

		Long diff1 = ChronoUnit.YEARS.between(l1, l2);
		return diff1.intValue();
	}

	public static int getDaysFromDate(Date dob) {
		Calendar c = Calendar.getInstance();
		c.setTime(dob);
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH) + 1;
		int date = c.get(Calendar.DATE);
		LocalDate l1 = LocalDate.of(year, month, date);
		LocalDate now1 = LocalDate.now();
		Long diff1 = ChronoUnit.DAYS.between(l1, now1);
		return diff1.intValue();
	}

	public static int getDiffMonths(Date startDate, Date endDate) {
		Calendar c1 = Calendar.getInstance();
		c1.setTime(startDate);
		Calendar c2 = Calendar.getInstance();
		c2.setTime(startDate);

		int year = c1.get(Calendar.YEAR);
		int month = c1.get(Calendar.MONTH) + 1;
		int date = c1.get(Calendar.DATE);
		LocalDate l1 = LocalDate.of(year, month, date);

		int year2 = c2.get(Calendar.YEAR);
		int month2 = c2.get(Calendar.MONTH) + 1;
		int date2 = c2.get(Calendar.DATE);
		LocalDate now1 = LocalDate.of(year2, month2, date2);
		Long diff1 = ChronoUnit.MONTHS.between(now1, l1);
		return diff1.intValue();
	}

	public static int getDiffDays(Date startDate, Date endDate) {
		Calendar c1 = Calendar.getInstance();
		c1.setTime(startDate);
		Calendar c2 = Calendar.getInstance();
		c2.setTime(endDate);

		int year = c1.get(Calendar.YEAR);
		int month = c1.get(Calendar.MONTH) + 1;
		int date = c1.get(Calendar.DATE);
		LocalDate l1 = LocalDate.of(year, month, date);

		int year2 = c2.get(Calendar.YEAR);
		int month2 = c2.get(Calendar.MONTH) + 1;
		int date2 = c2.get(Calendar.DATE);
		LocalDate now1 = LocalDate.of(year2, month2, date2);
		Long diff1 = ChronoUnit.DAYS.between(l1, now1);
		return diff1.intValue();
	}

//	public static ResponseEntity<TrueClaimResponse<Boolean>> returnTrueClaimResponseBoolean(Boolean b,
//			HttpStatus httpStatus, int status, String message) {
//		TrueClaimResponse<Boolean> tcr = new TrueClaimResponse<>();
//		tcr.setStatus(status);
//		tcr.setMessage(message);
//		tcr.setBody(b);
//		return new ResponseEntity<TrueClaimResponse<Boolean>>(tcr, httpStatus);
//	}

	public static Boolean isValidEmail(String email) {
		if (isNull(email))
			return Boolean.FALSE;
		String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
		return email.matches(regex);
	}

	public static Boolean isEqualIgnoreCase(Long value1, Long value2) {
		if (isEmpty(value1) && isEmpty(value2)) {
			return Boolean.TRUE;
		}
		if (isEmpty(value1) || isEmpty(value2)) {
			return Boolean.FALSE;
		}
		if (Long.toString(value1).equalsIgnoreCase(Long.toString(value2))) {
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

	public static Boolean isEqualIgnoreCase(Double value1, Double value2) {

		if (isNull(value1) && isNull(value2)) {
			return Boolean.TRUE;
		}

		if (isNull(value1) || isNull(value2)) {
			return Boolean.FALSE;
		}

		if (isEmpty(value1.toString()) || isEmpty(value2.toString())) {
			return Boolean.FALSE;
		}
		if (Double.toString(value1).equalsIgnoreCase(Double.toString(value2))) {
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

	public static Boolean isEquals(Object value1, Object value2) {
		if (isNull(value1) && isNull(value2)) {
			return Boolean.TRUE;
		}
		if (isNull(value1) || isNull(value2)) {
			return Boolean.FALSE;
		}
		if (value1.equals(value2)) {
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

//	public static RelationshipCategory mapRelationshipTypeToCategory(RelationshipType relationshipType) {
//
//		if (relationshipType != null) {
//			if (relationshipType == RelationshipType.BROTHER || relationshipType == RelationshipType.SISTER) {
//				return RelationshipCategory.SIBLING;
//			} else if (relationshipType == RelationshipType.COUSIN
//					|| relationshipType == RelationshipType.BROTHER_IN_LAW
//					|| relationshipType == RelationshipType.SISTER_IN_LAW) {
//				return RelationshipCategory.COUSIN;
//			} else if (relationshipType == RelationshipType.FATHER || relationshipType == RelationshipType.MOTHER
//					|| relationshipType == RelationshipType.GRAND_FATHER
//					|| relationshipType == RelationshipType.GRAND_MOTHER) {
//				return RelationshipCategory.PARENT;
//			} else if (relationshipType == RelationshipType.FATHER_IN_LAW
//					|| relationshipType == RelationshipType.MOTHER_IN_LAW) {
//				return RelationshipCategory.PARENT_IN_LAW;
//			} else if (relationshipType == RelationshipType.SON || relationshipType == RelationshipType.DAUGHTER
//					|| relationshipType == RelationshipType.SON_IN_LAW
//					|| relationshipType == RelationshipType.DAUGHTER_IN_LAW) {
//				return RelationshipCategory.CHILD;
//			} else if (relationshipType == RelationshipType.SELF_EMPLOYEE
//					|| relationshipType == RelationshipType.SELF) {
//				return RelationshipCategory.PRIMARY_INSURED;
//			} else if (relationshipType == RelationshipType.SPOUSE || relationshipType == RelationshipType.PARTNER) {
//				return RelationshipCategory.SPOUSE;
//			} else if (relationshipType == RelationshipType.ALL) {
//				return RelationshipCategory.ALL;
//			}
//		}
//		return null;
//	}
//
//	public static String mapRelationshipTypeToCategory(String relationshipType) {
//
//		if (relationshipType != null) {
//			if (isEqualIgnoreCase(relationshipType, "BROTHER") || isEqualIgnoreCase(relationshipType, "SISTER")) {
//				return RelationshipCategory.SIBLING.name();
//			} else if (isEqualIgnoreCase(relationshipType, "COUSIN")) {
//				return RelationshipCategory.COUSIN.name();
//			} else if (isEqualIgnoreCase(relationshipType, "FATHER") || isEqualIgnoreCase(relationshipType, "MOTHER")) {
//				return RelationshipCategory.PARENT.name();
//			} else if (isEqualIgnoreCase(relationshipType, "FATHER_IN_LAW")
//					|| isEqualIgnoreCase(relationshipType, "MOTHER_IN_LAW")) {
//				return RelationshipCategory.PARENT_IN_LAW.name();
//			} else if (isEqualIgnoreCase(relationshipType, "SON") || isEqualIgnoreCase(relationshipType, "DAUGHTER")) {
//				return RelationshipCategory.CHILD.name();
//			} else if (isEqualIgnoreCase(relationshipType, "EMPLOYEE")) {
//				return "EMPLOYEE";
//			} else if (isEqualIgnoreCase(relationshipType, "SPOUSE")
//					|| isEqualIgnoreCase(relationshipType, "PARTNER")) {
//				return RelationshipCategory.SPOUSE.name();
//			}
//		}
//		return null;
//	}
//
//	public static List<RelationshipType> mapRelationshipCategoryToType(RelationshipCategory relationshipCategory) {
//
//		List<RelationshipType> listObj = new ArrayList<RelationshipType>();
//		if (relationshipCategory != null) {
//			if (relationshipCategory == RelationshipCategory.SIBLING) {
//				listObj.add(RelationshipType.BROTHER);
//				listObj.add(RelationshipType.SISTER);
//				return listObj;
//			} else if (relationshipCategory == RelationshipCategory.COUSIN) {
//				listObj.add(RelationshipType.COUSIN);
//				listObj.add(RelationshipType.BROTHER_IN_LAW);
//				listObj.add(RelationshipType.SISTER_IN_LAW);
//				return listObj;
//			} else if (relationshipCategory == RelationshipCategory.PARENT) {
//				listObj.add(RelationshipType.FATHER);
//				listObj.add(RelationshipType.MOTHER);
//				listObj.add(RelationshipType.GRAND_FATHER);
//				listObj.add(RelationshipType.GRAND_MOTHER);
//				return listObj;
//			} else if (relationshipCategory == RelationshipCategory.PARENT_IN_LAW) {
//				listObj.add(RelationshipType.FATHER_IN_LAW);
//				listObj.add(RelationshipType.MOTHER_IN_LAW);
//				return listObj;
//			} else if (relationshipCategory == RelationshipCategory.CHILD) {
//				listObj.add(RelationshipType.SON);
//				listObj.add(RelationshipType.DAUGHTER);
//				listObj.add(RelationshipType.SON_IN_LAW);
//				listObj.add(RelationshipType.DAUGHTER_IN_LAW);
//				return listObj;
//			} else if (relationshipCategory == RelationshipCategory.PRIMARY_INSURED) {
//				listObj.add(RelationshipType.SELF);
//				return listObj;
//			} else if (relationshipCategory == RelationshipCategory.SPOUSE) {
//				listObj.add(RelationshipType.SPOUSE);
//				listObj.add(RelationshipType.PARTNER);
//				return listObj;
//			} else if (relationshipCategory == RelationshipCategory.ALL) {
//				listObj.add(RelationshipType.ALL);
//				return listObj;
//			}
//		}
//		return null;
//	}
//
//	public static String mapRelationshipTypeStringToDisplay(String relationshipType) {
//		String rel = null;
//		if (CommonUtils.isEqualIgnoreCase(relationshipType, RelationshipType.SELF.name())) {
//			rel = "Self";
//		} else if (CommonUtils.isEqualIgnoreCase(relationshipType, "PRIMARY_INSURED")) {
//			rel = "Self";
//		} else if (CommonUtils.isEqualIgnoreCase(relationshipType, "SELF")) {
//			rel = "Self";
//		} else if (CommonUtils.isEqualIgnoreCase(relationshipType, RelationshipType.SPOUSE.name())) {
//			rel = "Spouse";
//		} else if (CommonUtils.isEqualIgnoreCase(relationshipType, RelationshipType.SON.name())) {
//			rel = "Son";
//		} else if (CommonUtils.isEqualIgnoreCase(relationshipType, RelationshipType.DAUGHTER.name())) {
//			rel = "Daughter";
//		} else if (CommonUtils.isEqualIgnoreCase(relationshipType, RelationshipType.FATHER.name())) {
//			rel = "Father";
//		} else if (CommonUtils.isEqualIgnoreCase(relationshipType, RelationshipType.MOTHER.name())) {
//			rel = "Mother";
//		} else if (CommonUtils.isEqualIgnoreCase(relationshipType, RelationshipType.FATHER_IN_LAW.name())) {
//			rel = "Father in law";
//		} else if (CommonUtils.isEqualIgnoreCase(relationshipType, RelationshipType.MOTHER_IN_LAW.name())) {
//			rel = "Mother in law";
//		} else if (CommonUtils.isEqualIgnoreCase(relationshipType, RelationshipType.GRAND_FATHER.name())) {
//			rel = "Grandfather";
//		} else if (CommonUtils.isEqualIgnoreCase(relationshipType, RelationshipType.GRAND_MOTHER.name())) {
//			rel = "Grandmother";
//		} else if (CommonUtils.isEqualIgnoreCase(relationshipType, RelationshipType.BROTHER.name())) {
//			rel = "Brother";
//		} else if (CommonUtils.isEqualIgnoreCase(relationshipType, RelationshipType.SISTER.name())) {
//			rel = "Sister";
//		} else if (CommonUtils.isEqualIgnoreCase(relationshipType, RelationshipType.COUSIN.name())) {
//			rel = "Cousin";
//		} else if (CommonUtils.isEqualIgnoreCase(relationshipType, RelationshipType.PARTNER.name())) {
//			rel = "Partner";
//		} else if (CommonUtils.isEqualIgnoreCase(relationshipType, RelationshipType.SON_IN_LAW.name())) {
//			rel = "Son in law";
//		} else if (CommonUtils.isEqualIgnoreCase(relationshipType, RelationshipType.DAUGHTER_IN_LAW.name())) {
//			rel = "Daughter in law";
//		} else if (CommonUtils.isEqualIgnoreCase(relationshipType, RelationshipType.BROTHER_IN_LAW.name())) {
//			rel = "Brother in law";
//		} else if (CommonUtils.isEqualIgnoreCase(relationshipType, RelationshipType.SISTER_IN_LAW.name())) {
//			rel = "Sister in law";
//		} else {
//			rel = relationshipType;
//		}
//		return rel;
//	}
//
//	public static String mapRelationshipCategoryToDisplay(RelationshipCategory relationshipCategory) {
//		if (RelationshipCategory.ALL.equals(relationshipCategory)) {
//			return "All";
//		}
//		if (RelationshipCategory.CHILD.equals(relationshipCategory)) {
//			return "Child";
//		}
//		if (RelationshipCategory.COUSIN.equals(relationshipCategory)) {
//			return "Cousin";
//		}
//		if (RelationshipCategory.PARENT.equals(relationshipCategory)) {
//			return "Parent";
//		}
//		if (RelationshipCategory.PARENT_IN_LAW.equals(relationshipCategory)) {
//			return "Parent in Law";
//		}
//		if (RelationshipCategory.PRIMARY_INSURED.equals(relationshipCategory)) {
//			return "Primary Insured";
//		}
//		if (RelationshipCategory.SIBLING.equals(relationshipCategory)) {
//			return "Sibling";
//		}
//		if (RelationshipCategory.SPOUSE.equals(relationshipCategory)) {
//			return "Spouse";
//		}
//		return null;
//	}
//
//	public static String mapDisplayToRelationshipCategory(String string) {
//		if ("All".equals(string)) {
//			return "ALL";
//		}
//		if (CommonUtils.isEqualIgnoreCase("Child", string) || CommonUtils.isEqualIgnoreCase(string, "SON")
//				|| CommonUtils.isEqualIgnoreCase(string, "DAUGHTER")) {
//			return "CHILD";
//		}
//		if ("Cousin".equals(string)) {
//			return "COUSIN";
//		}
//		if ("Parent".equals(string) || CommonUtils.isEqualIgnoreCase(string, "Father")
//				|| CommonUtils.isEqualIgnoreCase(string, "Mother")) {
//			return "PARENT";
//		}
//		if ("Parent-in-Law".equals(string) || "Parent in Law".equals(string)
//				|| CommonUtils.isEqualIgnoreCase(string, "Father-in-law")
//				|| CommonUtils.isEqualIgnoreCase(string, "Mother-in-law")) {
//			return "PARENT_IN_LAW";
//		}
//		if ("Primary Insured".equals(string) || CommonUtils.isEqualIgnoreCase("EMPLOYEE", string)) {
//			return "PRIMARY_INSURED";
//		}
//		if ("Sibling".equals(string) || CommonUtils.isEqualIgnoreCase(string, "Brother")
//				|| CommonUtils.isEqualIgnoreCase(string, "Sister")) {
//			return "SIBLING";
//		}
//		if ("Spouse".equals(string) || CommonUtils.isEqualIgnoreCase("PARTNER", string)
//				|| CommonUtils.isEqualIgnoreCase("SPOUSE", string)) {
//			return "SPOUSE";
//		}
//		return null;
//	}
//
//	public static Gender mapGenderToEnum(String string) {
//		Gender gender = null;
//		if (string.equalsIgnoreCase("MALE")) {
//			gender = Gender.MALE;
//		}
//		if (string.equalsIgnoreCase("FEMALE")) {
//			gender = Gender.FEMALE;
//		}
//		if (string.equalsIgnoreCase("OTHERS") || string.equalsIgnoreCase("OTHER")) {
//			gender = Gender.OTHER;
//		}
//		return gender;
//	}

	public static Date getMeYesterday() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);

		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);

		Date d = cal.getTime();
		return d;
	}

	public static Date getMe530() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, -5);
		cal.set(Calendar.MINUTE, -30);

		Date d = cal.getTime();
		return d;
	}

	public static Date getMe25DaysBack() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -25);

		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);

		Date d = cal.getTime();
		return d;
	}

	public static Date setInitTime(Date date) {

		if (date == null) {
			return null;
		}

		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}

	public static Date setEndTime(Date date) {
		if (date == null) {
			return null;
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		return cal.getTime();
	}

	public static Boolean checkValidYear(Date date) {

		if (date == null) {
			return Boolean.TRUE;
		}
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		int year = cal.get(Calendar.YEAR);

		if (year < 1900) {
			return Boolean.FALSE;
		} else {
			return Boolean.TRUE;
		}
	}

	public static String trimWithNonBreakingSpace(String value) {

		if (value == null) {
			return null;
		}
		value = value.replaceAll("\u2007", " ");
		value = value.replaceAll("\u202F", " ");
		value = value.replaceAll("\u00A0", " ");
		value = value.replaceAll("\n", " ");
		value = value.replaceAll("\\R+", " ");
		value = value.replaceAll("\\R", " ");
		value = value.replaceAll("\\r\\n|\\r|\\n", " ");
		return value.trim();
	}

	public static String trimWithNonBreakingSpace2(String value) {

		if (value == null) {
			return null;
		}
		value = value.replaceAll("\u2007", " ");
		value = value.replaceAll("\u202F", " ");
		value = value.replaceAll("\u00A0", " ");
		value = value.replaceAll("\n", " ");
		value = value.replaceAll("\\R+", " ");
		value = value.replaceAll("\\R", " ");
		value = value.replaceAll("\\r\\n|\\r|\\n", " ");
		value = value.replaceAll("\"", "");
		return value.trim();
	}

//	public static String convertCoverageTypeToString(CoverType coverType) {
//		if (coverType == null) {
//			return "";
//		} else if (CoverType.BASE.name().equals(coverType.name())) {
//			return "Base";
//		} else if (CoverType.BASE_TOPUP.name().equals(coverType.name())) {
//			return "Base Topup";
//		} else if (CoverType.AMC.name().equals(coverType.name())) {
//			return "AMC";
//		} else if (CoverType.AMC_TOPUP.name().equals(coverType.name())) {
//			return "AMC Topup";
//		} else if (CoverType.DRG.name().equals(coverType.name())) {
//			return "DRG";
//		} else if (CoverType.OPD.name().equals(coverType.name())) {
//			return "OPD";
//		} else if (CoverType.BUFFER.name().equals(coverType.name())) {
//			return "Buffer";
//		} else if (CoverType.OTHERS.name().equals(coverType.name())) {
//			return "Others";
//		} else if (CoverType.ADD_ON.name().equals(coverType.name())) {
//			return "Add On";
//		} else if (CoverType.WSA.name().equals(coverType.name())) {
//			return "WSA";
//		}
//
//		return "";
//	}
//
//	public static String mapInsuranceTypetoDisplay(InsuranceType in) {
//		if (in == null) {
//			return null;
//		} else if (InsuranceType.CRITICAL_ILLNESS.equals(in)) {
//			return "Critical Illness";
//		} else if (InsuranceType.HEALTH.equals(in)) {
//			return "Health";
//		} else if (InsuranceType.HOME.equals(in)) {
//			return "Home";
//		} else if (InsuranceType.LIFE.equals(in)) {
//			return "Life";
//		} else if (InsuranceType.MOTOR.equals(in)) {
//			return "Motor";
//		} else if (InsuranceType.OPD.equals(in)) {
//			return "OPD";
//		} else if (InsuranceType.OTHER.equals(in)) {
//			return "Other";
//		} else if (InsuranceType.PERSONAL_ACCIDENT.equals(in)) {
//			return "Personal Accident";
//		} else if (InsuranceType.TERM_LIFE.equals(in)) {
//			return "Term Life";
//		} else if (InsuranceType.TRAVEL.equals(in)) {
//			return "Travel";
//		} else if (InsuranceType.ULIP.equals(in)) {
//			return "ULIP";
//		}
//
//		return "";
//	}

	public static Boolean isNotEmptyCell(Cell cell) {
		if (cell == null) {
			return Boolean.FALSE;
		}
		if (CommonUtils.isEmpty(cell.getStringCellValue())) {
			return Boolean.FALSE;
		}
		return Boolean.TRUE;
	}

	public static Date convertDateToUTCDate(Date dateOld) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(dateOld);
		calendar.setTimeZone(TimeZone.getTimeZone("UTC"));
		calendar.set(Calendar.HOUR_OF_DAY, 00);
		calendar.set(Calendar.MINUTE, 00);

		calendar.set(Calendar.SECOND, 00);

		calendar.set(Calendar.AM_PM, 0);

//		calendar.set(Calendar.HOUR_OF_DAY, 00);
//
//		calendar.get(Calendar.HOUR); // 12 hour clock
//        calendar.get(Calendar.HOUR_OF_DAY); // 24 hour clock
//        calendar.get(Calendar.MINUTE);
//        calendar.get(Calendar.SECOND);
//        calendar.get(Calendar.AM_PM);

		return calendar.getTime();
	}

	public static String addDaysToDate(Date date, int days) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar c1 = Calendar.getInstance();
		c1.setTime(date);
		c1.add(Calendar.DAY_OF_MONTH, days);
		String newDate = sdf.format(c1.getTime());
		return newDate;
	}

	public static Date addHoursToDate(Date date, int days) {
		Calendar c1 = Calendar.getInstance();
		c1.setTime(date);
		c1.add(Calendar.HOUR_OF_DAY, days);
		return c1.getTime();
	}

//	public static String mapHospitalCategoryToDisplay(HospitalCategory value) {
//		if (HospitalCategory.ALL.equals(value)) {
//			return "All";
//		}
//		if (HospitalCategory.NETWORK.equals(value)) {
//			return "Network";
//		}
//		if (HospitalCategory.NON_NETWORK.equals(value)) {
//			return "Non Network";
//		}
//
//		return null;
//	}
//
//	public static String mapPeriodStartTypeToDisplay(PeriodStartType value) {
//		if (PeriodStartType.COVERAGE_END_DATE.equals(value)) {
//			return "Coverage End Date";
//		}
//		if (PeriodStartType.COVERAGE_START_DATE.equals(value)) {
//			return "Coverage Start Date";
//		}
//		if (PeriodStartType.DATE_OF_ADMISSION.equals(value)) {
//			return "Date of Admission";
//		}
//		if (PeriodStartType.DATE_OF_BIRTH.equals(value)) {
//			return "Date of Birth";
//		}
//		if (PeriodStartType.DATE_OF_DISCHARGE.equals(value)) {
//			return "Date of Discharge";
//		}
//
//		return null;
//	}
//
//	public static String mapEventTypeToEnum(String eventTypeName) {
//		if (isEqualIgnoreCase(eventTypeName, "Welcome")) {
//			return EventType.WELCOME_MAILER.name();
//		}
//		if (isEqualIgnoreCase(eventTypeName, "Reminder - Welcome Mailer")) {
//			return EventType.WELCOME_MAILER_REMINDER.name();
//		}
//		if (isEqualIgnoreCase(eventTypeName, "Mid Term Joining")) {
//			return EventType.MIDTERM_WELCOME_MAILER.name();
//		}
//		if (isEqualIgnoreCase(eventTypeName, "Reminder - Mid Term Joining")) {
//			return EventType.MIDTERM_REMINDER.name();
//		}
//		if (isEqualIgnoreCase(eventTypeName, "Transfer")) {
//			return EventType.TRANSFER_WELCOME_MAILER.name();
//		}
//		if (isEqualIgnoreCase(eventTypeName, "Transfer Reminder")) {
//			return EventType.TRANSFER_REMINDER.name();
//		}
//		if (isEqualIgnoreCase(eventTypeName, "Grade change")) {
//			return EventType.GRADE_CHANGE_WELCOME_MAILER.name();
//		}
//		if (isEqualIgnoreCase(eventTypeName, "Reminder")) {
//			return EventType.GRADE_CHANGE_REMINDER.name();
//		}
//		if (isEqualIgnoreCase(eventTypeName, "Custom")) {
//			return EventType.CUSTOM.name();
//		}
//		if (isEqualIgnoreCase(eventTypeName, "Immediate Confirmation")) {
//			return EventType.IMMEDIATE_CONFIRMATION.name();
//		}
//		if (isEqualIgnoreCase(eventTypeName, "End of Window Period Confirmation")) {
//			return EventType.ENROLLMENT_CONFIRMATION_INCEPTION.name();
//		}
//		if (isEqualIgnoreCase(eventTypeName, "Midterm Confirmation")) {
//			return EventType.MIDTERM_CONFIRMATION.name();
//		}
//		if (isEqualIgnoreCase(eventTypeName, "Grade Change Confirmation")) {
//			return EventType.GRADE_CHANGE_CONFIRMATION.name();
//		}
//		if (isEqualIgnoreCase(eventTypeName, "Transfer sConfirmation")) {
//			return EventType.TRANSFER_CONFIRMATION.name();
//		}
//		if (isEqualIgnoreCase(eventTypeName, "ID card")) {
//			return EventType.ID_CARD.name();
//		}
//		if (isEqualIgnoreCase(eventTypeName, "Password Mailer")) {
//			return EventType.PASSWORD_MAILER.name();
//		}
//		if (isEqualIgnoreCase(eventTypeName, "Generic Mailer")) {
//			return EventType.GENERIC_MAILER.name();
//		}
//		if (isEqualIgnoreCase(eventTypeName, "Birthday Mailer")) {
//			return EventType.BIRTHDAY_MAILER.name();
//		}
//		if (isEqualIgnoreCase(eventTypeName, "Anniversary Mailer")) {
//			return EventType.ANNIVERSARY_MAILER.name();
//		}
//		return null;
//	}

	/**
	 * Get no of days between dates
	 * 
	 * @param fromDate
	 * @param toDate
	 */
	public static Long daysBetweenDates(Date fromDate, Date toDate) {
		if (fromDate != null && toDate != null) {
			return ((toDate.getTime() - fromDate.getTime()) / (1000 * 60 * 60 * 24));
		} else {
			return null;
		}
	}

	public static String convertDateToLiteralString(Date date) {
		String dateStr = convertDateToString(date);
		try {
			String dateTargetPattern = "dd-MMM-yyyy";

			SimpleDateFormat sf = new SimpleDateFormat("dd-MM-yyyy");
			Date dt = sf.parse(dateStr);
			sf.applyPattern(dateTargetPattern);

			String resultDate = sf.format(dt);
			return resultDate;
		} catch (Exception e) {
			e.printStackTrace();
			return dateStr;
		}
	}

	public static Date convertLiteralStringToDate(String date) {
		try {
			SimpleDateFormat sf = new SimpleDateFormat("dd-MMM-yyyy");
			Date dt = sf.parse(date);
			return dt;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static int getCurrentFinancialYear() {
		int year = Calendar.getInstance().get(Calendar.YEAR);

		int month = Calendar.getInstance().get(Calendar.MONTH) + 1;
		// System.out.println("Financial month : " + month);
		int result = 0;
		if (month < 4) {
			result = year - 1;
		} else {
			result = year;
		}
		return result;
	}

//	public static String mapRelationshipCategoryToDisplayName(RelationshipCategory relationshipType) {
//		if (relationshipType != null) {
//			if (relationshipType.equals(RelationshipCategory.PRIMARY_INSURED)) {
//				return "Employee";
//			} else if (relationshipType.equals(RelationshipCategory.SPOUSE)) {
//				return "Spouse";
//			} else if (relationshipType.equals(RelationshipCategory.PARTNER)) {
//				return "Partner";
//			} else if (relationshipType.equals(RelationshipCategory.PARTNER)) {
//				return "Partner";
//			} else if (relationshipType.equals(RelationshipCategory.CHILD)) {
//				return "Children";
//			} else if (relationshipType.equals(RelationshipCategory.PARENT)) {
//				return "Parent";
//			} else if (relationshipType.equals(RelationshipCategory.PARENT_IN_LAW)) {
//				return "Parent in law";
//			} else if (relationshipType.equals(RelationshipCategory.SIBLING)) {
//				return "Sibling";
//			} else if (relationshipType.equals(RelationshipCategory.COUSIN)) {
//				return "Cousin";
//			}
//		}
//		return null;
//	}

	public static String customFileFormatName(String docType, String status, String name) {
		if (docType == null) {
			docType = "Claim";
		}
		if (status == null) {
			status = "Generic";
		}
		if (name == null) {
			name = "Generic";
		}
		String doc = docType.trim();
		doc = doc.replaceAll(" ", "_");
		name = name.trim();
		name = name.replaceAll(" ", "_");
		String fileName = doc + "_" + status.trim() + "_" + name + "_" + convertDateToStringDateTime(new Date()) + "_"
				+ new Date().getTime();
		fileName = fileName.trim();
		fileName = fileName.replaceAll(" ", "_");
		fileName = fileName.replaceAll("-", "_");
		fileName = fileName.replaceAll(":", "_");
		System.out.println("**************************************** FINAL FILENAME::::::::" + fileName);
		return fileName;
	}

//	public static List<String> getClaimsEvents() {
//		List<String> eventTypes = new ArrayList<String>();
//		eventTypes.add(EventType.PRE_AUTH_SUBMIT.name());
//		eventTypes.add(EventType.PRE_AUTH_QUERY.name());
//		eventTypes.add(EventType.PRE_AUTH_QUERY_RESPOND.name());
//		eventTypes.add(EventType.PRE_AUTH_APPROVE.name());
//		eventTypes.add(EventType.ENHANCEMENT_SUBMIT.name());
//		eventTypes.add(EventType.ENHANCEMENT_QUERY.name());
//		eventTypes.add(EventType.ENHANCEMENT_QUERY_RESPOND.name());
//		eventTypes.add(EventType.ENHANCEMENT_APPROVE.name());
//		eventTypes.add(EventType.ENHANCEMENT_REJECT.name());
//		eventTypes.add(EventType.FINAL_BILLS_SUBMIT.name());
//		eventTypes.add(EventType.FINAL_BILLS_QUERY.name());
//		eventTypes.add(EventType.FINAL_BILLS_QUERY_RESPONDED.name());
//		eventTypes.add(EventType.CLAIM_APPROVE.name());
//		eventTypes.add(EventType.CLAIM_REJECT.name());
//		eventTypes.add(EventType.PHYSICAL_FILE_RECEIVED.name());
//		eventTypes.add(EventType.UTR_NUMBER_RECEIVED.name());
//		eventTypes.add(EventType.INSURER_APPROVAL_RECEIVED.name());
//		eventTypes.add(EventType.CLAIM_REGISTER.name());
//		eventTypes.add(EventType.DOCUMENT_RECOVERY.name());
//		eventTypes.add(EventType.INSURER_APPROVAL.name());
//		eventTypes.add(EventType.DOCUMENT_RECOVERY_CLOSED.name());
//		eventTypes.add(EventType.APPROVED.name());
//		eventTypes.add(EventType.REJECTED.name());
//		eventTypes.add(EventType.UTR_RECEIVED_SETTLEMENT.name());
//		eventTypes.add(EventType.SAVED_CLAIM.name());
//		eventTypes.add(EventType.VERIFY_BANK_DETAILS.name());
//		eventTypes.add(EventType.FILE_SUBMITTED_UNDER_QUERY.name());
//		eventTypes.add(EventType.CLAIM_REGISTER_OPD.name());
//		eventTypes.add(EventType.DOCUMENT_RECOVERY_OPD.name());
//		eventTypes.add(EventType.INSURER_APPROVAL_OPD.name());
//		eventTypes.add(EventType.DOCUMENT_RECOVERY_CLOSED_OPD.name());
//		eventTypes.add(EventType.APPROVED_OPD.name());
//		eventTypes.add(EventType.REJECTED_OPD.name());
//		eventTypes.add(EventType.UTR_RECEIVED_SETTLEMENT_OPD.name());
//		eventTypes.add(EventType.CLAIM_REGISTER_PRE_POST.name());
//		eventTypes.add(EventType.DOCUMENT_RECOVERY_PRE_POST.name());
//		eventTypes.add(EventType.INSURER_APPROVAL_PRE_POST.name());
//		eventTypes.add(EventType.DOCUMENT_RECOVERY_CLOSED_PRE_POST.name());
//		eventTypes.add(EventType.APPROVED_PRE_POST.name());
//		eventTypes.add(EventType.REJECTED_PRE_POST.name());
//		eventTypes.add(EventType.UTR_RECEIVED_SETTLEMENT_PRE_POST.name());
//		eventTypes.add(EventType.UNENDORSED_UNENROLLED.name());
//		eventTypes.add(EventType.FILE_SUBMITTED_QUERY_RESPONDED.name());
//		eventTypes.add(EventType.SEND_TO_INSURER.name());
//		eventTypes.add(EventType.CLAIM_REOPEN_CASHLESS.name());
//		eventTypes.add(EventType.READY_TO_PAY.name());
//		eventTypes.add(EventType.DIRECT_REJECTION.name());
//		eventTypes.add(EventType.CLAIM_REOPEN_REIMBURSEMENT.name());
//		return eventTypes;
//	}
//
//	public static List<String> getEnrollmentEvents() {
//		List<String> events = new ArrayList<String>();
//		events.add(EventType.WELCOME_MAILER.name());
//		events.add(EventType.WELCOME_MAILER_REMINDER.name());
//		events.add(EventType.ENROLLMENT_CONFIRMATION_INCEPTION.name());
//		events.add(EventType.MIDTERM_WELCOME_MAILER.name());
//		events.add(EventType.MIDTERM_REMINDER.name());
//		events.add(EventType.MIDTERM_CONFIRMATION.name());
//		events.add(EventType.GRADE_CHANGE_WELCOME_MAILER.name());
//		events.add(EventType.GRADE_CHANGE_REMINDER.name());
//		events.add(EventType.GRADE_CHANGE_CONFIRMATION.name());
//		events.add(EventType.TRANSFER_WELCOME_MAILER.name());
//		events.add(EventType.TRANSFER_REMINDER.name());
//		events.add(EventType.TRANSFER_CONFIRMATION.name());
//		events.add(EventType.ID_CARD.name());
//		events.add(EventType.IMMEDIATE_CONFIRMATION.name());
//		events.add(EventType.CUSTOM.name());
//		events.add(EventType.LOGIN.name());
//		events.add(EventType.ADD_NOMINEE.name());
//		events.add(EventType.INDIVIDUAL_MEMBER_EDIT.name());
//		return events;
//	}
//
//	public static String blackListClaimCondition(String value) {
//		if (BlackListClaimStatus.INVESTIGATE_CLAIM.name().equals(value)) {
//			return "Investigate the claim and process as per investigation findings";
//		}
//		if (BlackListClaimStatus.REJECT_CLAIM.name().equals(value)) {
//			return "Reject the claim. Investigation not required.";
//		}
//		if (BlackListClaimStatus.INVESTIGATE_REFER_TO_INSURER.name().equals(value)) {
//			return "Investigate the claim and refer to Insurer";
//		}
//		return null;
//	}
//
//	public static String mapProviderClaimStatus(String claimStatus) {
//		if (CommonUtils.isEqualIgnoreCase(claimStatus, ClaimStatus.PRE_AUTH_SUBMITTED.name())
//				|| CommonUtils.isEqualIgnoreCase(claimStatus, ClaimStatus.PRE_AUTH_UNDER_REVIEW.name())
//				|| CommonUtils.isEqualIgnoreCase(claimStatus, ClaimStatus.PRE_AUTH_QUERY_RESPONDED.name())) {
//			return ClaimStatus.PRE_AUTH_SUBMITTED.name();
//		} else if (CommonUtils.isEqualIgnoreCase(claimStatus, ClaimStatus.ENHANCEMENT_SUBMITTED.name())
//				|| CommonUtils.isEqualIgnoreCase(claimStatus, ClaimStatus.ENHANCEMENT_UNDER_REVIEW.name())
//				|| CommonUtils.isEqualIgnoreCase(claimStatus, ClaimStatus.ENHANCEMENT_QUERY_RESPONDED.name())) {
//			return ClaimStatus.ENHANCEMENT_SUBMITTED.name();
//		} else if (CommonUtils.isEqualIgnoreCase(claimStatus, ClaimStatus.FINAL_BILLS_SUBMITTED.name())
//				|| CommonUtils.isEqualIgnoreCase(claimStatus, ClaimStatus.FINAL_BILLS_UNDER_REVIEW.name())
//				|| CommonUtils.isEqualIgnoreCase(claimStatus, ClaimStatus.FINAL_BILLS_QUERY_RESPONDED.name())) {
//			return ClaimStatus.FINAL_BILLS_SUBMITTED.name();
//		} else if (CommonUtils.isEqualIgnoreCase(claimStatus, ClaimStatus.SCANNED_FILE_RECEIVED.name())
//				|| CommonUtils.isEqualIgnoreCase(claimStatus, ClaimStatus.FILE_SUBMITTED_QUERY_RESPONDED.name())) {
//			return ClaimStatus.SCANNED_FILE_RECEIVED.name();
//		} else {
//			return claimStatus;
//		}
//	}

	public static Boolean isNullOrZero(String preClaimApprovedAmount) {
		if (preClaimApprovedAmount == null) {
			return Boolean.TRUE;
		}
		if (CommonUtils.isEqualIgnoreCase(preClaimApprovedAmount, "0")) {
			return Boolean.TRUE;
		}
		if (CommonUtils.isEqualIgnoreCase(preClaimApprovedAmount, "0.0")) {
			return Boolean.TRUE;
		}
		return Boolean.FALSE;
	}

	public static Boolean isEmptyInt(Integer value) {
		if (value == null) {
			return Boolean.TRUE;
		} else {
			return Boolean.FALSE;
		}
	}

	public static boolean isValidIFSCode(String str) {
		String regex = "^[A-Z]{4}0[A-Z0-9]{6}$";
		Pattern p = Pattern.compile(regex);
		if (str == null) {
			return false;
		}
		Matcher m = p.matcher(str);
		return m.matches();
	}

	public static boolean isValidBankAccountNumber(String str) {
		// String regex = "/^(?:[0-9]{9}|[0-9]{18}|[0-9]{2}-[0-9]{3}-[0-9]{6})$/";
		String regex = "[a-zA-Z0-9]{7,18}";
		Pattern p = Pattern.compile(regex);
		if (str == null) {
			return false;
		}
		Matcher m = p.matcher(str);
		return m.matches();
	}

	public static String findRange(Long duration) {
		if (duration == null) {
			return "not defined";
		}
		if (duration <= 60) {
			return "0 to 1";
		}
		if (duration > 60 && duration <= 120) {
			return "1 to 2";
		}
		if (duration > 120 && duration <= 180) {
			return "2 to 3";
		}
		if (duration > 180) {
			return "3+";
		}
		return "not defined";
	}

	public static String getSqlOnlyDateFormat(Date date) {
		if (date != null) {
			return new SimpleDateFormat("dd-MM-yyyy").format(date);
		} else {
			return new SimpleDateFormat("dd-MM-yyyy").format(new Date());
		}
	}

	public static String getSqlOnlyTimeFormat(Date date) {
		if (date != null) {
			return new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(date);
		} else {
			return new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date());
		}
	}

	public static Boolean checkDateCannotFutureDate(Date date) {

		String currentDate = new SimpleDateFormat("dd/MM/yyyy").format(new Date());

		Date cd = CommonUtils.convertString2Date(currentDate);

		int result = date.compareTo(cd);

		if (result < 0 || result == 0) {
			return Boolean.TRUE;
		} else {
			return Boolean.FALSE;
		}
	}

	public static Boolean checkDateBtwPolicyDates(Date startDate, Date endDate, Date currentDate) {

		String sDate = new SimpleDateFormat("dd/MM/yyyy").format(startDate);
		Date sd = CommonUtils.convertString2Date(sDate);

		String eDate = new SimpleDateFormat("dd/MM/yyyy").format(endDate);
		Date ed = CommonUtils.convertString2Date(eDate);

		String cDate = new SimpleDateFormat("dd/MM/yyyy").format(currentDate);
		Date checkDate = CommonUtils.convertString2Date(cDate);

		int result = checkDate.compareTo(sd);
		int result2 = checkDate.compareTo(ed);

		if ((result > 0 || result == 0) && (result2 < 0 || result2 == 0)) {
			return Boolean.TRUE;
		} else {
			return Boolean.FALSE;
		}
	}

	public static String get10sRange(Integer number) {
		try {
			int division = 0;
			int upperLimit = 0;
			int lowerLimit = 0;
			if (number % 10 == 0) {
				division = number / 10;
				upperLimit = division * 10 - 9;
				lowerLimit = (division * 10);
				return Integer.toString(upperLimit) + "-" + Integer.toString(lowerLimit);
			} else {
				division = number / 10;
				upperLimit = (division) * 10 + 1;
				lowerLimit = upperLimit + 9;
				return Integer.toString(upperLimit) + "-" + Integer.toString(lowerLimit);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Long getDaysDifference(Date date1, Date date2) {
		try {
			Long differneceInDays = TimeUnit.MILLISECONDS.toDays(date1.getTime() - date2.getTime());
			return differneceInDays;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public static Long getHoursDifference(Date date1, Date date2) {
		try {
			return TimeUnit.MILLISECONDS.toHours(date1.getTime() - date2.getTime());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	public static String getOnlyDateFromDate(Date date) {

		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		String dateOnly = dateFormat.format(date);
		return dateOnly;
	}

	public static String getOnlyTime(Date date) {
		SimpleDateFormat localDateFormat = new SimpleDateFormat("HH:mm:ss");
		String time = localDateFormat.format(date);
		return time;
	}

	public static Long getMinutesDifference(Date date1, Date date2) {
		try {
			if (date1 == null) {
				date1 = new Date();
			}
			if (date2 == null) {
				date2 = new Date();
			}
			return TimeUnit.MILLISECONDS.toMinutes(date1.getTime() - date2.getTime());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}

	public static Date convertLocalDateToDate(LocalDate date) {
		try {
			return Date.from(date.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static Boolean isdateWithinRange(Date date, Date lowerRangeDate, Date upperRangeDate) {
		try {
			if (date.after(lowerRangeDate) && date.before(upperRangeDate)) {
				return Boolean.TRUE;
			} else {
				return Boolean.FALSE;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String replaceCommaWithSpace(String str) {
		try {
			if (!CommonUtils.isNull(str)) {
				return str.replace(',', ' ');
			} else {
				return str;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String convertAmountToWords(Integer number) {
		try {
			if (number == 0) {
				return "zero";
			} else {
				return numberToWord(number);
			}
		} catch (Exception e) {
			System.out.println("Please enter a valid number");
		}
		return null;
	}

	private static String numberToWord(Integer number) {
		// variable to hold string representation of number
		String words = "";
		String unitsArray[] = { "zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten",
				"eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen" };
		String tensArray[] = { "zero", "ten", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty",
				"ninety" };
		if (number == 0) {
			return "zero";
		}

		if (number < 0) {
			String numberStr = "" + number;
			numberStr = numberStr.substring(1);
			return "minus " + numberToWord(Integer.parseInt(numberStr));
		}

		if ((number / 100000) > 0) {
			words += numberToWord(number / 100000) + " lac ";
			number %= 100000;
		}
		// check if number is divisible by 1 thousand
		if ((number / 1000) > 0) {
			words += numberToWord(number / 1000) + " thousand ";
			number %= 1000;
		}
		// check if number is divisible by 1 hundred
		if ((number / 100) > 0) {
			words += numberToWord(number / 100) + " hundred ";
			number %= 100;
		}
		if (number > 0) {
			// check if number is within teens
			if (number < 20) {
				// fetch the appropriate value from unit array
				words += unitsArray[number];
			} else {
				// fetch the appropriate value from tens array
				words += tensArray[number / 10];
				if ((number % 10) > 0) {
					words += "-" + unitsArray[number % 10];
				}
			}
		}
		return words;
	}

//	public static Gender mapRelationshipTypeWithGender(RelationshipType type) {
//
//		if (type == RelationshipType.BROTHER || type == RelationshipType.FATHER
//				|| type == RelationshipType.FATHER_IN_LAW || type == RelationshipType.GRAND_FATHER
//				|| type == RelationshipType.SON || type == RelationshipType.SON_IN_LAW
//				|| type == RelationshipType.BROTHER_IN_LAW) {
//			return Gender.MALE;
//		} else if (type == RelationshipType.SISTER || type == RelationshipType.MOTHER
//				|| type == RelationshipType.GRAND_MOTHER || type == RelationshipType.MOTHER_IN_LAW
//				|| type == RelationshipType.DAUGHTER || type == RelationshipType.DAUGHTER_IN_LAW
//				|| type == RelationshipType.SISTER_IN_LAW) {
//			return Gender.FEMALE;
//		}
//		return null;
//	}

	public static List<String> getApprovedClaimStatus() {
		List<String> approvedClaimStatus = new ArrayList<String>();
		approvedClaimStatus.add("CLAIM_APPROVED");
		approvedClaimStatus.add("SCANNED_FILE_RECEIVED");
		approvedClaimStatus.add("READY_TO_PAY");
		approvedClaimStatus.add("FILE_SUBMITTED_UNDER_QUERY");
		approvedClaimStatus.add("FILE_SUBMITTED_QUERY_RESPONDED");
		approvedClaimStatus.add("PHYSICAL_FILE_PENDING");
		approvedClaimStatus.add("UPLOADED_FOR_PAYMENT");
		approvedClaimStatus.add("PAYMENT_BOUNCE");
		approvedClaimStatus.add("PARTIALLY_SETTLED");
		approvedClaimStatus.add("SETTLED");
		return approvedClaimStatus;
	}

	public static String convertNumberToCommaSeparatedWithoutRupeeSymbol(Double value) {
		if (CommonUtils.isEmpty(value)) {
			return null;
		}
		String format = NumberFormat.getNumberInstance(Locale.US).format(value);
		return format;
	}

	public static String convertDoubleToStringWithZero(Double val) {
		df2.setRoundingMode(RoundingMode.UP);
		if (val != null && val > 0) {
			return df2.format(val);
		}
		return "0";
	}

	public static String convertDoubleToString(Double val) {
		df2.setRoundingMode(RoundingMode.UP);
		if (val != null && val > 0) {
			return df2.format(val);
		}
		return null;
	}

	public static Long setValueOrZero(String value) {
		if (value != null && value.trim().length() > 0) {
			return Long.parseLong(value);
		}
		return 0L;
	}

	public static Integer setValueOrZeroInt(String value) {
		if (value != null && value.trim().length() > 0) {
			return Integer.parseInt(value);
		}
		return 0;
	}

	public static String convertLongToString(Long val) {
		if (val != null && val > 0) {
			return String.valueOf(val);
		}
		return null;
	}

	public static boolean isValidGSTNo(String str) {
		String regex = "^[0-9]{2}[A-Z]{5}[0-9]{4}[A-Z]{1}[1-9A-Z]{1}Z[0-9A-Z]{1}$";
		Pattern p = Pattern.compile(regex);
		if (isEmpty(str)) {
			return false;
		}
		Matcher m = p.matcher(str);
		return m.matches();
	}

	public static String setValueOrBlank(String str) {
		if (str == null || str.trim().length() == 0) {
			return "";
		}
		return str;
	}

//	public static List<String> getWaitingCashlessApproval() {
//		List<String> claimStatus = new ArrayList<String>();
//		claimStatus.add(ClaimStatus.MEMBER_UNENDORSED.name());
//		claimStatus.add(ClaimStatus.PRE_APPROVAL_RECEIVED.name());
//		claimStatus.add(ClaimStatus.PRE_AUTH_QUERY_RESPONDED.name());
//		claimStatus.add(ClaimStatus.PRE_AUTH_UNDER_QUERY.name());
//		claimStatus.add(ClaimStatus.PRE_AUTH_UNDER_REVIEW.name());
//		claimStatus.add(ClaimStatus.PRE_AUTH_SUBMITTED.name());
//		claimStatus.add(ClaimStatus.ENHANCEMENT_SUBMITTED.name());
//		claimStatus.add(ClaimStatus.ENHANCEMENT_QUERY_RESPONDED.name());
//		claimStatus.add(ClaimStatus.ENHANCEMENT_REJECTED.name());
//		claimStatus.add(ClaimStatus.ENHANCEMENT_UNDER_QUERY.name());
//		claimStatus.add(ClaimStatus.ENHANCEMENT_UNDER_REVIEW.name());
//		claimStatus.add(ClaimStatus.ENHANCEMENT_APPROVED.name());
//		claimStatus.add(ClaimStatus.FINAL_BILLS_QUERY_RESPONDED.name());
//		claimStatus.add(ClaimStatus.FINAL_BILLS_SUBMITTED.name());
//		claimStatus.add(ClaimStatus.FINAL_BILLS_UNDER_QUERY.name());
//		claimStatus.add(ClaimStatus.FINAL_BILLS_UNDER_REVIEW.name());
//		return claimStatus;
//	}
//
//	public static List<String> getCashlessDischargeStatus() {
//		List<String> claimStatus = new ArrayList<String>();
//		claimStatus.add(ClaimStatus.CLAIM_APPROVED.name());
//		claimStatus.add(ClaimStatus.PHYSICAL_FILE_PENDING.name());
//		claimStatus.add(ClaimStatus.SCANNED_FILE_RECEIVED.name());
//		claimStatus.add(ClaimStatus.READY_TO_PAY.name());
//		claimStatus.add(ClaimStatus.UPLOADED_FOR_PAYMENT.name());
//		claimStatus.add(ClaimStatus.SETTLED.name());
//		claimStatus.add(ClaimStatus.PARTIALLY_SETTLED.name());
//		claimStatus.add(ClaimStatus.PAYMENT_BOUNCE.name());
//		return claimStatus;
//	}
//
//	public static List<String> getReimbursementOpenStatus() {
//		List<String> claimStatus = new ArrayList<String>();
//		claimStatus.add("CLAIM_REGISTERED");
//		claimStatus.add("IN_PROCESSING");
//		claimStatus.add("ON_HOLD");
//		claimStatus.add(ClaimStatus.PHYSICAL_FILE_PENDING.name());
//		claimStatus.add(ClaimStatus.READY_TO_PAY.name());
//		claimStatus.add(ClaimStatus.UPLOADED_FOR_PAYMENT.name());
//		claimStatus.add(ClaimStatus.PARTIALLY_SETTLED.name());
//		claimStatus.add(ClaimStatus.PAYMENT_BOUNCE.name());
//		return claimStatus;
//	}
//
//	public static List<String> getReimbursementCalculationStatus() {
//		List<String> claimStatus = new ArrayList<String>();
//		claimStatus.add(ClaimStatus.PHYSICAL_FILE_PENDING.name());
//		claimStatus.add(ClaimStatus.READY_TO_PAY.name());
//		claimStatus.add(ClaimStatus.UPLOADED_FOR_PAYMENT.name());
//		claimStatus.add(ClaimStatus.PARTIALLY_SETTLED.name());
//		claimStatus.add(ClaimStatus.PAYMENT_BOUNCE.name());
//		claimStatus.add(ClaimStatus.SETTLED.name());
//		return claimStatus;
//	}
//
//	public static String sanitizeClaimStatusAndSubreason(String claimStatus, String claimType, String claimSubReason,
//			Boolean isDataEntry) {
//		try {
//			if (claimType.equalsIgnoreCase(ClaimType.CASHLESS.name())
//					|| claimType.equalsIgnoreCase(ClaimType.CASHLESS_OPD.name())) {
//				if (CommonUtils.isEqualIgnoreCase(claimStatus, ClaimStatus.PRE_AUTH_SUBMITTED.name())) {
//					return "Pre Auth Received";
//				} else if (CommonUtils.isEqualIgnoreCase(claimStatus, ClaimStatus.PRE_AUTH_UNDER_QUERY.name())) {
//					return "Pre Auth Under Query";
//				} else if (CommonUtils.isEqualIgnoreCase(claimStatus, ClaimStatus.PRE_AUTH_QUERY_RESPONDED.name())) {
//					return "Pre Auth Query Response received";
//				} else if (CommonUtils.isEqualIgnoreCase(claimStatus, ClaimStatus.PRE_AUTH_UNDER_REVIEW.name())) {
//					return "Pre Auth Received";
//				} else if (CommonUtils.isEqualIgnoreCase(claimStatus, ClaimStatus.PRE_APPROVAL_RECEIVED.name())) {
//					return "Pre Approval Given";
//				} else if (CommonUtils.isEqualIgnoreCase(claimStatus, ClaimStatus.ENHANCEMENT_SUBMITTED.name())) {
//					return "Enhancement Request Received";
//				} else if (CommonUtils.isEqualIgnoreCase(claimStatus, ClaimStatus.ENHANCEMENT_UNDER_QUERY.name())) {
//					return "Enhancement Under Query";
//				} else if (CommonUtils.isEqualIgnoreCase(claimStatus, ClaimStatus.ENHANCEMENT_QUERY_RESPONDED.name())) {
//					return "Enhancement Query Response received";
//				} else if (CommonUtils.isEqualIgnoreCase(claimStatus, ClaimStatus.ENHANCEMENT_UNDER_REVIEW.name())) {
//					return "Enhancement Request Received";
//				} else if (CommonUtils.isEqualIgnoreCase(claimStatus, ClaimStatus.ENHANCEMENT_APPROVED.name())) {
//					return "Enhancement Approved";
//				} else if (CommonUtils.isEqualIgnoreCase(claimStatus, ClaimStatus.ENHANCEMENT_REJECTED.name())) {
//					return "Enhancement Rejected";
//				} else if (CommonUtils.isEqualIgnoreCase(claimStatus, ClaimStatus.FINAL_BILLS_SUBMITTED.name())) {
//					return "Final Bill Received";
//				} else if (CommonUtils.isEqualIgnoreCase(claimStatus, ClaimStatus.FINAL_BILLS_UNDER_QUERY.name())) {
//					return "Final Bills Under Query";
//				} else if (CommonUtils.isEqualIgnoreCase(claimStatus, ClaimStatus.FINAL_BILLS_QUERY_RESPONDED.name())) {
//					return "Final Bills Query Response received";
//				} else if (CommonUtils.isEqualIgnoreCase(claimStatus, ClaimStatus.FINAL_BILLS_UNDER_REVIEW.name())) {
//					return "Final Bill Received";
//				} else if (CommonUtils.isEqualIgnoreCase(claimStatus, ClaimStatus.CLAIM_APPROVED.name())) {
//					return "Claim Approved";
//				} else if (CommonUtils.isEqualIgnoreCase(claimStatus, ClaimStatus.SCANNED_FILE_RECEIVED.name())) {
//					return "Claim Under Process";
//				} else if (CommonUtils.isEqualIgnoreCase(claimStatus, ClaimStatus.PHYSICAL_FILE_PENDING.name())) {
//					return "Claim Under Process";
//				} else if (CommonUtils.isEqualIgnoreCase(claimStatus, ClaimStatus.READY_TO_PAY.name())) {
//					return "Claim Under Process";
//				} else if (CommonUtils.isEqualIgnoreCase(claimStatus, ClaimStatus.FILE_SUBMITTED_UNDER_QUERY.name())) {
//					return "File Submitted Under Query";
//				} else if (CommonUtils.isEqualIgnoreCase(claimStatus,
//						ClaimStatus.FILE_SUBMITTED_QUERY_RESPONDED.name())) {
//					return "File Submitted Query Responded";
//				} else if (CommonUtils.isEqualIgnoreCase(claimStatus, ClaimStatus.UPLOADED_FOR_PAYMENT.name())) {
//					return "Claim Under Process";
//				} else if (CommonUtils.isEqualIgnoreCase(claimStatus, ClaimStatus.PARTIALLY_SETTLED.name())) {
//					return "Partially Settled";
//				} else if (CommonUtils.isEqualIgnoreCase(claimStatus, ClaimStatus.SETTLED.name())) {
//					return "Fully Settled";
//				} else if (CommonUtils.isEqualIgnoreCase(claimStatus, "PAYMENT_BOUNCE")) {
//					return "We are connecting with the hospital to collect correct account details";
//				} else if (CommonUtils.isEqualIgnoreCase(claimStatus, ClaimStatus.CLAIM_REJECTED.name())) {
//					if (claimSubReason != null && claimSubReason.equalsIgnoreCase("Cancel Claim")) {
//						return "This claim has been cancelled, please contact your Relationship Manager for more information.";
//					} else {
//						return "Claim Denied";
//					}
//				} else if (CommonUtils.isEqualIgnoreCase(claimStatus, ClaimStatus.MEMBER_UNENDORSED.name())) {
//					return "Pre Auth Received";
//				}
//			} else {
//				if (CommonUtils.isEqualIgnoreCase(claimStatus, "CLAIM_REGISTERED")) {
//					return "Claim Received";
//				} else if (CommonUtils.isEqualIgnoreCase(claimStatus, "IN_PROCESSING")) {
//					return "Claim Under Process";
//				} else if (CommonUtils.isEqualIgnoreCase(claimStatus, ClaimStatus.PHYSICAL_FILE_PENDING.name())) {
//					return "Claim Under Process";
//				} else if (CommonUtils.isEqualIgnoreCase(claimStatus, "ON_HOLD")) {
//					if (claimSubReason != null && claimSubReason.equalsIgnoreCase("Provider Registration")) {
//						return "Claim Under Process";
//					} else if (claimSubReason != null && (claimSubReason.equalsIgnoreCase("Insurer Approval Required")
//							|| claimSubReason.equalsIgnoreCase("Rejection Review")
//							|| claimSubReason.equalsIgnoreCase("Send To Insurer"))) {
//						return "Claim referred to Insurer";
//					} else if (claimSubReason != null && claimSubReason.equalsIgnoreCase("Document Recovery")) {
//						return "Document Deficiency";
//					} else if (claimSubReason != null && claimSubReason.equalsIgnoreCase("Partial Document Received")) {
//						return "Document Deficiency Partially Satisfied";
//					} else if (claimSubReason != null
//							&& claimSubReason.equalsIgnoreCase("Main Claim Approval Pending")) {
//						return "Main claim documents pending from Hospital";
//					} else if (isDataEntry != null && isDataEntry) {
//						return "Claim Under Process";
//					} else {
//						return "Claim Under Process";
//					}
//				} else if (CommonUtils.isEqualIgnoreCase(claimStatus, ClaimStatus.MEMBER_UNENDORSED.name())) {
//					return "Claim Under Process";
//				} else if (CommonUtils.isEqualIgnoreCase(claimStatus, ClaimStatus.READY_TO_PAY.name())) {
//					return "Preparing for Payment";
//				} else if (CommonUtils.isEqualIgnoreCase(claimStatus, ClaimStatus.UPLOADED_FOR_PAYMENT.name())) {
//					return "Payment Request Sent to Insurer";
//				} else if (CommonUtils.isEqualIgnoreCase(claimStatus, ClaimStatus.PARTIALLY_SETTLED.name())) {
//					return "Partially Settled";
//				} else if (CommonUtils.isEqualIgnoreCase(claimStatus, ClaimStatus.SETTLED.name())) {
//					return "Fully Settled";
//				} else if (CommonUtils.isEqualIgnoreCase(claimStatus, "PAYMENT_BOUNCE")) {
//					return "There is a discrepency in account details submitted, please contact with your Relationship Manager for details";
//				} else if (CommonUtils.isEqualIgnoreCase(claimStatus, ClaimStatus.CLAIM_REJECTED.name())) {
//					if (claimSubReason != null && claimSubReason.equalsIgnoreCase("Cancel Claim")) {
//						return "This claim has been cancelled, please contact your Relationship Manager for more information.";
//					} else if (claimSubReason != null && claimSubReason.equalsIgnoreCase("Dr Failure")) {
//						return "Please submit the definicient documents with in 7 days ";
//					} else {
//						return "Claim Denied";
//					}
//				}
//			}
//			return claimStatus;
//		} catch (Exception e) {
//			e.printStackTrace();
//			return null;
//		}
//	}

	public static Date convertddMMYYYYToTruecoverDate(String d1) {
		try {
			String date1 = "";
			String[] split1 = d1.split("/");
			for (int i = split1.length - 1; i >= 0; i--) {
				date1 += split1[i];
				if (i != 0) {
					date1 += "-";
				}
			}

			date1 += "T00:00:00.000Z";

			Date startDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").parse(date1);

			return startDate;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

//	public static String getActionstatusValueFromClaimStatus(String claimststatus) {
//		if (claimststatus.equalsIgnoreCase(ClaimStatus.PRE_AUTH_UNDER_QUERY.name())
//				|| claimststatus.equalsIgnoreCase(ClaimStatus.ENHANCEMENT_UNDER_QUERY.name())) {
//			return "30";
//		} else if (claimststatus.equalsIgnoreCase(ClaimStatus.PRE_APPROVAL_RECEIVED.name())) {
//			return "31";
//		} else if (claimststatus.equalsIgnoreCase(ClaimStatus.ENHANCEMENT_APPROVED.name())) {
//			return "33";
//		} else if (claimststatus.equalsIgnoreCase(ClaimStatus.ENHANCEMENT_REJECTED.name())) {
//			return "35";
//		} else if (claimststatus.equalsIgnoreCase(ClaimStatus.CLAIM_APPROVED.name())) {
//			return "3";
//		} else if (claimststatus.equalsIgnoreCase(ClaimStatus.FINAL_BILLS_UNDER_QUERY.name())) {
//			return "4";
//		} else if (claimststatus.equalsIgnoreCase(ClaimStatus.READY_TO_PAY.name())) {
//			return "10";
//		} else if (claimststatus.equalsIgnoreCase(ClaimStatus.FILE_SUBMITTED_UNDER_QUERY.name())) {
//			return "11";
//		} else if (claimststatus.equalsIgnoreCase(ClaimStatus.SETTLED.name())) {
//			return "20";
//		} else {
//			return "0";
//		}
//	}

	public static String getDescriptionByActionStatusCode(String value) {
		switch (value) {
		case "30":
			return "Preauthorization - Need More Information";
		case "31":
			return "Preauthorization - Approved";
		case "32":
			return "Preauthorization - Denied";
		case "33":
			return "Preauthorization - Enhanced";
		case "34":
			return "Preauthorization - Cancelled";
		case "35":
			return "Preauthorization - Enhancement – Denied";
		case "3":
			return "Pre Discharge - Approved";
		case "4":
			return "Pre Discharge - Need More Information";
		case "5":
			return "Pre Discharge - Rejected";
		case "8":
			return "Pre Discharge – Cancelled";
		case "10":
			return "Claims - Approved";
		case "11":
			return "Claims - Need More Information";
		case "12":
			return "Claims - Rejected";
		case "15":
			return "Claims - Cancelled";
		case "20":
			return "Claims - Paid";

		default:
			return "";
		}

	}

	public static Integer getStageIdByClaimStatus(String value) {
		switch (value) {
		case "PRE_AUTH_SUBMITTED":
			return 34;
		case "PRE_AUTH_UNDER_REVIEW":
			return 35;
		case "PRE_AUTH_UNDER_QUERY":
			return 36;
		case "PRE_APPROVAL_RECEIVED":
			return 37;
		case "PRE_AUTH_QUERY_RESPONDED":
			return 38;
		case "ENHANCEMENT_SUBMITTED":
			return 39;
		case "ENHANCEMENT_UNDER_REVIEW":
			return 40;
		case "ENHANCEMENT_UNDER_QUERY":
			return 41;
		case "ENHANCEMENT_APPROVED":
			return 42;
		case "ENHANCEMENT_REJECTED":
			return 43;
		case "ENHANCEMENT_QUERY_RESPONDED":
			return 44;
		case "FINAL_BILLS_SUBMITTED":
			return 45;
		case "FINAL_BILLS_UNDER_REVIEW":
			return 46;
		case "FINAL_BILLS_UNDER_QUERY":
			return 47;
		case "FINAL_BILLS_QUERY_RESPONDED":
			return 48;
		case "CLAIM_APPROVED":
			return 49;
		case "CLAIM_REJECTED":
			return 50;
		case "CANCEL_FROM_HOSPITAL":
			return 51;
		case "MEMBER_UNENDORSED":
			return 52;

		default:
			return 0;
		}

	}

//	public static String getClaimbookStatusFromCurrentStatus(String claimStatus) {
//
//		if (claimStatus.equalsIgnoreCase(ClaimStatus.PRE_AUTH_SUBMITTED.name())
//				|| claimStatus.equalsIgnoreCase(ClaimStatus.PRE_AUTH_UNDER_REVIEW.name())
//				|| claimStatus.equalsIgnoreCase(ClaimStatus.ENHANCEMENT_SUBMITTED.name())
//				|| claimStatus.equalsIgnoreCase(ClaimStatus.ENHANCEMENT_UNDER_REVIEW.name())
//				|| claimStatus.equalsIgnoreCase(ClaimStatus.FINAL_BILLS_SUBMITTED.name())
//				|| claimStatus.equalsIgnoreCase(ClaimStatus.FINAL_BILLS_UNDER_REVIEW.name())
//				|| claimStatus.equalsIgnoreCase(ClaimStatus.SCANNED_FILE_RECEIVED.name())
//				|| claimStatus.equalsIgnoreCase(ClaimStatus.FILE_SUBMITTED_UNDER_REVIEW.name())) {
//			return "Pre-Auth Submitted";
//		} else if (claimStatus.equalsIgnoreCase(ClaimStatus.PRE_AUTH_UNDER_QUERY.name())
//				|| claimStatus.equalsIgnoreCase(ClaimStatus.PRE_AUTH_QUERY_RESPONDED.name())
//				|| claimStatus.equalsIgnoreCase(ClaimStatus.ENHANCEMENT_UNDER_QUERY.name())
//				|| claimStatus.equalsIgnoreCase(ClaimStatus.ENHANCEMENT_QUERY_RESPONDED.name())
//				|| claimStatus.equalsIgnoreCase(ClaimStatus.FINAL_BILLS_UNDER_QUERY.name())
//				|| claimStatus.equalsIgnoreCase(ClaimStatus.FINAL_BILLS_QUERY_RESPONDED.name())
//				|| claimStatus.equalsIgnoreCase(ClaimStatus.FILE_SUBMITTED_UNDER_QUERY.name())
//				|| claimStatus.equalsIgnoreCase(ClaimStatus.FILE_SUBMITTED_QUERY_RESPONDED.name())) {
//			return "Pre Auth Query";
//		} else if (claimStatus.equalsIgnoreCase(ClaimStatus.PRE_APPROVAL_RECEIVED.name())
//				|| claimStatus.equalsIgnoreCase(ClaimStatus.ENHANCEMENT_APPROVED.name())
//				|| claimStatus.equalsIgnoreCase(ClaimStatus.CLAIM_APPROVED.name())) {
//			return "Pre-Auth Approved";
//		} else if (claimStatus.equalsIgnoreCase(ClaimStatus.ENHANCEMENT_REJECTED.name())
//				|| claimStatus.equalsIgnoreCase(ClaimStatus.CLAIM_REJECTED.name())) {
//			return "Pre Auth Denied";
//		} else if (claimStatus.equalsIgnoreCase(ClaimStatus.CANCEL_FROM_HOSPITAL.name())) {
//			return "Cancelled";
//		} else {
//			return "";
//		}
//	}

	public static Date convertString2Date4(String value) {

		try {
			return new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(value);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String convertNumberToCommaSeparatedWithoutRs(long value) {
		if (CommonUtils.isEmpty(value)) {
			return null;
		}
		Format format = com.ibm.icu.text.NumberFormat.getCurrencyInstance(new Locale("en", "in"));
		String y = format.format(value);
		String z = org.apache.commons.lang3.StringUtils.replace(y, ".00", "");
		z = org.apache.commons.lang3.StringUtils.replace(z, "Rs. ", "");
		z = org.apache.commons.lang3.StringUtils.replace(z, "₹ ", "");
		z = org.apache.commons.lang3.StringUtils.replace(z, "₹ ", "");
		z = org.apache.commons.lang3.StringUtils.replace(z, "₹", "");
		return z;
	}

	public static Date convertString2Date5(String value) {

		try {
			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(value);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Double setValueOrZeroInDouble(String value) {
		if (value != null && value.trim().length() > 0) {
			return Double.parseDouble(value);
		}
		return 0D;
	}

	public static Double setValueOrZeroInDoubleValue(Double value) {
		if (value != null) {
			return value;
		}
		return 0D;
	}

	public static Date getMeDaysBack(int days) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -days);

		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);

		Date d = cal.getTime();
		return d;
	}

	public static String getMaskEmail(String email) {
		if (isEmpty(email)) {
			return null;
		}
		String[] accountNumberChars = email.split("");
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < accountNumberChars.length; i++) {
			if (i < 5) {
				builder.append(accountNumberChars[i]);
			} else {
				builder.append("*");
			}
		}
		return builder.toString();
	}

	public static String convertDateToMMMDDYYYYString(Date date) {
		try {
			if (isNotNull(date)) {
				DateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy");
				String strDate = dateFormat.format(date);
				return strDate;
			}
			return "";
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String mapOpdGroupTypeToValue(String opdGroupType) {
		if (isEqualIgnoreCase(opdGroupType, "CONSULTATION_OPD")) {
			return "Consultation";
		} else if (isEqualIgnoreCase(opdGroupType, "DENTAL_OPD")) {
			return "Dental";
		} else if (isEqualIgnoreCase(opdGroupType, "AYUSH_OPD")) {
			return "Ayush";
		} else if (isEqualIgnoreCase(opdGroupType, "PHYSIOTHERAPY")) {
			return "Physiotherapy";
		} else if (isEqualIgnoreCase(opdGroupType, "MENTAL_WELLNESS")) {
			return "Health and Wellness";
		} else if (isEqualIgnoreCase(opdGroupType, "COVID_VACCINE")) {
			return "COVID Vaccine";
		} else if (isEqualIgnoreCase(opdGroupType, "COVID_HOMECARE")) {
			return "COVID Home Care";
		} else if (isEqualIgnoreCase(opdGroupType, "VISION_OPD")) {
			return "Vision Care";
		} else if (isEqualIgnoreCase(opdGroupType, "INVESTIGATION")) {
			return "Investigation";
		} else if (isEqualIgnoreCase(opdGroupType, "PROCEDURES")) {
			return "Procedure";
		} else if (isEqualIgnoreCase(opdGroupType, "PHARMACY")) {
			return "Pharmacy";
		}
		return null;
	}

	public static String getMaskMobile(String bankAccount) {
		if (CommonUtils.isEmpty(bankAccount)) {
			return null;
		}
		String[] accountNumberChars = bankAccount.split("");
		int indexToStartNotMask = (accountNumberChars.length - 4);
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < accountNumberChars.length; i++) {
			if (i < indexToStartNotMask) {
				builder.append("*");
			} else {
				builder.append(accountNumberChars[i]);
			}
		}
		return builder.toString();
	}
	
	public static boolean isAlphabetsOnlyDotAndSpace(String str) {
		String regex = "^[a-zA-Z]+(?:[ .][a-zA-Z]+)*$";
		Pattern p = Pattern.compile(regex);
		if (isEmpty(str)) {
			return false;
		}
		Matcher m = p.matcher(str);
		return m.matches();
	}
	
	public static boolean checkIsDocTypeAllowed(String extension) {
		String[] allowedTypes = { "jpg", "jpeg", "png", "tiff", "pdf", "xls", "xlsx", "xlsb", "doc", "docs", "csv",
				"zip" };
		for (int i = 0; i < allowedTypes.length; i++) {
			if (extension.equalsIgnoreCase(allowedTypes[i])) {
				return true;
			}
		}
		return false;
	}
}
