import org.springframework.context.support.ClassPathXmlApplicationContext;

public class PostProcessorMain {
    public static void main(String[] args) throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        context.getBean(Quoter.class).sayQuote();
    }
}