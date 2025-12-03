package XMLAPIs;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.Data;

import java.util.List;

@Data
@JacksonXmlRootElement(localName="MRData", namespace="http://ergast.com/mrd/1.5")
public class MRData {

    @JacksonXmlProperty(localName="CircuitTable")
    private CircuitTable circuitTable;

    @JacksonXmlProperty(isAttribute = true, localName="series")
    private String series;

    @JacksonXmlProperty(isAttribute = true, localName="url")
    private String url;

    @JacksonXmlProperty(isAttribute = true, localName="limit")
    private String limit;

    @JacksonXmlProperty(isAttribute = true, localName="offset")
    private String offset;

    @JacksonXmlProperty(isAttribute = true, localName="total")
    private String total;

    @Data
    public static class CircuitTable{

        @JacksonXmlElementWrapper(useWrapping = false)
        @JacksonXmlProperty(localName="Circuit")
        private List<Circuit> circuits;

        @JacksonXmlProperty(isAttribute = true, localName="season")
        private String season;

        @Data
        public static class Circuit{

            @JacksonXmlProperty(isAttribute = true, localName="CircuitId")
            private String circuitId;

            @JacksonXmlProperty(isAttribute = true, localName="url")
            private String url;

            @JacksonXmlProperty(localName="CircuitName")
            private String circuitName;

            @JacksonXmlProperty(localName="Location")
            private Location location;

            @Data
            public static class Location {
                @JacksonXmlProperty(isAttribute=true, localName="lat")
                private String latitude;

                @JacksonXmlProperty(isAttribute=true, localName="long")
                private String longitude;

                @JacksonXmlProperty(localName="Locality")
                private String locality;

                @JacksonXmlProperty(localName="Country")
                private String country;
            }
        }
    }
}
