import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ProfilingQuoter {
    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        while (true) {
            Thread.sleep(1000);
            context.getBean(Quoter.class).sayQuote();
        }
    }
}