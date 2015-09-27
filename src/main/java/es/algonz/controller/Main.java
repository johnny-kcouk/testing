package es.algonz.controller;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Main {

	private static final Log LOGGER = LogFactory.getLog(Main.class);

	public static void main(String[] args) throws NoSuchAlgorithmException {
//		String passwordToHash = "";		
//		
//		String securePassword = get_SHA_1_SecurePassword(passwordToHash);
//		System.out.println(securePassword);
//
//		securePassword = get_SHA_256_SecurePassword(passwordToHash);
//		System.out.println(securePassword);
//
//		securePassword = get_SHA_384_SecurePassword(passwordToHash);
//		System.out.println(securePassword);
//
//		securePassword = get_SHA_512_SecurePassword(passwordToHash);
//		System.out.println(securePassword);
		
//		int[] test = { -15, 3, -4, 5, 1, -6, 2, 15 };
//		int[] test = { -1, 1, -1 };
//		int[] test = { -1, 3, -4, 5, 1, -6, 2, 1 };
//		int[] test = {  };
		int[] test = { -1, -3, -4, 5, 11, -6, 2, 6 };
//		int[] test = { -1, 3, -4, 5, 1, -6, 2, 1 };
//		int[] test = { -1, 3, -4, 5, 1, -6, 2, 1 };
//		int[] test = { -1, 3, -4, 5, 1, -6, 2, 1 };
//		int[] test = { -1, 3, -4, 5, 1, -6, 2, 1 };
//		int[] test = { -1, 3, -4, 5, 1, -6, 2, 1 };
//		int[] test = { -1, 3, -4, 5, 1, -6, 2, 1 };
//		int[] test = { -1, 3, -4, 5, 1, -6, 2, 1 };
//		int[] test = { -1, 3, -4, 5, 1, -6, 2, 1 };
//		int[] test = { -1, 3, -4, 5, 1, -6, 2, 1 };
//		int[] test = { -1, 3, -4, 5, 1, -6, 2, 1 };
//		int[] test = { -1, 3, -4, 5, 1, -6, 2, 1 };
		System.out.println(solution(test));
		
	}

	private static int  solution(int[] A)
    {
		if(A == null || A.length == 0)
			return -1;
		
		if (A.length == 1)
			return 0;

		int rightIndex = A.length-1;
		int leftIndex = 0;
		int leftSum = A[0]; 
		int rightSum = A[rightIndex] ;
		
		while (leftIndex <= rightIndex && leftIndex <  A.length-1 && rightIndex > 0){
		
			if(leftSum == rightSum && (rightIndex-leftIndex == 2 || rightIndex == leftIndex)){
				return leftIndex + 1;
			}
			
			if(leftSum < rightSum){
				leftIndex++;
				leftSum += A[leftIndex];
			}else {
				rightIndex--;
				rightSum += A[rightIndex];
			}
			
		}
		
		return -1;
    }
	
	
	private static String get_SecurePassword(String passwordToHash, String instance) {
		String generatedPassword = null;
		try {
			 
	        MessageDigest md = MessageDigest.getInstance(instance);
	        md.update(passwordToHash.getBytes());
	 
	        byte byteData[] = md.digest();
	 
	        //convert the byte to hex format method 1
	        StringBuffer sb = new StringBuffer();
	        for (int i = 0; i < byteData.length; i++) {
	         sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
	        }

			generatedPassword = sb.toString();
		} catch (NoSuchAlgorithmException e) {
			LOGGER.error(e.getMessage(),e);
		}
		return generatedPassword;
	}

	private static String get_SHA_1_SecurePassword(String passwordToHash) {
		return get_SecurePassword(passwordToHash, "SHA-1");
	}

	
	private static String get_SHA_256_SecurePassword(String passwordToHash) {
		return get_SecurePassword(passwordToHash, "SHA-256");
	}

	private static String get_SHA_384_SecurePassword(String passwordToHash) {
		return get_SecurePassword(passwordToHash, "SHA-384");
	}

	private static String get_SHA_512_SecurePassword(String passwordToHash) {

		return get_SecurePassword(passwordToHash, "SHA-512");
	}

}
// public class Main
// {
//
// public static JasperDesign jasperDesign;
// public static JasperPrint jasperPrint;
// public static JasperReport jasperReport;
// public static String reportTemplateUrl = "comunidad.jrxml";
//
// public static String removeHTML(String htmlString)
// {
//
//
// // Remove Carriage return from java String
// String noHTMLString = htmlString.replaceAll("<br/>", "\n");
//
//
// // Remove HTML tag from java String
// noHTMLString = noHTMLString.replaceAll("<.*?>", "");
//
// return noHTMLString;
// }
//
// public static void main(String[] args) {
//
// String strHTML= "<html>"+
// "<head>"+
// "<title>Convert HTML to Text String</title>"+
// "</head>"+
// "<br/>"
// + "<body>"+
// "This is HTML String of java's source code  "+
// "</body>"+
// "</html>";
//
// String stringWithoutHTML=removeHTML(strHTML);
//
// System.out.println(stringWithoutHTML);
// }
//
//
// // public static void main(String[] args) throws IOException
// // {
// // try
// // {
// // BufferedInputStream resourceAsStream = ((BufferedInputStream)
// Thread.currentThread().getContextClassLoader()
// // .getResourceAsStream(reportTemplateUrl));
// // //get report file and then load into jasperDesign
// // jasperDesign = JRXmlLoader.load(resourceAsStream);
// // //compile the jasperDesign
// // jasperReport = JasperCompileManager.compileReport(jasperDesign);
// // //fill the ready report with data and parameter
// // jasperPrint = JasperFillManager.fillReport(jasperReport, null,
// // new JRBeanCollectionDataSource(
// // findReportData()));
// // //view the report using JasperViewer
// // JasperViewer.viewReport(jasperPrint);
// // }
// // catch (JRException e)
// // {
// // LOGGER.error(e.getMessage(),e);
// // }
// // }
//
// private static Collection<ComunidadVO> findReportData()
// {
// //declare a list of object
// List<ComunidadVO> data = new LinkedList<ComunidadVO>();
// ComunidadVO c1 = new ComunidadVO();
// c1.setTeNombre("comouonidad prueba");
// data.add(c1);
// ComunidadVO c2 = new ComunidadVO();
// c2.setTeNombre("ma asdio asd");
// data.add(c2);
// return data;
// }
// }