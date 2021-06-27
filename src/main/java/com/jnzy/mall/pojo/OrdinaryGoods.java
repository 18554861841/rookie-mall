package com.jnzy.mall.pojo;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Component
public class OrdinaryGoods implements Serializable {
    private Long id;

    private String productName;

    private Double productPrices;

    private Long stock;

    private String description;

    private String productPic;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName == null ? null : productName.trim();
    }

    public Double getProductPrices() {
        return productPrices;
    }

    public void setProductPrices(Double productPrices) {
        this.productPrices = productPrices;
    }

    public Long getStock() {
        return stock;
    }

    public void setStock(Long stock) {
        this.stock = stock;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getProductPic() {
        return productPic;
    }

    public void setProductPic(String productPic) {
        this.productPic = productPic == null ? null : productPic.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", productName=").append(productName);
        sb.append(", productPrices=").append(productPrices);
        sb.append(", stock=").append(stock);
        sb.append(", description=").append(description);
        sb.append(", productPic=").append(productPic);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}