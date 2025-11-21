package PetAPI;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PetLombok {

    private int id;
    private String name;
    private Category category;
    private List<String> photoUrls;
    private List<Tags> tags;
    private String status;

    @Builder
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Category{
        private int id;
        private String name;
    }

    @Builder
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Tags{
        private int id;
        private String name;
    }
}
