package leon.ssi.service;

import leon.ssi.model.Page;

import org.springframework.stereotype.Service;

@Service
public class PageService {
	public Page getPage(String currentPage, String pageMethod, int totalRows) {
		// 定义pager对象，用于传到页面
		Page page = new Page(totalRows);
		// 如果当前页号为空，表示为首次查询该页
		// 如果不为空，则刷新pager对象，输入当前页号等信息
		if (currentPage != null) {
			page.refresh(Integer.parseInt(currentPage));
		}
		// 获取当前执行的方法，首页，前一页，后一页，尾页。
		if (pageMethod != null) {
			if (pageMethod.equals("first")) {
				page.first();
			} else if (pageMethod.equals("previous")) {
				page.previous();
			} else if (pageMethod.equals("next")) {
				page.next();
			} else if (pageMethod.equals("last")) {
				page.last();
			}
		}
		return page;
	}
}
