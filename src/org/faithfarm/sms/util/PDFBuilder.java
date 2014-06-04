package org.faithfarm.sms.util;

import java.awt.Color;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.text.WordUtils;
import org.faithfarm.sms.domain.CwtModuleSection;
import org.faithfarm.sms.domain.CwtModules;
import org.faithfarm.sms.domain.CwtProgram;
import org.faithfarm.sms.domain.CwtRoster;
import org.faithfarm.sms.domain.Intake;
import org.faithfarm.sms.domain.JobSkill;
import org.faithfarm.sms.domain.MedicalCondition;
import org.faithfarm.sms.domain.StudentPassHistory;
import org.faithfarm.sms.domain.SystemUser;
import org.faithfarm.sms.hibernate.data.GenericDao;
import org.faithfarm.sms.hibernate.data.IntakeDao;
import org.faithfarm.sms.hibernate.data.StudentHistoryDao;
import org.faithfarm.sms.hibernate.data.StudentPassHistoryDao;
import org.faithfarm.sms.struts.form.IntakeForm;

import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfStamper;
import com.lowagie.text.pdf.PdfWriter;

public class PDFBuilder {

	
	public void omegaPdf(HttpServletResponse response) {
		
		GenericDao dao = new GenericDao();
				
		response.setContentType("application/pdf");
		Document document = new Document();
		try {
			PdfWriter writer = PdfWriter.getInstance(document,
					response.getOutputStream());
			document.setMargins(10, 10, 10, 10);
			document.open();
			String currentDate = Validator.convertDate(new java.util.Date()
					+ "");

			// Create a table to server as a spacer
			PdfPTable spacerTbl = new PdfPTable(1);
			PdfPCell spacerCell = new PdfPCell(new Paragraph(""));
			spacerCell.setColspan(1);
			spacerTbl.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			spacerCell.setBorder(Rectangle.NO_BORDER);
			spacerCell.setFixedHeight(20);
			spacerTbl.addCell(spacerCell);

			Font cellFont = FontFactory.getFont(FontFactory.COURIER, 8);
			Font headerFont = FontFactory.getFont(FontFactory.HELVETICA, 10);
			Font titleFont = FontFactory.getFont(FontFactory.HELVETICA, 14);
			Font dateFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 6);
			Font colHeaderFont = FontFactory.getFont(
					FontFactory.HELVETICA_BOLD, 8);
			colHeaderFont.setStyle(Font.UNDERLINE);
			headerFont.setStyle(Font.BOLD);

			Image img = Image.getInstance(String.format(
					"c:\\pdfFiles\\logo_wordpress_2.png", ""));
			img.scaleToFit(150, 94);
			document.add(new Chunk(img, 0, -90, true));

			// spacer
			document.add(spacerTbl);
			document.add(spacerTbl);
			document.add(spacerTbl);
			document.add(spacerTbl);
			document.add(spacerTbl);
			document.add(spacerTbl);

			String sTitle="";
			sTitle="Fort Lauderdale Omega Report ";
			Chunk titleChunk = new Chunk(sTitle);
			titleFont.setStyle(Font.BOLD);
			titleChunk.setFont(titleFont);
			document.add(titleChunk);
			document.add(spacerTbl);

			Chunk dateChunk = new Chunk("Run on " + currentDate);
			dateFont.setStyle(Font.ITALIC);
			dateChunk.setFont(dateFont);
			document.add(dateChunk);

			// spacer
			document.add(spacerTbl);

			List<Intake> class6List = dao.listOmega();
			
			PdfPTable table0 = new PdfPTable(3);
			Font detailsFont = FontFactory.getFont(
					FontFactory.COURIER, 8);
			detailsFont.setStyle(Font.NORMAL);
			PdfPCell cell0 = new PdfPCell(new Paragraph("column span 3"));
			cell0.setColspan(3);
			table0.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			cell0.setBorder(Rectangle.NO_BORDER);
			table0.setWidthPercentage(100);
			table0.setHorizontalAlignment(Element.ALIGN_LEFT);

			table0.addCell(new Phrase("STUDENT NAME", colHeaderFont));
			table0.addCell(new Phrase("DOB", colHeaderFont));
			table0.addCell(new Phrase("ENTRY DATE", colHeaderFont));
						
			for (int i=0;i<class6List.size();i++) {
				Intake intake = (Intake)class6List.get(i);
				
				cell0.setColspan(5);
				table0.getDefaultCell().setBorder(Rectangle.NO_BORDER);
				cell0.setBorder(Rectangle.NO_BORDER);
				table0.setWidthPercentage(100);
				table0.setHorizontalAlignment(Element.ALIGN_LEFT);

				table0.addCell(new Phrase(intake.getFirstname()+" "+intake.getLastname(), detailsFont));
				table0.addCell(new Phrase(intake.getDob(),detailsFont));
				table0.addCell(new Phrase(intake.getEntryDate(), detailsFont));
			}
			
			table0.addCell(new Phrase("TOTAL ENROLLED: "+class6List.size(), colHeaderFont));

			document.add(table0);
			document.close();

		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (IOException io) {
			io.printStackTrace();
		}
	}
	public void cwtGraduatePdf(String farm, HttpServletResponse response) {
		
		GenericDao dao = new GenericDao();
				
		response.setContentType("application/pdf");
		Document document = new Document();
		try {
			PdfWriter writer = PdfWriter.getInstance(document,
					response.getOutputStream());
			document.setMargins(10, 10, 10, 10);
			document.open();
			String currentDate = Validator.convertDate(new java.util.Date()
					+ "");

			// Create a table to server as a spacer
			PdfPTable spacerTbl = new PdfPTable(1);
			PdfPCell spacerCell = new PdfPCell(new Paragraph(""));
			spacerCell.setColspan(1);
			spacerTbl.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			spacerCell.setBorder(Rectangle.NO_BORDER);
			spacerCell.setFixedHeight(20);
			spacerTbl.addCell(spacerCell);

			Font cellFont = FontFactory.getFont(FontFactory.COURIER, 8);
			Font headerFont = FontFactory.getFont(FontFactory.HELVETICA, 10);
			Font titleFont = FontFactory.getFont(FontFactory.HELVETICA, 14);
			Font dateFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 6);
			Font colHeaderFont = FontFactory.getFont(
					FontFactory.HELVETICA_BOLD, 8);
			colHeaderFont.setStyle(Font.UNDERLINE);
			headerFont.setStyle(Font.BOLD);

			Image img = Image.getInstance(String.format(
					"c:\\pdfFiles\\logo_wordpress_2.png", ""));
			img.scaleToFit(150, 94);
			document.add(new Chunk(img, 0, -90, true));

			// spacer
			document.add(spacerTbl);
			document.add(spacerTbl);
			document.add(spacerTbl);
			document.add(spacerTbl);
			document.add(spacerTbl);
			document.add(spacerTbl);

			String sTitle="";
			sTitle=farm + " CWT Graduate Certificate Report for ";
			Chunk titleChunk = new Chunk(sTitle);
			titleFont.setStyle(Font.BOLD);
			titleChunk.setFont(titleFont);
			document.add(titleChunk);
			document.add(spacerTbl);

			Chunk dateChunk = new Chunk("Run on " + currentDate);
			dateFont.setStyle(Font.ITALIC);
			dateChunk.setFont(dateFont);
			document.add(dateChunk);

			// spacer
			document.add(spacerTbl);

			List class6List = dao.listClass("6", farm);
			
			PdfPTable table0 = new PdfPTable(3);
			Font detailsFont = FontFactory.getFont(
					FontFactory.COURIER, 8);
			detailsFont.setStyle(Font.NORMAL);
			PdfPCell cell0 = new PdfPCell(new Paragraph("column span 3"));
			cell0.setColspan(3);
			table0.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			cell0.setBorder(Rectangle.NO_BORDER);
			table0.setWidthPercentage(100);
			table0.setHorizontalAlignment(Element.ALIGN_LEFT);

			table0.addCell(new Phrase("STUDENT NAME", colHeaderFont));
			table0.addCell(new Phrase("CWT Program", colHeaderFont));
			table0.addCell(new Phrase("Complete", colHeaderFont));
						
			for (int i=0;i<class6List.size();i++) {
				Intake intake = (Intake)class6List.get(i);
				CwtProgram program = (CwtProgram)dao.findById(new CwtProgram().getClass(),intake.getCwtProgramId());
				int intakeModuleCount = dao.countCwtModulesForIntake(intake.getIntakeId(), intake.getCwtProgramId());
				int programModuleCount = dao.countCwtModules(intake.getCwtProgramId());
				String completed = "No";
				if (intakeModuleCount==programModuleCount)
					completed="Yes";
				
				cell0.setColspan(5);
				table0.getDefaultCell().setBorder(Rectangle.NO_BORDER);
				cell0.setBorder(Rectangle.NO_BORDER);
				table0.setWidthPercentage(100);
				table0.setHorizontalAlignment(Element.ALIGN_LEFT);

				table0.addCell(new Phrase(intake.getFirstname()+" "+intake.getLastname(), detailsFont));
				table0.addCell(new Phrase(program.getProgramName(), detailsFont));
				table0.addCell(new Phrase(completed, detailsFont));
			}
			document.add(table0);
			document.close();

		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (IOException io) {
			io.printStackTrace();
		}
	}
	public void passlistPdf(SystemUser user, String passDate1, String passDate2, HttpServletResponse response) {
		List<Intake> passList = new ArrayList<Intake>();
		String farm=user.getFarmBase();
		response.setContentType("application/pdf");
		Document document = new Document();
		try {
			PdfWriter writer = PdfWriter.getInstance(document,
					response.getOutputStream());

			document.setMargins(10, 10, 10, 10);
			document.open();
			String currentDate = Validator.convertDate(new java.util.Date()
					+ "");

			// Create a table to server as a spacer
			PdfPTable spacerTbl = new PdfPTable(1);
			PdfPCell spacerCell = new PdfPCell(new Paragraph(""));
			spacerCell.setColspan(1);
			spacerTbl.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			spacerCell.setBorder(Rectangle.NO_BORDER);
			spacerCell.setFixedHeight(20);
			spacerTbl.addCell(spacerCell);

			Font cellFont = FontFactory.getFont(FontFactory.COURIER, 8);
			Font headerFont = FontFactory.getFont(FontFactory.HELVETICA, 10);
			Font titleFont = FontFactory.getFont(FontFactory.HELVETICA, 14);
			Font dateFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 6);
			Font colHeaderFont = FontFactory.getFont(
					FontFactory.HELVETICA_BOLD, 8);
			colHeaderFont.setStyle(Font.UNDERLINE);
			headerFont.setStyle(Font.BOLD);

			Image img = Image.getInstance(String.format(
					"c:\\pdfFiles\\logo_wordpress_2.png", ""));
			img.scaleToFit(150, 94);
			document.add(new Chunk(img, 0, -90, true));

			// spacer
			document.add(spacerTbl);
			document.add(spacerTbl);
			document.add(spacerTbl);
			document.add(spacerTbl);
			document.add(spacerTbl);
			document.add(spacerTbl);

			String sTitle="";
			if (passDate2==null||passDate2.length()==0)
				sTitle="Passes for " + passDate1 + " @ "+ farm;
			else
				sTitle="Passes for " + passDate1 + " to "+passDate2+" @ "+ farm;
			Chunk titleChunk = new Chunk(sTitle);
			titleFont.setStyle(Font.BOLD);
			titleChunk.setFont(titleFont);
			document.add(titleChunk);
			document.add(spacerTbl);

			Chunk dateChunk = new Chunk("Run on " + currentDate);
			dateFont.setStyle(Font.ITALIC);
			dateChunk.setFont(dateFont);
			document.add(dateChunk);

			// spacer
			document.add(spacerTbl);

			StudentPassHistoryDao dao = new StudentPassHistoryDao();
			IntakeDao intakeDao = new IntakeDao();
			 
			passList = dao.search(passDate1,passDate2);

			// Table of Applicants
			PdfPTable table0 = new PdfPTable(5);
			PdfPCell cell0 = new PdfPCell(new Paragraph("column span 3"));
			cell0.setColspan(5);
			table0.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			cell0.setBorder(Rectangle.NO_BORDER);
			table0.setWidthPercentage(100);
			table0.setHorizontalAlignment(Element.ALIGN_LEFT);

			table0.addCell(new Phrase("DATE", colHeaderFont));
			table0.addCell(new Phrase("STUDENT NAME", colHeaderFont));
			table0.addCell(new Phrase("HOURS", colHeaderFont));
			table0.addCell(new Phrase("TYPE", colHeaderFont));
			table0.addCell(new Phrase("COMMENTS", colHeaderFont));
			for (Iterator iterator = passList.iterator(); iterator.hasNext();) {
				StudentPassHistory pass = (StudentPassHistory)iterator.next();
				Intake intake = intakeDao.find(pass.getIntakeId());
				
				if (intake.getFarmBase().equals(farm)) {
					table0.addCell(new Phrase(pass.getPassDate(), cellFont));
					table0.addCell(new Phrase(intake.getFirstname().toUpperCase()
							+ " " + intake.getLastname().toUpperCase(), cellFont));
					table0.addCell(new Phrase(pass.getHours(), cellFont));
					table0.addCell(new Phrase(pass.getPassType(), cellFont));
					table0.addCell(new Phrase(pass.getComments(), cellFont));
				}
			}

			document.add(table0);
			document.close();

		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (IOException io) {
			io.printStackTrace();
		}
	}

	public void waitlistPdf(SystemUser user, String farm,
			HttpServletResponse response) {
		List<Intake> appList = new ArrayList<Intake>();

		response.setContentType("application/pdf");
		Document document = new Document();
		try {
			PdfWriter writer = PdfWriter.getInstance(document,
					response.getOutputStream());

			document.setMargins(10, 10, 10, 10);
			document.open();
			String currentDate = Validator.convertDate(new java.util.Date()
					+ "");

			// Create a table to server as a spacer
			PdfPTable spacerTbl = new PdfPTable(1);
			PdfPCell spacerCell = new PdfPCell(new Paragraph(""));
			spacerCell.setColspan(1);
			spacerTbl.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			spacerCell.setBorder(Rectangle.NO_BORDER);
			spacerCell.setFixedHeight(20);
			spacerTbl.addCell(spacerCell);

			Font cellFont = FontFactory.getFont(FontFactory.COURIER, 8);
			Font headerFont = FontFactory.getFont(FontFactory.HELVETICA, 10);
			Font titleFont = FontFactory.getFont(FontFactory.HELVETICA, 14);
			Font dateFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 6);
			Font colHeaderFont = FontFactory.getFont(
					FontFactory.HELVETICA_BOLD, 8);
			colHeaderFont.setStyle(Font.UNDERLINE);
			headerFont.setStyle(Font.BOLD);

			Image img = Image.getInstance(String.format(
					"c:\\pdfFiles\\logo_wordpress_2.png", ""));
			img.scaleToFit(150, 94);
			document.add(new Chunk(img, 0, -90, true));

			// spacer
			document.add(spacerTbl);
			document.add(spacerTbl);
			document.add(spacerTbl);
			document.add(spacerTbl);
			document.add(spacerTbl);
			document.add(spacerTbl);

			Chunk titleChunk = new Chunk("Waiting List for " + farm);
			titleFont.setStyle(Font.BOLD);
			titleChunk.setFont(titleFont);
			document.add(titleChunk);
			document.add(spacerTbl);

			Chunk dateChunk = new Chunk("Run on " + currentDate);
			dateFont.setStyle(Font.ITALIC);
			dateChunk.setFont(dateFont);
			document.add(dateChunk);

			// spacer
			document.add(spacerTbl);

			IntakeDao dao = new IntakeDao();

			appList = dao.searchApplications(null, null, null, null, null,
					null, "Waitlist", null,null,farm);

			// Table of Applicants
			PdfPTable table0 = new PdfPTable(4);
			PdfPCell cell0 = new PdfPCell(new Paragraph("column span 3"));
			cell0.setColspan(4);
			table0.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			cell0.setBorder(Rectangle.NO_BORDER);
			table0.setWidthPercentage(100);
			table0.setHorizontalAlignment(Element.ALIGN_LEFT);

			table0.addCell(new Phrase("APPLICANT NAME", colHeaderFont));
			table0.addCell(new Phrase("CONTACT PHONE", colHeaderFont));
			table0.addCell(new Phrase("EMAIL ADDRESS", colHeaderFont));
			table0.addCell(new Phrase("SUBMISSION DATE", colHeaderFont));
			for (Iterator iterator = appList.iterator(); iterator.hasNext();) {
				Intake intake = (Intake) iterator.next();
				table0.addCell(new Phrase(intake.getFirstname().toUpperCase()
						+ " " + intake.getLastname().toUpperCase(), cellFont));
				table0.addCell(new Phrase(intake.getReferredByPhone(), cellFont));
				table0.addCell(new Phrase(intake.getEmailAddress(), cellFont));
				table0.addCell(new Phrase(intake.getCreationDate(), cellFont));
			}

			document.add(table0);
			document.close();

		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (IOException io) {
			io.printStackTrace();
		}
	}

	public void applicationPdf(IntakeForm form, HttpServletResponse response) {

		Intake intake = form.getIntake();
		response.setContentType("application/pdf");
		Document document = new Document();
		try {
			PdfWriter writer = PdfWriter.getInstance(document,
					response.getOutputStream()); // Code 2
			document.open();

			String applicantName = intake.getFirstname() + " " + intake.getMi()
					+ " " + intake.getLastname();
			String currentDate = Validator.convertDate(new java.util.Date()
					+ "");

			// Application Page 1
			PdfReader pdfTemplate = new PdfReader(
					"c:\\pdfFiles\\IntakeApplicationFinal_PDF.pdf");
			PdfStamper stamper = new PdfStamper(pdfTemplate,
					response.getOutputStream());
			stamper.setFormFlattening(true);
			stamper.getAcroFields().setField("todaysDate", currentDate);
			stamper.getAcroFields().setField("lastname", intake.getLastname());
			stamper.getAcroFields()
					.setField("firstname", intake.getFirstname());
			stamper.getAcroFields().setField("mi", intake.getMi());
			stamper.getAcroFields().setField("ssn", intake.getSsn());
			stamper.getAcroFields().setField("address", intake.getAddress());
			stamper.getAcroFields().setField("city", intake.getCity());
			stamper.getAcroFields().setField("state", intake.getState());
			stamper.getAcroFields().setField("zipcode", intake.getZipcode());

			stamper.getAcroFields().setField("referredBy",
					intake.getReferredBy());
			stamper.getAcroFields().setField("referredPhone",
					intake.getReferredByPhone());
			stamper.getAcroFields().setField("emergencyContact",
					intake.getEmergencyContact());
			stamper.getAcroFields().setField("emergencyRelationship",
					intake.getEmergencryRelationship());
			stamper.getAcroFields().setField("emergencyPhone",
					intake.getEmergencyPhone());
			stamper.getAcroFields().setField("dob", intake.getDob());
			stamper.getAcroFields().setField("age", intake.getAge());
			stamper.getAcroFields().setField("height", intake.getHeight());
			stamper.getAcroFields().setField("weight", intake.getWeight());
			stamper.getAcroFields().setField("eyes", intake.getEyeColor());
			stamper.getAcroFields().setField("hair", intake.getHairColor());
			stamper.getAcroFields()
					.setField("ethnicity", intake.getEthnicity());
			stamper.getAcroFields().setField("homeLocationOther",
					intake.getHomeLocation());
			stamper.getAcroFields().setField("incomeSource",
					intake.getIncomeSource());
			stamper.getAcroFields().setField("incomeSource1",
					intake.getIncomeSource());
			stamper.getAcroFields()
					.setField("income", intake.getIncomeAmount());
			stamper.getAcroFields().setField("otherBenefits",
					intake.getOtherBenefits());
			stamper.getAcroFields().setField("veteranFlag",
					intake.getVeteranStatus());
			stamper.getAcroFields().setField("branchOfService",
					intake.getBranchOfService());
			stamper.getAcroFields().setField("highestRank", intake.getRank());
			stamper.getAcroFields().setField("lengthOfService",
					intake.getLengthOfService());
			stamper.getAcroFields().setField("dlFlag", intake.getDlFlag());
			stamper.getAcroFields().setField("dlState", intake.getDlState());
			stamper.getAcroFields().setField("dlNumber", intake.getDlNumber());
			stamper.getAcroFields().setField("stateIdFlag",
					intake.getStateIdFlag());
			stamper.getAcroFields().setField("stateIdType",
					intake.getStateIdType());
			stamper.getAcroFields().setField("motherLiving",
					intake.getMotherLivingFlag());
			stamper.getAcroFields().setField("fatherLiving",
					intake.getFatherLivingFlag());
			stamper.getAcroFields().setField("relationshipMother",
					intake.getRelationshipWithMother());
			stamper.getAcroFields().setField("relationshipFather",
					intake.getRelationshipWithFather());
			stamper.getAcroFields().setField("brothers", intake.getBrothers());
			stamper.getAcroFields().setField("sisters", intake.getSisters());
			stamper.getAcroFields().setField("children", intake.getChildren());
			stamper.getAcroFields().setField("religiousExperience",
					intake.getReligiousExperience());
			stamper.getAcroFields().setField("religiousBackground",
					intake.getReligion());
			if ("Never Married".equals(intake.getMaritalStatus()))
				stamper.getAcroFields().setField("marriage1", "Yes");
			if ("Married".equals(intake.getMaritalStatus()))
				stamper.getAcroFields().setField("marriage2", "Yes");
			if ("Divorced".equals(intake.getMaritalStatus()))
				stamper.getAcroFields().setField("marriage3", "Yes");
			if ("Separated".equals(intake.getMaritalStatus()))
				stamper.getAcroFields().setField("marriage4", "Yes");
			if ("Spouse Deceased".equals(intake.getMaritalStatus()))
				stamper.getAcroFields().setField("marriage5", "Yes");
			if ("Lives with Girlfriend".equals(intake.getMaritalStatus()))
				stamper.getAcroFields().setField("marriage6", "Yes");
			if ("Remarried".equals(intake.getMaritalStatus()))
				stamper.getAcroFields().setField("marriage7", "Yes");
			if ("Re-divorced".equals(intake.getMaritalStatus()))
				stamper.getAcroFields().setField("marriage8", "Yes");
			if (intake.getEducationLevel() != null) {
				if (intake.getEducationLevel().equals("9th Grade")
						|| intake.getEducationLevel().equals("10th Grade")
						|| intake.getEducationLevel().equals("11th Grade")
						|| intake.getEducationLevel().equals("12th Grade"))
					stamper.getAcroFields().setField("highSchoolFlag", "Yes");
				if (intake.getEducationLevel().equals("3rd Grade")
						|| intake.getEducationLevel().equals("4th Grade")
						|| intake.getEducationLevel().equals("1st Grade")
						|| intake.getEducationLevel().equals("2nd Grade")
						|| intake.getEducationLevel().equals("5th Grade")
						|| intake.getEducationLevel().equals("6th Grade")
						|| intake.getEducationLevel().equals("7th Grade")
						|| intake.getEducationLevel().equals("8th Grade"))
					stamper.getAcroFields().setField("elementaryFlag", "Yes");
				if (intake.getEducationLevel().equals("GED"))
					stamper.getAcroFields().setField("ged", "Yes");
				if (intake.getEducationLevel().contains("Degree"))
					stamper.getAcroFields().setField("college", "Yes");
			}

			if ("Yes".equals(intake.getGraduateFlag()))
				stamper.getAcroFields().setField("graduateYes", "Yes");
			else
				stamper.getAcroFields().setField("graduateNo", "Yes");
			if ("No".equals(intake.getEnglishSpeakingFlag()))
				stamper.getAcroFields().setField("englishSpeakNo", "Yes");
			else
				stamper.getAcroFields().setField("englishSpeakYes", "Yes");
			if ("No".equals(intake.getEnglishReadingFlag()))
				stamper.getAcroFields().setField("englishReadNo", "Yes");
			else
				stamper.getAcroFields().setField("englishReadYes", "Yes");

			if ("Yes".equals(intake.getIncomeWeeklyFlag()))
				stamper.getAcroFields().setField("weekly", "Yes");
			if ("Yes".equals(intake.getIncomeMonthlyFlag()))
				stamper.getAcroFields().setField("monthly", "Yes");

			if (intake.getSsFlag() != null
					&& "yes".equals(intake.getSsFlag().toLowerCase()))
				stamper.getAcroFields().setField("ss", "Yes");
			if (intake.getVaFlag() != null
					&& "yes".equals(intake.getVaFlag().toLowerCase()))
				stamper.getAcroFields().setField("va", "Yes");
			if (intake.getWcFlag() != null
					&& "yes".equals(intake.getWcFlag().toLowerCase()))
				stamper.getAcroFields().setField("wc", "Yes");

			if ("Own Home".equals(intake.getHomeLocation()))
				stamper.getAcroFields().setField("homeLocation1", "Yes");
			if ("Parent's Home".equals(intake.getHomeLocation()))
				stamper.getAcroFields().setField("homeLocation2", "Yes");
			if ("Friend's Home".equals(intake.getHomeLocation()))
				stamper.getAcroFields().setField("homeLocation3", "Yes");
			if ("Halfway Home".equals(intake.getHomeLocation()))
				stamper.getAcroFields().setField("homeLocation4", "Yes");
			if ("Hotel".equals(intake.getHomeLocation()))
				stamper.getAcroFields().setField("homeLocation5", "Yes");
			if ("Rehab Program".equals(intake.getHomeLocation()))
				stamper.getAcroFields().setField("homeLocation6", "Yes");
			if ("Vehicle".equals(intake.getHomeLocation()))
				stamper.getAcroFields().setField("homeLocation7", "Yes");
			if ("One Street".equals(intake.getHomeLocation()))
				stamper.getAcroFields().setField("homeLocation8", "Yes");

			if ("Protestant".equals(intake.getReligion()))
				stamper.getAcroFields().setField("protestant", "Yes");
			if ("Jewish".equals(intake.getReligion()))
				stamper.getAcroFields().setField("jewish", "Yes");
			if ("Buddhist".equals(intake.getReligion()))
				stamper.getAcroFields().setField("buddhist", "Yes");
			if ("Santaria".equals(intake.getReligion()))
				stamper.getAcroFields().setField("santaria", "Yes");
			if ("Rastafarian".equals(intake.getReligion()))
				stamper.getAcroFields().setField("rastafarian", "Yes");
			if ("Catholic".equals(intake.getReligion()))
				stamper.getAcroFields().setField("catholic", "Yes");
			if ("Muslim".equals(intake.getReligion()))
				stamper.getAcroFields().setField("muslim", "Yes");
			if ("Hindu".equals(intake.getReligion()))
				stamper.getAcroFields().setField("hindu", "Yes");
			if ("New Age".equals(intake.getReligion()))
				stamper.getAcroFields().setField("newage", "Yes");
			if ("Other".equals(intake.getReligion()))
				stamper.getAcroFields().setField("other", "Yes");

			// Application Page 2
			stamper.getAcroFields().setField("alcoholYears",
					intake.getAlcoholYearsUsed());
			stamper.getAcroFields().setField("alcoholDate",
					intake.getAlcoholLastUsed());
			stamper.getAcroFields().setField("speedYears",
					intake.getSpeedYearsUsed());
			stamper.getAcroFields().setField("speedDate",
					intake.getSpeedLastUsed());
			stamper.getAcroFields().setField("cocaineYears",
					intake.getCocaineYearsUsed());
			stamper.getAcroFields().setField("cocaineDate",
					intake.getCocaineLastUsed());
			stamper.getAcroFields().setField("heroinYears",
					intake.getHeroinYearsUsed());
			stamper.getAcroFields().setField("heroinDate",
					intake.getHeroinLastUsed());
			stamper.getAcroFields().setField("marijuanaYears",
					intake.getMarijuanaYearsUsed());
			stamper.getAcroFields().setField("marijuanaDate",
					intake.getMarijuanaLastUsed());
			stamper.getAcroFields().setField("xanaxYears",
					intake.getXanaxYearsUsed());
			stamper.getAcroFields().setField("xanaxDate",
					intake.getXanaxLastUsed());
			stamper.getAcroFields().setField("oxycodoneYears",
					intake.getOxycodoneYearsUsed());
			stamper.getAcroFields().setField("oxycodoneDate",
					intake.getOxycodoneLastUsed());
			stamper.getAcroFields().setField("otherYears",
					intake.getOtherYearsUsed());
			stamper.getAcroFields().setField("otherDate",
					intake.getOtherLastUsed());

			stamper.getAcroFields().setField("sober3Years",
					intake.getSober3Years());
			stamper.getAcroFields().setField("longestSober",
					intake.getSober1Year());

			if (intake.getUsagePattern() != null) {
				if (intake.getUsagePattern().contains("Constantly"))
					stamper.getAcroFields().setField("constantly", "Yes");
				if (intake.getUsagePattern().contains("Weekends"))
					stamper.getAcroFields().setField("weekends", "Yes");
				if (intake.getUsagePattern().contains("Special Occasions"))
					stamper.getAcroFields().setField("specialOccasions", "Yes");
				if (intake.getUsagePattern().contains("Whenever available"))
					stamper.getAcroFields().setField("whenever", "Yes");
				if (intake.getUsagePattern().contains("When Things Get Tough"))
					stamper.getAcroFields().setField("tough", "Yes");
				if (intake.getUsagePattern().contains("A Week/Off A Week"))
					stamper.getAcroFields().setField("onaweek", "Yes");
			}

			stamper.getAcroFields().setField("perweek",
					intake.getQuantityPerWeek());
			stamper.getAcroFields().setField("perweek2years",
					intake.getQuantity2Years());

			if (intake.getUsageLosses() != null) {
				if (intake.getUsageLosses().contains("Job"))
					stamper.getAcroFields().setField("job", "Yes");
				if (intake.getUsageLosses().contains("Marriage"))
					stamper.getAcroFields().setField("marriage", "Yes");
				if (intake.getUsageLosses().contains("Friends"))
					stamper.getAcroFields().setField("friends", "Yes");
				if (intake.getUsageLosses().contains("Possessions"))
					stamper.getAcroFields().setField("possessions", "Yes");
				if (intake.getUsageLosses().contains("Arrests"))
					stamper.getAcroFields().setField("arrests", "Yes");
				if (intake.getUsageLosses().contains("DUI"))
					stamper.getAcroFields().setField("dui", "Yes");
				if (intake.getUsageLosses().contains("Debt"))
					stamper.getAcroFields().setField("debt", "Yes");
				if (intake.getUsageLosses().contains("Health"))
					stamper.getAcroFields().setField("health", "Yes");
				if (intake.getUsageLosses().contains("Incarceration"))
					stamper.getAcroFields().setField("incarceration", "Yes");
			}

			if (intake.getPhysicalEffects() != null) {
				if (intake.getPhysicalEffects().contains("motivational loss"))
					stamper.getAcroFields().setField("motivationalLoss", "Yes");
				if (intake.getPhysicalEffects().contains("shakes-convulsions"))
					stamper.getAcroFields().setField("shakes", "Yes");
				if (intake.getPhysicalEffects().contains("memory loss"))
					stamper.getAcroFields().setField("memoryLoss", "Yes");
				if (intake.getPhysicalEffects().contains("incoherent thinking"))
					stamper.getAcroFields().setField("incoherent", "Yes");
				if (intake.getPhysicalEffects().contains("organ problems"))
					stamper.getAcroFields().setField("organ", "Yes");
			}
			if ("Yes".equals(intake.getAaFlag()))
				stamper.getAcroFields().setField("aa", "Yes");
			stamper.getAcroFields().setField("aaYears", intake.getAaYears());
			if ("Yes".equals(intake.getPrevFfFlag()))
				stamper.getAcroFields().setField("farmYes", "Yes");
			else
				stamper.getAcroFields().setField("farmNo", "Yes");

			stamper.getAcroFields().setField("farmYears",
					intake.getPrevFfYear());
			stamper.getAcroFields().setField("rehabOther",
					intake.getPrevFfOther());

			String healthAnswer[] = form.getHealthAnswer();
			for (int index = 0; index < 16; index++) {
				if ("Yes".equals(healthAnswer[index]))
					stamper.getAcroFields().setField(
							"sq" + (index + 1) + "Yes", "Yes");
				else
					stamper.getAcroFields().setField("sq" + (index + 1) + "No",
							"Yes");
			}

			// Application Page 3
			String emotionalAnswer[] = form.getEmotionalAnswer();
			String emotionalAnswerDate[] = form.getEmotionalAnswerDate();
			String emotionalAnswerDetail[] = form.getEmotionalAnswerDetails();
			int count = 1;
			for (int index = 0; index < 6; index++) {
				if ("Yes".equals(emotionalAnswer[index]))
					stamper.getAcroFields().setField("eq" + count + "Yes",
							"Yes");
				else
					stamper.getAcroFields()
							.setField("eq" + count + "No", "Yes");
				stamper.getAcroFields().setField("eq" + count + "Dates",
						emotionalAnswerDate[index]);
				stamper.getAcroFields().setField("eq" + count + "Desc",
						emotionalAnswerDetail[index]);
				count++;
			}

			if ("excellent".equals(intake.getCurrentHealth()))
				stamper.getAcroFields().setField("healthExcellent", "Yes");
			if ("good".equals(intake.getCurrentHealth()))
				stamper.getAcroFields().setField("healthGood", "Yes");
			if ("fair".equals(intake.getCurrentHealth()))
				stamper.getAcroFields().setField("healthFair", "Yes");
			if ("failing".equals(intake.getCurrentHealth()))
				stamper.getAcroFields().setField("healthFailing", "Yes");
			if ("poor".equals(intake.getCurrentHealth()))
				stamper.getAcroFields().setField("healthPoor", "Yes");

			if ("Yes".equals(intake.getMedicationFlag()))
				stamper.getAcroFields().setField("medYes", "Yes");
			else
				stamper.getAcroFields().setField("medNo", "Yes");
			stamper.getAcroFields().setField("meds",
					intake.getMedicationDetails());

			if ("Yes".equals(intake.getNeedMedicationFlag()))
				stamper.getAcroFields().setField("needMedYes", "Yes");
			else
				stamper.getAcroFields().setField("needMedNo", "Yes");

			stamper.getAcroFields().setField("refills",
					intake.getMedicationRefillDetails());

			if ("Yes".equals(intake.getDoctorApptFlag()))
				stamper.getAcroFields().setField("drApptYes", "Yes");
			else
				stamper.getAcroFields().setField("drApptNo", "Yes");
			stamper.getAcroFields().setField("drappt",
					intake.getDoctorApptDetails());

			stamper.getAcroFields().setField("sickness",
					intake.getDoctorApptDetails());
			stamper.getAcroFields().setField("consulted",
					intake.getDoctorApptDetails());
			stamper.getAcroFields().setField("diagnosis",
					intake.getDoctorApptDetails());
			stamper.getAcroFields().setField("advised",
					intake.getDoctorApptDetails());
			stamper.getAcroFields().setField("backInjuries",
					intake.getDoctorApptDetails());

			if ("Yes".equals(intake.getEyewearFlag()))
				stamper.getAcroFields().setField("eyewearYes", "Yes");
			else
				stamper.getAcroFields().setField("eyewearNo", "Yes");

			if ("All the time".equals(intake.getEyewearUsage()))
				stamper.getAcroFields().setField("eyewearAllTime", "Yes");
			if ("Occasionally".equals(intake.getEyewearUsage()))
				stamper.getAcroFields().setField("eyewearOccasionally", "Yes");
			if ("Reading".equals(intake.getEyewearUsage()))
				stamper.getAcroFields().setField("eyewearReading", "Yes");
			String physicalAnswer[] = form.getPhysicalAnswer();
			String physicalAnswerDetail[] = form.getPhysicalAnswerDetails();
			for (int index = 0; index < 5; index++)
				stamper.getAcroFields().setField("pq" + (index + 1) + "Desc",
						physicalAnswerDetail[index]);

			// Application Page 4
			String mentalAnswer[] = form.getMentalAnswer();
			String mentalAnswerDate[] = form.getMentalAnswerDate();
			String mentalAnswerDetail[] = form.getMentalAnswerDetails();
			count = 1;
			for (int index = 0; index < 6; index++) {
				if ("Yes".equals(mentalAnswer[index]))
					stamper.getAcroFields().setField("mq" + count + "Yes",
							"Yes");
				else
					stamper.getAcroFields()
							.setField("mq" + count + "No", "Yes");
				stamper.getAcroFields().setField("mq" + count + "Dates",
						mentalAnswerDate[index]);
				stamper.getAcroFields().setField("mq" + count + "Desc",
						mentalAnswerDetail[index]);
				count++;
			}

			if ("Less than 2 weeks".equals(intake.getHomelessTime()))
				stamper.getAcroFields().setField("homeless1", "Yes");
			if ("2 weeks to 1 month".equals(intake.getHomelessTime()))
				stamper.getAcroFields().setField("homeless2", "Yes");
			if ("1 to 3 months".equals(intake.getHomelessTime()))
				stamper.getAcroFields().setField("homeless3", "Yes");
			if ("3 months to 1 year".equals(intake.getHomelessTime()))
				stamper.getAcroFields().setField("homeless4", "Yes");
			if ("more than 1 year".equals(intake.getHomelessTime()))
				stamper.getAcroFields().setField("homeless5", "Yes");
			if ("Never".equals(intake.getHomelessHowOften()))
				stamper.getAcroFields().setField("homeless6", "Yes");
			if ("1 to 2 times".equals(intake.getHomelessHowOften()))
				stamper.getAcroFields().setField("homeless7", "Yes");
			if ("more than 2 times in 2 years".equals(intake
					.getHomelessHowOften()))
				stamper.getAcroFields().setField("homeless8", "Yes");
			if ("Long Term".equals(intake.getHomelessHowOften()))
				stamper.getAcroFields().setField("homeless9", "Yes");

			if ("Lack of a fixed, regular and adequate night time residence."
					.equals(intake.getHomelessReason()))
				stamper.getAcroFields().setField("reason1", "Yes");
			if ("Primary night time residence is a shelter designed to provide temporary living accomodations (including welfar hotels, congregate shelters, and transitional housing for the mentally ill)."
					.equals(intake.getHomelessReason()))
				stamper.getAcroFields().setField("reason2", "Yes");
			if ("Primary night time residence is an institution that provides a temporary residence for individuals intended to be institutionalized."
					.equals(intake.getHomelessReason()))
				stamper.getAcroFields().setField("reason3", "Yes");
			if ("Primary night time residence is a public or private place not designated for, or ordinarily used as a regular sleeping accomodation for human beings."
					.equals(intake.getHomelessReason()))
				stamper.getAcroFields().setField("reason4", "Yes");

			// Application 5
			if ("Yes".equals(intake.getIndustrialInjuryFlag()))
				stamper.getAcroFields().setField("injuryYes", "Yes");
			else
				stamper.getAcroFields().setField("injuryNo", "Yes");
			stamper.getAcroFields().setField("injuryDate",
					intake.getIndustrialInjuryDate());
			stamper.getAcroFields().setField("injuryReason",
					intake.getIndustrialInjuryReason());
			stamper.getAcroFields().setField("injuryWhere",
					intake.getIndustrialInjuryLocation());
			stamper.getAcroFields().setField("injuryEmployer",
					intake.getIndustrialInjuryEmployer());

			if ("Yes".equals(intake.getDisabilityFlag()))
				stamper.getAcroFields().setField("disabilityYes", "Yes");
			else
				stamper.getAcroFields().setField("disabilityNo", "Yes");
			stamper.getAcroFields().setField("disability",
					intake.getDisabilityDetails());
			stamper.getAcroFields().setField("lastExam",
					intake.getDisabilityExaminationDate());
			stamper.getAcroFields().setField("physician",
					intake.getDisabilityPhysician());
			stamper.getAcroFields().setField("physicianAddress",
					intake.getDisabilityPhysicianAddress());

			List<MedicalCondition> medicalConditions = form
					.getMedicalConditions();
			String medicalCondition[] = form.getMedicalCondition();
			count = 0;
			for (int i = 0; i < 34; i++) {
				if ("Yes".equals(medicalCondition[count]))
					stamper.getAcroFields().setField("mc" + (count + 1) + "Y",
							"Yes");
				else
					stamper.getAcroFields().setField("mc" + (count + 1) + "N",
							"Yes");
				count++;
			}

			if ("Yes".equals(intake.getHerniaOperationFlag()))
				stamper.getAcroFields().setField("herniaYes", "Yes");
			else
				stamper.getAcroFields().setField("herniaNo", "Yes");
			stamper.getAcroFields().setField("herniaSide",
					intake.getHerniaSide());
			stamper.getAcroFields().setField("mcdetails",
					intake.getMedicalConditionDetails());

			if ("Yes".equals(intake.getLawsuitFlag()))
				stamper.getAcroFields().setField("sueY", "Yes");
			else
				stamper.getAcroFields().setField("sueN", "Yes");
			stamper.getAcroFields().setField("sueDetails",
					intake.getLawsuitDetails());

			if ("Yes".equals(intake.getCurrentLawsuitFlag()))
				stamper.getAcroFields().setField("lawsuitY", "Yes");
			else
				stamper.getAcroFields().setField("lawsuitN", "Yes");
			stamper.getAcroFields().setField("lawsuitDetails",
					intake.getCurrentLawsuitDetails());

			if ("Yes".equals(intake.getFelonyFlag()))
				stamper.getAcroFields().setField("felonyY", "Yes");
			else
				stamper.getAcroFields().setField("felonyN", "Yes");
			stamper.getAcroFields().setField("felonyDetails",
					intake.getFelonyDetails());
			stamper.getAcroFields().setField("felonyqty",
					intake.getFelonyQty() + "");

			if ("Yes".equals(intake.getSexualOffenseFlag()))
				stamper.getAcroFields().setField("sexYes", "Yes");
			else
				stamper.getAcroFields().setField("sexNo", "Yes");
			stamper.getAcroFields().setField("sexDetails",
					intake.getSexualOffenseDetails());
			stamper.getAcroFields().setField("sexqty",
					intake.getSexualOffenseQty());

			if ("Yes".equals(intake.getProbationFlag()))
				stamper.getAcroFields().setField("probationYes", "Yes");
			else
				stamper.getAcroFields().setField("probationNo", "Yes");
			stamper.getAcroFields().setField("probationCounty",
					intake.getProbationCounty());
			stamper.getAcroFields().setField("probationOfficer",
					intake.getProbationOfficer());
			stamper.getAcroFields().setField("probationPhone",
					intake.getProbationOfficerPhone());

			if ("Yes".equals(intake.getProbationApptFlag()))
				stamper.getAcroFields().setField("probationApptYes", "Yes");
			else
				stamper.getAcroFields().setField("probationApptNo", "Yes");
			stamper.getAcroFields().setField("probationDate",
					intake.getProbationApptDetails());

			// Application Page7
			List<JobSkill> works = form.getJobSkills();
			String work[] = form.getWorkExperience();
			count = 1;
			for (int i = 0; i < 25; i++) {
				if ("Yes".equals(work[i]))
					stamper.getAcroFields().setField("job" + count, "Yes");
				count++;
			}
			stamper.getAcroFields().setField("jobOther",
					intake.getOtherJobSkill());

			stamper.getAcroFields().setField("employer1",
					intake.getEmployer1Name());
			stamper.getAcroFields().setField("employerContact1",
					intake.getEmployer1Contact());
			stamper.getAcroFields().setField("employerDate1",
					intake.getEmployer1Dates());
			stamper.getAcroFields().setField("employerPhone1",
					intake.getEmployer1Phone());
			stamper.getAcroFields().setField("employer2",
					intake.getEmployer2Name());
			stamper.getAcroFields().setField("employerContact2",
					intake.getEmployer2Contact());
			stamper.getAcroFields().setField("employerDate2",
					intake.getEmployer2Dates());
			stamper.getAcroFields().setField("employerPhone2",
					intake.getEmployer2Phone());
			stamper.getAcroFields().setField("employer3",
					intake.getEmployer3Name());
			stamper.getAcroFields().setField("employerContact3",
					intake.getEmployer3Contact());
			stamper.getAcroFields().setField("employerDate3",
					intake.getEmployer3Dates());
			stamper.getAcroFields().setField("employerPhone3",
					intake.getEmployer3Phone());
			stamper.getAcroFields().setField("employer4",
					intake.getEmployer4Name());
			stamper.getAcroFields().setField("employerContact4",
					intake.getEmployer4Contact());
			stamper.getAcroFields().setField("employerDate4",
					intake.getEmployer4Dates());
			stamper.getAcroFields().setField("employerPhone41",
					intake.getEmployer4Phone());

			// All Applicant Print Name and Date fields
			stamper.getAcroFields().setField("txtApplicantName", applicantName);
			stamper.getAcroFields()
					.setField("txtApplicantName1", applicantName);
			stamper.getAcroFields()
					.setField("txtApplicantName2", applicantName);
			stamper.getAcroFields()
					.setField("txtApplicantName3", applicantName);
			stamper.getAcroFields()
					.setField("txtApplicantName4", applicantName);
			stamper.getAcroFields()
					.setField("txtApplicantName5", applicantName);
			stamper.getAcroFields()
					.setField("txtApplicantName6", applicantName);
			stamper.getAcroFields()
					.setField("txtApplicantName7", applicantName);
			stamper.getAcroFields()
					.setField("txtApplicantName8", applicantName);
			stamper.getAcroFields()
					.setField("txtApplicantName9", applicantName);
			stamper.getAcroFields().setField("txtApplicantName10",
					applicantName);
			stamper.getAcroFields().setField("txtApplicantName11",
					applicantName);
			stamper.getAcroFields().setField("txtApplicantName12",
					applicantName);
			stamper.getAcroFields().setField("txtApplicantName13",
					applicantName);
			stamper.getAcroFields().setField("txtApplicantName14",
					applicantName);
			stamper.getAcroFields().setField("txtApplicantName15",
					applicantName);

			String intakeOfficer="";
			
			if ("Fort Lauderdale".equals(intake.getFarmBase())) 
				intakeOfficer="Sammie Johnson";
			else if ("Boynton Beach".equals(intake.getFarmBase())) 
				intakeOfficer="Robbin Juris-Dick";
			else if ("Okeechobee".equals(intake.getFarmBase())) {
				intakeOfficer="";
				currentDate="";
			}
			else if ("Victoria Andres".equals(intake.getFarmBase())) 
				intakeOfficer="Victoria Andres";			
				
			stamper.getAcroFields().setField("txtIntakeOfficerName1", intakeOfficer);
			stamper.getAcroFields().setField("txtIntakeOfficerName2", intakeOfficer);
			stamper.getAcroFields().setField("txtIntakeOfficerName3", intakeOfficer);
			stamper.getAcroFields().setField("txtIntakeOfficerName4", intakeOfficer);
			stamper.getAcroFields().setField("txtIntakeOfficerName5", intakeOfficer);
			
			stamper.getAcroFields().setField("txtDate1", currentDate);
			stamper.getAcroFields().setField("txtDate2", currentDate);
			stamper.getAcroFields().setField("txtDate3", currentDate);
			stamper.getAcroFields().setField("txtDate4", currentDate);
			stamper.getAcroFields().setField("txtDate5", currentDate);
			stamper.getAcroFields().setField("txtDate6", currentDate);
			stamper.getAcroFields().setField("txtDate7", currentDate);
			stamper.getAcroFields().setField("txtDate8", currentDate);
			stamper.getAcroFields().setField("txtDate9", currentDate);
			stamper.getAcroFields().setField("txtDate10", currentDate);
			stamper.getAcroFields().setField("txtDate11", currentDate);
			stamper.getAcroFields().setField("txtDate12", currentDate);
			stamper.getAcroFields().setField("txtDate13", currentDate);
			stamper.getAcroFields().setField("txtDate14", currentDate);
			stamper.getAcroFields().setField("txtDate15", currentDate);
			stamper.getAcroFields().setField("txtDate16", currentDate);
			stamper.getAcroFields().setField("txtDate17", currentDate);
			stamper.getAcroFields().setField("txtDate18", currentDate);
			stamper.getAcroFields().setField("txtDate19", currentDate);
			stamper.getAcroFields().setField("txtDate20", currentDate);
			stamper.getAcroFields().setField("txtDate21", currentDate);

			stamper.close();
			// document.close();
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (IOException io) {
			io.printStackTrace();
		}
	}

	public void classListPdf(SystemUser user, String farm,
			HttpServletResponse response) {

		List<Intake> class0List = new ArrayList<Intake>();
		int totalEnrolled=0;
		
		response.setContentType("application/pdf");
		Document document = new Document();
		try {
			PdfWriter writer = PdfWriter.getInstance(document,
					response.getOutputStream());

			document.setMargins(10, 10, 10, 10);
			document.open();
			String currentDate = Validator.convertDate(new java.util.Date()
					+ "");

			Font cellFont = FontFactory.getFont(FontFactory.HELVETICA, 8);
			Font headerFont = FontFactory.getFont(FontFactory.HELVETICA, 10);
			Font titleFont = FontFactory
					.getFont(FontFactory.HELVETICA_BOLD, 14);
			Font dateFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 6);

			headerFont.setStyle(Font.BOLD);

			Chunk titleChunk = new Chunk("Class List for " + farm);
			titleFont.setStyle(Font.BOLD);
			titleChunk.setFont(titleFont);
			document.add(titleChunk);

			Chunk dateChunk = new Chunk("Run on " + currentDate);
			dateFont.setStyle(Font.ITALIC);
			dateChunk.setFont(dateFont);
			document.add(dateChunk);

			PdfPTable spacerTbl = new PdfPTable(1);
			PdfPCell spacerCell = new PdfPCell(new Paragraph(""));
			spacerCell.setColspan(1);
			spacerTbl.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			spacerCell.setBorder(Rectangle.NO_BORDER);
			spacerCell.setFixedHeight(20);
			spacerTbl.addCell(spacerCell);
			document.add(spacerTbl);

			IntakeDao dao = new IntakeDao();
			StudentHistoryDao studentHistoryDao=new StudentHistoryDao();
			
			String sClass = "Orientation";

			for (int cls = 0; cls < 11; cls++) {
				if (cls > 0)
					sClass = cls + "";
				if (cls == 7)
					sClass = "Fresh Start";
				if (cls == 8)
					sClass = "Intern";
				if (cls == 9)
					sClass = "SLS";
				if (cls == 10)
					sClass = "Student Teacher";
				class0List = dao.listClass(sClass, farm);
				totalEnrolled+=class0List.size();
				
				PdfPTable htable0 = new PdfPTable(1);
				PdfPCell hcell0 = new PdfPCell(new Paragraph(""));
				hcell0.setColspan(1);
				htable0.getDefaultCell().setBorder(Rectangle.NO_BORDER);
				htable0.getDefaultCell().setBackgroundColor(Color.cyan);
				htable0.setWidthPercentage(100);
				htable0.setHorizontalAlignment(Element.ALIGN_LEFT);
				htable0.addCell(new Phrase("Class: " + sClass + "  ("
						+ class0List.size() + " enrolled)", cellFont));
				document.add(htable0);

				// Table of Students
				PdfPTable table0 = new PdfPTable(8);
				PdfPCell cell0 = new PdfPCell(new Paragraph("column span 4"));
				cell0.setColspan(8);
				table0.getDefaultCell().setBorder(Rectangle.NO_BORDER);
				cell0.setBorder(Rectangle.NO_BORDER);
				table0.setWidthPercentage(100);
				table0.setHorizontalAlignment(Element.ALIGN_LEFT);

				for (Iterator iterator = class0List.iterator(); iterator
						.hasNext();) {
					Intake intake = (Intake) iterator.next();
					
					String fname = intake.getFirstname();
					String lname = intake.getLastname();
					fname = WordUtils.capitalize(fname);
					lname = WordUtils.capitalize(lname);
					table0.addCell(new Phrase(fname + " "
							+ lname, cellFont));
					table0.addCell(new Phrase(intake.getEntryDate(), cellFont));
				}

				int filler = (class0List.size()) % 4;
				for (int i = 0; i < (8 - filler); i++) {
					table0.addCell("");
					table0.addCell("");
				}

				document.add(table0);
			} // for loop for class
			
			
			Chunk totalChunk = new Chunk("Total Enrolled " + totalEnrolled);
			Chunk blank = new Chunk("");
			dateFont.setStyle(Font.BOLD);
			dateChunk.setFont(dateFont);
			document.add(spacerTbl);
			document.add(totalChunk);
			
			document.close();

		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (IOException io) {
			io.printStackTrace();
		}
	}

	public void generateOccupancyReport(SystemUser user, HttpServletResponse response) {
		response.setContentType("application/pdf");
		
		PdfWriter writer = null;
		Document document = new Document();
		document.setMargins(10, 10, 10, 10);
		
		IntakeDao dao = new IntakeDao();
		
		List<Intake> listBYN1=null;
		List<Intake> listBYN2=null;
		List<Intake> listBYN3=null;
		List<Intake> listBYN4=null;
		List<Intake> listBYN5=null;
		List<Intake> listBYN6=null;
		List<Intake> listFTL1=null;
		List<Intake> listFTL2=null;
		List<Intake> listFTL3=null;
		List<Intake> listFTL4=null;
		List<Intake> listFTL5=null;
		List<Intake> listFTL6=null;
		List<Intake> listOKE1=null;
		List<Intake> listOKE2=null;
		List<Intake> listOKE3=null;
		List<Intake> listOKE4=null;
		List<Intake> listOKE5=null;
		List<Intake> listOKE6=null;
		
		double bynBeds=139;
		double okeBeds=108;
		double ftlBeds=130;
		double byn=0.0,ftl=0.0,oke=0.0,tot=0.0;
		try {
			listBYN1 =  dao.listByStatus("In Program", "Boynton Beach");
			listBYN2 =  dao.listByStatus("Left Prop./Graduated to Fresh Start", "Boynton Beach");
			listBYN3 =  dao.listByStatus("Left Prop./Graduated to SLS", "Boynton Beach");
			listBYN4 =  dao.listByStatus("Left Prop./Graduated to Intern", "Boynton Beach");
			listBYN5 =  dao.listByStatus("Left Prop./Graduated to Omega Work", "Boynton Beach");
			listBYN6 =  dao.listByStatus("Left Prop./Graduated to Omega School", "Boynton Beach");
			
			listFTL1 =  dao.listByStatus("In Program", "Fort Lauderdale");
			listFTL2 =  dao.listByStatus("Left Prop./Graduated to Fresh Start", "Fort Lauderdale");
			listFTL3 =  dao.listByStatus("Left Prop./Graduated to SLS", "Fort Lauderdale");
			listFTL4 =  dao.listByStatus("Left Prop./Graduated to Intern", "Fort Lauderdale");
			listFTL5 =  dao.listByStatus("Left Prop./Graduated to Omega Work", "Fort Lauderdale");
			listFTL6 =  dao.listByStatus("Left Prop./Graduated to Omega School", "Fort Lauderdale");

			listOKE1 =  dao.listByStatus("In Program", "Okeechobee");
			listOKE2 =  dao.listByStatus("Left Prop./Graduated to Fresh Start", "Okeechobee");
			listOKE3 =  dao.listByStatus("Left Prop./Graduated to SLS", "Okeechobee");
			listOKE4 =  dao.listByStatus("Left Prop./Graduated to Intern", "Okeechobee");
			listOKE5 =  dao.listByStatus("Left Prop./Graduated to Omega Work", "Okeechobee");
			listOKE6 =  dao.listByStatus("Left Prop./Graduated to Omega School", "Okeechobee");

			byn=((listBYN1.size()+listBYN2.size()+listBYN3.size()+listBYN4.size()+listBYN5.size()+listBYN6.size())/bynBeds)*100.0;
			ftl=((listFTL1.size()+listFTL2.size()+listFTL3.size()+listFTL4.size()+listFTL5.size()+listFTL6.size())/ftlBeds)*100.0;
			oke=((listOKE1.size()+listOKE2.size()+listOKE3.size()+listOKE4.size()+listOKE5.size()+listOKE6.size())/okeBeds)*100.0;
			tot=((listBYN1.size()+listBYN2.size()+listBYN3.size()+listBYN4.size()+listBYN5.size()+listBYN6.size())+
					(listFTL1.size()+listFTL2.size()+listFTL3.size()+listFTL4.size()+listFTL5.size()+listFTL6.size())+
					(listOKE1.size()+listOKE2.size()+listOKE3.size()+listOKE4.size()+listOKE5.size()+listOKE6.size()) )/(bynBeds+ftlBeds+okeBeds)*100.0;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		response.setContentType("application/pdf");
		try {
			writer = PdfWriter.getInstance(document,
					response.getOutputStream());

			document.setMargins(10, 10, 10, 10);
			document.open();
			String currentDate = Validator.convertDate(new java.util.Date()
					+ "");

			// Create a table to server as a spacer
			PdfPTable spacerTbl = new PdfPTable(1);
			PdfPCell spacerCell = new PdfPCell(new Paragraph(""));
			spacerCell.setColspan(1);
			spacerTbl.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			spacerCell.setBorder(Rectangle.NO_BORDER);
			spacerCell.setFixedHeight(20);
			spacerTbl.addCell(spacerCell);

			Font cellFont = FontFactory.getFont(FontFactory.COURIER, 8);
			Font summaryFont = FontFactory.getFont(FontFactory.COURIER, 8);
			Font headerFont = FontFactory.getFont(FontFactory.HELVETICA, 10);
			Font titleFont = FontFactory.getFont(FontFactory.HELVETICA, 14);
			Font dateFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 6);
			Font colHeaderFont = FontFactory.getFont(
					FontFactory.HELVETICA_BOLD, 8);
			colHeaderFont.setStyle(Font.UNDERLINE);
			headerFont.setStyle(Font.BOLD);

			Image img = Image.getInstance(String.format(
					"c:\\pdfFiles\\logo_wordpress_2.png", ""));
			img.scaleToFit(150, 94);
			document.add(new Chunk(img, 0, -90, true));

			// spacer
			document.add(spacerTbl);
			document.add(spacerTbl);
			document.add(spacerTbl);
			document.add(spacerTbl);
			document.add(spacerTbl);
			document.add(spacerTbl);

			Chunk titleChunk = new Chunk("Occupancy Report");
			titleFont.setStyle(Font.BOLD);
			titleChunk.setFont(titleFont);
			document.add(titleChunk);
			document.add(spacerTbl);

			Chunk dateChunk = new Chunk("Run on " + currentDate);
			dateFont.setStyle(Font.ITALIC);
			dateChunk.setFont(dateFont);
			document.add(dateChunk);

			// spacer
			document.add(spacerTbl);

			
			// Table of Applicants
			PdfPTable table0 = new PdfPTable(new float[] { 1 ,1, 1, 1, 1, 1, 1, 1, 1 });
			PdfPCell cell0 = new PdfPCell(new Paragraph("column span 3"));
			cell0.setColspan(9);
			table0.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			cell0.setBorder(Rectangle.NO_BORDER);
			table0.setWidthPercentage(100f);
			table0.setHorizontalAlignment(Element.ALIGN_LEFT);
			
			table0.addCell(new Phrase("Farm", colHeaderFont));
			table0.addCell(new Phrase("In Program", colHeaderFont));
			table0.addCell(new Phrase("Fresh Start", colHeaderFont));
			table0.addCell(new Phrase("SLS", colHeaderFont));
			table0.addCell(new Phrase("Intern", colHeaderFont));
			table0.addCell(new Phrase("Omega Work", colHeaderFont));
			table0.addCell(new Phrase("Omega School", colHeaderFont));
			table0.addCell(new Phrase("Total Beds", colHeaderFont));
			table0.addCell(new Phrase("%", colHeaderFont));
			
			DecimalFormat df = new DecimalFormat("###.##");
			
			table0.addCell(new Phrase("BYN", cellFont));
			table0.addCell(new Phrase(listBYN1.size()+"", cellFont));
			table0.addCell(new Phrase(listBYN2.size()+"", cellFont));
			table0.addCell(new Phrase(listBYN3.size()+"", cellFont));
			table0.addCell(new Phrase(listBYN4.size()+"", cellFont));
			table0.addCell(new Phrase(listBYN5.size()+"", cellFont));
			table0.addCell(new Phrase(listBYN6.size()+"", cellFont));
			table0.addCell(new Phrase("139", cellFont));
			table0.addCell(new Phrase(df.format(byn)+"%", cellFont));

			table0.addCell(new Phrase("FTL", cellFont));
			table0.addCell(new Phrase(listFTL1.size()+"", cellFont));
			table0.addCell(new Phrase(listFTL2.size()+"", cellFont));
			table0.addCell(new Phrase(listFTL3.size()+"", cellFont));
			table0.addCell(new Phrase(listFTL4.size()+"", cellFont));
			table0.addCell(new Phrase(listFTL5.size()+"", cellFont));
			table0.addCell(new Phrase(listFTL6.size()+"", cellFont));
			table0.addCell(new Phrase("135", cellFont));
			table0.addCell(new Phrase(df.format(ftl)+"%", cellFont));

			table0.addCell(new Phrase("OKE", cellFont));
			table0.addCell(new Phrase(listOKE1.size()+"", cellFont));
			table0.addCell(new Phrase(listOKE2.size()+"", cellFont));
			table0.addCell(new Phrase(listOKE3.size()+"", cellFont));
			table0.addCell(new Phrase(listOKE4.size()+"", cellFont));
			table0.addCell(new Phrase(listOKE5.size()+"", cellFont));
			table0.addCell(new Phrase(listOKE6.size()+"", cellFont));
			table0.addCell(new Phrase("108", cellFont));
			table0.addCell(new Phrase(df.format(oke)+"%", cellFont));

			summaryFont.setStyle(Font.BOLD);
			table0.addCell(new Phrase("TOTAL", summaryFont));
			table0.addCell(new Phrase(listBYN1.size()+listFTL1.size()+listOKE1.size()+"", summaryFont));
			table0.addCell(new Phrase(listBYN2.size()+listFTL2.size()+listOKE2.size()+"", summaryFont));
			table0.addCell(new Phrase(listBYN3.size()+listFTL3.size()+listOKE3.size()+"", summaryFont));
			table0.addCell(new Phrase(listBYN4.size()+listFTL4.size()+listOKE4.size()+"", summaryFont));
			table0.addCell(new Phrase(listBYN5.size()+listFTL5.size()+listOKE5.size()+"", summaryFont));
			table0.addCell(new Phrase(listBYN6.size()+listFTL6.size()+listOKE6.size()+"", summaryFont));
			table0.addCell(new Phrase("352", summaryFont));
			table0.addCell(new Phrase(df.format(tot)+"%", summaryFont));

			
			document.add(table0);
			document.close();
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (IOException io) {
			io.printStackTrace();
		}

	}
	
	public void generateCompletionReport(SystemUser user, HttpServletResponse response) {
		response.setContentType("application/pdf");
		
		PdfWriter writer = null;
		Document document = new Document();
		document.setMargins(10, 10, 10, 10);
		
		IntakeDao dao = new IntakeDao();
		
		List<Intake> listBYN1=null;
		List<Intake> listBYN2=null;
		List<Intake> listBYN3=null;
		List<Intake> listBYN4=null;
		List<Intake> listBYN5=null;
		List<Intake> listBYN6=null;
		List<Intake> listBYN7=null;
		List<Intake> listBYN8=null;
		List<Intake> listBYN9=null;
		List<Intake> listBYN10=null;
		List<Intake> listBYN11=null;
		List<Intake> listBYN12=null;
		List<Intake> listBYN13=null;
		List<Intake> listFTL1=null;
		List<Intake> listFTL2=null;
		List<Intake> listFTL3=null;
		List<Intake> listFTL4=null;
		List<Intake> listFTL5=null;
		List<Intake> listFTL6=null;
		List<Intake> listFTL7=null;
		List<Intake> listFTL8=null;
		List<Intake> listFTL9=null;
		List<Intake> listFTL10=null;
		List<Intake> listFTL11=null;
		List<Intake> listFTL12=null;
		List<Intake> listFTL13=null;
		List<Intake> listOKE1=null;
		List<Intake> listOKE2=null;
		List<Intake> listOKE3=null;
		List<Intake> listOKE4=null;
		List<Intake> listOKE5=null;
		List<Intake> listOKE6=null;
		List<Intake> listOKE7=null;
		List<Intake> listOKE8=null;
		List<Intake> listOKE9=null;
		List<Intake> listOKE10=null;
		List<Intake> listOKE11=null;
		List<Intake> listOKE12=null;
		List<Intake> listOKE13=null;
		
			
		int bynFailure=0,bynSuccess=0, ftlFailure=0, ftlSuccess=0, okeFailure=0, okeSuccess=0;
		double okeBeds=108;
		double ftlBeds=135;
		double byn=0.0,ftl=0.0,oke=0.0,tot=0.0;
		
		response.setContentType("application/pdf");
		try {
			writer = PdfWriter.getInstance(document,
					response.getOutputStream());

			document.setMargins(10, 10, 10, 10);
			document.open();
			String currentDate = Validator.convertDate(new java.util.Date()
					+ "");

			// Create a table to server as a spacer
			PdfPTable spacerTbl = new PdfPTable(1);
			PdfPCell spacerCell = new PdfPCell(new Paragraph(""));
			spacerCell.setColspan(1);
			spacerTbl.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			spacerCell.setBorder(Rectangle.NO_BORDER);
			spacerCell.setFixedHeight(20);
			spacerTbl.addCell(spacerCell);

			Font cellFont = FontFactory.getFont(FontFactory.COURIER, 10);
			Font summaryFont = FontFactory.getFont(FontFactory.COURIER, 10);
			Font headerFont = FontFactory.getFont(FontFactory.HELVETICA, 10);
			Font titleFont = FontFactory.getFont(FontFactory.HELVETICA, 14);
			Font dateFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 6);
			Font colHeaderFont = FontFactory.getFont(
					FontFactory.HELVETICA_BOLD, 8);
			colHeaderFont.setStyle(Font.UNDERLINE);
			headerFont.setStyle(Font.BOLD);

			Image img = Image.getInstance(String.format(
					"c:\\pdfFiles\\logo_wordpress_2.png", ""));
			img.scaleToFit(150, 94);
			document.add(new Chunk(img, 0, -90, true));

			// spacer
			document.add(spacerTbl);
			document.add(spacerTbl);
			document.add(spacerTbl);
			document.add(spacerTbl);
			document.add(spacerTbl);
			document.add(spacerTbl);

			Chunk titleChunk = new Chunk("Resident Status Report");
			titleFont.setStyle(Font.BOLD);
			titleChunk.setFont(titleFont);
			document.add(titleChunk);
			document.add(spacerTbl);

			Chunk dateChunk = new Chunk("Run on " + currentDate);
			dateFont.setStyle(Font.ITALIC);
			dateChunk.setFont(dateFont);
			document.add(dateChunk);

			// spacer
			document.add(spacerTbl);

			
			String farm="";
					
				farm="Boynton Beach";
				listBYN1 =  dao.search(null, null,null, null, null, null, farm, null, null,"Walked Off",null,null,null,null,null);
				listBYN2 =  dao.search(null, null,null, null, null, null, farm, null, null,"Dismissed",null,null,null,null,null);
				listBYN3 =  dao.search(null, null,null, null, null, null, farm, null, null,"Left Prop./Did Not Graduate",null,null,null,null,null);
				listBYN4 =  dao.search(null, null,null, null, null, null, farm, null, null,"Dissmissed - Banned 30 Days",null,null,null,null,null);
				listBYN5 =  dao.search(null, null,null, null, null, null, farm, null, null,"Dissmissed - Banned 60 Days",null,null,null,null,null);
				listBYN6 =  dao.search(null, null,null, null, null, null, farm, null, null,"Dissmissed - Banned 90 Days",null,null,null,null,null);
				listBYN7 =  dao.search(null, null,null, null, null, null, farm, null, null,"Dissmissed - Banned Perm.",null,null,null,null,null);
				bynFailure = listBYN1.size()+listBYN2.size()+listBYN3.size()+listBYN4.size()+listBYN5.size()+listBYN6.size()+listBYN7.size();
				
				listBYN8 =  dao.search(null, null,null, null, null, null, farm, null, null,"Left Prop./Graduated",null,null,null,null,null);
				listBYN9 =  dao.search(null, null,null, null, null, null, farm, null, null,"Left Prop./Graduated to Fresh Start",null,null,null,null,null);
				listBYN10 =  dao.search(null, null,null, null, null, null, farm, null, null,"Left Prop./Graduated to SLS",null,null,null,null,null);
				listBYN11 =  dao.search(null, null,null, null, null, null, farm, null, null,"Left Prop./Graduated to Intern",null,null,null,null,null);
				listBYN12 =  dao.search(null, null,null, null, null, null, farm, null, null,"Left Prop./Graduated to Omega Work",null,null,null,null,null);
				listBYN13 =  dao.search(null, null,null, null, null, null, farm, null, null,"Left Prop./Graduated to Omega School",null,null,null,null,null);
				bynSuccess = listBYN8.size()+listBYN9.size()+listBYN10.size()+listBYN11.size()+listBYN12.size()+listBYN13.size();
				
				farm="Fort Lauderdale";
				listFTL1 =  dao.search(null, null,null, null, null, null, farm, null, null,"Walked Off",null,null,null,null,null);
				listFTL2 =  dao.search(null, null,null, null, null, null, farm, null, null,"Dismissed",null,null,null,null,null);
				listFTL3 =  dao.search(null, null,null, null, null, null, farm, null, null,"Left Prop./Did Not Graduate",null,null,null,null,null);
				listFTL4 =  dao.search(null, null,null, null, null, null, farm, null, null,"Dissmissed - Banned 30 Days",null,null,null,null,null);
				listFTL5 =  dao.search(null, null,null, null, null, null, farm, null, null,"Dissmissed - Banned 60 Days",null,null,null,null,null);
				listFTL6 =  dao.search(null, null,null, null, null, null, farm, null, null,"Dissmissed - Banned 90 Days",null,null,null,null,null);
				listFTL7 =  dao.search(null, null,null, null, null, null, farm, null, null,"Dissmissed - Banned Perm.",null,null,null,null,null);
				ftlFailure = listFTL1.size()+listFTL2.size()+listFTL3.size()+listFTL4.size()+listFTL5.size()+listFTL6.size()+listFTL7.size();

				listFTL8 =  dao.search(null, null,null, null, null, null, farm, null, null,"Left Prop./Graduated",null,null,null,null,null);
				listFTL9 =  dao.search(null, null,null, null, null, null, farm, null, null,"Left Prop./Graduated to Fresh Start",null,null,null,null,null);
				listFTL10 =  dao.search(null, null,null, null, null, null, farm, null, null,"Left Prop./Graduated to SLS",null,null,null,null,null);
				listFTL11 =  dao.search(null, null,null, null, null, null, farm, null, null,"Left Prop./Graduated to Intern",null,null,null,null,null);
				listFTL12 =  dao.search(null, null,null, null, null, null, farm, null, null,"Left Prop./Graduated to Omega Work",null,null,null,null,null);
				listFTL13 =  dao.search(null, null,null, null, null, null, farm, null, null,"Left Prop./Graduated to Omega School",null,null,null,null,null);
				ftlSuccess = listFTL8.size()+listFTL9.size()+listFTL10.size()+listFTL11.size()+listFTL12.size()+listFTL13.size();

				farm="Okeechobee";
				listOKE1 =  dao.search(null, null,null, null, null, null, farm, null, null,"Walked Off",null,null,null,null,null);
				listOKE2 =  dao.search(null, null,null, null, null, null, farm, null, null,"Dismissed",null,null,null,null,null);
				listOKE3 =  dao.search(null, null,null, null, null, null, farm, null, null,"Left Prop./Did Not Graduate",null,null,null,null,null);
				listOKE4 =  dao.search(null, null,null, null, null, null, farm, null, null,"Dissmissed - Banned 30 Days",null,null,null,null,null);
				listOKE5 =  dao.search(null, null,null, null, null, null, farm, null, null,"Dissmissed - Banned 60 Days",null,null,null,null,null);
				listOKE6 =  dao.search(null, null,null, null, null, null, farm, null, null,"Dissmissed - Banned 90 Days",null,null,null,null,null);
				listOKE7 =  dao.search(null, null,null, null, null, null, farm, null, null,"Dissmissed - Banned Perm.",null,null,null,null,null);
				okeFailure = listOKE1.size()+listOKE2.size()+listOKE3.size()+listOKE4.size()+listOKE5.size()+listOKE6.size()+listOKE7.size();

				listOKE8 =  dao.search(null, null,null, null, null, null, farm, null, null,"Left Prop./Graduated",null,null,null,null,null);
				listOKE9 =  dao.search(null, null,null, null, null, null, farm, null, null,"Left Prop./Graduated to Fresh Start",null,null,null,null,null);
				listOKE10 =  dao.search(null, null,null, null, null, null, farm, null, null,"Left Prop./Graduated to SLS",null,null,null,null,null);
				listOKE11 =  dao.search(null, null,null, null, null, null, farm, null, null,"Left Prop./Graduated to Intern",null,null,null,null,null);
				listOKE12 =  dao.search(null, null,null, null, null, null, farm, null, null,"Left Prop./Graduated to Omega Work",null,null,null,null,null);
				listOKE13 =  dao.search(null, null,null, null, null, null, farm, null, null,"Left Prop./Graduated to Omega School",null,null,null,null,null);
				okeSuccess = listOKE8.size()+listOKE9.size()+listOKE10.size()+listOKE11.size()+listOKE12.size()+listOKE13.size();

				PdfPTable table0 = new PdfPTable(new float[] { 2 ,1, 1, 1, 1  });
				table0.getDefaultCell().setBorder(Rectangle.NO_BORDER);
				table0.setWidthPercentage(100f);
				table0.setHorizontalAlignment(Element.ALIGN_LEFT);

				table0.addCell(new Phrase("STATUS", colHeaderFont));
				table0.addCell(new Phrase("BYN", colHeaderFont));
				table0.addCell(new Phrase("FTL", colHeaderFont));
				table0.addCell(new Phrase("OKE", colHeaderFont));
				table0.addCell(new Phrase("TOTAL", colHeaderFont));
				
				DecimalFormat df = new DecimalFormat("###.##");
				
				table0.addCell(new Phrase("Walked Off", cellFont));
				table0.addCell(new Phrase(listBYN1.size()+"", cellFont));
				table0.addCell(new Phrase(listFTL1.size()+"", cellFont));
				table0.addCell(new Phrase(listOKE1.size()+"", cellFont));
				table0.addCell(new Phrase(listBYN1.size()+listFTL1.size()+listOKE1.size()+"", summaryFont));
				table0.addCell(new Phrase("Dismissed", cellFont));
				table0.addCell(new Phrase(listBYN2.size()+"", cellFont));
				table0.addCell(new Phrase(listFTL2.size()+"", cellFont));
				table0.addCell(new Phrase(listOKE2.size()+"", cellFont));
				table0.addCell(new Phrase(listBYN2.size()+listFTL2.size()+listOKE2.size()+"", summaryFont));
				table0.addCell(new Phrase("Left Prop./Did Not Graduate", cellFont));
				table0.addCell(new Phrase(listBYN3.size()+"", cellFont));
				table0.addCell(new Phrase(listFTL3.size()+"", cellFont));
				table0.addCell(new Phrase(listOKE3.size()+"", cellFont));
				table0.addCell(new Phrase(listBYN3.size()+listFTL3.size()+listOKE3.size()+"", summaryFont));
				table0.addCell(new Phrase("Dissmissed - Banned 30 Days", cellFont));
				table0.addCell(new Phrase(listBYN4.size()+"", cellFont));
				table0.addCell(new Phrase(listFTL4.size()+"", cellFont));
				table0.addCell(new Phrase(listOKE4.size()+"", cellFont));
				table0.addCell(new Phrase(listBYN4.size()+listFTL4.size()+listOKE4.size()+"", summaryFont));
				table0.addCell(new Phrase("Dissmissed - Banned 60 Days", cellFont));
				table0.addCell(new Phrase(listBYN5.size()+"", cellFont));
				table0.addCell(new Phrase(listFTL5.size()+"", cellFont));
				table0.addCell(new Phrase(listOKE5.size()+"", cellFont));
				table0.addCell(new Phrase(listBYN5.size()+listFTL5.size()+listOKE5.size()+"", summaryFont));
				table0.addCell(new Phrase("Dissmissed - Banned 90 Days", cellFont));
				table0.addCell(new Phrase(listBYN6.size()+"", cellFont));
				table0.addCell(new Phrase(listFTL6.size()+"", cellFont));
				table0.addCell(new Phrase(listOKE6.size()+"", cellFont));
				table0.addCell(new Phrase(listBYN6.size()+listFTL6.size()+listOKE6.size()+"", summaryFont));
				table0.addCell(new Phrase("Dissmissed - Banned Perm.", cellFont));
				table0.addCell(new Phrase(listBYN7.size()+"", cellFont));
				table0.addCell(new Phrase(listFTL7.size()+"", cellFont));
				table0.addCell(new Phrase(listOKE7.size()+"", cellFont));
				table0.addCell(new Phrase(listBYN7.size()+listFTL7.size()+listOKE7.size()+"", summaryFont));
				summaryFont.setStyle(Font.BOLD);
				table0.addCell(new Phrase("Total Not Completed", summaryFont));
				table0.addCell(new Phrase(bynFailure+"", summaryFont));
				table0.addCell(new Phrase(ftlFailure+"", summaryFont));
				table0.addCell(new Phrase(okeFailure+"", summaryFont));
				table0.addCell(new Phrase(bynFailure+ftlFailure+okeFailure+"", summaryFont));
				
				double dByn=0.0, dFtl=0.0, dOke=0.0,dAll=0.0;
				dByn=(new Double(bynFailure)/(bynFailure+bynSuccess))*100.00;
				dFtl=(new Double(ftlFailure)/(ftlFailure+ftlSuccess))*100.00;
				dOke=(new Double(okeFailure)/(okeFailure+okeSuccess))*100.00;				
				dAll=(new Double(bynFailure+ftlFailure+okeFailure)/( (bynFailure+bynSuccess)+(ftlFailure+ftlSuccess)+(okeFailure+okeSuccess) ))*100.00;				
				
				table0.addCell(new Phrase("%", summaryFont));
				table0.addCell(new Phrase( df.format(dByn)+"%", summaryFont));
				table0.addCell(new Phrase( df.format(dFtl)+"%", summaryFont));
				table0.addCell(new Phrase( df.format(dOke)+"%", summaryFont));
				table0.addCell(new Phrase( df.format(dAll)+"%", summaryFont));
				

				PdfPTable table1 = new PdfPTable(new float[] { 2 ,1, 1, 1, 1  });
				table1.getDefaultCell().setBorder(Rectangle.NO_BORDER);
				table1.setWidthPercentage(100f);
				table1.setHorizontalAlignment(Element.ALIGN_LEFT);
				
				table1.addCell(new Phrase("Graduated", cellFont));
				table1.addCell(new Phrase(listBYN8.size()+"", cellFont));
				table1.addCell(new Phrase(listFTL8.size()+"", cellFont));
				table1.addCell(new Phrase(listOKE8.size()+"", cellFont));
				table1.addCell(new Phrase(listBYN8.size()+listFTL8.size()+listOKE8.size()+"", summaryFont));
				table1.addCell(new Phrase("Graduated to Fresh Start", cellFont));
				table1.addCell(new Phrase(listBYN9.size()+"", cellFont));
				table1.addCell(new Phrase(listFTL9.size()+"", cellFont));
				table1.addCell(new Phrase(listOKE9.size()+"", cellFont));
				table1.addCell(new Phrase(listBYN9.size()+listFTL9.size()+listOKE9.size()+"", summaryFont));
				table1.addCell(new Phrase("Graduated to SLS", cellFont));
				table1.addCell(new Phrase(listBYN10.size()+"", cellFont));
				table1.addCell(new Phrase(listFTL10.size()+"", cellFont));
				table1.addCell(new Phrase(listOKE10.size()+"", cellFont));
				table1.addCell(new Phrase(listBYN10.size()+listFTL10.size()+listOKE10.size()+"", summaryFont));
				table1.addCell(new Phrase("Graduated to Intern", cellFont));
				table1.addCell(new Phrase(listBYN11.size()+"", cellFont));
				table1.addCell(new Phrase(listFTL11.size()+"", cellFont));
				table1.addCell(new Phrase(listOKE11.size()+"", cellFont));
				table1.addCell(new Phrase(listBYN11.size()+listFTL11.size()+listOKE11.size()+"", summaryFont));
				table1.addCell(new Phrase("Graduated to Omega Work", cellFont));
				table1.addCell(new Phrase(listBYN12.size()+"", cellFont));
				table1.addCell(new Phrase(listFTL12.size()+"", cellFont));
				table1.addCell(new Phrase(listOKE12.size()+"", cellFont));
				table1.addCell(new Phrase(listBYN12.size()+listFTL12.size()+listOKE12.size()+"", summaryFont));
				table1.addCell(new Phrase("Graduated to Omega School", cellFont));
				table1.addCell(new Phrase(listBYN13.size()+"", cellFont));
				table1.addCell(new Phrase(listFTL13.size()+"", cellFont));
				table1.addCell(new Phrase(listOKE13.size()+"", cellFont));
				table1.addCell(new Phrase(listBYN13.size()+listFTL13.size()+listOKE13.size()+"", summaryFont));
				table1.addCell(new Phrase("Total Completed", summaryFont));
				table1.addCell(new Phrase(bynSuccess+"", summaryFont));
				table1.addCell(new Phrase(ftlSuccess+"", summaryFont));
				table1.addCell(new Phrase(okeSuccess+"", summaryFont));
				table1.addCell(new Phrase(bynSuccess+ftlSuccess+okeSuccess+"", summaryFont));

				dByn=(new Double(bynSuccess)/(bynFailure+bynSuccess))*100.00;
				dFtl=(new Double(ftlSuccess)/(ftlFailure+ftlSuccess))*100.00;
				dOke=(new Double(okeSuccess)/(okeFailure+okeSuccess))*100.00;				
				dAll=(new Double(bynSuccess+ftlSuccess+okeSuccess)/( (bynFailure+bynSuccess)+(ftlFailure+ftlSuccess)+(okeFailure+okeSuccess) ))*100.00;				
				
				table1.addCell(new Phrase("%", summaryFont));
				table1.addCell(new Phrase( df.format(dByn)+"%", summaryFont));
				table1.addCell(new Phrase( df.format(dFtl)+"%", summaryFont));
				table1.addCell(new Phrase( df.format(dOke)+"%", summaryFont));
				table1.addCell(new Phrase( df.format(dAll)+"%", summaryFont));

				PdfPTable table2 = new PdfPTable(new float[] { 2 ,1, 1, 1, 1  });
				table2.getDefaultCell().setBorder(Rectangle.NO_BORDER);
				table2.setWidthPercentage(100f);
				table2.setHorizontalAlignment(Element.ALIGN_LEFT);

				table2.addCell(new Phrase("Total Residents", summaryFont));
				table2.addCell(new Phrase((bynFailure+bynSuccess)+"", summaryFont));
				table2.addCell(new Phrase((ftlFailure+ftlSuccess)+"", summaryFont));
				table2.addCell(new Phrase((okeFailure+okeSuccess)+"", summaryFont));
				table2.addCell(new Phrase(((bynFailure+bynSuccess)+(ftlFailure+ftlSuccess)+(okeFailure+okeSuccess))+"", summaryFont));

				PdfPTable table3 = new PdfPTable(1);
				table3.getDefaultCell().setBorder(Rectangle.NO_BORDER);
				table3.setWidthPercentage(100f);
				table3.setHorizontalAlignment(Element.ALIGN_LEFT);
				table3.addCell(new Phrase("Calculations do not include student's with a status 'In Program'", dateFont));
				
			document.add(table0);
			document.add(spacerTbl);
			document.add(table1);
			document.add(spacerTbl);
			document.add(table2);
			document.add(spacerTbl);
			document.add(spacerTbl);
			document.add(spacerTbl);
			document.add(spacerTbl);
			document.add(table3);
			
			document.close();
			
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (IOException io) {
			io.printStackTrace();
		}

	}
	
	
	public void generateCwtRoster(SystemUser user, CwtModules module, CwtModuleSection section, List<CwtRoster>list, HttpServletResponse response) {
		response.setContentType("application/pdf");
		
		PdfWriter writer = null;
		Document document = new Document();
		document.setMargins(10, 10, 10, 10);
		
		
		response.setContentType("application/pdf");
		try {
			writer = PdfWriter.getInstance(document,
					response.getOutputStream());

			document.setMargins(10, 10, 10, 10);
			document.open();
			String currentDate = Validator.convertDate(new java.util.Date()
					+ "");

			// Create a table to server as a spacer
			PdfPTable spacerTbl = new PdfPTable(1);
			PdfPCell spacerCell = new PdfPCell(new Paragraph(""));
			spacerCell.setColspan(1);
			spacerTbl.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			spacerCell.setBorder(Rectangle.NO_BORDER);
			spacerCell.setFixedHeight(20);
			spacerTbl.addCell(spacerCell);

			Font cellFont = FontFactory.getFont(FontFactory.COURIER, 8);
			Font summaryFont = FontFactory.getFont(FontFactory.COURIER, 8);
			Font headerFont = FontFactory.getFont(FontFactory.HELVETICA, 10);
			Font titleFont = FontFactory.getFont(FontFactory.HELVETICA, 14);
			Font dateFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 6);
			Font colHeaderFont = FontFactory.getFont(
					FontFactory.HELVETICA_BOLD, 8);
			colHeaderFont.setStyle(Font.UNDERLINE);
			headerFont.setStyle(Font.BOLD);

			Image img = Image.getInstance(String.format(
					"c:\\pdfFiles\\logo_wordpress_2.png", ""));
			img.scaleToFit(150, 94);
			document.add(new Chunk(img, 0, -90, true));

			// spacer
			document.add(spacerTbl);
			document.add(spacerTbl);
			document.add(spacerTbl);
			document.add(spacerTbl);
			document.add(spacerTbl);
			document.add(spacerTbl);

			Chunk titleChunk = new Chunk("CWT: "+module.getModuleName());
			titleFont.setStyle(Font.BOLD);
			titleChunk.setFont(titleFont);
			document.add(titleChunk);
			document.add(spacerTbl);

			Chunk details1Chunk = new Chunk("Farm: "+section.getFarmBase());
			details1Chunk.setFont(headerFont);
			document.add(details1Chunk);
			document.add(spacerTbl);
			
			Chunk details5Chunk = new Chunk("Instructor: "+user.getFirstName()+" "+user.getLastName());
			details5Chunk.setFont(headerFont);
			document.add(details5Chunk);
			document.add(spacerTbl);
			
			Chunk details2Chunk = new Chunk("Meeting Days: "+section.getMeetingDays());
			details2Chunk.setFont(headerFont);
			document.add(details2Chunk);
			document.add(spacerTbl);
			Chunk details3Chunk = new Chunk("Meeting Times: "+section.getMeetingTimes());
			details3Chunk.setFont(headerFont);
			document.add(details3Chunk);
			document.add(spacerTbl);
			Chunk details4Chunk = new Chunk("Location: "+section.getLocation());
			details4Chunk.setFont(headerFont);
			document.add(details4Chunk);
			document.add(spacerTbl);
			Chunk details6Chunk = new Chunk("Class Size: "+list.size());
			details6Chunk.setFont(headerFont);
			document.add(details6Chunk);

			// spacer
			document.add(spacerTbl);

			
			// Table of Applicants
			PdfPTable table0 = new PdfPTable(new float[] { 1 ,1, 1, 1 });
			PdfPCell cell0 = new PdfPCell(new Paragraph("column span 3"));
			cell0.setColspan(5);
			table0.getDefaultCell().setBorder(Rectangle.NO_BORDER);
			cell0.setBorder(Rectangle.NO_BORDER);
			table0.setWidthPercentage(100f);
			table0.setHorizontalAlignment(Element.ALIGN_LEFT);
			
			table0.addCell(new Phrase("Student", colHeaderFont));
			table0.addCell(new Phrase("Exam Score", colHeaderFont));
			table0.addCell(new Phrase("Attended", colHeaderFont));
			table0.addCell(new Phrase("Status", colHeaderFont));
			
			IntakeDao dao = new IntakeDao();
			
			for (Iterator iterator = list.iterator(); iterator.hasNext();) {
				 CwtRoster roster = (CwtRoster)iterator.next();
				 Intake intake = dao.find(roster.getIntakeId());
				 String attendFlag = roster.getAttendFlag();
				 String examScore = roster.getExamScore();
				 if(attendFlag==null)
					 attendFlag="";
				 if (examScore==null)
					 examScore="";
				table0.addCell(new Phrase(intake.getFirstname()+" "+intake.getLastname(), cellFont));
				table0.addCell(new Phrase(examScore, cellFont));
				table0.addCell(new Phrase(attendFlag, cellFont));
				table0.addCell(new Phrase(roster.getStatus()+"", cellFont));				
			}
		
			
			document.add(table0);			
			document.close();
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (IOException io) {
			io.printStackTrace();
		}

	}
}
