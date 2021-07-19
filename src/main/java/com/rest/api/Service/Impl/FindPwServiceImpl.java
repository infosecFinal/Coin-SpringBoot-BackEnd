package com.rest.api.Service.Impl;

import com.rest.api.DAO.AccountDAO;
import com.rest.api.Service.FindPwService;
import com.rest.api.VO.FindPw;
import lombok.RequiredArgsConstructor;
import org.apache.commons.mail.HtmlEmail;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class FindPwServiceImpl implements FindPwService {

    private final AccountDAO accountDAO;

    @Override
    public void sendEmail(FindPw findPw, String div) {

        String charSet = "utf-8";
        String hostSMTP = "smtp.naver.com";
        String hostSMTPid = "####@naver.com"; // 서버 email
        String hostSMTPpwd = "#############"; // 서버 password

        String fromEmail = "####@naver.com";
        String fromName = "COINNET_ADMIN";
        String subject = "";
        String msg = "";

        if (div.equals("findpw")) {
            subject = "코인넷 임시 비밀번호 입니다.";
            msg += "<div align='center' style='border:1px solid black; font-family:verdana'>";
            msg += "<h3 style='color: blue;'>";
            msg += findPw.getMember_id() + "님의 임시 비밀번호 입니다. 비밀번호를 변경하여 사용하세요.</h3>";
            msg += "<p>임시 비밀번호 : ";
            msg += findPw.getMember_pw() + "</p></div>";
        }

        String mail = findPw.getMember_email();
        try {
            HtmlEmail email = new HtmlEmail();
            email.setDebug(true);
            email.setCharset(charSet);
            email.setHostName(hostSMTP);
            email.setSmtpPort(587); // 네이버 IMAP/SMTP 설정
            email.setAuthentication(hostSMTPid, hostSMTPpwd);
            email.addTo(mail);
            email.setFrom(fromEmail, fromName);
            email.setSubject(subject);
            email.setHtmlMsg(msg);
            email.send();
            System.out.println("메일발송 성공");
        } catch (Exception e) {
            System.out.println("메일발송 실패 : " + e);
        }
    }

    @Override
    public void findPw(HttpServletResponse response, FindPw findPw) throws Exception {
        response.setContentType("text/html;charset=utf-8");
        String id_ck = accountDAO.getUserIDList(findPw.getMember_id());
        String email_ck = accountDAO.getUserInfo(findPw.getMember_id()).getUser_email();
        System.out.println(email_ck);
        PrintWriter out = response.getWriter();

        if (id_ck == null) {
            out.print("가입되지 않은 아이디입니다.");
            out.close();
        } else if (!email_ck.equals(findPw.getMember_email())) {
            out.print("올바르지 않은 이메일입니다.");
            out.close();
        } else {
            StringBuilder new_pw = new StringBuilder();
            Random rand = new Random();
            for (int i = 0; i < 13; i++) {
                if (rand.nextBoolean()) {
                    new_pw.append((char) ((int) (rand.nextInt(26)) + 97));
                } else {
                    new_pw.append((rand.nextInt(10)));
                }
            }
            findPw.setMember_pw(new_pw.toString());
            accountDAO.updatePw(findPw);
            sendEmail(findPw, "findpw");
            out.print("이메일로 임시 비밀번호를 발송하였습니다.");
            out.close();
        }
    }
}