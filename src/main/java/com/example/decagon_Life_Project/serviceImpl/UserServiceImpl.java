package com.example.decagon_Life_Project.serviceImpl;

import com.example.decagon_Life_Project.entities.UserEntity;
import com.example.decagon_Life_Project.enums.ResponseCodeEnum;
import com.example.decagon_Life_Project.models.UserEntityDto;
import com.example.decagon_Life_Project.repository.UserEntityRepository;
import com.example.decagon_Life_Project.services.UserService;
import com.example.decagon_Life_Project.utils.BaseResponse;
import com.example.decagon_Life_Project.utils.ResponseCodeUtil;
import lombok.Data;
import net.bytebuddy.utility.RandomString;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;

@Service
@Data
public class UserServiceImpl implements UserService {

    private final UserEntityRepository userEntityRepository;

    private final JavaMailSenderImpl mailSender;
    @Override
      public BaseResponse signUp(UserEntityDto userEntityDto) throws MessagingException, UnsupportedEncodingException {
        BaseResponse response = new BaseResponse();
        UserEntity newUser = new UserEntity();
        newUser.setFirstName(userEntityDto.getFirstName());
        newUser.setLastName(userEntityDto.getLastName());
        newUser.setEmail(userEntityDto.getEmail());
        newUser.setPassword(userEntityDto.getPassword());
        newUser.setSubscribeToNewsletter(userEntityDto.getSubscribeToNewsletter());
        String randomCode = RandomString.make(24);
        newUser.setToken(randomCode);
        newUser.setIsActive(false);
        userEntityRepository.save(newUser);
        //sendVerificationEmail(userEntityDto.getEmail(), "FMT-Email Verification Code", "This is your " + randomCode);
        String URL = "http://localhost:8080/api/v1/user/confirm?token=" + randomCode;
        String link = "<h3>Hello "  + userEntityDto.getFirstName()  +
                "<br> Click the link below to activate your account <a href=" + URL + "><br>Activate</a></h3>";
        sendVerificationEmail(userEntityDto.getEmail(), "FMT-Email Verification Code", link);
        //return new BaseResponse(ResponseCodeEnum.SUCCESSFUL_REGISTRATION);
        return new ResponseCodeUtil().updateResponseData(response, ResponseCodeEnum.SUCCESSFUL_REGISTRATION);


    }

    @Override
    public void sendVerificationEmail(String toEmail, String subject, String body) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper message = new MimeMessageHelper(mimeMessage, "utf-8");
        message.setTo(toEmail);
        message.setFrom("Food-mart-Team");
        message.setText(body,true);
        message.setSubject(subject);


        mailSender.send(mimeMessage);

    }

    @Override
    public String confirmUser(String token) {
        UserEntity unverifiedUser = userEntityRepository.getUserEntitiesByToken(token);
        if (unverifiedUser.getToken().equals(token)){
            unverifiedUser.setToken(null);
            unverifiedUser.setIsActive(true);
            userEntityRepository.save(unverifiedUser);
            return "Dear " + unverifiedUser.getFirstName() + " your account is now verified";
        }


        return "User not found";
    }
}
