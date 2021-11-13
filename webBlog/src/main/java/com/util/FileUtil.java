package com.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

public class FileUtil {

	private static String documentroot = null;

	public static final String contentPass = "/content";

	public static final String description = "/description/";

	public static String getDocumentroot() {
		return documentroot;
	}

	public static void setDocumentroot(String documentroot) {
		FileUtil.documentroot = documentroot;
	}

	public static String getDescriptionMsg(String filePath, String filename) {

		StringBuffer buff = new StringBuffer();
		try (BufferedReader reader = new BufferedReader(new FileReader(new File(documentroot + contentPass + filePath + description + filename)))) {

			String str = null;
			while ((str = reader.readLine()) != null) {
				buff.append(str);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return buff.toString();
	}

	public static void downloadFile(HttpServletResponse response, String filepath, String filename) {

        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition","attachment;filename=\"" + filename + "\"");
        response.setHeader("Cache-Control", "private");

        try (OutputStream out = response.getOutputStream();
        		FileInputStream in = new FileInputStream(getDocumentroot() + contentPass + filepath + filename);) {
            byte[] buff = new byte[1024];
            int len = 0;
            while ((len = in.read(buff, 0, buff.length)) != -1) {
                out.write(buff, 0, len);
            }
            out.flush();
        }catch (Exception e){
            System.err.println(e);
        }

	}

	public static String getHtmlResouce(String htmlpath) {

		StringBuffer buff = new StringBuffer();

		try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(getDocumentroot() + htmlpath + ".html")))) {

			String str = null;
			while ((str = reader.readLine()) != null) {
				buff.append(str);
				buff.append("\r\n");
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return buff.toString();
	}
}
