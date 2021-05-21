package com.jnzy.mall.pojo;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SeckillGoods implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    /**
     * 商品名称
     */
    private String productName;

    /**
     * 商品价格
     */
    private Double productPrices;

    /**
     * 库存
     */
    private Integer stock;

    /**
     * 商品详情
     */
    private String description;

    /**
     * 商品图片
     */
    private String productPic;

    /**
     * 发布时间
     */
    @DateTimeFormat(pattern="YYYY-MM-dd HH:mm:ss" )
    @JsonFormat(timezone = "GMT+8")
    private Date startTime;

    /**
     * 发布时间
     */
    @DateTimeFormat(pattern="YYYY-MM-dd HH:mm:ss" )
    @JsonFormat(timezone = "GMT+8")
    private Date endTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
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

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
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
        sb.append(", startTime=").append(startTime);
        sb.append(", endTime=").append(endTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
