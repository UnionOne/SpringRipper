package quoters;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class PhaseQuoter {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        context.getBean(TerminatorQuoter.class).sayQuote();
    }
}