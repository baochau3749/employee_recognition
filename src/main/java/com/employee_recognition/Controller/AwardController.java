package com.employee_recognition.Controller;

import java.beans.PropertyEditorSupport;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import javax.net.ssl.HttpsURLConnection;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.request.WebRequest;

import com.amazonaws.http.HttpResponse;
import com.employee_recognition.Entity.Award;
import com.employee_recognition.Entity.AwardType;
import com.employee_recognition.Entity.Employee;
import com.employee_recognition.Entity.User;
import com.employee_recognition.Repository.AwardTypeRepository;
import com.employee_recognition.Repository.EmployeeRepository;
import com.employee_recognition.Service.AwardService;
import com.employee_recognition.Service.UserService;

@Controller
@SessionAttributes({"userID","user"})
@RequestMapping("/user")
public class AwardController {

	@Autowired
	private EmployeeRepository employeeDAO;
	
	@Autowired
	private AwardTypeRepository awardTypeDAO;
	
	@Autowired
	private UserService userDAO;
	
	@Autowired
	private AwardService awardDAO;
	
	private Award award = new Award();
	private AwardType at = new AwardType();

	private Timestamp formDate;
	
	private String awardDate;
	
	@InitBinder
	public void initBinder(WebDataBinder binder, WebRequest request) {

	        binder.registerCustomEditor(AwardType.class, "awardType", new PropertyEditorSupport() {
	         @Override
	         public void setAsText(String type) {
	            setValue((awardTypeDAO.findById(Long.parseLong((String) type))));
	         }
	     });
	        
	        binder.registerCustomEditor(Timestamp.class, "dateGiven", new PropertyEditorSupport() {
		         @Override
		         public void setAsText(String date) {
		        	 if (date.equals("")) {
		        		 formDate = new Timestamp(System.currentTimeMillis());
		        	 }
		        	 else {
		        		 SimpleDateFormat dateFormatObj = new SimpleDateFormat("MM/dd/yyyy hh:mm aaa");	        		 
		        		 java.util.Date parsedDate = null;
						try {
							parsedDate = dateFormatObj.parse(date);
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
		        		 formDate = new Timestamp(parsedDate.getTime());
		        	 }
		        	 	setValue(formDate);
		         }
		     });
	}
	
	@GetMapping("/award")
	public String employeeMainPage(@SessionAttribute("userID") Long userID, Model model) {
		User currentUser = userDAO.getUserById(userID); 
		award.setUser(currentUser);
		model.addAttribute("user", currentUser);
		//generate award list
		award.setAwardType(at);
		model.addAttribute("awardTypes", awardTypeDAO.getAwardTypeList());
		//generate employee list
		model.addAttribute("employees", employeeDAO.getEmployees());
		//add award
		model.addAttribute(award);
		return "award";
	}
	
	
	@PostMapping("/createAward")
	public String saveAward(@ModelAttribute("award")Award award, @SessionAttribute("userID") Long userId) {	
		User currentUser = userDAO.getUserById(userId); 
		award.setUser(currentUser);
		awardDAO.saveAward(award);
		//send award
		String userFirst = currentUser.getUserProfile().getFirstName();
		String userLast = currentUser.getUserProfile().getLastName();
		Employee e = employeeDAO.findById(award.getEmployee());
		String empFirst = e.getFirstName();
		String empLast = e.getLastName();
		String fileName = currentUser.getUserProfile().getTargetFile();
		String userName = userFirst + " " + userLast;
		String empName = empFirst + " " + empLast;
		String justDate = dateToString(formDate);
		//System.out.println(formDate.toString());
		sendAward(userName, empName, justDate, fileName);
		return "redirect:";
	}
	
	public void sendAward(String userName, String empName, String date, String fileName) {
		//username, employee name, date, sig
		List<NameValuePair> body = new ArrayList<NameValuePair>(2);
		body.add(new BasicNameValuePair("user", userName));
		body.add(new BasicNameValuePair("employee", empName));
		body.add(new BasicNameValuePair("date", date));
		body.add(new BasicNameValuePair("file", fileName));
		
		HttpClient client = HttpClients.createDefault();
		HttpPost post = new HttpPost("http://ec2-18-223-63-217.us-east-2.compute.amazonaws.com/latex");
		try {
			post.setEntity(new UrlEncodedFormEntity(body, "UTF-8"));
			client.execute(post);
			//HttpEntity e = res.getEntity();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public String dateToString(Timestamp date) {
		String dateString = date.toString();
		//split by space
		String sepDate[] = dateString.split("\\s+");
		return sepDate[0];
		
		
	}
}
