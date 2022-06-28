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

import com.sena.disocc.app.modelo.DetallesCompra;
import com.sena.disocc.app.modelo.Usuario;

public class ExportarDetalleCompra {

	private XSSFWorkbook workbook;
	private XSSFSheet sheet;
	List<DetallesCompra> detalleList;
	
	public ExportarDetalleCompra(List<DetallesCompra> listaDetalle) {
		
		this.detalleList= listaDetalle;
		workbook = new XSSFWorkbook();
	}
	
	private void writeHeaderLine() {
		sheet = workbook.createSheet("ListaDetallesCompras");

		Row row = sheet.createRow(0);

		CellStyle style = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setBold(true);
		font.setFontHeight(16);
		style.setFont(font);

		createCell(row, 0, "ID", style);
		createCell(row, 1, "COMPRA ", style);
		createCell(row, 2, "PRODUCTO", style);
		createCell(row, 3, "FECHA", style);
		createCell(row, 4, "PRECIO DEL PRODUCTO", style);
		createCell(row, 5, "CANTIDAD", style);
		createCell(row, 6, "SUBTOTAL", style);
		createCell(row, 7, "IVA", style);
		createCell(row, 8, "TOTAL", style);


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

		for (DetallesCompra usu : this.detalleList) {
			Row row = sheet.createRow(rowCount++);
			int columnCount = 0;
			createCell(row, columnCount++, usu.getId(), style);
			createCell(row, columnCount++, usu.getCompra().getIdCompra(), style);
			createCell(row, columnCount++, usu.getProducto().getNombre(), style);
			createCell(row, columnCount++, usu.getFecha().toString(), style);
			createCell(row, columnCount++, String.valueOf(usu.getProducto().getPrecioUnidad()), style);
			createCell(row, columnCount++, usu.getCantidad(), style);
			createCell(row, columnCount++, String.valueOf(usu.getSubtotal()), style);
			createCell(row, columnCount++, usu.getIva(), style);
			createCell(row, columnCount++, String.valueOf(usu.getTotal()), style);
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
