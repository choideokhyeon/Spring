package com.test.service;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.dao.BoardDao;
import com.test.dto.BoardDto;
import com.test.dto.Criteria;
import com.test.dto.PageDto;

import lombok.extern.log4j.Log4j;

@Service
@Log4j
public class BoardService {
	
	
	@Autowired
	BoardDao dao;
	
	
	private String uploadDir;
	 
	
	//모든 게시물 가져오기
//	public boolean GetBoardList(HttpServletRequest request) {
//		List<BoardDto> list = dao.SelectAll();
//		if(list!=null) {
//			request.setAttribute("list", list);
//			return true;
//		}
//		return false;
//	}
	
	
	//모든 게시물 가져오기
	public boolean GetBoardList(Criteria criteria, HttpServletRequest request,HttpServletResponse response) {
		
		//쿠키 생성(/board/read.do 새로고침시 조회수 반복증가를 막기위한용도) 
		Cookie init = new Cookie("reading","true");
		response.addCookie(init);
		
		//전체게시물 건수 받기
		int totalcount = dao.getAmount();
		
		//PageDto 만들기
		PageDto pagedto = new PageDto(totalcount,criteria);
		
		//시작 게시물 번호 구하기(수정)
		int startno = criteria.getPageno()*criteria.getAmount()-criteria.getAmount();
		
		//PageDto를 Session에 추가
		HttpSession session = request.getSession(false);
		
		List<BoardDto> list = dao.SelectAll(startno,criteria.getAmount());
		if(list!=null) {
			request.setAttribute("list", list);
			session.setAttribute("pagedto", pagedto);
			return true;
		}
		return false;
	}
	
	//게시물 추가하기
//	public boolean PostBoard(BoardDto dto,HttpServletRequest request)
//	{
//		boolean flag=false;
//		
//		int result=dao.Insert(dto);
//		
//		if(result>0)
//			flag=true;
//		
//		return flag;
//	}
	

	
	//게시물 추가하기(업로드포함)
//	public boolean PostBoard(BoardDto dto,HttpServletRequest request)
//	{
//		boolean flag=false;
//		
//		 
//		//디렉토리 경로 설정
//		System.out.println("DIRPATH : " + request.getSession().getServletContext().getRealPath("/"));
//		uploadDir=request.getSession().getServletContext().getRealPath("/")+"upload";
//		
//		//접속한 Email 계정 확인
//		HttpSession session = request.getSession(false);
//		AuthDto adto =(AuthDto)session.getAttribute("authdto");
//		
//		//UUID(랜덤값) 폴더생성
//		UUID uuid = UUID.randomUUID();
//		String path = uploadDir+File.separator+adto.getEmail()+File.separator+uuid;
//		
//		//추출된 파일정보 저장용 Buffer
//		StringBuffer filelist = new StringBuffer();
//		StringBuffer filesize= new StringBuffer();
//		
//		
//		try {
//			
//			Collection<Part> parts=request.getParts();
//			for(Part part : parts)
//			{
//				if(part.getName().equals("files"))
//				{
//					System.out.println("-------------------------------------------");
//					
////					System.out.println("PART명 : " + part.getName());
////					System.out.println("PART크기 : " + part.getSize());				
////					for(String name : part.getHeaderNames()) {
////						System.out.println("Header Name : " + name);
////						System.out.println("Header Value : " + part.getHeader(name));
////					}
//					
//					//파일명 추출
//					System.out.println("파일명 : " + getFileName(part));
//					String filename=getFileName(part);
//					
//					if(!filename.equals(""))
//					{
//						
//						//폴더생성
//						File dir = new File(path);
//						if(!dir.exists()) {
//							dir.mkdirs();
//						}
//						//업로드
//						part.write(path+File.separator+filename);
//						
//						//파일명 DB Table에 추가 위한 저장
//						filelist.append(filename+";");
//						//디렉토리 경로 DB Table에 추가 위한 저장
//						filesize.append(part.getSize()+";");
//					}
//					
//					System.out.println("-------------------------------------------");
//				}
//				
//			}	
//			dto.setDirpath(uuid+"");
//			dto.setFilename(filelist.toString());
//			dto.setFilesize(filesize.toString());
//			
//			//DB연결
//			int result=dao.Insert(dto);		
//			if(result>0)
//				flag=true;
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//		
//		return flag;
//	}
//	
//	
//	
//	private String getFileName(Part part) {
////		-------------------------------------------
////		PART명 : files
////		PART크기 : 107637
////		Header Name : content-disposition
////		Header Value : form-data; name="files"; filename="aaa.csv"
////		Header Name : content-type
////		Header Value : text/csv
////		-------------------------------------------
//		
//		String contentDisp = part.getHeader("content-disposition");
//		//String contentDisp = form-data; name="files"; filename="aaa.csv"
//		
//		String[] tokens = contentDisp.split(";");//[form-data,name="files",filename="aaa.csv"]
//		String filename = tokens[tokens.length - 1]; //filename="aaa.csv"
//
//		return filename.substring(11,filename.length()-1);
//	}
//	
//	
//	
//	public boolean GetBoard(int bno, HttpServletRequest req,HttpServletResponse resp) {
//			
//			boolean flag=false;
//		
//			//쿠키 확인 후  CountUp(/board/read.do 새로고침시 조회수 반복증가를 막기위한용도) 
//			Cookie[] cookies = req.getCookies();
//			for(Cookie cookie:cookies)
//			{
//				if(cookie.getName().equals("reading"))
//				{
//					if(cookie.getValue().equals("true"))
//					{
//						//CountUp
//						dao.Update(bno);
//						
//						//쿠키 value 변경
//						cookie.setValue("false");
//						resp.addCookie(cookie);
//					}	
//				}
//			}
//			
//
//			
//
//			
//			BoardDto dto = dao.Select(bno);
//			if(dto!=null) {
//				//session에 추가한 정보
//				//pagedto / authdto / + boarddto
//				HttpSession session = req.getSession(false);
//				session.setAttribute("boarddto", dto);	
//				flag=true;
//			}		
//			
//		return flag;
//	}
//	
//	
//	public void download(HttpServletRequest req, HttpServletResponse resp) {
//		try {
//		//파라미터
//		String filename=req.getParameter("filename");
//		String uuid=req.getParameter("uuid");
//		//이메일정보 확인
//		HttpSession session = req.getSession(false);
//		AuthDto adto =(AuthDto)session.getAttribute("authdto");
//		String email=adto.getEmail();
//		//경로설정
//		String path=req.getSession().getServletContext().getRealPath("/"); 
//		path+="upload"+File.separator+email+File.separator+uuid;
//		
//		//헤더설정
//		resp.setHeader("Content-Type","application/octet-stream;charset=utf-8");
//		resp.setHeader("Content-Disposition","attachment; filename="+URLEncoder.encode(filename,"UTF-8").replaceAll("\\+", "%20"));
//		
//		//스트림형성 
//		FileInputStream fin = new FileInputStream(path+File.separator+filename);
//		ServletOutputStream bout = resp.getOutputStream();
//		
//		//다운로드 처리
//		int read=0;
//		byte[] buff = new byte[4096];
//		while(true)
//		{
//			read = fin.read(buff,0,buff.length);
//			if(read==-1)
//				break;
//			bout.write(buff,0,read);		
//		}
//		bout.flush();
//		bout.close();
//		fin.close();
//		
//		}catch(Exception e) {
//			e.printStackTrace();
//		}
//		
//	}
//	
//	public void downloadzip(HttpServletRequest req, HttpServletResponse resp) {
//		try {
//		//파라미터
//		//String filename=req.getParameter("filename");
//		String uuid=req.getParameter("uuid");
//		//이메일정보 확인
//		HttpSession session = req.getSession(false);
//		AuthDto adto =(AuthDto)session.getAttribute("authdto");
//		String email=adto.getEmail();
//		//경로설정
//		String path=req.getSession().getServletContext().getRealPath("/"); 
//		path+="upload"+File.separator+email+File.separator+uuid;
//		
//		
//		
//		//--------------
//		FileInputStream fin=null;
//		ZipEntry zipEntry = null;
//		File dir = new File(path);
//		File filelist[] = dir.listFiles();
//		
//		//헤더설정
//		resp.setHeader("Content-Type","application/octet-stream;charset=utf-8");
//		resp.setHeader("Content-Disposition","attachment; filename=All.zip");
//		
//		//스트림형성 
//		ServletOutputStream bout = resp.getOutputStream();
//		ZipOutputStream zout = new ZipOutputStream(bout);
//		
//	
//		byte[] buff = new byte[4096];	
//		for(File file:filelist)
//		{
//			fin = new FileInputStream(file);
//			ZipEntry zip = new ZipEntry(file.getName().toString());
//			zout.putNextEntry(zip);
//			while (true) 
//			{
//				int data = fin.read(buff,0,buff.length);
//				if (data == -1)
//					break;
//				zout.write(buff,0,data);
//			}
//			
//			zout.closeEntry();
//			fin.close();
//		}
//		zout.close();
//		
//		
//	
//		
//		}catch(Exception e) {
//			e.printStackTrace();
//		}
//		
//	}
//	
//	public boolean replyPost(ReplyDto rdto) {
//		boolean flag=false;
//		
//		int result= dao.Insert(rdto);
//		if(result>0)
//			flag=true;
//		
//		return flag;
//		
//	}
//	
//	
//	public List<ReplyDto> replyList(ReplyDto dto) {
//		return dao.SelectAll(dto);
//	}
//	
//	
//	
//	public int getReplyCount(int bno) {
//		
//		return dao.getReplyAmount(bno);
//		
//	}
//	
//	//파일삭제
//	public void removefile(HttpServletRequest req, HttpServletResponse resp) {
//		try {
//			Map<String,String[]> params = req.getParameterMap();
//			
//			//디렉토리 경로 설정
//			System.out.println("DIRPATH : " + req.getSession().getServletContext().getRealPath("/"));
//			uploadDir=req.getSession().getServletContext().getRealPath("/")+"upload";
//			
//			//접속한 Email 계정 확인
//			HttpSession session = req.getSession(false);
//			AuthDto adto =(AuthDto)session.getAttribute("authdto");
//			
//			//UUID(랜덤값) 폴더생성
//			String uuid = params.get("dirpath")[0];
//			String path = uploadDir+File.separator+adto.getEmail()+File.separator+uuid;
//			
//			//파일경로 확인
//			String filepath = path+File.separator+params.get("filename")[0];
//			System.out.println("파일삭제 경로 : " + filepath);
//			//파일삭제
//			  
//			 boolean flag = new File(filepath).delete();
//			 System.out.println("삭제 여부 : " + flag);
//			 
//			  
//		 
//	 
//			//세션 boarddto 재설정
//			BoardDto bdto = (BoardDto)session.getAttribute("boarddto");
//			String filenames = bdto.getFilename();
//			String filesizes = bdto.getFilesize();
//			filenames=filenames.replace(params.get("filename")[0]+";", "");
//			filesizes=filesizes.replace(params.get("filesize")[0]+";", "");
//			bdto.setFilename(filenames);
//			bdto.setFilesize(filesizes);
//			session.setAttribute("boarddto", bdto);
//			System.out.println("파일삭제전 BDTO : " + bdto);
//			//db 삭제 
//			dao.Update(bdto.getNo(),filenames,filesizes);
//		 
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		
//	}
//	public void boardUpdate(Map<String, String[]> params,HttpServletRequest req) {
//		String title = params.get("title")[0];
//		String content = params.get("content")[0];
//		HttpSession session = req.getSession(false);
//		
//		BoardDto dto = (BoardDto)session.getAttribute("boarddto");
//		dto.setTitle(title);
//		dto.setContent(content);
//		
//		dao.Update(dto);
//		
//		
//	}
//	public boolean RemoveBoard(HttpServletRequest req, HttpServletResponse resp) {
//		
//		boolean flag = false;
//		//------------------------------------
//		Map<String,String[]> params = req.getParameterMap();
//		
//		//디렉토리 경로 설정
//		System.out.println("DIRPATH : " + req.getSession().getServletContext().getRealPath("/"));
//		uploadDir=req.getSession().getServletContext().getRealPath("/")+"upload";
//		
//		//접속한 Email 계정 확인
//		HttpSession session = req.getSession(false);
//		AuthDto adto =(AuthDto)session.getAttribute("authdto");
//		
//		//UUID(랜덤값) 폴더생성
//		String uuid = params.get("dirpath")[0];
//		String path = uploadDir+File.separator+adto.getEmail()+File.separator+uuid;
//		
//		
//		//파일삭제  
//		File folder = new File(path);
//		File[] folder_list=null;
//		if(folder.exists())
//		{
//			folder_list = folder.listFiles(); //파일리스트 얻어오기
//			for(File file :folder_list) {
//				file.delete();
//			}
//			folder.delete(); //폴더 삭제
//		}
//		
//		
//		//-------------------------------------
//		String bno = req.getParameter("bno");	 
//		
//		int result = dao.Delete(bno);
//		if(result>0)
//			flag=true;
//		return flag;
//	}

	
	
	
}