package com.hhhy.web.servlet;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;

public class ExcelServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    public ExcelServlet() {}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setHeader("Connection", "close");
		response.setHeader("Content-Type", "application/vnd.ms-excel;charset=UTF-8");
		String filename = "会议记录清单.xls";
		filename = encodeFileName(request, filename);
		response.setHeader("Content-Disposition", "attachment;filename=" + filename);
		response.resetBuffer();
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet("会议记录清单");
		//wb.setSheetName(0, "会议记录清单");
		
		String[][] content = {
				{ "流水号", "开始时间", "结束时间", "会议时长(分)", "会议人数", "实际参与人数" },
				{ "1", "2012-03", "2012-05", "45", "5", "3" } };
		
		for(int i=0; i<content.length; i++){
			Row row = sheet.createRow(i);
			for (int j=0; j<content[i].length; j++) {
				row.createCell(j).setCellValue(content[i][j]);
			}
		}
		
		
		OutputStream out = response.getOutputStream();
		wb.write(out);
		out.close();
	}
	
	public  String encodeFileName(HttpServletRequest request,
			String fileName) throws UnsupportedEncodingException {
		String agent = request.getHeader("USER-AGENT");
		if (null != agent && -1 != agent.indexOf("MSIE")) {
			return URLEncoder.encode(fileName, "UTF-8");
		} else if (null != agent && -1 != agent.indexOf("Mozilla")) {
			return "=?UTF-8?B?"
					+ (new String(Base64.encodeBase64(fileName
							.getBytes("UTF-8")))) + "?=";
		} else {
			return fileName;
		}
	}

}
