import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("/greet")
public class Greeting {
    @GET
    public String greet() {
        return "Hello, Vardhman";
    }
}
