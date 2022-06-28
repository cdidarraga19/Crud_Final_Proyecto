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

import com.sena.disocc.app.modelo.Producto;
import com.sena.disocc.app.modelo.Usuario;

public class ExportarProducto {
	
	private XSSFWorkbook workbook;
	private XSSFSheet sheet;
	private List<Producto> productoList;
	
	public ExportarProducto(List<Producto> listaProducto) {
		
		this.productoList = listaProducto;
		workbook = new XSSFWorkbook();
	}
	
	private void writeHeaderLine() {
		sheet = workbook.createSheet("ListaProducto");

		Row row = sheet.createRow(0);

		CellStyle style = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setBold(true);
		font.setFontHeight(16);
		style.setFont(font);

		createCell(row, 0, "ID", style);
		createCell(row, 1, "DESCRIPCION", style);
		createCell(row, 2, "FECHA DE VENCIMIENTO", style);
		createCell(row, 3, "NOMBRE", style);
		createCell(row, 4, "PRECIO UNIDAD", style);
		createCell(row, 5, "STOCK DISPONIBLE", style);
		createCell(row, 6, "CATEGORIA", style);
		createCell(row, 7, "MARCA", style);


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

		for (Producto usu : this.productoList) {
			Row row = sheet.createRow(rowCount++);
			int columnCount = 0;
			createCell(row, columnCount++, usu.getIdProducto(), style);
			createCell(row, columnCount++, usu.getDescripcion(), style);
			createCell(row, columnCount++, usu.getFechaVencimiento().toString(), style);
			createCell(row, columnCount++, usu.getNombre(), style);
			createCell(row, columnCount++, String.valueOf(usu.getPrecioUnidad()), style);
			createCell(row, columnCount++, usu.getStockDisponible(), style);
			createCell(row, columnCount++, usu.getCategoriasProducto().getNombreCategoria(), style);
			createCell(row, columnCount++, usu.getMarca().getNombreMarca(), style);
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
