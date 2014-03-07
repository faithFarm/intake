package org.faithfarm.sms.application;


import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.faithfarm.sms.domain.CwtSupervisor;
import org.faithfarm.sms.domain.Intake;
import org.faithfarm.sms.hibernate.dao.CwtSupervisorDao;
import org.faithfarm.sms.hibernate.dao.IntakeDao;
import org.faithfarm.sms.util.Validator;

public class Controller extends HttpServlet {

	private String URL = "";
	private Validator v = new Validator();
	private final static Logger LOGGER = Logger.getLogger(Controller.class
			.getName());

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		LOGGER.setLevel(Level.SEVERE);
		HttpSession session = request.getSession(true);

		 if (!v.isAuthenticated(request))		
			 URL="pages/security/index.jsp";
		 
		try {
			String action = request.getParameter("action");
			String entity = request.getParameter("entity");
			
			URL = "pages/index.jsp";
			/*
			if ("1".equals(action)) {
				CwtSupervisorDao dao = new CwtSupervisorDao();
				CwtSupervisor obj = dao.findById((new Long(10002)));
				System.out.println("intake list="+obj.getFirstname());
				
				/*
				QuestionDao dao = new QuestionDao();
				List<Question>list = dao.listQuestions();
				System.out.println("question list="+list.size());
				
				JobSkillDao dao1 = new JobSkillDao();
				List<JobSkill>list1 = dao1.listJobSkills();
				System.out.println("job list="+list1.size());
				
				MedicalConditionDao dao2 = new MedicalConditionDao();
				List<MedicalCondition>list2 = dao2.listMedicalConditions();
				System.out.println("med list="+list2.size());
				
				StateDao dao3 = new StateDao();
				List<State>list3 = dao3.listStates();
				System.out.println("state list="+list3.size());
				
			}*/
		} catch (Exception e) {
			session.setAttribute("SYSTEM_ERR", e.getMessage());
			LOGGER.log(Level.SEVERE,e.getMessage());
			URL = "pages/error.jsp";
		}
		
		
		request.getRequestDispatcher("/" + URL).forward(request, response);
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doGet(req, resp);
	}
        
  

}