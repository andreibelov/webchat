package net.bootlab.webchat.model.validators;

import net.bootlab.webchat.configs.AppProperties;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidator implements ConstraintValidator<EmailConstraint, String> {

    private final Pattern pattern;

    @Autowired
    public EmailValidator(AppProperties appProperties){
        this.pattern = Pattern.compile(appProperties.getEmailValidatorPattern());
    }

    @Override
    public void initialize(EmailConstraint constraintAnnotation) {
        //There is no need to initialize anything before using validator
    }

    @Override
    public boolean isValid(String mail, ConstraintValidatorContext context) {

        Matcher matcher = pattern.matcher(mail);

        return matcher.matches();
    }
}
