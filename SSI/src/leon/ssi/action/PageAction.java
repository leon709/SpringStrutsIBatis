package leon.ssi.action;

import javax.annotation.Resource;

import leon.ssi.model.Page;
import leon.ssi.service.PageService;

import com.opensymphony.xwork2.ActionSupport;

public class PageAction extends ActionSupport {

	private static final long serialVersionUID = 1L;
	
	@Resource
	private PageService pageService;
	
	private String currentPage;
	private String pageMethod;
	private int msgNum;
	
	
	public String pageMessages() {
        // 获得所有的帖子总数
      //  msgNum = messageService.getRows();
        
        // 获得制定方法的page对象
        Page page = pageService.getPage(this.getCurrentPage(), this.getPageMethod(), msgNum);
        // 当前页面
        this.setCurrentPage(String.valueOf(page.getCurrentPage()));
        // 总条数
        this.setMsgNum(this.getMsgNum());
       
        // 用于分页显示的记录
       // themeMessages = messageService.getMessages(page.getPageSize(), page.getStartRow());
        
        return SUCCESS;
    }


	public int getMsgNum() {
		return msgNum;
	}


	public void setMsgNum(int msgNum) {
		this.msgNum = msgNum;
	}


	public String getCurrentPage() {
		return currentPage;
	}


	public void setCurrentPage(String currentPage) {
		this.currentPage = currentPage;
	}


	public String getPageMethod() {
		return pageMethod;
	}


	public void setPageMethod(String pageMethod) {
		this.pageMethod = pageMethod;
	}
}
