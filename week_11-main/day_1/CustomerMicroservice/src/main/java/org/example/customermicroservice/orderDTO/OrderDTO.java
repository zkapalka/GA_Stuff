package org.example.customermicroservice.orderDTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
    private Integer id;
    private String orderDetails;
    private Integer customerId;
    private List<Integer> productId;
}
