package org.faithfarm.sms.struts.action;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionRedirect;
import org.faithfarm.sms.application.Constants;
import org.faithfarm.sms.domain.ErrorMessage;
import org.faithfarm.sms.domain.IpPermission;
import org.faithfarm.sms.domain.SystemUser;
import org.faithfarm.sms.domain.UserAuthorizedSession;
import org.faithfarm.sms.hibernate.dao.UserAuthorizedSessionDao;
import org.faithfarm.sms.hibernate.data.GenericDao;
import org.faithfarm.sms.hibernate.data.SystemUserDao;
import org.faithfarm.sms.struts.form.LoginForm;
import org.faithfarm.sms.util.HtmlDropDownBuilder;
import org.faithfarm.sms.util.Validator;


public class LoginAction extends Action {
	
	private final static Logger LOGGER = Logger.getLogger(LoginAction.class.getName());
	private final static HtmlDropDownBuilder html = new HtmlDropDownBuilder();
	
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {		
		LOGGER.setLevel(Level.SEVERE);
		
		String ip=request.getRemoteAddr().toString();
		/*
			if (!"174.141.99.194".equals(ip) && !"75.147.217.62".equals(ip) && //Boynton Beach Farm
						!"70.89.102.41".equals(ip) && //FTL Farm
						!"67.238.59.138".equals(ip) && //OKE Farm
						!"76.109.62.180".equals(ip) && //EHW Farm
						!"75.149.244.9".equals(ip) && //BYN CORP
						!"76.109.55.65".equals(ip) && //Miles Office
						!"75.149.244.10".equals(ip) && //Judy's Office
						!"127.0.0.1".equals(ip)  ) { //Local Development Box
					LOGGER.log(Level.SEVERE,"INVALID IP ADDRESS TRIED TO ACCESS THE SYSTEM: "+request.getRemoteAddr().toString());
					return mapping.findForward(Constants.ACCESS_DENIED);
				}
				*/
		boolean access=false;
		
		if ("0:0:0:0:0:0:0:1".equals(ip))
			access=true;
		else {
			GenericDao dao = new GenericDao();
			List<IpPermission> list = dao.listAll(new IpPermission().getClass());
			for (int i=0;i<list.size();i++) {
				IpPermission obj = (IpPermission)list.get(i);
				if (obj.getIpAddress().equals(ip)) 
					access=true;
					
			}
		}
				
				if (!access) {
					LOGGER.log(Level.SEVERE,"INVALID IP ADDRESS TRIED TO ACCESS THE SYSTEM: "+request.getRemoteAddr().toString());
					return mapping.findForward(Constants.ACCESS_DENIED);
				}
				
		 HttpSession session = request.getSession(true);
		 
		 try {
		
			 String action = request.getParameter("action");
			 LoginForm loginForm = (LoginForm)form;

			 if ("logout".equals(action)) {
				 loginForm.setSystemUser(null);
				 session.invalidate();
				 return mapping.findForward(Constants.LOGOUT);
			 }

		 //first check to see if an existing login session is still valid
		 SystemUser user=(SystemUser)session.getAttribute("system_user");
		 if (user!=null) {
			 if ("Intake".equals(user.getGroup_())) {
				 
				 //if ("ManageUsers".equals(action)) {
				 //checks to see if manage users has been selected
				 String retValue=this.manageUsers(action, loginForm, request, session);
				 return mapping.findForward(retValue);
				 //} 
				 //else
				//	 return mapping.findForward(Constants.SUCCESS);
			 } else {
				 List<ErrorMessage> messages = new ArrayList<ErrorMessage>();
				 ActionErrors errors = new ActionErrors();
				 messages.add(new ErrorMessage("Access denied. This user is not authorized to view this application.",""));
				 loginForm.setMessages(messages); 	 
				return mapping.findForward(Constants.LOGIN);
			 }
		 }
		 
		 
		 //SystemUserHome userDao = new SystemUserHome();
		 SystemUserDao userDao = new SystemUserDao();
		 UserAuthorizedSessionDao sessionDao = new UserAuthorizedSessionDao();
		 UserAuthorizedSession sessionObj = new UserAuthorizedSession();
		 
		 ActionRedirect redirect = null;
		 
		 LOGGER.log(Level.INFO, "In login action..."+loginForm.getSystemUser().getUsername());
		 
		 if ("Login".equals(action)) {
			 boolean valid = this.validate(loginForm);
			 if (!valid)
				 return mapping.findForward(Constants.LOGIN);
			 
			 user = userDao.authenticate(loginForm.getSystemUser().getUsername(), loginForm.getSystemUser().getPassword());
			 if (user!=null) {
				 if ("Intake".equals(user.getGroup_())) {
					 html.refresh(session);
					 loginForm.setSystemUser(user);
					 session.setAttribute("system_user", user);
					 
					 if (user.getLoginCount()==0)
						 return mapping.findForward(Constants.PASSWORD_RESET);
					 else
						 return mapping.findForward(Constants.SUCCESS);
				 } else {
					 List<ErrorMessage> messages = new ArrayList<ErrorMessage>();
					 ActionErrors errors = new ActionErrors();
					 messages.add(new ErrorMessage("Access denied. This user is not authorized to view this application.",""));
					 loginForm.setMessages(messages); 	 
					return mapping.findForward(Constants.LOGIN);
				 }
			 } else
			 {
				 loginForm.getSystemUser().setUsername(null);
				 loginForm.getSystemUser().setPassword(null);
				 return mapping.findForward(Constants.FAILURE);
			 }
			 
		 }

		 return mapping.findForward(Constants.LOGIN);
		}
			catch (Exception e) {
				StringWriter sw = new StringWriter();
				PrintWriter pw = new PrintWriter(sw);
				e.printStackTrace(pw);			
				session.setAttribute("SYSTEM_ERROR", sw.toString());
				e.printStackTrace();
				return mapping.findForward(Constants.ERROR);
			}
		 //return mapping.findForward(Constants.GLOBAL_LOGIN);
		 
		 /*
		  * else if ("ManageUsers".equals(action)) { 
				 List<SystemUser> users = dao.listAll(new SystemUser().getClass());
				 loginForm.setUserList(users);
				 return mapping.findForward(Constants.MANAGE_USERS);
			 } else if ("Edit".equals(action)) {
				 String id= request.getParameter("id");
				 SystemUser obj = (SystemUser)dao.findById(new SystemUser().getClass(), new Long(id));
				 loginForm.setUser(obj);
				 return mapping.findForward(Constants.CREATE_USER);
			 }
		  */
	}
	
	public boolean validate(LoginForm loginForm) {
		  List<ErrorMessage> messages = new ArrayList<ErrorMessage>();
		  ActionErrors errors = new ActionErrors();
		  loginForm.setMessage("");
		  
		  if ((loginForm.getSystemUser().getUsername()==null) || (loginForm.getSystemUser().getUsername().length() < 1)) 
		     	messages.add(new ErrorMessage("username is required",""));
		  if ((loginForm.getSystemUser().getPassword()==null) || (loginForm.getSystemUser().getPassword().length() < 1)) 
			    messages.add(new ErrorMessage("password is required",""));
		  
		  if (messages.size()>0) {
			  loginForm.setMessages(messages); 	 
			  return false;
		  }
		  else
			  return true;
		}
	
	private String manageUsers(String action, LoginForm loginForm, HttpServletRequest request, HttpSession session) {
		
		GenericDao dao = new GenericDao();
		SystemUserDao userDao = new SystemUserDao();
		if ("PasswordReset".equals(action)) {
			 return Constants.PASSWORD_RESET;
		 }
		 else if ("Change Password".equals(action)) {
			 
			 boolean success=this.validateNewPassword(loginForm);
			 
			 if (success) {
				 SystemUser user1 = (SystemUser)session.getAttribute("system_user");
				 user1.setPassword(loginForm.getPassword1());
				 Long count=user1.getLoginCount();
				 user1.setLoginCount(++count);
				 userDao.update(user1);
				 return Constants.LOGIN;
			 }
			 else
				 return Constants.PASSWORD_RESET;
		 }
		 else if ("ManageUsers".equals(action)) {
			 loginForm.setUserList(userDao.list());
		 	 return Constants.MANAGE_USERS;
		 } else if ("Create".equals(action)) {
			 	loginForm.setUser(new SystemUser());
			 	return Constants.CREATE_USER;
		 }
		 else if ("Edit".equals(action)) { 
			 	String key = request.getParameter("id");
			 	loginForm.setUser(userDao.find(new Long(key+"")));
			 	return Constants.CREATE_USER;
		 }
		 else if ("Delete".equals(action)) {
			 	userDao.delete(loginForm.getUser());
			 	loginForm.setUserList(userDao.list());
			 	return Constants.MANAGE_USERS;
		 }
		 else if ("Save".equals(action)) { 
			 SystemUser loggedInUser = (SystemUser)loginForm.getUser(); //session.getAttribute("system_user");
			 if (loggedInUser.getLoginCount()==null)
				 loggedInUser.setLoginCount(new Long(0));
			 if (loginForm.getUser().getUserId()==null) {
				loginForm.getUser().setCreatedBy(loggedInUser.getUsername());
				loginForm.getUser().setCreationDate(Validator.getEpoch()+"");
			 	userDao.save(loginForm.getUser());
			 }
			 else {				
				loginForm.getUser().setLastUpdatedBy(loggedInUser.getUsername());
				loginForm.getUser().setLastUpdatedDate(Validator.getEpoch()+"");
				userDao.update(loginForm.getUser());
			 }
			 	loginForm.setUserList(userDao.list());
			 	return Constants.MANAGE_USERS;
		 }
		 else
			 return Constants.SUCCESS;
	}
	
	public boolean validateNewPassword(LoginForm loginForm) {
		
		String pwd1=loginForm.getPassword1();
		String pwd2=loginForm.getPassword2();
		
		//check if passwords match
		if (!pwd1.equals(pwd2)) {
			loginForm.setMessage("passwords must match");
			return false;
		}
		//check length of password
		if (pwd1.length()<8||pwd1.length()>15) {
			loginForm.setMessage("password must be between 8 and 15 characters long");
			return false;
		}
		//check for uppercase
		int count=0;
		for ( int i=0;i<pwd1.length();i++) {
			Character ch=pwd1.charAt(i);
			
			if (ch.isUpperCase(ch))
				count++;
		}
		if (count==0) {
			loginForm.setMessage("password must contain at least 1 uppercase letter");
			return false;
		}
		
		//check for lowercase
		count=0;
		for ( int i=0;i<pwd1.length();i++) {
			Character ch=pwd1.charAt(i);
			
			if (ch.isLowerCase(ch))
				count++;
		}
		if (count==0) {
			loginForm.setMessage("password must contain at least 1 lowercase letter");
			return false;
		}
		//check for digit
		count=0;
		for ( int i=0;i<9;i++) {
			if (pwd1.contains((i+"")))
				count++;
		}
		if (count==0) {
			loginForm.setMessage("password must contain at least 1 number (0-9)");
			return false;
		}
		//check for alpha-numeric
		count=0;
		if (pwd1.contains("!")) count++;
		if (pwd1.contains("#")) count++;
		if (pwd1.contains("@")) count++;
		if (pwd1.contains("$")) count++;
		if (pwd1.contains("%")) count++;
		if (pwd1.contains("^")) count++;
		if (pwd1.contains("&")) count++;
		if (pwd1.contains("*")) count++;
		if (pwd1.contains("(")) count++;
		if (pwd1.contains(")")) count++;
		if (pwd1.contains("-")) count++;
		if (pwd1.contains("_")) count++;
		if (pwd1.contains("+")) count++;
		if (pwd1.contains("=")) count++;
		if (pwd1.contains("[")) count++;
		if (pwd1.contains("]")) count++;
		if (pwd1.contains("{")) count++;
		if (pwd1.contains("}")) count++;
		if (pwd1.contains("<!")) count++;
		if (pwd1.contains(">")) count++;
		if (pwd1.contains("?")) count++;
		if (pwd1.contains("~")) count++;
		if (pwd1.contains("`")) count++;
		if (pwd1.contains("\\")) count++;
		if (pwd1.contains("|")) count++;
		if (pwd1.contains("/")) count++;
		if (count==0) {
			loginForm.setMessage("password must contain at least 1 special character (!, @, #, %, etc.)");
			return false;
		}
		
		
		
		return true;
	}
	
}
