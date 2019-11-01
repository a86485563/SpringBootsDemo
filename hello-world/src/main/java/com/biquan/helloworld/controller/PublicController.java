package com.biquan.helloworld.controller;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.biquan.helloworld.dao.AuthInfo;

import net.sf.jmimemagic.Magic;
import net.sf.jmimemagic.MagicMatch;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/api/public")
public class PublicController {
	private static final Logger logger = LogManager.getLogger(PublicController.class);
	private static final int BUFFER_SIZE = 4096;

	public static final String BOUNDARYSTR = "aifudao7816510d1hq";
	public static final String BOUNDARY = "--" + BOUNDARYSTR + "\r\n";

//	@CrossOrigin("http://localhost:8080")
//	@PostMapping(value="/HelloPublic",consumes=MediaType.APPLICATION_JSON_VALUE)
////	@ResponseBody
//    public String HelloPublic(@RequestBody String name, HttpServletResponse response) {
//		System.out.println(name);  // 印出"matt"
//		
//
//		return"9090 HelloPublic";
//	}
	@CrossOrigin("http://localhost:8080")
	@PostMapping(value = "post/HelloPublic", consumes = MediaType.APPLICATION_JSON_VALUE)
	// @ResponseBody
	public String HelloPublic(@RequestBody AuthInfo authInfo, HttpServletRequest request) {
		System.out.println("log4j2 is enable : " + logger.isInfoEnabled());

		logger.info(authInfo.toString()); // 印出"matt"
		logger.info(request.getHeader("Authorization")); // 印出"matt"
		int intTest = 0;

		changeInt(intTest);
		return authInfo.toString();
	}

	@PostMapping(value = "post/Form")
	@ResponseBody
	public String handleFileUpload(HttpServletRequest request) throws Exception {
		MultipartHttpServletRequest params = ((MultipartHttpServletRequest) request);
		String name = params.getParameter("name");
		String id = params.getParameter("id");
		// request.getParameterMap();

		logger.info("name :" + name);
		logger.info("id :" + id);

		// 確定request stream 有資料
		String bs = IOUtils.toString(request.getInputStream(), "UTF-8");
		logger.info("bs :" + bs);

		List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");

		// MultipartFile file = files.get(0);

		logger.info("file length: " + files.get(0).getInputStream().available());
		String modifyUrl = request.getRequestURL().toString().replace("Form", "getForm");
		URL url = new URL(modifyUrl);
		IntHolder intHolder = new IntHolder(0);
		HttpHeaders headers = new HttpHeaders();
		doHttpUrlConnectionReturnByteArrayReleaseTest(params.getParameterMap(), params.getMultiFileMap(), url,
				request.getContentType(), HttpMethod.POST.toString(), intHolder, headers);
		return "upload successful";
	}

	@PostMapping(value = "post/RestForm",produces = MediaType.IMAGE_GIF_VALUE)
	@ResponseBody
	public ResponseEntity<byte[]> RestFileUpload(HttpServletRequest request) throws Exception {
		//MultipartHttpServletRequest params = ((MultipartHttpServletRequest) request);
		//String name = params.getParameter("name");
		//String id = params.getParameter("id");
		// request.getParameterMap();

		//logger.info("name :" + name);
		//logger.info("id :" + id);

		// 確定request stream 有資料
	//	String bs = IOUtils.toString(request.getInputStream(), "UTF-8");
		//logger.info("bs :" + bs);

//		List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");

		// MultipartFile file = files.get(0);

		//logger.info("file length: " + files.get(0).getInputStream().available());
		//String modifyUrl = request.getRequestURL().toString().replace("Form", "getForm");
		//URL url = new URL(modifyUrl);
		//Create FileSystemResource to read img
		FileSystemResource fileSystemResource = new FileSystemResource(new File("C:\\Users\\BQ_Yen\\Desktop\\send.gif"));
		
		// use RestTemplate
		//設置要傳送的body
		MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
		body.add("name", "nameTest");
		body.add("id", "9527");
		body.add("file", fileSystemResource);
		
		// 設置請求頭
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.MULTIPART_FORM_DATA);

		HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

		String serverUrl = "http://localhost:9090/api/public/post/getForm/";

		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<byte[]> response = restTemplate.postForEntity(serverUrl, requestEntity, byte[].class);

		return response;
	}

	private void changeInt(int integer) {
		// TODO Auto-generated method stub

		integer = 10;
	}

	@CrossOrigin("http://localhost:8080")
	@GetMapping(value = "get/HelloPublic")
	// @ResponseBody
	public String GetHelloPublic(@RequestParam Map<String, String> allParams) {
		System.out.println("entrySet() : " + allParams.entrySet());
		for (String var : allParams.values()) {
			System.out.println("value : " + var);

		}

		return "Parameters are " + allParams.entrySet();
	}

	@PostMapping("/getAuthen")
	public String getToken(HttpSession session, HttpServletResponse response) {

		Random random = new Random();

		Boolean bAuthen = true;
		System.out.println(bAuthen);

		return "Response from 9090: \n" + bAuthen.toString();
	}

	@PostMapping(value = "post/getForm",produces = MediaType.IMAGE_GIF_VALUE)
	@ResponseBody
	public ResponseEntity<byte[]> getFormData(HttpServletRequest request) throws IOException {
		MultipartHttpServletRequest params = ((MultipartHttpServletRequest) request);
//		
		String name = params.getParameter("name");
		String id = params.getParameter("id");
//		
		logger.info("name:" + name);
		logger.info("id:" + id);
		List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");

//		
		// String bs = IOUtils.toString(request.getInputStream(), "UTF-8");
		// logger.info("bs :"+bs);
		//final HttpHeaders headers = new HttpHeaders();
		//headers.setContentType(MediaType.IMAGE_GIF);
		//logger.info("show img");
		logger.info("receive Image bytes: " + files.get(0).getInputStream().available());
		// ByteArrayInputStream input_stream = new
		// ByteArrayInputStream(files.get(0).getBytes());
		// BufferedImage final_buffered_image = ImageIO.read(input_stream);
		// ImageIO.write(final_buffered_image, "gif", new File("gif"));

		InputStream inFile = files.get(0).getInputStream();
		 
		//byte [] byTemp = new byte[1024];
//		byte[] byResponse = new byte[in.available()];
//		
//		int bytes = 0;
//		byte[] bufferOut = new byte[1024];
//		while ((bytes = in.read(bufferOut)) != -1) {
//			int x =0;
//			System.arraycopy(bufferOut, 0, byResponse, bufferOut.length*x, bufferOut.length);
//		}
		
		return ResponseEntity.ok().body(IOUtils.toByteArray(inFile));
	}

	private byte[] doHttpUrlConnectionReturnByteArrayRelease(InputStream outIs, URL url, String strContentType,

			String Method, IntHolder httpStatus, HttpHeaders headers) throws Exception {

		logger.info("=============Start HttpConnection=============");

		String bs = IOUtils.toString(outIs, "UTF-8");
		logger.info("====data in connect method====== ");
		logger.info(bs);
		///

		byte[] responseFromServer = null;

		// String result = "";

		try {
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true); // post有寫出資料所以要設為true
			conn.setInstanceFollowRedirects(false); // 不用自動重新導向所以設為false
			conn.setRequestMethod(Method); // POST or get
			conn.setRequestProperty("Connection", "Keep-Alive");
			if (strContentType != null)
				conn.setRequestProperty("Content-Type", strContentType);
			// 不接受caches
			conn.setUseCaches(false);
			// 設定time out 5s
			conn.setConnectTimeout(5000);
			conn.setReadTimeout(5000);
			// 寫出 post data
			// equal try open and finally close , when leave try close itself
			// if (jsonInputString != null) {
			// try (OutputStream os = conn.getOutputStream()) {
			// byte[] input = jsonInputString.getBytes("utf-8");
			// os.write(input, 0, input.length);
			// }
			// }
//			 DataOutputStream out = new DataOutputStream(conn
//                     .getOutputStream());
//             StringBuilder request = new StringBuilder();
//             for (String key : map.keySet()) {
//                 request.append(key + "=" + URLEncoder.encode(map.get(key), "UTF-8") + "&");
//             }              
//             out.writeBytes(request.toString());//写入请求参数
//             out.flush();
//             out.close();
			// send end clean stream

			// 得到response狀態

			// HTTP/1.0 200 OK

			// HTTP/1.0 401 Unauthorized

			int code = conn.getResponseCode();

			httpStatus.value = code;

			logger.info("========Srever Response code: " + code + " =========");

			// equal try open and finally close , when leave try close itself

			StringBuilder responseFrom9090 = null;

			if (HttpStatus.OK.value() == code) {

				logger.info("Server Response code is 200");

				// InputStream Is = new InputStreamReader(conn.getInputStream());

				responseFromServer = IOUtils.toByteArray(conn.getInputStream());

				headers.set("Content-Type", conn.getHeaderField("Content-Type"));

			}

			logger.info("=============Success finish HttpUrlconnect=============");

			// result = (responseFrom9090 == null) ? "" : responseFrom9090.toString();

			return responseFromServer;

		} catch (MalformedURLException e) {

			e.printStackTrace();
			return null;

		} catch (IOException e) {

			e.printStackTrace();

			return null;
		}

	}

	private byte[] doHttpUrlConnectionReturnByteArrayReleaseTest(Map<String, String[]> textMap,
			Map<String, List<MultipartFile>> fileMap, URL url, String strContentType,

			String Method, IntHolder httpStatus, HttpHeaders headers) throws Exception {

		logger.info("=============Start HttpConnection=============");

		logger.info("====data in connect method====== ");
		// logger.info("name=nameTest&id=9527");
		// get boundary
		String BOUNDARY = strContentType.split(";")[1].split("=")[1];
		byte[] responseFromServer = null;

		// String result = "";

		try {
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setDoOutput(true); // post有寫出資料所以要設為true
			conn.setInstanceFollowRedirects(false); // 不用自動重新導向所以設為false
			conn.setRequestMethod(Method); // POST or get

			conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);

			// conn.setRequestProperty("Connection", "Keep-Alive");
//			if (strContentType != null)
//				conn.setRequestProperty("Content-Type", strContentType);
//			// 不接受caches
			conn.setUseCaches(false);
			// 設定time out 5s
			conn.setConnectTimeout(5000);
			conn.setReadTimeout(5000);
			conn.setRequestProperty("Connection", "Keep-Alive");

			OutputStream out = new DataOutputStream(conn.getOutputStream());
			// text
			if (textMap != null) {
				StringBuffer strBuf = new StringBuffer();
				Iterator<Map.Entry<String, String[]>> iter = textMap.entrySet().iterator();
				while (iter.hasNext()) {
					Map.Entry<String, String[]> entry = iter.next();
					String inputName = (String) entry.getKey();
					String inputValue = (String) convertArrayToStringMethod(entry.getValue());
					if (inputValue == null) {
						continue;
					}
					strBuf.append("\r\n").append("--").append(BOUNDARY).append("\r\n");
					strBuf.append("Content-Disposition: form-data; name=\"" + inputName + "\"\r\n\r\n");
					strBuf.append(inputValue);
				}
				out.write(strBuf.toString().getBytes());
			}

			// file
			if (fileMap != null) {
				Iterator<Map.Entry<String, List<MultipartFile>>> iter = fileMap.entrySet().iterator();
				while (iter.hasNext()) {
					Map.Entry<String, List<MultipartFile>> entry = iter.next();
					String inputName = (String) entry.getKey();
					List<MultipartFile> inputValue = (List<MultipartFile>) entry.getValue();
					if (inputValue == null) {
						continue;
					}
					// File file = inputValue.get(0).transferTo();;
					String filename = inputValue.get(0).getName();
					MagicMatch match = Magic.getMagicMatch(inputValue.get(0).getBytes());
					String contentType = match.getMimeType();

					StringBuffer strBuf = new StringBuffer();
					strBuf.append("\r\n").append("--").append(BOUNDARY).append("\r\n");
					strBuf.append("Content-Disposition: form-data; name=\"" + inputName + "\"; filename=\"" + filename
							+ "\"\r\n");
					strBuf.append("Content-Type:" + contentType + "\r\n\r\n");

					out.write(strBuf.toString().getBytes());

					DataInputStream in = new DataInputStream(inputValue.get(0).getInputStream());
					int bytes = 0;
					byte[] bufferOut = new byte[1024];
					while ((bytes = in.read(bufferOut)) != -1) {
						out.write(bufferOut, 0, bytes);
					}
					in.close();
				}
			}

			byte[] endData = ("\r\n--" + BOUNDARY + "--\r\n").getBytes();
			out.write(endData);
			out.flush();
			out.close();
			// send end clean stream

			// 得到response狀態

			// HTTP/1.0 200 OK

			// HTTP/1.0 401 Unauthorized

			int code = conn.getResponseCode();

			httpStatus.value = code;

			logger.info("========Srever Response code: " + code + " =========");

			// equal try open and finally close , when leave try close itself

			StringBuilder responseFrom9090 = null;

			if (HttpStatus.OK.value() == code) {

				logger.info("Server Response code is 200");

				// InputStream Is = new InputStreamReader(conn.getInputStream());

				responseFromServer = IOUtils.toByteArray(conn.getInputStream());

				headers.set("Content-Type", conn.getHeaderField("Content-Type"));

			}

			logger.info("=============Success finish HttpUrlconnect=============");

			// result = (responseFrom9090 == null) ? "" : responseFrom9090.toString();

			return responseFromServer;

		} catch (MalformedURLException e) {

			e.printStackTrace();
			return null;

		} catch (IOException e) {

			e.printStackTrace();

			return null;
		}

	}

	public String convertArrayToStringMethod(String[] strArray) {
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < strArray.length; i++) {
			stringBuilder.append(strArray[i]);
		}
		return stringBuilder.toString();
	}

}

class IntHolder {
	public int value;

	public IntHolder(int value) {
		this.value = value;
	}

	@Override
	public String toString() {

		return String.valueOf(value);
	}

}