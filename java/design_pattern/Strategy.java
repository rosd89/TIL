import java.text.ParseException;
import java.util.Arrays;
import java.util.List;

public class Strategy {
    public static void main(String[] args) throws ParseException {
        final String d = "a,b,c,d,f,g,e";
        final ExContext ec = new ExContext(d);

        System.out.println(d.getClass());
        System.out.println(d);

        System.out.println(ec.getData().getClass());
        System.out.println(ec.getData());
    }
}

class ExContext {
    private String data;
    private ParserStrategy parserStrategy;

    ExContext(String data) {
        this.data = data;

        if (data.startsWith("{") || data.startsWith("[")) {
            this.parserStrategy = new JsonParser();
        }

        this.parserStrategy = new CsvParser();
    }

    List<String> getData() throws ParseException {
        return this.parserStrategy.parse(this.data);
    }
}

interface ParserStrategy {
    List<String> parse(String data) throws ParseException;
}

class CsvParser implements ParserStrategy {

    @Override
    public List<String> parse(String data) {
        return Arrays.asList(data.split(","));
    }
}

class JsonParser implements ParserStrategy {

    @Override
    public List<String> parse(String data) throws ParseException {
        // Json Parser 구현
        throw new ParseException("JSON Parser가 구현되어 있지 않습니다.", -1);
    }
}
