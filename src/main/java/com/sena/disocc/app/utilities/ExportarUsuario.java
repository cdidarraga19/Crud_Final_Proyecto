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

import com.sena.disocc.app.modelo.Usuario;

public class ExportarUsuario {

	private XSSFWorkbook workbook;
	private XSSFSheet sheet;
	private List<Usuario> usuarioList;

	public ExportarUsuario(List<Usuario> listaUsuario) {

		this.usuarioList = listaUsuario;
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
		createCell(row, 1, "Contraseña", style);
		createCell(row, 2, "Correo", style);
		createCell(row, 3, "Direccion", style);
		createCell(row, 4, "NUMERO DE DOCUMENTO", style);
		createCell(row, 5, "PRIMER APELLIDO", style);
		createCell(row, 6, "PRIMER NOMBRE", style);
		createCell(row, 7, "SEGUNDO APELLIDO", style);
		createCell(row, 8, "SEGUNDO NOMBRE", style);
		createCell(row, 9, "TELEFONO", style);
		createCell(row, 10, "ESTADO", style);
		createCell(row, 11, "ROL", style);
		createCell(row, 12, "TIPO DE DOCUMENTO", style);

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

		for (Usuario usu : this.usuarioList) {
			Row row = sheet.createRow(rowCount++);
			int columnCount = 0;
			createCell(row, columnCount++, usu.getIdUsuario(), style);
			createCell(row, columnCount++, usu.getContrase_ďa(), style);
			createCell(row, columnCount++, usu.getCorreo(), style);
			createCell(row, columnCount++, usu.getDireccion(), style);
			createCell(row, columnCount++, usu.getNumeroDocumento(), style);
			createCell(row, columnCount++, usu.getPrimerApellido(), style);
			createCell(row, columnCount++, usu.getPrimerNombre(), style);
			createCell(row, columnCount++, usu.getSegundoApellido(), style);
			createCell(row, columnCount++, usu.getSegundoNombre(), style);
			createCell(row, columnCount++, usu.getTelefono(), style);
			createCell(row, columnCount++, usu.getEstado().getNombre(), style);
			createCell(row, columnCount++, usu.getRole().getNomRol(), style);
			createCell(row, columnCount++, usu.getTiposDocumento().getNombreDocumento(), style);
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
