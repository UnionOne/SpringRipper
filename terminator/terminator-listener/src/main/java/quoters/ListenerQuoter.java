package quoters;

import org.springframework.context.support.ClassPathXmlApplicationContext;

// Three Phases Constructor
public class ListenerQuoter {
    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
    }
}