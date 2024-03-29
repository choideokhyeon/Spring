<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Upload Form page</title>

<!-- JQ -->
<script src="https://code.jquery.com/jquery-3.6.3.min.js" integrity="sha256-pvPw+upLPUjgMXY0G+8O0xUf+/Im1MZjXxxgOcBQBXU=" crossorigin="anonymous"></script>

</head>
<body>
<h1>Upload Form</h1>
<hr>
<form action="${pageContext.request.contextPath}/upload/reqUpload" method="post" enctype="multipart/form-data" onsubmit="return false">
	<input type="file" name="files" multiple>
	<input type="submit" value="전송">
</form>

<script>
	//submit버튼 이벤트 처리
	$("input[type='submit']").on("click",function(e){
		const formdata = new FormData();					// 새로운 form 태그 생성
		const inputfiles = $("input[name='files']");	// name=files 검색 inputfiles 연결
		const files = inputfiles[0].files;
		console.log(files);
		
		//formdata에 file을 저장
		for(i in files)
		{
			if(!isValid(files[i]))	//유효성 체크 함수 삽입
				return;
			formdata.append("files", files[i]);
			console.log(files[i]); //파일정보
		}
		
		console.log("===================================================");
		
		//formdata에 저장된 file들 확인
		for(let value of formdata.values())
		{
			console.log(value);
		}
			
 		
		$.ajax({
			url : '${pageContext.request.contextPath}/upload/reqUpload',
			type : 'POST',
			data : formdata,
			contentType : false,	//false로 선언시 content-type 헤더가 multipart/form-data로 전송되게 함
			processData : false,	//기본값 true(파라미터를 key:value 형태의 값을 Query String으로 전달)
 									//false Query String으로 전달하지 않음, 보통 파일전송시 사용
			success : function(result){alert("Upload Success");},
 			error : function(request,status,error){
 				//alert("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
 				}
			});
		})

		
		
	//유효성 체크 함수
	const isValid = function(data){
		
		const regex = new RegExp("(.*?\.(exe|sh|zip|alz)$)");
		//(.*?\.(exe|sh|zip|alz)$)
		//.*? 				파일명 형태	(.(애니) : 문자 or 숫자 or 공백 중 한문자)
		//								(* : 0~n개까지의 문자)
		//								(? : 없거나 1개인 경우)
		//\.				. 기호 그대로 표시
		//(exe|sh|zip|alz)$	확장자명		($ : 끝나는 문자열을 찾을때 사용)
		//								(| : or 연산자)
		
		//확장자 exe,sh,zip,alz 거부(*정규표현식 찾아보기)
		if(regex.test(data.name))
		{
			alert('업로드가 불가능한 파일입니다');
			return false;
		}
		
		//파일 사이즈 20MB 초과
		const maxSize = 1024*1024*20;	//1024 = KB, 1024^2 = MB , 1024^3 = GB
		if(data.size >= maxSize)
		{
			alert("20MB 이상인 파일은 업로드 할 수 없습니다");
			return false;
		}
		
		return true;
	}
	
</script>
</body>
</html>