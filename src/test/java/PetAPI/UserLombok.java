package PetAPI;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.testng.annotations.Ignore;
import org.testng.annotations.Optional;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserLombok {

    private String email;
    private String username;
    private String password;
    private String phone;
    private Address address;
    private Name name;

    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class Address{
        private GeoLocation geolocation;
        private String city;
        private String street;
        private int number;
        private String zipcode;

        @Builder
        @NoArgsConstructor
        @AllArgsConstructor
        @Data
        public static class GeoLocation{
            private String lat;
            @JsonProperty("long")
            private String longitude;
        }
    }

    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class Name{
        private String firstname;
        private String lastname;
    }

}
