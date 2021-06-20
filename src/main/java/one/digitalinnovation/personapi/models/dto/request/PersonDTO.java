package one.digitalinnovation.personapi.models.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PersonDTO {

    private Long id;

    @NotEmpty(message = "First name field is required")
    @Size(min = 2, max = 100, message = "First name field must be between 2 and 100")
    private String firstName;

    @NotEmpty(message = "Last name field is required")
    @Size(min = 2, max = 100, message = "Last name field must be between 2 and 100")
    private String lastName;

    @NotEmpty(message = "CPF field is required.")
    @CPF(message = "Invalid CPF")
    private String cpf;

    private String birthDate;

    @Valid
    @NotEmpty(message = "phones field is required")
    private List<PhoneDTO> phones;

}
