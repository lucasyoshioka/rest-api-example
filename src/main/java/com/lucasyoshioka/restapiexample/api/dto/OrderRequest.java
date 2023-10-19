package com.lucasyoshioka.restapiexample.api.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

public class OrderRequest {

    @NotNull(message = "cliente não informado")
    private Integer customerId;

    @NotEmpty(message = "nenhum produto selecionado")
    private Set<Integer> itemIds;

    @NotBlank(message = "endereço de entrega não informado")
    private String deliveryAddress;

    public OrderRequest() {
    }

    public Integer getCustomerId() {
        return this.customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Set<Integer> getItemIds() {
        return this.itemIds;
    }

    public void setItemIds(Set<Integer> itemIds) {
        this.itemIds = itemIds;
    }

    public String getDeliveryAddress() {
        return this.deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

}
