package leon.ssi.action;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import leon.ssi.model.Student;
import leon.ssi.model.StudentCourseDetailBean;
import leon.ssi.service.StudentService;
import leon.ssi.util.StringUtil;
import leon.ssi.util.WebUtil;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;

@Controller("StudentAction")
public class StudentAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	protected final Log logger = LogFactory.getLog(getClass());
	
	@Resource
	private StudentService studentService;
	
	private List<Student> studentList;
	private List<StudentCourseDetailBean> studentCourseList;
	
	private String studentId;
	private String studentName;
	private String enableFuzzy;
	
	
	public String toStudentManagement()throws Exception{
		try {
			Map<String,String> param = new HashMap<String,String>();
			
			if(!StringUtil.isEmpty(studentId))
				param.put("studentId", studentId);
			if(!StringUtil.isEmpty(studentName))
				param.put("studentName", studentName);
			if(!StringUtil.isEmpty(enableFuzzy))
				param.put("enableFuzzy", enableFuzzy);
			
			studentList = studentService.getAllStudent(param);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	public String ajaxGetStudentCourseDetail() throws Exception{
        try{
            Map<String,String> param = new HashMap<String,String>();
            if(!StringUtil.isEmpty(studentId))
                param.put("studentId", studentId);
            List<StudentCourseDetailBean> studentCourseList = this.studentService.getStudentCourseDetail(param);
            StringBuffer text = new StringBuffer("<table width='100%' >");
            text.append("<tr bgcolor='#00AA88' >");
            text.append("<th width='100' >"+getText("label.studentName")+"</th>");
            text.append("<th>"+getText("label.courseName")+"</th>");
            text.append("<th width='100' >"+getText("label.score")+"</th>");
            text.append("<th width='100' >"+getText("label.teacherName")+"</th>");
            text.append("</tr>");
            
            int i=0;
            StudentCourseDetailBean bean=null;
            for(i=0;i<studentCourseList.size();i++){
            	bean=studentCourseList.get(i);
            	if(WebUtil.isEven(i)){
            		text.append("<tr>");
            	}else{
            		text.append("<tr bgcolor='#99FFFF' >");
            	}
                text.append("<td>"+bean.getStudentName()+"</td>");
                text.append("<td>"+bean.getCourseName()+"</td>");
                text.append("<td>"+bean.getScore()+"</td>");
                text.append("<td>"+bean.getTeacherName()+"</td>");
                text.append("</tr>");
            }
            text.append("</table>");
            HttpServletResponse res = ServletActionContext.getResponse();
            res.setContentType("text/html; charset=utf-8");
            PrintWriter pw = res.getWriter();
            pw.write(text.toString());
             
        }catch(Exception e){
        	logger.debug(this.getClass().getName()+"-->ajaxGetStudentCourseDetail"+e.getMessage());
            throw new Exception(e);
        }
        return null;
    }
	
	public String jsonGetStudentCourseDetail() throws Exception{
		try {
			Map<String,String> param = new HashMap<String,String>();
            if(!StringUtil.isEmpty(studentId))
                param.put("studentId", studentId);
            
            studentCourseList = this.studentService.getStudentCourseDetail(param);
            
		} catch (Exception e) {
			logger.debug(this.getClass().getName()+"-->jsonGetStudentCourseDetail"+e.getMessage());
            throw new Exception(e);
		}
		return SUCCESS;
	}

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getEnableFuzzy() {
		return enableFuzzy;
	}

	public void setEnableFuzzy(String enableFuzzy) {
		this.enableFuzzy = enableFuzzy;
	}

	public List<Student> getStudentList() {
		return studentList;
	}

	public void setStudentList(List<Student> studentList) {
		this.studentList = studentList;
	}

	public List<StudentCourseDetailBean> getStudentCourseList() {
		return studentCourseList;
	}

	public void setStudentCourseList(List<StudentCourseDetailBean> studentCourseList) {
		this.studentCourseList = studentCourseList;
	}
}
