package org.faithfarm.sms.struts.form;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts.action.ActionForm;
import org.faithfarm.sms.domain.CwtMaster;
import org.faithfarm.sms.domain.Intake;
import org.faithfarm.sms.domain.ViewFastFind;

public class ReportForm extends ActionForm {

	private List<Intake> class0List = new ArrayList<Intake>();
	private List<Intake> class1List = new ArrayList<Intake>();
	private List<Intake> class2List = new ArrayList<Intake>();
	private List<Intake> class3List = new ArrayList<Intake>();
	private List<Intake> class4List = new ArrayList<Intake>();
	private List<Intake> class5List = new ArrayList<Intake>();
	private List<Intake> class6List = new ArrayList<Intake>();
	private List<Intake> class7List = new ArrayList<Intake>();
	private List<Intake> class8List = new ArrayList<Intake>();
	private List<Intake> class9List = new ArrayList<Intake>();
	private List<Intake> class10List = new ArrayList<Intake>();
	private List<Intake> class11List = new ArrayList<Intake>();

	private List<CwtMaster> class0CwtMasterList = new ArrayList<CwtMaster>();
	private List<CwtMaster> class1CwtMasterList = new ArrayList<CwtMaster>();
	private List<CwtMaster> class2CwtMasterList = new ArrayList<CwtMaster>();
	private List<CwtMaster> class3CwtMasterList = new ArrayList<CwtMaster>();
	private List<CwtMaster> class4CwtMasterList = new ArrayList<CwtMaster>();
	private List<CwtMaster> class5CwtMasterList = new ArrayList<CwtMaster>();
	private List<CwtMaster> class6CwtMasterList = new ArrayList<CwtMaster>();
	private List<CwtMaster> class7CwtMasterList = new ArrayList<CwtMaster>();
	private List<CwtMaster> class8CwtMasterList = new ArrayList<CwtMaster>();
	private List<CwtMaster> class9CwtMasterList = new ArrayList<CwtMaster>();
	private List<CwtMaster> class10CwtMasterList = new ArrayList<CwtMaster>();
	private List<CwtMaster> class11CwtMasterList = new ArrayList<CwtMaster>();

	private List<ViewFastFind> fastFindList = new ArrayList<ViewFastFind>();
	
	private String farmBase = "";
	private String reportTitle = "";
	private String runDate = "";
	private String action = "";
	private String passDate = "";
	private String beginPassDate = "";
	private String endPassDate = "";
	
	private List<Intake> computerList = new ArrayList<Intake>();
	private List<Intake> truckDriverList = new ArrayList<Intake>();
	private List<Intake> fixItList = new ArrayList<Intake>();
	private List<Intake> canteenList = new ArrayList<Intake>();
	private List<Intake> kitchenList = new ArrayList<Intake>();
	private List<Intake> laundryList = new ArrayList<Intake>();
	private List<Intake> houseCrewList = new ArrayList<Intake>();
	private List<Intake> garageList = new ArrayList<Intake>();
	private List<Intake> groundsCrewList = new ArrayList<Intake>();
	private List<Intake> mowerRepairList = new ArrayList<Intake>();
	private List<Intake> gateGuardsList = new ArrayList<Intake>();
	private List<Intake> frontOfficeList = new ArrayList<Intake>();
	private List<Intake> courierList = new ArrayList<Intake>();
	private List<Intake> dtcHousemanList = new ArrayList<Intake>();
	private List<Intake> applianceList = new ArrayList<Intake>();
	private List<Intake> booksList = new ArrayList<Intake>();
	private List<Intake> bricBracSortingList = new ArrayList<Intake>();
	private List<Intake> clothingSortingList = new ArrayList<Intake>();
	private List<Intake> salvageList = new ArrayList<Intake>();
	private List<Intake> dockLeadList = new ArrayList<Intake>();
	private List<Intake> dockWorkerList = new ArrayList<Intake>();
	private List<Intake> dispatchList = new ArrayList<Intake>();
	private List<Intake> radioManList = new ArrayList<Intake>();
	private List<Intake> schedulingList = new ArrayList<Intake>();
	private List<Intake> delCoordinatorList = new ArrayList<Intake>();
	private List<Intake> helperList = new ArrayList<Intake>();
	private List<Intake> outsideSalesList = new ArrayList<Intake>();
	private List<Intake> customerPickUpList = new ArrayList<Intake>();
	private List<Intake> newFurnitureList = new ArrayList<Intake>();
	private List<Intake> asIsSalesList = new ArrayList<Intake>();
	private List<Intake> bricBracRoomList = new ArrayList<Intake>();
	private List<Intake> bricBracCorralList = new ArrayList<Intake>();
	private List<Intake> clothingWomensList = new ArrayList<Intake>();
	private List<Intake> newFurnitureWarehouseList = new ArrayList<Intake>();
	private List<Intake> maintenanceList = new ArrayList<Intake>();
	private List<Intake> bicycleRepairList = new ArrayList<Intake>();
	private List<Intake> donationPickUpList = new ArrayList<Intake>();
	private List<Intake> careerCenterList = new ArrayList<Intake>();
	private List<Intake> atCorpList = new ArrayList<Intake>();
	private List<Intake> dairyList = new ArrayList<Intake>();
	
	private int  computerListSize = 0;
	private int  truckDriverListSize = 0;
	private int  fixItListSize = 0;
	private int  canteenListSize = 0;
	private int  kitchenListSize = 0;
	private int  laundryListSize = 0;
	private int  houseCrewListSize = 0;
	private int  garageListSize = 0;
	private int  groundsCrewListSize = 0;
	private int  mowerRepairListSize = 0;
	private int  gateGuardsListSize = 0;
	private int  frontOfficeListSize = 0;
	private int  courierListSize = 0;
	private int  dtcHousemanListSize = 0;
	private int  applianceListSize = 0;
	private int  booksListSize = 0;
	private int  bricBracSortingListSize = 0;
	private int  clothingSortingListSize = 0;
	private int  salvageListSize = 0;
	private int  dockLeadListSize = 0;
	private int  dockWorkerListSize = 0;
	private int  dispatchListSize = 0;
	private int  radioManListSize = 0;
	private int  schedulingListSize = 0;
	private int  delCoordinatorListSize = 0;
	private int  helperListSize = 0;
	private int  outsideSalesListSize = 0;
	private int  customerPickUpListSize = 0;
	private int  newFurnitureListSize = 0;
	private int  asIsSalesListSize = 0;
	private int  bricBracRoomListSize = 0;
	private int  bricBracCorralListSize = 0;
	private int  clothingWomensListSize = 0;
	private int  newFurnitureWarehouseListSize = 0;
	private int  maintenanceListSize = 0;
	private int  bicycleRepairListSize = 0;
	private int  donationPickUpListSize = 0;
	private int  careerCenterListSize = 0;
	private int  atCorpListSize = 0;
	private int  dairyListSize = 0;
	
	
	private String classTitle0 = "Orientation";
	private String classTitle1 = "Class 1";
	private String classTitle2 = "Class 2";
	private String classTitle3 = "Class 3";
	private String classTitle4 = "Class 4";
	private String classTitle5 = "Class 5";
	private String classTitle6 = "Class 6";
	private String classTitle7 = "Fresh Start";
	private String classTitle8 = "Interns";
	private String classTitle9 = "SLS";
	private String classTitle10 = "Student Teacher";
	private String classTitle11 = "Omega";

	private String[] class0flag = new String[100];
	private String[] class1flag = new String[100];
	private String[] class2flag = new String[100];
	private String[] class3flag = new String[100];
	private String[] class4flag = new String[100];
	private String[] class5flag = new String[100];
	private String[] class6flag = new String[100];

	String program0[] = new String[200];
	String program1[] = new String[200];
	String program2[] = new String[200];
	String program3[] = new String[200];
	String program4[] = new String[200];
	String program5[] = new String[200];
	String program6[] = new String[200];
	String program7[] = new String[200];
	String program8[] = new String[200];
	String program9[] = new String[200];
	String program10[] = new String[200];
	String program11[] = new String[200];

	private String lastRotationDate = "";
	private String lastRotatedBy = "";

	public List<CwtMaster> getClass0CwtMasterList() {
		return class0CwtMasterList;
	}

	public void setClass0CwtMasterList(List<CwtMaster> class0CwtMasterList) {
		this.class0CwtMasterList = class0CwtMasterList;
	}

	public List<CwtMaster> getClass1CwtMasterList() {
		return class1CwtMasterList;
	}

	public void setClass1CwtMasterList(List<CwtMaster> class1CwtMasterList) {
		this.class1CwtMasterList = class1CwtMasterList;
	}

	public List<CwtMaster> getClass2CwtMasterList() {
		return class2CwtMasterList;
	}

	public void setClass2CwtMasterList(List<CwtMaster> class2CwtMasterList) {
		this.class2CwtMasterList = class2CwtMasterList;
	}

	public List<CwtMaster> getClass3CwtMasterList() {
		return class3CwtMasterList;
	}

	public void setClass3CwtMasterList(List<CwtMaster> class3CwtMasterList) {
		this.class3CwtMasterList = class3CwtMasterList;
	}

	public List<CwtMaster> getClass4CwtMasterList() {
		return class4CwtMasterList;
	}

	public void setClass4CwtMasterList(List<CwtMaster> class4CwtMasterList) {
		this.class4CwtMasterList = class4CwtMasterList;
	}

	public List<CwtMaster> getClass5CwtMasterList() {
		return class5CwtMasterList;
	}

	public void setClass5CwtMasterList(List<CwtMaster> class5CwtMasterList) {
		this.class5CwtMasterList = class5CwtMasterList;
	}

	public List<CwtMaster> getClass6CwtMasterList() {
		return class6CwtMasterList;
	}

	public void setClass6CwtMasterList(List<CwtMaster> class6CwtMasterList) {
		this.class6CwtMasterList = class6CwtMasterList;
	}

	public List<CwtMaster> getClass7CwtMasterList() {
		return class7CwtMasterList;
	}

	public void setClass7CwtMasterList(List<CwtMaster> class7CwtMasterList) {
		this.class7CwtMasterList = class7CwtMasterList;
	}

	public List<CwtMaster> getClass8CwtMasterList() {
		return class8CwtMasterList;
	}

	public void setClass8CwtMasterList(List<CwtMaster> class8CwtMasterList) {
		this.class8CwtMasterList = class8CwtMasterList;
	}

	public List<CwtMaster> getClass9CwtMasterList() {
		return class9CwtMasterList;
	}

	public void setClass9CwtMasterList(List<CwtMaster> class9CwtMasterList) {
		this.class9CwtMasterList = class9CwtMasterList;
	}

	public List<CwtMaster> getClass10CwtMasterList() {
		return class10CwtMasterList;
	}

	public void setClass10CwtMasterList(List<CwtMaster> class10CwtMasterList) {
		this.class10CwtMasterList = class10CwtMasterList;
	}

	public String getLastRotationDate() {
		return lastRotationDate;
	}

	public void setLastRotationDate(String lastRotationDate) {
		this.lastRotationDate = lastRotationDate;
	}

	public String getLastRotatedBy() {
		return lastRotatedBy;
	}

	public void setLastRotatedBy(String lastRotatedBy) {
		this.lastRotatedBy = lastRotatedBy;
	}

	public String[] getClass1flag() {
		return class1flag;
	}

	public void setClass1flag(String[] class1flag) {
		this.class1flag = class1flag;
	}

	public String[] getClass2flag() {
		return class2flag;
	}

	public void setClass2flag(String[] class2flag) {
		this.class2flag = class2flag;
	}

	public String[] getClass3flag() {
		return class3flag;
	}

	public void setClass3flag(String[] class3flag) {
		this.class3flag = class3flag;
	}

	public String[] getClass4flag() {
		return class4flag;
	}

	public void setClass4flag(String[] class4flag) {
		this.class4flag = class4flag;
	}

	public String[] getClass5flag() {
		return class5flag;
	}

	public void setClass5flag(String[] class5flag) {
		this.class5flag = class5flag;
	}

	public String[] getClass6flag() {
		return class6flag;
	}

	public void setClass6flag(String[] class6flag) {
		this.class6flag = class6flag;
	}

	public String[] getClass0flag() {
		return class0flag;
	}

	public void setClass0flag(String[] class0flag) {
		this.class0flag = class0flag;
	}

	public String getClassTitle0() {
		return classTitle0;
	}

	public void setClassTitle0(String classTitle0) {
		this.classTitle0 = classTitle0;
	}

	public String getClassTitle1() {
		return classTitle1;
	}

	public void setClassTitle1(String classTitle1) {
		this.classTitle1 = classTitle1;
	}

	public String getClassTitle2() {
		return classTitle2;
	}

	public void setClassTitle2(String classTitle2) {
		this.classTitle2 = classTitle2;
	}

	public String getClassTitle3() {
		return classTitle3;
	}

	public void setClassTitle3(String classTitle3) {
		this.classTitle3 = classTitle3;
	}

	public String getClassTitle4() {
		return classTitle4;
	}

	public void setClassTitle4(String classTitle4) {
		this.classTitle4 = classTitle4;
	}

	public String getClassTitle5() {
		return classTitle5;
	}

	public void setClassTitle5(String classTitle5) {
		this.classTitle5 = classTitle5;
	}

	public String getClassTitle6() {
		return classTitle6;
	}

	public void setClassTitle6(String classTitle6) {
		this.classTitle6 = classTitle6;
	}

	public String getClassTitle7() {
		return classTitle7;
	}

	public void setClassTitle7(String classTitle7) {
		this.classTitle7 = classTitle7;
	}

	public String getClassTitle8() {
		return classTitle8;
	}

	public void setClassTitle8(String classTitle8) {
		this.classTitle8 = classTitle8;
	}

	public String getClassTitle9() {
		return classTitle9;
	}

	public void setClassTitle9(String classTitle9) {
		this.classTitle9 = classTitle9;
	}

	public String getClassTitle10() {
		return classTitle10;
	}

	public void setClassTitle10(String classTitle10) {
		this.classTitle10 = classTitle10;
	}

	public List<Intake> getClass0List() {
		return class0List;
	}

	public void setClass0List(List<Intake> class0List) {
		this.class0List = class0List;
	}

	public List<Intake> getClass1List() {
		return class1List;
	}

	public void setClass1List(List<Intake> class1List) {
		this.class1List = class1List;
	}

	public List<Intake> getClass2List() {
		return class2List;
	}

	public void setClass2List(List<Intake> class2List) {
		this.class2List = class2List;
	}

	public List<Intake> getClass3List() {
		return class3List;
	}

	public void setClass3List(List<Intake> class3List) {
		this.class3List = class3List;
	}

	public List<Intake> getClass4List() {
		return class4List;
	}

	public void setClass4List(List<Intake> class4List) {
		this.class4List = class4List;
	}

	public List<Intake> getClass5List() {
		return class5List;
	}

	public void setClass5List(List<Intake> class5List) {
		this.class5List = class5List;
	}

	public List<Intake> getClass6List() {
		return class6List;
	}

	public void setClass6List(List<Intake> class6List) {
		this.class6List = class6List;
	}

	public List<Intake> getClass7List() {
		return class7List;
	}

	public void setClass7List(List<Intake> class7List) {
		this.class7List = class7List;
	}

	public List<Intake> getClass8List() {
		return class8List;
	}

	public void setClass8List(List<Intake> class8List) {
		this.class8List = class8List;
	}

	public List<Intake> getClass9List() {
		return class9List;
	}

	public void setClass9List(List<Intake> class9List) {
		this.class9List = class9List;
	}

	public List<Intake> getClass10List() {
		return class10List;
	}

	public void setClass10List(List<Intake> class10List) {
		this.class10List = class10List;
	}

	public String getFarmBase() {
		return farmBase;
	}

	public void setFarmBase(String farmBase) {
		this.farmBase = farmBase;
	}

	public String getReportTitle() {
		return reportTitle;
	}

	public void setReportTitle(String reportTitle) {
		this.reportTitle = reportTitle;
	}

	public String getRunDate() {
		return runDate;
	}

	public void setRunDate(String runDate) {
		this.runDate = runDate;
	}

	public String[] getProgram0() {
		return program0;
	}

	public void setProgram0(String[] program0) {
		this.program0 = program0;
	}

	public String[] getProgram1() {
		return program1;
	}

	public void setProgram1(String[] program1) {
		this.program1 = program1;
	}

	public String[] getProgram2() {
		return program2;
	}

	public void setProgram2(String[] program2) {
		this.program2 = program2;
	}

	public String[] getProgram3() {
		return program3;
	}

	public void setProgram3(String[] program3) {
		this.program3 = program3;
	}

	public String[] getProgram4() {
		return program4;
	}

	public void setProgram4(String[] program4) {
		this.program4 = program4;
	}

	public String[] getProgram5() {
		return program5;
	}

	public void setProgram5(String[] program5) {
		this.program5 = program5;
	}

	public String[] getProgram6() {
		return program6;
	}

	public void setProgram6(String[] program6) {
		this.program6 = program6;
	}

	public String[] getProgram7() {
		return program7;
	}

	public void setProgram7(String[] program7) {
		this.program7 = program7;
	}

	public String[] getProgram8() {
		return program8;
	}

	public void setProgram8(String[] program8) {
		this.program8 = program8;
	}

	public String[] getProgram9() {
		return program9;
	}

	public void setProgram9(String[] program9) {
		this.program9 = program9;
	}

	public String[] getProgram10() {
		return program10;
	}

	public void setProgram10(String[] program10) {
		this.program10 = program10;
	}

	public List<Intake> getComputerList() {
		return computerList;
	}

	public void setComputerList(List<Intake> computerList) {
		this.computerList = computerList;
	}

	public List<Intake> getTruckDriverList() {
		return truckDriverList;
	}

	public void setTruckDriverList(List<Intake> truckDriverList) {
		this.truckDriverList = truckDriverList;
	}

	public List<Intake> getFixItList() {
		return fixItList;
	}

	public void setFixItList(List<Intake> fixItList) {
		this.fixItList = fixItList;
	}

	public int getComputerListSize() {
		return computerListSize;
	}

	public void setComputerListSize(int computerListSize) {
		this.computerListSize = computerListSize;
	}

	public int getTruckDriverListSize() {
		return truckDriverListSize;
	}

	public void setTruckDriverListSize(int truckDriverListSize) {
		this.truckDriverListSize = truckDriverListSize;
	}

	public int getFixItListSize() {
		return fixItListSize;
	}

	public void setFixItListSize(int fixItListSize) {
		this.fixItListSize = fixItListSize;
	}

	public List<Intake> getCanteenList() {
		return canteenList;
	}

	public void setCanteenList(List<Intake> canteenList) {
		this.canteenList = canteenList;
	}

	public List<Intake> getKitchenList() {
		return kitchenList;
	}

	public void setKitchenList(List<Intake> kitchenList) {
		this.kitchenList = kitchenList;
	}

	public List<Intake> getLaundryList() {
		return laundryList;
	}

	public void setLaundryList(List<Intake> laundryList) {
		this.laundryList = laundryList;
	}

	public List<Intake> getHouseCrewList() {
		return houseCrewList;
	}

	public void setHouseCrewList(List<Intake> houseCrewList) {
		this.houseCrewList = houseCrewList;
	}

	public List<Intake> getGarageList() {
		return garageList;
	}

	public void setGarageList(List<Intake> garageList) {
		this.garageList = garageList;
	}

	public List<Intake> getGroundsCrewList() {
		return groundsCrewList;
	}

	public void setGroundsCrewList(List<Intake> groundsCrewList) {
		this.groundsCrewList = groundsCrewList;
	}

	public List<Intake> getMowerRepairList() {
		return mowerRepairList;
	}

	public void setMowerRepairList(List<Intake> mowerRepairList) {
		this.mowerRepairList = mowerRepairList;
	}

	public List<Intake> getGateGuardsList() {
		return gateGuardsList;
	}

	public void setGateGuardsList(List<Intake> gateGuardsList) {
		this.gateGuardsList = gateGuardsList;
	}

	public List<Intake> getFrontOfficeList() {
		return frontOfficeList;
	}

	public void setFrontOfficeList(List<Intake> frontOfficeList) {
		this.frontOfficeList = frontOfficeList;
	}

	public List<Intake> getCourierList() {
		return courierList;
	}

	public void setCourierList(List<Intake> courierList) {
		this.courierList = courierList;
	}

	public List<Intake> getDtcHousemanList() {
		return dtcHousemanList;
	}

	public void setDtcHousemanList(List<Intake> dtcHousemanList) {
		this.dtcHousemanList = dtcHousemanList;
	}

	public List<Intake> getApplianceList() {
		return applianceList;
	}

	public void setApplianceList(List<Intake> applianceList) {
		this.applianceList = applianceList;
	}

	public List<Intake> getBooksList() {
		return booksList;
	}

	public void setBooksList(List<Intake> booksList) {
		this.booksList = booksList;
	}

	public List<Intake> getBricBracSortingList() {
		return bricBracSortingList;
	}

	public void setBricBracSortingList(List<Intake> bricBracSortingList) {
		this.bricBracSortingList = bricBracSortingList;
	}

	public List<Intake> getClothingSortingList() {
		return clothingSortingList;
	}

	public void setClothingSortingList(List<Intake> clothingSortingList) {
		this.clothingSortingList = clothingSortingList;
	}

	public List<Intake> getSalvageList() {
		return salvageList;
	}

	public void setSalvageList(List<Intake> salvageList) {
		this.salvageList = salvageList;
	}

	public List<Intake> getDockLeadList() {
		return dockLeadList;
	}

	public void setDockLeadList(List<Intake> dockLeadList) {
		this.dockLeadList = dockLeadList;
	}

	public List<Intake> getDockWorkerList() {
		return dockWorkerList;
	}

	public void setDockWorkerList(List<Intake> dockWorkerList) {
		this.dockWorkerList = dockWorkerList;
	}

	public List<Intake> getDispatchList() {
		return dispatchList;
	}

	public void setDispatchList(List<Intake> dispatchList) {
		this.dispatchList = dispatchList;
	}

	public List<Intake> getRadioManList() {
		return radioManList;
	}

	public void setRadioManList(List<Intake> radioManList) {
		this.radioManList = radioManList;
	}

	public List<Intake> getSchedulingList() {
		return schedulingList;
	}

	public void setSchedulingList(List<Intake> schedulingList) {
		this.schedulingList = schedulingList;
	}

	public List<Intake> getDelCoordinatorList() {
		return delCoordinatorList;
	}

	public void setDelCoordinatorList(List<Intake> delCoordinatorList) {
		this.delCoordinatorList = delCoordinatorList;
	}

	public List<Intake> getHelperList() {
		return helperList;
	}

	public void setHelperList(List<Intake> helperList) {
		this.helperList = helperList;
	}

	public List<Intake> getOutsideSalesList() {
		return outsideSalesList;
	}

	public void setOutsideSalesList(List<Intake> outsideSalesList) {
		this.outsideSalesList = outsideSalesList;
	}

	public List<Intake> getCustomerPickUpList() {
		return customerPickUpList;
	}

	public void setCustomerPickUpList(List<Intake> customerPickUpList) {
		this.customerPickUpList = customerPickUpList;
	}

	public List<Intake> getNewFurnitureList() {
		return newFurnitureList;
	}

	public void setNewFurnitureList(List<Intake> newFurnitureList) {
		this.newFurnitureList = newFurnitureList;
	}

	public List<Intake> getAsIsSalesList() {
		return asIsSalesList;
	}

	public void setAsIsSalesList(List<Intake> asIsSalesList) {
		this.asIsSalesList = asIsSalesList;
	}

	public List<Intake> getBricBracRoomList() {
		return bricBracRoomList;
	}

	public void setBricBracRoomList(List<Intake> bricBracRoomList) {
		this.bricBracRoomList = bricBracRoomList;
	}

	public List<Intake> getBricBracCorralList() {
		return bricBracCorralList;
	}

	public void setBricBracCorralList(List<Intake> bricBracCorralList) {
		this.bricBracCorralList = bricBracCorralList;
	}

	public List<Intake> getClothingWomensList() {
		return clothingWomensList;
	}

	public void setClothingWomensList(List<Intake> clothingWomensList) {
		this.clothingWomensList = clothingWomensList;
	}

	public List<Intake> getNewFurnitureWarehouseList() {
		return newFurnitureWarehouseList;
	}

	public void setNewFurnitureWarehouseList(List<Intake> newFurnitureWarehouseList) {
		this.newFurnitureWarehouseList = newFurnitureWarehouseList;
	}

	public List<Intake> getMaintenanceList() {
		return maintenanceList;
	}

	public void setMaintenanceList(List<Intake> maintenanceList) {
		this.maintenanceList = maintenanceList;
	}

	public List<Intake> getBicycleRepairList() {
		return bicycleRepairList;
	}

	public void setBicycleRepairList(List<Intake> bicycleRepairList) {
		this.bicycleRepairList = bicycleRepairList;
	}

	public List<Intake> getDonationPickUpList() {
		return donationPickUpList;
	}

	public void setDonationPickUpList(List<Intake> donationPickUpList) {
		this.donationPickUpList = donationPickUpList;
	}

	public List<Intake> getCareerCenterList() {
		return careerCenterList;
	}

	public void setCareerCenterList(List<Intake> careerCenterList) {
		this.careerCenterList = careerCenterList;
	}

	public List<Intake> getAtCorpList() {
		return atCorpList;
	}

	public void setAtCorpList(List<Intake> atCorpList) {
		this.atCorpList = atCorpList;
	}

	public List<Intake> getDairyList() {
		return dairyList;
	}

	public void setDairyList(List<Intake> dairyList) {
		this.dairyList = dairyList;
	}

	public int getCanteenListSize() {
		return canteenListSize;
	}

	public void setCanteenListSize(int canteenListSize) {
		this.canteenListSize = canteenListSize;
	}

	public int getKitchenListSize() {
		return kitchenListSize;
	}

	public void setKitchenListSize(int kitchenListSize) {
		this.kitchenListSize = kitchenListSize;
	}

	public int getLaundryListSize() {
		return laundryListSize;
	}

	public void setLaundryListSize(int laundryListSize) {
		this.laundryListSize = laundryListSize;
	}

	public int getHouseCrewListSize() {
		return houseCrewListSize;
	}

	public void setHouseCrewListSize(int houseCrewListSize) {
		this.houseCrewListSize = houseCrewListSize;
	}

	public int getGarageListSize() {
		return garageListSize;
	}

	public void setGarageListSize(int garageListSize) {
		this.garageListSize = garageListSize;
	}

	public int getGroundsCrewListSize() {
		return groundsCrewListSize;
	}

	public void setGroundsCrewListSize(int groundsCrewListSize) {
		this.groundsCrewListSize = groundsCrewListSize;
	}

	public int getMowerRepairListSize() {
		return mowerRepairListSize;
	}

	public void setMowerRepairListSize(int mowerRepairListSize) {
		this.mowerRepairListSize = mowerRepairListSize;
	}

	public int getGateGuardsListSize() {
		return gateGuardsListSize;
	}

	public void setGateGuardsListSize(int gateGuardsListSize) {
		this.gateGuardsListSize = gateGuardsListSize;
	}

	public int getFrontOfficeListSize() {
		return frontOfficeListSize;
	}

	public void setFrontOfficeListSize(int frontOfficeListSize) {
		this.frontOfficeListSize = frontOfficeListSize;
	}

	public int getCourierListSize() {
		return courierListSize;
	}

	public void setCourierListSize(int courierListSize) {
		this.courierListSize = courierListSize;
	}

	public int getDtcHousemanListSize() {
		return dtcHousemanListSize;
	}

	public void setDtcHousemanListSize(int dtcHousemanListSize) {
		this.dtcHousemanListSize = dtcHousemanListSize;
	}

	public int getApplianceListSize() {
		return applianceListSize;
	}

	public void setApplianceListSize(int applianceListSize) {
		this.applianceListSize = applianceListSize;
	}

	public int getBooksListSize() {
		return booksListSize;
	}

	public void setBooksListSize(int booksListSize) {
		this.booksListSize = booksListSize;
	}

	public int getBricBracSortingListSize() {
		return bricBracSortingListSize;
	}

	public void setBricBracSortingListSize(int bricBracSortingListSize) {
		this.bricBracSortingListSize = bricBracSortingListSize;
	}

	public int getClothingSortingListSize() {
		return clothingSortingListSize;
	}

	public void setClothingSortingListSize(int clothingSortingListSize) {
		this.clothingSortingListSize = clothingSortingListSize;
	}

	public int getSalvageListSize() {
		return salvageListSize;
	}

	public void setSalvageListSize(int salvageListSize) {
		this.salvageListSize = salvageListSize;
	}

	public int getDockLeadListSize() {
		return dockLeadListSize;
	}

	public void setDockLeadListSize(int dockLeadListSize) {
		this.dockLeadListSize = dockLeadListSize;
	}

	public int getDockWorkerListSize() {
		return dockWorkerListSize;
	}

	public void setDockWorkerListSize(int dockWorkerListSize) {
		this.dockWorkerListSize = dockWorkerListSize;
	}

	public int getDispatchListSize() {
		return dispatchListSize;
	}

	public void setDispatchListSize(int dispatchListSize) {
		this.dispatchListSize = dispatchListSize;
	}

	public int getRadioManListSize() {
		return radioManListSize;
	}

	public void setRadioManListSize(int radioManListSize) {
		this.radioManListSize = radioManListSize;
	}

	public int getSchedulingListSize() {
		return schedulingListSize;
	}

	public void setSchedulingListSize(int schedulingListSize) {
		this.schedulingListSize = schedulingListSize;
	}

	public int getDelCoordinatorListSize() {
		return delCoordinatorListSize;
	}

	public void setDelCoordinatorListSize(int delCoordinatorListSize) {
		this.delCoordinatorListSize = delCoordinatorListSize;
	}

	public int getHelperListSize() {
		return helperListSize;
	}

	public void setHelperListSize(int helperListSize) {
		this.helperListSize = helperListSize;
	}

	public int getOutsideSalesListSize() {
		return outsideSalesListSize;
	}

	public void setOutsideSalesListSize(int outsideSalesListSize) {
		this.outsideSalesListSize = outsideSalesListSize;
	}

	public int getCustomerPickUpListSize() {
		return customerPickUpListSize;
	}

	public void setCustomerPickUpListSize(int customerPickUpListSize) {
		this.customerPickUpListSize = customerPickUpListSize;
	}

	public int getNewFurnitureListSize() {
		return newFurnitureListSize;
	}

	public void setNewFurnitureListSize(int newFurnitureListSize) {
		this.newFurnitureListSize = newFurnitureListSize;
	}

	public int getAsIsSalesListSize() {
		return asIsSalesListSize;
	}

	public void setAsIsSalesListSize(int asIsSalesListSize) {
		this.asIsSalesListSize = asIsSalesListSize;
	}

	public int getBricBracRoomListSize() {
		return bricBracRoomListSize;
	}

	public void setBricBracRoomListSize(int bricBracRoomListSize) {
		this.bricBracRoomListSize = bricBracRoomListSize;
	}

	public int getBricBracCorralListSize() {
		return bricBracCorralListSize;
	}

	public void setBricBracCorralListSize(int bricBracCorralListSize) {
		this.bricBracCorralListSize = bricBracCorralListSize;
	}

	public int getClothingWomensListSize() {
		return clothingWomensListSize;
	}

	public void setClothingWomensListSize(int clothingWomensListSize) {
		this.clothingWomensListSize = clothingWomensListSize;
	}

	public int getNewFurnitureWarehouseListSize() {
		return newFurnitureWarehouseListSize;
	}

	public void setNewFurnitureWarehouseListSize(int newFurnitureWarehouseListSize) {
		this.newFurnitureWarehouseListSize = newFurnitureWarehouseListSize;
	}

	public int getMaintenanceListSize() {
		return maintenanceListSize;
	}

	public void setMaintenanceListSize(int maintenanceListSize) {
		this.maintenanceListSize = maintenanceListSize;
	}

	public int getBicycleRepairListSize() {
		return bicycleRepairListSize;
	}

	public void setBicycleRepairListSize(int bicycleRepairListSize) {
		this.bicycleRepairListSize = bicycleRepairListSize;
	}

	public int getDonationPickUpListSize() {
		return donationPickUpListSize;
	}

	public void setDonationPickUpListSize(int donationPickUpListSize) {
		this.donationPickUpListSize = donationPickUpListSize;
	}

	public int getCareerCenterListSize() {
		return careerCenterListSize;
	}

	public void setCareerCenterListSize(int careerCenterListSize) {
		this.careerCenterListSize = careerCenterListSize;
	}

	public int getAtCorpListSize() {
		return atCorpListSize;
	}

	public void setAtCorpListSize(int atCorpListSize) {
		this.atCorpListSize = atCorpListSize;
	}

	public int getDairyListSize() {
		return dairyListSize;
	}

	public void setDairyListSize(int dairyListSize) {
		this.dairyListSize = dairyListSize;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getPassDate() {
		return passDate;
	}

	public void setPassDate(String passDate) {
		this.passDate = passDate;
	}

	public List<Intake> getClass11List() {
		return class11List;
	}

	public void setClass11List(List<Intake> class11List) {
		this.class11List = class11List;
	}

	public List<CwtMaster> getClass11CwtMasterList() {
		return class11CwtMasterList;
	}

	public void setClass11CwtMasterList(List<CwtMaster> class11CwtMasterList) {
		this.class11CwtMasterList = class11CwtMasterList;
	}

	public String getClassTitle11() {
		return classTitle11;
	}

	public void setClassTitle11(String classTitle11) {
		this.classTitle11 = classTitle11;
	}

	public String[] getProgram11() {
		return program11;
	}

	public void setProgram11(String[] program11) {
		this.program11 = program11;
	}

	public String getBeginPassDate() {
		return beginPassDate;
	}

	public void setBeginPassDate(String beginPassDate) {
		this.beginPassDate = beginPassDate;
	}

	public String getEndPassDate() {
		return endPassDate;
	}

	public void setEndPassDate(String endPassDate) {
		this.endPassDate = endPassDate;
	}

	public List<ViewFastFind> getFastFindList() {
		return fastFindList;
	}

	public void setFastFindList(List<ViewFastFind> fastFindList) {
		this.fastFindList = fastFindList;
	}

	
	

}
