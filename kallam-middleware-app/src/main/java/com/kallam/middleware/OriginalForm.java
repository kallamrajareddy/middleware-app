package com.kallam.middleware;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.MalformedURLException;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Table;

public class OriginalForm {

	public static final String DEST = "HarshaFinance.pdf";

	public static void main(String[] args) throws FileNotFoundException, MalformedURLException {
		File file = new File(DEST);
		// file.getParentFile().mkdirs();

		// new NestedTablesAligned().manipulatePdf(DEST);

		PdfDocument pdfDocument = new PdfDocument(new PdfWriter(DEST));
		Document document = new Document(pdfDocument, PageSize.A4.rotate());
		PageSize pageSize = pdfDocument.getDefaultPageSize();

		String imageUrl = "http://localhost:8182/images/GP-0001.jpg";

		ImageData data = ImageDataFactory.create(imageUrl);

		// Creating the image
		Image img = new Image(data);
		img.setFixedPosition(pageSize.getRight() - 70, pageSize.getTop() - 60, (pageSize.getWidth() - 70));
		img.setWidth(50);
		img.setHeight(50);

		Table main = new Table(new float[] {20, 40, 40});
		Cell mainCell1 = new Cell();
		Cell mainCell2 = new Cell();
		Cell mainCell3 = new Cell();
		mainCell1.setHeight(10);
		
		
		Table table = new Table(1);
	    Cell cell1 = new Cell();
	    cell1.setHeight(10);
	    cell1.setBold();
	    cell1.setWidth(150);
	    cell1.add("Form F (SEC.7 , Rule 8)");
	    //cell1.setFixedPosition(pageSize.getLeft() + 5, pageSize.getTop() - 50, pageSize.getWidth() - 700);
	    Cell cell2 = new Cell();
	    cell2.setWidth(150);
	    cell2.setHeight(10);
	    cell2.setBold();
	    cell2.add("Lic No. : 239/2008");
	    //cell2.setFixedPosition(pageSize.getLeft() + 5, pageSize.getTop() - 100, pageSize.getWidth() - 700);
	    table.addCell(cell1);
	    table.addCell(cell2);
	    table.setWidth(155);
	    //table.setFixedPosition(pageSize.getLeft() + 6, pageSize.getTop() - 100, pageSize.getWidth() - 10);
	    
	    mainCell1.add(table);
	    main.addCell(mainCell1);
	    
	    table = new Table(1);
	     cell1 = new Cell();
	    cell1.setHeight(10);
	    cell1.setBold();
	    cell1.setWidth(150);
	    cell1.add("Form F (rgsdfgs.7 , Rule 8)");
	    //cell1.setFixedPosition(pageSize.getLeft() + 5, pageSize.getTop() - 50, pageSize.getWidth() - 700);
	     cell2 = new Cell();
	    cell2.setWidth(150);
	    cell2.setHeight(10);
	    cell2.setBold();
	    cell2.add("Lic No. : 23fgsdfg9/2008");
	    //cell2.setFixedPosition(pageSize.getLeft() + 5, pageSize.getTop() - 100, pageSize.getWidth() - 700);
	    table.addCell(cell1);
	    table.addCell(cell2);
	    table.setWidth(155);
	   // table.setFixedPosition(150, pageSize.getTop() - 100, 150);
	    mainCell2.add(table);
	    main.addCell(mainCell2);
	    
		main.setFixedPosition(pageSize.getLeft() + 5, pageSize.getTop() - 100, pageSize.getWidth() - 10);

		document.add(main);
		document.close();
	}

}
