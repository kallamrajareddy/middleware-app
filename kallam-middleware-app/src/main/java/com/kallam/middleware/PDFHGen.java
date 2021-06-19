package com.kallam.middleware;

import java.io.FileOutputStream;
import java.net.URL;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class PDFHGen {
	
	 public void tableLayout(PdfPTable table, float[][] widths, float[] heights,
		        int headerRows, int rowStart, PdfContentByte[] canvases) {
		        int columns;
		        Rectangle rect;
		        int footer = widths.length - table.getFooterRows();
		        int header = table.getHeaderRows() - table.getFooterRows() + 1;
		        for (int row = header; row < footer; row += 2) {
		            columns = widths[row].length - 1;
		            rect = new Rectangle(widths[row][0], heights[row],
		                        widths[row][columns], heights[row + 1]);
		            rect.setBackgroundColor(BaseColor.YELLOW);
		            rect.setBorder(Rectangle.NO_BORDER);
		            canvases[PdfPTable.BASECANVAS].rectangle(rect);
		        }
		    }

	public static void main(String[] args) {
		Document document = new Document();
		try
		{
			
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("AddImageExample.pdf"));
			Rectangle rect= new Rectangle(PageSize.A4);
			/*rect.enableBorderSide(1);
			rect.enableBorderSide(2);
			rect.enableBorderSide(4);
			rect.enableBorderSide(8);*/
			rect.setBorder(2);
			rect.setBorderColor(BaseColor.BLACK);
			rect.setBorder(Rectangle.BOX);
			rect.setBorderWidth(1);
		    document.open();
		    
		    
		    PdfPTable table = new PdfPTable(3); // 3 columns.
	        table.setWidthPercentage(100); //Width 100%
	        table.setSpacingBefore(0f); //Space before table
	        table.setSpacingAfter(0f); //Space after table
	 
	        //Set Column widths
	        float[] columnWidths = {100f, 100f, 40f};
	        table.setWidths(columnWidths);
	 
	        PdfPCell cell1 = new PdfPCell();
	        PdfPTable cell1table = new PdfPTable(1);
	        float[] cellcolumnWidths = {100};
	        cell1table.setWidthPercentage(100);
	        cell1table.setWidths(cellcolumnWidths);
	        cell1table.addCell("one");
	        cell1table.addCell(new PdfPCell(new Phrase("Two\nLines")));
	        
	        cell1.setBorderColor(BaseColor.BLACK);
	        cell1.setPaddingLeft(10);
	        cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
	        cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        cell1.addElement(cell1table);
	        //cell1.setFixedHeight(fixedHeight);
	 
	        PdfPCell cell2 = new PdfPCell(new Paragraph("Cell 2"));
	        cell2.setBorderColor(BaseColor.GREEN);
	        cell2.setPaddingLeft(10);
	        cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
	        cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
	 
	        String imageUrl = "http://localhost:8182/images/GP-0001.jpg";
		    Image image2 = Image.getInstance(new URL(imageUrl));
		    //image2.setAbsolutePosition(100f, 550f);
		    //image2.scaleAbsolute(50, 50);
		    //document.add(image2);
	        PdfPCell cell3 = new PdfPCell();
	        image2.setWidthPercentage(100);
	        cell3.addElement(image2);
	        /*
	        cell3.setBorderColor(BaseColor.RED);
	        cell3.setPaddingLeft(50);
	        cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
	        cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);*/
	 
	        //To avoid having the cell border and the content overlap, if you are having thick cell borders
	        //cell1.setUserBorderPadding(true);
	        //cell2.setUserBorderPadding(true);
	        //cell3.setUserBorderPadding(true);
	 
	        table.addCell(cell1);
	        table.addCell(cell2);
	        table.addCell(cell3);
	 
	        document.add(table);
	        //document.add(rect);
		    
		 
		   
		 
		    document.close();
		    writer.close();
		} catch (Exception e)
		{
		    e.printStackTrace();
		}
	}

}
