package com.sena.disocc.app.utilities;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.sena.disocc.app.modelo.Respuesta;
import com.sena.disocc.app.modelo.Usuario;

public class ExportarRespuestas {
	
	private XSSFWorkbook workbook;
	private XSSFSheet sheet;
	List<Respuesta> respeustaList;
	
	public ExportarRespuestas (List<Respuesta> listaRespuesta) {
		
		this.respeustaList = listaRespuesta;
		workbook = new XSSFWorkbook();
	}
	
	private void writeHeaderLine() {
		sheet = workbook.createSheet("ListaUsuario");

		Row row = sheet.createRow(0);

		CellStyle style = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setBold(true);
		font.setFontHeight(16);
		style.setFont(font);

		createCell(row, 0, "ID", style);
		createCell(row, 1, "DESCRIPCION", style);
		createCell(row, 2, "PETICION", style);
		createCell(row, 3, "USUARIO", style);
		createCell(row, 4, "TIPO DE PETICION", style);



	}
	

	private void createCell(Row row, int columnCount, Object value, CellStyle style) {
		sheet.autoSizeColumn(columnCount);
		Cell cell = row.createCell(columnCount);
		if (value instanceof Integer) {
			cell.setCellValue((Integer) value);
		} else if (value instanceof Boolean) {
			cell.setCellValue((Boolean) value);
		} else {
			cell.setCellValue((String) value);
		}
		cell.setCellStyle(style);
	}

	private void writeDataLines() {
		int rowCount = 1;

		CellStyle style = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setFontHeight(14);
		style.setFont(font);

		for (Respuesta usu : this.respeustaList) {
			Row row = sheet.createRow(rowCount++);
			int columnCount = 0;
			createCell(row, columnCount++, usu.getIdRespuesta(), style);
			createCell(row, columnCount++, usu.getDescripcion(), style);
			createCell(row, columnCount++, usu.getPeticione().getDescripcion(), style);
			createCell(row, columnCount++, usu.getUsuario().getCorreo(), style);
			createCell(row, columnCount++, usu.getPeticione().getTipoPeticione().getNombre(), style);


		}
	}

	public void export(HttpServletResponse response) throws IOException {
		writeHeaderLine();
		writeDataLines();

		ServletOutputStream outputStream = response.getOutputStream();
		workbook.write(outputStream);
		workbook.close();

		outputStream.close();

	}

}
