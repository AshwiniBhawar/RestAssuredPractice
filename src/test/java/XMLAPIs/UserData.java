package XMLAPIs;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;
import lombok.Data;
import java.util.List;

@Data
@JacksonXmlRootElement(localName="objects")
public class UserData {

    @JacksonXmlProperty(isAttribute=true, localName = "type")
    private String type;

    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName="object")
    private List<ObjectData> object;

    @Data
    public static class ObjectData{

        @JacksonXmlProperty(localName="email")
        private String email;

        @JacksonXmlProperty(localName="gender")
        private String gender;

        @JacksonXmlProperty(localName="status")
        private String status;

        @JacksonXmlProperty(localName="name")
        private String name;

        @JacksonXmlProperty(localName="id")
        private IDWrap id;

        @Data
        public static class IDWrap{

            @JacksonXmlProperty(isAttribute=true, localName = "type")
            private String type;

            @JacksonXmlText
            private int value;
        }
    }
}

