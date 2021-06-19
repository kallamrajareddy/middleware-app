package com.kallam.middleware;

import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.HorizontalAlignment;
import com.itextpdf.layout.property.UnitValue;
 
import java.io.File;
 
public class NestedTablesAligned {
    public static final String DEST = "nested_tables_aligned.pdf";
 
    public static void main(String[] args) throws Exception {
        File file = new File(DEST);
        //file.getParentFile().mkdirs();
 
       // new NestedTablesAligned().manipulatePdf(DEST);
        
        PdfDocument pdfDocument = new PdfDocument(new PdfWriter(DEST));
        Document document = new Document(pdfDocument,  PageSize.A4.rotate());
    PageSize pageSize = pdfDocument.getDefaultPageSize();
    
String imageUrl = "http://localhost:8182/images/GP-0001.jpg";
    
    ImageData data = ImageDataFactory.create(imageUrl);        

    // Creating the image       
    Image img = new Image(data);
    img.setFixedPosition(pageSize.getRight() -70, pageSize.getTop() - 60, (pageSize.getWidth() - 70) );
    img.setWidth(50);
    img.setHeight(50);

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
    Table innerTable = new Table(new float[] {70, 70});
    innerTable.addCell("table 2 - 1");
    innerTable.addCell("table 2 - 2");
    table.addCell(innerTable);
    table.setFixedPosition(pageSize.getLeft() + 5, pageSize.getTop() - 80, pageSize.getWidth() - 700);
    
    table.setHeight(150);
    document.add(table);
    document.add(img);

    table = new Table(new float[] {40, 60});
    table.addCell("table 2 - 1");
    table.addCell("table 2 - 2");
    
    table.setFixedPosition(pageSize.getLeft() + 30, pageSize.getTop() - 265, (pageSize.getWidth() - 70) / 3);
    //table.setFixedPosition(pageSize.getLeft() + 30, pageSize.getTop() - 265, (pageSize.getWidth() - 70) / 2);
    table.setHeight(185);
    document.add(table);
    

    table = new Table(new float[] {20, 50, 30});
    table.addCell("table 4 - 1");
    table.addCell("table 4 - 2");
    table.addCell("table 4 - 3");
    table.setFixedPosition(pageSize.getLeft() + 30, pageSize.getTop() - 720, (pageSize.getWidth() - 70) / 2);
    table.setHeight(450);
    document.add(table);

    table = new Table(1);
    table.addCell("table 6");
    table.setFixedPosition(pageSize.getLeft() + 30, pageSize.getTop() - 810, (pageSize.getWidth() - 70) / 2);
    table.setHeight(85);
    document.add(table);
 
    table = new Table(new float[] {20, 40, 20, 20});
    table.addCell("table 3 - 1");
    table.addCell("table 3 - 2");
    table.addCell("table 3 - 3");
    table.addCell("table 3 - 4");
    table.setFixedPosition(pageSize.getRight() - (pageSize.getWidth() - 10) / 2, pageSize.getTop() - 345, (pageSize.getWidth() - 70) / 2);
    table.setHeight(265);
    document.add(table);

    table = new Table(1);
    table.addCell("table 5 - 1");
    table.addCell("table 5 - 2");
    table.setFixedPosition(pageSize.getRight() - (pageSize.getWidth() - 10) / 2, pageSize.getTop() - 640, (pageSize.getWidth() - 70) / 2);
    table.setHeight(290);
    document.add(table);
    
   

    table = new Table(new float[] {20, 50, 30});
    table.addCell("table 7 - 1");
    table.addCell("table 7 - 2");
   table.addCell("table 7 - 3");
    table.setFixedPosition(pageSize.getRight() - (pageSize.getWidth() - 10) / 2, pageSize.getTop() - 810, (pageSize.getWidth() - 70) / 2);
    table.setHeight(165);
    document.add(table);
    //document.add(img);
    document.close();
    }
 
    protected void manipulatePdf(String dest) throws Exception {
        PdfDocument pdfDoc = new PdfDocument(new PdfWriter(dest));
        Document doc = new Document(pdfDoc, PageSize.A4.rotate());
 
        float[] columnWidths = {200f, 200f, 200f};
 
        Table table = new Table(columnWidths);
        buildNestedTables(table);
 
        doc.add(table);
 
        doc.close();
    }
 
    private static void buildNestedTables(Table outerTable) {
    	float[] tabl1 = {1};
    	float[] tabl2 = {2};
        Table innerTable1 = new Table(tabl1);
        innerTable1.setWidth(100f);
        innerTable1.setHorizontalAlignment(HorizontalAlignment.LEFT);
        innerTable1.addCell("Cell 1");
        innerTable1.addCell("Cell 2");
        outerTable.addCell(innerTable1);
 
        Table innerTable2 = new Table(tabl2);
        innerTable2.setWidth(100f);
        innerTable2.setHorizontalAlignment(HorizontalAlignment.CENTER);
        innerTable2.addCell("Cell 3");
        innerTable2.addCell("Cell 4");
        outerTable.addCell(innerTable2);
 
        Table innerTable3 = new Table(tabl2);
        innerTable3.setWidth(100f);
        innerTable3.setHorizontalAlignment(HorizontalAlignment.RIGHT);
        innerTable3.addCell("Cell 5");
        innerTable3.addCell("Cell 6");
        outerTable.addCell(innerTable3);
    }
}