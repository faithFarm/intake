package org.faithfarm.sms.struts.action;

import java.io.IOException; 
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.faithfarm.sms.hibernate.dao.IntakeDao;
import org.faithfarm.sms.struts.form.IntakeForm;
import org.faithfarm.sms.util.Validator;

public class ImageAction extends Action {

	private final static Logger LOGGER = Logger.getLogger(ImageAction.class.getName());
	
	private Validator valid8r = new Validator();
	
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

		LOGGER.setLevel(Level.SEVERE);
		IntakeDao dao = new IntakeDao();

		HttpSession session = request.getSession(true);
		IntakeForm intakeForm = (IntakeForm)form;
		
		try {
			byte imgData[] = intakeForm.getIntake().getImageHeadshot();
			ServletOutputStream output = response.getOutputStream();
			response.setContentType("image/gif");
			output.write(imgData);
		} catch (IOException e) {}

		return null;
	}

	
}
