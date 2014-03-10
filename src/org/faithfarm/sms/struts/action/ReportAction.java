package org.faithfarm.sms.struts.action;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.faithfarm.sms.application.Constants;
import org.faithfarm.sms.domain.CourseRotationHistory;
import org.faithfarm.sms.domain.CwtJob;
import org.faithfarm.sms.domain.CwtMaster;
import org.faithfarm.sms.domain.CwtProgram;
import org.faithfarm.sms.domain.CwtSupervisor;
import org.faithfarm.sms.domain.Intake;
import org.faithfarm.sms.domain.StudentHistory;
import org.faithfarm.sms.domain.SystemUser;
import org.faithfarm.sms.domain.ViewFastFind;
import org.faithfarm.sms.hibernate.data.CourseRotationHistoryDao;
import org.faithfarm.sms.hibernate.data.CwtJobDao;
import org.faithfarm.sms.hibernate.data.CwtProgramDao;
import org.faithfarm.sms.hibernate.data.CwtSupervisorDao;
import org.faithfarm.sms.hibernate.data.GenericDao;
import org.faithfarm.sms.hibernate.data.IntakeDao;
import org.faithfarm.sms.hibernate.data.StudentHistoryDao;
import org.faithfarm.sms.struts.form.ReportForm;
import org.faithfarm.sms.util.HtmlDropDownBuilder;
import org.faithfarm.sms.util.PDFBuilder;
import org.faithfarm.sms.util.Validator;

public class ReportAction extends Action {
	
	private final static Logger LOGGER = Logger.getLogger(ReportAction.class.getName());
	private final static HtmlDropDownBuilder html = new HtmlDropDownBuilder();
	private final static Validator v8r = new Validator();
	
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {		
		LOGGER.setLevel(Level.SEVERE);

		 HttpSession session = request.getSession(false);
		 
		 String action=request.getParameter("action");
		 SystemUser user = (SystemUser) session.getAttribute("system_user");
		 try {

		/*
		 * test ddl's stored in session.  If empty, session expired, redirect to login
		 */
		ArrayList ddllist = (ArrayList)session.getAttribute("ddl_farm");
		if (ddllist==null) 
			return mapping.findForward(Constants.LOGIN);

		 IntakeDao intakeDao = new IntakeDao ();
		 CwtSupervisorDao sDao = new CwtSupervisorDao();
		 CwtJobDao jDao = new CwtJobDao();
		 StudentHistoryDao hDao = new StudentHistoryDao();
		
		 CourseRotationHistoryDao historyDao = new CourseRotationHistoryDao();
		 
		 List<Intake> intakeList = new ArrayList<Intake>();
		 
		 ReportForm reportForm = (ReportForm)form;
		 
		 String report=request.getParameter("rpt");
		 String farm=request.getParameter("farm");
		 String passDate1 = request.getParameter("beginPassDate");
		 String passDate2 = request.getParameter("endPassDate");
		 
		 PDFBuilder pdf = new PDFBuilder();
			
			
		 if ("FastFind".equals(action)||"FastFind".equals(reportForm.getAction())) {
			 reportForm.setReportTitle("Fast Find List");
			 if (reportForm.getFarmBase()==null||reportForm.getFarmBase().length()==0) {
				 farm=user.getFarmBase();
				 reportForm.setFarmBase(farm);
			 }
			 else
				 farm = reportForm.getFarmBase();
			 
			 //this.buildFastFindList(reportForm, farm);
			 this.buildNewFFList(reportForm, farm);
			 reportForm.setAction("");
			 reportForm.setFarmBase(farm);
			 return mapping.findForward(Constants.FAST_FIND); 
		 }
		 if ("Rotate".equals(action)) {
			 List<CourseRotationHistory> list=historyDao.findAllByFarm(farm);
			 for (Iterator iterator=list.iterator();iterator.hasNext();) {
				 	CourseRotationHistory obj = (CourseRotationHistory)iterator.next();
				 	reportForm.setLastRotatedBy(obj.getCreatedBy());
				 	reportForm.setLastRotationDate(v8r.convertEpoch(new Long(obj.getRotationDate())));
				 	reportForm.setFarmBase(obj.getFarmBase());
			 }
				 
			  
		 }
		 if ("ClassList".equals(report)||"Rotate".equals(action)) {
			 reportForm.setReportTitle("Class List");
			 reportForm.setFarmBase(farm);
			 java.util.Date sDate = new java.util.Date();
			 reportForm.setRunDate(v8r.convertDate(sDate.toString()));
			 reportForm.setClass0List(intakeDao.listClass("Orientation", farm));
			 reportForm.setClass1List(intakeDao.listClass("1", farm));
			 reportForm.setClass2List(intakeDao.listClass("2", farm));
			 reportForm.setClass3List(intakeDao.listClass("3", farm));
			 reportForm.setClass4List(intakeDao.listClass("4", farm));
			 reportForm.setClass5List(intakeDao.listClass("5", farm));
			 reportForm.setClass6List(intakeDao.listClass("6", farm));
			 reportForm.setClass7List(intakeDao.listClass("Fresh Start", farm));
			 reportForm.setClass8List(intakeDao.listClass("SLS", farm));
			 reportForm.setClass9List(intakeDao.listClass("Intern", farm));
			 reportForm.setClass10List(intakeDao.listClass("Student Teacher", farm));			 
			 reportForm.setClass0flag(new String[100]);
			 reportForm.setClass1flag(new String[100]);
			 reportForm.setClass2flag(new String[100]);
			 reportForm.setClass3flag(new String[100]);
			 reportForm.setClass4flag(new String[100]);
			 reportForm.setClass5flag(new String[100]);
			 reportForm.setClass6flag(new String[100]);
			 
			 if ("Rotate".equals(action))
				 return mapping.findForward(Constants.ROTATE); 
			 else
				 return mapping.findForward(Constants.CLASS_REPORT); 
		 }
		 else if ("Rotate Students".equals(action)) {
			 this.rotateClasses(reportForm);
			 CourseRotationHistory obj = new CourseRotationHistory();
			 obj.setCreatedBy(user.getUsername());
			 obj.setRotationDate(Validator.getEpoch()+"");
			 obj.setFarmBase(user.getFarmBase());
			 historyDao.save(obj);
			 return mapping.findForward("rotatesuccess");
		 }
		 else if ("JobList".equals(report)) {
			 buildJobList (reportForm, farm);
			 if ("Boynton Beach".equals(farm))
				 return mapping.findForward("joblist_byn");
			 if ("Fort Lauderdale".equals(farm))
				 return mapping.findForward("joblist_ftl");
			 else
				 return mapping.findForward("joblist_oke");
				
		 }
		 else  if ("passList".equals(action)) {
				pdf.passlistPdf(user, passDate1, passDate2, response);
				return null;
		 }
		 else
			 return mapping.findForward(Constants.SUCCESS);
		}
		catch (Exception e) {
				StringWriter sw = new StringWriter();
				PrintWriter pw = new PrintWriter(sw);
				e.printStackTrace(pw);			
				session.setAttribute("SYSTEM_ERROR", sw.toString());
				e.printStackTrace();
				return mapping.findForward(Constants.ERROR);
			}
	}
	
	
	private void buildJobList (ReportForm form, String farmBase) {
		
		IntakeDao dao = new IntakeDao();
		
		List<Intake> list = dao.search(null, null, null, null, null, null, farmBase, null, null, "In Program",null,null,null,null,null);
		
		List<Intake>list0 = new ArrayList<Intake>();
		List<Intake>list1 = new ArrayList<Intake>();
		List<Intake>list2 = new ArrayList<Intake>();
		List<Intake>list3 = new ArrayList<Intake>();
		List<Intake>list4 = new ArrayList<Intake>();
		List<Intake>list5 = new ArrayList<Intake>();
		List<Intake>list6 = new ArrayList<Intake>();
		List<Intake>list7 = new ArrayList<Intake>();
		List<Intake>list8 = new ArrayList<Intake>();
		List<Intake>list9 = new ArrayList<Intake>();
		List<Intake>list10 = new ArrayList<Intake>();
		List<Intake>list11 = new ArrayList<Intake>();
		List<Intake>list12 = new ArrayList<Intake>();
		List<Intake>list13 = new ArrayList<Intake>();
		List<Intake>list14 = new ArrayList<Intake>();
		List<Intake>list15 = new ArrayList<Intake>();
		List<Intake>list16 = new ArrayList<Intake>();
		List<Intake>list17 = new ArrayList<Intake>();
		List<Intake>list18 = new ArrayList<Intake>();
		List<Intake>list19 = new ArrayList<Intake>();
		List<Intake>list20 = new ArrayList<Intake>();
		List<Intake>list21 = new ArrayList<Intake>();
		List<Intake>list22 = new ArrayList<Intake>();
		List<Intake>list23 = new ArrayList<Intake>();
		List<Intake>list24 = new ArrayList<Intake>();
		List<Intake>list25 = new ArrayList<Intake>();
		List<Intake>list26 = new ArrayList<Intake>();
		List<Intake>list27 = new ArrayList<Intake>();
		List<Intake>list28 = new ArrayList<Intake>();
		List<Intake>list29 = new ArrayList<Intake>();
		List<Intake>list30 = new ArrayList<Intake>();
		List<Intake>list31 = new ArrayList<Intake>();
		List<Intake>list32 = new ArrayList<Intake>();
		List<Intake>list33 = new ArrayList<Intake>();
		List<Intake>list34 = new ArrayList<Intake>();
		List<Intake>list35 = new ArrayList<Intake>();
		List<Intake>list36 = new ArrayList<Intake>();
		List<Intake>list37 = new ArrayList<Intake>();
		List<Intake>list38 = new ArrayList<Intake>();
		List<Intake>list39 = new ArrayList<Intake>();
		 
			
			for (Iterator iterator =
					 list.iterator(); iterator.hasNext();){
				 Intake intake = (Intake)iterator.next();
			
					 if (intake.getJobId()!=null) {
					
					 //canteen
					 if (intake.getJobId()==21) 
								list0.add(intake);	
					//kitchen
					 if (intake.getJobId()==22||intake.getJobId()==42||intake.getJobId()==41||intake.getJobId()==40) 
								list1.add(intake);	
					//laundry
					 if (intake.getJobId()==29) 
								list2.add(intake);	
					//house crew
					 if (intake.getJobId()==37) 
								list3.add(intake);	
					//garage
					 if (intake.getJobId()==27) 
								list4.add(intake);	
					//grounds crew
					 if (intake.getJobId()==24) 
								list5.add(intake);	
					//mower repair
					 if (intake.getJobId()==23) 
								list6.add(intake);	
					 //computer
					 if (intake.getJobId()==33) 
						list7.add(intake);
					 //fix it
					 if (intake.getJobId()==32) 
						list8.add(intake);
					 //gate guards
					 if (intake.getJobId()==52) 
						list9.add(intake);
					 //front office
					 if (intake.getJobId()==53) 
						list10.add(intake);
					 //courier
					 if (intake.getJobId()==54) 
						list11.add(intake);
					 //dtc houseman
					 if (intake.getJobId()==55) 
						list12.add(intake);
					 //Appliance
					 if (intake.getJobId()==31) 
						list13.add(intake);
					 //Books
					 if (intake.getJobId()==58) 
						list14.add(intake);
					 //Bric-Brac Sorting
					 if (intake.getJobId()==26) 
						list15.add(intake);
					 //Clothing Sorting
					 if (intake.getJobId()==25) 
						list16.add(intake);
					 //Salvage
					 if (intake.getJobId()==28) 
						list17.add(intake);
					 //Dock Lead
					 if (intake.getJobId()==57) 
						list18.add(intake);
					 //Dock Worker
					 if (intake.getJobId()==35) 
						list19.add(intake);
					 //drivers
					 if (intake.getJobId()==17) 
						 list20.add(intake);
					 //Dispatch
					 if (intake.getJobId()==38) 
						list21.add(intake);
					 //Radio Man
					 if (intake.getJobId()==46) 
						list22.add(intake);
					 //Scheduling
					 if (intake.getJobId()==58) 
						list23.add(intake);
					 //Del. Coordinator
					 if (intake.getJobId()==51) 
						list24.add(intake);
					 //Helpers
					 if (intake.getJobId()==59) 
						list25.add(intake);
					 //Outside Sales
					 if (intake.getJobId()==30) 
						list26.add(intake);
					 //Customer P/U
					 if (intake.getJobId()==60) 
						list27.add(intake);
					 //NF Sales
					 if (intake.getJobId()==47) 
						list28.add(intake);
					 //As Is Sales
					 if (intake.getJobId()==36) 
						list29.add(intake);
					 //Bric Brac Room
					 if (intake.getJobId()==48) 
						list30.add(intake);
					 //Bric Brac Sorting
					 if (intake.getJobId()==26) 
						list31.add(intake);
					 //Women's Clothing
					 if (intake.getJobId()==62) 
						list32.add(intake);
					 //NF Warehouse
					 if (intake.getJobId()==34) 
						list33.add(intake);
					 //MaintenEnce
					 if (intake.getJobId()==20) 
						list34.add(intake);
					 //Bicycle Repair
					 if (intake.getJobId()==50) 
						list35.add(intake);
					 //Donation Pickup
					 if (intake.getJobId()==63) 
						list36.add(intake);
					 //Career Center
					 if (intake.getJobId()==58) 
						list37.add(intake);
					 //At. Corporate
					 if (intake.getJobId()==65) 
						list38.add(intake);
					 //Dairy List
					 if (intake.getJobId()==16) 
						list39.add(intake);



				 
				 }//end null check
				 
			}
			
			form.setCanteenList(list0);
			form.setKitchenList(list1);
			form.setLaundryList(list2);
			form.setHouseCrewList(list3);
			form.setGarageList(list4);
			form.setGroundsCrewList(list5);
			form.setMowerRepairList(list6);
			form.setComputerList(list7);
			form.setFixItList(list8);
			form.setGateGuardsList(list9);
			form.setFrontOfficeList(list10);
			form.setCourierList(list11);
			form.setDtcHousemanList(list12);
			form.setApplianceList(list13);
			form.setBooksList(list14);
			form.setBricBracSortingList(list15);
			form.setClothingSortingList(list16);
			form.setSalvageList(list17);
			form.setDockLeadList(list18);
			form.setDockWorkerList(list19);
			form.setTruckDriverList(list20);
			form.setDispatchList(list21);
			form.setRadioManList(list22);
			form.setSchedulingList(list23);
			form.setDelCoordinatorList(list24);
			form.setHelperList(list25);
			form.setOutsideSalesList(list26);
			form.setCustomerPickUpList(list27);
			form.setNewFurnitureList(list28);
			form.setAsIsSalesList(list29);
			form.setBricBracRoomList(list30);
			form.setBricBracCorralList(list31);
			form.setClothingWomensList(list32);
			form.setNewFurnitureWarehouseList(list33);
			form.setMaintenanceList(list34);
			form.setBicycleRepairList(list35);
			form.setDonationPickUpList(list36);
			form.setCareerCenterList(list37);
			form.setAtCorpList(list38);
			form.setDairyList(list39);
			
			form.setCanteenListSize(list0.size());
			form.setKitchenListSize(list1.size());
			form.setLaundryListSize(list2.size());
			form.setHouseCrewListSize(list3.size());
			form.setGarageListSize(list4.size());
			form.setGroundsCrewListSize(list5.size());
			form.setMowerRepairListSize(list6.size());
			form.setComputerListSize(list7.size());
			form.setFixItListSize(list8.size());
			form.setGateGuardsListSize(list9.size());
			form.setFrontOfficeListSize(list10.size());
			form.setCourierListSize(list11.size());
			form.setDtcHousemanListSize(list12.size());
			form.setApplianceListSize(list13.size());
			form.setBooksListSize(list14.size());
			form.setBricBracSortingListSize(list15.size());
			form.setClothingSortingListSize(list16.size());
			form.setSalvageListSize(list17.size());
			form.setDockLeadListSize(list18.size());
			form.setDockWorkerListSize(list19.size());
			form.setTruckDriverListSize(list20.size());
			form.setDispatchListSize(list21.size());
			form.setRadioManListSize(list22.size());
			form.setSchedulingListSize(list23.size());
			form.setDelCoordinatorListSize(list24.size());
			form.setHelperListSize(list25.size());
			form.setOutsideSalesListSize(list26.size());
			form.setCustomerPickUpListSize(list27.size());
			form.setNewFurnitureListSize(list28.size());
			form.setAsIsSalesListSize(list29.size());
			form.setBricBracRoomListSize(list30.size());
			form.setBricBracCorralListSize(list31.size());
			form.setClothingWomensListSize(list32.size());
			form.setNewFurnitureWarehouseListSize(list33.size());
			form.setMaintenanceListSize(list34.size());
			form.setBicycleRepairListSize(list35.size());
			form.setDonationPickUpListSize(list36.size());
			form.setCareerCenterListSize(list37.size());
			form.setAtCorpListSize(list38.size());
			form.setDairyListSize(list39.size());
			
			
			
		
		
	}
	private void rotateClasses(ReportForm reportForm) {
		
		 IntakeDao dao = new IntakeDao();
		 int i=0;
		 
		 //Rotate Class 6
		 String flag6[] = reportForm.getClass6flag();
		 for (Iterator iterator =
				 reportForm.getClass6List().iterator(); iterator.hasNext();){
			 Intake intake = (Intake)iterator.next();
			 if ("Yes".equals(flag6[i])) { 
				 intake.setClass_("Graduated");
				 intake.setIntakeStatus("Graduated");
				 intake.setApplicationStatus("Graduated");
				 dao.update(intake);
			 }
		 i++;
		 }
		 reportForm.setClass6flag(flag6);
		 
		 
		 //Rotate Class 5
		 i=0;
		 String flag5[] = reportForm.getClass5flag();
		 for (Iterator iterator =
				 reportForm.getClass5List().iterator(); iterator.hasNext();){
			 Intake intake = (Intake)iterator.next();
			 if ("Yes".equals(flag5[i])) { 
				 intake.setClass_("6");
				 dao.update(intake);
			 }
		 i++;
		 }
		 reportForm.setClass5flag(flag5);

		 
		 //Rotate Class 4
		 i=0;
		 String flag4[] = reportForm.getClass4flag();
		 for (Iterator iterator =
				 reportForm.getClass4List().iterator(); iterator.hasNext();){
			 Intake intake = (Intake)iterator.next();
			 if ("Yes".equals(flag4[i])) { 
				 intake.setClass_("5");
				 dao.update(intake);
			 }
		 i++;
		 }
		 reportForm.setClass4flag(flag4);

		 //Rotate Class 3
		 i=0;
		 String flag3[] = reportForm.getClass3flag();
		 for (Iterator iterator =
				 reportForm.getClass3List().iterator(); iterator.hasNext();){
			 Intake intake = (Intake)iterator.next();
			 if ("Yes".equals(flag3[i])) { 
				 intake.setClass_("4");
				 dao.update(intake);
			 }
		 i++;
		 }
		 reportForm.setClass3flag(flag3);

		 
		 //Rotate Class 2
		 i=0;
		 String flag2[] = reportForm.getClass2flag();
		 for (Iterator iterator =
				 reportForm.getClass2List().iterator(); iterator.hasNext();){
			 Intake intake = (Intake)iterator.next();
			 if ("Yes".equals(flag2[i])) { 
				 intake.setClass_("3");
				 dao.update(intake);
			 }
		 i++;
		 }
		 reportForm.setClass2flag(flag2);

		 
		 //Rotate Class 1
		 i=0;
		 String flag1[] = reportForm.getClass1flag();
		 for (Iterator iterator =
				 reportForm.getClass1List().iterator(); iterator.hasNext();){
			 Intake intake = (Intake)iterator.next();
			 if ("Yes".equals(flag1[i])) { 
				 intake.setClass_("2");
				 dao.update(intake);
			 }
		 i++;
		 }
		 reportForm.setClass1flag(flag1);

		 
		 //Rotate Class 0
		 i=0;
		 String flag0[] = reportForm.getClass0flag();
		 for (Iterator iterator =
				 reportForm.getClass0List().iterator(); iterator.hasNext();){
			 Intake intake = (Intake)iterator.next();
			 if ("Yes".equals(flag0[i])) { 
				 intake.setClass_("1");
				 dao.update(intake);
			 }
		 i++;
		 }
		 reportForm.setClass0flag(flag0);
	}
	
	private void buildNewFFList (ReportForm reportForm, String farm) {
		GenericDao dao = new GenericDao();
		
		List<ViewFastFind> list = dao.listAllByFarm(new ViewFastFind().getClass(), farm);
		reportForm.setFastFindList(list);
	}
	private void OLDbuildFastFindList (ReportForm reportForm, String farm) {
		
		 IntakeDao intakeDao = new IntakeDao ();
		 CwtSupervisorDao sDao = new CwtSupervisorDao();
		 CwtJobDao jDao = new CwtJobDao();
		 StudentHistoryDao hDao = new StudentHistoryDao();
		 CwtProgramDao cwtDao = new CwtProgramDao();
		 CourseRotationHistoryDao historyDao = new CourseRotationHistoryDao();

		 
		 
		 /*
		  * Class 0
		  * 
		  */
		 List<Intake> list = intakeDao.listClass("Orientation", farm);
		 List<CwtMaster> masterList = new ArrayList<CwtMaster>();
		 String program0[] = new String[200];
		 
		 int index=0;
		 for (Iterator iterator=list.iterator();iterator.hasNext();) {
			 Intake intake = (Intake)iterator.next();
			 
			 
			 CwtProgram program = new CwtProgram();
			 if (intake.getCwtProgramId()!=null) {
				 program = cwtDao.find(intake.getCwtProgramId());
				 if (program==null)
					 program=new CwtProgram();
			 }
			 CwtSupervisor supervisor = new CwtSupervisor();
			 if (intake.getSupervisorId()!=null) {
				 supervisor = sDao.find(intake.getSupervisorId());
				 if (supervisor==null)
					 supervisor=new CwtSupervisor();
			 }
			 CwtJob job =new CwtJob();
			 if (intake.getJobId()!=null) {
				  job = jDao.find(intake.getJobId());
				  if (job==null)
					  job=new CwtJob();
			 }
			 List<StudentHistory> history = hDao.findByIntakeId(new StudentHistory().getClass(), intake.getIntakeId());
			 	StudentHistory studentHistory = null;
			 	for (Iterator iterator2=history.iterator();iterator2.hasNext();)
			 		 studentHistory = (StudentHistory)iterator2.next();
			 	
			 	if (studentHistory!=null)
			 		program0[index]=studentHistory.getPhase();
			 	else
			 		program0[index]="";
		 index++;
		 
		 CwtMaster master = new CwtMaster();
		 master.setIntake(intake);
		 master.setCwtJob(job);
		 master.setCwtSupervisor(supervisor);
		 master.setProgram(program);
		 masterList.add(master);
		 }
		 reportForm.setClass0CwtMasterList(masterList);
		 reportForm.setProgram0(program0);
		 
		 /*
		  * 
		  * Class 1
		  */
		 List<Intake> list1 = intakeDao.listClass("1", farm);
		 List<CwtMaster> masterList1 = new ArrayList<CwtMaster>();
		 String program1[] = new String[200];
		 
		 index=0;
		 for (Iterator iterator=list1.iterator();iterator.hasNext();) {
			 Intake intake = (Intake)iterator.next();
			 
			 CwtProgram program = new CwtProgram();
			 if (intake.getCwtProgramId()!=null) {
				 program = cwtDao.find(intake.getCwtProgramId());
				 if (program==null)
					 program=new CwtProgram();
			 }
			 
			 CwtSupervisor supervisor = new CwtSupervisor();
			 if (intake.getSupervisorId()!=null) {
				 supervisor = sDao.find(intake.getSupervisorId());
				 if (supervisor==null)
					 supervisor=new CwtSupervisor();
			 }
			 
			 CwtJob job = new CwtJob();
			 if (intake.getJobId()!=null) {
				 job = jDao.find(intake.getJobId());
				 if (job==null)
					 job=new CwtJob();
		 	 }
		 
			 List<StudentHistory> history = hDao.findByIntakeId(new StudentHistory().getClass(), intake.getIntakeId());
			 	StudentHistory studentHistory = null;
			 	for (Iterator iterator2=history.iterator();iterator2.hasNext();)
			 		 studentHistory = (StudentHistory)iterator2.next();
			 	if (studentHistory!=null)
			 		program1[index]=studentHistory.getPhase();
			 	else
			 		program1[index]="";
		 index++;
		 
		 CwtMaster master1 = new CwtMaster();
		 master1.setIntake(intake);
		 master1.setCwtJob(job);
		 master1.setCwtSupervisor(supervisor);
		 master1.setProgram(program);
		 masterList1.add(master1);
		 }
		 reportForm.setClass1CwtMasterList(masterList1);
		 reportForm.setProgram1(program1);
		 
		 
		 /*
		  * 
		  * Class 2
		  */
		 List<Intake> list2 = intakeDao.listClass("2", farm);
		 List<CwtMaster> masterList2 = new ArrayList<CwtMaster>();
		 String program2[] = new String[200];
		 
		 index=0;
		 for (Iterator iterator=list2.iterator();iterator.hasNext();) {
			 Intake intake = (Intake)iterator.next();
			 
			 
			 CwtProgram program = new CwtProgram();
			 if (intake.getCwtProgramId()!=null) {
				 program = cwtDao.find(intake.getCwtProgramId());
				 if (program==null)
					 program=new CwtProgram();
			 }
			 
			 CwtSupervisor supervisor = new CwtSupervisor();
			 if (intake.getSupervisorId()!=null) {
				 supervisor = sDao.find(intake.getSupervisorId());
				 if (supervisor==null)
					 supervisor=new CwtSupervisor();
			 }
			 
			 CwtJob job = new CwtJob();
			 if (intake.getJobId()!=null) {
				 job = jDao.find(intake.getJobId());
				 if (job==null)
					 job=new CwtJob();
		 	 }
			 
			 List<StudentHistory> history = hDao.findByIntakeId(new StudentHistory().getClass(),intake.getIntakeId());
			 	StudentHistory studentHistory = null;
			 	for (Iterator iterator2=history.iterator();iterator2.hasNext();)
			 		 studentHistory = (StudentHistory)iterator2.next();
			 	if (studentHistory!=null)
			 		program2[index]=studentHistory.getPhase();
			 	else
			 		program2[index]="";
		 index++;
		 
		 CwtMaster master2 = new CwtMaster();
		 master2.setIntake(intake);
		 master2.setCwtJob(job);
		 master2.setCwtSupervisor(supervisor);
		 master2.setProgram(program);
		 masterList2.add(master2);
		 }
		 reportForm.setClass2CwtMasterList(masterList2);
		 reportForm.setProgram2(program2);

		 /*
		  * 
		  * Class 3
		  */
		 List<Intake> list3 = intakeDao.listClass("3", farm);
		 List<CwtMaster> masterList3 = new ArrayList<CwtMaster>();
		 String program3[] = new String[200];
		 
		 index=0;
		 for (Iterator iterator=list3.iterator();iterator.hasNext();) {
			 Intake intake = (Intake)iterator.next();
			 
			 CwtProgram program = new CwtProgram();
			 if (intake.getCwtProgramId()!=null) {
				 program = cwtDao.find(intake.getCwtProgramId());
				 if (program==null)
					 program=new CwtProgram();
			 }
			 
			 CwtSupervisor supervisor = new CwtSupervisor();
			 if (intake.getSupervisorId()!=null) {
				 supervisor = sDao.find(intake.getSupervisorId());
				 if (supervisor==null)
					 supervisor=new CwtSupervisor();
			 }
			 
			 CwtJob job = new CwtJob();
			 if (intake.getJobId()!=null) {
				 job = jDao.find(intake.getJobId());
				 if (job==null)
					 job=new CwtJob();
		 	 }
			 List<StudentHistory> history = hDao.findByIntakeId(new StudentHistory().getClass(),intake.getIntakeId());
			 	StudentHistory studentHistory = null;
			 	for (Iterator iterator2=history.iterator();iterator2.hasNext();)
			 		 studentHistory = (StudentHistory)iterator2.next();
			 	if (studentHistory!=null)
			 		program3[index]=studentHistory.getPhase();
			 	else
			 		program3[index]="";
		 index++;
		 
		 CwtMaster master3 = new CwtMaster();
		 master3.setIntake(intake);
		 master3.setCwtJob(job);
		 master3.setCwtSupervisor(supervisor);
		 master3.setProgram(program);
		 masterList3.add(master3);
		 }
		 reportForm.setClass3CwtMasterList(masterList3);
		 reportForm.setProgram3(program3);

	
	 /*
	  * 
	  * Class 4
	  */
	 List<Intake> list4 = intakeDao.listClass("4", farm);
	 List<CwtMaster> masterList4 = new ArrayList<CwtMaster>();
	 String program4[] = new String[200];
	 
	 index=0;
	 for (Iterator iterator=list4.iterator();iterator.hasNext();) {
		 Intake intake = (Intake)iterator.next();
		 
		 CwtProgram program = new CwtProgram();
		 if (intake.getCwtProgramId()!=null) {
			 program = cwtDao.find(intake.getCwtProgramId());
			 if (program==null)
				 program=new CwtProgram();
		 }
		 
		 CwtSupervisor supervisor = new CwtSupervisor();
		 if (intake.getSupervisorId()!=null) {
			 supervisor = sDao.find(intake.getSupervisorId());
			 if (supervisor==null)
				 supervisor=new CwtSupervisor();
		 }
		 
		 CwtJob job = new CwtJob();
		 if (intake.getJobId()!=null) {
			 job = jDao.find(intake.getJobId());
			 if (job==null)
				 job=new CwtJob();
	 	 }
		 List<StudentHistory> history = hDao.findByIntakeId(new StudentHistory().getClass(),intake.getIntakeId());
		 	StudentHistory studentHistory = null;
		 	for (Iterator iterator2=history.iterator();iterator2.hasNext();)
		 		 studentHistory = (StudentHistory)iterator2.next();
		 	if (studentHistory!=null)
		 		program4[index]=studentHistory.getPhase();
		 	else
		 		program4[index]="";
	 index++;
	 
	 CwtMaster master4 = new CwtMaster();
	 master4.setIntake(intake);
	 master4.setCwtJob(job);
	 master4.setCwtSupervisor(supervisor);
	 master4.setProgram(program);
	 masterList4.add(master4);
	 }
	 reportForm.setClass4CwtMasterList(masterList4);
	 reportForm.setProgram4(program4);

	
	 /*
	  * 
	  * Class 5
	  */
	 List<Intake> list5 = intakeDao.listClass("5", farm);
	 List<CwtMaster> masterList5 = new ArrayList<CwtMaster>();
	 String program5[] = new String[200];
	 
	 index=0;
	 for (Iterator iterator=list5.iterator();iterator.hasNext();) {
		 Intake intake = (Intake)iterator.next();
		 
		 CwtProgram program = new CwtProgram();
		 if (intake.getCwtProgramId()!=null) {
			 program = cwtDao.find(intake.getCwtProgramId());
			 if (program==null)
				 program=new CwtProgram();
		 }
		 
		 CwtSupervisor supervisor = new CwtSupervisor();
		 if (intake.getSupervisorId()!=null) {
			 supervisor = sDao.find(intake.getSupervisorId());
			 if (supervisor==null)
				 supervisor=new CwtSupervisor();
		 }
		 
		 CwtJob job = new CwtJob();
		 if (intake.getJobId()!=null) {
			 job = jDao.find(intake.getJobId());
			 if (job==null)
				 job=new CwtJob();
	 	 }
		 
		 List<StudentHistory> history = hDao.findByIntakeId(new StudentHistory().getClass(),intake.getIntakeId());
		 	StudentHistory studentHistory = null;
		 	for (Iterator iterator2=history.iterator();iterator2.hasNext();)
		 		 studentHistory = (StudentHistory)iterator2.next();
		 	if (studentHistory!=null)
		 		program5[index]=studentHistory.getPhase();
		 	else
		 		program5[index]="";
	 index++;
	 
	 CwtMaster master5 = new CwtMaster();
	 master5.setIntake(intake);
	 master5.setCwtJob(job);
	 master5.setCwtSupervisor(supervisor);
	 master5.setProgram(program);
	 masterList5.add(master5);
	 }
	 reportForm.setClass5CwtMasterList(masterList5);
	 reportForm.setProgram5(program5);

	 /*
	  * 
	  * Class 6
	  */
	 List<Intake> list6 = intakeDao.listClass("6", farm);
	 List<CwtMaster> masterList6 = new ArrayList<CwtMaster>();
	 String program6[] = new String[200];
	 
	 index=0;
	 for (Iterator iterator=list6.iterator();iterator.hasNext();) {
		 Intake intake = (Intake)iterator.next();
		 
		 
		 CwtProgram program = new CwtProgram();
		 if (intake.getCwtProgramId()!=null) {
			 program = cwtDao.find(intake.getCwtProgramId());
			 if (program==null)
				 program=new CwtProgram();
		 }
		 
		 CwtSupervisor supervisor = new CwtSupervisor();
		 if (intake.getSupervisorId()!=null) {
			 supervisor = sDao.find(intake.getSupervisorId());
			 if (supervisor==null)
				 supervisor=new CwtSupervisor();
		 }
		 
		 CwtJob job = new CwtJob();
		 if (intake.getJobId()!=null) {
			 job = jDao.find(intake.getJobId());
			 if (job==null)
				 job=new CwtJob();
	 	 }
		 
		 List<StudentHistory> history = hDao.findByIntakeId(new StudentHistory().getClass(),intake.getIntakeId());
		 	StudentHistory studentHistory = null;
		 	for (Iterator iterator2=history.iterator();iterator2.hasNext();)
		 		 studentHistory = (StudentHistory)iterator2.next();
		 	if (studentHistory!=null)
		 		program6[index]=studentHistory.getPhase();
		 	else
		 		program6[index]="";
	 index++;
	 
	 CwtMaster master6 = new CwtMaster();
	 master6.setIntake(intake);
	 master6.setCwtJob(job);
	 master6.setCwtSupervisor(supervisor);
	 master6.setProgram(program);
	 masterList6.add(master6);
	 }
	 reportForm.setClass6CwtMasterList(masterList6);
	 reportForm.setProgram6(program6);

	 /*
	  * 
	  * Class 7
	  */
	 List<Intake> list7 = intakeDao.listClass("Fresh Start", farm);
	 List<CwtMaster> masterList7 = new ArrayList<CwtMaster>();
	 String program7[] = new String[200];
	 
	 index=0;
	 for (Iterator iterator=list7.iterator();iterator.hasNext();) {
		 Intake intake = (Intake)iterator.next();
		 
		 
		 CwtProgram program = new CwtProgram();
		 if (intake.getCwtProgramId()!=null) {
			 program = cwtDao.find(intake.getCwtProgramId());
			 if (program==null)
				 program=new CwtProgram();
		 }
		 
		 
		 CwtSupervisor supervisor = new CwtSupervisor();
		 if (intake.getSupervisorId()!=null) {
			 supervisor = sDao.find(intake.getSupervisorId());
			 if (supervisor==null)
				 supervisor=new CwtSupervisor();
		 }
		 
		 CwtJob job = new CwtJob();
		 if (intake.getJobId()!=null) {
			 job = jDao.find(intake.getJobId());
			 if (job==null)
				 job=new CwtJob();
	 	 }
		 
		 List<StudentHistory> history = hDao.findByIntakeId(new StudentHistory().getClass(),intake.getIntakeId());
		 	StudentHistory studentHistory = null;
		 	for (Iterator iterator2=history.iterator();iterator2.hasNext();)
		 		 studentHistory = (StudentHistory)iterator2.next();
		 	if (studentHistory!=null)
		 		program7[index]=studentHistory.getPhase();
		 	else
		 		program7[index]="";
	 index++;
	 
	 CwtMaster master7 = new CwtMaster();
	 master7.setIntake(intake);
	 master7.setCwtJob(job);
	 master7.setCwtSupervisor(supervisor);
	 master7.setProgram(program);
	 masterList7.add(master7);
	 }
	 reportForm.setClass7CwtMasterList(masterList7);
	 reportForm.setProgram7(program7);
	 
	 
	 /*
	  * 
	  * Class 8
	  */
	 List<Intake> list8 = intakeDao.listClass("Intern", farm);
	 List<CwtMaster> masterList8 = new ArrayList<CwtMaster>();
	 String program8[] = new String[200];
	 
	 index=0;
	 for (Iterator iterator=list8.iterator();iterator.hasNext();) {
		 Intake intake = (Intake)iterator.next();
		 
		 CwtProgram program = new CwtProgram();
		 if (intake.getCwtProgramId()!=null) {
			 program = cwtDao.find(intake.getCwtProgramId());
			 if (program==null)
				 program=new CwtProgram();
		 }
		 
		 CwtSupervisor supervisor = new CwtSupervisor();
		 if (intake.getSupervisorId()!=null) {
			 supervisor = sDao.find(intake.getSupervisorId());
			 if (supervisor==null)
				 supervisor=new CwtSupervisor();
		 }
		 
		 CwtJob job = new CwtJob();
		 if (intake.getJobId()!=null) {
			 job = jDao.find(intake.getJobId());
			 if (job==null)
				 job=new CwtJob();
	 	 }
		 
		 List<StudentHistory> history = hDao.findByIntakeId(new StudentHistory().getClass(),intake.getIntakeId());
		 	StudentHistory studentHistory = null;
		 	for (Iterator iterator2=history.iterator();iterator2.hasNext();)
		 		 studentHistory = (StudentHistory)iterator2.next();
		 	if (studentHistory!=null)
		 		program8[index]=studentHistory.getPhase();
		 	else
		 		program8[index]="";
	 index++;
	 
	 CwtMaster master8 = new CwtMaster();
	 master8.setIntake(intake);
	 master8.setCwtJob(job);
	 master8.setCwtSupervisor(supervisor);
	 master8.setProgram(program);
	 masterList8.add(master8);
	 }
	 reportForm.setClass8CwtMasterList(masterList8);
	 reportForm.setProgram8(program8);

	 
	 /*
	  * 
	  * Class 9
	  */
	 List<Intake> list9 = intakeDao.listClass("SLS", farm);
	 List<CwtMaster> masterList9 = new ArrayList<CwtMaster>();
	 String program9[] = new String[200];
	 
	 index=0;
	 for (Iterator iterator=list9.iterator();iterator.hasNext();) {
		 Intake intake = (Intake)iterator.next();
		 
		 CwtProgram program = new CwtProgram();
		 if (intake.getCwtProgramId()!=null) {
			 program = cwtDao.find(intake.getCwtProgramId());
			 if (program==null)
				 program=new CwtProgram();
		 }
		 
		 CwtSupervisor supervisor = new CwtSupervisor();
		 if (intake.getSupervisorId()!=null) {
			 supervisor = sDao.find(intake.getSupervisorId());
			 if (supervisor==null)
				 supervisor=new CwtSupervisor();
		 }
		 
		 CwtJob job = new CwtJob();
		 if (intake.getJobId()!=null) {
			 job = jDao.find(intake.getJobId());
			 if (job==null)
				 job=new CwtJob();
	 	 }
		 
		 List<StudentHistory> history = hDao.findByIntakeId(new StudentHistory().getClass(),intake.getIntakeId());
		 	StudentHistory studentHistory = null;
		 	for (Iterator iterator2=history.iterator();iterator2.hasNext();)
		 		 studentHistory = (StudentHistory)iterator2.next();
		 	if (studentHistory!=null)
		 		program9[index]=studentHistory.getPhase();
		 	else
		 		program9[index]="";
	 index++;
	 
	 CwtMaster master9 = new CwtMaster();
	 master9.setIntake(intake);
	 master9.setCwtJob(job);
	 master9.setCwtSupervisor(supervisor);
	 master9.setProgram(program);
	 masterList9.add(master9);
	 }
	 reportForm.setClass9CwtMasterList(masterList9);
	 reportForm.setProgram9(program9);

	 
	 /*
	  * 
	  * Class 10
	  */
	 List<Intake> list10 = intakeDao.listClass("Student Teacher", farm);
	 List<CwtMaster> masterList10 = new ArrayList<CwtMaster>();
	 String program10[] = new String[200];
	 
	 index=0;
	 for (Iterator iterator=list10.iterator();iterator.hasNext();) {
		 Intake intake = (Intake)iterator.next();
		 
		 CwtProgram program = new CwtProgram();
		 if (intake.getCwtProgramId()!=null) {
			 program = cwtDao.find(intake.getCwtProgramId());
			 if (program==null)
				 program=new CwtProgram();
		 }
		 
		 CwtSupervisor supervisor = new CwtSupervisor();
		 if (intake.getSupervisorId()!=null) {
			 supervisor = sDao.find(intake.getSupervisorId());
			 if (supervisor==null)
				 supervisor=new CwtSupervisor();
		 }
		 
		 CwtJob job = new CwtJob();
		 if (intake.getJobId()!=null) {
			 job = jDao.find(intake.getJobId());
			 if (job==null)
				 job=new CwtJob();
	 	 }
		 
		 List<StudentHistory> history = hDao.findByIntakeId(new StudentHistory().getClass(),intake.getIntakeId());
		 	StudentHistory studentHistory = null;
		 	for (Iterator iterator2=history.iterator();iterator2.hasNext();)
		 		 studentHistory = (StudentHistory)iterator2.next();
		 	if (studentHistory!=null)
		 		program10[index]=studentHistory.getPhase();
		 	else
		 		program10[index]="";
	 index++;
	 
	 CwtMaster master10 = new CwtMaster();
	 master10.setIntake(intake);
	 master10.setCwtJob(job);
	 master10.setCwtSupervisor(supervisor);
	 master10.setProgram(program);
	 masterList10.add(master10);
	 }
	 reportForm.setClass10CwtMasterList(masterList10);
	 reportForm.setProgram10(program10);
	 
	 
	 /*
	  * 
	  * Class 11
	  */
	 List<Intake> list11 = intakeDao.listClass("Omega", farm);
	 List<CwtMaster> masterList11 = new ArrayList<CwtMaster>();
	 String program11[] = new String[200];
	 
	 index=0;
	 for (Iterator iterator=list11.iterator();iterator.hasNext();) {
		 Intake intake = (Intake)iterator.next();
		 
		 CwtProgram program = new CwtProgram();
		 if (intake.getCwtProgramId()!=null) {
			 program = cwtDao.find(intake.getCwtProgramId());
			 if (program==null)
				 program=new CwtProgram();
		 }
		 
		 CwtSupervisor supervisor = new CwtSupervisor();
		 if (intake.getSupervisorId()!=null) {
			 supervisor = sDao.find(intake.getSupervisorId());
			 if (supervisor==null)
				 supervisor=new CwtSupervisor();
		 }
		 
		 CwtJob job = new CwtJob();
		 if (intake.getJobId()!=null) {
			 job = jDao.find(intake.getJobId());
			 if (job==null)
				 job=new CwtJob();
	 	 }
		 
		 List<StudentHistory> history = hDao.findByIntakeId(new StudentHistory().getClass(),intake.getIntakeId());
		 	StudentHistory studentHistory = null;
		 	for (Iterator iterator2=history.iterator();iterator2.hasNext();)
		 		 studentHistory = (StudentHistory)iterator2.next();
		 	if (studentHistory!=null)
		 		program11[index]=studentHistory.getPhase();
		 	else
		 		program11[index]="";
	 index++;
	 
	 CwtMaster master11 = new CwtMaster();
	 master11.setIntake(intake);
	 master11.setCwtJob(job);
	 master11.setCwtSupervisor(supervisor);
	 master11.setProgram(program);
	 masterList11.add(master11);
	 }
	 reportForm.setClass11CwtMasterList(masterList11);
	 reportForm.setProgram11(program11);


	}
	
	
}
