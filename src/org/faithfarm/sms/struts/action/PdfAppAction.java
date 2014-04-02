package org.faithfarm.sms.struts.action;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
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
import org.faithfarm.sms.domain.CwtModuleSection;
import org.faithfarm.sms.domain.CwtModules;
import org.faithfarm.sms.domain.CwtRoster;
import org.faithfarm.sms.domain.SystemUser;
import org.faithfarm.sms.hibernate.data.CwtModuleSectionDao;
import org.faithfarm.sms.hibernate.data.CwtModulesDao;
import org.faithfarm.sms.hibernate.data.CwtRosterDao;
import org.faithfarm.sms.hibernate.data.IntakeDao;
import org.faithfarm.sms.struts.form.IntakeForm;
import org.faithfarm.sms.util.PDFBuilder;
import org.faithfarm.sms.util.Validator;

public class PdfAppAction extends Action { 

	private final static Logger LOGGER = Logger.getLogger(ImageAction.class.getName());
	private Validator valid8r = new Validator();
	
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		
		HttpSession session = request.getSession(false);
		String action=request.getParameter("action");
		String farm=request.getParameter("farm");
		SystemUser user = (SystemUser)session.getAttribute("system_user");
		try {
			LOGGER.setLevel(Level.SEVERE);
			IntakeForm intakeForm = (IntakeForm)form;
			PDFBuilder pdf = new PDFBuilder();
					
			IntakeDao dao = new IntakeDao();
			if ("application".equals(action))
				pdf.applicationPdf(intakeForm, response);
			if ("classlist".equals(action))	
				pdf.classListPdf(user, farm, response);
			if ("waitlist".equals(action))
				pdf.waitlistPdf(user, farm, response);
			if ("omega".equals(action))
				pdf.omegaPdf(response);
			if ("occupancy".equals(action)) 
				pdf.generateOccupancyReport(user, response);
			if ("completion".equals(action)) 
					pdf.generateCompletionReport(user, response);
			if ("cwtgrad".equals(action)) 
				pdf.cwtGraduatePdf(farm, response);
			if ("Print".equals(action)) {
				String archivedFlag=request.getParameter("archivedFlag");
				CwtModuleSectionDao cwtModuleSectionDao = new CwtModuleSectionDao();
				CwtRosterDao rosterDao = new CwtRosterDao();
				CwtModulesDao cwtModulesDao = new CwtModulesDao();
				
				String sId = request.getParameter("id");
				Long sectionId = new Long(sId);
				CwtModuleSection section = cwtModuleSectionDao.find(sectionId);
				CwtModules module = cwtModulesDao.find(section.getModuleId());
				List<CwtRoster> list = new  ArrayList<CwtRoster>();
				
				if ("No".equals(archivedFlag))
					list = rosterDao.findByObjectId(CwtRoster.class, "sectionId", sectionId);
				if ("Yes".equals(archivedFlag))
					list = rosterDao.findArchivedRosters(new CwtRoster().getClass(), "sectionId", sectionId, archivedFlag);
				
				pdf.generateCwtRoster(user, module, section, list, response);
			}
		} catch(Exception e) {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			e.printStackTrace(pw);
			session.setAttribute("SYSTEM_ERROR", sw.toString());
			e.printStackTrace();
			return mapping.findForward(Constants.ERROR);
		}
		return null;
	}

	
}
