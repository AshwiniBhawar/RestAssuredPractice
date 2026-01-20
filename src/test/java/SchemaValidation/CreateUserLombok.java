package SchemaValidation;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//POJO- Plain Old Java Object
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserLombok {

    private String name;
    private String gender;
    private String email;
    private String status;
}

