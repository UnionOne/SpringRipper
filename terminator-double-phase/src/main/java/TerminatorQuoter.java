import javax.annotation.PostConstruct;

public class TerminatorQuoter implements Quoter {
    @InjectRandomInt(min = 2, max = 15)
    private int repeat;

    private String message;

    @PostConstruct
    public void init() {
        System.out.println("Phase 2");
        System.out.println(this.repeat);
    }

    public TerminatorQuoter() {
        System.out.println("Phase 1");
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public void sayQuote() {
        for (int i = 0; i < this.repeat; i++) {
            System.out.println("message: " + this.message);
        }
    }
}