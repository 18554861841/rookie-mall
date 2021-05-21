package com.jnzy.mall.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author jobob
 * @since 2021-04-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class SeckillOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    /**
     * 用户名称
     */
    private String username;

    /**
     * 商品名称
     */
    private String productName;

    /**
     * 折扣
     */
    private String discount;

    /**
     * 手机号
     */
    private String telephone;

    /**
     * 总金额
     */
    private Double totalPrice;

    /**
     * 送货地址
     */
    private String deliveryAddress;

    /**
     * 下单时间
     */
    private Date orderTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName == null ? null : productName.trim();
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress == null ? null : deliveryAddress.trim();
    }

    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", username=").append(username);
        sb.append(", productName=").append(productName);
        sb.append(", discount=").append(discount);
        sb.append(", telephone=").append(telephone);
        sb.append(", totalPrice=").append(totalPrice);
        sb.append(", deliveryAddress=").append(deliveryAddress);
        sb.append(", orderTime=").append(orderTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
