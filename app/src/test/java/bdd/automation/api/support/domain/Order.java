package bdd.automation.api.support.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Builder.Default
    private int id = 100;
    @Builder.Default
    private int petId = 77;
    @Builder.Default
    private int quantity = 1;
    @Builder.Default
    private String shipDate = "2023-06-08T23:02:48.113Z";
    @Builder.Default
    private String status = "approved";
    @Builder.Default
    private boolean complete = true;
}
