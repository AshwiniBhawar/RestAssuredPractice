package DeserializationWithJsonArray;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductsLombok {

    private int id;
    private String title;
    private double price;
    private String description;
    private String category;
    private String image;
    private Rating rating;

    @Builder
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Rating{
        private Double rate;
        private Integer count;
    }
}
