스프링부트를 기반으로한 자바 게시판입니다.

Language : Java, JSP, JavaScript(JQuery, Ajax)
Freamework : Spring Framework, MyBatis, Bootstrap
DB : Oracle

주요 기능 : 
1. 스프링 시큐리티를 이용한 권한 별 페이지 접근
2. 비밀번호 암호화
3. 쿠키를 통한 아이디 저장
4. 게시글에 파일 첨부 및 다운로드기능
5. 댓글, 대댓글 기능
6. 게시글 페이징처리

관리자의 추가 기능 : 모든 게시글 삭제 가능

SQL익스포트 파일은 익스포트.sql로 저장해놓았습니다.
테스트하면서 사용했던 계정과 게시글, 댓글도 포함되어져 있습니다.

src/main/resources에 있는 application.properties를 통해 컴퓨터환경에 맞게 경로를 변경하거나 포트번호를 변경한 후 실행하면 됩니다.

server.port : 톰캣 포트로 8085로 지정해놓았습니다.

file.upload-dir : 게시글 등록 시점에 파일 첨부시, 첨부된 파일이 저장될 경로

address : 게시글에 첨부되어있는 첨부파일을 다운로드할 때 파일이 다운로드되는 경로

시작 URL : localhost:8085




