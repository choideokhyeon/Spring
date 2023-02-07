package Security;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.test.dto.AuthDto2;
import com.test.dto.MemberDto2;
import com.test.mapper.MemberMapper;

import lombok.extern.log4j.Log4j;

@Log4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
	{
	"file:src/main/webapp/WEB-INF/spring/root-context.xml",
	"file:src/main/webapp/WEB-INF/spring/security-context.xml"
	}
)
public class SecurityTests {

	@Autowired
	private PasswordEncoder passwordencoder;

	@Autowired
	private DataSource ds;
	

	
	@Test
	public void	makeUser() throws SQLException {
		
		String sql = "insert into tbl_member(userid,userpw,username) values(?,?,?)";
		
		Connection conn=ds.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, "user");
		pstmt.setString(2, passwordencoder.encode("1234"));
		pstmt.setString(3, "일반유저");
		pstmt.executeUpdate();
		
		pstmt.setString(1, "member");
		pstmt.setString(2, passwordencoder.encode("1234"));
		pstmt.setString(3, "직원");
		pstmt.executeUpdate();
		
		pstmt.setString(1, "admin");
		pstmt.setString(2, passwordencoder.encode("1234"));
		pstmt.setString(3, "관리자");
		pstmt.executeUpdate();
		
	}

	
	@Test
	public void  insertAuth() throws SQLException {
		String sql = "insert into tbl_member_auth(userid,auth) values(?,?)";
		
		Connection conn=ds.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		

		pstmt.setString(1, "user");
		pstmt.setString(2, "ROLE_USER");
		pstmt.executeUpdate();
		
		pstmt.setString(1, "member");
		pstmt.setString(2, "ROLE_MEMBER");
		pstmt.executeUpdate();
		
		pstmt.setString(1, "admin");
		pstmt.setString(2, "ROLE_ADMIN");
		pstmt.executeUpdate();
		
	}
	
	@Autowired
	private MemberMapper mapper;
	
	@Test
	public void Mapper() {
		MemberDto2 dto = mapper.read("user");
		System.out.println(dto);
		List<AuthDto2> list = dto.getAuthList();
		for(AuthDto2 auth : list)
		{
			System.out.println(auth);
		}
	}

}